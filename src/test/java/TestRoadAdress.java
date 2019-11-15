import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestRoadAdress {
	
	@Test
	public void testBasicRoadAdressQuery() throws IOException {

		ResultParameters expected = new ResultParameters();
		RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/tieosoitehaku?ajr=1&tie=1&aosa=1&aet=0");
		
		//expected.setGeometria(String geometria);
		//expected.setTunniste(String tunniste);
		expected.setX(385885.719999971);
		expected.setY(6671747.8099999);
		expected.setZ(9.66899999999441);
		//expected.setX_loppu(Double x_loppu);
		//expected.setY_loppu(Double y_loppu);
		//expected.setZ_loppu(Double z_loppu);
		//expected.setValimatka(Double valimatka);
		//expected.setValimatka_loppu(Double valimatka_loppu);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(1);
		expected.setAet(0);
		//expected.setL_tie(Integer l_tie);
		//expected.setL_ajr(Integer l_ajr);
		//expected.setLosa(Integer losa);
		//expected.setLet(Integer let);
		//expected.setLink_id(Integer link_id);
		//expected.setM_arvo(Double m_arvo);
		//expected.setLink_id_loppu(Integer link_id_loppu);
		//expected.setM_arvo_loppu(Double m_arvo_loppu);
		expected.setKuntakoodi(91);
		expected.setKuntanimi("Helsinki");
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
		expected.setEly(1);
		//expected.setElynimi(String elynimi);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setUalue(400);
		expected.setUaluenimi("Kunta hoitaa");
		//expected.setKuntakoodi_loppu(Integer kuntakoodi_loppu);
		//expected.setKuntanimi_loppu(String kuntanimi_loppu);
		//expected.setMaakuntakoodi_loppu(Integer maakuntakoodi_loppu);
		//expected.setMaakuntanimi_loppu(String maakuntanimi_loppu);
		//expected.setEly_loppu(Integer ely_loppu);
		//expected.setElynimi_loppu(String elynimi_loppu);
		//expected.setUalue_loppu(Integer ualue_loppu);
		//expected.setUaluenimi_loppu(String ualuenimi_loppu);

		assertThat(expected).isEqualToComparingFieldByField(testInfo.result);
	
	}

}