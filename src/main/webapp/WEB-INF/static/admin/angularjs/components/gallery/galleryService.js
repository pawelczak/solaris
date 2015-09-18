/*
 * Service for gallery api
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.constant("galleryListApiUrl", "/admin/api/gallery/list")
.constant("galleryAddApiUrl", "/admin/api/gallery/add")
.constant("galleryEditApiUrl", "/admin/api/gallery/edit")
.constant("galleryDeleteApiUrl", "/admin/api/gallery/delete")
.factory("galleryService", ["$http", "galleryListApiUrl", "galleryAddApiUrl", "galleryEditApiUrl", "galleryDeleteApiUrl", function($http, galleryListApiUrl, galleryAddApiUrl, galleryEditApiUrl, galleryDeleteApiUrl) {
	
	var config = {
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		transformRequest: function transformRequest(obj) {
	        var str = [];
	        
	        for(var p in obj) {
	        	
	        	if (!(obj[p] instanceof Array)) {
	        		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
	        	} else {
	        		for (var element in obj[p]) {
	        			str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p][element]));
	        		}
	        	}
	        }
	        
	        return str.join("&");
	    }
	};
	
	return {
		
		/* Finds list of galleries
		 * 
		 * return promise function
		 */
		findAll: function() {
			
			return $http.get(contextPath + galleryListApiUrl);
		},
		
		
		/* Add gallery
		 * 
		 * return promise function
		 */
		add: function(reqData) {

			return $http.post(contextPath + galleryAddApiUrl, reqData, config);
		},
		
		
		/* Edit gallery
		 * 
		 * return promise function
		 */
		edit: function(reqData) {
			
			return $http.post(contextPath + galleryEditApiUrl, reqData, config);
		},
		
		
		/* Remove gallery
		 * 
		 * return promise function
		 */
		remove: function(reqData) {
			
			return $http.post(contextPath + galleryDeleteApiUrl, reqData, config);
		}
		
	};

	
	
}]);
