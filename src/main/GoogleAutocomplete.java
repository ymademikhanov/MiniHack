package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleAutocomplete implements Autocomplete {
	private String url = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=";
	private String key = "AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	private List<Suggestion> suggestions = new ArrayList<Suggestion>();;

	@Override
	public void autocomplete(String input) {
		suggestions.clear();
		try {
			URL requestUrl = new URL(url + key + "&input=" + URLEncoder.encode(input, "UTF-8"));
			HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				StringBuffer response = new StringBuffer();
				String inputLine;
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				JSONArray array = new JSONObject(response.toString()).getJSONArray("predictions");
				for (int i= 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					suggestions.add(new Suggestion(obj.getString("description"), obj.getString("place_id")));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@Override
	public List<Suggestion> suggestions() {
		return suggestions;
	}
}
