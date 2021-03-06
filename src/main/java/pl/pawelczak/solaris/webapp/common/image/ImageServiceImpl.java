package pl.pawelczak.solaris.webapp.common.image;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

	
	
	//------------------------ LOGIC --------------------------
	
	public void save(String filename, MultipartFile image) throws ImageUploadException {
		
		try {
			File file = new File (filename);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		} catch(IOException e) {
			throw new ImageUploadException("photo.upload.fail");
		}
		
	}
	
	public void delete(String filename) {
		
		try {
			
			File file = new File(filename);
			
			if(file.delete()){
				//TODO
				System.out.println(file.getName() + " is deleted!");
			} else {
				//TODO
				System.out.println("Delete operation is failed.");
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
