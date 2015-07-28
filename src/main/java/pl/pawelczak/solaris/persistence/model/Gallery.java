package pl.pawelczak.solaris.persistence.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="gallery")
public final class Gallery {

	private static final int MAX_LENGTH_NAME = 200;
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name", nullable=false, length = MAX_LENGTH_NAME)
	private String name;
	
	@Transient
	private List<Photo> photoList; 
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name="visible", nullable=false)
	private Boolean visible;
	
	
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Photo> getPhotoList() {
		return photoList;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Boolean getVisible() {
		return this.visible;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setId(Long id) {
		this.id = id;
	} 
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhotoList(List<Photo> photos) {
		this.photoList = photos;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	
	//------------------------ BUILDER --------------------------
	
    public static Builder getBuilder(String name) {
        return new Builder(name);
    }
	
	public static class Builder {
		
		private Gallery gallery;
		
		public Builder(String name) {
			gallery = new Gallery();
			gallery.setName(name);
			gallery.setVisible(false);
		}
		
        public Builder description(String desc) {
        	gallery.description = desc;
            return this;
        }
        
        public Builder visible(Boolean visible) {
        	gallery.visible = visible;
        	return this;
        }
		
		public Gallery build() {
			return gallery;
		}
		
	}
	
	
}
