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
	
	
	public Long getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
