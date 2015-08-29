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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

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
	
	@Mock
	private PhotoImageService photoImageService;
	
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
		photoForm.setDescription(expectedPhotoList.get(0).getDescription());
		photoForm.setImageSrc(expectedPhotoList.get(0).getImageSrc());
		
		when(photoFormConverter.convert(photoForm)).thenReturn(expectedPhotoList.get(0));
		when(photoRepository.save(expectedPhotoList.get(0))).thenReturn(expectedPhotoList.get(0));
		
		photoService.setPhotoFormConverter(photoFormConverter);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.add(photoForm);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), actualPhoto);
		assertEquals(photoForm.getTitle(), actualPhoto.getTitle());
		assertEquals(photoForm.getDescription(), actualPhoto.getDescription());
		assertEquals(photoForm.getImageSrc(), actualPhoto.getImageSrc());
		
	}
	
	@Test
	public void update() {
		
		
		//given
		Long galleryId = 2l;
		String photoTitle = "Updated titile";
		String photoDesc = "Desc photo";
		String photoSrc = "folder/img.jpg";
		Photo expectedPhoto = expectedPhotoList.get(0);
		PhotoForm photoForm = new PhotoForm();
		photoForm.setId(expectedPhoto.getId());
		photoForm.setGalleryId(galleryId);
		photoForm.setTitle(photoTitle);
		photoForm.setDescription(photoDesc);
		photoForm.setImageSrc(photoSrc);

		when(photoRepository.findOne(expectedPhoto.getId())).thenReturn(expectedPhoto);
		when(photoRepository.save(expectedPhoto)).thenReturn(expectedPhoto);
		photoService.setPhotoRepository(photoRepository);
		
		
		//execute
		Photo actualPhoto = photoService.update(photoForm);
		
		
		//assert
		PhotoTestUtils.assertPhoto(expectedPhoto, actualPhoto);
		assertEquals(photoForm.getGalleryId(), actualPhoto.getGalleryId());
		assertEquals(photoForm.getTitle(), actualPhoto.getTitle());
		assertEquals(photoForm.getDescription(), actualPhoto.getDescription());
		assertEquals(photoForm.getImageSrc(), actualPhoto.getImageSrc());
		
		verify(photoRepository, times(1)).findOne(expectedPhoto.getId());
        verify(photoRepository, times(1)).save(expectedPhoto);
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void updateImage() {
		
		
		//given
		MultipartFile image = Mockito.mock(MultipartFile.class);
		Photo expectedPhoto = expectedPhotoList.get(0);
		Long photoId = expectedPhoto.getId();
		
		when(photoRepository.findOne(expectedPhoto.getId())).thenReturn(expectedPhoto);
		when(photoRepository.save(expectedPhoto)).thenReturn(expectedPhoto);
		when(photoImageService.save(expectedPhoto.getId(), image)).thenReturn("title");
		photoService.setPhotoRepository(photoRepository);
		photoService.setPhotoImageService(photoImageService);
		
		//execute
		photoService.updateImage(photoId, image);
		
		
		//assert
		verify(photoRepository, times(1)).findOne(expectedPhoto.getId());
        verify(photoRepository, times(1)).save(expectedPhoto);
        verify(photoImageService, times(1)).save(photoId, image);
        verifyNoMoreInteractions(photoRepository);
        verifyNoMoreInteractions(photoImageService);
	}
	
	@Test
	public void deleteById() {
		
		
		//given
		Photo deletePhoto = expectedPhotoList.get(0);
		Long photoId = deletePhoto.getId();
		when(photoRepository.findOne(photoId)).thenReturn(deletePhoto);
		photoService.setPhotoRepository(photoRepository);
		photoService.setPhotoImageService(photoImageService);
		
		
		//execute
		photoService.deleteById(photoId);
		
		
		//assert
		verify(photoRepository, times(1)).findOne(deletePhoto.getId());
        verify(photoRepository, times(1)).delete(deletePhoto);
        verify(photoImageService, times(1)).delete(photoId);
        verifyNoMoreInteractions(photoRepository);
        verifyNoMoreInteractions(photoImageService);
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
		photoService.setPhotoImageService(photoImageService);
		
		
		//execute
		List<Photo> deletedPhotos = photoService.delete(photoDeleteForm);
		
		
		//assert
		assertEquals(2, deletedPhotos.size());
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(0), deletedPhotos.get(0));
		PhotoTestUtils.assertPhoto(expectedPhotoList.get(1), deletedPhotos.get(1));
		
		verify(photoRepository, times(1)).findAll(ids);
        verify(photoRepository, times(1)).delete(deletedPhotos);
        verify(photoImageService, times(1)).delete(ids.get(0));
        verify(photoImageService, times(1)).delete(ids.get(1));
        verifyNoMoreInteractions(photoRepository);
        verifyNoMoreInteractions(photoImageService);
	}
	
	@Test
	public void delete_by_galleryId() {
		
		//given
		Long galleryId = 23l;

		when(photoRepository.findAllByGalleryId(galleryId)).thenReturn(expectedPhotoList);
		photoService.setPhotoRepository(photoRepository);
		photoService.setPhotoImageService(photoImageService);
		
		
		//execute
		photoService.deleteByGalleryId(galleryId);
		
				
		//assert
		verify(photoRepository, times(1)).findAllByGalleryId(galleryId);
        verify(photoRepository, times(1)).delete(expectedPhotoList);
        verify(photoImageService, times(1)).delete(expectedPhotoList.get(0).getId());
        verify(photoImageService, times(1)).delete(expectedPhotoList.get(1).getId());
        verifyNoMoreInteractions(photoRepository);
        verifyNoMoreInteractions(photoImageService);
	}
	
}
