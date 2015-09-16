angular.module("solarisSite")
.constant("photoListByGalleryId", function(galleryId) { return "/api/gallery/" + galleryId + "/photos";})
.service("photoService", ["$http", "photoListByGalleryId", function($http, photoListByGalleryId) {
	
	return {
		
		findAllByGalleryId: function(galleryId) {
			return $http.get(contextPath + photoListByGalleryId(galleryId));
		}
	};
	
	
}]);

