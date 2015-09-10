package pl.pawelczak.solaris.webapp.site;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import pl.pawelczak.solaris.webapp.WebappTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({ 
@ContextConfiguration(classes = WebappTestConfiguration.class) })
public class SiteControllerTest {

	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private SiteController siteController;
    
	
	//------------------------ CONFIG --------------------------
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------	
    
	@Test
	public void home() throws Exception {
		
        //execute
        ResultActions actions = mockMvc.perform(get("/"));
        
        
        //assert
        actions
            .andExpect(status().isOk())
            .andExpect(view().name("siteHome"));
		
	}
	
	@Test
	public void photo() throws Exception {
		
        //execute
        ResultActions actions = mockMvc.perform(get("/photo"));
        
        
        //assert
        actions
            .andExpect(status().isOk())
            .andExpect(view().name("sitePhoto"));
		
	}
	
	@Test
	public void contact() throws Exception {
		
        //execute
        ResultActions actions = mockMvc.perform(get("/contact"));
        
        
        //assert
        actions
            .andExpect(status().isOk())
            .andExpect(view().name("siteContact"));
		
	}
}
