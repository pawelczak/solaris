package pl.pawelczak.solaris.webapp.admin.photo.service;

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
import pl.pawelczak.solaris.webapp.admin.photo.PhotoTestFactory;
import pl.pawelczak.solaris.webapp.admin.photo.PhotoTestUtils;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoDeleteForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoFormConverter;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTest {


	
	private PhotoServiceImpl photoService = new PhotoServiceImpl();
	
	@Mock
	private PhotoRepository photoRepository;
	
	@Mock
	private PhotoFormConverter photoFormConverter;
	
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
	
	@Test
	public void findAll_byIdsList() {
		
		//given
		List<Long> ids = new ArrayList<Long>();
		
		ids.add(expectedPhotoList.get(0).getId());
		ids.add(expectedPhotoList.get(1).getId());
		
		when(photoRepository.findAll(ids)).thenReturn(expectedPhotoList.subList(0, 2));
		
		photoService.setPhotoRepository(photoRepository);

		
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
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.findOne(id);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhoto, actualPhoto);
		
		verify(photoRepository, times(1)).findOne(id);
        verifyNoMoreInteractions(photoRepository);
		
	}
	
	@Test
	public void add() {
		
		
		//given
		PhotoForm photoForm = new PhotoForm();
		photoForm.setTitle(expectedPhotoList.get(0).getTitle());
		
		when(photoFormConverter.convert(photoForm)).thenReturn(expectedPhotoList.get(0));
		when(photoRepository.save(expectedPhotoList.get(0))).thenReturn(expectedPhotoList.get(0));
		
		photoService.setPhotoFormConverter(photoFormConverter);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.add(photoForm);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), actualPhoto);
		
	}
	
	@Test
	public void update() {
		
		
		//given
		Long galleryId = 2l;
		String photoTitle = "Updated titile";
		Photo expectedPhoto = expectedPhotoList.get(0);
		PhotoForm photoForm = new PhotoForm();
		photoForm.setGalleryId(galleryId);
		photoForm.setTitle(photoTitle);

		when(photoRepository.findOne(expectedPhoto.getId())).thenReturn(expectedPhoto);
		when(photoRepository.save(expectedPhoto)).thenReturn(expectedPhoto);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.update(photoForm);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhoto, actualPhoto);
		
		verify(photoRepository, times(1)).findOne(expectedPhoto.getId());
        verify(photoRepository, times(1)).save(expectedPhoto);
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void deleteById() {
		
		
		//given
		Photo deletePhoto = expectedPhotoList.get(0);
		Long photoId = deletePhoto.getId();
		when(photoRepository.findOne(photoId)).thenReturn(deletePhoto);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		photoService.deleteById(photoId);
		
		
		//assert
		verify(photoRepository, times(1)).findOne(deletePhoto.getId());
        verify(photoRepository, times(1)).delete(deletePhoto);
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void delete_by_photoDeleteForm() {
		
		
		//given
		List<Long> ids = new ArrayList<Long>();
		ids.add(expectedPhotoList.get(0).getId());
		ids.add(expectedPhotoList.get(1).getId());
		
		PhotoDeleteForm photoDeleteForm = new PhotoDeleteForm();
		photoDeleteForm.setIds(ids);
		
		when(photoRepository.findAll(ids)).thenReturn(expectedPhotoList.subList(0, 2));
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		List<Photo> deletedPhotos = photoService.delete(photoDeleteForm);
		
		
		//assert
		assertEquals(2, deletedPhotos.size());
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), deletedPhotos.get(0));
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(1), deletedPhotos.get(1));
		
		verify(photoRepository, times(1)).findAll(ids);
        verify(photoRepository, times(1)).delete(deletedPhotos);
        verifyNoMoreInteractions(photoRepository);
	}
	
}
