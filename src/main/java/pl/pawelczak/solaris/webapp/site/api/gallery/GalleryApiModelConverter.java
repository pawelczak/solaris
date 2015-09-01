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
		
		GalleryApiModel galleryApiModel = GalleryApiModel.getBuilder(gallery.getId())
															.name(gallery.getName())
															.description(gallery.getDescription())
															.featuredImageSrc(getFeaturedImageSrc(gallery))
															.build();
		
		
		return galleryApiModel;
	}
	
	private String getFeaturedImageSrc(Gallery gallery) {
		
		if (gallery.getPhotoList() != null && gallery.getPhotoList().size() > 0) {
			
			if (!gallery.getPhotoList().get(0).getImageSrc().isEmpty()) {
				return gallery.getPhotoList().get(0).getImageSrc();
			}
		}
		
		return "";
	}
	
	
}
