/*
 * Simple DTO form for adding new gallery  
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.factory("addGalleryFormFactory", function() {
	
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
		form.name = "";
		form.description = "";
		form.visible = false;
	}
});
