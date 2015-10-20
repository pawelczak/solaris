angular.module("solarisAdmin")
.constant("PHOTO_ORDER_PROPERTY", "title")
.constant("PHOTO_ORDER_PROPERTY_FILTER_VALUES", ["title", "description"])
.constant("PHOTO_PAGE_SIZE", 10)
.constant("PHOTO_PAGE_SIZE_FILTER_VALUES", [5, 10, 20, 50])
.controller("photoListController", ["$scope", "PHOTO_ORDER_PROPERTY", "PHOTO_ORDER_PROPERTY_FILTER_VALUES", "PHOTO_PAGE_SIZE", "PHOTO_PAGE_SIZE_FILTER_VALUES",
            function($scope, PHOTO_ORDER_PROPERTY, PHOTO_ORDER_PROPERTY_FILTER_VALUES, PHOTO_PAGE_SIZE, PHOTO_PAGE_SIZE_FILTER_VALUES) {
	

	
	//------------------------ VARIABLES --------------------------
	
	$scope.selectedPage = 1;
	$scope.numberOfPages = 1;
	
	//------------------------ ORDER --------------------------
	
	$scope.photoOrderByPropertyFilterValues = PHOTO_ORDER_PROPERTY_FILTER_VALUES;
	
	/*
	 * If photoOrderByProperty from adminController is not defined,
	 * assigned it const value
	 */
	if ($scope.photoOrderByProperty == null) {
		$scope.photoOrderByProperty = PHOTO_ORDER_PROPERTY;
	} 
	
	$scope.setOrderBy = function(property) {
		$scope.selectedPage = 1;
		$scope.photoOrderByProperty = property;
		
		//TODO move to parent adminController
		$scope.$parent.$parent.photoOrderByProperty = property;
	}
	
	
	//------------------------ PAGING --------------------------

	$scope.photoPageSizeFilterValues = PHOTO_PAGE_SIZE_FILTER_VALUES;
	
	/*
	 * If photoPageSize from adminController is not defined,
	 * assigned it const value
	 */
	if ($scope.photoPageSize == null) {
		$scope.photoPageSize = PHOTO_PAGE_SIZE;
	} 

	
	$scope.selectPage = function(page) {
		$scope.selectedPage = page;
	};
	
	$scope.selectPreviousPage = function () {
		$scope.selectPage($scope.selectedPage - 1);
	};
	
	$scope.selectNextPage = function () {
		$scope.selectPage($scope.selectedPage + 1);
	};
	
	$scope.getPageClass = function(page) {
		return $scope.selectedPage == page ? "btn-primary" : "";
	};
	
	$scope.setPageSize = function(size) {
		$scope.selectedPage = 1;
		$scope.photoPageSize = size;
		//TODO move to parent adminController
		$scope.$parent.$parent.photoPageSize = size;
	};
	
	$scope.isPreviousButtonDisabled = function() {
		return $scope.selectedPage == 1;
	};
	
	$scope.isNextButtonDisabled = function() {
		$scope.numberOfPages = $scope.getNumberOfPages();
		
		return $scope.selectedPage == $scope.numberOfPages;
	};
	
	$scope.getNumberOfPages = function() {
		
		if ($scope.data.photos !== undefined && $scope.data.photos.length > 0) {
			return Math.ceil($scope.data.photos.length/$scope.photoPageSize);
		} else {
			return 1;
		}
		
	};
	
	
}]);

