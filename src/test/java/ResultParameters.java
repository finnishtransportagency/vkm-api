import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ResultParameters{
	
	public String type;
	public String coordinates;
	public String tunniste;
	public Double x;
	public Double y;
	public Double z;
	public Double x_loppu;
	public Double y_loppu;
	public Double z_loppu;
	public Double valimatka;
	public Double valimatka_loppu;
	public Integer tie;
	public Integer ajorata;
	public Integer osa;
	public Integer etaisyys;
	public Integer tie_loppu;
	public Integer ajorata_loppu;
	public Integer osa_loppu;
	public Integer etaisyys_loppu;
	public Integer link_id;
	public Double m_arvo;
	public Integer link_id_loppu;
	public Double m_arvo_loppu;
	public Integer kuntakoodi;
	public String kuntanimi;
	public String katunimi;
	public String katunimi_se;
	public Integer katunumero;
	public String katunimi_loppu;
	public String katunimi_se_loppu;
	public Integer katunumero_loppu;
	public Double pituus;
	public Integer vaylan_luonne;
	public Integer tietyyppi;
	public Integer vaylan_luonne_loppu;
	public Integer tietyyppi_loppu;
	public Integer ely;
	public String elynimi;
	public Integer maakuntakoodi;
	public String maakuntanimi;
	public Integer ualue;	
	public String ualuenimi;
	public Integer kuntakoodi_loppu;
	public String kuntanimi_loppu;
	public Integer maakuntakoodi_loppu;
	public String maakuntanimi_loppu;
	public Integer ely_loppu;
	public String elynimi_loppu;
	public Integer ualue_loppu;
	public String ualuenimi_loppu;
	public Boolean katkoja;
	public String virheet;
	//public JSONObject json;
	
	
	//Constructor
    public ResultParameters() throws JsonParseException, JsonMappingException, IOException {
		setAllToNull();
    }

    //
    //Getters
    
//    public String getGeometria() {
//		return geometria;
//	}
    
    public String getType() {
		return type;
	}
	
	public String getCoordinates() {
		return coordinates;
	}
    
    public String getTunniste() {
		return tunniste;
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getZ() {
		return z;
	}

	public Double getX_loppu() {
		return x_loppu;
	}

	public Double getY_loppu() {
		return y_loppu;
	}

	public Double getZ_loppu() {
		return z_loppu;
	}

	public Double getValimatka() {
		return valimatka;
	}

	public Double getValimatka_loppu() {
		return valimatka_loppu;
	}

	public Integer getTie() {
		return tie;
	}

	public Integer getAjr() {
		return ajorata;
	}

	public Integer getAosa() {
		return osa;
	}

	public Integer getAet() {
		return etaisyys;
	}

	public Integer getL_tie() {
		return tie_loppu;
	}

	public Integer getL_ajr() {
		return ajorata_loppu;
	}

	public Integer getLosa() {
		return osa_loppu;
	}

	public Integer getLet() {
		return etaisyys_loppu;
	}

	public Integer getLink_id() {
		return link_id;
	}

	public Double getM_arvo() {
		return m_arvo;
	}

	public Integer getLink_id_loppu() {
		return link_id_loppu;
	}

	public Double getM_arvo_loppu() {
		return m_arvo_loppu;
	}

	public Integer getKuntakoodi() {
		return kuntakoodi;
	}

	public String getKuntanimi() {
		return kuntanimi;
	}

	public String getKatunimi() {
		return katunimi;
	}

	public String getKatunimi_se() {
		return katunimi_se;
	}
	
	public Integer getKatunumero() {
		return katunumero;
	}

	public String getKatunimi_loppu() {
		return katunimi_loppu;
	}

	public String getKatunimi_se_loppu() {
		return katunimi_se_loppu;
	}
	
	public Integer getKatunumero_loppu() {
		return katunumero_loppu;
	}

	public Double getPituus() {
		return pituus;
	}

	public Integer getVaylan_luonne() {
		return vaylan_luonne;
	}

	public Integer getTietyyppi() {
		return tietyyppi;
	}

	public Integer getVaylan_luonne_loppu() {
		return vaylan_luonne_loppu;
	}

	public Integer getTietyyppi_loppu() {
		return tietyyppi_loppu;
	}

	public Integer getEly() {
		return ely;
	}

	public String getElynimi() {
		return elynimi;
	}

	public Integer getMaakuntakoodi() {
		return maakuntakoodi;
	}

	public String getMaakuntanimi() {
		return maakuntanimi;
	}

	public Integer getUalue() {
		return ualue;
	}

	public String getUaluenimi() {
		return ualuenimi;
	}

	public Integer getKuntakoodi_loppu() {
		return kuntakoodi_loppu;
	}

	public String getKuntanimi_loppu() {
		return kuntanimi_loppu;
	}

	public Integer getMaakuntakoodi_loppu() {
		return maakuntakoodi_loppu;
	}

	public String getMaakuntanimi_loppu() {
		return maakuntanimi_loppu;
	}

	public Integer getEly_loppu() {
		return ely_loppu;
	}

	public String getElynimi_loppu() {
		return elynimi_loppu;
	}

	public Integer getUalue_loppu() {
		return ualue_loppu;
	}

	public String getUaluenimi_loppu() {
		return ualuenimi_loppu;
	}
	
	public String getVirheet() {
		return virheet;
	}
	
	public Boolean getKatkoja(Boolean katkoja) {
		return katkoja;
	}
	
	//
	//Setters
	
//	public void setGeometria(String geom) {
//		this.geometria = geom;
//	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setTunniste(String tunniste) {
		this.tunniste = tunniste;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public void setX_loppu(Double x_loppu) {
		this.x_loppu = x_loppu;
	}

	public void setY_loppu(Double y_loppu) {
		this.y_loppu = y_loppu;
	}

	public void setZ_loppu(Double z_loppu) {
		this.z_loppu = z_loppu;
	}

	public void setValimatka(Double valimatka) {
		this.valimatka = valimatka;
	}

	public void setValimatka_loppu(Double valimatka_loppu) {
		this.valimatka_loppu = valimatka_loppu;
	}

	public void setTie(Integer tie) {
		this.tie = tie;
	}

	public void setAjr(Integer ajr) {
		this.ajorata = ajr;
	}

	public void setAosa(Integer aosa) {
		this.osa = aosa;
	}

	public void setAet(Integer aet) {
		this.etaisyys = aet;
	}

	public void setL_tie(Integer l_tie) {
		this.tie_loppu = l_tie;
	}

	public void setL_ajr(Integer l_ajr) {
		this.ajorata_loppu = l_ajr;
	}

	public void setLosa(Integer losa) {
		this.osa_loppu = losa;
	}

	public void setLet(Integer let) {
		this.etaisyys_loppu = let;
	}

	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}

	public void setM_arvo(Double m_arvo) {
		this.m_arvo = m_arvo;
	}

	public void setLink_id_loppu(Integer link_id_loppu) {
		this.link_id_loppu = link_id_loppu;
	}

	public void setM_arvo_loppu(Double m_arvo_loppu) {
		this.m_arvo_loppu = m_arvo_loppu;
	}

	public void setKuntakoodi(Integer kuntakoodi) {
		this.kuntakoodi = kuntakoodi;
	}

	public void setKuntanimi(String kuntanimi) {
		this.kuntanimi = kuntanimi;
	}

	public void setKatunimi(String katunimi) {
		this.katunimi = katunimi;
	}

	public void setKatunimi_se(String katunimi_se) {
		this.katunimi_se = katunimi_se;
	}
	
	public void setKatunumero(Integer katunumero) {
		this.katunumero = katunumero;
	}

	public void setKatunimi_loppu(String katunimi_loppu) {
		this.katunimi_loppu = katunimi_loppu;
	}

	public void setKatunimi_se_loppu(String katunimi_se_loppu) {
		this.katunimi_se_loppu = katunimi_se_loppu;
	}
	
	public void setKatunumero_loppu(Integer katunumero_loppu) {
		this.katunumero_loppu = katunumero_loppu;
	}

	public void setPituus(Double pituus) {
		this.pituus = pituus;
	}

	public void setVaylan_luonne(Integer vaylan_luonne) {
		this.vaylan_luonne = vaylan_luonne;
	}

	public void setTietyyppi(Integer tietyyppi) {
		this.tietyyppi = tietyyppi;
	}

	public void setVaylan_luonne_loppu(Integer vaylan_luonne_loppu) {
		this.vaylan_luonne_loppu = vaylan_luonne_loppu;
	}

	public void setTietyyppi_loppu(Integer tietyyppi_loppu) {
		this.tietyyppi_loppu = tietyyppi_loppu;
	}

	public void setEly(Integer ely) {
		this.ely = ely;
	}

	public void setElynimi(String elynimi) {
		this.elynimi = elynimi;
	}

	public void setMaakuntakoodi(Integer maakuntakoodi) {
		this.maakuntakoodi = maakuntakoodi;
	}

		//Tämä poistetaan kun maakuntakoodi palautuu geocodessakin lukuna
		public void setMaakuntakoodi(String maakuntakoodi) {
			this.maakuntakoodi = Integer.parseInt(maakuntakoodi);
		}
	
	public void setMaakuntanimi(String maakuntanimi) {
		this.maakuntanimi = maakuntanimi;
	}

	public void setUalue(Integer ualue) {
		this.ualue = ualue;
	}

	public void setUaluenimi(String ualuenimi) {
		this.ualuenimi = ualuenimi;
	}

	public void setKuntakoodi_loppu(Integer kuntakoodi_loppu) {
		this.kuntakoodi_loppu = kuntakoodi_loppu;
	}

	public void setKuntanimi_loppu(String kuntanimi_loppu) {
		this.kuntanimi_loppu = kuntanimi_loppu;
	}
		
	//Huom. myös maakuntakoodi_loppu palautuu geocodessa merkkinä
	public void setMaakuntakoodi_loppu(Integer maakuntakoodi_loppu) {
		this.maakuntakoodi_loppu = maakuntakoodi_loppu;
	}

	public void setMaakuntanimi_loppu(String maakuntanimi_loppu) {
		this.maakuntanimi_loppu = maakuntanimi_loppu;
	}

	public void setEly_loppu(Integer ely_loppu) {
		this.ely_loppu = ely_loppu;
	}

	public void setElynimi_loppu(String elynimi_loppu) {
		this.elynimi_loppu = elynimi_loppu;
	}

	public void setUalue_loppu(Integer ualue_loppu) {
		this.ualue_loppu = ualue_loppu;
	}

	public void setUaluenimi_loppu(String ualuenimi_loppu) {
		this.ualuenimi_loppu = ualuenimi_loppu;
	}
	
	public void setVirheet(String virheet) {
		this.virheet = virheet;
	}
	
	public void setKatkoja(Boolean katkoja) {
		this.katkoja = katkoja;
	}
	
	//
	//Other methods
	
	private void setAllToNull() throws JsonParseException, JsonMappingException, IOException {
		//setGeometria(null);
		setType(null);
		setCoordinates(null);
		setTunniste(null);
		setX(null);
		setY(null);
		setZ(null);
		setX_loppu(null);
		setY_loppu(null);
		setZ_loppu(null);
		setValimatka(null);
		setValimatka_loppu(null);
		setTie(null);
		setAjr(null);
		setAosa(null);
		setAet(null);
		setL_tie(null);
		setL_ajr(null);
		setLosa(null);
		setLet(null);
		setLink_id(null);
		setM_arvo(null);
		setLink_id_loppu(null);
		setM_arvo_loppu(null);
		setKuntakoodi(null);
		setKuntanimi(null);
		setKatunimi(null);
		setKatunimi_se(null);
		setKatunumero(null);
		setKatunimi_loppu(null);
		setKatunimi_se_loppu(null);
		setKatunumero_loppu(null);
		setPituus(null);
		setVaylan_luonne(null);
		setTietyyppi(null);
		setVaylan_luonne_loppu(null);
		setTietyyppi_loppu(null);
		setEly(null);
		setElynimi(null);
			Integer koodi = null; //Tämä poistetaan kun kaikki rajapinnat palauttavat numerot lukuina
		setMaakuntakoodi(koodi);
		setMaakuntanimi(null);
		setUalue(null);
		setUaluenimi(null);
		setKuntakoodi_loppu(null);
		setKuntanimi_loppu(null);
		setMaakuntakoodi_loppu(null);
		setMaakuntanimi_loppu(null);
		setEly_loppu(null);
		setElynimi_loppu(null);
		setUalue_loppu(null);
		setUaluenimi_loppu(null);
		setVirheet(null);
		setKatkoja(null);
	}
    
}


