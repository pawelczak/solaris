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
import pl.pawelczak.solaris.webapp.common.photo.PhotoServiceBaseImpl;

@Service
public class PhotoServiceImpl extends PhotoServiceBaseImpl implements PhotoService {

	
	private PhotoRepository photoRepository;
	
	private PhotoFormConverter photoFormConverter;
	
	private PhotoImageService photoImageService;
	
	
	//------------------------ LOGIC --------------------------
	
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
		
		List<Photo> photos = (List<Photo>) photoRepository.findAll(photoDeleteForm.getIds()); 
		
		deletePhotos(photos);
		
		return photos;
	}
	
	public void deleteByGalleryId(Long galleryId) {
		
		List<Photo> photos = photoRepository.findAllByGalleryId(galleryId);
		
		deletePhotos(photos);
	}

	
	//------------------------ PRIVATE --------------------------
	
	private void deletePhotos(List<Photo> photoList) {
		
		for(Photo photo : photoList) {
			photoImageService.delete(photo.getId());
		}
		
		photoRepository.delete(photoList);
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
