package fi.livi.tloik.viitekehysmyynninpalvelu.controller;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.assertj.core.util.Lists;
import org.geolatte.geom.C3DM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.format.annotation;
import org.springframework.web.servlet.ModelAndView;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.tloik.viitekehysmyynninpalvelu.request.VkmRequest;
import fi.livi.vkm.IViitekehysmuunnin;
import fi.livi.vkm.VkmVirheException;
import fi.livi.vkm.dto.ReverseGeocodeResult;
import fi.livi.vkm.dto.VkmTieosoite;
import fi.livi.vkm.util.VkmUtil;
import springfox.documentation.annotations.ApiIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Tämä on tehty testausta varten.
 *
 */
@RestController
@Configurable
@RequestMapping("/")
@ControllerAdvice

public class ViitekehysmuunninPalveluController {

    private static final Integer DEFAULT_SADE = 100;
    private static final Integer MAX_SADE = 1000;
    private static final Integer MIN_SADE = 1;
    private static final List<Integer> DEFAULT_PALAUTUSARVOT = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Integer DEFAULT_TILANNEPVM_MIN_VUOSI = 1900;

    
    @Autowired
    private IViitekehysmuunnin palveluNG;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    @ApiIgnore
    public ModelAndView swaggerUi(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
    
    @RequestMapping(value = "reversegeocode", params = { "x", "y"  }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.ReverseGeocodeResult> reversegeocode(@RequestParam(name = "tunniste", required = false) String tunniste, 
            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
            @RequestParam(name = "katunimi", required = false) String katunimi,
            @RequestParam(name = "x", required = true) Double x,
            @RequestParam(name = "y", required = true) Double y,
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                if(sade == null){
                    sade = DEFAULT_SADE;
                } else if(sade > MAX_SADE || sade < MIN_SADE) {
                	throw new VkmVirheException("Virheellinen sade-arvo.");
                }
                if(palautusarvot == null){
                    palautusarvot = DEFAULT_PALAUTUSARVOT;
                }

                ReverseGeocodeResult tulos = palveluNG.reverseGeocode(tunniste, kuntakoodi, katunimi, x, y, sade, palautusarvot).orElse(null);

                if (x_loppu != null && y_loppu != null){
                    ReverseGeocodeResult loppu = palveluNG.reverseGeocode(tunniste, kuntakoodi, katunimi, x_loppu, y_loppu, sade, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setKatunimiLoppu(loppu.getKatunimi());
                    tulos.setKatunumero(loppu.getKatunumero());
                    tulos.setValimatka(loppu.getValimatka());
                    tulos.setKuntakoodiLoppu(loppu.getKuntakoodi());
                    tulos.setKuntanimiLoppu(loppu.getKuntanimi());
                    tulos.setMaakuntakoodiLoppu(loppu.getMaakuntakoodi());
                    tulos.setMaakuntanimiLoppu(loppu.getMaakuntanimi());
                    tulos.setUrakkaAlueLoppu(loppu.getUalue());
                    tulos.setElyLoppu(loppu.getEly());
                    tulos.setUaluenimi_loppu(loppu.getUaluenimi());
                }
                
              //Viedään json-objekti listaan ja palautetaan listana eri rajapintojen json-palautusten yhdenmukaisuuden vuoksi
                List<fi.livi.vkm.dto.ReverseGeocodeResult> tulosInList = new ArrayList<>();
                tulosInList.add(tulos);
        return tulosInList;
    }
    
    @RequestMapping(value = "xyhaku", params = { "x", "y" }, method = RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoite> haeKoordinaatilla(@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "x", required = true) Double x,
            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "z", required = false) Double z,
            @RequestParam(name = "tie", required = false) Integer tie,
            @RequestParam(name = "aosa", required = false) Integer osa,
            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @RequestParam(name = "z_loppu", required = false) Double z_loppu, //z väliaikaisesti poissa käytöstä
            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @RequestParam(name = "sade", required = false) Integer sade,
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                List<Integer> notNullAjoradat = ajr != null ? ajr : Lists.newArrayList(0, 1);

                if(sade == null){
                    sade = DEFAULT_SADE;
                } else if(sade > MAX_SADE || sade < MIN_SADE) {
                	throw new VkmVirheException("Virheellinen sade-arvo.");
                }
//                if(z == null){
//                    z = 0.0;
//                }
//                if(z_loppu == null){
//                    z_loppu = 0.0;
//                }
                if(palautusarvot == null){
                    palautusarvot = DEFAULT_PALAUTUSARVOT;
                }
                
//                if (tilannepvmAsString != null) {
//                	tilannepvm = LocalDate.parse(tilannepvmAsString, DATE_FORMAT);
//                	
//                    if(tilannepvm.getYear() < DEFAULT_TILANNEPVM_MIN_VUOSI || tilannepvm.isAfter(LocalDate.now())) {
//                    	throw new VkmVirheException("Aineistoa ei ole saatavilla kyseisellä tilannepäivämäärällä");
//                    }
//                }
//                else {
//                    tilannepvm = null;
//                }
//                if (kohdepvmAsString != null) {
//                	kohdepvm = LocalDate.parse(kohdepvmAsString, DATE_FORMAT);
//                }
//                else {
//                    kohdepvm = null;
//                }
                
                VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x, y), tie, osa, sade, Lists.newArrayList(notNullAjoradat), vaylan_luonne, palautusarvot).orElse(null);


                if (x_loppu != null && y_loppu != null){
                    VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x_loppu, y_loppu), tie, osa, sade, Lists.newArrayList(notNullAjoradat), vaylan_luonne, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setZ(loppu.getZ());
                    tulos.setDistance(loppu.getValimatka());
                    tulos.setMaakuntakoodi_loppu(loppu.getMaakuntakoodi());
                    tulos.setMaakuntanimi_loppu(loppu.getMaakuntanimi());
                    tulos.setKuntakoodi_loppu(loppu.getKuntakoodi());
                    tulos.setKuntanimi_loppu(loppu.getKuntanimi());
                    tulos.setUrakkaAlue_loppu(loppu.getUalue());
                    tulos.setEly_loppu(loppu.getEly());
                    tulos.setVaylanLuonne_loppu(loppu.getVaylan_luonne());
                    tulos.setLosa(loppu.getAosa());
                    tulos.setLet(loppu.getAet());
                    tulos.setUrakkaAlueNimi_loppu(loppu.getUaluenimi());
                }
                
