angular.module("solarisSite")
.controller("galleryController", ["$scope", "$sce", "galleryService", function($scope, $sce, galleryService) {
	
	//TODO This should be in properties file
	$scope.contextPath = contextPath + "/";
		
	$scope.searchPhrase = "";
	$scope.data = {};
	
	$scope.loadGalleries = function() {
		galleryService.findAll().success(function(data) {
			 $scope.data.galleries = data;
		});
	};
	
	$scope.loadGalleries();
	
	
	
	$scope.getBlockClassName = function(index) {
		
		switch(index % 3) {
			
			case 0:
				return "left-block";
			break;
			case 1:
				return "middle-block";
			break;
			case 2:
				return "right-block";
			break;
			
			default:
				break;
		
		}
		
	};
	
	$scope.highlight = function(text, search) {
	    if (!search) {
	        return $sce.trustAsHtml(text);
	    }
	    return $sce.trustAsHtml(text.replace(new RegExp(search, 'gi'), '<span class="highlightedText">$&</span>'));
	};
	
	
}]);

