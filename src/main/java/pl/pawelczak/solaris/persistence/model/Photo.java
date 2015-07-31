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
		
		public Photo build() {
			return photo;
		}
		
	}
}
