package pl.pawelczak.solaris.webapp.site.api.photo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;
import pl.pawelczak.solaris.persistence.repository.PhotoRepository;
import pl.pawelczak.solaris.webapp.common.photo.PhotoServiceBaseImpl;

@Service
public class PhotoApiServiceImpl extends PhotoServiceBaseImpl implements PhotoApiService {

	
	@Autowired
	private PhotoRepository photoRepository;
	
	
	//------------------------ LOGIC --------------------------
	
	public List<Photo> findAllByGalleryId(Long galleryId) {
		return photoRepository.findAllByGalleryId(galleryId);
	}
}
