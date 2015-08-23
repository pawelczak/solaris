package pl.pawelczak.solaris.webapp.admin.photo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoDeleteForm;
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
	
	@RequestMapping(value = "/admin/api/photo/edit", method=RequestMethod.POST)
	public String photoEdit(@ModelAttribute PhotoForm photoForm) {
		
		Photo editedPhoto = photoService.update(photoForm);
		
		return "redirect:/admin/api/photo/" + editedPhoto.getId();
	}
	
	@RequestMapping(value = "/admin/api/photo/editImage", method=RequestMethod.POST)
	public String editPhotoImage(@RequestParam(value="imageSrc", required=false) MultipartFile image,
			@RequestParam(value="photoId", required=true) Long photoId) {
		
		Photo editedPhoto = photoService.updateImage(photoId, image);
		
		return "redirect:/admin/api/photo/" + editedPhoto.getId();
	}
	
	@RequestMapping(value = "/admin/api/photo/delete", method=RequestMethod.POST)
	@ResponseBody
	public List<Photo> photoDelete(@ModelAttribute PhotoDeleteForm photoDeleteForm) {
		
		return photoService.delete(photoDeleteForm);
	}
	
	@RequestMapping("/admin/api/photo/delete/{id}")
	public String photoDelete(@PathVariable("id") Long photoId) {
		
		photoService.deleteById(photoId);
		
		return "redirect:/admin/api/photo/" + photoId;
	}
	
	
}
