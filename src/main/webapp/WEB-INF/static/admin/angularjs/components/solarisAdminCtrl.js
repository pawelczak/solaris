angular.module("solarisAdmin")
	
.controller("solarisAdminCtrl", function($scope) {
	
	$scope.imageVisible = contextPath + "/static/admin/image/visible.png";
	
	$scope.getImageVisible = function() {
		return $scope.imageVisible;
	};
});

