import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;

public class RequestResponse {
	
	String query;
	String response;
	ResultParameters result = new ResultParameters();
	
	public RequestResponse(String url) throws IOException {
		query = url;
		response = getResponse(url);
		result = getResult(response);	
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
	
	private ResultParameters getResult(String rawJson) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ResultParameters ready = new ResultParameters();
		
		rawJson = rawJson.replaceAll(System.lineSeparator(), "");
		rawJson = removeBracketsFromEnds(rawJson);
		
		return ready = mapper.readValue(rawJson, ResultParameters.class);
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

}