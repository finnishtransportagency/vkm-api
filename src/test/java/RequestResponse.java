import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestResponse {
	
	String query;
	String response;
	String json;
	String geom;
	ResultParameters result = new ResultParameters();
	
	public RequestResponse(String url) throws IOException {
		query = url;
		response = getResponse(url);
		json = getJson(response);
		result = getResult(json);
		result.setGeometria(geom);
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

		return staticContent = content.toString();
	}
	
	private String getJson(String response) {
		response = response.replaceAll(System.lineSeparator(), "");
		response = removeBracketsFromEnds(response);
		response = extractGeometry(response);
		return response;
	}
	
	private ResultParameters getResult(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ResultParameters ready = new ResultParameters();
		
		return ready = mapper.readValue(json, ResultParameters.class);
		
	}
	
	private static String removeBracketsFromEnds(String process) {
		Integer bracketStartCharNumber = 91;
		Integer bracketEndCharNumber = 93;
		Integer charNumber;
		boolean search = true;
		while (search) {
			charNumber = (int) process.charAt(0);
			if ( charNumber == bracketStartCharNumber ) {
				process = process.substring(1);
			}
			else {
				search = false;
			}
		}
		
		search = true;
		while (search) {
			charNumber = (int) process.charAt(process.length()-1);
			if ( charNumber == bracketEndCharNumber ) {
				process = process.substring(0,process.length()-1);
			}
			else {
				search = false;
			}
		}
		
		return process;
	}
	
	private String extractGeometry(String json) {
		String regexGeom = ",\"geometria\".*.]]]}";
		Pattern r = Pattern.compile(regexGeom);
		Matcher m = r.matcher(json);
		
		if (m.find()) {
			geom = json;
			String regexOtherStart = ".*.\"geometria\":";
			String regexOtherEnd = "]]]}.*.";
			geom = geom.replaceAll(regexOtherStart, "");
			geom = geom.replaceAll(regexOtherEnd, "]]]}");
			
			json = json.replaceAll(regexGeom, "");
		}
		
		return json;
	}

}