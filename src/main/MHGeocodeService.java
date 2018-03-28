package main;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

@Path("/geocode")
public class MHGeocodeService {
	private static double coefficient = 150000;
	private String url = "https://maps.googleapis.com/maps/api/geocode/json?&key=";
	private String key = "AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	
	private static int getZoom(JSONObject viewport) {
		JSONObject northeast = viewport.getJSONObject("northeast");
		JSONObject southwest = viewport.getJSONObject("southwest");
		
		double height = northeast.getDouble("lat") - southwest.getDouble("lat");
		double width = northeast.getDouble("lng") - southwest.getDouble("lng");
		
		double sum = height + width;
		double zoom = (1 / sum * MHGeocodeService.coefficient);
		return Math.max(11, (int) Math.log(zoom));
	}
	
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
				JSONObject geometry = json.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");
				JSONObject location = geometry.getJSONObject("location");
				location.put("zoom", MHGeocodeService.getZoom(geometry.getJSONObject("viewport")));
				result = location.toString();
			}
			return Response.ok(result).build();
		} catch (Exception e) {
			System.out.println(e);
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}