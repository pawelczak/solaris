package pl.pawelczak.solaris.webapp;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import pl.pawelczak.solaris.config.SecurityConfiguration;

@Configuration
@ComponentScan(basePackages = {
		"pl.pawelczak.solaris.config",
        "pl.pawelczak.solaris.persistence",
        "pl.pawelczak.solaris.webapp",
        "pl.pawelczak.solaris.webapp.common"
})
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class WebappConfiguration extends WebMvcConfigurerAdapter {

	
	
    @Resource
    private Environment environment;
	
    
    
    /**
     * Ensures that dispatcher servlet can be mapped to '/' and static resources
     * are still served by the containers default servlet.
     * @param configurer
     */
   @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
    /**
     * Configures the location of static resources such as css files.
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {    	
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
        registry.addResourceHandler("/photos/**").addResourceLocations("/photoImages/");
    }

    @Bean
    public TilesViewResolver viewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles/common-tiles.xml", "/WEB-INF/tiles/admin-tiles.xml", "/WEB-INF/tiles/site-tiles.xml"});
        return tilesConfigurer;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024*1024*50);
        return multipartResolver;
    }
    
}
