package pl.pawelczak.solaris.webapp.common.gallery;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Gallery;

public interface GalleryServiceBase {

	public List<Gallery> findAll();
	
	public Iterable<Gallery> findAll(List<Long> ids);
	
	public Gallery findOne(Long id);
}
