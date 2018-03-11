package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;


@Path("/findPlace")
public class MHService {

	private static String googlePlacesUrl = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyC_TEVCna6Qfoe54Tv7qELwCjZ70GpYHfU";
	
	public MHService() {
	}
	
	@GET
	public Response getList(@QueryParam("input") String input) throws IOException {
		String result;
		String request = googlePlacesUrl + "&input=" + URLEncoder.encode(input, "UTF-8");
		
		System.out.println(request);
		
		URL requestUrl = new URL(request);
		HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
		con.setRequestMethod("GET");
		
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
//			System.out.println(response);
			result = response.toString();
			
			JSONObject obj = new JSONObject(result);
			JSONArray obj2 = obj.getJSONArray("predictions");
			
			List<String> list = new ArrayList<String>();
			
			String fromList = "[";
			for (int i= 0; i< obj2.length(); i++) {
			    list.add(obj2.getJSONObject(i).getString("description"));
			    fromList += "\"";
			    fromList += obj2.getJSONObject(i).getString("description");
			    fromList += "\", ";
			}
			
			fromList += "\"\"]";
			System.out.println(fromList);
			result = new Gson().toJson(list);
			result = fromList;
		} else {
			result = "fail";
			System.out.println(responseCode);
		}
		return Response.ok(result).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response postInstance(String data) {
		ResponseBuilder builder;
		return null;
	}
	
	@DELETE
	public Response deleteInstance(@QueryParam("id") String id) {
		ResponseBuilder builder;
		return null;
	}
	
}