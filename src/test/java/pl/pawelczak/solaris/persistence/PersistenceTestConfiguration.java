package pl.pawelczak.solaris.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import pl.pawelczak.solaris.common.TestConfigurationBase;

@Configuration
@ComponentScan(basePackages = {
        "pl.pawelczak.solaris.persistence"
})
@Import({PersistenceConfiguration.class})
public class PersistenceTestConfiguration extends TestConfigurationBase {
    
}
