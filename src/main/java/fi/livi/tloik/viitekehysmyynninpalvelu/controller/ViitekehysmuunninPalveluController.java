package fi.livi.tloik.viitekehysmyynninpalvelu.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import org.assertj.core.util.Lists;
import org.geolatte.geom.C3DM;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import fi.livi.vkm.dto.FeatureCollection;
import fi.livi.vkm.dto.InParameters;
import fi.livi.vkm.IViitekehysmuunnin;
import fi.livi.vkm.VkmVirheException;
import fi.livi.vkm.dto.geoJsonWrapper;
import fi.livi.vkm.util.VkmUtil;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@Configurable
@RequestMapping("/")
@ControllerAdvice

public class ViitekehysmuunninPalveluController {

    
    @Autowired
    private IViitekehysmuunnin palveluNG;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    @ApiIgnore
    public ModelAndView swaggerUi(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
   
    

    @RequestMapping(value = "muunna", method= RequestMethod.GET)
    public FeatureCollection yleisRajapinta(
    		
    		@RequestParam(name = "tunniste", required = false) String tunniste, 
    		
    		@RequestParam(name = "x", required = false) Double x,
            @RequestParam(name = "y", required = false) Double y,
            @RequestParam(name = "z", required = false) Double z,
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @RequestParam(name = "z_loppu", required = false) Double z_loppu,
            @RequestParam(name = "z_vaihtelu", required = false) Double z_vaihtelu,
            @ApiParam(value = "Oletusarvo 100")
            @RequestParam(name = "sade", required = false) Integer sade,
            
            @RequestParam(name = "tie", required = false) Integer tie,
            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
            @RequestParam(name = "aosa", required = false) Integer aosa,
            @RequestParam(name = "aet", required = false) Integer aet,
            @ApiParam(value = "Muodossa pp.kk.vvvv")
            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv")
            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,

            @RequestParam(name = "losa", required = false) Integer losa,
            @RequestParam(name = "let", required = false) Integer let,
            
            @RequestParam(name = "link_id", required = false) Integer link_id,
            @RequestParam(name = "m_arvo", required = false) Double m_arvo,
            @RequestParam(name = "link_id_loppu", required = false) Integer link_id_loppu,
            @RequestParam(name = "m_arvo_loppu", required = false) Double m_arvo_loppu,
            
            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
            @RequestParam(name = "katunimi", required = false) String katunimi,
            @RequestParam(name = "katunumero", required = false) Integer katunumero,
            @RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
            
            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @RequestParam(name = "tietyyppi", required = false) List<Integer> tietyyppi,
            
            @RequestParam(name = "ely", required = false) Integer ely,
            @RequestParam(name = "ualue", required = false) Integer ualue,
            @RequestParam(name = "maakuntakoodi", required = false) Integer maakuntakoodi,
            
            @ApiParam(value = "Arvolla 'false' pistemäinen haku, arvolla 'true' viivamainen haku, oletus 'false'")
            @RequestParam(name = "valihaku", required = false) String valihaku,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria, 6=lineaarilokaatio")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
    		@RequestParam(name = "json", required = false) String json
    		)
    
            throws JSONException {
    	
    			List<InParameters> kyselyLista = new ArrayList<InParameters>();
    			InParameters kysely;
    			boolean koordinaatitNull = (x == null && y == null && x_loppu == null && y_loppu == null && z == null && z_loppu == null && z_vaihtelu == null & sade == null);
    			boolean tieOsoiteNull = (tie == null && ajr == null && aosa == null && aet == null && losa == null && let == null);
    			boolean pvmNull = (tilannepvmAsString == null && kohdepvmAsString == null);
    			boolean linkOsoiteNull = (link_id == null && m_arvo == null && link_id_loppu == null && m_arvo_loppu == null);
    			boolean katuOsoiteNull = (kuntakoodi == null && katunimi == null && katunumero == null && katunumero_loppu == null);
    			boolean vaylaTyypitNull = (vaylan_luonne == null && tietyyppi == null);
    			boolean alueetNull = (ely == null && ualue == null && maakuntakoodi == null);
    			boolean metaParametritNull = (valihaku == null && tunniste == null && palautusarvot == null);
    			boolean muutKuinJsonNull = (koordinaatitNull && tieOsoiteNull && pvmNull && linkOsoiteNull && katuOsoiteNull && vaylaTyypitNull && alueetNull && metaParametritNull);
    			
    			if (json == null && !muutKuinJsonNull) {
    				kysely = new InParameters(tunniste, 
    											x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
    											tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
    											link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
    											kuntakoodi, katunimi, katunumero, katunumero_loppu, 
    											vaylan_luonne, tietyyppi, 
    											ely, ualue, maakuntakoodi,
    											valihaku, palautusarvot
    											);
    				kyselyLista.add(kysely);
    			}
    			else if (json != null && muutKuinJsonNull){
    				JSONArray jsonKysely;
    				JSONObject jsonData;
    				try {
    					jsonKysely = new JSONArray(json);
    				}
    				catch(Exception e) {
    					throw new JSONException("Virhe json-parametrissa: "+ e.getMessage());
    				}
    				if (jsonKysely != null ) {
    					for (int i = 0; i < jsonKysely.length(); i++) {
    						jsonData = jsonKysely.getJSONObject(i);
    						
    						tunniste = VkmUtil.getJsonString(jsonData, "tunniste");
    						x = VkmUtil.getJsonDouble(jsonData, "x");
    						y = VkmUtil.getJsonDouble(jsonData, "y");
    						x_loppu = VkmUtil.getJsonDouble(jsonData, "x_loppu");
    						y_loppu = VkmUtil.getJsonDouble(jsonData, "y_loppu");
    						z = VkmUtil.getJsonDouble(jsonData, "z");
    						z_loppu = VkmUtil.getJsonDouble(jsonData, "z_loppu");
    						z_vaihtelu = VkmUtil.getJsonDouble(jsonData, "z_yla");
    						sade = VkmUtil.getJsonInteger(jsonData, "sade");
    						tie = VkmUtil.getJsonInteger(jsonData, "tie");
    						ajr =  VkmUtil.getJsonString(jsonData, "ajr") != null ? VkmUtil.toIntegerList(jsonData.getString("ajr")) : null;
    						aosa = VkmUtil.getJsonInteger(jsonData, "aosa");
    						aet = VkmUtil.getJsonInteger(jsonData, "aet");
    						losa = VkmUtil.getJsonInteger(jsonData, "losa");
    						let = VkmUtil.getJsonInteger(jsonData, "let");
    						tilannepvmAsString = VkmUtil.getJsonString(jsonData, "tilannepvmAsString");
    						kohdepvmAsString = VkmUtil.getJsonString(jsonData, "kohdepvmAsString");
    						link_id = VkmUtil.getJsonInteger(jsonData, "link_id");
    						m_arvo = VkmUtil.getJsonDouble(jsonData, "m_arvo");
    						link_id_loppu = VkmUtil.getJsonInteger(jsonData, "link_id_loppu");
    						m_arvo_loppu = VkmUtil.getJsonDouble(jsonData, "m_arvo_loppu");
    						kuntakoodi = VkmUtil.getJsonInteger(jsonData, "kuntakoodi");
    						katunimi = VkmUtil.getJsonString(jsonData, "katunimi");
    						katunumero = VkmUtil.getJsonInteger(jsonData, "katunumero");
    						katunumero_loppu = VkmUtil.getJsonInteger(jsonData, "katunumero_loppu");
    						vaylan_luonne = VkmUtil.getJsonString(jsonData, "vaylan_luonne") != null ? VkmUtil.toIntegerList(jsonData.getString("vaylan_luonne")) : null;
    						tietyyppi =  VkmUtil.getJsonString(jsonData, "tietyyppi") != null ? VkmUtil.toIntegerList(jsonData.getString("tietyyppi")) : null;
    						ely = VkmUtil.getJsonInteger(jsonData, "ely");
    						ualue = VkmUtil.getJsonInteger(jsonData, "ualue");
    						maakuntakoodi = VkmUtil.getJsonInteger(jsonData, "maakuntakoodi");
    						valihaku = VkmUtil.getJsonString(jsonData, "valihaku");
    						palautusarvot =  VkmUtil.getJsonString(jsonData, "palautusarvot") != null ? VkmUtil.toIntegerList(jsonData.getString("palautusarvot")) : null;
    						
    						kysely = new InParameters(tunniste, 
														x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
														tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
														link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
														kuntakoodi, katunimi, katunumero, katunumero_loppu, 
														vaylan_luonne, tietyyppi, 
														ely, ualue, maakuntakoodi,
														valihaku, palautusarvot
														);
    						kyselyLista.add(kysely);
    					}
    				}
    				else
    					throw new JSONException("Virhe json-parametrissa.");
    			}
    			else if (json != null && !muutKuinJsonNull) {
    				throw new JSONException("Annettaessa json-parametri ei voi antaa muita parametreja.");
    			}
    			else {
    				throw new JSONException("Kyselyn parametrit olivat tyhjät.");
    			}
    			
                List<geoJsonWrapper> tulos = new ArrayList<geoJsonWrapper>();
                              
                for (int i = 0; i < kyselyLista.size(); i++) {
                	kyselyLista.get(i).setJsonLabel("MUUNNOS" + (i+1));
                }
                 
                for (InParameters inParametrit : kyselyLista) {
                	List<geoJsonWrapper> tempStore = palveluNG.muunnosController(inParametrit);
                	for (geoJsonWrapper response : tempStore) {
                		tulos.add(response);
                	}
                }
                
                
         return new FeatureCollection(tulos);
    }
       
    
}
    
    

    
//    @RequestMapping(value = "xyhaku", method = RequestMethod.GET)
//    public void haeKoordinaatilla(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "x", required = true) Double x,
//            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "tie", required = false) Integer tie,
//            @RequestParam(name = "aosa", required = false) Integer aosa,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
//            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
//            @ApiParam(value = "Oletusarvo 100")
//            @RequestParam(name = "sade", required = false) Integer sade,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//                
//    			yleisRajapinta(tunniste, 
//    				x, y, null, x_loppu, y_loppu, null, null, sade, 
//    				tie, ajr, aosa, null, tilannepvmAsString, kohdepvmAsString, null, null, 
//    				null, null, null, null, 
//    				null, null, null, null, 
//    				vaylan_luonne, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//    }
//    
//    
//    @RequestMapping(value = "tieosoitehaku", method= RequestMethod.GET)
//    public void haeTieosoitteella(
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "tie", required = true) Integer tie,
//            @RequestParam(name = "aosa", required = true) Integer aosa,
//            @RequestParam(name = "aet", required = true) Integer aet,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//    		@RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//		
//				yleisRajapinta(tunniste, 
//					null, null, null, null, null, null, null, null, 
//					tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, null, null, 
//					null, null, null, null, 
//					null, null, null, null, 
//					null, null, 
//					null, null, null,
//					null, palautusarvot, null);
//		
//    }
//	
//    
//	@RequestMapping(value = "tieosoitevali", method= RequestMethod.GET)
//    public void haeKokoTie(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "tie", required = true) Integer tie,
//            @RequestParam(name = "aosa", required = false) Integer aosa,
//            @RequestParam(name = "aet", required = false) Integer aet,
//            @RequestParam(name = "losa", required = false) Integer losa,
//            @RequestParam(name = "let", required = false) Integer let,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString
//            ) {
//    	
//    			yleisRajapinta(tunniste, 
//    				null, null, null, null, null, null, null, null, 
//    				tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
//    				null, null, null, null, 
//    				null, null, null, null, 
//    				null, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//    	
//    }
//    
//    
//    @RequestMapping(value = "geocode", method= RequestMethod.GET)
//    public void  geocode(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "kuntakoodi", required = true) Integer kuntakoodi,
//            @RequestParam(name = "katunimi", required = true) String katunimi,
//            @RequestParam(name = "katunumero", required = true) Integer katunumero,
//            @RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//
//    			yleisRajapinta(tunniste, 
//    				null, null, null, null, null, null, null, null, 
//    				null, null, null, null, null, null, null, null, 
//    				null, null, null, null, 
//    				kuntakoodi, katunimi, katunumero, katunumero_loppu, 
//    				null, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//
//    }
//    
//    
//    @RequestMapping(value = "reversegeocode", method= RequestMethod.GET)
//    public void reversegeocode(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste, 
//            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
//            @RequestParam(name = "katunimi", required = false) String katunimi,
//            @RequestParam(name = "x", required = true) Double x,
//            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
//            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @ApiParam(value = "Oletusarvo 100")
//            @RequestParam(name = "sade", required = false) Integer sade,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//    			
//    			yleisRajapinta(tunniste, 
//					x, y, null, x_loppu, y_loppu, null, null, sade, 
//					null, null, null, null, null, null, null, null, 
//					null, null, null, null, 
//					kuntakoodi, katunimi, null, null, 
//					null, null, 
//					null, null, null,
//					null, palautusarvot, null);
//                
//    }
//      
//    
////    @RequestMapping(value = "muunnin-post", method = RequestMethod.POST)
////    public Object handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException {
////    	
////    	String json = request.getParameter("json");
////    	return muunnin(json);           
////    }