package pl.pawelczak.solaris.webapp.site.api.photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.webapp.site.api.photo.service.PhotoApiService;

@Controller("sitePhotoApiController")
public class PhotoApiController {

	
	@Autowired
	private PhotoApiService photoApiService;
	
	@Autowired
	private PhotoApiModelConverter photoApiModelConverter;
	
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/api/gallery/{galleryId}/photos")
	@ResponseBody
	public List<PhotoApiModel> photos(@PathVariable("galleryId") Long galleryId) {
		
		return photoApiModelConverter.convert(photoApiService.findAllByGalleryId(galleryId));
	}
}
