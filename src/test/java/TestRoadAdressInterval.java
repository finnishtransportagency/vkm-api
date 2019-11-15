import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRoadAdressInterval {
	
	@Test
	public void testBasicRoadAdressIntervalQuery() throws IOException {

		ResultParameters expected = new ResultParameters();
		RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/tieosoitevali?tie=10&osa=2&aet=0&losa=2&let=400&ajr=1");
		
		//expected.setGeometria(String geometria);
		//expected.setTunniste(String tunniste);
		expected.setX(245085.386021327);
		expected.setY(6712924.80482271);
		expected.setZ(11.5439999999944);
		expected.setX_loppu(245368.0237397933);
		expected.setY_loppu(6713263.916309222);
		expected.setZ_loppu(19.726472686506135);
		//expected.setValimatka(Double valimatka);
		//expected.setValimatka_loppu(Double valimatka_loppu);
		expected.setTie(10);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(0);
		//expected.setL_tie(Integer l_tie);
		//expected.setL_ajr(Integer l_ajr);
		expected.setLosa(2);
		expected.setLet(400);
		//expected.setLink_id(Integer link_id);
		//expected.setM_arvo(Double m_arvo);
		//expected.setLink_id_loppu(Integer link_id_loppu);
		//expected.setM_arvo_loppu(Double m_arvo_loppu);
		expected.setKuntakoodi(202);
		expected.setKuntanimi("Kaarina");
		//expected.setKatunimi(String katunimi);
		//expected.setKatunimi_se(String katunimi_se);
		//expected.setKatunumero(Integer katunumero);
		//expected.setKatunimi_loppu(String katunimi_loppu);
		//expected.setKatunimi_se_loppu(String katunimi_se_loppu);
		//expected.setKatunumero_loppu(Integer katunumero_loppu);
		//expected.setPituus(Double pituus);
		//expected.setVaylan_luonne(Integer vaylan_luonne);
		//expected.setTietyyppi(Integer tietyyppi);
		//expected.setVaylan_luonne_loppu(Integer vaylan_luonne_loppu);
		//expected.setTietyyppi_loppu(Integer tietyyppi_loppu);
		expected.setEly(2);
		//expected.setElynimi(String elynimi);
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		expected.setUalue(233);
		expected.setUaluenimi("Lieto 15 -20");
		expected.setKuntakoodi_loppu(202);
		expected.setKuntanimi_loppu("Kaarina");
		expected.setMaakuntakoodi_loppu(2);
		expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		expected.setEly_loppu(2);
		//expected.setElynimi_loppu(String elynimi_loppu);
		expected.setUalue_loppu(233);
		expected.setUaluenimi_loppu("Lieto 15 -20");

		assertThat(expected).isEqualToComparingFieldByField(testInfo.result);
	
	}
	
	/*************************** Työn alla
	@Test
	public void testBasicRoadAdressIntervalQueryWithGeometry() throws IOException {

		ResultParameters expected = new ResultParameters();
		RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/tieosoitevali?tie=10&osa=2&aet=0&losa=2&let=400&palautusarvot=5&ajr=1");
		
		//expected.setGeometria("{'type':'MultiLineString','coordinates':[[[245085.386021327,6712924.80482271],[245093.414021321,6712931.03582276],[245108.757987415,6712943.05279629]],[[245108.75802131,6712943.05282284],[245128.118021295,6712958.20082294],[245145.520021283,6712973.63882303],[245160.846021271,6712988.61282311],[245161.707021271,6712989.52182312]],[[245161.707021271,6712989.52182312],[245177.362021259,6713006.02582319],[245192.883021249,6713023.05582328],[245208.830021237,6713040.66882336],[245217.821016825,6713051.4858181]],[[245217.821021231,6713051.4858234],[245226.152929559,6713061.93070853]],[[245226.153021225,6713061.93082345],[245234.040021219,6713071.75882349],[245250.744021207,6713093.20782357],[245264.910021198,6713113.38882364],[245271.170021193,6713122.33182367],[245285.598021183,6713142.35182375],[245299.857021173,6713163.90782382],[245318.60402116,6713191.31982391],[245333.58302115,6713212.70782399],[245357.700021133,6713245.63782411],[245368.0237397933,6713263.916309222]]]}");
		//expected.setTunniste(String tunniste);
		//expected.setX(245085.386021327);
		//expected.setY(6712924.80482271);
		//expected.setZ(11.5439999999944);
		//expected.setX_loppu(245368.0237397933);
		//expected.setY_loppu(6713263.916309222);
		//expected.setZ_loppu(19.726472686506135);
		//expected.setValimatka(Double valimatka);
		//expected.setValimatka_loppu(Double valimatka_loppu);
		//expected.setTie(10);
		//expected.setAjr(1);
		//expected.setAosa(2);
		//expected.setAet(0);
		//expected.setL_tie(Integer l_tie);
		//expected.setL_ajr(Integer l_ajr);
		//expected.setLosa(2);
		//expected.setLet(400);
		//expected.setLink_id(Integer link_id);
		//expected.setM_arvo(Double m_arvo);
		//expected.setLink_id_loppu(Integer link_id_loppu);
		//expected.setM_arvo_loppu(Double m_arvo_loppu);
		//expected.setKuntakoodi(202);
		//expected.setKuntanimi("Kaarina");
		//expected.setKatunimi(String katunimi);
		//expected.setKatunimi_se(String katunimi_se);
		//expected.setKatunumero(Integer katunumero);
		//expected.setKatunimi_loppu(String katunimi_loppu);
		//expected.setKatunimi_se_loppu(String katunimi_se_loppu);
		//expected.setKatunumero_loppu(Integer katunumero_loppu);
		//expected.setPituus(444.16488787085405);
		//expected.setVaylan_luonne(Integer vaylan_luonne);
		//expected.setTietyyppi(Integer tietyyppi);
		//expected.setVaylan_luonne_loppu(Integer vaylan_luonne_loppu);
		//expected.setTietyyppi_loppu(Integer tietyyppi_loppu);
		//expected.setEly(2);
		//expected.setElynimi(String elynimi);
		//expected.setMaakuntakoodi(2);
		//expected.setMaakuntanimi("Varsinais-Suomi");
		//expected.setUalue(233);
		//expected.setUaluenimi("Lieto 15 -20");
		//expected.setKuntakoodi_loppu(202);
		//expected.setKuntanimi_loppu("Kaarina");
		//expected.setMaakuntakoodi_loppu(2);
		//expected.setMaakuntanimi_loppu("Varsinais-Suomi");
		//expected.setEly_loppu(2);
		//expected.setElynimi_loppu(String elynimi_loppu);
		//expected.setUalue_loppu(233);
		//expected.setUaluenimi_loppu("Lieto 15 -20");

		assertThat(expected).isEqualToComparingFieldByField(testInfo.result);
	
	}
	*****************/

}