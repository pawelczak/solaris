package pl.pawelczak.solaris.webapp.admin;

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
public class AdminControllerTest {

	
	@Autowired
    private WebApplicationContext webApplicationCtx;
    
    private MockMvc mockMvc;
	
    @Autowired
    @InjectMocks
    private AdminController adminController;
    
	
	//------------------------ CONFIG --------------------------
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        
        mockMvc = webAppContextSetup(webApplicationCtx)
                    .build();
    }
    
    
    //------------------------ TESTS --------------------------	
    
	@Test
	public void adminDashboard() throws Exception {
		
        //execute
        ResultActions actions = mockMvc.perform(get("/admin/"));
        
        
        //assert
        actions
            .andExpect(status().isOk())
            .andExpect(view().name("adminDashboard"));
		
	}
	
	@Test
	public void login() throws Exception {
		
        //execute
        ResultActions actions = mockMvc.perform(get("/admin/login"));
        
        
        //assert
        actions
            .andExpect(status().isOk())
            .andExpect(view().name("login"));
		
	}
	

}
