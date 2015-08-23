package pl.pawelczak.solaris.webapp.admin.photo.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PhotoApiModelTest {


	private static final String GALLERY_NAME = "Gallery name";
	
	private static final String PHOTO_TITLE = "Faboulus title";
	private static final String PHOTO_DESCRIPTION = "Nice photo";
	private static final String PHOTO_IMG_SRC = "folder/image.jpg";

    //------------------------ TESTS --------------------------

	
	@Test
	public void basicBuild() {
		
		
		//given
		PhotoApiModel.Gallery gallery = new PhotoApiModel.Gallery();
		gallery.setName(GALLERY_NAME);
		
		PhotoApiModel photo = PhotoApiModel.getBuilder(gallery)
											.title(PHOTO_TITLE)
											.description(PHOTO_DESCRIPTION)
											.imageSrc(PHOTO_IMG_SRC)
											.build();
		
		
		//assert
		assertEquals(gallery, photo.getGallery());
		assertEquals(PHOTO_TITLE, photo.getTitle());
		assertEquals(PHOTO_DESCRIPTION, photo.getDescription());
		assertEquals(PHOTO_IMG_SRC, photo.getImageSrc());
	}
	
	@Test
	public void builder_id_gallery() {

		
		//given
		Long photoId = 1l;
		PhotoApiModel.Gallery gallery = new PhotoApiModel.Gallery();
		gallery.setName(GALLERY_NAME);
		
		PhotoApiModel photo = PhotoApiModel.getBuilder(photoId, gallery)
											.title(PHOTO_TITLE)
											.description(PHOTO_DESCRIPTION)
											.imageSrc(PHOTO_IMG_SRC)
											.build();
		
		
		//assert
		assertEquals(photoId, photo.getId());
		assertEquals(gallery, photo.getGallery());
		assertEquals(PHOTO_TITLE, photo.getTitle());
		assertEquals(PHOTO_DESCRIPTION, photo.getDescription());
		assertEquals(PHOTO_IMG_SRC, photo.getImageSrc());
	}
	
	
}
