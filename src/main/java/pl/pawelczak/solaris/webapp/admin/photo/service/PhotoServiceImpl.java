package pl.pawelczak.solaris.webapp.admin.photo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

	
	private PhotoRepository photoRepository;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAll() {
		return photoRepository.findAll();
	}
	
	public Photo findOne(Long id) {
		return photoRepository.findOne(id);
	}
	
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
}
