package pl.pawelczak.solaris.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.pawelczak.solaris.persistence.model.Gallery;

@Service
public class DbCleaner {

    @PersistenceContext
    private EntityManager entityManager;

 
	@Transactional
    public void clean() {
    	
    	deleteAll(Gallery.class);
    }
    
    public void deleteAll(Class<?> clazz) {
        Query query = entityManager.createQuery("delete from " + clazz.getName());
        query.executeUpdate();
    }
    
    public void deleteAllSql(String tableName) {
        Query query = entityManager.createNativeQuery("delete from " + tableName);
        query.executeUpdate();
    }
    
}