package pl.pawelczak.solaris.webapp.common.photo;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Photo;

public interface PhotoServiceBase {

	
	public List<Photo> findAll();
	
	public Iterable<Photo> findAll(List<Long> ids);
	
	public Photo findOne(Long id);
	
}
