anguler.module("solarisSite")
.controller("photoViewer", function($scope, $routeParams) {
	
	$scope.data = {};
	
	$scope.data.galleryId = $routeParams.galleryId;
	$scope.data.photoId = $routeParams.photoId;
	
	
	
});
