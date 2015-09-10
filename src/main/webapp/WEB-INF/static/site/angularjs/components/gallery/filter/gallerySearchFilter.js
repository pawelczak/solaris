angular.module("solarisSite")
.filter("gallerySearchFilter", function() {
	
	return function(data, searchPhrase) {
		
		if (angular.isArray(data) && angular.isString(searchPhrase)) {
			
			var results = [];
			
			for(var i = 0, length = data.length; i < length; i += 1) {
				if(data[i].name.toLowerCase().indexOf(searchPhrase.toLowerCase()) > -1) {
					results.push(data[i]);
				}
			}
			
			return results;
		} else {
			return data;
		}
		
	};
});

