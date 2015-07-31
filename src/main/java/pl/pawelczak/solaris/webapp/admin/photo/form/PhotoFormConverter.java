package pl.pawelczak.solaris.webapp.admin.photo.form;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Photo;

@Service
public class PhotoFormConverter {

	
	
	//------------------------ LOGIC --------------------------
	
	public Photo convert(PhotoForm photoForm) {
		
		Photo photo = Photo.getBuilder(photoForm.getGalleryId())
							.title(photoForm.getTitle())
							.build();
		
		return photo;
	}
	
}
