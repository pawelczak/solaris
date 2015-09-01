package pl.pawelczak.solaris.webapp.site.api.photo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import pl.pawelczak.solaris.persistence.model.Photo;

public class PhotoApiModelConverterTest {

	
	
	private PhotoApiModelConverter photoApiModelConverter = new PhotoApiModelConverter();
	
	
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
		List<PhotoApiModel> photoApiModelList = photoApiModelConverter.convert(photoList);
		
		
		//assert
		assertEquals(photoList.size(), photoApiModelList.size());
		
		assertEquals(photoList.get(0).getId(), photoApiModelList.get(0).getId());
		assertEquals(photoList.get(0).getTitle(), photoApiModelList.get(0).getTitle());
		assertEquals(photoList.get(0).getDescription(), photoApiModelList.get(0).getDescription());
		assertEquals(photoList.get(0).getImageSrc(), photoApiModelList.get(0).getImageSrc());
		
		assertEquals(photoList.get(1).getId(), photoApiModelList.get(1).getId());
		assertEquals(photoList.get(1).getTitle(), photoApiModelList.get(1).getTitle());
		assertEquals(photoList.get(1).getDescription(), photoApiModelList.get(1).getDescription());
		assertEquals(photoList.get(1).getImageSrc(), photoApiModelList.get(1).getImageSrc());
	}
	
	

}
