package pl.pawelczak.solaris.persistence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class GalleryTest {

	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void build_base() {
		
		//given
		String requiredName = "Required gallery Name";
		
		
		//execute
		Gallery gallery = Gallery.getBuilder(requiredName).build();
		
				
		//assert
		assertNull(gallery.getId());
		assertNull(gallery.getDescription());
		
		assertEquals(requiredName, gallery.getName());
		assertEquals(false, gallery.getVisible());
	}
	
	@Test
	public void build_allValues() {
	
		
		//given
		String galleryName = "Nice gallery name";
		String galleryDesc = "Interesting description.";
		Boolean galleryVisible = true;
		
		
		//execute
		Gallery gallery = Gallery.getBuilder(galleryName)
									.description(galleryDesc)
									.visible(galleryVisible)
									.build();
		
		
		//assert
		assertNull(gallery.getId());
		
		assertEquals(galleryName, gallery.getName());
		assertEquals(galleryDesc, gallery.getDescription());
		assertEquals(galleryVisible, gallery.getVisible());
		
	}

}
