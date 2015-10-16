package pl.pawelczak.solaris.webapp.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class errorController {

		
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/404")
		public String error404() {
	return "error404";
	}
	
	@RequestMapping("/500")
		public String error500() {
	return "error500";
	}
}

