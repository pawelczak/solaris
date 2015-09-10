package pl.pawelczak.solaris.webapp.site.api.photo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class PhotoApiModelTest {

	
	
	//------------------------ TESTS --------------------------	
	

	@Test
	public void inner_gallery_builder() {
		
	
		//given
		Long galleryId = 12l;
		String galleryName = "Gr8 gallery name";
		
		
		//execute
		PhotoApiModel.Gallery gallery = PhotoApiModel.Gallery.getBuilder(galleryId)
																.name(galleryName)
																.build();
		
		//assert
		assertEquals(galleryId, gallery.getId());
		assertEquals(galleryName, gallery.getName());
		
	}
	
	@Test
	public void inner_photo_builder() {
		
		
		//given
		Long id = 57l;
		String title = "Photo title";
		String description = "Photo description";
		String imageSrc = "/file/src/1.jpg";
		
		
		//execute
		PhotoApiModel.Photo photo = PhotoApiModel.Photo.getBuilder(id)
													.title(title)
													.description(description)
													.imageSrc(imageSrc)
													.build();
		
		//assert
		assertEquals(id, photo.getId());
		assertEquals(title, photo.getTitle());
		assertEquals(description, photo.getDescription());
		assertEquals(imageSrc, photo.getImageSrc());
		
	}
	
	
	@Test
	public void PhotoApiModel_builder() {
		

		//given
		Long galleryId = 12l;
		String galleryName = "Gr8 gallery name";
		
		PhotoApiModel.Gallery gallery = PhotoApiModel.Gallery.getBuilder(galleryId)
				.name(galleryName)
				.build();
		
		Long id = 57l;
		String title = "Photo title";
		String description = "Photo description";
		String imageSrc = "/file/src/1.jpg";
		
		
		PhotoApiModel.Photo photo = PhotoApiModel.Photo.getBuilder(id)
													.title(title)
													.description(description)
													.imageSrc(imageSrc)
													.build();
		
		List<PhotoApiModel.Photo> photos = new ArrayList<PhotoApiModel.Photo>();
		photos.add(photo);
		
		
		//execute
		PhotoApiModel photoApiModel = PhotoApiModel.getBuilder(gallery, photos).build();
		
		
		//assert
		assertEquals(photoApiModel.getGallery(), gallery);
		assertEquals(photoApiModel.getPhotos(), photos);

	}

}
