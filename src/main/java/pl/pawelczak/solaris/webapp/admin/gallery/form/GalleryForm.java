package pl.pawelczak.solaris.webapp.admin.gallery.form;


/**
 * 
 * DTO for add/edit gallery.
 * 
 * @author Łukasz Pawełczak
 *
 */
public class GalleryForm {

	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean visible;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Boolean getVisible() {
		return visible;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}


