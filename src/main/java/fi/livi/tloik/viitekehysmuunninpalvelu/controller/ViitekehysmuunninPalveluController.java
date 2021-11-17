package fi.livi.tloik.viitekehysmuunninpalvelu.controller;


//import java.io.BufferedReader;
import java.io.IOException;
//import java.lang.annotation.Target;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

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
//import fi.livi.vkm.service.ViitekehysmuunninNGPalvelu;
import fi.livi.vkm.util.VkmUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.Tag;
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
    
    public static final String API_VERSION = "4.1.7";
    
    private static final Integer MAX_PISTEMAISIA = 1000;
    
    private static final Integer MAX_VIIVAMAISIA = 100;
    
    private ZoneId zone = ZoneId.of("Europe/Helsinki");
    private LocalDateTime PVM_SERVER = LocalDateTime.now();
    private LocalDateTime PVM_VKM = LocalDateTime.now(zone);
    
    //private static final Long MAX_VASTAUSKOKO = 10485760L;
    
    // Comment for build
    
    @RequestMapping(value = "aika", method = RequestMethod.GET)
	@ResponseBody
	public String getTime() {
	    return "Päivämäärä serverillä: " + PVM_SERVER + ". VKM-päivämäärä (\"kuluva päivä\" VKM:n mukaan, eli Suomen aika) : " + PVM_VKM;
	}
    
    @RequestMapping(value = "versio", method = RequestMethod.GET)
	@ResponseBody
	public String getVersion() {
	    return "API-versio: " + API_VERSION + ", CORE-versio: " + VkmUtil.CORE_VERSION;
	}
    
    @RequestMapping(value = "tiedote", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getMuutostiedote()
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("vkm-muutostiedote-avoindata.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
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
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
    
    
    @RequestMapping(value = "muutokset-older", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getMuutoksetOlder()
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("VKM-muutokset-touko2021.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
    
    
    @RequestMapping(value = "muutokset-latest", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<InputStreamResource> getMuutoksetLatest()
            throws IOException {

        ClassPathResource pdfFile = new ClassPathResource("VKM-muutokset-loka2021.pdf");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(pdfFile.contentLength())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }

    
    @RequestMapping(method = RequestMethod.GET)
    @ApiIgnore
    public ModelAndView swaggerUi(ModelMap model) {
        return new ModelAndView("redirect:/swagger-ui.html", model);
    }
    
    
//    @RequestMapping(value = "muunna", method = RequestMethod.POST)
//    @ResponseBody
//    public FeatureCollection handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException, IOException {
//    	String json = request.getParameter("json");
//    	String metadata = request.getParameter("metadata");
//    	FeatureCollection fc = yleisRajapinta(null,null,null,null,null,null,null,null,null,null,
//    			null,null,null,null,null,null,null,null,null,null,
//    			null,null,null,null,null,null,null,null,null,null,
//    			null,null,json,metadata,null,null,null,null,null);
//    	return fc;           
//    }
   
    
    @RequestMapping(value = "muunna", method = RequestMethod.POST)
    @ResponseBody
    public FeatureCollection handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException, IOException {
    	
    	String tunniste = request.getParameter("tunniste"); 
		
    	Double x = request.getParameter("x") != null ? Double.parseDouble(request.getParameter("x")) : null;
    	Double y = request.getParameter("y") != null ? Double.parseDouble(request.getParameter("y")) : null;
    	Double z = request.getParameter("z") != null ? Double.parseDouble(request.getParameter("z")) : null;
    	Double x_loppu = request.getParameter("x_loppu") != null ? Double.parseDouble(request.getParameter("x_loppu")) : null;
    	Double y_loppu = request.getParameter("y_loppu") != null ? Double.parseDouble(request.getParameter("y_loppu")) : null;
    	Double z_loppu = request.getParameter("z_loppu") != null ? Double.parseDouble(request.getParameter("z_loppu")) : null;
    	Double z_vaihtelu = request.getParameter("z_vaihtelu") != null ? Double.parseDouble(request.getParameter("z_vaihtelu")) : null;
    	Integer sade = request.getParameter("sade") != null ? Integer.parseInt(request.getParameter("sade")) : null;

    	Integer tie = request.getParameter("tie") != null ? Integer.parseInt(request.getParameter("tie")) : null;
    	List<Integer> ajr = request.getParameter("ajorata") != null ? VkmUtil.toIntegerList(request.getParameter("ajorata")) : null;
    	Integer aosa = request.getParameter("osa") != null ? Integer.parseInt(request.getParameter("osa")) : null;
    	Integer aet = request.getParameter("etaisyys") != null ? Integer.parseInt(request.getParameter("etaisyys")) : null;
    	String lakkautuspvmAsString = request.getParameter("lakkautuspvm");
    	String tilannepvmAsString = request.getParameter("tilannepvm");
    	String kohdepvmAsString = request.getParameter("kohdepvm");

    	Integer losa = request.getParameter("osa_loppu") != null ? Integer.parseInt(request.getParameter("osa_loppu")) : null;
    	Integer let = request.getParameter("etaisyys_loppu") != null ? Integer.parseInt(request.getParameter("etaisyys_loppu")) : null;

    	Integer link_id = request.getParameter("link_id") != null ? Integer.parseInt(request.getParameter("link_id")) : null;
    	Double m_arvo = request.getParameter("m_arvo") != null ? Double.parseDouble(request.getParameter("m_arvo")) : null;
    	Integer link_id_loppu = request.getParameter("link_id_loppu") != null ? Integer.parseInt(request.getParameter("link_id_loppu")) : null;
    	Double m_arvo_loppu = request.getParameter("m_arvo_loppu") != null ? Double.parseDouble(request.getParameter("m_arvo_loppu")) : null;

    	Integer kuntakoodi = request.getParameter("kuntakoodi") != null ? Integer.parseInt(request.getParameter("kuntakoodi")) : null;
    	String kuntanimi = request.getParameter("kuntanimi");
    	String katunimi = request.getParameter("katunimi");
    	Integer katunumero = request.getParameter("katunumero") != null ? Integer.parseInt(request.getParameter("katunumero")) : null;
    	Integer katunumero_loppu = request.getParameter("katunumero_loppu") != null ? Integer.parseInt(request.getParameter("katunumero_loppu")) : null;

    	List<Integer> vaylan_luonne = request.getParameter("vaylan_luonne") != null ? VkmUtil.toIntegerList(request.getParameter("vaylan_luonne")) : null;
    	List<Integer> hallinnollinen_luokka = request.getParameter("hallinnollinen_luokka") != null ? VkmUtil.toIntegerList(request.getParameter("vaylan_luonne")) : null;
    	List<Integer> tietyyppi = request.getParameter("tietyyppi") != null ? VkmUtil.toIntegerList(request.getParameter("tietyyppi")) : null;

    	Integer ely = request.getParameter("ely") != null ? Integer.parseInt(request.getParameter("ely")) : null;
    	Integer ualue = request.getParameter("ualue") != null ? Integer.parseInt(request.getParameter("ualue")) : null;
    	Integer maakuntakoodi = request.getParameter("maakuntakoodi") != null ? Integer.parseInt(request.getParameter("maakuntakoodi")) : null;

    	String valihaku = request.getParameter("valihaku");
    	List<Integer> palautusarvot = request.getParameter("palautusarvot") != null ? VkmUtil.toIntegerList(request.getParameter("palautusarvot")) : null;
    	String json = request.getParameter("json");
    	String metadata = request.getParameter("metadata");
    	   		
    	List<Integer> ajr2 = request.getParameter("ajr") != null ? VkmUtil.toIntegerList(request.getParameter("ajr")) : null;
    	Integer aosa2 = request.getParameter("aosa") != null ? Integer.parseInt(request.getParameter("aosa")) : null;
    	Integer aet2 = request.getParameter("aet") != null ? Integer.parseInt(request.getParameter("aet")) : null;
    	Integer losa2 = request.getParameter("losa") != null ? Integer.parseInt(request.getParameter("losa")) : null;
    	Integer let2 = request.getParameter("let") != null ? Integer.parseInt(request.getParameter("let")) : null;
    	
    	FeatureCollection fc = yleisRajapinta(tunniste, 
				x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
				tie, ajr, aosa, aet, lakkautuspvmAsString, tilannepvmAsString, kohdepvmAsString, losa, let, 
				link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
				kuntakoodi, kuntanimi, katunimi, katunumero, katunumero_loppu, 
				vaylan_luonne, hallinnollinen_luokka, tietyyppi, 
				ely, ualue, maakuntakoodi,
				valihaku, palautusarvot, json, metadata,
				ajr2, aosa2, aet2, losa2, let2
				);
    	
    	return fc;           
    }
    
    @RequestMapping(value = "muunna", method= RequestMethod.GET)
    public FeatureCollection yleisRajapinta(

    		@ApiParam(value = "Tekstimuotoinen, vapaamuotoinen tunniste, joka palautuu jokaisessa kyselyyn liittyvässä featuressa")
    		@RequestParam(name = "tunniste", required = false) String tunniste,

    		@ApiParam(value = "X-koordinaatti eli itäkoordinaatti")
    		@RequestParam(name = "x", required = false) Double x,
    		@ApiParam(value = "Y-koordinaatti eli pohjoiskoordinaatti")
            @RequestParam(name = "y", required = false) Double y,
            @ApiParam(value = "Z-koordinaatti eli korkeusarvo")
            @RequestParam(name = "z", required = false) Double z,
            @ApiParam(value = "Loppupisteen x-koordinaatti")
            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
            @ApiParam(value = "Loppupisteen y-koordinaatti")
            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
            @ApiParam(value = "Loppupisteen z-koordinaatti")
            @RequestParam(name = "z_loppu", required = false) Double z_loppu,
            @ApiParam(value = "Vaihteluväli, jonka sisältä etsitään korkeusarvoja")
            @RequestParam(name = "z_vaihtelu", required = false) Double z_vaihtelu,
            @ApiParam(value = "Säde xy(z)-koordinaateista, jonka sisältä etsitään sijainteja. Oletusarvo 100 (m)")
            @RequestParam(name = "sade", required = false) Integer sade,
            
            @ApiParam(value = "Tienumero")
            @RequestParam(name = "tie", required = false) Integer tie,
            @ApiParam(value = "Ajoratanumero. Oletus 0, 1 ja 2. Anna halutessasi uudet arvot (paina 'Add item' jokaiselle uudelle arvolle)")
            @RequestParam(name = "ajorata", required = false) List<Integer> ajr,
            @ApiParam(value = "Tieosanumero")
            @RequestParam(name = "osa", required = false) Integer aosa,
            @ApiParam(value = "Etäisyysarvo tieosalla")
            @RequestParam(name = "etaisyys", required = false) Integer aet,
            @ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv. Oletuksena kuluva päivä")
            //@ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv")
            @RequestParam(name = "lakkautuspvm", required = false) String lakkautuspvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv. Oletuksena kuluva päivä")
            //@ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv. Oletuksena kuluva päivä.")
            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
            @ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv. Oletuksena kuluva päivä")
            //@ApiParam(value = "Muodossa pp.kk.vvvv tai p.k.vvvv. Oletuksena kuluva päivä.")
            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,

            @ApiParam(value = "Loppupisteen tieosanumero")
            @RequestParam(name = "osa_loppu", required = false) Integer losa,
            @ApiParam(value = "Etäisyysarvo loppupisteen tieosalla")
            @RequestParam(name = "etaisyys_loppu", required = false) Integer let,
            
            @ApiParam(value = "Tielinkin numerotunnus")
            @RequestParam(name = "link_id", required = false) Integer link_id,
            @ApiParam(value = "M-arvo tielinkillä")
            @RequestParam(name = "m_arvo", required = false) Double m_arvo,
            @ApiParam(value = "Loppupisteen tielinkin numerotunnus")
            @RequestParam(name = "link_id_loppu", required = false) Integer link_id_loppu,
            @ApiParam(value = "M-arvo loppupisteen tielinkillä")
            @RequestParam(name = "m_arvo_loppu", required = false) Double m_arvo_loppu,
            
            @ApiParam(value = "Kunnan numerokoodi")
            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
            @ApiParam(value = "Kunnan nimi suomeksi tai ruotsiksi")
            //@ApiParam(value = "Suomeksi tai ruotsiksi")
            @RequestParam(name = "kuntanimi", required = false) String kuntanimi,
            @ApiParam(value = "Kadun nimi suomeksi tai ruotsiksi. Nimen voi katkaista asteriskilla * (esimerkiksi Hämeen*)")
            //@ApiParam(value = "Suomeksi tai ruotsiksi")
            @RequestParam(name = "katunimi", required = false) String katunimi,
            @ApiParam(value = "Katunumero")
            @RequestParam(name = "katunumero", required = false) Integer katunumero,
            @ApiParam(value = "Loppupisteen katunumero")
            @RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
            
            @ApiParam(value = "Väylän luonteen numerokoodi. Anna halutessasi arvot (paina 'Add item' jokaiselle uudelle arvolle)")
            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
            @ApiParam(value = "Hallinnollisen luokan numerokoodi. Anna halutessasi arvot (paina 'Add item' jokaiselle uudelle arvolle)")
            @RequestParam(name = "hallinnollinen_luokka", required = false) List<Integer> hallinnollinen_luokka,
            @ApiParam(value = "Tietyypin numerokoodi. Anna halutessasi arvot (paina 'Add item' jokaiselle uudelle arvolle)")
            @RequestParam(name = "tietyyppi", required = false) List<Integer> tietyyppi,
            
            @ApiParam(value = "Elyn numerokoodi", allowMultiple = true)
            @RequestParam(name = "ely", required = false) Integer ely,
            @ApiParam(value = "Urakka-alueen numerokoodi")
            @RequestParam(name = "ualue", required = false) Integer ualue,
            @ApiParam(value = "Maakunnan numerokoodi")
            @RequestParam(name = "maakuntakoodi", required = false) Integer maakuntakoodi,
            
            @ApiParam(value = "Arvolla 'false' pistemäinen haku, arvolla 'true' viivamainen haku. Oletus 'false'")
            //@ApiParam(value = "Arvolla 'false' pistemäinen haku, arvolla 'true' viivamainen haku. Oletus 'false'.")
            @RequestParam(name = "valihaku", required = false) String valihaku,
            @ApiParam(value = "Palautukseen halutut tiedot: 1=xyz-arvot, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=geometria, 6=lineaarilokaatio. Oletuksena 1, 2, 3 ja 4. Anna halutessasi uudet arvot (paina 'Add item' jokaiselle uudelle arvolle)")
            //@ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=geometria, 6=lineaarilokaatio")
            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
            @ApiParam(value = "Json, joka sisältää yhden tai useamman kyselyn. Json-parametrin lisäksi voi antaa vain metadata-parametrin. Muut parametrit sisältyvät json:iin")
    		@RequestParam(name = "json", required = false) String json,
    		@ApiParam(value = "Arvolla 'true' antaa FeatureCollectionin metatiedot, kuten virhetiedot kootusti. Oletus 'false'")
    		//@ApiParam(value = "Arvolla 'true' antaa FeatureCollectionin metatiedot. Oletus 'false'.")
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
    			boolean pvmNull = (lakkautuspvmAsString == null && tilannepvmAsString == null && kohdepvmAsString == null);
    			boolean linkOsoiteNull = (link_id == null && m_arvo == null && link_id_loppu == null && m_arvo_loppu == null);
    			boolean katuOsoiteNull = (kuntakoodi == null && kuntanimi== null && katunimi == null && katunumero == null && katunumero_loppu == null);
    			boolean vaylaTyypitNull = (vaylan_luonne == null && hallinnollinen_luokka == null && tietyyppi == null);
    			boolean alueetNull = (ely == null && ualue == null && maakuntakoodi == null);
    			boolean metaParametritNull = (valihaku == null && tunniste == null && palautusarvot == null);
    			boolean muutKuinJsonNull = (koordinaatitNull && tieOsoiteNull && pvmNull && linkOsoiteNull && katuOsoiteNull && vaylaTyypitNull && alueetNull && metaParametritNull);
    			
    			//Tarkistetaan halutaanko FeatureCollectionin metadata
    			Boolean addMetadata = (metadata != null && metadata.trim().toUpperCase().equals("TRUE"));
    			
    			//Kyseessä yksittäinen muunnos, ei json-parametrin kautta
    			if (json == null && !muutKuinJsonNull) {
    				kysely = new InParameters(tunniste, 
    											x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
    											tie, ajr, aosa, aet, lakkautuspvmAsString, tilannepvmAsString, kohdepvmAsString, losa, let, 
    											link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
    											kuntakoodi, kuntanimi, katunimi, katunumero, katunumero_loppu, 
    											vaylan_luonne, hallinnollinen_luokka, tietyyppi, 
    											ely, ualue, maakuntakoodi,
    											valihaku, palautusarvot, addMetadata
    											);
    				kyselyLista.add(kysely);
    			}
    			//Kyseessä muunnos json-parametrin kautta
    			else if (json != null && muutKuinJsonNull){
    				 //For testing, normally commented // json = replaceJsonWithTestJson(json, 100, true);
    				JSONArray jsonKysely;
    				JSONObject jsonData;
    				int pistemaisia = 0;
    				int viivamaisia = 0;
    				try {
    					jsonKysely = new JSONArray(json);
    				}
    				catch(Exception e) {
    					throw new JSONException("Virhe json-parametrissa: "+ e.getMessage());
    				}
    				if (jsonKysely != null ) {
    					
    					if (jsonKysely.length() > MAX_PISTEMAISIA + MAX_VIIVAMAISIA)
    						throw new JSONException("Kyselyssä liikaa muunnoksia. Kyselyssä voi olla korkeintaan 1000 pistemäistä ja 100 viivamaista muunnosta.");
    					
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
    						z_vaihtelu = VkmUtil.getJsonDouble(jsonData, "z_vaihtelu");
    						sade = VkmUtil.getJsonInteger(jsonData, "sade");
    						tie = VkmUtil.getJsonInteger(jsonData, "tie");
    						ajr =  VkmUtil.getJsonString(jsonData, "ajorata") != null ? VkmUtil.toIntegerList(jsonData.getString("ajorata")) : null;
    						aosa = VkmUtil.getJsonInteger(jsonData, "osa");
    						aet = VkmUtil.getJsonInteger(jsonData, "etaisyys");
    						losa = VkmUtil.getJsonInteger(jsonData, "osa_loppu");
    						let = VkmUtil.getJsonInteger(jsonData, "etaisyys_loppu");
    						lakkautuspvmAsString = VkmUtil.getJsonString(jsonData, "lakkautuspvm");
    						tilannepvmAsString = VkmUtil.getJsonString(jsonData, "tilannepvm");
    						kohdepvmAsString = VkmUtil.getJsonString(jsonData, "kohdepvm");
    						link_id = VkmUtil.getJsonInteger(jsonData, "link_id");
    						m_arvo = VkmUtil.getJsonDouble(jsonData, "m_arvo");
    						link_id_loppu = VkmUtil.getJsonInteger(jsonData, "link_id_loppu");
    						m_arvo_loppu = VkmUtil.getJsonDouble(jsonData, "m_arvo_loppu");
    						kuntakoodi = VkmUtil.getJsonInteger(jsonData, "kuntakoodi");
    						kuntanimi = VkmUtil.getJsonString(jsonData, "kuntanimi");
    						katunimi = VkmUtil.getJsonString(jsonData, "katunimi");
    						katunumero = VkmUtil.getJsonInteger(jsonData, "katunumero");
    						katunumero_loppu = VkmUtil.getJsonInteger(jsonData, "katunumero_loppu");
    						vaylan_luonne = VkmUtil.getJsonString(jsonData, "vaylan_luonne") != null ? VkmUtil.toIntegerList(jsonData.getString("vaylan_luonne")) : null;
    						hallinnollinen_luokka = VkmUtil.getJsonString(jsonData, "hallinnollinen_luokka") != null ? VkmUtil.toIntegerList(jsonData.getString("hallinnollinen_luokka")) : null;
    						tietyyppi =  VkmUtil.getJsonString(jsonData, "tietyyppi") != null ? VkmUtil.toIntegerList(jsonData.getString("tietyyppi")) : null;
    						ely = VkmUtil.getJsonInteger(jsonData, "ely");
    						ualue = VkmUtil.getJsonInteger(jsonData, "ualue");
    						maakuntakoodi = VkmUtil.getJsonInteger(jsonData, "maakuntakoodi");
    						valihaku = VkmUtil.getJsonString(jsonData, "valihaku");
    							if (valihaku != null && valihaku.trim().toUpperCase().equals("TRUE"))
    								viivamaisia += 1;
    							else
    								pistemaisia += 1;
    							if (pistemaisia > MAX_PISTEMAISIA || viivamaisia > MAX_VIIVAMAISIA)
    								throw new JSONException("Kyselyssä liikaa muunnoksia. Kyselyssä voi olla korkeintaan 1000 pistemäistä ja 100 viivamaista muunnosta.");
    						palautusarvot =  VkmUtil.getJsonString(jsonData, "palautusarvot") != null ? VkmUtil.toIntegerList(jsonData.getString("palautusarvot")) : null;
    						
    						//Aliasten käsittely
    						if (ajr == null) ajr =  VkmUtil.getJsonString(jsonData, "ajr") != null ? VkmUtil.toIntegerList(jsonData.getString("ajr")) : null;
    						if (aosa == null) aosa = VkmUtil.getJsonInteger(jsonData, "aosa");
    						if (aet == null) aet = VkmUtil.getJsonInteger(jsonData, "aet");
    						if (losa == null) losa = VkmUtil.getJsonInteger(jsonData, "losa");
    						if (let == null) let = VkmUtil.getJsonInteger(jsonData, "let");
    						
    						kysely = new InParameters(tunniste, 
														x, y, z, x_loppu, y_loppu, z_loppu, z_vaihtelu, sade, 
														tie, ajr, aosa, aet, lakkautuspvmAsString, tilannepvmAsString, kohdepvmAsString, losa, let, 
														link_id, m_arvo, link_id_loppu, m_arvo_loppu, 
														kuntakoodi, kuntanimi, katunimi, katunumero, katunumero_loppu, 
														vaylan_luonne, hallinnollinen_luokka, tietyyppi, 
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
                //Long koko = 0L;
                for (InParameters inParametrit : kyselyLista) {
                	List<geoJsonWrapper> tempStore = palveluNG.muunnosController(inParametrit);
                	for (geoJsonWrapper response : tempStore) {
                		tulos.add(response);
//                		if (koko > MAX_VASTAUSKOKO)
//                			throw new JSONException("Vastauksen koko liian suuri. Maksimikoko on " + MAX_VASTAUSKOKO + " tavua.");
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
    
    
    private String replaceJsonWithTestJson(String original, int count, boolean valihaku) {
    	original = null;
    	StringBuilder testjsonquery = new StringBuilder(0);
		int maxCount = count;
		testjsonquery.append("[");
		for (int i=0; i <= maxCount; i++) {
			if (!valihaku)
				testjsonquery.append("{\"tie\":3,\"osa\":101,\"ajorata\":\"1\",\"etaisyys\":" + i + "}");
			else {
				int loppu = i + 10;
				testjsonquery.append("{\"tie\":3,\"osa\":101,\"ajorata\":\"1\",\"etaisyys\":" + i + ",\"osa_loppu\":101,\"etaisyys_loppu\":" + loppu + ",\"valihaku\":\"true\"}");
			}
			if (i < maxCount)
				testjsonquery.append(",");
		}
		testjsonquery.append("]");
		original = testjsonquery.toString();
		return original;
    }
   
}