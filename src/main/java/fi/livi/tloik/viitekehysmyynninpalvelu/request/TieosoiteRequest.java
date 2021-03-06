package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.vkm.util.VkmUtil;

public class TieosoiteRequest extends VkmData {
    
	private Integer tie;
	private Integer osa;
	private Integer etaisyys;
	private String ajoradat;
	private Integer sade;
	private String palautusarvot;
	
	public TieosoiteRequest(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, Integer sade, String palautusarvot) {
		super(tunniste);
		this.tie = tie;
		this.osa = osa;
		this.etaisyys = etaisyys;
		this.ajoradat = ajoradat;
		this.sade = sade;
		this.palautusarvot = palautusarvot;
	}

    public static ArrayList<InParameters> fromJson(JSONArray jsonArray) throws JSONException {
		try{
			ArrayList<InParameters> result = new ArrayList<InParameters>();
		
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject params = jsonArray.getJSONObject(i);
				// Tietyyppi = 0. Tätä parametria ei lainkaan tarvita pyynnöissä, mutta sen on oltava olemassa TieosoiteAjorata-luokassa vastauksia varten.
                result.add(new InParameters("tieosoitehaku",params.getString(JSON_TUNNISTE),
							params.getInt(JSON_TIE),
                            params.getInt(JSON_OSA),
                            params.getInt(JSON_ETAISYYS), 
							params.getString(JSON_AJORATA),
							VkmUtil.getJsonLocalDate(params, JSON_TILANNEPVM),
							VkmUtil.getJsonLocalDate(params, JSON_KOHDEPVM),
							params.getInt(JSON_SADE),
							VkmUtil.getJsonString(params, JSON_PALAUTUSARVOT)
							
                
                
                
                ));
				
			}
			return result;
		}catch (Exception e){
			throw new JSONException("Virhe json-parametrissa : "+ e.getMessage());
		}
    }
}