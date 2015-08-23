package pl.pawelczak.solaris.webapp.admin.photo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;

@Service
public class PhotoTestFactory {

	
	public final static Long PHOTO_ONE_GALLERY_ID = 23l;
	public final static String PHOTO_ONE_TITLE = "Photo title 1";
	public final static String PHOTO_ONE_DESC = "First photo desc";
	public final static String PHOTO_ONE_IMG_SRC = "First photo image source";
	
	public final static Long PHOTO_TWO_GALLERY_ID = 25l;
	public final static String PHOTO_TWO_TITLE = "Hill behind the backyard";
	public final static String PHOTO_TWO_DESC = "Second photo desc";
	public final static String PHOTO_TWO_IMG_SRC = "Second photo img src";
	
	
	//------------------------ LOGIC --------------------------
	
	public static List<Photo> createPhotoList() {
		
		List<Photo> photos = new ArrayList<Photo>();
		
		Photo photoOne = Photo.getBuilder(PHOTO_ONE_GALLERY_ID)
								.title(PHOTO_ONE_TITLE)
								.description(PHOTO_ONE_DESC)
								.imageSrc(PHOTO_ONE_IMG_SRC)
								.build();
		Photo photoTwo = Photo.getBuilder(PHOTO_TWO_GALLERY_ID)
								.title(PHOTO_TWO_TITLE)
								.description(PHOTO_TWO_DESC)
								.imageSrc(PHOTO_TWO_IMG_SRC)
								.build();
		
		photos.add(photoOne);
		photos.add(photoTwo);
		
		return photos;
	}
	
}
