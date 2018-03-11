package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

@Path("/geocode")
public class MHGeocodeService {
	private Autocomplete autocompleter;
	private String url = "https://maps.googleapis.com/maps/api/geocode/json?&key=";
	private String key = "AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	public MHGeocodeService() {
		this.autocompleter = new DummyAutocomplete();
	}
	
	@GET
	public Response getList(@QueryParam("place_id") String input) {
		//place_id=ChIJd8BlQ2BZwokRAFUEcm_qrcAYOUR_API_KEY
		String result = "fail";
		try {
			URL requestUrl = new URL(url + key + "&place_id=" + input);
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
				
				JSONArray arr = new JSONObject(response.toString()).getJSONArray("results");
				JSONObject obj = arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
				result = obj.toString();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return Response.ok(result).build();
	}
}