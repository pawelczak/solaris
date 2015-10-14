angular.module("solarisAdmin")
	
.controller("adminController", ["$scope", function($scope) {
	
	//TODO This should be in properties file
	$scope.contextPath = "/solaris/";
	
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
	
	
	
	
}]);

