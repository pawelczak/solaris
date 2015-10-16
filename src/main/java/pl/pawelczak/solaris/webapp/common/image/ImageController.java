package pl.pawelczak.solaris.webapp.common.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ImageController {

	
	@Value("${photo.image.src}")
	private String imagePath;
	
	
	//------------------------ LOGIC --------------------------

	/**
	 * Normally pathVariable truncates fileName that contains "." inside e.g. fileName.json.
	 * Using {fileName:.+} solves that issue.
	 */
	@RequestMapping(value = "/image/{fileName:.+}", method = RequestMethod.GET)
	public void image(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
		InputStream in = getImageFileStream(fileName);
		
		try {
			response.setContentType("image/jpeg");
			IOUtils.copy(in, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	
	//------------------------ PRIVATE --------------------------
	
	private InputStream getImageFileStream(String fileName)  throws IOException {
		File file = new File(imagePath + fileName);
		return new FileInputStream(file);
	}
}
