angular.module("solarisAdmin")
.filter("selectedGallery", function() {

	return function(data, selectedGalleryId) {
		
		//return data;
		if (angular.isArray(data) && selectedGalleryId != undefined) {
			var results = [];
				
			for (var i = 0, length = data.length; i < length; i += 1) {
				
				if (data[i].gallery.id == selectedGalleryId) {
					results.push(data[i]);	
				}
			}
			
			return results;
		} else {
			return data;
		}
	};
	
});

