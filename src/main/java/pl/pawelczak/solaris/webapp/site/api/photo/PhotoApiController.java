package pl.pawelczak.solaris.webapp.site.api.photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.site.api.photo.service.GalleryApiService;
import pl.pawelczak.solaris.webapp.site.api.photo.service.PhotoApiService;

@Controller("sitePhotoApiController")
public class PhotoApiController {

	
	@Autowired
	private PhotoApiService photoApiService;
	
	@Autowired
	private PhotoApiModelConverter photoApiModelConverter;
	
	@Autowired
	private GalleryApiService galleryApiService;
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/api/gallery/{galleryId}/photos")
	@ResponseBody
	public PhotoApiModel photoApiModelView(@PathVariable("galleryId") Long galleryId) {
		
		Gallery gallery = galleryApiService.findOne(galleryId);
		List<Photo> photos = photoApiService.findAllByGalleryId(galleryId);
		
		return PhotoApiModel.getBuilder(photoApiModelConverter.convert(gallery), photoApiModelConverter.convert(photos)).build();
	}
}
