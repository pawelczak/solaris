package pl.pawelczak.solaris.webapp.site.api.photo;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhotoApiModelTest {

	
	
	//------------------------ TESTS --------------------------	
	
	@Test
	public void builder() {
		
		
		//given
		Long id = 57l;
		String title = "Photo title";
		String description = "Photo description";
		String imageSrc = "/file/src/1.jpg";
		
		
		//execute
		PhotoApiModel photoApiModel = PhotoApiModel.getBuilder(id)
													.title(title)
													.description(description)
													.imageSrc(imageSrc)
													.build();
		
		//assert
		assertEquals(id, photoApiModel.getId());
		assertEquals(title, photoApiModel.getTitle());
		assertEquals(description, photoApiModel.getDescription());
		assertEquals(imageSrc, photoApiModel.getImageSrc());
	}

}
