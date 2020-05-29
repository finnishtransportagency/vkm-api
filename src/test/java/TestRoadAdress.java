import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRoadAdress {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitehaku/";
	String BaseUrl = "http://localhost:8889/vkm-api/muunna/";
	
	@Test
	public void testBasicRoadAdressQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "1");
		q.addToQuery("ajr", "1");
		q.addToQuery("aosa", "1");
		q.addToQuery("aet", "0");
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
		expected.setX(385885.719999971);
		expected.setY(6671747.8099999);
		expected.setZ(9.66899999999441);
		//expected.setValimatka();
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(1);
		expected.setAet(0);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setEly(1);
		//expected.setElynimi();
		expected.setUalue(400);
		expected.setUaluenimi("Kunta hoitaa");

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

}