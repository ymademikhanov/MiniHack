package main;

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
	public List<Suggestion> autocomplete(String input) {
		this.suggestions.clear();
		try {
			URL requestUrl = new URL(url + key + "&input=" + URLEncoder.encode(input, "UTF-8"));
			HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				JSONObject json = new ResponseToJson().convertToJson(con);
				JSONArray array = json.getJSONArray("predictions");
				for (int i= 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);
					this.suggestions.add(new Suggestion(obj.getString("description"), obj.getString("place_id")));
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return this.suggestions;
	}
}
