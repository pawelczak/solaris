package pl.pawelczak.solaris.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.pawelczak.solaris.persistence.PersistenceTestSupport;
import pl.pawelczak.solaris.persistence.model.Gallery;


/**
 * 
 * Integration tests for GalleryRepository
 * 
 * @author Łukasz Pawełczak
 *
 */
public class GalleryRepositoryTest extends PersistenceTestSupport {


	
	@Autowired
	private GalleryRepository galleryRepository;
	

	private Gallery expectedGalleryOne;
	private Gallery expectedGalleryTwo;
	
	private static final String GALLERY_ONE_NAME = "Tatry Polskie";
	private static final String GALLERY_TWO_NAME = "Tatry Slowackie";
	
	
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
		List<Gallery> galleryList = galleryRepository.findAll();
		
		//assert
		assertEquals(2, galleryList.size());
		assertGallery(expectedGalleryOne, galleryList.get(0));
		assertGallery(expectedGalleryTwo, galleryList.get(1));
	}
	
	
	@Test
	public void findAll_list_ids() {
		
		
		//given
		List<Long> ids = new ArrayList<Long>();
		ids.add(expectedGalleryOne.getId());
		ids.add(expectedGalleryTwo.getId());
		
		//execute
		List<Gallery> galleryList = (List<Gallery>) galleryRepository.findAll(ids);
	
		
		//assert
		assertEquals(2, galleryList.size());
		assertGallery(expectedGalleryOne, galleryList.get(0));
		assertGallery(expectedGalleryTwo, galleryList.get(1));
	}
	
	
	
	//------------------------ PRIVATE --------------------------
	
	private void initializeTests() {
		
		Gallery galleryOne = Gallery.getBuilder(GALLERY_ONE_NAME).build();
		Gallery galleryTwo = Gallery.getBuilder(GALLERY_TWO_NAME).build();
		
		expectedGalleryOne = galleryRepository.save(galleryOne);
		expectedGalleryTwo = galleryRepository.save(galleryTwo);
	}
	
	
	private void assertGallery(Gallery expected, Gallery actual) {
		
		assertEquals(expected.getId(), actual.getId());
		assertEquals(expected.getName(), actual.getName());
	}
	
}


