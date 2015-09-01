package pl.pawelczak.solaris.webapp.site.api.photo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;

@Service("sitePhotoApiModelConverter")
public class PhotoApiModelConverter {

	
	
	//------------------------ LOGIC --------------------------
	
	public List<PhotoApiModel> convert(List<Photo> photoList) {
		
		List<PhotoApiModel> photos = new ArrayList<PhotoApiModel>();
		
		for(Photo photo : photoList) {
			photos.add(convert(photo));
		} 
		
		return photos;
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private PhotoApiModel convert(Photo photo) {
		PhotoApiModel photoApiModel = PhotoApiModel.getBuilder(photo.getId())
													.title(photo.getTitle())
													.description(photo.getDescription())
													.imageSrc(photo.getImageSrc())
													.build();
		return photoApiModel;
	}
}
