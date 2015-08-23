package pl.pawelczak.solaris.webapp.admin.photo.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import pl.pawelczak.solaris.webapp.common.image.ImageService;

@RunWith(MockitoJUnitRunner.class)
public class PhotoImageServiceImplTest {

	
	@Mock
	private ImageService imageService;
	
	private PhotoImageService photoImageService = new PhotoImageServiceImpl();


	//------------------------ CONFIG --------------------------
	
	@Before
	public void setup(){
		Whitebox.setInternalState(photoImageService, "filePath", "");
	}
	
	//------------------------ TESTS --------------------------
	
	@Test
	public void save() {
		
		
		//given
		Long photoId = 12l;
		MultipartFile image = Mockito.mock(MultipartFile.class);
		
		when(image.isEmpty()).thenReturn(false);
		when(image.getContentType()).thenReturn("image/jpeg");
		when(imageService.save(photoId + ".jpg", image)).thenReturn(true);
		((PhotoImageServiceImpl) photoImageService).setImageService(imageService);
		
		
		//execute
		String imgSrc = photoImageService.save(photoId, image);
		
		
		//assert
		verify(imageService, times(1)).save(photoId + ".jpg", image);
        verifyNoMoreInteractions(imageService);
		
	}

}
