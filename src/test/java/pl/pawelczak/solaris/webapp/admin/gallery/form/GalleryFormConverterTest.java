package pl.pawelczak.solaris.webapp.admin.gallery.form;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.pawelczak.solaris.persistence.model.Gallery;

public class GalleryFormConverterTest {

	
	private GalleryFormConverter galleryFormConverter = new GalleryFormConverter();
	

	private static final String GALLERY_NAME = "Nazwa galerii";
	private static final String GALLERY_DESC = "Opis galerii";
	private static final Boolean GALLERY_VISIBLE = false;

	//------------------------ TESTS --------------------------
	
	@Test
	public void convert_fromGalleryForm() {
		
		
		//given
		GalleryForm galleryForm = new GalleryForm();
		
		galleryForm.setName(GALLERY_NAME);
		galleryForm.setDescription(GALLERY_DESC);
		galleryForm.setVisible(GALLERY_VISIBLE);
		
		
		//execute
		Gallery actualGallery = galleryFormConverter.convert(galleryForm);
		
		
		//assert
		assertEquals(GALLERY_NAME, actualGallery.getName());
		assertEquals(GALLERY_DESC, actualGallery.getDescription());
		assertEquals(GALLERY_VISIBLE, actualGallery.getVisible());
		
	}

}
