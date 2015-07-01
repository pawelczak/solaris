package pl.pawelczak.solaris.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.pawelczak.solaris.common.TestConfigurationBase;
import pl.pawelczak.solaris.config.GeneralConfiguration;

/**
 * 
 * @author Łukasz Pawełczak
 *
 */
@Configuration
@Import({WebappConfiguration.class, GeneralConfiguration.class})
public class WebappTestConfiguration extends TestConfigurationBase { 
	
	

    
}

