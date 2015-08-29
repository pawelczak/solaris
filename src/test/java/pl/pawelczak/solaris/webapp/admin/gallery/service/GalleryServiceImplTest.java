package pl.pawelczak.solaris.webapp.admin.gallery.service;

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
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryFormConverter;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;

@RunWith(MockitoJUnitRunner.class)
public class GalleryServiceImplTest {

	
	private GalleryServiceImpl galleryService = new GalleryServiceImpl();
	
	@Mock
	private GalleryRepository galleryRepository;
	
	@Mock
	private GalleryFormConverter galleryFormConverter;
	
	@Mock
	private PhotoRepository photoRepository;
	
	@Mock
	private PhotoService photoService;
	
	
	private List<Gallery> galleryList = createGalleryList();
	
	private List<Photo> photoList = createPhotoList();
	
	private static final Long GALLERY_ONE_ID = 18l;
	private static final Long GALLERY_TWO_ID = 21l;
	private static final Long GALLERY_THREE_ID = 23l;
	
	private static final String GALLERY_ONE_NAME = "Tatry Polskie";
	private static final String GALLERY_TWO_NAME = "Tatry Słowackie";
	private static final String GALLERY_THREE_NAME = "Pireneje";
	
	private static final String GALLERY_ONE_DESC = "Opis Tatry Polskie";
	
	private static final Boolean GALLERY_ONE_VISIBLE = true;
	
