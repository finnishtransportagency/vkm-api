import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestXY {
	
	@Test
	public void testBasicXYQuery() throws IOException {

		ResultParameters expected = new ResultParameters();
		RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/xyhaku?x=384281&y=6674531");
		
		//expected.setGeometria(String geometria);
		//expected.setTunniste(String tunniste);
		expected.setX(384281.0511921047);
		expected.setY(6674531.617088948);
		expected.setZ(15.3264532305459);
		//expected.setX_loppu(Double x_loppu);
		//expected.setY_loppu(Double y_loppu);
		//expected.setZ_loppu(Double z_loppu);
		expected.setValimatka(0.619208690016535);
		//expected.setValimatka_loppu(Double valimatka_loppu);
		expected.setTie(1);
		expected.setAjr(1);
		expected.setAosa(2);
		expected.setAet(11);
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
		expected.setVaylan_luonne(11);
		//expected.setTietyyppi(Integer tietyyppi);
		//expected.setVaylan_luonne_loppu(Integer vaylan_luonne_loppu);
		//expected.setTietyyppi_loppu(Integer tietyyppi_loppu);
		expected.setEly(1);
		//expected.setElynimi(String elynimi);
		expected.setMaakuntakoodi(1);
		expected.setMaakuntanimi("Uusimaa");
		expected.setUalue(131);
		expected.setUaluenimi("Vantaa 14-19");
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