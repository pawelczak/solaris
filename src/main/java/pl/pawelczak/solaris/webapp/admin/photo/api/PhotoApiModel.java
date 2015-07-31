package pl.pawelczak.solaris.webapp.admin.photo.api;

public class PhotoApiModel {

	
	private Long id;
	
	private Gallery gallery;
	
	private String title;
	
	
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
		
		public PhotoApiModel build() {
			return photo;
		}
		
	}
}
