angular.module("solarisSite")
.controller("photoController", function($scope, $routeParams, photoService) {
	
	//TODO This should be in properties file
	$scope.contextPath = "/solaris/";
	
	$scope.data = {};
	$scope.data.galleryId = $routeParams.galleryId;
	
	$scope.data.gallery = {};
	$scope.data.photos = {};
	
	$scope.loadPhotos = function() {
		
		photoService.findAllByGalleryId($scope.data.galleryId)
		.success(function(data) {
			
			$scope.data.gallery = data.gallery;
			$scope.data.photos = data.photos;
		}); 
		
	};
	
	$scope.loadPhotos();
	
	
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
	
});

