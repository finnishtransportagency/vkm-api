package fi.livi.tloik.viitekehysmyynninpalvelu.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fi.livi.tloik.viitekehysmyynninpalvelu.request.VkmRequest;
import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;

import org.assertj.core.util.Lists;
import org.geolatte.geom.C3DM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.format.annotation;

import fi.livi.vkm.IViitekehysmuunnin;
import fi.livi.vkm.VkmVirheException;
import fi.livi.vkm.dto.VkmTieosoite;
import fi.livi.vkm.util.TrDbUtil;
import fi.livi.vkm.util.VkmUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Tämä on tehty testausta varten.
 *
 */
@RestController
@Configurable
@RequestMapping("vkm")
@ControllerAdvice
public class ViitekehysmuunninPalveluController {

    private static final Integer DEFAULT_SADE = 100;

    @Autowired
    private IViitekehysmuunnin palveluNG;
    
    @Autowired
    private Environment env;

    @RequestMapping(value = "xyhaku", params = { "x", "y" }, method= RequestMethod.GET)
    public String haeKoordinaatilla(@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "x", required = true) Double x,
            @RequestParam(name = "y", required = true) Double y,
            @RequestParam(name = "z", required = false) Double z,
            @RequestParam(name = "tie", required = false) Integer tie,
            @RequestParam(name = "osa", required = false) Integer osa,
            @RequestParam(name = "ajorata", required = false) List<Integer> ajoradat,
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @RequestParam(name = "z_loppu", required = false) Double z_loppu,
            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1, 2);
                if(sade == null){
                    sade = DEFAULT_SADE;
                }
                if(z == null){
                    z = 0.0;
                }
                if(z_loppu == null){
                    z_loppu = 0.0;
                }

                VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x, y, z), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, palautusarvot).orElse(null);

                if (x_loppu != null && y_loppu != null && z_loppu != null){
                    VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x_loppu, y_loppu, z_loppu), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setZ(loppu.getZ());
                    tulos.setDistance(loppu.getDistance());
                }
                
        return tulos.toString();
    }

    @RequestMapping(value = "tieosoitehaku", params = { "tie", "osa", "etaisyys" }, method= RequestMethod.GET)
    public String haeTieosoitteella(@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "tie", required = true) Integer tie,
            @RequestParam(name = "osa", required = true) Integer osa,
            @RequestParam(name = "etaisyys", required = true) Integer etaisyys,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "ajorata", required = false) List<Integer> ajoradat,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
        List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.emptyList();
        if(sade == null){
            sade = DEFAULT_SADE;
        }
        List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku(tunniste, tie, osa, etaisyys, Lists.newArrayList(notNullAjoradat),sade,palautusarvot);
        return pistemainenTieosoiteHaku.toString();
    }

    @RequestMapping(value = "tieosoitevali", method= RequestMethod.GET)
    public String haeKokoTie(@RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "tie", required = true) Integer tie,
            @RequestParam(name = "osa", required = false) Integer osa,
            @RequestParam(name = "etaisyys", required = false) Integer etaisyys,
            @RequestParam(name = "losa", required = false) Integer losa,
            @RequestParam(name = "let", required = false) Integer let,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "ajorata", required = false) List<Integer> ajoradat,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
        List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1, 2);
        int alkuOsa = Optional.ofNullable(osa).orElse(0);
        int alkuEtaisyys = Optional.ofNullable(etaisyys).orElse(0);
        int loppuOsa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
        int loppuEtaisyys = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
        if(sade == null){
            sade = DEFAULT_SADE;
        }
        List<fi.livi.vkm.dto.VkmTieosoiteVali> viivamainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(tunniste, tie, alkuOsa, alkuEtaisyys,
                loppuOsa, loppuEtaisyys, notNullAjoradat, sade, palautusarvot);
        return viivamainenTieosoiteHaku.toString();
    }

    @RequestMapping(value = "geocode", params = { "kuntakoodi", "katunimi", "katunumero" }, method= RequestMethod.GET)
    public String geocode( @RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "kuntakoodi", required = true) Integer kunta,
            @RequestParam(name = "katunimi", required = true) String katunimi,
            @RequestParam(name = "katunumero", required = true) Integer katunumero,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot ) throws VkmVirheException {
                if(sade == null){
                    sade = DEFAULT_SADE;
                }
        List<fi.livi.vkm.dto.GeocodeResult> geocode = palveluNG.geocode(tunniste,kunta,katunimi,katunumero,sade,palautusarvot);
        return geocode.toString();
    }
    
    @RequestMapping(value = "reversegeocode", params = { "x", "y"  }, method= RequestMethod.GET)
    public String geocode(@RequestParam(name = "tunniste", required = false) String tunniste, 
            @RequestParam(name = "x", required = true) Double x,
            @RequestParam(name = "y", required = true) Double y,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                if(sade == null){
                    sade = DEFAULT_SADE;
                }
        return palveluNG.reverseGeocode(tunniste,x,y,sade,palautusarvot).orElse(null).toString();
    }

    //Rakennetaan yleishakua, kopioitu tieosoite_to_koordista
    //Lisätään if ehdot eri hauille
    //Vastaa VKM Tieosoite-rajapintaa
    @RequestMapping(value = "Yleishaku", method= RequestMethod.GET)
    public Object yleisHakuTest(@RequestParam(name = "Haku", required = true) String haku,
            @RequestParam(name = "tunniste", required = false) String tunniste,
            @RequestParam(name = "x", required = false) Double x,
            @RequestParam(name = "y", required = false) Double y,
            @RequestParam(name = "z", required = false) Double z,
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @RequestParam(name = "z_loppu", required = false) Double z_loppu,
            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @RequestParam(name = "tie", required = false) Integer tie,
            @RequestParam(name = "osa", required = false) Integer osa,
            @RequestParam(name = "etaisyys", required = false) Integer etaisyys,
            @RequestParam(name = "losa", required = false) Integer losa,
            @RequestParam(name = "let", required = false) Integer let,
            @RequestParam(name = "sade", required = false) Integer sade,
            @RequestParam(name = "ajoradat", required = false) List<Integer> ajoradat,
            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
            @RequestParam(name = "katunimi", required = false) String katunimi,
            @RequestParam(name = "katunumero", required = false) Integer katunumero,
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot) throws VkmVirheException {
                

                if(sade == null){
                    sade = DEFAULT_SADE;
                }
                if(z == null){
                    z = 0.0;
                }
                if(z_loppu == null){
                    z_loppu = 0.0;
                }

            //haun valinta parametrien perusteella
            if("xyhaku".equalsIgnoreCase(haku)){
                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1, 2);
                VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x, y, z), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, palautusarvot).orElse(null);

                if (x_loppu != null && y_loppu != null && z_loppu != null){
                    VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(tunniste, doublesToPoint(x_loppu, y_loppu, z_loppu), tie, osa,  Lists.newArrayList(notNullAjoradat), vaylan_luonne, sade, palautusarvot).orElse(null);
                    tulos.setX(loppu.getX());
                    tulos.setY(loppu.getY());
                    tulos.setZ(loppu.getZ());
                    tulos.setDistance(loppu.getDistance());
                }
                return tulos.toString();
            }
            if("tieosoitehaku".equalsIgnoreCase(haku)){
                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.newArrayList(0, 1, 2);
                List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku(tunniste, tie, osa, etaisyys, Lists.newArrayList(notNullAjoradat),sade,palautusarvot);
                return pistemainenTieosoiteHaku.toString();
            }
            if("tieosoitevali".equalsIgnoreCase(haku)) {
                List<Integer> notNullAjoradat = ajoradat != null ? ajoradat : Lists.emptyList();
                int alkuOsa = Optional.ofNullable(osa).orElse(0);
                int alkuEtaisyys = Optional.ofNullable(etaisyys).orElse(0);
                int loppuOsa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
                int loppuEtaisyys = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
    
                List<fi.livi.vkm.dto.VkmTieosoiteVali> pistemainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(tunniste, tie, alkuOsa, alkuEtaisyys,
                loppuOsa, loppuEtaisyys, notNullAjoradat,sade, palautusarvot);
                return pistemainenTieosoiteHaku.toString();
            }
            
            if("geocode".equalsIgnoreCase(haku)){
                List<fi.livi.vkm.dto.GeocodeResult> geocode = palveluNG.geocode(tunniste, kuntakoodi, katunimi, katunumero, sade, palautusarvot);
                return geocode.toString();
            }
            if("reversegeocode".equalsIgnoreCase(haku)){
                return palveluNG.reverseGeocode(tunniste, x, y, sade, palautusarvot).orElse(null).toString();
            }
            else {
                return null;
            }
    }

    //Muunnos-rajapinta
    @RequestMapping(value = "muunnin", method= RequestMethod.GET)
    public Object muunnin(@RequestParam(name = "Haku", required = true) String haku,
        @RequestParam(name = "json", required = true) String json,
        @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
        @RequestParam(name = "tilannepvm", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate tilannepvm,
        @RequestParam(name = "tilannepvmloppu", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate tilannepvmloppu,
        @RequestParam(name = "kohdepvm", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate kohdepvm) throws VkmVirheException {


            VkmRequest vkmreq = new VkmRequest(haku,json);
            InParameters[] data = vkmreq.getData();
            List out = new ArrayList();

            //haun valinta parametrien perusteella
            if("xyhaku".equalsIgnoreCase(haku)){
                
                for(int i=0;i<data.length;i++){
                    VkmTieosoite tulos = palveluNG.xyTieosoiteHaku(
                        data[i].tunniste,doublesToPoint(data[i].x,data[i].y,data[i].z), data[i].tie, data[i].osa, Lists.newArrayList(data[i].ajoradat), data[i].vaylat, data[i].sade,data[i].palautusarvot).orElse(null);

                    if (data[i].x_loppu != null && data[i].y_loppu != null && data[i].z_loppu != null){
                        VkmTieosoite loppu = palveluNG.xyTieosoiteHaku(data[i].tunniste, doublesToPoint(data[i].x_loppu,
                            data[i].y_loppu, data[i].z_loppu), data[i].tie, data[i].osa, Lists.newArrayList(data[i].ajoradat), data[i].vaylat, data[i].sade, data[i].palautusarvot).orElse(null);
                        tulos.setX(loppu.getX());
                        tulos.setY(loppu.getY());
                        tulos.setZ(loppu.getZ());
                        tulos.setDistance(loppu.getDistance());
                    }
                    if(tilannepvm != null && kohdepvm != null) {
                		VkmTieosoite kohde = TrDbUtil.getTieosoitteenHistoriaFromTr(env, tulos, tilannepvm, kohdepvm);
                		tulos.setTieosoite(kohde);
                	}
                    out.add(tulos);
                }
                return out.toString();
                
            }
            if("tieosoitehaku".equalsIgnoreCase(haku)){
                
                for(int i=0;i<data.length;i++){
                    List<fi.livi.vkm.dto.VkmTieosoite> pistemainenTieosoiteHaku = palveluNG.pistemainenTieosoiteHaku
                    (data[i].tunniste, data[i].tie, data[i].osa, data[i].etaisyys, Lists.newArrayList(data[i].ajoradat),data[i].sade,data[i].palautusarvot);
                    for(int j=0;j<pistemainenTieosoiteHaku.size();j++){
                        out.add(pistemainenTieosoiteHaku.get(j));
                    }
                    
                }
                return out.toString();
            }
            if("tieosoitevali".equalsIgnoreCase(haku)){
                
                for(int i=0;i<data.length;i++){
                    List<fi.livi.vkm.dto.VkmTieosoiteVali> viivamainenTieosoiteHaku = palveluNG.viivamainenTieosoiteHaku(data[i].tunniste, data[i].tie, data[i].osa, data[i].etaisyys,
                    data[i].losa, data[i].let, data[i].ajoradat, data[i].sade, data[i].palautusarvot);
                    for(int j=0;j<viivamainenTieosoiteHaku.size();j++){
                        out.add(viivamainenTieosoiteHaku.get(j));
                    }
                    
                }
                return out.toString();
            }
            
            if("geocode".equalsIgnoreCase(haku)){
                
                for(int i=0;i<data.length;i++){
                    List<fi.livi.vkm.dto.GeocodeResult> geocode = palveluNG.geocode(data[i].tunniste, data[i].kuntakoodi, data[i].katunimi, data[i].katunumero, data[i].sade, data[i].palautusarvot);
                    for(int j=0;j<geocode.size();j++){
                        out.add(geocode.get(j));
                    }
                    
                }
                return out.toString();
            }

            if("reversegeocode".equalsIgnoreCase(haku)){
                
                for(int i=0;i<data.length;i++){
                    out.add(palveluNG.reverseGeocode(data[i].tunniste, data[i].x, data[i].y, data[i].sade, data[i].palautusarvot).orElse(null));
                }
                return out.toString();
                
                
            }
            
            else {
                return null;
            }
            
    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y) {
        return VkmUtil.mkPoint(x, y);
    }

    private static org.geolatte.geom.Point<C3DM> doublesToPoint(Double x, Double y, Double z) {
        return VkmUtil.mkPoint(x, y, z);
    }

}