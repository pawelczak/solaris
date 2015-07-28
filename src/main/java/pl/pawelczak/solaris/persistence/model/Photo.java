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

	@Column(name = "title")
	private String title;
	
	
	//------------------------ GETTERS --------------------------
	
	public Long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	//------------------------ BUILDER --------------------------
	
    public static Builder getBuilder() {
        return new Builder();
    }
	
	public static class Builder {
		
		private Photo photo;
		
		public Builder() {
			photo = new Photo();
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
