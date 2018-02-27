package com.src.pkg;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.src.pkg.geolocationresponse.BuildingLocation;
import com.src.pkg.geolocationresponse.Location;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class ServletToGetTheLocationOfBuilding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletToGetTheLocationOfBuilding() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter write = response.getWriter();
		if (request.getParameter("address") == null) {
			write.println(" Please Enter Your name");
		} 
		else {
			String address = request.getParameter("address");
			Location location = getLatitudeAndLongitude(address);
			
			double latittude = location.getLat();
			double longitude = location.getLng();
			write.println("  " + latittude + "," + longitude);
		}
		
	}

	public Location getLatitudeAndLongitude(String address) throws IOException {
			String responseString = getAdressAndGenerateURI(address);

			BuildingLocation location= new Gson().fromJson(responseString, BuildingLocation.class);
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
