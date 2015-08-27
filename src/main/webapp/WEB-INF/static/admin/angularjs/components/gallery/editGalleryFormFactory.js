/*
 * Simple DTO form for editing gallery
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.factory("editGalleryFormFactory", function() {
	
	return {
		
		create: function() {
			var form = {};
			
			createForm(form);
			
			return form;
		},
		
		reset: function(form) {
			createForm(form);
		},
		
		set: function(form, gallery) {
			form.id = gallery.id;
			form.name = gallery.name;
			form.description = gallery.description;
			form.visible = gallery.visible;
		}
		
	};
	
	function createForm(form) {
		form.id = 0;
		form.name = "";
		form.description = "";
		form.visible = false;
	}
});
