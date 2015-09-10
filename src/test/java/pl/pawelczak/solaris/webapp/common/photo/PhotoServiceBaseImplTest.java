package pl.pawelczak.solaris.webapp.common.photo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceBaseImplTest {

	
	@Mock
	private PhotoRepository photoRepository;
	
	private PhotoServiceBaseImpl photoService = new PhotoServiceBaseImpl();
	
	private List<Photo> expectedPhotoList = PhotoTestFactory.createPhotoList(); 
	
	
	//------------------------ TESTS --------------------------	
	
	@Test
	public void findAll() {
		
		
		//given
		when(photoRepository.findAll()).thenReturn(expectedPhotoList);
		photoService.setPhotoRepositoryBase(photoRepository);
		
		
		//execute
		List<Photo> actualPhotoList = photoService.findAll();
		
		
		//assert
		assertEquals(actualPhotoList.size(), expectedPhotoList.size());
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), actualPhotoList.get(0));
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(1), actualPhotoList.get(1));
		
		verify(photoRepository, times(1)).findAll();
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void findAll_byIdsList() {
		
		//given
		List<Long> ids = new ArrayList<Long>();
		
		ids.add(expectedPhotoList.get(0).getId());
		ids.add(expectedPhotoList.get(1).getId());
		
		when(photoRepository.findAll(ids)).thenReturn(expectedPhotoList.subList(0, 2));
		
		photoService.setPhotoRepositoryBase(photoRepository);

		
		//execute
		List<Photo> actualList = (List<Photo>) photoService.findAll(ids);
	
		
		//assert
		assertEquals(2, actualList.size());
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), actualList.get(0));
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(1), actualList.get(1));
		
		verify(photoRepository, times(1)).findAll(ids);
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void findOne() {
		
		//given
		Photo expectedPhoto = expectedPhotoList.get(0);
		Long id = expectedPhoto.getId();
		when(photoRepository.findOne(id)).thenReturn(expectedPhoto);
		photoService.setPhotoRepositoryBase(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.findOne(id);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhoto, actualPhoto);
		
		verify(photoRepository, times(1)).findOne(id);
        verifyNoMoreInteractions(photoRepository);
		
	}
}
