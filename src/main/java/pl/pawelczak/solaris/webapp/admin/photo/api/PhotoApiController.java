package pl.pawelczak.solaris.webapp.admin.photo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;

@Controller
public class PhotoApiController {

	
	@Autowired
	private PhotoService photoService; 
	
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/admin/api/photo/list")
	@ResponseBody
	public List<Photo> photosList() {
		return photoService.findAll();
	}	
		
	@RequestMapping("/admin/api/photo/{id}")
	@ResponseBody
	public Photo singlePhoto(@PathVariable("id") Long photoId) {
		
		return photoService.findOne(photoId);
	}
	
}
