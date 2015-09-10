package pl.pawelczak.solaris.webapp.common.photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;


@Service
public class PhotoServiceBaseImpl implements PhotoServiceBase {

	
	
	private PhotoRepository photoRepositoryBase;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAll() {
		return photoRepositoryBase.findAll();
	}
	
	public Iterable<Photo> findAll(List<Long> ids) {
		
		return photoRepositoryBase.findAll(ids);
	}
	
	public Photo findOne(Long id) {
		return photoRepositoryBase.findOne(id);
	}
	
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setPhotoRepositoryBase(PhotoRepository photoRepository) {
		this.photoRepositoryBase = photoRepository;
	}
}
