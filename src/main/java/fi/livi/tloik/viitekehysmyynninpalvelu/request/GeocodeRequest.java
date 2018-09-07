package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;

public class GeocodeRequest extends VkmData {

	private Integer kuntakoodi;
	private String katunimi;
	private Integer katunumero;
	private Integer sade;
	private String palautusarvot;

	public GeocodeRequest(String tunniste, Integer kuntakoodi, String katunimi, Integer katunumero, Integer sade, String palautusarvot) {
		super(tunniste);
		this.kuntakoodi = kuntakoodi;
		this.katunimi = katunimi;
		this.katunumero = katunumero;
		this.sade = sade;
		this.palautusarvot = palautusarvot;
	}


    public static InParameters[] fromJson(JSONObject jsonData) throws JSONException {
		try{
			JSONArray array = jsonData.getJSONArray("");
		
			InParameters[] result = new InParameters[array.length()];
		
			for (int i = 0; i < array.length(); i++) {
				JSONObject params = array.getJSONObject(i);
				
                result[i] = new InParameters(params.getString(JSON_TUNNISTE),
                            params.getInt(JSON_KUNTAKOODI),
                            params.getString(JSON_KATUNIMI), 
							params.getInt(JSON_KATUNUMERO),
							params.getInt(JSON_SADE),
							params.getString(JSON_PALAUTUSARVOT)
                
                
                
                );
				
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}