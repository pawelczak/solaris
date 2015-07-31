angular.module("solarisAdmin")
	
.controller("solarisAdminPhotoCtrl", function($scope, photoService) {
	
	
	
	$scope.addGalleryWindowVisible = false;
	
	
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
	
	
	//------------------------ ADD PHOTO --------------------------
	
	$scope.addPhoto = function(addPhotoForm) {
		
		
		photoService.add({
			title: addPhotoForm.title
		}).success(function(addedPhoto) {
			
			addPhotoForm.title = "";
			
			addPhotoForm.modified = true;
			
			$scope.data.photos.push(addedPhoto);
			
			$scope.hideAddPhotoWindow();
		});
		
	};
	
	/* Add photo form */
	$scope.showAddPhotoWindow = function() {
		$scope.addPhotoWindowVisible = true;
	}
	
	$scope.hideAddPhotoWindow = function() {
		$scope.addPhotoWindowVisible = false;
	}
	
	$scope.getAddPhotoWindowClass = function() {
		return $scope.addPhotoWindowVisible ? "display-block" : "display-none"; 
	}
	
	
});

