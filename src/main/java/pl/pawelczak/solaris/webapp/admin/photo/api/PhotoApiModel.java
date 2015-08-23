package pl.pawelczak.solaris.webapp.admin.photo.api;

public class PhotoApiModel {

	
	private Long id;
	
	private Gallery gallery;
	
	private String title;
	
	private String description;
	
	private String imageSrc;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public Gallery getGallery() {
		return this.gallery;
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
	
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
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
	
	
	//------------------------ INNER --------------------------
	
	public static class Gallery {
		
		private Long id;
		
		private String name;

		
		//------------------------ GETTERS --------------------------
		
		public Long getId() {
			return this.id;
		}
		
		public String getName() {
			return this.name;
		}
		
		
		//------------------------ SETTERS --------------------------
		
		public void setId(Long id) {
			this.id = id;
		} 
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	//------------------------ BUILDER --------------------------
	
    public static Builder getBuilder(Gallery gallery) {
        return new Builder(gallery);
    }
    
    public static Builder getBuilder(Long photoId, Gallery gallery) {
        return new Builder(photoId, gallery);
    }
	
	public static class Builder {
		
		private PhotoApiModel photo;
		
		public Builder(Gallery gallery) {
			photo = new PhotoApiModel();
			photo.setGallery(gallery);
		}
		
		public Builder(Long photoId, Gallery gallery) {
			photo = new PhotoApiModel();
			photo.setId(photoId);
			photo.setGallery(gallery);
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
