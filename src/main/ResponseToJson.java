package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.json.JSONObject;

public class ResponseToJson {
	
	public JSONObject convertToJson(HttpURLConnection con) {
		String result = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			result = response.toString();
		} catch(Exception e) {
			System.out.println(e);
		}
		return new JSONObject(result);
	}
}
