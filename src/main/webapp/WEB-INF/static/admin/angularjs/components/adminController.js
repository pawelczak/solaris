angular.module("solarisAdmin")
.controller("adminController", ["$scope", function($scope) {

	
	
	//TODO This should be in properties file
	$scope.contextPath = contextPath + "/";
	
	$scope.imageVisible = contextPath + "/static/admin/image/visible.png";
	
	$scope.getImageVisible = function() {
		return $scope.imageVisible;
	};
	
	$scope.selectView = function(view) {
		$scope.selectedView = view;
	}
	
	$scope.getSelecteViewClassName = function(view) {
		return $scope.selectedView == view ? "active" : "";
	}
	
	
	
	//------------------------ PHOTO VARIABLES --------------------------
	$scope.photoSelectedGalleryId;
	$scope.photoOrderByProperty
	$scope.photoPageSize;
	
	
	$scope.setPhotoSelectedGalleryId = function(galleryId) {
		$scope.photoSelectedGalleryId = galleryId;
	};
	
	$scope.setPhotoOrderBy = function(property) {
		$scope.photoOrderByProperty = property;
	};
	
	
}]);

