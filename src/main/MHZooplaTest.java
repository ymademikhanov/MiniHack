package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Connect to Zoopla.
public class MHZooplaTest {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws IOException {
		//http://api.zoopla.co.uk/api/v1/zed_index.js?api_key=xxxxxxxxx&postcode=sw185rw
		
		String urlka = "https://api.zoopla.co.uk/api/v1/property_listings.js?api_key=twyn9rt5kxrssvr2add22ecs&area=Farringdon";
		URL url = new URL(urlka);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		
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
