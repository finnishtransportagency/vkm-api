package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import fi.livi.vkm.util.VkmUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;

public class TieosoiteValiRequest extends VkmData {

	private Integer tie;
	private Integer osa;
	private Integer etaisyys;
	private String ajoradat;
	private Integer losa;
	private Integer let;
	private Integer sade;
	private String palautusarvot;
	
	public TieosoiteValiRequest(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, Integer losa, Integer let, Integer sade, String palautusarvot) {
		super(tunniste);
		this.tie = tie;
		this.osa = osa;
		this.etaisyys = etaisyys;
		this.ajoradat = ajoradat;
		this.losa = losa;
		this.let = let;
		this.sade = sade;
		this.palautusarvot = palautusarvot;
	}

    public static InParameters[] fromJson(JSONObject jsonData) throws JSONException {
		try{
			JSONArray array = jsonData.getJSONArray("tieosoitevali");
		
			InParameters[] result = new InParameters[array.length()];
		
			for (int i = 0; i < array.length(); i++) {
				JSONObject params = array.getJSONObject(i);
				// Tietyyppi = 0. Tätä parametria ei lainkaan tarvita pyynnöissä, mutta sen on oltava olemassa TieosoiteAjorata-luokassa vastauksia varten.
                result[i] = new InParameters(VkmUtil.getJsonString(params, JSON_TUNNISTE),
				params.getInt(JSON_TIE),
				VkmUtil.getJsonInteger(params, JSON_OSA),
				VkmUtil.getJsonInteger(params, JSON_ETAISYYS), 
				VkmUtil.getJsonString(params, JSON_AJORATA),
				VkmUtil.getJsonInteger(params, JSON_LOSA),
				VkmUtil.getJsonInteger(params, JSON_LET),
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