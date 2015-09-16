angular.module("solarisSite")
.constant("galleryListApiUrl", "/api/galleries")
.service("galleryService", ["$http", "galleryListApiUrl", function($http, galleryListApiUrl) {
	
	return {
		
		findAll: function() {
			
			return $http.get(contextPath + galleryListApiUrl);
		}

	};
	
}]);

