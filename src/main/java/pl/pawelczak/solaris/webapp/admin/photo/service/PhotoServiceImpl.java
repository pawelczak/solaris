package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoDeleteForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoFormConverter;
import pl.pawelczak.solaris.webapp.common.image.ImageUploadException;

@Service
public class PhotoServiceImpl implements PhotoService {

	
	private PhotoRepository photoRepository;
	
	private PhotoFormConverter photoFormConverter;
	
	private PhotoImageService photoImageService;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAll() {
		return photoRepository.findAll();
	}
	
	public Iterable<Photo> findAll(List<Long> ids) {
		
		return photoRepository.findAll(ids);
	}
	
	public Photo findOne(Long id) {
		return photoRepository.findOne(id);
	}
	
	
	public Photo add(PhotoForm photoForm) {
		return photoRepository.save(photoFormConverter.convert(photoForm));
	}
	
	public Photo update(PhotoForm photoForm) {
		
		Photo photo = photoRepository.findOne(photoForm.getId());
		
		photo.setGalleryId(photoForm.getGalleryId());
		photo.setTitle(photoForm.getTitle());
		photo.setDescription(photoForm.getDescription());
		photo.setImageSrc(photoForm.getImageSrc());
		
		return photoRepository.save(photo);
	}
	
	public Photo updateImage(Long photoId, MultipartFile image) throws ImageUploadException {
		Photo photo = photoRepository.findOne(photoId);
		
		photo.setImageSrc(photoImageService.save(photo.getId(), image));
		
		return photoRepository.save(photo);
	}
	
	public void deleteById(Long id) {
		
		Photo photo = photoRepository.findOne(id);
		photoRepository.delete(photo);
		photoImageService.delete(id);
	}
	
	public List<Photo> delete(PhotoDeleteForm photoDeleteForm) {
		
		List<Photo> photos = (List<Photo>) findAll(photoDeleteForm.getIds()); 
		
		photoRepository.delete(photos);
		
		for(Photo photo : photos) {
			photoImageService.delete(photo.getId());
		}
		
		return photos;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	@Autowired
	public void setPhotoFormConverter(PhotoFormConverter photoFormConverter) {
		this.photoFormConverter = photoFormConverter;
	}
	
	@Autowired
	public void setPhotoImageService(PhotoImageService photoImageService) {
		this.photoImageService = photoImageService;
	}
}
