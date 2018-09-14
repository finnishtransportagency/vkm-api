package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.vkm.util.VkmUtil;

public class KoordinaattiRequest extends VkmData{

	private double x;
	private double y;
	private Double z;
	private Integer tie;
	private Integer osa;
	private LocalDate tilannepvm;
	private LocalDate kohdepvm;
	private String ajoradat;
	private double x_loppu;
	private double y_loppu;
	private Double z_loppu;
	private Integer sade;
	private String vaylat;
	private String palautusarvot;

	public KoordinaattiRequest(String tunniste, double x, double y, Double z, Integer tie, Integer osa, String ajoradat, double x_loppu, double y_loppu, Double z_loppu, Integer sade, String vaylat, String palautusarvot) {
		super(tunniste);
		this.x = x;
		this.y = y;
		this.z = z;
		this.tie = tie;
		this.osa = osa;
		this.ajoradat = ajoradat;
		this.x_loppu = x_loppu;
		this.y_loppu = y_loppu;
		this.z_loppu = z_loppu;
		this.sade = sade;
		this.vaylat = vaylat;
		this.palautusarvot = palautusarvot;
	}

	
    public static InParameters[] fromJson(JSONObject jsonData) throws JSONException {
		try{
			JSONArray array = jsonData.getJSONArray("xyhaku");
			InParameters[] result = new InParameters[array.length()];
		
			for (int i = 0; i < array.length(); i++) {
				JSONObject params = array.getJSONObject(i);
				result[i] = new InParameters(VkmUtil.getJsonString(params, JSON_TUNNISTE),
				params.getDouble(JSON_X),
				params.getDouble(JSON_Y),
				VkmUtil.getJsonDouble(params, JSON_Z),
				VkmUtil.getJsonInteger(params, JSON_TIE),
				VkmUtil.getJsonInteger(params, JSON_OSA),
				VkmUtil.getJsonString(params, JSON_AJORATA),
				VkmUtil.getJsonLocalDate(params, JSON_TILANNEPVM),
				VkmUtil.getJsonLocalDate(params, JSON_KOHDEPVM),
				VkmUtil.getJsonDouble(params, JSON_X_LOPPU),
				VkmUtil.getJsonDouble(params, JSON_Y_LOPPU),
				VkmUtil.getJsonDouble(params, JSON_Z_LOPPU),
				VkmUtil.getJsonInteger(params, JSON_SADE),
				VkmUtil.getJsonString(params, JSON_VAYLAT),
				VkmUtil.getJsonString(params, JSON_PALAUTUSARVOT)
                );
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}