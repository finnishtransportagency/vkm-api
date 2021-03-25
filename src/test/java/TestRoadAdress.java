import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRoadAdress {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitehaku/";
	String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";
	
	
	@Test
	public void testBasicRoadAddress() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "1");
		q.addToQuery("ajorata", "1");
		q.addToQuery("osa", "1");
		q.addToQuery("etaisyys", "0");
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
		q.addToQuery("palautusarvot", "1,2,3,4,5,6");
		//q.addToQuery("json", "");
		
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
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);	
	}
	
	
	@Test
	public void testBasicRoadAddressWLakkautuspvm() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "4");
		q.addToQuery("ajorata", "0");
		q.addToQuery("osa", "302");
		q.addToQuery("etaisyys", "4465");
		q.addToQuery("lakkautuspvm", "29.10.2020");
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
		q.addToQuery("palautusarvot", "1,2,3,4,5,6");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT
	
		ResultParameters expected = new ResultParameters();
		
		expected.setType("Feature");
		expected.setGeomType("Point");
		expected.setCoordinates("[432912.307999997,6912721.83700024]");
		expected.setTunniste("1");
		expected.setX(432912.307999997);
		expected.setY(6912721.83700024);
		expected.setZ(98.4879999999976);
		//expected.setValimatka();
		expected.setTie(4);
		expected.setAjr(0);
		expected.setAosa(302);
		expected.setAet(4465);
		expected.setLakkPvm("29.10.2020");
		expected.setLink_id(11885177);
		expected.setM_arvo(0.0);
		expected.setKuntakoodi(179);
		expected.setKuntanimi("Jyväskylä");
		expected.setKatunimi("Nelostie");
		//expected.setKatunimi_se("");
		expected.setKatunumero(426);
		expected.setTietyyppi(1);
		expected.setVaylan_luonne(11);
		expected.setMaakuntakoodi(13);
		expected.setMaakuntanimi("Keski-Suomi");
		expected.setEly(9);
		expected.setElynimi("Keski-Suomi");
		expected.setUalue(930);
		expected.setUaluenimi("Jyväskylä 17-22");
	
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
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);	
	}
	
	
	@Test
	public void testBasicRoadAddressWEndPoint() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "1");
		q.addToQuery("ajorata", "1");
		q.addToQuery("osa", "1");
		q.addToQuery("etaisyys", "0");
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
		q.addToQuery("osa_loppu", "1");
		q.addToQuery("etaisyys_loppu", "24");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		//q.addToQuery("katunumero_loppu", "");
		
		//q.addToQuery("valihaku", "");
		q.addToQuery("palautusarvot", "1,2,3,4,5,6");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT
	
		ResultParameters expected = new ResultParameters();
		
		expected.setType("Feature");
		expected.setGeomType("MultiPoint");
		expected.setCoordinates("[[385885.719999971,6671747.8099999],[385872.187999971,6671767.2179999]]");
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
		expected.setX_loppu(385872.187999971);
		expected.setY_loppu(6671767.2179999);
		expected.setZ_loppu(9.13199999999779);
		//expected.setValimatka_loppu();
		expected.setL_tie(1);
		expected.setL_ajr(1);
		expected.setLosa(1);
		expected.setLet(24);
		expected.setLink_id_loppu(442308);
		expected.setM_arvo_loppu(23.659799999994);
		expected.setKuntakoodi_loppu(91);
		expected.setKuntanimi_loppu("Helsinki");
		expected.setKatunimi_loppu("Mannerheimintie");
		expected.setKatunimi_se_loppu("Mannerheimvägen");
		//expected.setKatunumero_loppu();
		expected.setTietyyppi_loppu(3);
		expected.setVaylan_luonne_loppu(11);
		expected.setMaakuntakoodi_loppu(1);
		expected.setMaakuntanimi_loppu("Uusimaa");
		expected.setEly_loppu(1);
		expected.setElynimi_loppu("Uusimaa");
		expected.setUalue_loppu(400);
		expected.setUaluenimi_loppu("Kunta hoitaa");
		//expected.setVirheet();
		//expected.setFeatureCollectionMetadata();
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);	
	}

}