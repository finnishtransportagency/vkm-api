package fi.livi.tloik.viitekehysmyynninpalvelu.request;

public abstract class VkmData {

	protected static final String JSON_TUNNISTE = "tunniste";

	protected static final String JSON_PALAUTUSARVO = "palautusarvo";

	protected static final String JSON_VIRHETEKSTI = "virheteksti";

	protected static final String JSON_TIE = "tie";

	protected static final String JSON_OSA = "osa";

	protected static final String JSON_ETAISYYS = "etaisyys";

	protected static final String JSON_LOSA = "losa";

	protected static final String JSON_LET = "let";

	protected static final String JSON_AJORATA = "ajoradat";

	protected static final String JSON_VALIMATKA = "valimatka";
	
	protected static final String JSON_TILANNEPVM = "tilannepvm";
	
	protected static final String JSON_KOHDEPVM = "kohdepvm";

	protected static final String JSON_X = "x";

	protected static final String JSON_Y = "y";

	protected static final String JSON_Z = "z";

	protected static final String JSON_X_LOPPU = "x_loppu";

	protected static final String JSON_Y_LOPPU = "y_loppu";

	protected static final String JSON_Z_LOPPU = "z_loppu";

	protected static final String JSON_SADE = "sade";
	
	protected static final String JSON_VAYLAT = "vaylan_luonne";
	
	protected static final String JSON_TIETYYPPI = "tietyyppi";

	protected static final String JSON_PALAUTUSARVOT = "palautusarvot";

	// ***************************************************
	// Alueet
	protected static final String JSON_KUNTAKOODI = "kuntakoodi";
	protected static final String JSON_KUNTA_NIMI = "kunta_nimi";
	protected static final String JSON_MAAKUNTA = "maakunta";
	protected static final String JSON_MAAKUNTA_NIMI = "maakunta_nimi";
	protected static final String JSON_ELY = "ely";
	protected static final String JSON_ELY_NIMI = "ely_nimi";
	protected static final String JSON_URAKKA_ALUE = "urakka_alue";
	protected static final String JSON_URAKKA_ALUE_NIMI = "urakka_alue_nimi";
	// Alueet
	// *****************************************************

	//******************************************************
	// Geocode
	protected static final String JSON_KATUNIMI = "katunimi";
	protected static final String JSON_KATUNUMERO = "katunumero";
	// Geocode
	//******************************************************
	protected static final String JSON_RAIDE_TEXT = "raide_text";

	private String tunniste;
	
	public VkmData(String tunniste) {
		this.tunniste = tunniste;
	}

	public String getTunniste() {
		return tunniste;
	}
	
	@Override
	public String toString() {
		return "tunniste=" +tunniste;
	}

}
