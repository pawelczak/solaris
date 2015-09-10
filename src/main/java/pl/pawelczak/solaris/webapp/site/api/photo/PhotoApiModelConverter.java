package pl.pawelczak.solaris.webapp.site.api.photo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;

@Service("sitePhotoApiModelConverter")
public class PhotoApiModelConverter {

	
	
	//------------------------ LOGIC --------------------------

	public PhotoApiModel.Gallery convert(Gallery gallery) {
		return PhotoApiModel.Gallery.getBuilder(gallery.getId())
									.name(gallery.getName())
									.build();
	}
	
	public List<PhotoApiModel.Photo> convert(List<Photo> photoList) {
		
		List<PhotoApiModel.Photo> photos = new ArrayList<PhotoApiModel.Photo>();
		
		for(Photo photo : photoList) {
			photos.add(convert(photo));
		} 
		
		return photos;
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private PhotoApiModel.Photo convert(Photo photo) {
		PhotoApiModel.Photo photoApiModel = PhotoApiModel.Photo.getBuilder(photo.getId())
													.title(photo.getTitle())
													.description(photo.getDescription())
													.imageSrc(photo.getImageSrc())
													.build();
		return photoApiModel;
	}
}
