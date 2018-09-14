package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.vkm.VkmVirheException;

public class VkmRequest {

    private String in;
    private InParameters[] data;

    public VkmRequest(String haku, String json) throws VkmVirheException, JSONException {
        this.in = haku;
		JSONTokener tokener = new JSONTokener(json);
		JSONObject jsonData;
        jsonData = (JSONObject) tokener.nextValue();
        if("xyhaku".equalsIgnoreCase(haku)){
            this.data = KoordinaattiRequest.fromJson(jsonData);
        }
        else if("tieosoitehaku".equalsIgnoreCase(haku)){
            this.data = TieosoiteRequest.fromJson(jsonData);
        }
        else if("tieosoitevali".equalsIgnoreCase(haku)) {
            this.data = TieosoiteValiRequest.fromJson(jsonData);
        }
        else if("geocode".equalsIgnoreCase(haku)){
            this.data = GeocodeRequest.fromJson(jsonData);
        }
        else if("reversegeocode".equalsIgnoreCase(haku)){
            this.data = ReverseGeocodeRequest.fromJson(jsonData);
        }
		
    }
    
    /**
	 * @return IN parameter
	 */
	public String getIn() {
		return in;
    }
    
    public InParameters[] getData() {
		return data;
	}

}