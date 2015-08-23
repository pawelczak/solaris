package pl.pawelczak.solaris.webapp.admin.photo.form;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.pawelczak.solaris.persistence.model.Photo;

public class PhotoFormConverterTest {

	
	PhotoFormConverter photoFormConverter = new PhotoFormConverter();
	
	private final static String PHOTO_ONE_TITLE = "Large tree";
	private final static String PHOTO_ONE_DESCRIPTION = "Nice photo";
	private final static String PHOTO_ONE_IMG_SRC = "folder/image.jpg";
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void convert() {
		
		//given
		PhotoForm photoForm = new PhotoForm();
		photoForm.setTitle(PHOTO_ONE_TITLE);
		photoForm.setDescription(PHOTO_ONE_DESCRIPTION);
		photoForm.setImageSrc(PHOTO_ONE_IMG_SRC);
		
		
		//execute
		Photo actualPhoto = photoFormConverter.convert(photoForm);
		
		
		//assert
		assertNotNull(actualPhoto);
		assertEquals(PHOTO_ONE_TITLE, actualPhoto.getTitle());
		assertEquals(PHOTO_ONE_DESCRIPTION, actualPhoto.getDescription());
		assertEquals(PHOTO_ONE_IMG_SRC, actualPhoto.getImageSrc());
	}
	
}
