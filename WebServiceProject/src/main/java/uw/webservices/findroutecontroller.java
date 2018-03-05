package uw.webservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class findroutecontroller {
	@RequestMapping("/")
	public String Index() {
		return "Index";
	}
	
	@RequestMapping("/findroute")
	public String findRoute() {
		return "findroute";
	}
	
	@RequestMapping("/findroutetofaculty")
	public String findroutetofaculty() {
		return "findroutetofaculty";
	}
	
	@RequestMapping("/findroutetorestro")
	public String findroutetorestro() {
		return "findroutetorestro";
	}
	
	@RequestMapping("/directions")
	public String directions() {
		return "directions";
	}
}
