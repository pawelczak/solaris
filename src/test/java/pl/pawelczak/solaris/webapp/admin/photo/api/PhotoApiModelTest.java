package pl.pawelczak.solaris.webapp.admin.photo.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PhotoApiModelTest {



    //------------------------ TESTS --------------------------

	
	@Test
	public void basicBuild() {
		
		
		//given
		PhotoApiModel.Gallery gallery = new PhotoApiModel.Gallery();
		gallery.setName("gallery name");
		
		PhotoApiModel photo = PhotoApiModel.getBuilder(gallery).build();
		
		
		//assert
		assertEquals(gallery, photo.getGallery());
	}
	
	@Test
	public void builder_id_gallery() {

		
		//given
		Long photoId = 1l;
		PhotoApiModel.Gallery gallery = new PhotoApiModel.Gallery();
		gallery.setName("gallery name");
		
		PhotoApiModel photo = PhotoApiModel.getBuilder(photoId, gallery).build();
		
		
		//assert
		assertEquals(photoId, photo.getId());
		assertEquals(gallery, photo.getGallery());
	}
	
	
}
