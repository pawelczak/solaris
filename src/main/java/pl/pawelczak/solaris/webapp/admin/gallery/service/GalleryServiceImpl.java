package pl.pawelczak.solaris.webapp.admin.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryFormConverter;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;
import pl.pawelczak.solaris.webapp.common.gallery.GalleryServiceBaseImpl;

@Service
public class GalleryServiceImpl extends GalleryServiceBaseImpl implements GalleryService {

	
	private GalleryRepository galleryRepository;
	
	private GalleryFormConverter galleryFormConverter; 
	
	private PhotoService photoService;
	
	
	//------------------------ LOGIC --------------------------

	public Gallery add(GalleryForm galleryForm) {
		
		Gallery gallery = galleryFormConverter.convert(galleryForm);
		
		return galleryRepository.save(gallery);
	}
	
	public Gallery update(GalleryForm galleryForm) {
		
		Gallery gallery = galleryRepository.findOne(galleryForm.getId());
		
		gallery.setName(galleryForm.getName());
		gallery.setDescription(galleryForm.getDescription());
		gallery.setVisible(galleryForm.getVisible());
		
		return galleryRepository.save(gallery);
	}
	
	public void deleteById(Long id) {
		
		Gallery gallery = galleryRepository.findOne(id);
		
		photoService.deleteByGalleryId(gallery.getId());
		
		galleryRepository.delete(gallery);
	}
	
	public List<Gallery> delete(GalleryDeleteForm galleryDeleteForm) {
		
		List<Gallery> galleries = (List<Gallery>) galleryRepository.findAll(galleryDeleteForm.getIds()); 
		
		for(Gallery gallery : galleries) {
			photoService.deleteByGalleryId(gallery.getId());
		}
		
		galleryRepository.delete(galleries);
		
		return galleries;
	}

	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setGalleryRepository(GalleryRepository galleryRepository) {
		this.galleryRepository = galleryRepository;
	}
	
	@Autowired
	public void setGalleryFormConverter(GalleryFormConverter galleryFormConverter) {
		this.galleryFormConverter = galleryFormConverter;
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	
	
	
}
