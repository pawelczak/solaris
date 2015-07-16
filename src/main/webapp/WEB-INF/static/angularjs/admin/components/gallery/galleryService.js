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
.factory("galleryService", function($http, galleryListApiUrl, galleryAddApiUrl, galleryEditApiUrl, galleryDeleteApiUrl) {
	
	
	return {
		
		/* Get list of galleries
		 * 
		 * return promise function
		 */
		getList: function() {
			
			return $http.get(contextPath + galleryListApiUrl);
		},
		
		
		/* Add gallery
		 * 
		 * return promise function
		 */
		add: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + galleryAddApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: transformRequest,
				data: reqData
			});
		},
		
		
		/* Edit gallery
		 * 
		 * return promise function
		 */
		edit: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + galleryEditApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: transformRequest,
				data: reqData
			});
		},
		
		
		/* Remove gallery
		 * 
		 * return promise function
		 */
		remove: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + galleryDeleteApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: transformRequest,
				data: reqData
			});
		}
		
	};
	
	
	function transformRequest(obj) {
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
	
	
});