	private static final String PHOTO_ONE_TITLE = "Nice view";
	private static final String PHOTO_TWO_TITLE = "Gr8 view";
	private static final String PHOTO_THREE_TITLE = "Awsome view";
	private static final String PHOTO_FOUR_TITLE = "Incredible view";
	
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void findAll_noArguments() {
		
		
		//given
		when(galleryRepository.findAll()).thenReturn(galleryList);
		galleryService.setGalleryRepository(galleryRepository);
		
		when(photoRepository.findAllByGalleryId(galleryList.get(0).getId())).thenReturn(photoList.subList(0, 1));
		when(photoRepository.findAllByGalleryId(galleryList.get(1).getId())).thenReturn(new ArrayList<Photo>());
		when(photoRepository.findAllByGalleryId(galleryList.get(2).getId())).thenReturn(photoList.subList(2, 4));
		galleryService.setPhotoRepository(photoRepository);

		
		//execute
		List<Gallery> actualList = galleryService.findAll();
	
		
		//assert
		assertEquals(3, actualList.size());
		assertEquals(GALLERY_ONE_NAME, actualList.get(0).getName());
		assertEquals(1, actualList.get(0).getPhotoList().size());
		assertEquals(GALLERY_ONE_ID, actualList.get(0).getPhotoList().get(0).getGalleryId());
		assertEquals(PHOTO_ONE_TITLE, actualList.get(0).getPhotoList().get(0).getTitle());
		
		assertEquals(GALLERY_TWO_NAME, actualList.get(1).getName());
		assertEquals(0, actualList.get(1).getPhotoList().size());

		
		assertEquals(GALLERY_THREE_NAME, actualList.get(2).getName());
		assertEquals(2, actualList.get(2).getPhotoList().size());
		assertEquals(GALLERY_THREE_ID, actualList.get(2).getPhotoList().get(0).getGalleryId());
		assertEquals(PHOTO_THREE_TITLE, actualList.get(2).getPhotoList().get(0).getTitle());
		assertEquals(GALLERY_THREE_ID, actualList.get(2).getPhotoList().get(1).getGalleryId());
		assertEquals(PHOTO_FOUR_TITLE, actualList.get(2).getPhotoList().get(1).getTitle());
		
		verify(galleryRepository, times(1)).findAll();
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(0).getId());
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(1).getId());
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(2).getId());
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(photoRepository);
	}
	
	@Test
	public void findAll_byIdsList() {
		
		//given
		List<Long> ids = new ArrayList<Long>();
		
		ids.add(GALLERY_ONE_ID);
		ids.add(GALLERY_TWO_ID);
		
		when(galleryRepository.findAll(ids)).thenReturn(galleryList.subList(0, 2));
		when(photoRepository.findAllByGalleryId(galleryList.get(0).getId())).thenReturn(photoList.subList(0, 1));
		when(photoRepository.findAllByGalleryId(galleryList.get(1).getId())).thenReturn(new ArrayList<Photo>());
		
		galleryService.setGalleryRepository(galleryRepository);
		galleryService.setPhotoRepository(photoRepository);

		
		//execute
		List<Gallery> actualList = (List<Gallery>) galleryService.findAll(ids);
	
		
		//assert
		assertEquals(2, actualList.size());
		assertEquals(ids.get(0), actualList.get(0).getId());
		assertEquals(GALLERY_ONE_NAME, actualList.get(0).getName());
		assertEquals(1, actualList.get(0).getPhotoList().size());
		assertEquals(GALLERY_ONE_ID, actualList.get(0).getPhotoList().get(0).getGalleryId());
		assertEquals(PHOTO_ONE_TITLE, actualList.get(0).getPhotoList().get(0).getTitle());
		
		assertEquals(ids.get(1), actualList.get(1).getId());
		assertEquals(GALLERY_TWO_NAME, actualList.get(1).getName());
		assertEquals(0, actualList.get(1).getPhotoList().size());
		
		verify(galleryRepository, times(1)).findAll(ids);
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(0).getId());
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(1).getId());
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(photoRepository);
	}

	@Test 
	public void findOne() {
		
		//given
		Long id = GALLERY_TWO_ID;		
		
		when(galleryRepository.findOne(id)).thenReturn(galleryList.get(1));
		
		when(photoRepository.findAllByGalleryId(GALLERY_TWO_ID)).thenReturn(photoList.subList(1, 2));
		
		galleryService.setGalleryRepository(galleryRepository);
		galleryService.setPhotoRepository(photoRepository);
		
		//execute
		Gallery actual = galleryService.findOne(id);
		
		
		//assert
		assertEquals(id, actual.getId());
		assertEquals(GALLERY_TWO_NAME, actual.getName());
		
		assertEquals(1, actual.getPhotoList().size());
		assertEquals(GALLERY_TWO_ID, actual.getPhotoList().get(0).getGalleryId());
		assertEquals(PHOTO_TWO_TITLE, actual.getPhotoList().get(0).getTitle());
		
		verify(galleryRepository, times(1)).findOne(id);
		verify(photoRepository, times(1)).findAllByGalleryId(GALLERY_TWO_ID);
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(photoRepository);
		
	}
	
	@Test
	public void add_GalleryForm() {
		
		//given
		GalleryForm galleryForm = new GalleryForm();
		
		galleryForm.setName(GALLERY_ONE_NAME);
		
		Gallery gallery = Gallery.getBuilder(GALLERY_ONE_NAME).build();
		Whitebox.setInternalState(gallery, "id", GALLERY_ONE_ID);
		
		when(galleryFormConverter.convert(galleryForm)).thenReturn(gallery);
		when(galleryRepository.save(gallery)).thenReturn(gallery);
		
		galleryService.setGalleryFormConverter(galleryFormConverter);
		galleryService.setGalleryRepository(galleryRepository);
		
		
		//execute
		Gallery addedGallery = galleryService.add(galleryForm);
		
		//assert
		assertEquals(GALLERY_ONE_ID, addedGallery.getId());
		assertEquals(GALLERY_ONE_NAME, addedGallery.getName());
		
		verify(galleryRepository, times(1)).save(gallery);
        verifyNoMoreInteractions(galleryRepository);
        verify(galleryFormConverter, times(1)).convert(galleryForm);
        verifyNoMoreInteractions(galleryFormConverter);
	}
	
	@Test
	public void update_galleryForm() {
		
		
		//given
		Long oldGalleryId = 23l; 
		
		Gallery oldGallery = Gallery.getBuilder("Old gallery Name")
									.description("Old description")
									.visible(!GALLERY_ONE_VISIBLE)
									.build();
		Whitebox.setInternalState(oldGallery, "id", oldGalleryId);
		
		GalleryForm galleryForm = new GalleryForm();
		
		galleryForm.setId(oldGalleryId);
		galleryForm.setName(GALLERY_ONE_NAME);
		galleryForm.setDescription(GALLERY_ONE_DESC);
		galleryForm.setVisible(GALLERY_ONE_VISIBLE);
		
		
		when(galleryRepository.findOne(oldGalleryId)).thenReturn(oldGallery);
		when(galleryRepository.save(oldGallery)).thenReturn(oldGallery);
		galleryService.setGalleryRepository(galleryRepository);
		
		
		//execute
		Gallery updatedGallery = galleryService.update(galleryForm);
		
		
		//assert
		assertEquals(oldGalleryId, updatedGallery.getId());
		assertEquals(GALLERY_ONE_NAME, updatedGallery.getName());
		assertEquals(GALLERY_ONE_DESC, updatedGallery.getDescription());
		assertEquals(GALLERY_ONE_VISIBLE, updatedGallery.getVisible());
		
		verify(galleryRepository, times(1)).findOne(oldGalleryId);
        verify(galleryRepository, times(1)).save(oldGallery);
        verifyNoMoreInteractions(galleryRepository);
	}
	
	@Test
	public void deleteById() {
		
		
		//given
		Long galleryId = 45l;
		
		Photo photo = Photo.getBuilder(galleryId).build();
		List<Photo> photoList = new ArrayList<Photo>();
		photoList.add(photo);
		
		Gallery gallery = Gallery.getBuilder("Deleted gallery")
									.photoList(photoList)
									.build();
		
		Whitebox.setInternalState(gallery, "id", galleryId);
		
		when(galleryRepository.findOne(galleryId)).thenReturn(gallery);
		galleryService.setGalleryRepository(galleryRepository);
		galleryService.setPhotoService(photoService);
		
		//execute
		galleryService.deleteById(galleryId);
		
		//assert
		verify(galleryRepository, times(1)).findOne(galleryId);
        verify(galleryRepository, times(1)).delete(gallery);
        verify(photoService, times(1)).deleteByGalleryId(gallery.getId());
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(photoService);
	}
	
	@Test
	public void delete_by_galleryDeleteForm() {
		
		
		//given
		List<Long> ids = new ArrayList<Long>();
		ids.add(GALLERY_ONE_ID);
		ids.add(GALLERY_TWO_ID);
		
		GalleryDeleteForm galleryDeleteForm = new GalleryDeleteForm();
		
		galleryDeleteForm.setIds(ids);
		
		List<Photo> photoList = createPhotoList();
		List<Gallery> galleryList = createGalleryList();
		galleryList.get(0).setPhotoList(photoList.subList(0, 1));
		
		when(photoRepository.findAllByGalleryId(galleryList.get(0).getId())).thenReturn(photoList.subList(0, 1));
		when(photoRepository.findAllByGalleryId(galleryList.get(1).getId())).thenReturn(new ArrayList<Photo>());
		
		when(galleryRepository.findAll(ids)).thenReturn(galleryList.subList(0, 2));
		
		galleryService.setGalleryRepository(galleryRepository);
		galleryService.setPhotoService(photoService);
		galleryService.setPhotoRepository(photoRepository);

		
		//execute
		List<Gallery> deletedGalleries = galleryService.delete(galleryDeleteForm);
		
		
		//assert
		assertEquals(2, deletedGalleries.size());
		assertEquals(GALLERY_ONE_ID, deletedGalleries.get(0).getId());
		
		verify(galleryRepository, times(1)).findAll(ids);
        verify(galleryRepository, times(1)).delete(galleryList.subList(0, 2));
        verify(photoService, times(1)).deleteByGalleryId(GALLERY_ONE_ID);
        verify(photoService, times(1)).deleteByGalleryId(GALLERY_TWO_ID);
        verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(0).getId());
		verify(photoRepository, times(1)).findAllByGalleryId(galleryList.get(1).getId());
        verifyNoMoreInteractions(galleryRepository);
        verifyNoMoreInteractions(photoService);
        verifyNoMoreInteractions(photoRepository);
	}
	
	//------------------------ PRIVATE --------------------------
	
	private List<Gallery> createGalleryList() {
		
		Gallery galleryOne = Gallery.getBuilder(GALLERY_ONE_NAME).build();
		Whitebox.setInternalState(galleryOne, "id", GALLERY_ONE_ID);
		
		Gallery galleryTwo = Gallery.getBuilder(GALLERY_TWO_NAME).build();
		Whitebox.setInternalState(galleryTwo, "id", GALLERY_TWO_ID);
		
		Gallery galleryThree = Gallery.getBuilder(GALLERY_THREE_NAME).build();
		Whitebox.setInternalState(galleryThree, "id", GALLERY_THREE_ID);
		
		
		List<Gallery> list = new ArrayList<Gallery>();
		
		list.add(galleryOne);
		list.add(galleryTwo);
		list.add(galleryThree);
		
		return list;
	}
	
	private List<Photo> createPhotoList() {
		Photo photoOne = Photo.getBuilder(GALLERY_ONE_ID)
								.title(PHOTO_ONE_TITLE)
								.build();
		
		Photo photoTwo = Photo.getBuilder(GALLERY_TWO_ID)
				.title(PHOTO_TWO_TITLE)
				.build();
		
		Photo photoThree = Photo.getBuilder(GALLERY_THREE_ID)
				.title(PHOTO_THREE_TITLE)
				.build();
		
		Photo photoFour = Photo.getBuilder(GALLERY_THREE_ID)
				.title(PHOTO_FOUR_TITLE)
				.build();
		
		List<Photo> photoList = new ArrayList<Photo>();
		
		photoList.add(photoOne);
		photoList.add(photoTwo);
		photoList.add(photoThree);
		photoList.add(photoFour);
		
		return photoList;
	}
}

