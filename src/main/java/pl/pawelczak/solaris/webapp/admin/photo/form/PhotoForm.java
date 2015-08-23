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

	private String description;
	
	private String imageSrc;
	
	
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
	
	public String getDescription() {
		return this.description;
	}
	
	public String getImageSrc() {
		return this.imageSrc;
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
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setImageSrc(String src) {
		this.imageSrc = src;
	}
	
}
