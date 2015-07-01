package pl.pawelczak.solaris.webapp.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {

	
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/admin/")
	public String adminDashboard() {
		
		return "adminDashboard";
	}
}
