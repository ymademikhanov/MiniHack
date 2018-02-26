package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

public class MHGoogleAPITest {	
	public static void main(String[] args) throws IOException {
		//AIzaSyDdRpr14HhdbyWQkq5p2Pe0tMv2si_g4sY
		GooglePlaces client = new GooglePlaces("AIzaSyDdRpr14HhdbyWQkq5p2Pe0tMv2si_g4sY");
		
		double lat = 51.5332643;
		double lng = -0.1281917;
		double radius = 5000;
		List<Place> places = client.getNearbyPlaces(lat, lng, radius, GooglePlaces.MAXIMUM_RESULTS);
		
		for (Place place : places) {
			System.out.println(place.getName());
		}
		
		System.out.println("Finished");
	}
	
	public static void testing1() throws IOException {
		String key="AIzaSyDdRpr14HhdbyWQkq5p2Pe0tMv2si_g4sY";
		String urlka = "https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=" + key;
		URL url = new URL(urlka);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
//		con.setRequestProperty("User-Agent", USER_AGENT);
		
		int responseCode = con.getResponseCode();
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response);
		} else {
			System.out.println("fail");
		}
	}
}
