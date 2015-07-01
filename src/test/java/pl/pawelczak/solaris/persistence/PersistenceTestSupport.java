package pl.pawelczak.solaris.persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceTestConfiguration.class })
//@Category(SlowTest.class)  // unfortunately it is not interpreted by surfire for subclasses
public abstract class PersistenceTestSupport {

    
    private DbCleaner dbCleaner;
    
    
    @Before
    public void before() {
        dbCleaner.clean();
    }
    
    @After
    public void after() {
        dbCleaner.clean(); 
    }

    @Autowired
    public void setDbCleaner(DbCleaner dbCleaner) {
    	this.dbCleaner = dbCleaner;
    }
}
