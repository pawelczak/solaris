package pl.pawelczak.solaris.webapp.site.api.gallery;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;

public class GalleryApiModelConverterTest {

	
	private static final Long GALLERY_ONE_ID = 84l;
	private static final String GALLERY_ONE_NAME = "Gallery #1 name";
	private static final String GALLERY_ONE_DESCRIPTION = "Awesome view";
	private static final String GALLERY_ONE_FEATURED_IMAGE_SRC = "/file/path/1.jpeg";
	
	private static final Long GALLERY_TWO_ID = 85l;
	private static final String GALLERY_TWO_NAME = "Gallery #2 name";
	private static final String GALLERY_TWO_DESCRIPTION = "Not so awesome view";
	private static final String GALLERY_TWO_FEATURED_IMAGE_SRC = "/file/path/2.jpeg";
	
	private static final Long GALLERY_THREE_ID = 86l;
	private static final String GALLERY_THREE_NAME = "Gallery #3 name";
	private static final String GALLERY_THREE_DESCRIPTION = "Eye of the neither";
	private static final String GALLERY_THREE_FEATURED_IMAGE_SRC = "/file/path/3.jpeg";
	
	private GalleryApiModelConverter galleryApiModelConverter = new GalleryApiModelConverter();
	
	
	//------------------------ TEST --------------------------
	
	@Test
	public void convert_empty_list() {
		
		//given
		List<Gallery> galleries = new ArrayList<Gallery>();
		
		
		//execute
		List<GalleryApiModel> actualGalleries = galleryApiModelConverter.convert(galleries);
		
		
		//assert
		assertEquals(galleries.size(), actualGalleries.size());
	}
	
	@Test
	public void convert_list() {
		
		//given
		List<Gallery> galleries = createGalleries();
		
		
		//execute
		List<GalleryApiModel> actualGalleries = galleryApiModelConverter.convert(galleries);
		
		
		//assert
		assertEquals(galleries.size(), actualGalleries.size());
		
		assertEquals(GALLERY_ONE_ID, actualGalleries.get(0).getId());
		assertEquals(GALLERY_ONE_NAME, actualGalleries.get(0).getName());
		assertEquals(GALLERY_ONE_DESCRIPTION, actualGalleries.get(0).getDescription());
		assertEquals(GALLERY_ONE_FEATURED_IMAGE_SRC, actualGalleries.get(0).getFeaturedImageSrc());
		
		assertEquals(GALLERY_TWO_ID, actualGalleries.get(1).getId());
		assertEquals(GALLERY_TWO_NAME, actualGalleries.get(1).getName());
		assertEquals(GALLERY_TWO_DESCRIPTION, actualGalleries.get(1).getDescription());
		assertEquals(GALLERY_TWO_FEATURED_IMAGE_SRC, actualGalleries.get(1).getFeaturedImageSrc());
		
		assertEquals(GALLERY_THREE_ID, actualGalleries.get(2).getId());
		assertEquals(GALLERY_THREE_NAME, actualGalleries.get(2).getName());
		assertEquals(GALLERY_THREE_DESCRIPTION, actualGalleries.get(2).getDescription());
		assertEquals("", actualGalleries.get(2).getFeaturedImageSrc());
	}

	
	//------------------------ PRIVATE --------------------------
	
	private List<Gallery> createGalleries() {
		
		
		Photo photoOne = Photo.getBuilder(GALLERY_ONE_ID).imageSrc(GALLERY_ONE_FEATURED_IMAGE_SRC).build();
		Photo photoTwo = Photo.getBuilder(GALLERY_ONE_ID).imageSrc(GALLERY_TWO_FEATURED_IMAGE_SRC).build();
		List<Photo> photoList = new ArrayList<Photo>();
		photoList.add(photoOne);
		photoList.add(photoTwo);
		
		List<Photo> reversedPhotoList = new ArrayList<Photo>();
		reversedPhotoList.add(photoTwo);
		reversedPhotoList.add(photoOne);
		
		
		Gallery galleryOne = Gallery.getBuilder(GALLERY_ONE_NAME)
									.description(GALLERY_ONE_DESCRIPTION)
									.photoList(photoList)
									.build();
		
		Whitebox.setInternalState(galleryOne, "id", GALLERY_ONE_ID);
		
		Gallery galleryTwo = Gallery.getBuilder(GALLERY_TWO_NAME)
									.description(GALLERY_TWO_DESCRIPTION)
									.photoList(reversedPhotoList)
									.build();

		Whitebox.setInternalState(galleryTwo, "id", GALLERY_TWO_ID);
		
		Gallery galleryThree = Gallery.getBuilder(GALLERY_THREE_NAME)
										.description(GALLERY_THREE_DESCRIPTION)
										.build();

		Whitebox.setInternalState(galleryThree, "id", GALLERY_THREE_ID);
		
		List<Gallery> galleries = new ArrayList<Gallery>();
		
		galleries.add(galleryOne);
		galleries.add(galleryTwo);
		galleries.add(galleryThree);
		
		return galleries;
	}
	
}
