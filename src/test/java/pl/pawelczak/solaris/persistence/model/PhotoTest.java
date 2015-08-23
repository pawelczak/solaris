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
		String description = "Fabulous view";
		String imageSrc = "folder/image.jpg";
		
		
		//execute
		Photo photo = Photo.getBuilder(galleryId)
							.title(title)
							.description(description)
							.imageSrc(imageSrc)
							.build();
		
		
		//assert
		assertNull(photo.getId());
		assertEquals(galleryId, photo.getGalleryId());
		assertEquals(title, photo.getTitle());
		assertEquals(description, photo.getDescription());
		assertEquals(imageSrc, photo.getImageSrc());
		
	}

}
