package pl.pawelczak.solaris.webapp.common.gallery;

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

@RunWith(MockitoJUnitRunner.class)
public class GalleryServiceBaseImplTest {

	
	
	private GalleryServiceBaseImpl galleryServiceBase = new GalleryServiceBaseImpl();
	
	@Mock
	private GalleryRepository galleryRepository;
	
	@Mock
	private PhotoRepository photoRepository;
	
	
	private List<Gallery> galleryList = createGalleryList();
	
	private List<Photo> photoList = createPhotoList();
	
	private static final Long GALLERY_ONE_ID = 18l;
	private static final Long GALLERY_TWO_ID = 21l;
	private static final Long GALLERY_THREE_ID = 23l;
	
	private static final String GALLERY_ONE_NAME = "Tatry Polskie";
	private static final String GALLERY_TWO_NAME = "Tatry Słowackie";
	private static final String GALLERY_THREE_NAME = "Pireneje";
	
	private static final String PHOTO_ONE_TITLE = "Nice view";
	private static final String PHOTO_TWO_TITLE = "Gr8 view";
	private static final String PHOTO_THREE_TITLE = "Awsome view";
	private static final String PHOTO_FOUR_TITLE = "Incredible view";
	
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void findAll_noArguments() {
		
		
		//given
		when(galleryRepository.findAll()).thenReturn(galleryList);
		galleryServiceBase.setGalleryRepositoryBase(galleryRepository);
		
		when(photoRepository.findAllByGalleryId(galleryList.get(0).getId())).thenReturn(photoList.subList(0, 1));
		when(photoRepository.findAllByGalleryId(galleryList.get(1).getId())).thenReturn(new ArrayList<Photo>());
		when(photoRepository.findAllByGalleryId(galleryList.get(2).getId())).thenReturn(photoList.subList(2, 4));
		galleryServiceBase.setPhotoRepositoryBase(photoRepository);

		
		//execute
		List<Gallery> actualList = galleryServiceBase.findAll();
	
		
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
		
		galleryServiceBase.setGalleryRepositoryBase(galleryRepository);
		galleryServiceBase.setPhotoRepositoryBase(photoRepository);

		
		//execute
		List<Gallery> actualList = (List<Gallery>) galleryServiceBase.findAll(ids);
	
		
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
		
		galleryServiceBase.setGalleryRepositoryBase(galleryRepository);
		galleryServiceBase.setPhotoRepositoryBase(photoRepository);
		
		//execute
		Gallery actual = galleryServiceBase.findOne(id);
		
		
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
