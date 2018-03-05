package uw.webservices.destination;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;

import uw.webservices.geolocationresponse.Location;

@RestController
public class DatabaseConnectionForRestro {

	@RequestMapping("/getrestrolocation")
	protected Location getrestrolocation(@RequestParam(value="restro") String restro) {

		if (restro == null) {
			return null;
		} else {
			
			String query ="SELECT address FROM restaurant WHERE name like '%" + restro + "%'; " ;
			
			DatabaseHelper databaseHelper = new DatabaseHelper();
			
				System.out.println(query);
			
				String address = databaseHelper.GetAddressFromDatabase(query, "address");
				
				System.out.println("Address: "+ address);
				
				DestinationLocation locServlet = new DestinationLocation();
				Location loc;
				try {
					loc = locServlet.getLatitudeAndLongitude(address);
					
					return loc;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return null;
	}
}