package pl.pawelczak.solaris.webapp.site.api.photo;




public class PhotoApiModel {

	
	private Long id;
	
	private String title;
	
	private String description;
	
	private String imageSrc;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
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
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setImageSrc(String src) {
		this.imageSrc = src;
	}
	
	
	//------------------------ BUILDER --------------------------
    
    public static Builder getBuilder(Long photoId) {
        return new Builder(photoId);
    }
	
	public static class Builder {
		
		private PhotoApiModel photo;
		
		public Builder(Long photoId) {
			photo = new PhotoApiModel();
			photo.setId(photoId);
		}
		
        public Builder title(String title) {
        	photo.title = title;
            return this;
        }
        
        public Builder description(String desc) {
        	photo.description = desc;
        	return this;
        }
        
        public Builder imageSrc(String src) {
        	photo.imageSrc = src;
        	return this;
        }
		
		public PhotoApiModel build() {
			return photo;
		}
		
	}
	
}
