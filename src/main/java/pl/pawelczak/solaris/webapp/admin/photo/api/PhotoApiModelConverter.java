package pl.pawelczak.solaris.webapp.admin.photo.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.gallery.service.GalleryService;


@Service
public class PhotoApiModelConverter {

	
	
	private GalleryService galleryService;
	
	
    //------------------------ LOGIC --------------------------
	
	public PhotoApiModel convert(Photo photo) {
		
		PhotoApiModel.Gallery gallery = convert(galleryService.findOne(photo.getGalleryId()));
		
		return PhotoApiModel.getBuilder(photo.getId(), gallery)
								.title(photo.getTitle())
								.description(photo.getDescription())
								.imageSrc(photo.getImageSrc())
								.build();
	}
	
	public List<PhotoApiModel> convert(List<Photo> photos) {
		
		List<PhotoApiModel> convertedPhotos = new ArrayList<PhotoApiModel>();
		
		for(Photo photo : photos) {
			convertedPhotos.add(convert(photo));
		}
		
		return convertedPhotos;
	}
	
	public PhotoApiModel.Gallery convert(Gallery gallery) {
		
		PhotoApiModel.Gallery photoGallery = new PhotoApiModel.Gallery();
		photoGallery.setId(gallery.getId());
		photoGallery.setName(gallery.getName());
		
		return photoGallery;
	}
	
	
    //------------------------ SETTERS --------------------------
	
	@Autowired
	public void setGalleryService(GalleryService galleryService) {
		this.galleryService = galleryService;
	}
	
}

