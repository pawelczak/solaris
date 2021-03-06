package pl.pawelczak.solaris.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.pawelczak.solaris.persistence.PersistenceTestSupport;
import pl.pawelczak.solaris.persistence.model.Photo;

/**
 * 
 * Integration tests for PhotoRepository
 * 
 * @author Łukasz Pawełczak
 *
 */
public class PhotoRepositoryTest extends PersistenceTestSupport {

	
	@Autowired
	private PhotoRepository photoRepository;
	

	private Photo expectedPhotoOne;
	private Photo expectedPhotoTwo;
	
	
	private static final String PHOTO_ONE_TITLE = "First photo titiel";
	private static final String PHOTO_ONE_DESC = "First photo desc";
	private static final String PHOTO_ONE_IMG_SRC = "First photo image source";
	private static final Long PHOTO_ONE_GALLERY_ID = 21l;
	
	private static final String PHOTO_TWO_TITLE = "Awesome titile for a photo";
	private static final String PHOTO_TWO_DESC = "Second photo desc";
	private static final String PHOTO_TWO_IMG_SRC = "Second photo img src";
	private static final Long PHOTO_TWO_GALLERY_ID = 27l;
	
	
	//------------------------ BEFORE --------------------------
	
	@Before
	public void before() {
		super.before();
		initializeTests();
	}
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void findAll() {
		
		//execute
		List<Photo> photoList = photoRepository.findAll();
		
		//assert
		assertEquals(2, photoList.size());
		assertPhoto(expectedPhotoOne, photoList.get(0));
		assertPhoto(expectedPhotoTwo, photoList.get(1));
	}
	
	@Test
	public void findAllByGalleryId() {
		
		//given
		Long galleryId = 27l;
		
		//execute
		List<Photo> photoList = photoRepository.findAllByGalleryId(galleryId);
		
		//assert
		assertEquals(1, photoList.size());
		assertPhoto(expectedPhotoTwo, photoList.get(0));
	}
	
	/*
	@Test
	public void delete() {
		
		//before delete
		List<Photo> photoList = photoRepository.findAll();
		
		assertEquals(2, photoList.size());
		assertPhoto(expectedPhotoOne, photoList.get(0));
		assertPhoto(expectedPhotoTwo, photoList.get(1));
		
		
		//delete
		Photo photo = photoRepository.findOne(expectedPhotoOne.getId());
		
		photoRepository.delete(photo);
		
		photoList = photoRepository.findAll();
		
		assertEquals(1, photoList.size());
		assertPhoto(expectedPhotoTwo, photoList.get(0));
	}
	
	
	@Test
	public void deleteByGalleryId() {
		
		//given
		Long galleryId = PHOTO_ONE_GALLERY_ID;
		
		
		//execute
		photoRepository.deleteByGalleryId(galleryId);
		List<Photo> photoList = photoRepository.findAll();
		
		//assert
		assertEquals(1, photoList.size());
		assertPhoto(expectedPhotoTwo, photoList.get(0));
		
	}*/
	
	
	//------------------------ PRIVATE --------------------------
	
	private void initializeTests() {
		
		Photo photoOne = Photo.getBuilder(PHOTO_ONE_GALLERY_ID)
								.title(PHOTO_ONE_TITLE)
								.description(PHOTO_ONE_DESC)
								.imageSrc(PHOTO_ONE_IMG_SRC)
								.build();
		Photo photoTwo = Photo.getBuilder(PHOTO_TWO_GALLERY_ID)
								.title(PHOTO_TWO_TITLE)
								.description(PHOTO_TWO_DESC)
								.imageSrc(PHOTO_TWO_IMG_SRC)
								.build();
		
		expectedPhotoOne = photoRepository.save(photoOne);
		expectedPhotoTwo = photoRepository.save(photoTwo);
	}
	
	
	private void assertPhoto(Photo expected, Photo actual) {
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTitle(), actual.getTitle());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getImageSrc(), actual.getImageSrc());
	}

}
