package pl.pawelczak.solaris.webapp.site.api.gallery;



public class GalleryApiModel {

	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String featuredImageSrc;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getFeaturedImageSrc() {
		return this.featuredImageSrc;
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
	
	public void setFeaturedImageSrc(String featuredImageSrc) {
		this.featuredImageSrc = featuredImageSrc;
	}
	
}
