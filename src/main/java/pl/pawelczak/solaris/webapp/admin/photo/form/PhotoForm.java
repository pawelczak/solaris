package pl.pawelczak.solaris.webapp.admin.photo.form;


/**
 * 
 * Simple DTO for Photo class
 * 
 * @author Łukasz Pawełczak
 *
 */
public class PhotoForm {

	
	private Long id;
	
	private Long galleryId;
	
	private String title;

	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return id;
	}

	public Long getGalleryId() {
		return galleryId;
	}

	public String getTitle() {
		return title;
	}
	

	//------------------------ SETTERS --------------------------

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setGalleryId(Long galleryId) {
		this.galleryId = galleryId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
