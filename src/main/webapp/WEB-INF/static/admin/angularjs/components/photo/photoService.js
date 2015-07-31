/*
 * Service for photo api
 * 
 * @author Łukasz Pawełczak
 * 
 */
angular.module("solarisAdmin")
.constant("photoListApiUrl", "/admin/api/photo/list")
.constant("photoAddApiUrl", "/admin/api/photo/add")
.service("photoService", function($http, photoListApiUrl, photoAddApiUrl) {
	
	return {
		
		
		/* Get list of all photos
		 * 
		 * return promise function
		 */
		getList: function() {
			return $http.get(contextPath + photoListApiUrl);
		},
		
		/* Add gallery
		 * 
		 * return promise function
		 */
		add: function(reqData) {
			
			return $http({
				method: "POST",
				url: contextPath + photoAddApiUrl,
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			    transformRequest: transformRequest,
				data: reqData
			});
		},
		
		/* Edit gallery
		 * 
		 * return promise function
		 */
		edit: function() {},
		
		/* Remove gallery
		 * 
		 * return promise function
		 */
		remove: function() {}
		
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