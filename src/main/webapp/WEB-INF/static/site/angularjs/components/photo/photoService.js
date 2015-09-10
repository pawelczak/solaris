angular.module("solarisSite")
.constant("photoListByGalleryId", function(galleryId) { return "/api/gallery/" + galleryId + "/photos";})
.service("photoService", function($http, photoListByGalleryId) {
	
	return {
		
		findAllByGalleryId: function(galleryId) {
			return $http.get(contextPath + photoListByGalleryId(galleryId));
		},
		
		findOne: function(photoId) {
			//return $http.get();
		}
		
	};
	
	
});

