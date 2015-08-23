package pl.pawelczak.solaris.webapp.admin.photo;

import static org.junit.Assert.assertEquals;
import pl.pawelczak.solaris.persistence.model.Photo;

public class PhotoTestUtils {

	
	//------------------------ LOGIC --------------------------
	
	public static void assertPhoto(Photo expected, Photo actual) {
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getImageSrc(), actual.getImageSrc());
	}
	
}
