/*
 * Service for photo api
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.constant("photoListApiUrl", "/admin/api/photo/list")
.service("photoService", function($http, photoListApiUrl) {
	
	return {
		
		
		/* Get list of all photos
		 * 
		 * return promise function
		 */
		getList: function() {
			return $http.get(contextPath + photoListApiUrl);
		},
		
		add: function() {},
		
		edit: function() {},
		
		remove: function() {}
		
	};
});