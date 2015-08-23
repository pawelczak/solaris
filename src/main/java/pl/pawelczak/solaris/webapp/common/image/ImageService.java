package pl.pawelczak.solaris.webapp.common.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	
	public Boolean save(String filename, MultipartFile image);
	
}
