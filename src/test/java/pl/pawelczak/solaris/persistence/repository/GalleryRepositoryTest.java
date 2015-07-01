package pl.pawelczak.solaris.persistence.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.pawelczak.solaris.persistence.PersistenceTestSupport;
import pl.pawelczak.solaris.persistence.model.Gallery;

public class GalleryRepositoryTest extends PersistenceTestSupport {

	
	
	
	@Autowired
	private GalleryRepository galleryRepository;
	
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
		List<Gallery> list = galleryRepository.findAll();
		
		//assert
		assertEquals(2, list.size());
		assertEquals(GALLERY_ONE_NAME, list.get(0).getName());
		assertEquals(GALLERY_TWO_NAME, list.get(1).getName());
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private void initializeTests() {
		
		Gallery galleryOne = Gallery.getBuilder(GALLERY_ONE_NAME).build();
		Gallery galleryTwo = Gallery.getBuilder(GALLERY_TWO_NAME).build();
		
		galleryRepository.save(galleryOne);
		galleryRepository.save(galleryTwo);
	}
	
	
}

