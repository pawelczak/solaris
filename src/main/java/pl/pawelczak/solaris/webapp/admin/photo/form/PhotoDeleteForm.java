package pl.pawelczak.solaris.webapp.admin.photo.form;

import java.util.List;

public class PhotoDeleteForm {

	
	private List<Long> ids;
	
	
	//------------------------ GETTERS --------------------------
	
	public List<Long> getIds() {
		return ids;
	}
	
	
	//------------------------ SETTERS --------------------------
	
	public void setIds(List<Long> photoIds) {
		this.ids = photoIds;
	}
}
