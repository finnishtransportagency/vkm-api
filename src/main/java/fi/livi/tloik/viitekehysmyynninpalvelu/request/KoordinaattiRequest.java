package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;

public class KoordinaattiRequest extends VkmData{

	private double x;
	private double y;
	private Double z;
	private Integer sade;
	private String vaylat;
	private String palautusarvot;

	public KoordinaattiRequest(String tunniste, double x, double y, Double z,Integer sade, String vaylat, String palautusarvot) {
		super(tunniste);
		this.x = x;
		this.y = y;
		this.z = z;
		this.sade = sade;
		this.vaylat = vaylat;
		this.palautusarvot = palautusarvot;
	}


    public static InParameters[] fromJson(JSONObject jsonData) throws JSONException {
		try{
			JSONArray array = jsonData.getJSONArray("");
		
			InParameters[] result = new InParameters[array.length()];
		
			for (int i = 0; i < array.length(); i++) {
				JSONObject params = array.getJSONObject(i);
				
				result[i] = new InParameters(params.getString(JSON_TUNNISTE),
							params.getDouble(JSON_X),
                            params.getDouble(JSON_Y),
							params.getDouble(JSON_Z),
							params.getInt(JSON_SADE),
							params.getString(JSON_VAYLAT),
							params.getString(JSON_PALAUTUSARVOT)
							
                
                );
				
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}