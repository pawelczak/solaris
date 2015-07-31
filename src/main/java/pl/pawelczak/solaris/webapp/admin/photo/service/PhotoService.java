package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;

public interface PhotoService {

	
	public List<Photo> findAll();
	
	public Photo findOne(Long id);
	
	public Photo add(PhotoForm photoForm);
	
}
