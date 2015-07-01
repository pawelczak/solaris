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
import pl.pawelczak.solaris.persistence.repository.GalleryRepository;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryDeleteForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryForm;
import pl.pawelczak.solaris.webapp.admin.gallery.form.GalleryFormConverter;

@RunWith(MockitoJUnitRunner.class)
public class GalleryServiceImplTest {

	
	private GalleryServiceImpl galleryService = new GalleryServiceImpl();
	
	@Mock
	private GalleryRepository galleryRepository;
	
	@Mock
	private GalleryFormConverter galleryFormConverter;
	
	private List<Gallery> galleryList = createGalleryList();
	
	private static final Long GALLERY_ONE_ID = 18l;
	private static final Long GALLERY_TWO_ID = 21l;
	private static final Long GALLERY_THREE_ID = 23l;
	
	private static final String GALLERY_ONE_NAME = "Tatry Polskie";
	private static final String GALLERY_TWO_NAME = "Tatry Słowackie";
	private static final String GALLERY_THREE_NAME = "Pireneje";
	
	private static final String GALLERY_ONE_DESC = "Opis Tatry Polskie";
	
	private static final Boolean GALLERY_ONE_VISIBLE = true;
	
	
	
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void findAll_noArguments() {
		
		//given
		when(galleryRepository.findAll()).thenReturn(galleryList);
		
		galleryService.setGalleryRepository(galleryRepository);

		
		//execute
		List<Gallery> actualList = galleryService.findAll();
	
		
		//assert
		assertEquals(3, actualList.size());
		assertEquals(GALLERY_ONE_NAME, actualList.get(0).getName());
		assertEquals(GALLERY_TWO_NAME, actualList.get(1).getName());
		assertEquals(GALLERY_THREE_NAME, actualList.get(2).getName());
		
		verify(galleryRepository, times(1)).findAll();
        verifyNoMoreInteractions(galleryRepository);
	}
	
	@Test
	public void findAll_byIdsList() {
		
		//given
		List<Long> ids = new ArrayList<Long>();
		
		ids.add(GALLERY_ONE_ID);
		ids.add(GALLERY_TWO_ID);
		
		when(galleryRepository.findAll(ids)).thenReturn(galleryList.subList(0, 2));
		
		galleryService.setGalleryRepository(galleryRepository);

		
		//execute
		List<Gallery> actualList = (List<Gallery>) galleryService.findAll(ids);
	
		
		//assert
		assertEquals(2, actualList.size());
		assertEquals(ids.get(0), actualList.get(0).getId());
		assertEquals(GALLERY_ONE_NAME, actualList.get(0).getName());
		assertEquals(ids.get(1), actualList.get(1).getId());
		assertEquals(GALLERY_TWO_NAME, actualList.get(1).getName());
		
		verify(galleryRepository, times(1)).findAll(ids);
        verifyNoMoreInteractions(galleryRepository);
	}

	@Test 
	public void findOne() {
		
		//given
		Long id = GALLERY_TWO_ID;		
		
		when(galleryRepository.findOne(id)).thenReturn(galleryList.get(1));
		
		galleryService.setGalleryRepository(galleryRepository);
		
		//execute
		Gallery actual = galleryService.findOne(id);
		
		
		//assert
		assertEquals(id, actual.getId());
		assertEquals(GALLERY_TWO_NAME, actual.getName());
		
		verify(galleryRepository, times(1)).findOne(id);
        verifyNoMoreInteractions(galleryRepository);
		
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
		
		Gallery gallery = Gallery.getBuilder("Deleted gallery").build();
		
		when(galleryRepository.findOne(galleryId)).thenReturn(gallery);
		galleryService.setGalleryRepository(galleryRepository);
		
		//execute
		galleryService.deleteById(galleryId);
		
		//assert
		verify(galleryRepository, times(1)).findOne(galleryId);
        verify(galleryRepository, times(1)).delete(gallery);
        verifyNoMoreInteractions(galleryRepository);
	}
	
	@Test
	public void delete_by_galleryDeleteForm() {
		
		
		//given
		List<Long> ids = new ArrayList<Long>();
		ids.add(GALLERY_ONE_ID);
		ids.add(GALLERY_TWO_ID);
		ids.add(GALLERY_THREE_ID);
		
		GalleryDeleteForm galleryDeleteForm = new GalleryDeleteForm();
		
		galleryDeleteForm.setIds(ids);
		
		List<Gallery> galleryList = createGalleryList();
		
		when(galleryRepository.findAll(ids)).thenReturn(galleryList);
		galleryService.setGalleryRepository(galleryRepository);
		
		
		//execute
		List<Gallery> actualGalleries = galleryService.delete(galleryDeleteForm);
		
		
		//assert
		assertEquals(3, actualGalleries.size());
		assertEquals(GALLERY_ONE_ID, actualGalleries.get(0).getId());
		
		verify(galleryRepository, times(1)).findAll(ids);
        verify(galleryRepository, times(1)).delete(galleryList);
        verifyNoMoreInteractions(galleryRepository);
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
}

