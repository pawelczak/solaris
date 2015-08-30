package pl.pawelczak.solaris.webapp.site.api.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.webapp.site.api.gallery.service.GalleryApiService;

@Controller("siteGalleryApiController")
public class GalleryApiController {

	
	@Autowired
	private GalleryApiService galleryApiService;
	
	@Autowired
	private GalleryApiModelConverter galleryApiModelConverter;
	
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/api/galleries")
	@ResponseBody
	public List<GalleryApiModel> galleries() {
		
		return galleryApiModelConverter.convert(galleryApiService.findAll());
	}
}
