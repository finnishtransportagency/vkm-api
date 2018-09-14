package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import fi.livi.vkm.util.VkmUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;

public class ReverseGeocodeRequest extends VkmData {

	private Double x;
	private Double y;
	private String katunimi;
	private Integer sade;
	private String palautusarvot;

	public ReverseGeocodeRequest(String tunniste, Double x, Double y, Integer sade, String palautusarvot) {
		super(tunniste);
		this.x = x;
		this.y = y;
		this.sade = sade;
		this.palautusarvot = palautusarvot;
	}

    public static InParameters[] fromJson(JSONObject jsonData) throws JSONException {
		try{
			JSONArray array = jsonData.getJSONArray("reversegeocode");
		
			InParameters[] result = new InParameters[array.length()];
		
			for (int i = 0; i < array.length(); i++) {
				JSONObject params = array.getJSONObject(i);
				
                result[i] = new InParameters(VkmUtil.getJsonString(params, JSON_TUNNISTE),
                params.getDouble(JSON_X),
				params.getDouble(JSON_Y),
				VkmUtil.getJsonInteger(params, JSON_SADE),
                VkmUtil.getJsonString(params, JSON_PALAUTUSARVOT)
                
                
                
                );
				
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}