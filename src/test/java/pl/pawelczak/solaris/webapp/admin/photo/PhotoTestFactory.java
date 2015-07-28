package pl.pawelczak.solaris.webapp.admin.photo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;

@Service
public class PhotoTestFactory {

	
	public final static String PHOTO_ONE_TITLE = "Photo title 1";
	public final static String PHOTO_TWO_TITLE = "Hill behind the backyard";
	
	
	//------------------------ LOGIC --------------------------
	
	public static List<Photo> createPhotoList() {
		
		List<Photo> photos = new ArrayList<Photo>();
		
		Photo photoOne = Photo.getBuilder().title(PHOTO_ONE_TITLE).build();
		Photo photoTwo = Photo.getBuilder().title(PHOTO_TWO_TITLE).build();
		
		photos.add(photoOne);
		photos.add(photoTwo);
		
		return photos;
	}
	
}