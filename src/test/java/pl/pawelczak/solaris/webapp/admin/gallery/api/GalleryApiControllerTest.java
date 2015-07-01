package pl.pawelczak.solaris.webapp.admin.gallery.api;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.webapp.WebappTestConfiguration;
import pl.pawelczak.solaris.webapp.admin.gallery.service.GalleryService;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
@ContextConfiguration(classes = WebappTestConfiguration.class)})
public class GalleryApiControllerTest {

	
	
	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private GalleryApiController galleryApiController;
    
	@Mock
	private GalleryService galleryService;
    
	
	private List<Gallery> galleryList = createGalleryList();
	
	private static final Long GALLERY_ONE_ID = 18l;
	private static final String GALLERY_ONE_NAME = "Tatry Polskie";
	private static final String GALLERY_ONE_DESC = "Opis tatry";
	private static final Boolean GALLERY_ONE_VISIBLE = false;
	
	private static final Long GALLERY_TWO_ID = 19l;
	private static final String GALLERY_TWO_NAME = "Pireneje";
	private static final String GALLERY_TWO_DESC = "Opis pireneje";
	private static final Boolean GALLERY_TWO_VISIBLE = true;
	
	
	//------------------------ CONFIG --------------------------
	
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(galleryService.findAll()).thenReturn(galleryList);
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------	
	
	
	@Test
	public void galleriesList() throws Exception {
	    
		
        //execute
        ResultActions actions = mockMvc.perform(get("/admin/api/gallery/list")
                .accept(MediaType.APPLICATION_JSON));
                
        
        //assert
        actions
        		.andExpect(status().isOk())
        		//.andExpect(jsonPath("$", hasSize(2)))
        		//.andExpect(jsonPath("$.[0].id", is(GALLERY_ONE_ID)))
				.andExpect(jsonPath("$.[0].name").value(GALLERY_ONE_NAME))
				.andExpect(jsonPath("$.[0].description").value(GALLERY_ONE_DESC))
				.andExpect(jsonPath("$.[0].visible").value(GALLERY_ONE_VISIBLE))
		        //.andExpect(jsonPath("$[1].id", is(GALLERY_TWO_ID)))
				.andExpect(jsonPath("$.[1].name").value(GALLERY_TWO_NAME))
				.andExpect(jsonPath("$.[1].description").value(GALLERY_TWO_DESC))
				.andExpect(jsonPath("$.[1].visible").value(GALLERY_TWO_VISIBLE));

   
        verify(galleryService, times(1)).findAll();
        verifyNoMoreInteractions(galleryService);
	}

	

	//------------------------ PRIVATE --------------------------

	
	private List<Gallery> createGalleryList() {
		
		List<Gallery> list = new ArrayList<Gallery>();
		
		Gallery galleryOne = Gallery.getBuilder(GALLERY_ONE_NAME)
									.description(GALLERY_ONE_DESC)
									.visible(GALLERY_ONE_VISIBLE)
									.build();
		Whitebox.setInternalState(galleryOne, "id", GALLERY_ONE_ID);
		
		
		Gallery galleryTwo = Gallery.getBuilder(GALLERY_TWO_NAME)
									.description(GALLERY_TWO_DESC)
									.visible(GALLERY_TWO_VISIBLE)
									.build();
		Whitebox.setInternalState(galleryOne, "id", GALLERY_TWO_ID);
		
		list.add(galleryOne);
		list.add(galleryTwo);
		
		return list;
	}
}

