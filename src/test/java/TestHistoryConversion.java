import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHistoryConversion {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitehaku/";
	String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";

	@Test
	public void testBasicHistoryConversion() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "78131");
		q.addToQuery("ajorata", "0");
		q.addToQuery("osa", "850");
		q.addToQuery("etaisyys", "118");
		q.addToQuery("tilannepvm", "01.10.2010");
		q.addToQuery("kohdepvm", "01.01.2011");
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
		expected.setCoordinates("[424452.738999997,7187970.89700028]");
		expected.setTunniste("1");
		expected.setX(424452.738999997);
		expected.setY(7187970.89700028);
		expected.setZ(5.00400000000081);
		//expected.setValimatka();
		expected.setTie(88666);
		expected.setAjr(0);
		expected.setAosa(850);
		expected.setAet(118);
		expected.setLink_id(10768168);
		expected.setM_arvo(0.0);
		expected.setKuntakoodi(425);
		expected.setKuntanimi("Liminka");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		expected.setTietyyppi(1);
		expected.setVaylan_luonne(7);
		expected.setMaakuntakoodi(17);
		expected.setMaakuntanimi("Pohjois-Pohjanmaa");
		expected.setEly(12);
		expected.setElynimi("Pohjois-Pohjanmaa ja Kainuu");
		expected.setUalue(1250);
		expected.setUaluenimi("Oulu 17-22");
	
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
		
		expected.setKuntanimi_se("Limingo");
		expected.setMaakuntanimi_se("Norra Österbotten");
		//expected.setKuntanimi_se_loppu("");
		//expected.setMaakuntanimi_se_loppu("");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}
	
	
	@Test
	public void testBasicHistoryConversionWEndPoint() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("z_vaihtelu", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "78131");
		q.addToQuery("ajorata", "0");
		q.addToQuery("osa", "850");
		q.addToQuery("etaisyys", "118");
		q.addToQuery("tilannepvm", "01.10.2010");
		q.addToQuery("kohdepvm", "01.01.2011");
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
		q.addToQuery("osa_loppu", "850");
		q.addToQuery("etaisyys_loppu", "230");
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
		expected.setCoordinates("[[424452.738999997,7187970.89700028],[424511.887999997,7188068.81700028]]");
		expected.setTunniste("1");
		expected.setX(424452.738999997);
		expected.setY(7187970.89700028);
		expected.setZ(5.00400000000081);
		//expected.setValimatka();
		expected.setTie(88666);
		expected.setAjr(0);
		expected.setAosa(850);
		expected.setAet(118);
		expected.setLink_id(10768168);
		expected.setM_arvo(0.0);
		expected.setKuntakoodi(425);
		expected.setKuntanimi("Liminka");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		expected.setTietyyppi(1);
		expected.setVaylan_luonne(7);
		expected.setMaakuntakoodi(17);
		expected.setMaakuntanimi("Pohjois-Pohjanmaa");
		expected.setEly(12);
		expected.setElynimi("Pohjois-Pohjanmaa ja Kainuu");
		expected.setUalue(1250);
		expected.setUaluenimi("Oulu 17-22");
	
		//expected.setViivanPituus();
		//expected.setMitattuPituus();
		expected.setX_loppu(424511.887999997);
		expected.setY_loppu(7188068.81700028);
		expected.setZ_loppu(5.17399999999907);
		//expected.setValimatka_loppu();
		expected.setL_tie(88666);
		expected.setL_ajr(0);
		expected.setLosa(850);
		expected.setLet(230);
		expected.setLink_id_loppu(10768150);
		expected.setM_arvo_loppu(65.3007999999973);
		expected.setKuntakoodi_loppu(425);
		expected.setKuntanimi_loppu("Liminka");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		expected.setTietyyppi_loppu(1);
		expected.setVaylan_luonne_loppu(7);
		expected.setMaakuntakoodi_loppu(17);
		expected.setMaakuntanimi_loppu("Pohjois-Pohjanmaa");
		expected.setEly_loppu(12);
		expected.setElynimi_loppu("Pohjois-Pohjanmaa ja Kainuu");
		expected.setUalue_loppu(1250);
		expected.setUaluenimi_loppu("Oulu 17-22");
		//expected.setVirheet();
		//expected.setFeatureCollectionMetadata();
		
		expected.setKuntanimi_se("Limingo");
		expected.setMaakuntanimi_se("Norra Österbotten");
		expected.setKuntanimi_se_loppu("Limingo");
		expected.setMaakuntanimi_se_loppu("Norra Österbotten");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}
}
