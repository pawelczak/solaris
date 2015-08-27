/*
 * Simple DTO for editing photos
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.factory("editPhotoFormFactory", function() {
	
	return {
		
		create: function() {
			var form = {};
			
			createForm(form);
			
			return form;
		},
		
		reset: function(form) {
			createForm(form);
		},
		
		set: function(form, photo) {
			form.id = photo.id;
			form.title = photo.title
			form.galleryId = photo.gallery.id;
			form.description = photo.description;
			form.imageSrc = photo.imageSrc;
		}
		
	};
	
	function createForm(form) {
		form.id = 0;
		form.title = "";
		form.galleryId = 0;
		form.description = "";
		form.imageSrc = "";
	}
});
