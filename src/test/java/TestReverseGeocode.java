import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestReverseGeocode {
	
	@Test
	public void testBasicReverseGeocodeQuery() throws IOException {

		ResultParameters expected = new ResultParameters();
		RequestResponse testInfo = new RequestResponse("http://localhost:8889/vkm-api/geocode?kuntakoodi=853&katunimi=Kauppiaskatu&katunumero=1");
		
		//expected.setGeometria(String geometria);
		//expected.setTunniste(String tunniste);
		expected.setX(239964.95471300394);
		expected.setY(6710871.919606933);
		//expected.setZ(Double z);
		//expected.setX_loppu(Double x_loppu);
		//expected.setY_loppu(Double y_loppu);
		//expected.setZ_loppu(Double z_loppu);
		//expected.setValimatka(Double valimatka);
		//expected.setValimatka_loppu(Double valimatka_loppu);
		//expected.setTie(Integer tie);
		//expected.setAjr(Integer ajr);
		//expected.setAosa(Integer aosa);
		//expected.setAet(Integer aet);
		//expected.setL_tie(Integer l_tie);
		//expected.setL_ajr(Integer l_ajr);
		//expected.setLosa(Integer losa);
		//expected.setLet(Integer let);
		//expected.setLink_id(Integer link_id);
		//expected.setM_arvo(Double m_arvo);
		//expected.setLink_id_loppu(Integer link_id_loppu);
		//expected.setM_arvo_loppu(Double m_arvo_loppu);
		expected.setKuntakoodi(853);
		expected.setKuntanimi("Turku");
		expected.setKatunimi("Kauppiaskatu");
		expected.setKatunimi_se("Köpmansgatan");
		expected.setKatunumero(1);
		//expected.setKatunimi_loppu(String katunimi_loppu);
		//expected.setKatunimi_se_loppu(String katunimi_se_loppu);
		//expected.setKatunumero_loppu(Integer katunumero_loppu);
		//expected.setPituus(Double pituus);
		//expected.setVaylan_luonne(Integer vaylan_luonne);
		//expected.setTietyyppi(Integer tietyyppi);
		//expected.setVaylan_luonne_loppu(Integer vaylan_luonne_loppu);
		//expected.setTietyyppi_loppu(Integer tietyyppi_loppu);
		//expected.setEly(Integer ely);
		//expected.setElynimi(String elynimi);
		expected.setMaakuntakoodi(2);
		expected.setMaakuntanimi("Varsinais-Suomi");
		//expected.setUalue(Integer ualue);
		//expected.setUaluenimi(String ualuenimi);
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