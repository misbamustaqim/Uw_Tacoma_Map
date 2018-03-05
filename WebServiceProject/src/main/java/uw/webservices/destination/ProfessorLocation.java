package uw.webservices.destination;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.*;
import uw.webservices.geolocationresponse.Location;

@RestController
public class ProfessorLocation {

	@RequestMapping("/getproflocation")
	protected Location getproflocation(@RequestParam(value="prof") String prof)
			throws ServletException, IOException {

		if (prof == null) {
			return null;
		} else {
			
			String query ="SELECT address FROM faculty WHERE name like '%" + prof + "%'; " ;
			//System.out.println(query);

			DatabaseHelper databaseHelper = new DatabaseHelper();
			
			String address = databaseHelper.GetAddressFromDatabase(query, "address");
			
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
