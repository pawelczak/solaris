package pl.pawelczak.solaris.webapp.site.api.photo;

import java.util.List;




public class PhotoApiModel {


	private Gallery gallery;
	
	private List<Photo> photos;
	
	//------------------------ GETTERS --------------------------
	
	public Gallery getGallery() {
		return this.gallery;
	}
	
	public List<Photo> getPhotos() {
		return this.photos;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
	
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
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
		
		
		//------------------------ BUILDER --------------------------
		
		public static Builder getBuilder(Long galleryId) {
			return new Builder(galleryId);
		}
		
		public static class Builder {
			
			private Gallery gallery;
			
			public Builder(Long galleryId) {
				this.gallery = new Gallery();
				gallery.setId(galleryId);
			}
			
			public Builder name(String name) {
				gallery.setName(name);
				return this;
			}
			
			public Gallery build() {
				return gallery;
			}
		}
	}
	
	public static class Photo {
		
		
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
	    	
	    	
	    	private Photo photo;
	    	
	    	public Builder(Long photoId) {
	    		photo = new Photo();
	    		photo.setId(photoId);
	    	}
	    	
	    	public Builder title(String title) {
	    		photo.setTitle(title);
	    		return this;
	    	}
	    	
	    	public Builder description(String description) {
	    		photo.setDescription(description);
	    		return this;
	    	}
	    	
	    	public Builder imageSrc(String imageSrc) {
	    		photo.setImageSrc(imageSrc);
	    		return this;
	    	}
	    	
	    	public Photo build() {
	    		return photo;
	    	}
	    	
	    }
	}
	
	//------------------------ BUILDER --------------------------
    
    public static Builder getBuilder(Gallery gallery, List<Photo> photos) {
        return new Builder(gallery, photos);
    }
	
	public static class Builder {
		
		private PhotoApiModel photoApiModel;
		
		public Builder(Gallery gallery, List<Photo> photos) {
			photoApiModel = new PhotoApiModel();
			photoApiModel.setGallery(gallery);
			photoApiModel.setPhotos(photos);
		}
		
		public PhotoApiModel build() {
			return photoApiModel;
		}
		
	}
	
}
