package pl.pawelczak.solaris.webapp.site.api.photo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import pl.pawelczak.solaris.webapp.site.api.photo.service.PhotoApiService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
@ContextConfiguration(classes = WebappTestConfiguration.class) })
public class PhotoApiControllerTest {

	
	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private PhotoApiController photoApiController;
    
	@Mock
	private PhotoApiService photoApiService;
	
	@Mock
	private PhotoApiModelConverter photoApiModelConverter;
	
	private final static Long GALLERY_ID = 1l;
	
	private final static Long PHOTO_ONE_ID = 73l;
	private final static String PHOTO_ONE_TITLE = "Photo title 1";
	private final static String PHOTO_ONE_DESCRIPTION = "Photo description 1";
	private final static String PHOTO_ONE_IMAGE_SRC = "/photo/one/image/src";
	
	private final static Long PHOTO_TWO_ID = 25l;
	private final static String PHOTO_TWO_TITLE = "Hill behind the backyard";
	private final static String PHOTO_TWO_DESCRIPTION = "Photo description 2";
	private final static String PHOTO_TWO_IMAGE_SRC = "/photo/two/image/src";
	
	
	private List<Photo> photoList = new ArrayList<Photo>();
	
	private List<PhotoApiModel> photoApiModelList = createPhotoApiModelList();
	
	
	//------------------------ CONFIG --------------------------
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(photoApiService.findAllByGalleryId(GALLERY_ID)).thenReturn(photoList);
        when(photoApiModelConverter.convert(photoList)).thenReturn(photoApiModelList);
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------	
    
    @Test
	public void photoList() throws Exception {
	    
		
        //execute
        ResultActions actions = mockMvc.perform(get("/api/gallery/" + GALLERY_ID + "/photos")
                .accept(MediaType.APPLICATION_JSON));
                
        
        
        //assert
        actions
        		.andExpect(status().isOk())
        		//.andExpect(jsonPath("$", hasSize(2)))
        		//.andExpect(jsonPath("$.[0].id", is(GALLERY_ONE_ID)))
				.andExpect(jsonPath("$.[0].title").value(PHOTO_ONE_TITLE))
				.andExpect(jsonPath("$.[0].description").value(PHOTO_ONE_DESCRIPTION))
				.andExpect(jsonPath("$.[0].imageSrc").value(PHOTO_ONE_IMAGE_SRC))
		        //.andExpect(jsonPath("$[1].id", is(GALLERY_TWO_ID)))
				.andExpect(jsonPath("$.[1].title").value(PHOTO_TWO_TITLE))
				.andExpect(jsonPath("$.[1].description").value(PHOTO_TWO_DESCRIPTION))
				.andExpect(jsonPath("$.[1].imageSrc").value(PHOTO_TWO_IMAGE_SRC));

   

        verify(photoApiService, times(1)).findAllByGalleryId(GALLERY_ID);
        verify(photoApiModelConverter, times(1)).convert(photoList);
        verifyNoMoreInteractions(photoApiService);
        verifyNoMoreInteractions(photoApiModelConverter);
	}

    
    //------------------------ TESTS --------------------------	
    
	private List<PhotoApiModel> createPhotoApiModelList() {
		
		List<PhotoApiModel> photos = new ArrayList<PhotoApiModel>();
		
		
		PhotoApiModel photoOne = PhotoApiModel.getBuilder(PHOTO_ONE_ID)
												.title(PHOTO_ONE_TITLE)
												.description(PHOTO_ONE_DESCRIPTION)
												.imageSrc(PHOTO_ONE_IMAGE_SRC)
												.build();
		PhotoApiModel photoTwo = PhotoApiModel.getBuilder(PHOTO_TWO_ID)
												.title(PHOTO_TWO_TITLE)
												.description(PHOTO_TWO_DESCRIPTION)
												.imageSrc(PHOTO_TWO_IMAGE_SRC)
												.build();
		
		photos.add(photoOne);
		photos.add(photoTwo);
		
		return photos;
	}
}
