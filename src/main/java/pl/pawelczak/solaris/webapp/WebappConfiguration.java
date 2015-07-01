package pl.pawelczak.solaris.webapp;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@ComponentScan(basePackages = {
		"pl.pawelczak.solaris.config",
        "pl.pawelczak.solaris.persistence",
        "pl.pawelczak.solaris.webapp"
})
@EnableWebMvc
//@EnableJpaRepositories(basePackages = {"pl.pawelczak.solaris.persistence.repository"})
//@EnableTransactionManagement
//@PropertySource("classpath:persistence.properties")
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
    }

    @Bean
    public TilesViewResolver viewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        return viewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[]{"/WEB-INF/tiles/admin-tiles.xml", "/WEB-INF/tiles/site-tiles.xml"});
        return tilesConfigurer;
    }

    
}
