package pl.pawelczak.solaris.webapp.common.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	
	public void save(String filename, MultipartFile image);
	
}
