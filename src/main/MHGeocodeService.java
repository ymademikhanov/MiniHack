package main;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/geocode")
public class MHGeocodeService {
	private String url = "https://maps.googleapis.com/maps/api/geocode/json?&key=";
	private String key = "AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	
	@GET
	public Response getList(@QueryParam("place_id") String input) {
		try {
			URL requestUrl = new URL(url + key + "&place_id=" + input);
			HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			String result = "Response code: " + responseCode;
			if (responseCode == HttpURLConnection.HTTP_OK) {
				JSONObject json = new ResponseToJson().convertToJson(con);
				JSONArray arr = json.getJSONArray("results");
				JSONObject obj = arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
				result = obj.toString();
			}
			return Response.ok(result).build();
		} catch (Exception e) {
			System.out.println(e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}