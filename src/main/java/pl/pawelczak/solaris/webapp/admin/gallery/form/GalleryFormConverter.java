package pl.pawelczak.solaris.webapp.admin.gallery.form;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;


@Service
public class GalleryFormConverter {

	
	//------------------------ LOGIC --------------------------
	
	public Gallery convert(GalleryForm galleryForm) {
		
		//TODO galleryForm.getName not null
		//introduce google predicate check
		
		Gallery gallery = Gallery.getBuilder(galleryForm.getName())
									.description(galleryForm.getDescription())
									.visible(galleryForm.getVisible())
									.build();
		
		return gallery;
	}
}
