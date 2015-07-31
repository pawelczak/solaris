package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoForm;
import pl.pawelczak.solaris.webapp.admin.photo.form.PhotoFormConverter;

@Service
public class PhotoServiceImpl implements PhotoService {

	
	private PhotoRepository photoRepository;
	
	private PhotoFormConverter photoFormConverter;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAll() {
		return photoRepository.findAll();
	}
	
	public Photo findOne(Long id) {
		return photoRepository.findOne(id);
	}
	
	
	public Photo add(PhotoForm photoForm) {
		return photoRepository.save(photoFormConverter.convert(photoForm));
	}
	
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
	
	@Autowired
	public void setPhotoFormConverter(PhotoFormConverter photoFormConverter) {
		this.photoFormConverter = photoFormConverter;
	}
}
