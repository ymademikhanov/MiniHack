package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MHGoogleAPITest {
	private static String googleApiRequestUrl = "https://maps.googleapis.com/maps/api/directions/json?";
	private static String googleApiKey = "AIzaSyDdRpr14HhdbyWQkq5p2Pe0tMv2si_g4sY";
	
	public static void main(String[] args) throws IOException {
		testing1();
	}
	
	public static void testing1() throws IOException {
		String urlka = googleApiRequestUrl + "origin=Farringdon&destination=WhiteChapel&mode=train&key=" + googleApiKey;
		URL url = new URL(urlka);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
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
			System.out.println(response);
		} else {
			System.out.println("fail");
		}
	}
}
