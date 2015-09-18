/*
 * Service for photo api
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.constant("photoListApiUrl", "/admin/api/photo/list")
.constant("photoAddApiUrl", "/admin/api/photo/add")
.constant("photoEditApiUrl", "/admin/api/photo/edit")
.constant("photoEditImageApiUrl", "/admin/api/photo/editImage")
.constant("photoDeleteApiUrl", "/admin/api/photo/delete")
.service("photoService", ["$http", "photoListApiUrl", "photoAddApiUrl", "photoEditApiUrl", "photoEditImageApiUrl", "photoDeleteApiUrl",
      function($http, photoListApiUrl, photoAddApiUrl, photoEditApiUrl, photoEditImageApiUrl, photoDeleteApiUrl) {

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
		
		
		/* Finds list of all photos
		 * 
		 * return promise function
		 */
		findAll: function() {
			return $http.get(contextPath + photoListApiUrl);
		},
		
		/* Add photo
		 * 
		 * return promise function
		 */
		add: function(reqData) {
			
			return $http.post(contextPath + photoAddApiUrl, reqData, config);
		},
		
		/* Edit photo
		 * 
		 * return promise function
		 */
		edit: function(reqData) {
			
			return $http.post(contextPath + photoEditApiUrl, reqData, config);
		},
		
		/* Edit photo Image
		 * 
		 * return promise function
		 */
		editImage: function(reqData) {
			
			return $http.post(contextPath + photoEditImageApiUrl, reqData,
				{
					headers: {'Content-Type': undefined},
				    transformRequest: function transformEditPhoto(data) {
				        var formData = new FormData();
	
				        formData.append("photoId", angular.toJson(data.photoId));
				        formData.append("imageSrc", data.imageSrc);
				        
				        return formData;
					}
				}
			);
				
		},
		
		/* Remove photo
		 * 
		 * return promise function
		 */
		remove: function(reqData) {
			
			return $http.post(contextPath + photoDeleteApiUrl, reqData, config);
		}
		
	};
	

	
}]);
