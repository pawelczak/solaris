package pl.pawelczak.solaris.webapp.site.api.gallery;

import static org.junit.Assert.*;

import org.junit.Test;

public class GalleryApiModelTest {

	
	//------------------------ TEST --------------------------
	
	@Test
	public void builder() {
		
		
		//given
		Long id = 2l;
		String name = "Gallery Name";
		String description = "Random string . #?";
		String featuredImageSrc = "file/pat/to/image.png";
		
		//execute
		GalleryApiModel galleryApiModel = GalleryApiModel.getBuilder(id)
														.name(name)
														.description(description)
														.featuredImageSrc(featuredImageSrc)
														.build();
		
		//assert
		assertEquals(id, galleryApiModel.getId());
		assertEquals(name, galleryApiModel.getName());
		assertEquals(description, galleryApiModel.getDescription());
		assertEquals(featuredImageSrc, galleryApiModel.getFeaturedImageSrc());
	}

}
