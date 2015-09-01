package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoDeleteForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.common.photo.PhotoServiceBase;

public interface PhotoService extends PhotoServiceBase {

	
	public List<Photo> findAll();
	
	public Photo findOne(Long id);
	
	public Photo add(PhotoForm photoForm);
	
	public Photo update(PhotoForm photoForm);
	
	public Photo updateImage(Long photoId, MultipartFile file);
	
	public void deleteById(Long id);
	
	public List<Photo> delete(PhotoDeleteForm photoDeleteForm);
	
	public void deleteByGalleryId(Long galleryId);
}
