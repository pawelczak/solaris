package pl.pawelczak.solaris.webapp.common.gallery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;

@Service
public class GalleryServiceBaseImpl implements GalleryServiceBase {

	
	private GalleryRepository galleryRepositoryBase;
	
	private PhotoRepository photoRepositoryBase;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Gallery> findAll() {

		List<Gallery> gallery = galleryRepositoryBase.findAll();
		
		initializePhotos(gallery);
		
		return gallery;
	}
	
	public Iterable<Gallery> findAll(List<Long> ids) {
		
		List<Gallery> gallery = (List<Gallery>) galleryRepositoryBase.findAll(ids);
		
		initializePhotos(gallery);
		
		return gallery; 
	}
	
	public Gallery findOne(Long id) {
		
		Gallery gallery = galleryRepositoryBase.findOne(id);
		
		initializePhotos(gallery);
		
		return gallery;
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private void initializePhotos(Gallery gallery) {
		gallery.setPhotoList(photoRepositoryBase.findAllByGalleryId(gallery.getId()));
	}
	
	private void initializePhotos(List<Gallery> galleries) {
		for(Gallery gallery : galleries) {
			initializePhotos(gallery);
		}
	}
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setGalleryRepositoryBase(GalleryRepository galleryRepository) {
		this.galleryRepositoryBase = galleryRepository;
	}
	
	@Autowired
	public void setPhotoRepositoryBase(PhotoRepository photoRepository) {
		this.photoRepositoryBase = photoRepository;
	}
	
}
