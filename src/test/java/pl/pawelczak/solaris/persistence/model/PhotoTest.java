package pl.pawelczak.solaris.persistence.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoTest {

	
	//------------------------ TESTS --------------------------
	
	@Test
	public void builder() {
		
		
		//given
		String title = "Photo title #1";
		
		
		//execute
		Photo photo = Photo.getBuilder()
							.title(title)
							.build();
		
		
		//assert
		assertNull(photo.getId());
		assertEquals(photo.getTitle(), title);
		
	}

}
