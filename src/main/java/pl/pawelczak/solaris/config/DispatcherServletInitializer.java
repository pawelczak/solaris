package pl.pawelczak.solaris.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import pl.pawelczak.solaris.persistence.PersistenceConfiguration;
import pl.pawelczak.solaris.webapp.WebappConfiguration;


   
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
   
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { GeneralConfiguration.class, PersistenceConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebappConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }
   
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter};
    }
    
}