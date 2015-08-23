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
.service("photoService", function($http, photoListApiUrl, photoAddApiUrl, photoEditApiUrl, photoEditImageApiUrl, photoDeleteApiUrl) {
	
	return {
		
		
		/* Get list of all photos
		 * 
		 * return promise function
		 */
		getList: function() {
			return $http.get(contextPath + photoListApiUrl);
		},
		
		/* Add photo
		 * 
		 * return promise function
		 */
		add: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + photoAddApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded' },
			    transformRequest: transformRequest,
				data: reqData
			});
		},
		
		/* Edit photo
		 * 
		 * return promise function
		 */
		edit: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + photoEditApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: transformRequest,
				data: reqData
			});
		},
		
		/* Edit photo Image
		 * 
		 * return promise function
		 */
		editImage: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + photoEditImageApiUrl,
				headers: {'Content-Type': undefined},
			    transformRequest: function transformEditPhoto(data) {
			        var formData = new FormData();

			        formData.append("photoId", angular.toJson(data.photoId));
			        formData.append("imageSrc", data.imageSrc);
			        
			        return formData;
				},
				data: reqData
			});
		},
		
		/* Remove photo
		 * 
		 * return promise function
		 */
		remove: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + photoDeleteApiUrl,
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