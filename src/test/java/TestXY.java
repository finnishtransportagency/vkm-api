import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestXY {
	
	String BaseUrl = "http://localhost:8889/vkm-api/xyhaku/";
	
	@Test
	public void testBasicXYQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY

		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		q.addToQuery("x", "384281");
		q.addToQuery("y", "6674531");
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
		expected.setX(384281.0511921047);
		expected.setY(6674531.617088948);
		expected.setZ(15.3264532305459);
		expected.setValimatka(0.619208690016535);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(11);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		expected.setVaylan_luonne(11);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setEly(1);
		//expected.setElynimi();
		expected.setUalue(131);
		expected.setUaluenimi("Vantaa 14-19");

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
	public void testXYWithRadiusQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY

		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		q.addToQuery("x", "384181");
		q.addToQuery("y", "6674431");
		//q.addToQuery("z", "");
		q.addToQuery("sade", "200");
		//q.addToQuery("tie", "");
		//q.addToQuery("ajr", "");
		//q.addToQuery("aosa", "");
		//q.addToQuery("aet", "");
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
		expected.setX(384189.9013300307);
		expected.setY(6674539.247154606);
		expected.setZ(16.811720334449777);
		expected.setValimatka(108.612523018843);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(101);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		expected.setVaylan_luonne(11);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setEly(1);
		//expected.setElynimi();
		expected.setUalue(131);
		expected.setUaluenimi("Vantaa 14-19");

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
	public void testXYWithLeadtypeQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		q.addToQuery("x", "385482");
		q.addToQuery("y", "6672270");
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
		//q.addToQuery("kuntakoodi", "");
		//q.addToQuery("katunimi", "");
		//q.addToQuery("katunumero", "");
		//q.addToQuery("tietyyppi", "");
		q.addToQuery("vaylan_luonne", "11");
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
		expected.setX(385495.6375900992);
		expected.setY(6672285.258098392);
		expected.setZ(7.185874670170957);
		expected.setValimatka(20.4644430719753);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(1);
		expected.setAet(668);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		expected.setVaylan_luonne(11);
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
	
	@Test
	public void testXYWithRoadLimiterQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		q.addToQuery("x", "384170");
		q.addToQuery("y", "6671559");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "40922");
		//q.addToQuery("ajr", "");
		//q.addToQuery("aosa", "");
		//q.addToQuery("aet", "");
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
		//RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/xyhaku?tunniste=1&x=384170&y=6671559&tie=40922");
		
		expected.setTunniste("1");
		expected.setX(384166.32499997);
		expected.setY(6671543.95499987);
		expected.setZ(3.65099999999802);
		expected.setValimatka(15.4873385105938);
		expected.setTie(40922);
		expected.setAjr(1);
		expected.setAosa(1);
		expected.setAet(3095);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		expected.setVaylan_luonne(4);
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
	
	@Test
	public void testBasicXYIntervalQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		q.addToQuery("x", "384281");
		q.addToQuery("y", "6674531");
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
		//q.addToQuery("kuntakoodi", "");
		//q.addToQuery("katunimi", "");
		//q.addToQuery("katunumero", "");
		//q.addToQuery("tietyyppi", "");
		//q.addToQuery("vaylan_luonne", "");
		//q.addToQuery("ely", "");
		//q.addToQuery("ualue", "");
		//q.addToQuery("maakuntakoodi", "");
		
		q.addToQuery("x_loppu", "384088.52995879017");
		q.addToQuery("y_loppu", "6674543.712320467");
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
		expected.setX(384281.0511921047);
		expected.setY(6674531.617088948);
		expected.setZ(15.3264532305459);
		expected.setValimatka(0.619208690016535);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(11);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		expected.setVaylan_luonne(11);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setEly(1);
		//expected.setElynimi();
		expected.setUalue(131);
		expected.setUaluenimi("Vantaa 14-19");

		//expected.setGeometria();
		//expected.setPituus();
		expected.setX_loppu(384088.52995879017);
		expected.setY_loppu(6674543.712320467);
		expected.setZ_loppu(18.906417838277722);
		expected.setValimatka_loppu(0.0);
		//expected.setL_tie();
		//expected.setL_ajr();
		expected.setLosa(2);
		expected.setLet(200);
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKuntakoodi_loppu(91);
		expected.setKuntanimi_loppu("Helsinki");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		expected.setVaylan_luonne_loppu(11);
		expected.setMaakuntakoodi_loppu(1);
		expected.setMaakuntanimi_loppu("Uusimaa");
		expected.setEly_loppu(1);
		//expected.setElynimi_loppu();
		expected.setUalue_loppu(131);
		expected.setUaluenimi_loppu("Vantaa 14-19");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}

}