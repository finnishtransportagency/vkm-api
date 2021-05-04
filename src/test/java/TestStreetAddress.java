import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestStreetAddress {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitehaku/";
	String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";
	
	@Test
	public void testBasicStreetAddress() throws IOException {
		
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
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		q.addToQuery("kuntakoodi", "837");
		q.addToQuery("katunimi", "Näsijärvenkatu");
		q.addToQuery("katunumero", "7");
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
		expected.setCoordinates("[327051.073000659,6823095.15199016]");
		expected.setTunniste("1");
		expected.setX(327051.073000659);
		expected.setY(6823095.15199016);
		expected.setZ(99.7130000000034);
		//expected.setValimatka();
		//expected.setTie();
		//expected.setAjr();
		//expected.setAosa();
		//expected.setAet();
		expected.setLink_id(5648901);
		expected.setM_arvo(94.4713999999949);
		expected.setKuntakoodi(837);
		expected.setKuntanimi("Tampere");
		expected.setKatunimi("Näsijärvenkatu");
		//expected.setKatunimi_se();
		expected.setKatunumero(7);
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(6);
		expected.setMaakuntanimi("Pirkanmaa");
		expected.setEly(4);
		expected.setElynimi("Pirkanmaa");
		//expected.setUalue();
		//expected.setUaluenimi();
	
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
		
		expected.setKuntanimi_se("Tammerfors");
		expected.setMaakuntanimi_se("Birkaland");
		//expected.setKuntanimi_se_loppu("");
		//expected.setMaakuntanimi_se_loppu("");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}

	
	@Test
	public void testBasicStreetAddressWStrName() throws IOException {
		
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
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		//q.addToQuery("kuntakoodi", "");
		q.addToQuery("kuntanimi", "Tampere");
		q.addToQuery("katunimi", "Näsijärvenkatu");
		q.addToQuery("katunumero", "7");
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
		expected.setCoordinates("[327051.073000659,6823095.15199016]");
		expected.setTunniste("1");
		expected.setX(327051.073000659);
		expected.setY(6823095.15199016);
		expected.setZ(99.7130000000034);
		//expected.setValimatka();
		//expected.setTie();
		//expected.setAjr();
		//expected.setAosa();
		//expected.setAet();
		expected.setLink_id(5648901);
		expected.setM_arvo(94.4713999999949);
		expected.setKuntakoodi(837);
		expected.setKuntanimi("Tampere");
		expected.setKatunimi("Näsijärvenkatu");
		//expected.setKatunimi_se();
		expected.setKatunumero(7);
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(6);
		expected.setMaakuntanimi("Pirkanmaa");
		expected.setEly(4);
		expected.setElynimi("Pirkanmaa");
		//expected.setUalue();
		//expected.setUaluenimi();
	
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
		
		expected.setKuntanimi_se("Tammerfors");
		expected.setMaakuntanimi_se("Birkaland");
		//expected.setKuntanimi_se_loppu("");
		//expected.setMaakuntanimi_se_loppu("");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}
	
	
	@Test
	public void testBasicStreetAddressWendPoint() throws IOException {
		
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
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		q.addToQuery("kuntakoodi", "837");
		q.addToQuery("katunimi", "Näsijärvenkatu");
		q.addToQuery("katunumero", "7");
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
		q.addToQuery("katunumero_loppu", "9");
		
		//q.addToQuery("valihaku", "");
		q.addToQuery("palautusarvot", "1,2,3,4,5,6");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT
	
		ResultParameters expected = new ResultParameters();
		
		expected.setType("Feature");
		expected.setGeomType("MultiPoint");
		expected.setCoordinates("[[327051.073000659,6823095.15199016],[326956.846000663,6823088.36599012]]");
		expected.setTunniste("1");
		expected.setX(327051.073000659);
		expected.setY(6823095.15199016);
		expected.setZ(99.7130000000034);
		//expected.setValimatka();
		//expected.setTie();
		//expected.setAjr();
		//expected.setAosa();
		//expected.setAet();
		expected.setLink_id(5648901);
		expected.setM_arvo(94.4713999999949);
		expected.setKuntakoodi(837);
		expected.setKuntanimi("Tampere");
		expected.setKatunimi("Näsijärvenkatu");
		//expected.setKatunimi_se();
		expected.setKatunumero(7);
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(6);
		expected.setMaakuntanimi("Pirkanmaa");
		expected.setEly(4);
		expected.setElynimi("Pirkanmaa");
		//expected.setUalue();
		//expected.setUaluenimi();
	
		//expected.setViivanPituus();
		//expected.setMitattuPituus();
		expected.setX_loppu(326956.846000663);
		expected.setY_loppu(6823088.36599012);
		expected.setZ_loppu(100.047000000006);
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		//expected.setLosa();
		//expected.setLet();
		expected.setLink_id_loppu(5648901);
		expected.setM_arvo_loppu(0.0);
		expected.setKuntakoodi_loppu(837);
		expected.setKuntanimi_loppu("Tampere");
		expected.setKatunimi_loppu("Näsijärvenkatu");
		//expected.setKatunimi_se_loppu();
		expected.setKatunumero_loppu(9);
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(6);
		expected.setMaakuntanimi_loppu("Pirkanmaa");
		expected.setEly_loppu(4);
		expected.setElynimi_loppu("Pirkanmaa");
		//expected.setUalue_loppu();
		//expected.setUaluenimi_loppu();
		//expected.setVirheet();
		//expected.setFeatureCollectionMetadata();
		
		expected.setKuntanimi_se("Tammerfors");
		expected.setMaakuntanimi_se("Birkaland");
		expected.setKuntanimi_se_loppu("Tammerfors");
		expected.setMaakuntanimi_se_loppu("Birkaland");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
	
		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	}
	
}