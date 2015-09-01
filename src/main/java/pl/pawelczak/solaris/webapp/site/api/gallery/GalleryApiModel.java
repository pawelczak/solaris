package pl.pawelczak.solaris.webapp.site.api.gallery;





public class GalleryApiModel {

	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String featuredImageSrc;

	
	//------------------------ CONSTRUCTORS --------------------------
	
	
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
	
	
	//------------------------ BUILDER --------------------------
	
    public static Builder getBuilder(Long id) {
        return new Builder(id);
    }
	
	public static class Builder {
		
		private GalleryApiModel galleryApiModel;
		
		public Builder(Long id) {
			galleryApiModel = new GalleryApiModel();
			galleryApiModel.id = id;
		}
		
		public Builder name(String name) {
			galleryApiModel.name = name;
			return this;
		}
		
		public Builder description(String description) {
			galleryApiModel.description = description;
			return this;
		}
		
		public Builder featuredImageSrc(String featuredImageSrc) {
			galleryApiModel.featuredImageSrc = featuredImageSrc;
			return this;
		}
		
		public GalleryApiModel build() {
			return galleryApiModel;
		}
		
	}
}
