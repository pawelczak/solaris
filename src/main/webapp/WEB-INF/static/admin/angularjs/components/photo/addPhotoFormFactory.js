/*
 * Simple DTO addPhotoForm 
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.factory("addPhotoFormFactory", function() {
	
	return {
		
		create: function() {
			var form = {};
			
			createForm(form);
			
			return form;
		},
		
		reset: function(form) {
			createForm(form);
		}
		
	};
	
	function createForm(form) {
		form.title = "";
		form.galleryId = 0;
		form.description = "";
		form.imageSrc = "";
	}
});
