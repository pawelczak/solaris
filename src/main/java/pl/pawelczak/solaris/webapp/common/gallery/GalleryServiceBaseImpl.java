package pl.pawelczak.solaris.webapp.common.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;

@Service
public class GalleryServiceBaseImpl implements GalleryServiceBase {

	
	private GalleryRepository galleryRepository;
	
	private PhotoRepository photoRepository;
	
	
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
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
}
