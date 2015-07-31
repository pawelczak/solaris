package pl.pawelczak.solaris.webapp.admin.photo.form;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.pawelczak.solaris.persistence.model.Photo;

public class PhotoFormConverterTest {

	
	PhotoFormConverter photoFormConverter = new PhotoFormConverter();
	
	private final static String PHOTO_ONE_TITLE = "Large tree";
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void convert() {
		
		//given
		PhotoForm photoForm = new PhotoForm();
		photoForm.setTitle(PHOTO_ONE_TITLE);
		
		
		//execute
		Photo actualPhoto = photoFormConverter.convert(photoForm);
		
		
		//assert
		assertNotNull(actualPhoto);
		assertEquals(PHOTO_ONE_TITLE, actualPhoto.getTitle());
	}
	
}
