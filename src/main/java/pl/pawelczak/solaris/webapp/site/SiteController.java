package pl.pawelczak.solaris.webapp.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {


	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/")
	public String home() {
		return "siteHome";
	}

	@RequestMapping("/gallery")
	public String gallery() {
		return "siteGallery";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "siteContact";
	}
}