/*** Testikeissin pohja --- Liitä testikansiossa siihen rajapintaluokkaan, jota haluat testata

		@Test
		public void testNameOfTest() throws IOException {
			
			// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
			
			Query q = new Query(BaseUrl);
			
			//q.addToQuery("tunniste", "");
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
			//q.addToQuery("palautusarvot", "");
			//q.addToQuery("json", "");
			
			RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
			
			// ANNETAAN ODOTETUT OUT-PARAMETRIT
		
			ResultParameters expected = new ResultParameters();
			
			//expected.setType();
			//expected.setCoordinates();
			//expected.setTunniste();
			//expected.setX();
			//expected.setY();
			//expected.setZ();
			//expected.setValimatka();
			//expected.setTie();
			//expected.setAjr();
			//expected.setAosa();
			//expected.setAet();
			//expected.setLink_id();
			//expected.setM_arvo();
			//expected.setKuntakoodi();
			//expected.setKuntanimi();
			//expected.setKatunimi();
			//expected.setKatunimi_se();
			//expected.setKatunumero();
			//expected.setTietyyppi();
			//expected.setVaylan_luonne();
			//expected.setMaakuntakoodi();
			//expected.setMaakuntanimi();
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
			//expected.setKatkoja();
			//expected.setVirheet();
			
			// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
		
			assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
		
		}

***/