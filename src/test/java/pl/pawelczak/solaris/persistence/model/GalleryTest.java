package pl.pawelczak.solaris.persistence.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

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
		
		Photo photo = Mockito.mock(Photo.class);
		List<Photo> photos = new ArrayList<Photo>();
		photos.add(photo);
		
		//execute
		Gallery gallery = Gallery.getBuilder(galleryName)
									.photoList(photos)
									.description(galleryDesc)
									.visible(galleryVisible)
									.build();
		
		
		//assert
		assertNull(gallery.getId());
		
		assertEquals(galleryName, gallery.getName());
		assertEquals(photos, gallery.getPhotoList());
		assertEquals(galleryDesc, gallery.getDescription());
		assertEquals(galleryVisible, gallery.getVisible());
		
	}

}
