import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TestJson {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitevali/";
	String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";
	
	@Test
	public void testBasicJson() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		//q.addToQuery("tunniste", "");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		//q.addToQuery("tie", "");
		//q.addToQuery("ajorata", "");
		//q.addToQuery("osa", "");
		//q.addToQuery("etaisyys", "");
		//q.addToQuery("tilannepvm", "");
		//q.addToQuery("kohdepvm", "");
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		//q.addToQuery("kuntakoodi", "");
		//q.addToQuery("katunimi", "");
		//q.addToQuery("katunumero", "");
		//q.addToQuery("tietyyppi", "");
		//q.addToQuery("vaylan_luonne", "");
		//q.addToQuery("ely", "");
		//q.addToQuery("ualue", "");
		//q.addToQuery("maakuntakoodi", "");
		
		//q.addToQuery("x_loppu", "");
		//q.addToQuery("y_loppu", "");
		//q.addToQuery("z_loppu", "");
		//q.addToQuery("osa_loppu", "");
		//q.addToQuery("etaisyys_loppu", "");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		//q.addToQuery("katunumero_loppu", "");
		
		//q.addToQuery("valihaku", "");
		//q.addToQuery("palautusarvot", "");
		q.addToQuery("json", "[{'tunniste':'1','tie':1,'ajorata':'1','osa':1,'etaisyys':0,'palautusarvot':'1,2,3,4,5,6'}]");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT
	
		ResultParameters expected = new ResultParameters();
		
		expected.setType("Feature");
		expected.setGeomType("Point");
		expected.setCoordinates("[385885.719999971,6671747.8099999]");
		expected.setTunniste("1");
		expected.setX(385885.719999971);
		expected.setY(6671747.8099999);
		expected.setZ(9.66899999999441);
		//expected.setValimatka();
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(1);
		expected.setAet(0);
		expected.setLink_id(442308);
		expected.setM_arvo(0.0);
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		expected.setKatunimi("Mannerheimintie");
		expected.setKatunimi_se("Mannerheimvägen");
		//expected.setKatunumero();
		expected.setTietyyppi(3);
		expected.setVaylan_luonne(11);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setEly(1);
		expected.setElynimi("Uusimaa");
		expected.setUalue(400);
		expected.setUaluenimi("Kunta hoitaa");
	
		//expected.setViivanPituus();
		//expected.setMitattuPituus();
		//expected.setX_loppu();
		//expected.setY_loppu();
		//expected.setZ_loppu();
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		//expected.setLosa();
		//expected.setLet();
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		//expected.setKuntakoodi_loppu();
		//expected.setKuntanimi_loppu();
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		//expected.setMaakuntakoodi_loppu();
		//expected.setMaakuntanimi_loppu();
		//expected.setEly_loppu();
		//expected.setElynimi_loppu();
		//expected.setUalue_loppu();
		//expected.setUaluenimi_loppu();
		//expected.setVirheet();
		//expected.setFeatureCollectionMetadata();
		
		expected.setKuntanimi_se("Helsingfors");
		expected.setMaakuntanimi_se("Nyland");
		//expected.setKuntanimi_se_loppu("");
		//expected.setMaakuntanimi_se_loppu("");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}

}
