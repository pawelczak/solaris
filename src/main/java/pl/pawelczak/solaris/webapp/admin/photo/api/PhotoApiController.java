package pl.pawelczak.solaris.webapp.admin.photo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;

@Controller
public class PhotoApiController {

	
	@Autowired
	private PhotoService photoService; 
	
	@Autowired
	private PhotoApiModelConverter photoApiModelConverter;
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/admin/api/photo/list")
	@ResponseBody
	public List<PhotoApiModel> photosList() {
		return photoApiModelConverter.convert(photoService.findAll());
	}	
		
	@RequestMapping("/admin/api/photo/{id}")
	@ResponseBody
	public PhotoApiModel singlePhoto(@PathVariable("id") Long photoId) {
		
		return photoApiModelConverter.convert(photoService.findOne(photoId));
	}
	
	@RequestMapping(value = "/admin/api/photo/add", method=RequestMethod.POST)
	public String singlePhotoAdd(@ModelAttribute PhotoForm photoForm) {
		
		Photo addedPhoto = photoService.add(photoForm);
		
		return "redirect:/admin/api/photo/" + addedPhoto.getId();
	}
}
