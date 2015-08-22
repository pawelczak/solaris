angular.module("solarisAdmin")
	
.controller("solarisAdminCtrl", function($scope) {
	
	
	$scope.selectedView = 1;
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
	
});

