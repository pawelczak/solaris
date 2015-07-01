angular.module("solarisAdmin")
	
.controller("solarisAdminCtrl", function($scope, $http) {
	
	$scope.imageVisible = contextPath + "/static/image/visible.png";
	
	$scope.getImageVisible = function() {
		return contextPath + "/static/image/visible.png";
	};
});

