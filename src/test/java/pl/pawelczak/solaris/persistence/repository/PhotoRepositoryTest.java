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
	private static final String PHOTO_TWO_TITLE = "Awesome titile for a photo";
	
	
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
	
	
	
	//------------------------ PRIVATE --------------------------
	
	private void initializeTests() {
		
		Photo photoOne = Photo.getBuilder().title(PHOTO_ONE_TITLE).build();
		Photo photoTwo = Photo.getBuilder().title(PHOTO_TWO_TITLE).build();
		
		expectedPhotoOne = photoRepository.save(photoOne);
		expectedPhotoTwo = photoRepository.save(photoTwo);
	}
	
	
	private void assertPhoto(Photo expected, Photo actual) {
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getTitle(), actual.getTitle());
	}

}
