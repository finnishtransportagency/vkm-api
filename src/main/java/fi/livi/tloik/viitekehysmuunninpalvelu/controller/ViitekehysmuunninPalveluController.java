package fi.livi.tloik.viitekehysmuunninpalvelu.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fi.livi.vkm.dto.FeatureCollection;
import fi.livi.vkm.dto.InParameters;
import fi.livi.vkm.IViitekehysmuunnin;
import fi.livi.vkm.VkmVirheException;
import fi.livi.vkm.dto.geoJsonWrapper;
import fi.livi.vkm.service.ViitekehysmuunninNGPalvelu;
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
    
    private boolean addMetadata = false;
    
    public static final String API_VERSION = "1.0.0";
    
    // Comment for build nr 0006
    
    
    @RequestMapping(value = "versio", method = RequestMethod.GET)
	@ResponseBody
	public String getVersion() {
	    return "API-versio: " + API_VERSION + ", CORE-versio: " + VkmUtil.CORE_VERSION;
	}
    
    
    @RequestMapping(value = "rajapintakuvaus", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getRajapintakuvaus()
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("viitekehysmuunnin-2020-rajapintakuvaus.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }

    
    @RequestMapping(method = RequestMethod.GET)
    @ApiIgnore
    public ModelAndView swaggerUi(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
    
    
    @RequestMapping(value = "muunna", method = RequestMethod.POST)
    @ResponseBody
    public FeatureCollection handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException, IOException {
    	String json = request.getParameter("json");
    	String metadata = request.getParameter("metadata");
    	FeatureCollection fc = yleisRajapinta(null,null,null,null,null,null,null,null,null,null,
    			null,null,null,null,null,null,null,null,null,null,
    			null,null,null,null,null,null,null,null,null,null,
    			null,null,json,metadata,null,null,null,null,null);
    	return fc;           
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
            @RequestParam(name = "ajorata", required = false) List<Integer> ajr,
            @RequestParam(name = "osa", required = false) Integer aosa,
            @RequestParam(name = "etaisyys", required = false) Integer aet,
            @ApiParam(value = "Muodossa pp.kk.vvvv")
            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv")
            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,

            @RequestParam(name = "osa_loppu", required = false) Integer losa,
            @RequestParam(name = "etaisyys_loppu", required = false) Integer let,
            
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
            
            @ApiParam(value = "Arvolla 'false' pistemäinen haku, arvolla 'true' viivamainen haku. Oletus 'false'.")
            @RequestParam(name = "valihaku", required = false) String valihaku,
            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria, 6=lineaarilokaatio")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
    		@RequestParam(name = "json", required = false) String json,
    		@ApiParam(value = "Vain json-parametria käytettäessä. Arvolla 'true' antaa FeatureCollectionin metatiedot. Oletus 'false'.")
            @RequestParam(name = "metadata", required = false) String metadata,
    		
    		// Aliakset tietyille in-parametreille
    		@RequestParam(name = "ajr", required = false) @ApiIgnore List<Integer> ajr2,
    		@RequestParam(name = "aosa", required = false) @ApiIgnore Integer aosa2,
            @RequestParam(name = "aet", required = false) @ApiIgnore Integer aet2,
            @RequestParam(name = "losa", required = false) @ApiIgnore Integer losa2,
            @RequestParam(name = "let", required = false) @ApiIgnore Integer let2
    		)
    
            throws JSONException {
    	
    			//Aliasten käsittely
    			ajr = (ajr == null && ajr2 != null) ? ajr2 : ajr;
    			aosa = (aosa == null && aosa2 != null) ? aosa2 : aosa;
    			aet = (aet == null && aet2 != null) ? aet2 : aet;
    			losa = (losa == null && losa2 != null) ? losa2 : losa;
    			let = (let == null && let2 != null) ? let2 : let;
    	
    			List<InParameters> kyselyLista = new ArrayList<InParameters>();
    			InParameters kysely;
    			
    			//Tarkistetaan minkä tyyppiset parametrit null
    			boolean koordinaatitNull = (x == null && y == null && x_loppu == null && y_loppu == null && z == null && z_loppu == null && z_vaihtelu == null & sade == null);
    			boolean tieOsoiteNull = (tie == null && ajr == null && aosa == null && aet == null && losa == null && let == null);
    			boolean pvmNull = (tilannepvmAsString == null && kohdepvmAsString == null);
    			boolean linkOsoiteNull = (link_id == null && m_arvo == null && link_id_loppu == null && m_arvo_loppu == null);
    			boolean katuOsoiteNull = (kuntakoodi == null && katunimi == null && katunumero == null && katunumero_loppu == null);
    			boolean vaylaTyypitNull = (vaylan_luonne == null && tietyyppi == null);
    			boolean alueetNull = (ely == null && ualue == null && maakuntakoodi == null);
    			boolean metaParametritNull = (valihaku == null && tunniste == null && palautusarvot == null);
    			boolean muutKuinJsonNull = (koordinaatitNull && tieOsoiteNull && pvmNull && linkOsoiteNull && katuOsoiteNull && vaylaTyypitNull && alueetNull && metaParametritNull);
    			
    			//Tarkistetaan halutaanko FeatureCollectionin metadata
    			Boolean addMetadata = (metadata != null && metadata.trim().toUpperCase().equals("TRUE"));
    			
    			//Kyseessä yksittäinen muunnos, ei json-parametrin kautta
    			if (json == null && !muutKuinJsonNull) {
    				kysely = new InParameters(tunniste, 
    											x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
    											tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
    											link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
    											kuntakoodi, katunimi, katunumero, katunumero_loppu, 
    											vaylan_luonne, tietyyppi, 
    											ely, ualue, maakuntakoodi,
    											valihaku, palautusarvot, addMetadata
    											);
    				kyselyLista.add(kysely);
    			}
    			//Kyseessä muunnos json-parametrin kautta
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
    						
    						//Haetaan muuttujiin arvot jsonista
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
    						ajr =  VkmUtil.getJsonString(jsonData, "ajorata") != null ? VkmUtil.toIntegerList(jsonData.getString("ajorata")) : null;
    						aosa = VkmUtil.getJsonInteger(jsonData, "osa");
    						aet = VkmUtil.getJsonInteger(jsonData, "etaisyys");
    						losa = VkmUtil.getJsonInteger(jsonData, "osa_loppu");
    						let = VkmUtil.getJsonInteger(jsonData, "etaisyys_loppu");
    						tilannepvmAsString = VkmUtil.getJsonString(jsonData, "tilannepvm");
    						kohdepvmAsString = VkmUtil.getJsonString(jsonData, "kohdepvm");
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
    						
    						//Aliasten käsittely
    						if (ajr == null) ajr =  VkmUtil.getJsonString(jsonData, "ajr") != null ? VkmUtil.toIntegerList(jsonData.getString("ajr")) : null;
    						if (aosa == null) aosa = VkmUtil.getJsonInteger(jsonData, "aosa");
    						if (aet == null) aet = VkmUtil.getJsonInteger(jsonData, "aet");
    						if (losa == null) losa = VkmUtil.getJsonInteger(jsonData, "losa");
    						if (let == null) let = VkmUtil.getJsonInteger(jsonData, "let");
    						
    						kysely = new InParameters(tunniste, 
														x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
														tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
														link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
														kuntakoodi, katunimi, katunumero, katunumero_loppu, 
														vaylan_luonne, tietyyppi, 
														ely, ualue, maakuntakoodi,
														valihaku, palautusarvot, addMetadata
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
    			
    			//Luodaan muuttuja, johon kootaan muunnosten tulokset
                List<geoJsonWrapper> tulos = new ArrayList<geoJsonWrapper>();
                
                //Annetaan muunnokselle sisäinen tunniste
                for (int i = 0; i < kyselyLista.size(); i++) {
                	kyselyLista.get(i).setJsonLabel("MUUNNOS" + (i+1));
                }
                
                //Tässä luupataan muunnokset läpi ja kootaan muunnosten tulokset
                for (InParameters inParametrit : kyselyLista) {
                	List<geoJsonWrapper> tempStore = palveluNG.muunnosController(inParametrit);
                	for (geoJsonWrapper response : tempStore) {
                		tulos.add(response);
                	}
                }
                
                //Poistetaan kaikista featureista id
                for (geoJsonWrapper t : tulos) {
                	t.id = null;
                }
                
                //Poistetaan kaikista featureista featurecollection_metadata, jos mitään metadataa ei ole merkitty featureen
                for (geoJsonWrapper t : tulos) {
                	if (t.properties.featurecollection_metadata != null)
                		if (t.properties.featurecollection_metadata.allNulls())
                			t.properties.featurecollection_metadata = null;
                }
                
                
         return new FeatureCollection(tulos, addMetadata);
    }
   
}