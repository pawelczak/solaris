package pl.pawelczak.solaris.webapp.admin.photo.api;

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
    
	
	private List<Photo> photoList = createPhotoList();
	
	private static final Long PHOTO_ONE_ID = 73l;
	private static final String PHOTO_ONE_TITLE = "Great view";
	
	private static final Long PHOTO_TWO_ID = 88l;
	private static final String PHOTO_TWO_TITLE = "Awesome view";
	
	
	//------------------------ CONFIG --------------------------
	
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(photoService.findAll()).thenReturn(photoList);
        
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
        		//.andExpect(jsonPath("$.[0].id", is(PHOTO_ONE_ID)))
				.andExpect(jsonPath("$.[0].title").value(PHOTO_ONE_TITLE))
		        //.andExpect(jsonPath("$[1].id", is(PHOTO_TWO_ID)))
				.andExpect(jsonPath("$.[1].title").value(PHOTO_TWO_TITLE));

   

        verify(photoService, times(1)).findAll();
        verifyNoMoreInteractions(photoService);
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private List<Photo> createPhotoList() {
		
		List<Photo> list = new ArrayList<Photo>();
		
		Photo photoOne = Photo.getBuilder()
									.title(PHOTO_ONE_TITLE)
									.build();
		Whitebox.setInternalState(photoOne, "id", PHOTO_ONE_ID);
		
		
		Photo photoTwo = Photo.getBuilder()
									.title(PHOTO_TWO_TITLE)
									.build();
		Whitebox.setInternalState(photoTwo, "id", PHOTO_TWO_ID);
		
		list.add(photoOne);
		list.add(photoTwo);
		
		return list;
	}
}
