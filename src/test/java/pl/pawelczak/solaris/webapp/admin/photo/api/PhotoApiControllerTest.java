package pl.pawelczak.solaris.webapp.admin.photo.api;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.WebappTestConfiguration;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.admin.photo.service.PhotoService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
@ContextConfiguration(classes = WebappTestConfiguration.class)})
public class PhotoApiControllerTest {

	
	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private PhotoApiController photoApiController;
    
	@Mock
	private PhotoService photoService;
	
	@Mock
	private PhotoApiModelConverter photoApiModelConverter;
    
	private List<Photo> photoList = new ArrayList<Photo>();
	
	private List<PhotoApiModel> expectedPhotoList = createPhotoApiModelList();
	
	private final static Long PHOTO_ONE_ID = 73l;
	private final static String PHOTO_ONE_TITLE = "Photo title 1";
	
	private final static Long PHOTO_TWO_GALLERY_ID = 25l;
	private final static String PHOTO_TWO_TITLE = "Hill behind the backyard";
	
	private final static Long GALLERY_ONE_ID = 8l;
	private final static String GALLERY_ONE_NAME = "Gr8 gallery name";

	private final static Long GALLERY_TWO_ID = 9l;
	private final static String GALLERY_TWO_NAME = "Cool gallery";
	
	//------------------------ CONFIG --------------------------
	
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(photoService.findAll()).thenReturn(photoList);
        when(photoApiModelConverter.convert(photoList)).thenReturn(expectedPhotoList);
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------
    
	@Test
	public void photosList() throws Exception {
	    
		
        //execute
        ResultActions actions = mockMvc.perform(get("/admin/api/photo/list")
                .accept(MediaType.APPLICATION_JSON));
                
        
        
        //assert
        actions
        		.andExpect(status().isOk())
        		//.andExpect(jsonPath("$", hasSize(2)))
        		//.andExpect(jsonPath("$.[0].id", is(photoList.get(0).getId())))
				.andExpect(jsonPath("$.[0].title").value(expectedPhotoList.get(0).getTitle()))
				//.andExpect(jsonPath("$.[0].gallery.id").value(expectedPhotoList.get(0).getGallery().getId()))
				.andExpect(jsonPath("$.[0].gallery.name").value(expectedPhotoList.get(0).getGallery().getName()))
		        //.andExpect(jsonPath("$[1].id", is(photoList.get(1).getId())))
				.andExpect(jsonPath("$.[1].title").value(expectedPhotoList.get(1).getTitle()))
				//.andExpect(jsonPath("$.[1].gallery.id").value(expectedPhotoList.get(1).getGallery().getId()))
				.andExpect(jsonPath("$.[1].gallery.name").value(expectedPhotoList.get(1).getGallery().getName()));

   

        verify(photoService, times(1)).findAll();
        verify(photoApiModelConverter, times(1)).convert(photoList);
        verifyNoMoreInteractions(photoService);
        verifyNoMoreInteractions(photoApiModelConverter);
	}
	
	//TODO @Test
	public void singlePhotoAdd() throws Exception {
		
		//given
		PhotoForm photoForm = new PhotoForm();
		photoForm.setId(PHOTO_ONE_ID);
		
		Photo photo = Photo.getBuilder(32l).build();
		Whitebox.setInternalState(photo, "id", PHOTO_ONE_ID);
		
		when(photoService.add(photoForm)).thenReturn(photo);
		when(photoApiModelConverter.convert(photo)).thenReturn(expectedPhotoList.get(0));
		
		
		//execute
		ResultActions actions = mockMvc.perform(post("/admin/api/photo/add").param("id", PHOTO_ONE_ID.toString())
                .accept(MediaType.APPLICATION_JSON));
		
		//assert
		actions
				.andExpect(
						redirectedUrl("/admin/api/gallery/" + PHOTO_ONE_ID)
				);
		
		verify(photoService, times(1)).add(photoForm);
        verify(photoApiModelConverter, times(1)).convert(photo);
        verifyNoMoreInteractions(photoService);
        verifyNoMoreInteractions(photoApiModelConverter);
	}
	
	
    //------------------------ PRIVATE --------------------------
	
	private List<PhotoApiModel> createPhotoApiModelList() {
		
		List<PhotoApiModel> photos = new ArrayList<PhotoApiModel>();
		
		PhotoApiModel.Gallery galleryOne = new PhotoApiModel.Gallery();
		galleryOne.setName(GALLERY_ONE_NAME);
		Whitebox.setInternalState(galleryOne, "id", GALLERY_ONE_ID);
		PhotoApiModel.Gallery galleryTwo = new PhotoApiModel.Gallery();
		galleryTwo.setName(GALLERY_TWO_NAME);
		Whitebox.setInternalState(galleryTwo, "id", GALLERY_TWO_ID);
		
		PhotoApiModel photoOne = PhotoApiModel.getBuilder(galleryOne).title(PHOTO_ONE_TITLE).build();
		PhotoApiModel photoTwo = PhotoApiModel.getBuilder(galleryTwo).title(PHOTO_TWO_TITLE).build();
		
		photos.add(photoOne);
		photos.add(photoTwo);
		
		return photos;
	}
}

