package pl.pawelczak.solaris.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="photo")
public class Photo {

	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "gallery_id")
	private Long galleryId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image_src")
	private String imageSrc;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public Long getGalleryId() {
		return this.galleryId;
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
	
	
	//------------------------ BUILDER --------------------------
	
    public static Builder getBuilder(Long galleryId) {
        return new Builder(galleryId);
    }
	
	public static class Builder {
		
		private Photo photo;
		
		public Builder(Long galleryId) {
			photo = new Photo();
			photo.setGalleryId(galleryId);
		}
		
        public Builder title(String title) {
        	photo.title = title;
            return this;
        }
		
        public Builder description(String desc) {
        	photo.description = desc;
        	return this;
        }
        
        public Builder imageSrc(String imageSrc) {
        	photo.imageSrc = imageSrc;
        	return this;
        }
        
		public Photo build() {
			return photo;
		}
		
	}
}
