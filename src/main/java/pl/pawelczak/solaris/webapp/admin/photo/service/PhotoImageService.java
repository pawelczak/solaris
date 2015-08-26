package pl.pawelczak.solaris.webapp.admin.photo.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoImageService {

	public String save(Long photoId, MultipartFile image);
	
	public void delete(Long photoId);
	
}
