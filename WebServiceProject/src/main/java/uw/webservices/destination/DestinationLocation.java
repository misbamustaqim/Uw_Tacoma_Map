package uw.webservices.destination;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uw.webservices.geolocationresponse.Location;

@RestController
public class DestinationLocation {

	@RequestMapping("/getbuildinglocation")
	protected Location getbuildinglocation(@RequestParam(value="address") String address)
	{
		if (address == null) {
			return null;
		} else {
			
			try {
					DestinationLocation locServlet = new DestinationLocation();
					Location loc = locServlet.getLatitudeAndLongitude(address);
					return loc;

			} catch (Exception e) {
				try {
					throw e;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return null;
	}

	public Location getLatitudeAndLongitude(String address) throws IOException {
			GetDestination getDestination = new GetDestination();
		
			Location location = getDestination.getLatitudeAndLongitude(address);
			
			return location;
	}

}