                //Viedään json-objekti listaan ja palautetaan listana eri rajapintojen json-palautusten yhdenmukaisuuden vuoksi
                List<fi.livi.vkm.dto.VkmTieosoite> tulosInList = new ArrayList<>();
                tulosInList.add(tulos);
                
                if(tulos == null) {
                	throw new VkmVirheException("Tieosoitetta ei löytynyt.");
                }

                return tulosInList;         
    }
    
    
    @RequestMapping(value = "geocode", params = { "kuntakoodi", "katunimi", "katunumero" }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.GeocodeResult>  geocode( @RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "kuntakoodi", required = true) Integer kuntakoodi,
            @RequestParam(name = "katunimi", required = true) String katunimi,
            @RequestParam(name = "katunumero", required = true) Integer katunumero,
            @RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot ) throws VkmVirheException {
                if(palautusarvot == null){
                    palautusarvot = DEFAULT_PALAUTUSARVOT;
                }

        List<fi.livi.vkm.dto.GeocodeResult> tulos = palveluNG.geocode(tunniste, kuntakoodi, katunimi, katunumero, palautusarvot);
        
        if(katunumero_loppu != null) {
        	List<fi.livi.vkm.dto.GeocodeResult> loppu = palveluNG.geocode(tunniste, kuntakoodi, katunimi, katunumero_loppu, palautusarvot);
        	for(int i = 0; i<tulos.size();i++) {
        	tulos.get(i).setX(loppu.get(i).getX());
        	tulos.get(i).setY(loppu.get(i).getY());
        	tulos.get(i).setKatunumeroLoppu(loppu.get(i).getKatunumero());
        	tulos.get(i).setKatunimiLoppu(loppu.get(i).getKatunimi());
        	tulos.get(i).setKatunimiSeLoppu(loppu.get(i).getKatunimi_se());
        	tulos.get(i).setKuntakoodiLoppu(loppu.get(i).getKuntakoodi());
        	tulos.get(i).setKuntanimiLoppu(loppu.get(i).getKuntanimi());
        	tulos.get(i).setMaakuntakoodiLoppu(loppu.get(i).getMaakuntakoodi());
        	tulos.get(i).setMaakuntanimiLoppu(loppu.get(i).getMaakuntanimi());
        	tulos.get(i).setUrakkaAlueLoppu(loppu.get(i).getUrakka());
        	tulos.get(i).setElyLoppu(loppu.get(i).getEly());
        	tulos.get(i).setUrakkaAluenimi_loppu(loppu.get(i).getUaluenimi());
        	}
        }

        return tulos;
    }
    
    
    @RequestMapping(value = "tieosoitevali", method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoiteVali> haeKokoTie(
    		@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "tie", required = true) Integer tie,
            @RequestParam(name = "aosa", required = false) Integer osa,
            @RequestParam(name = "aet", required = false) Integer aet,
            @RequestParam(name = "losa", required = false) Integer losa,
            @RequestParam(name = "let", required = false) Integer let,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString
    		) throws VkmVirheException, NamingException, SQLException {
    	List<Integer> notNullAjoradat = ajr != null ? ajr : Lists.newArrayList(0, 1);
    	int alkuOsa = Optional.ofNullable(osa).orElse(Integer.MIN_VALUE);
    	int loppuOsa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
		int alkuEtaisyys = Optional.ofNullable(aet).orElse(Integer.MIN_VALUE);
		int loppuEtaisyys = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
		LocalDate tilannepvm;
	    LocalDate kohdepvm;
    	
    	if(palautusarvot == null) {
    		palautusarvot = DEFAULT_PALAUTUSARVOT;
    	}
    	
    	if (tilannepvmAsString != null) {
            tilannepvm = LocalDate.parse(tilannepvmAsString, DATE_FORMAT);
            
            if(tilannepvm.getYear() < DEFAULT_TILANNEPVM_MIN_VUOSI || tilannepvm.isAfter(LocalDate.now())) {
            	throw new VkmVirheException("Aineistoa ei ole saatavilla kyseisellä tilannepäivämäärällä");
            }
        }
        else {
            tilannepvm = null;
        }
        if (kohdepvmAsString != null) {
            kohdepvm = LocalDate.parse(kohdepvmAsString, DATE_FORMAT);
        }
        else {
            kohdepvm = null;
        }

        List<fi.livi.vkm.dto.VkmTieosoiteVali> viivamainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(tunniste, tie, alkuOsa, loppuOsa, alkuEtaisyys, loppuEtaisyys, notNullAjoradat, palautusarvot, tilannepvm, kohdepvm);
        
        return viivamainenTieosoiteHaku;
    }
    
	@RequestMapping(value = "tieosoitehaku", params = { "tie", "aosa", "aet" }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoite> haeTieosoitteella(@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "tie", required = true) Integer tie,
            @RequestParam(name = "aosa", required = true) Integer osa,
            @RequestParam(name = "aet", required = true) Integer aet,
            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @RequestParam(name= "kohdepvm", required = false) String kohdepvmAsString,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
		LocalDate tilannepvm;
	    LocalDate kohdepvm;
        List<Integer> notNullAjoradat = ajr != null ? ajr : Lists.newArrayList(0, 1);

        if(palautusarvot == null){
            palautusarvot = DEFAULT_PALAUTUSARVOT;
        }
        
        if (tilannepvmAsString != null) {
            tilannepvm = LocalDate.parse(tilannepvmAsString, DATE_FORMAT);
            
            if(tilannepvm.getYear() < DEFAULT_TILANNEPVM_MIN_VUOSI || tilannepvm.isAfter(LocalDate.now())) {
            	throw new VkmVirheException("Aineistoa ei ole saatavilla kyseisellä tilannepäivämäärällä");
            }
        }
        else {
            tilannepvm = null;
        }
        if (kohdepvmAsString != null) {
            kohdepvm = LocalDate.parse(kohdepvmAsString, DATE_FORMAT);
        }
        else {
            kohdepvm = null;
        }
        
        if (tilannepvm == null) {
        	List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku(tunniste, tie, osa, aet, Lists.newArrayList(notNullAjoradat), palautusarvot);
        	return pistemainenTieosoiteHaku;
        }
        else {
        	if (kohdepvm == null) kohdepvm = LocalDate.now();
        	List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHistoriaHaku(tunniste, tie, osa, aet, Lists.newArrayList(notNullAjoradat), tilannepvm, kohdepvm, palautusarvot);
        	return pistemainenTieosoiteHaku;
        }
    }

    
    
