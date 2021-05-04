import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestLinkInterval {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitehaku/";
	String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";

	
	@Test
	public void testRoadAddressIntervalOnLink() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
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
		q.addToQuery("link_id", "442308");
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
		
		q.addToQuery("valihaku", "true");
		q.addToQuery("palautusarvot", "1,2,3,4,5,6");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT
	
		ResultParameters expected = new ResultParameters();
		
		expected.setType("Feature");
		expected.setGeomType("LineString");
		expected.setCoordinates("[[385885.719999971,6671747.8099999,9.66899999999441],[385872.187999971,6671767.2179999,9.13199999999779]]");
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
	
		expected.setViivanPituus(23.659786304893704);//Should be 23.659799999994!
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
		
		expected.setKuntanimi_se("Helsingfors");
		expected.setMaakuntanimi_se("Nyland");
		expected.setKuntanimi_se_loppu("Helsingfors");
		expected.setMaakuntanimi_se_loppu("Nyland");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);	
	}
	
}