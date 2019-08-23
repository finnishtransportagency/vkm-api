package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.vkm.util.VkmUtil;

public class KoordinaattiRequest extends VkmData{

	/**
	 *
	 */

	private static final String XYHAKU = "xyhaku";
	private double x;
	private double y;
	private double z;
	private Integer tie;
	private Integer osa;
	private LocalDate tilannepvm;
	private LocalDate kohdepvm;
	private String ajoradat;
	private double x_loppu;
	private double y_loppu;
	private double z_loppu;
	private Integer sade;
	private String vaylat;
	private String palautusarvot;

	public KoordinaattiRequest(String tunniste, double x, double y, double z, Integer tie, Integer osa, String ajoradat, double x_loppu, double y_loppu, double z_loppu, Integer sade, String vaylat, String palautusarvot) {
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

	
    public static ArrayList<InParameters> fromJson(JSONArray jsonArray) throws JSONException {
		try{
			//JSONArray array = jsonData.getJSONArray("xyhaku");
			ArrayList<InParameters> result = new ArrayList<InParameters>();
		
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject params = jsonArray.getJSONObject(i);
				result.add(new InParameters(XYHAKU,VkmUtil.getJsonString(params, JSON_TUNNISTE),
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
                ));
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}