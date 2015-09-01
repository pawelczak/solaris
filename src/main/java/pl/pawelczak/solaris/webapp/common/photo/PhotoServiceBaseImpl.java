package pl.pawelczak.solaris.webapp.common.photo;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;


@Service
public class PhotoServiceBaseImpl implements PhotoServiceBase {

	
	
	private PhotoRepository photoRepository;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAll() {
		return photoRepository.findAll();
	}
	
	public Iterable<Photo> findAll(List<Long> ids) {
		
		return photoRepository.findAll(ids);
	}
	
	public Photo findOne(Long id) {
		return photoRepository.findOne(id);
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setPhotoRepository(PhotoRepository photoRepository) {
		this.photoRepository = photoRepository;
	}
}
