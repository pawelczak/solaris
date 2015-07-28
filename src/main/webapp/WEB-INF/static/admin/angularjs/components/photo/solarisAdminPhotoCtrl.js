angular.module("solarisAdmin")
	
.controller("solarisAdminPhotoCtrl", function($scope, photoService) {
	
	
	
	$scope.initDataScope = function() {
		
		if (typeof $scope.data !== "Object") {
			$scope.data = {};
		}
		
		if (typeof $scope.data.photos !== "array") {
			$scope.loadPhotos();
		}
		
	}
	
	
	$scope.loadPhotos = function() {
		
		photoService.getList().success(function(data) {
			$scope.data.photos = data;
		});
	};
	

	
	$scope.initDataScope();
	
});

