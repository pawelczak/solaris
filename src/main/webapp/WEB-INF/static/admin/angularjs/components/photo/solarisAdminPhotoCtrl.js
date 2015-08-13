angular.module("solarisAdmin")
	
.controller("solarisAdminPhotoCtrl", function($scope, photoService, galleryService) {
	
	
	$scope.addGalleryWindowVisible = false;
	$scope.editGalleryWindowVisible = false;
	$scope.deleteGalleryWindowVisible = false;
	$scope.selectedPhotos = [];
	
	$scope.initDataScope = function() {
		
		
		if (typeof $scope.data !== "object") {
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

	
	//------------------------ GALLERY --------------------------
	
	//TODO prepare/use simple gallery list view
	$scope.loadGalleryList = function() {
		
		if (typeof $scope.data.galleryList !== "array" || $scope.data.galleryList.length === 0) {
		
			galleryService.getList().success(function(galleryList) {
				$scope.data.galleryList = galleryList;
			});
		}
		
	};
	
	//------------------------ ADD PHOTO --------------------------
	
	$scope.addPhoto = function(addPhotoForm) {
		
		
		photoService.add({
			galleryId: addPhotoForm.galleryId,
			title: addPhotoForm.title
		}).success(function(addedPhoto) {
			
			addPhotoForm.title = "";
			
			addedPhoto.modified = true;
			
			$scope.data.photos.push(addedPhoto);
			
			$scope.hideAddPhotoWindow();
		});
		
	};
	
	/* Add photo form */
	$scope.showAddPhotoWindow = function() {
		
		/* Load gallery list */
		$scope.loadGalleryList();
		
		$scope.addPhotoWindowVisible = true;
	};
	
	$scope.hideAddPhotoWindow = function() {
		$scope.addPhotoWindowVisible = false;
	};
	
	$scope.getAddPhotoWindowClass = function() {
		return $scope.addPhotoWindowVisible ? "display-block" : "display-none"; 
	};
	
	
	//------------------------ EDIT PHOTO --------------------------
	
	$scope.editPhoto = function(editPhotoForm) {
		
		photoService.edit({
			id: editPhotoForm.id,
			title: editPhotoForm.title,
			galleryId: editPhotoForm.desc
		})
		.success(function(editedPhoto) {
			
			$scope.selectedPhotos = [];
			
			for (var photo in $scope.data.photos) {
				
				if ($scope.data.photos[photo].id === editedPhoto.id) {
					$scope.data.photos.splice(photo, 1);
				}
			}
			
			editedPhoto.modified = true;
			
			$scope.data.photos.push(editedGallery);
			
			$scope.hideEditPhotoWindow();
		});
		
	};
	
	/* Edit Photo form */
	$scope.showEditPhotoWindow = function() {
		
		//Edit photo form set values
		//ng-value directive doesn't work
		$("#editPhotoFormId").val($scope.selectedPhotos[0].id).trigger("change");
		$("#editPhotoFormTitle").val($scope.selectedPhotos[0].title).trigger("change");
		$("#editPhotoFormGalleryId").val($scope.selectedPhotos[0].galleryId).trigger("change");
		
		$scope.editPhotoWindowVisible = true;
	}
	
	$scope.hideEditPhotoWindow = function() {
		$scope.editPhotoWindowVisible = false;
	};
	
	$scope.getEditPhotoWindowClass = function() {
		return $scope.editPhotoWindowVisible ? "display-block" : "display-none"; 
	};
	
	//------------------------ MISC --------------------------
	
	$scope.setOrderBy = function(property) {
		$scope.selectedPage = 1;
		$scope.orderByProperty = property;
	}
	
	$scope.toggleSelection = function toggleSelection(photo) {
		var idx = $scope.selectedPhotos.indexOf(photo);

		if (idx > -1) {
			// is currently selected
			$scope.selectedPhotos.splice(idx, 1);
		} else {
			// is newly selected
			$scope.selectedPhotos.push(photo);
		}
	};
	
	/* Disables/Enables action buttons e.g. edit delete */ 
	$scope.isActionButtonDisabled = function() {
		return $scope.selectedPhotos.length < 1;
	}

	$scope.getImageVisible = function(gallery) {
		return !gallery.visible ? "display-none" : "display-block";
	};
	
	$scope.getImageHidden = function(gallery) {
		return gallery.visible ? "display-none" : "display-block";
	};
	
});

