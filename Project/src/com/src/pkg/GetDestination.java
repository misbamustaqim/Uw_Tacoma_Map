package com.src.pkg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.src.pkg.geolocationresponse.BuildingLocation;
import com.src.pkg.geolocationresponse.Location;

public class GetDestination {

	public Location getLatitudeAndLongitude(String address) throws IOException {
		String responseString = getAdressAndGenerateURI(address);

		BuildingLocation location = new Gson().fromJson(responseString, BuildingLocation.class);

		Location loc = location.getResults().iterator().next().getGeometry().getLocation();
		return loc;
	}

	public String getAdressAndGenerateURI(String address) throws IOException {

		String str = address.replace(" ", "+");

		String URLString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + str
				+ ",+tacoma,+WA&key=AIzaSyDT6Z8Vuu5mXcG-jJj4knG5tDjVffPKW6o";
		System.out.println(" URL " + URLString);

		URL url = new URL(URLString);

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "test");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());

		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

}
