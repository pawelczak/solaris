angular.module("solarisSite")
.controller("photoViewerController", ["$scope", "$routeParams", "photoService", function($scope, $routeParams, photoService) {
	
	//TODO This should be in properties file
	$scope.contextPath = "/solaris/";
	
	$scope.data = {};
	
	$scope.data.galleryId = $routeParams.galleryId;
	$scope.data.photoId = $routeParams.photoId;
	$scope.data.photos = [];
	
	$scope.selectedPhotoIndex = 0;
	
	$scope.loadPhotos = function() {
		
		photoService.findAllByGalleryId($scope.data.galleryId)
		.success(function(data) {
			
			$scope.data.photos = data.photos;
			
			$scope.updateSelectedPhotoIndex();
		}); 
		
	};
	
	$scope.updateSelectedPhotoIndex = function() {
		
		for(var i = 0, length = $scope.data.photos.length; i < length; i += 1) {
			if ($scope.data.photos[i].id == $scope.data.photoId) {
				$scope.selectedPhotoIndex = i;
			}
		}
		
	}
	
	$scope.loadPhotos();
	
	
	$scope.nextPhoto = function() {
		$scope.selectedPhotoIndex += 1;
	};
	
	$scope.prevPhoto = function() {
		$scope.selectedPhotoIndex -= 1;
	};
	
	$scope.nextPhotoButtonClassName = function() {
		return $scope.selectedPhotoIndex < $scope.data.photos.length -1 ? "display-block" : "display-none";
	}

	$scope.prevPhotoButtonClassName = function() {
		return $scope.selectedPhotoIndex > 0 ? "display-block" : "display-none";
	}
	
}]);
