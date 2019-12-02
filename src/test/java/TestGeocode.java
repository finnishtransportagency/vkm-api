import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestGeocode {
	
	String BaseUrl = "http://localhost:8889/vkm-api/geocode/";
	
	@Test
	public void testBasicGeocodeQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		//q.addToQuery("tie", "");
		//q.addToQuery("ajr", "");
		//q.addToQuery("aosa", "");
		//q.addToQuery("aet", "");
		//q.addToQuery("tilannepvm", "");
		//q.addToQuery("kohdepvm", "");
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		q.addToQuery("kuntakoodi", "853");
		q.addToQuery("katunimi", "Eerikinkatu");
		q.addToQuery("katunumero", "1");
		//q.addToQuery("tietyyppi", "");
		//q.addToQuery("vaylan_luonne", "");
		//q.addToQuery("ely", "");
		//q.addToQuery("ualue", "");
		//q.addToQuery("maakuntakoodi", "");
		
		//q.addToQuery("x_loppu", "");
		//q.addToQuery("y_loppu", "");
		//q.addToQuery("z_loppu", "");
		//q.addToQuery("losa", "");
		//q.addToQuery("let", "");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		//q.addToQuery("katunumero_loppu", "");
		
		//q.addToQuery("palautusarvot", "");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT

		ResultParameters expected = new ResultParameters();
		
		expected.setTunniste("1");
		expected.setX(240225.935025337);
		expected.setY(6711297.2857944);
		//expected.setZ();
		//expected.setValimatka();
		//expected.setTie();
		//expected.setAjr();
		//expected.setAosa();
		//expected.setAet();
		//expected.setLink_id();
		//expected.setM_arvo();	
		expected.setKuntakoodi(853);
		expected.setKuntanimi("Turku");
		expected.setKatunimi("Eerikinkatu");
		expected.setKatunimi_se("Eriksgatan");
		expected.setKatunumero(1);
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		//expected.setEly();
		//expected.setElynimi();
		//expected.setUalue();
		//expected.setUaluenimi();

		//expected.setGeometria();
		//expected.setPituus();
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
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}

	@Test
	public void testBasicGeocodeIntervalQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		//q.addToQuery("tie", "");
		//q.addToQuery("ajr", "");
		//q.addToQuery("aosa", "");
		//q.addToQuery("aet", "");
		//q.addToQuery("tilannepvm", "");
		//q.addToQuery("kohdepvm", "");
		//q.addToQuery("link_id", "");
		//q.addToQuery("m_arvo", "");
		q.addToQuery("kuntakoodi", "853");
		q.addToQuery("katunimi", "Eerikinkatu");
		q.addToQuery("katunumero", "1");
		//q.addToQuery("tietyyppi", "");
		//q.addToQuery("vaylan_luonne", "");
		//q.addToQuery("ely", "");
		//q.addToQuery("ualue", "");
		//q.addToQuery("maakuntakoodi", "");
		
		//q.addToQuery("x_loppu", "");
		//q.addToQuery("y_loppu", "");
		//q.addToQuery("z_loppu", "");
		//q.addToQuery("losa", "");
		//q.addToQuery("let", "");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		q.addToQuery("katunumero_loppu", "11");
		
		//q.addToQuery("palautusarvot", "");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT

		ResultParameters expected = new ResultParameters();
		
		expected.setTunniste("1");
		expected.setX(240225.935025337);
		expected.setY(6711297.2857944);
		//expected.setZ();
		//expected.setValimatka();
		//expected.setTie();
		//expected.setAjr();
		//expected.setAosa();
		//expected.setAet();
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(853);
		expected.setKuntanimi("Turku");
		expected.setKatunimi("Eerikinkatu");
		expected.setKatunimi_se("Eriksgatan");
		expected.setKatunumero(1);
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		//expected.setEly();
		//expected.setElynimi();
		//expected.setUalue();
		//expected.setUaluenimi();
		
		//expected.setGeometria();
		//expected.setPituus();
		expected.setX_loppu(239864.321025651);
		expected.setY_loppu(6711039.09479221);
		//expected.setZ_loppu();
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		//expected.setLosa();
		//expected.setLet();
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKatunimi_loppu("Eerikinkatu");
		expected.setKatunimi_se_loppu("Eriksgatan");
		expected.setKatunumero_loppu(11);
		expected.setKuntakoodi_loppu(853);
		expected.setKuntanimi_loppu("Turku");
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		//expected.setEly_loppu();
		//expected.setElynimi_loppu();
		//expected.setUalue_loppu();
		//expected.setUaluenimi_loppu();
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}
	
}