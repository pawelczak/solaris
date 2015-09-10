package pl.pawelczak.solaris.webapp.site.api.photo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import pl.pawelczak.solaris.persistence.model.Gallery;
import pl.pawelczak.solaris.persistence.model.Photo;

public class PhotoApiModelConverterTest {

	
	
	private PhotoApiModelConverter photoApiModelConverter = new PhotoApiModelConverter();
	
	
	//------------------------ TEST --------------------------
	
	@Test
	public void convert_gallery() {
		
		
		//given
		Long galleryId = 2l;
		String galleryName = "Gr8 gallery name!";
		Gallery gallery = Gallery.getBuilder(galleryName).build();
		Whitebox.setInternalState(gallery, "id", galleryId);
		
		
		//execute
		PhotoApiModel.Gallery convertedGallery = photoApiModelConverter.convert(gallery);
		
		
		//assert
		assertEquals(galleryId, convertedGallery.getId());
		assertEquals(galleryName, convertedGallery.getName());
	}
	
	@Test
	public void convert_photo_list() {
		
		
		//given
		Long galleryOneId = 1l;
		Long galleryTwoId = 2l;
		Long photoOneId = 10l;
		Long photoTwoId = 11l;
		String photoOneTitle = "photo one title";
		String photoOneDesc = "Photo one description.";
		String photoOneImageSrc = "photo/one/file/path";
		
		Photo photoOne = Photo.getBuilder(galleryOneId).title(photoOneTitle).description(photoOneDesc).imageSrc(photoOneImageSrc).build();
		Whitebox.setInternalState(photoOne, "id", photoOneId);
		
		Photo photoTwo = Photo.getBuilder(galleryTwoId).build();
		Whitebox.setInternalState(photoOne, "id", photoTwoId);
		
		List<Photo> photoList = new ArrayList<Photo>();
		
		photoList.add(photoOne);
		photoList.add(photoTwo);
		
		
		//execute
		List<PhotoApiModel.Photo> convertedPhotoList = photoApiModelConverter.convert(photoList);
		
		
		//assert
		assertEquals(photoList.size(), convertedPhotoList.size());
		
		assertEquals(photoList.get(0).getId(), convertedPhotoList.get(0).getId());
		assertEquals(photoList.get(0).getTitle(), convertedPhotoList.get(0).getTitle());
		assertEquals(photoList.get(0).getDescription(), convertedPhotoList.get(0).getDescription());
		assertEquals(photoList.get(0).getImageSrc(), convertedPhotoList.get(0).getImageSrc());
		
		assertEquals(photoList.get(1).getId(), convertedPhotoList.get(1).getId());
		assertEquals(photoList.get(1).getTitle(), convertedPhotoList.get(1).getTitle());
		assertEquals(photoList.get(1).getDescription(), convertedPhotoList.get(1).getDescription());
		assertEquals(photoList.get(1).getImageSrc(), convertedPhotoList.get(1).getImageSrc());
	}
	
	

}
