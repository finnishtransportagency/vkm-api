import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRoadAdressInterval {
	
	String BaseUrl = "http://localhost:8889/vkm-api/tieosoitevali/";
	
	@Test
	public void testBasicRoadAdressIntervalQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "10");
		q.addToQuery("ajr", "1");
		q.addToQuery("aosa", "2");
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
		q.addToQuery("losa", "2");
		q.addToQuery("let", "400");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		//q.addToQuery("katunumero_loppu", "");
		
		//q.addToQuery("palautusarvot", "");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT

		ResultParameters expected = new ResultParameters();
		
		expected.setTunniste("1");
		expected.setX(245085.386021327);
		expected.setY(6712924.80482271);
		expected.setZ(11.5439999999944);
		//expected.setValimatka();
		expected.setTie(10);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(0);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(202);
		expected.setKuntanimi("Kaarina");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		expected.setEly(2);
		//expected.setElynimi();
		expected.setUalue(233);
		expected.setUaluenimi("Lieto 15 -20");

		//expected.setGeometria();
		//expected.setPituus();
		expected.setX_loppu(245369.33164209858);
		expected.setY_loppu(6713263.082851381);
		expected.setZ_loppu(19.796457796392367);
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		expected.setLosa(2);
		expected.setLet(400);
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKuntakoodi_loppu(202);
		expected.setKuntanimi_loppu("Kaarina");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		expected.setEly_loppu(2);
		//expected.setElynimi_loppu();
		expected.setUalue_loppu(233);
		expected.setUaluenimi_loppu("Lieto 15 -20");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}
	
	@Test
	public void testBasicRoadAdressIntervalWithGeometryQuery() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "10");
		q.addToQuery("ajr", "1");
		q.addToQuery("aosa", "2");
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
		q.addToQuery("losa", "2");
		q.addToQuery("let", "400");
		//q.addToQuery("link_id_loppu", "");
		//q.addToQuery("m_arvo_loppu", "");
		//q.addToQuery("katunumero_loppu", "");
		
		q.addToQuery("palautusarvot", "1,2,3,4,5");
		//q.addToQuery("json", "");
		
		RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
		
		// ANNETAAN ODOTETUT OUT-PARAMETRIT

		ResultParameters expected = new ResultParameters();
		
		expected.setTunniste("1");
		expected.setX(245085.386021327);
		expected.setY(6712924.80482271);
		expected.setZ(11.5439999999944);
		//expected.setValimatka();
		expected.setTie(10);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(0);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(202);
		expected.setKuntanimi("Kaarina");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		expected.setEly(2);
		//expected.setElynimi();
		expected.setUalue(233);
		expected.setUaluenimi("Lieto 15 -20");

		expected.setGeometria("{\"type\":\"MultiLineString\",\"coordinates\":[[[245085.386021327,6712924.80482271],[245093.414021321,6712931.03582276],[245108.757987415,6712943.05279629]],[[245108.75802131,6712943.05282284],[245128.118021295,6712958.20082294],[245145.520021283,6712973.63882303],[245160.846021271,6712988.61282311],[245161.707021271,6712989.52182312]],[[245161.707021271,6712989.52182312],[245177.362021259,6713006.02582319],[245192.883021249,6713023.05582328],[245208.830021237,6713040.66882336],[245217.821016825,6713051.4858181]],[[245217.821021231,6713051.4858234],[245226.152929559,6713061.93070853]],[[245226.153021225,6713061.93082345],[245234.040021219,6713071.75882349],[245250.744021207,6713093.20782357],[245264.910021198,6713113.38882364],[245271.170021193,6713122.33182367],[245285.598021183,6713142.35182375],[245299.857021173,6713163.90782382],[245318.60402116,6713191.31982391],[245333.58302115,6713212.70782399],[245368.464021126,6713261.17982416],[245369.33164209858,6713263.082851381]]]}");
		expected.setPituus(444.16491966665023);
		expected.setX_loppu(245369.33164209858);
		expected.setY_loppu(6713263.082851381);
		expected.setZ_loppu(19.796457796392367);
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		expected.setLosa(2);
		expected.setLet(400);
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKuntakoodi_loppu(202);
		expected.setKuntanimi_loppu("Kaarina");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		expected.setEly_loppu(2);
		//expected.setElynimi_loppu();
		expected.setUalue_loppu(233);
		expected.setUaluenimi_loppu("Lieto 15 -20");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}
	
	@Test
	public void testRoadAdressIntervalWithRoadOnly() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "1");
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
		expected.setX_loppu(241080.825024594);
		expected.setY_loppu(6711666.30579962);
		expected.setZ_loppu(14.502999999997);
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		expected.setLosa(36);
		expected.setLet(2785);
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKuntakoodi_loppu(853);
		expected.setKuntanimi_loppu("Turku");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		expected.setEly_loppu(2);
		//expected.setElynimi_loppu();
		expected.setUalue_loppu(400);
		expected.setUaluenimi_loppu("Kunta hoitaa");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}
	
	@Test
	public void testRoadAdressIntervalWithRoadAndPart() throws IOException {
		
		// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
		
		Query q = new Query(BaseUrl);
		
		q.addToQuery("tunniste", "1");
		//q.addToQuery("x", "");
		//q.addToQuery("y", "");
		//q.addToQuery("z", "");
		//q.addToQuery("sade", "");
		q.addToQuery("tie", "1");
		//q.addToQuery("ajr", "");
		q.addToQuery("aosa", "36");
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
		expected.setX(242499.7250232);
		expected.setY(6709246.34980926);
		expected.setZ(16.0709999999963);
		//expected.setValimatka();
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(36);
		expected.setAet(0);
		//expected.setLink_id();
		//expected.setM_arvo();
		expected.setKuntakoodi(853);
		expected.setKuntanimi("Turku");
		//expected.setKatunimi();
		//expected.setKatunimi_se();
		//expected.setKatunumero();
		//expected.setTietyyppi();
		//expected.setVaylan_luonne();
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		expected.setEly(2);
		//expected.setElynimi();
		expected.setUalue(400);
		expected.setUaluenimi("Kunta hoitaa");

		//expected.setGeometria();
		//expected.setPituus();
		expected.setX_loppu(241080.825024594);
		expected.setY_loppu(6711666.30579962);
		expected.setZ_loppu(14.502999999997);
		//expected.setValimatka_loppu();
		//expected.setL_tie();
		//expected.setL_ajr();
		expected.setLosa(36);
		expected.setLet(2785);
		//expected.setLink_id_loppu();
		//expected.setM_arvo_loppu();
		expected.setKuntakoodi_loppu(853);
		expected.setKuntanimi_loppu("Turku");
		//expected.setKatunimi_loppu();
		//expected.setKatunimi_se_loppu();
		//expected.setKatunumero_loppu();
		//expected.setTietyyppi_loppu();
		//expected.setVaylan_luonne_loppu();
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		expected.setEly_loppu(2);
		//expected.setElynimi_loppu();
		expected.setUalue_loppu(400);
		expected.setUaluenimi_loppu("Kunta hoitaa");
		
		// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN

		assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
	
	}

}