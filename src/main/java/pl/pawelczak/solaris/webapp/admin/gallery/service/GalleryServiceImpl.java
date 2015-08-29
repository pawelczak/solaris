package pl.pawelczak.solaris.webapp.admin.gallery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryFormConverter;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;

@Service
public class GalleryServiceImpl implements GalleryService {

	
	
	private GalleryRepository galleryRepository;
	
	private GalleryFormConverter galleryFormConverter; 
	
	private PhotoRepository photoRepository;
	
	private PhotoService photoService;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Gallery> findAll() {

		List<Gallery> gallery = galleryRepository.findAll();
		
		initializePhotos(gallery);
		
		return gallery;
	}
	
	public Iterable<Gallery> findAll(List<Long> ids) {
		
		List<Gallery> gallery = (List<Gallery>) galleryRepository.findAll(ids);
		
		initializePhotos(gallery);
		
		return gallery; 
	}
	
	public Gallery findOne(Long id) {
		
		Gallery gallery = galleryRepository.findOne(id);
		
		initializePhotos(gallery);
		
		return gallery;
	}
	
	
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
		
		List<Gallery> galleries = (List<Gallery>) findAll(galleryDeleteForm.getIds()); 
		
		for(Gallery gallery : galleries) {
			photoService.deleteByGalleryId(gallery.getId());
		}
		
		galleryRepository.delete(galleries);
		
		return galleries;
	}

	
	//------------------------ PRIVATE --------------------------
	
	private void initializePhotos(Gallery gallery) {
		gallery.setPhotoList(photoRepository.findAllByGalleryId(gallery.getId()));
	}
	
	private void initializePhotos(List<Gallery> galleries) {
		for(Gallery gallery : galleries) {
			initializePhotos(gallery);
		}
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
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	
}
