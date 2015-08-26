package pl.pawelczak.solaris.webapp.admin.photo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pl.pawelczak.solaris.webapp.common.image.ImageService;
import pl.pawelczak.solaris.webapp.common.image.ImageUploadException;

@Service
public class PhotoImageServiceImpl implements PhotoImageService{

	
	@Value("${photo.image.src}")
	private String filePath;
	
	private ImageService imageService;
	
	
	//------------------------ GETTERS --------------------------
	
	public String getImageFilePath() {
		return this.filePath;
	}
	
	
	//------------------------ LOGIC --------------------------
	
	public String save(Long photoId, MultipartFile image) throws ImageUploadException {
		
		String imageSrc = createPhotoFileName(photoId);
		
		try {
			if (!image.isEmpty()) {
				
				if (!image.getContentType().equals("image/jpeg")) {
					throw new ImageUploadException("Nieakceptowane pliki jpeg");
				}
				
				imageService.save(filePath + imageSrc, image);

			}
		} catch (ImageUploadException e) {
			
		}
		
		return imageSrc;
	}
	
	public void delete(Long photoId) {

		imageService.delete(filePath + createPhotoFileName(photoId));
	}

	
	//------------------------ PRIVATE --------------------------
	
	private String createPhotoFileName(Long photoId) {
		return photoId + ".jpg";
	}
	
	
	//------------------------ SETTERS --------------------------
	
	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}
}
