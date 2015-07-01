package pl.pawelczak.solaris.webapp.admin.gallery.form;

import java.util.List;



public class GalleryDeleteForm {

	
	private List<Long> ids;
	
	
	//------------------------ GETTERS --------------------------
	
	public List<Long> getIds() {
		return ids;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setIds(List<Long> galleriesId) {
		this.ids = galleriesId;
	}
	
}
