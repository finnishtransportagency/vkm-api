package fi.livi.tloik.viitekehysmyynninpalvelu.request;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.InParameters;
import fi.livi.vkm.VkmVirheException;

public class VkmRequest {

    private String in;
    private InParameters[] data;
    private ArrayList<InParameters> list = new ArrayList<InParameters>();

    public VkmRequest(String json) throws VkmVirheException, JSONException {
        //this.in = haku;
		JSONTokener tokener = new JSONTokener(json);
		JSONObject jsonData;
        while(tokener.more()){
            jsonData = (JSONObject) tokener.nextValue();
            if (jsonData.has("xyhaku")){
            JSONArray array = jsonData.getJSONArray("xyhaku");
            this.list.addAll(KoordinaattiRequest.fromJson(array));

            }
            else if(jsonData.has("tieosoitehaku")){
                JSONArray array = jsonData.getJSONArray("tieosoitehaku");
                this.list.addAll(TieosoiteRequest.fromJson(array));
            }
            else if(jsonData.has("tieosoitevali")) {
                JSONArray array = jsonData.getJSONArray("tieosoitevali");
                this.list.addAll(TieosoiteValiRequest.fromJson(array));
            }
            else if(jsonData.has("geocode")){
                JSONArray array = jsonData.getJSONArray("geocode");
                this.list.addAll(GeocodeRequest.fromJson(array));
            }
            else if(jsonData.has("reversegeocode")){
                JSONArray array = jsonData.getJSONArray("reversegeocode");
                this.list.addAll(ReverseGeocodeRequest.fromJson(array));
            }
        }
        
        
		
    }
    
    /**
	 * @return IN parameter
	 */
	public String getIn() {
		return in;
    }
    
    public InParameters[] getData() {
        data = new InParameters[list.size()];
        list.toArray(data);
		return data;
	}

}