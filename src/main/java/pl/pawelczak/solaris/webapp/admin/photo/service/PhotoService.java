package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Photo;

public interface PhotoService {

	
	public List<Photo> findAll();
	
	public Photo findOne(Long id);
}
