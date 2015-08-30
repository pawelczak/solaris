package pl.pawelczak.solaris.webapp.site.api.gallery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.pawelczak.solaris.persistence.model.Gallery;

@Service
public class GalleryApiModelConverter {

	
	
	//------------------------ LOGIC --------------------------
	
	public List<GalleryApiModel> convert(List<Gallery> galleries) {
		
		List<GalleryApiModel> galleryApiModelList = new ArrayList<GalleryApiModel>(); 
		
		for(Gallery gallery : galleries) {
			galleryApiModelList.add(convert(gallery));
		}
		
		return galleryApiModelList;
	}

	
	//------------------------ PRIVATE --------------------------
	
	private GalleryApiModel convert(Gallery gallery) {
		
		GalleryApiModel galleryApiModel = new GalleryApiModel();
		
		galleryApiModel.setId(gallery.getId());
		galleryApiModel.setName(gallery.getName());
		galleryApiModel.setDescription(gallery.getDescription());
		galleryApiModel.setFeaturedImageSrc(getFeaturedImageSrc(gallery));
		
		return galleryApiModel;
	}
	
	private String getFeaturedImageSrc(Gallery gallery) {
		
		if (gallery.getPhotoList().size() > 0) {
			
			if (!gallery.getPhotoList().get(0).getImageSrc().isEmpty()) {
				return gallery.getPhotoList().get(0).getImageSrc();
			}
		}
		
		return "";
	}
	
	
}
