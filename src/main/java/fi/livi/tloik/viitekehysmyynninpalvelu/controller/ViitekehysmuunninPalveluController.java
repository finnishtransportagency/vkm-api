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
import io.swagger.annotations.ApiParam;
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
    private static final List<Integer> DEFAULT_PALAUTUSARVOT = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Integer DEFAULT_TILANNEPVM_MIN_VUOSI = 1980;
    public LocalDate tilannepvm;
    public LocalDate kohdepvm;

    
    @Autowired
    private IViitekehysmuunnin palveluNG;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    @ApiIgnore
    public ModelAndView swaggerUi(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
    
    
//    @RequestMapping(value = "muunnin-post", method = RequestMethod.POST)
//    public Object handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException {
//    	
//    	String json = request.getParameter("json");
//    	return muunnin(json);           
//    }
    
    @RequestMapping(value = "xyhaku", params = { "x", "y" }, method = RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoite> haeKoordinaatilla(
    		@ApiParam(value = " ")@RequestParam(name = "tunniste", required = false) String tunniste,
            @ApiParam(value = " ", required = true)@RequestParam(name = "x") Double x,
            @ApiParam(value = " ", required = true)@RequestParam(name = "y") Double y,
            @ApiParam(value = " ")@RequestParam(name = "z", required = false) Double z,
            @ApiParam(value = " ")@RequestParam(name = "tie", required = false) Integer tie,
            @ApiParam(value = " ")@RequestParam(name = "osa", required = false) Integer osa,
            @ApiParam(value = " ")@RequestParam(name = "ajoradat", required = false) List<Integer> ajoradat,
            @ApiParam(value = " ")@RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @ApiParam(value = " ")@RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @ApiParam(value = " ")@RequestParam(name = "z_loppu", required = false) Double z_loppu,
            @ApiParam(value = " ")@RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @ApiParam(value = "Oletusarvo 100")@RequestParam(name = "sade", required = false) Integer sade,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1);

                if(sade == null){
                    sade = DEFAULT_SADE; 
                }
                if(sade == 0) {
                	throw new VkmVirheException("Virheellinen sade arvo.");
                }
                if(z == null){
                    z = 0.0;
                }
                if(z_loppu == null){
                    z_loppu = 0.0;
                }
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
                
                VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x, y, z), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, tilannepvm, kohdepvm, env, palautusarvot).orElse(null);


                if (x_loppu != null && y_loppu != null && z_loppu != null){
                    VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x_loppu, y_loppu, z_loppu), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, tilannepvm, kohdepvm, env, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setZ(loppu.getZ());
                    tulos.setDistance(loppu.getDistance());
                }
                
                //Viedään json-objekti listaan ja palautetaan listana eri rajapintojen json-palautusten yhdenmukaisuuden vuoksi
                List<fi.livi.vkm.dto.VkmTieosoite> tulosInList = new ArrayList<>();
                tulosInList.add(tulos);
                
                if(tulos == null) {
                	throw new VkmVirheException("Tieosoitetta ei löytynyt.");
                }

                return tulosInList;         
    }

	@RequestMapping(value = "tieosoitehaku", params = { "tie", "osa", "etaisyys" }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoite> haeTieosoitteella(
    		@ApiParam(value = " ")@RequestParam(name = "tunniste", required = false) String tunniste,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "tie") Integer tie,
            @ApiParam(value = " ", required = true)@RequestParam(name = "osa") Integer osa,
            @ApiParam(value = " ", required = true)@RequestParam(name = "etaisyys") Integer etaisyys,
            @ApiParam(value = " ")@RequestParam(name = "sade", required = false) Integer sade,
            @ApiParam(value = " ")@RequestParam(name = "ajoradat", required = false) List<Integer> ajoradat,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
        List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.emptyList();
        List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku;
        if(sade == null){
            sade = DEFAULT_SADE;
        }
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
        	pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku(tunniste, tie, osa, etaisyys, Lists.newArrayList(notNullAjoradat),sade, tilannepvm, kohdepvm, env, palautusarvot);
        } else {
        	if (kohdepvm == null) kohdepvm = LocalDate.now();
        	pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku(tunniste, tie, osa, etaisyys, Lists.newArrayList(notNullAjoradat),sade, tilannepvm, kohdepvm, env, palautusarvot);
        }
        return pistemainenTieosoiteHaku;
    }

    //yhdellä numrerolla (50) tulee ajoradat 1 ja 0 molemmat ulos, tarkoituskin?
    @RequestMapping(value = "tieosoitevali", method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.VkmTieosoiteVali> haeKokoTie(
            @ApiParam(value = " ")@RequestParam(name = "tunniste", required = false) String tunniste,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "tie") Integer tie,
            @ApiParam(value = " ")@RequestParam(name = "osa", required = false) Integer osa,
            @ApiParam(value = " ")@RequestParam(name = "etaisyys", required = false) Integer etaisyys,
            @ApiParam(value = " ")@RequestParam(name = "losa", required = false) Integer losa,
            @ApiParam(value = " ")@RequestParam(name = "let", required = false) Integer let,
            @ApiParam(value = "Oletusarvo 100")@RequestParam(name = "sade", required = false) Integer sade,
            @ApiParam(value = " ")@RequestParam(name = "ajoradat", required = false) List<Integer> ajoradat,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv")@RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
    		@RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException, NamingException, SQLException {
        List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1);                
        int alkuOsa = Optional.ofNullable(osa).orElse(Integer.MIN_VALUE);
        int alkuEtaisyys = Optional.ofNullable(etaisyys).orElse(Integer.MIN_VALUE);
        int loppuOsa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
        int loppuEtaisyys = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
                    
        if(sade == null){
            sade = DEFAULT_SADE;
        }
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

        List<fi.livi.vkm.dto.VkmTieosoiteVali> viivamainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(tunniste, tie, alkuOsa, alkuEtaisyys,
                loppuOsa, loppuEtaisyys, notNullAjoradat, sade, tilannepvm, kohdepvm, env, palautusarvot);
        
        return viivamainenTieosoiteHaku;
    }

    //X ja Y loppu tulee kahteen kertaan!
    @RequestMapping(value = "geocode", params = { "kuntakoodi", "katunimi", "katunumero" }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.GeocodeResult>  geocode( 
    		@ApiParam(value = " ")@RequestParam(name = "tunniste", required = false) String tunniste,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "kuntakoodi") Integer kuntakoodi,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "katunimi") String katunimi,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "katunumero") Integer katunumero,
    		@ApiParam(value = " ")@RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
    		@ApiParam(value = " ")@RequestParam(name = "sade", required = false) Integer sade,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot ) throws VkmVirheException {
                if(sade == null){
                    sade = DEFAULT_SADE;
                }
                if(palautusarvot == null){
                    palautusarvot = DEFAULT_PALAUTUSARVOT;
                }

        List<fi.livi.vkm.dto.GeocodeResult> tulos = palveluNG.geocode(tunniste, kuntakoodi, katunimi, katunumero, sade, palautusarvot);
            
        if(katunumero_loppu != null){
            List<fi.livi.vkm.dto.GeocodeResult> loppu = palveluNG.geocode(tunniste, kuntakoodi, katunimi, katunumero_loppu, sade, palautusarvot);
            for(int i = 0; i<tulos.size();i++)
            {
            tulos.get(i).setX(loppu.get(i).getX());
            tulos.get(i).setY(loppu.get(i).getY());
            tulos.get(i).setKatunumeroLoppu(loppu.get(i).getKatunumero());
            }

        }
        return tulos;
    }
    
    @RequestMapping(value = "reversegeocode", params = { "x", "y"  }, method= RequestMethod.GET)
    public List<fi.livi.vkm.dto.ReverseGeocodeResult> reversegeocode(
    		@ApiParam(value = " ")@RequestParam(name = "tunniste", required = false) String tunniste, 
    		@ApiParam(value = " ")@RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
    		@ApiParam(value = " ")@RequestParam(name = "katunimi", required = false) String katunimi,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "x") Double x,
    		@ApiParam(value = " ", required = true)@RequestParam(name = "y") Double y,
    		@ApiParam(value = " ")@RequestParam(name = "x_loppu", required = false) Double x_loppu,
    		@ApiParam(value = " ")@RequestParam(name = "y_loppu", required = false) Double y_loppu,
    		@ApiParam(value = " ")@RequestParam(name = "sade", required = false) Integer sade,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                if(sade == null){
                    sade = DEFAULT_SADE;
                }
                if(palautusarvot == null){
                    palautusarvot = DEFAULT_PALAUTUSARVOT;
                }


                ReverseGeocodeResult tulos = palveluNG.reverseGeocode(tunniste, kuntakoodi, katunimi, x, y, sade, palautusarvot).orElse(null);

                if (x_loppu != null && y_loppu != null){
                    ReverseGeocodeResult loppu = palveluNG.reverseGeocode(tunniste, kuntakoodi, katunimi, x_loppu, y_loppu, sade, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setKatunumero(loppu.getKatunumero());
                    tulos.setDistance(loppu.getDistance());

                }
                
              //Viedään json-objekti listaan ja palautetaan listana eri rajapintojen json-palautusten yhdenmukaisuuden vuoksi
                List<fi.livi.vkm.dto.ReverseGeocodeResult> tulosInList = new ArrayList<>();
                tulosInList.add(tulos);
        return tulosInList;
    }

    //TODO
    //Otetaan "haku" pois ja haetaan se sen sijaan jsonista

    //Ei toimi odotetulla tavalla
    //Muunnos-rajapinta
    @RequestMapping(value = "muunnin", method= RequestMethod.GET)
    public Object muunnin(@ApiParam(value = " ", required = true)@RequestParam(name = "json") String json) throws VkmVirheException, NamingException, SQLException {

            VkmRequest vkmreq = new VkmRequest(json);
            InParameters[] data = vkmreq.getData();
            List out = new ArrayList();
            for(int i=0;i<data.length;i++){
                //haun valinta parametrien perusteella
                if("xyhaku".equalsIgnoreCase(data[i].haku)){
                    
                        VkmTieosoite xyhaku = palveluNG.xyTieosoiteHaku(
                            data[i].tunniste,doublesToPoint(data[i].x,data[i].y,data[i].z), data[i].tie, data[i].osa, Lists.newArrayList(data[i].ajoradat), data[i].vaylat, data[i].sade, data[i].tilannepvm, data[i].kohdepvm, env,data[i].palautusarvot).orElse(null);
                       
                        if (data[i].x_loppu != null && data[i].y_loppu != null && data[i].z_loppu != null){
                            VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(data[i].tunniste, doublesToPoint(data[i].x_loppu,
                                data[i].y_loppu, data[i].z_loppu), data[i].tie, data[i].osa, Lists.newArrayList(data[i].ajoradat), data[i].vaylat, data[i].sade, data[i].tilannepvm, data[i].kohdepvm, env, data[i].palautusarvot).orElse(null);
                                xyhaku.setX(loppu.getX());
                                xyhaku.setY(loppu.getY());
                                xyhaku.setZ(loppu.getZ());
                                xyhaku.setDistance(loppu.getDistance());
                        }
                        out.add(xyhaku);
                    
                }
                if("tieosoitehaku".equalsIgnoreCase(data[i].haku)){
                        List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku
                        (data[i].tunniste, data[i].tie, data[i].osa, data[i].etaisyys, Lists.newArrayList(data[i].ajoradat),data[i].sade, data[i].tilannepvm, data[i].kohdepvm, env,data[i].palautusarvot);
                        out.add(pistemainenTieosoiteHaku);
                    
                }
                if("tieosoitevali".equalsIgnoreCase(data[i].haku)){
                	
                        List<fi.livi.vkm.dto.VkmTieosoiteVali> viivamainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(data[i].tunniste, data[i].tie, data[i].osa, data[i].etaisyys,
                        data[i].losa, data[i].let, data[i].ajoradat, data[i].sade, data[i].tilannepvm, data[i].kohdepvm, env, data[i].palautusarvot);
                        /*for(int j=1;j<viivamainenTieosoiteHaku.size();j++){
                            out.add(viivamainenTieosoiteHaku.get(j));
                        }*/
                        out.add(viivamainenTieosoiteHaku);
                        
                }
                
                //TODO: loppukatunumero
                if("geocode".equalsIgnoreCase(data[i].haku)){
                        List<fi.livi.vkm.dto.GeocodeResult> geocode = palveluNG.geocode(data[i].tunniste, data[i].kuntakoodi, data[i].katunimi, data[i].katunumero, data[i].sade, data[i].palautusarvot);
                        if(data[i].katunumero_loppu != null){
                            List<fi.livi.vkm.dto.GeocodeResult> loppu = palveluNG.geocode(data[i].tunniste, data[i].kuntakoodi, data[i].katunimi, data[i].katunumero_loppu, data[i].sade, data[i].palautusarvot);
                            for(int k = 0;k < geocode.size();k++)
                            {
                                geocode.get(k).setX(loppu.get(k).getX());
                                geocode.get(k).setY(loppu.get(k).getY());
                                geocode.get(k).setKatunumeroLoppu(loppu.get(k).getKatunumero());
                            }
                
                        }
                        
                        for(int j=1;j<geocode.size();j++){
                            out.add(geocode.get(j));
                        }
                        out.add(geocode);
                        
                }
                
                if("reversegeocode".equalsIgnoreCase(data[i].haku)){
                    
                        ReverseGeocodeResult tulos = palveluNG.reverseGeocode(data[i].tunniste, data[i].kuntakoodi, data[i].katunimi, data[i].x, data[i].y, data[i].sade, data[i].palautusarvot).orElse(null);

                        if (data[i].x_loppu != null && data[i].y_loppu != null){
                            ReverseGeocodeResult loppu = palveluNG.reverseGeocode(data[i].tunniste, data[i].kuntakoodi, data[i].katunimi, data[i].x_loppu, data[i].y_loppu, data[i].sade, data[i].palautusarvot).orElse(null);
                            tulos.setX(loppu.getX());
                            tulos.setY(loppu.getY());
                            tulos.setDistance(loppu.getDistance());
                            tulos.setKatunumero(loppu.getKatunumero());
                        }
                        out.add(tulos);
                }
        }
        //Muokataan palautuksesta objektien lista, ei listojen lista
        List outInner = new ArrayList();
        for (int i = 0; i < out.size(); i++) {
        	if (out.get(i) instanceof List) {
        		for (int j = 0; j < ((List)out.get(i)).size(); j++) {
        			outInner.add((((List) out.get(i)).get(j)));
        		}
        	}
        	else {
        		outInner.add(out.get(i));
        	}
        }
        return outInner;
            
    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y) {
        return VkmUtil.mkPoint(x, y);
    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y, Double z) {
        return VkmUtil.mkPoint(x, y, z);
    }

}