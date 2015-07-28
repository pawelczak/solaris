package pl.pawelczak.solaris.webapp.admin.photo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.admin.photo.PhotoTestFactory;
import pl.pawelczak.solaris.webapp.admin.photo.PhotoTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {


	
	private PhotoServiceImpl photoService = new PhotoServiceImpl();
	
	@Mock
	private PhotoRepository photoRepository;
	
	private List<Photo> expectedPhotoList = PhotoTestFactory.createPhotoList(); 
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void findAll() {
		
		
		//given
		when(photoRepository.findAll()).thenReturn(expectedPhotoList);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		List<Photo> actualPhotoList = photoService.findAll();
		
		
		//assert
		assertEquals(actualPhotoList.size(), expectedPhotoList.size());
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), actualPhotoList.get(0));
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(1), actualPhotoList.get(1));
		
		verify(photoRepository, times(1)).findAll();
        verifyNoMoreInteractions(photoRepository);
	}
	
	
}
