package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;


@Path("/findPlace")
public class MHService {

	private static String googleAutofillUrl = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	
	public MHService() {
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) throws IOException {
		String request = googleAutofillUrl + "&input=" + URLEncoder.encode(input, "UTF-8");
		
		URL requestUrl = new URL(request);
		HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
		con.setRequestMethod("GET");
		
		System.out.println(requestUrl);
		
		String result;
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			JSONObject obj = new JSONObject(response.toString());
			JSONArray arr = obj.getJSONArray("predictions");
			
			List<AutocompleteSuggestion> list = new ArrayList<AutocompleteSuggestion>();
			for (int i= 0; i< arr.length(); i++) {
				String description = arr.getJSONObject(i).getString("description");
				String place_id = arr.getJSONObject(i).getString("place_id");
			    list.add(new AutocompleteSuggestion(description, place_id));
			}
			result = new Gson().toJson(list);
			System.out.println(result);
		} else {
			System.out.println(responseCode);
			result = "fail";
		}
		return Response.ok(result).build();
	}
}