package pl.pawelczak.solaris.webapp.admin.gallery.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;
import pl.pawelczak.solaris.webapp.admin.gallery.service.GalleryService;



@Controller
public class GalleryApiController {

	
	@Autowired
	private GalleryService galleryService;
	
	
	
	//------------------------ LOGIC --------------------------
	
	@RequestMapping("/admin/api/gallery/list")
	@ResponseBody
	public List<Gallery> galleriesList() {
		
		return galleryService.findAll();
	}
	
	@RequestMapping("/admin/api/gallery/{id}")
	@ResponseBody
	public Gallery galleryView(@PathVariable("id") Long galleryId) {
		
		return galleryService.findOne(galleryId);
	}
	
	@RequestMapping(value = "/admin/api/gallery/add", method=RequestMethod.POST)
	public String galleryAdd(@ModelAttribute GalleryForm galleryForm) {
		
		Gallery addedGallery = galleryService.add(galleryForm);
		
		return "redirect:/admin/api/gallery/" + addedGallery.getId();
	}
	
	@RequestMapping(value = "/admin/api/gallery/edit", method=RequestMethod.POST)
	public String galleryEdit(@ModelAttribute GalleryForm galleryForm) {
		
		Gallery editedGallery = galleryService.update(galleryForm);
		
		return "redirect:/admin/api/gallery/" + editedGallery.getId();
	}
	
	@RequestMapping(value = "/admin/api/gallery/delete", method=RequestMethod.POST)
	@ResponseBody
	public List<Gallery> deleteGalleries(@ModelAttribute GalleryDeleteForm galleryDeleteForm) {
		
		return galleryService.delete(galleryDeleteForm);
	}
	
	@RequestMapping("/admin/api/gallery/delete/{id}")
	public String deleteGallery(@PathVariable("id") Long galleryId) {
		
		galleryService.deleteById(galleryId);
		
		return "redirect:/admin/api/gallery/" + galleryId;
	}
	
}


