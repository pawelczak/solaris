package pl.pawelczak.solaris.webapp.site.api.gallery;

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

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.webapp.WebappTestConfiguration;
import pl.pawelczak.solaris.webapp.site.api.gallery.service.GalleryApiService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
@ContextConfiguration(classes = WebappTestConfiguration.class) })
public class GalleryApiControllerTest {

	
	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private GalleryApiController galleryApiController;
    
	@Mock
	private GalleryApiService galleryApiService;
	
	@Mock
	private GalleryApiModelConverter galleryApiModelConverter;
	
	private static final Long GALLERY_ONE_ID = 84l;
	private static final String GALLERY_ONE_NAME = "Gallery #1 name";
	private static final String GALLERY_ONE_DESCRIPTION = "Awesome view";
	private static final String GALLERY_ONE_FEATURED_IMAGE_SRC = "/file/path/1.jpeg";
	
	private static final Long GALLERY_TWO_ID = 85l;
	private static final String GALLERY_TWO_NAME = "Gallery #2 name";
	private static final String GALLERY_TWO_DESCRIPTION = "Not so awesome view";
	private static final String GALLERY_TWO_FEATURED_IMAGE_SRC = "/file/path/2.jpeg";
	
	private List<Gallery> galleryList = new ArrayList<Gallery>();
	
	
	//------------------------ CONFIG --------------------------
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        when(galleryApiService.findByVisibleTrue()).thenReturn(galleryList);
        when(galleryApiModelConverter.convert(galleryList)).thenReturn(createGalleries());
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------	
    
    @Test
	public void galleriesList() throws Exception {
	    
		
        //execute
        ResultActions actions = mockMvc.perform(get("/api/galleries")
                .accept(MediaType.APPLICATION_JSON));
                
        
        
        //assert
        actions
        		.andExpect(status().isOk())
        		//.andExpect(jsonPath("$", hasSize(2)))
        		//.andExpect(jsonPath("$.[0].id", is(GALLERY_ONE_ID)))
				.andExpect(jsonPath("$.[0].name").value(GALLERY_ONE_NAME))
				.andExpect(jsonPath("$.[0].description").value(GALLERY_ONE_DESCRIPTION))
				.andExpect(jsonPath("$.[0].featuredImageSrc").value(GALLERY_ONE_FEATURED_IMAGE_SRC))
		        //.andExpect(jsonPath("$[1].id", is(GALLERY_TWO_ID)))
				.andExpect(jsonPath("$.[1].name").value(GALLERY_TWO_NAME))
				.andExpect(jsonPath("$.[1].description").value(GALLERY_TWO_DESCRIPTION))
				.andExpect(jsonPath("$.[1].featuredImageSrc").value(GALLERY_TWO_FEATURED_IMAGE_SRC));

   

        verify(galleryApiService, times(1)).findByVisibleTrue();
        verify(galleryApiModelConverter, times(1)).convert(galleryList);
        verifyNoMoreInteractions(galleryApiService);
        verifyNoMoreInteractions(galleryApiModelConverter);
	}

    
    //------------------------ PRIVATE --------------------------	
    
    private List<GalleryApiModel> createGalleries() {
    	
    	GalleryApiModel galleryApiModelOne = GalleryApiModel.getBuilder(GALLERY_ONE_ID)
															.name(GALLERY_ONE_NAME)
															.description(GALLERY_ONE_DESCRIPTION)
															.featuredImageSrc(GALLERY_ONE_FEATURED_IMAGE_SRC)
															.build();
    	
    	GalleryApiModel galleryApiModelTwo = GalleryApiModel.getBuilder(GALLERY_TWO_ID)
															.name(GALLERY_TWO_NAME)
															.description(GALLERY_TWO_DESCRIPTION)
															.featuredImageSrc(GALLERY_TWO_FEATURED_IMAGE_SRC)
															.build();
    	
    	List<GalleryApiModel> galleries = new ArrayList<GalleryApiModel>();
    	
    	galleries.add(galleryApiModelOne);
    	galleries.add(galleryApiModelTwo);
    	
    	return galleries;
    }
}