//    @RequestMapping(value = "muunnin-post", method = RequestMethod.POST)
//    public Object handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException {
//    	
//    	String json = request.getParameter("json");
//    	return muunnin(json);           
//    }
    
//    @RequestMapping(value = "xyhaku", params = { "x", "y" }, method = RequestMethod.GET)
//    public List<fi.livi.vkm.dto.VkmTieosoite> haeKoordinaatilla(@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "x", required = true) Double x,
//            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "z", required = false) Double z,
//            @RequestParam(name = "tie", required = false) Integer tie,
//            @RequestParam(name = "osa", required = false) Integer osa,
//            @RequestParam(name = "ajoradat", required = false) List<Integer> ajoradat,
//            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
//            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @RequestParam(name = "z_loppu", required = false) Double z_loppu,
//            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
//            @RequestParam(name = "sade", required = false) Integer sade,
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
//                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1);
//
//                if(sade == null){
//                    sade = DEFAULT_SADE; 
//                }
//                if(sade == 0) {
//                	throw new VkmVirheException("Virheellinen sade arvo.");
//                }
//                if(z == null){
//                    z = 0.0;
//                }
//                if(z_loppu == null){
//                    z_loppu = 0.0;
//                }
//                if(palautusarvot == null){
//                    palautusarvot = DEFAULT_PALAUTUSARVOT;
//                }
//                
//                if (tilannepvmAsString != null) {
//                	tilannepvm = LocalDate.parse(tilannepvmAsString, DATE_FORMAT);
//                	
//                    if(tilannepvm.getYear() < DEFAULT_TILANNEPVM_MIN_VUOSI || tilannepvm.isAfter(LocalDate.now())) {
//                    	throw new VkmVirheException("Aineistoa ei ole saatavilla kyseisellä tilannepäivämäärällä");
//                    }
//                }
//                else {
//                    tilannepvm = null;
//                }
//                if (kohdepvmAsString != null) {
//                	kohdepvm = LocalDate.parse(kohdepvmAsString, DATE_FORMAT);
//                }
//                else {
//                    kohdepvm = null;
//                }
//                
//                VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x, y, z), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, tilannepvm, kohdepvm, env, palautusarvot).orElse(null);
//
//
//                if (x_loppu != null && y_loppu != null && z_loppu != null){
//                    VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x_loppu, y_loppu, z_loppu), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, tilannepvm, kohdepvm, env, palautusarvot).orElse(null);
//                    tulos.setX(loppu.getX());
//                    tulos.setY(loppu.getY());
//                    tulos.setZ(loppu.getZ());
//                    tulos.setDistance(loppu.getDistance());
//                }
//                
//                //Viedään json-objekti listaan ja palautetaan listana eri rajapintojen json-palautusten yhdenmukaisuuden vuoksi
//                List<fi.livi.vkm.dto.VkmTieosoite> tulosInList = new ArrayList<>();
//                tulosInList.add(tulos);
//                
//                if(tulos == null) {
//                	throw new VkmVirheException("Tieosoitetta ei löytynyt.");
//                }
//
//                return tulosInList;         
//    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y) {
        return VkmUtil.mkPoint(x, y);
    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y, Double z) {
        return VkmUtil.mkPoint(x, y, z);
    }

}