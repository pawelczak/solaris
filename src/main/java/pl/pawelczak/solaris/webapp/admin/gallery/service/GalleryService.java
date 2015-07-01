package pl.pawelczak.solaris.webapp.admin.gallery.service;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;



public interface GalleryService {


	public List<Gallery> findAll();
	
	public Gallery findOne(Long id);
	
	public Gallery add(GalleryForm galleryForm);
	
	public Gallery update(GalleryForm galleryForm);
	
	public void deleteById(Long id);
	
	public List<Gallery> delete(GalleryDeleteForm galleryDeleteForm);
}
