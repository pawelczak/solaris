package pl.pawelczak.solaris.webapp.admin.photo.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.gallery.service.GalleryService;

@RunWith(MockitoJUnitRunner.class)
public class PhotoApiModelConverterTest {


	private PhotoApiModelConverter photoApiModelConverter = new PhotoApiModelConverter();
	
	@Mock
	private GalleryService galleryService;
	
	
    //------------------------ TESTS --------------------------
	
	@Test
	public void convert_from_photo() {
		
		
		//given
		Long galleryId = 15l;
		String galleryName = "Nice gallery name";
		String title = "Photo Title";
		String description = "Photo description";
		String imageSrc = "folder/image.png";
		
		Photo photo = Photo.getBuilder(galleryId)
							.title(title)
							.description(description)
							.imageSrc(imageSrc)
							.build();
		Gallery gallery = Gallery.getBuilder(galleryName).build();
		Whitebox.setInternalState(gallery, "id", galleryId);
		
		when(galleryService.findOne(galleryId)).thenReturn(gallery);
		photoApiModelConverter.setGalleryService(galleryService);
		
		
		//execute
		PhotoApiModel actualPhoto = photoApiModelConverter.convert(photo);
		
		
		//assert
		assertEquals(photo.getGalleryId(), actualPhoto.getGallery().getId());
		assertEquals(photo.getTitle(), actualPhoto.getTitle());
		assertEquals(photo.getDescription(), actualPhoto.getDescription());
		assertEquals(photo.getImageSrc(), actualPhoto.getImageSrc());
	}

}
