angular.module("pagingFilters", [])
.filter("range", function($filter) {
	return function(data, page, size) {
		if (angular.isArray(data) && angular.isNumber(page) && angular.isNumber(size)) {
			var startIndex = (page - 1) * size;
			if (data.length < startIndex) {
				return [];
			} else {
				return $filter("limitTo")(data.splice(startIndex), size);
			}
		} else {
			return data;
		}
	};
})
.filter("pageCount", function() {
	return function(data, size) {
		if (angular.isArray(data)) {
			var results = [];
				
			for (var i = 0, length = Math.ceil(data.length/size); i < length; i += 1) {
				results.push(i);
			}
			
			return results;
		} else {
			return data;
		}
	};
});
