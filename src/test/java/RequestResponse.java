import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


public class RequestResponse {
	
	String query;
	String response;
	String json;
	String type;
	String geomType;
	String coordinates;
	String virheet = null;
	String featurecollection_metadata = null;
	ResultParameters result = new ResultParameters();
	
	
	//Constructor
	public RequestResponse(String url) throws IOException {
		query = url;
		response = getResponse(url);
		json = getPropertiesJson(response);
		result = getResult(json, result);
		result.setType(this.type);
		result.setGeomType(this.geomType);
		result.setCoordinates(this.coordinates);
	}
	
	
	private String getResponse(String query) throws IOException {
		StringBuilder content;
		String staticContent;
		URL url = new URL(query);
	
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String line;
			content = new StringBuilder();
			while ((line = input.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
		} finally {
			connection.disconnect();
		}

		staticContent = content.toString();
		return staticContent;
	}
	
	
	private String getPropertiesJson(String response) {
		response = response.replaceAll(System.lineSeparator(), "");
		JSONObject jsonData = new JSONObject(response);
		JSONArray features = new JSONArray(jsonData.getJSONArray("features").toString()); 
		JSONObject feature = features.getJSONObject(0);
		JSONObject geometry = feature.getJSONObject("geometry");
		this.type = feature.getString("type");
		this.geomType = geometry.getString("type");
		this.coordinates = geometry.getJSONArray("coordinates").toString();
		JSONObject properties = feature.getJSONObject("properties");
		try {
			JSONObject featurecoll_metadata = properties.getJSONObject("featurecollection_metadata");
			this.featurecollection_metadata = featurecoll_metadata.toString();
		}
		catch(Exception e) {
			//
		}
		try {
			JSONObject errors = properties.getJSONObject("virheet");
			this.virheet = errors.toString();
		}
		catch(Exception e) {
			//
		}
		response = properties.toString();
		return response;
	}
	
	
	private ResultParameters getResult(String json, ResultParameters result) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ResultParametersTemp resultTemp = new ResultParametersTemp();
		resultTemp = mapper.readValue(json, ResultParametersTemp.class);
		result.mapBasicProperties(resultTemp);
		return result;
	}
	
}