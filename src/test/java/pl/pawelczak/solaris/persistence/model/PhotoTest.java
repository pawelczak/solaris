package pl.pawelczak.solaris.persistence.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoTest {

	
	//------------------------ TESTS --------------------------
	
	@Test
	public void builder() {
		
		
		//given
		Long galleryId = 12l;
		String title = "Photo title #1";
		
		
		//execute
		Photo photo = Photo.getBuilder(galleryId)
							.title(title)
							.build();
		
		
		//assert
		assertNull(photo.getId());
		assertEquals(galleryId, photo.getGalleryId());
		assertEquals(title, photo.getTitle());
		
	}

}
