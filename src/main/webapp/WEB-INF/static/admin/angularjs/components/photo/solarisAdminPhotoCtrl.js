angular.module("solarisAdmin")
.controller("solarisAdminPhotoCtrl", ["$scope", "photoService", "galleryService", "addPhotoFormFactory", "editPhotoFormFactory",
      function($scope, photoService, galleryService, addPhotoFormFactory, editPhotoFormFactory) {
	
	
	$scope.addPhotoWindowVisible = false;
	$scope.editPhotoWindowVisible = false;
	$scope.deletePhotoWindowVisible = false;
	$scope.selectedPhotos = [];
	
	$scope.initDataScope = function() {
		
		//TODO
		if (typeof $scope.data !== "object") {
			$scope.data = {};
		}

		//TODO
		if (typeof $scope.data.photos !== "object") {
			$scope.loadPhotos();
		}
		
		//TODO
		/* Load gallery list */
		$scope.loadGalleryList();
		
	}
	
	
	$scope.loadPhotos = function() {
		
		photoService.findAll().success(function(data) {
			$scope.data.photos = data;
		});
	};
	

	//------------------------ GALLERY --------------------------
	
	//TODO prepare/use simple gallery list view
	$scope.loadGalleryList = function(callback) {
		
		if(typeof $scope.data.galleryList !== "object" || $scope.data.galleryList.length === 0) {
		
			galleryService.findAll().success(function(galleryList) {
				$scope.data.galleryList = galleryList;
				
				if(callback !== undefined && typeof callback === "function") {
					callback();
				}
			});
		} else {
			if(callback !== undefined && typeof callback === "function") {
				callback();
			}
		}
		
	};
	
	
	//------------------------ INIT --------------------------
	
	$scope.initDataScope();


	//------------------------ FILE UPLOAD --------------------------
	
    $scope.files = [];

    //listen for the file selected event
    $scope.$on("fileSelected", function (event, args) {
        $scope.$apply(function () {            
            //add the file object to the scope's files collection
            $scope.files = [args.file];
        });
    });
	
	//------------------------ ADD PHOTO --------------------------
    
	$scope.addPhotoForm = addPhotoFormFactory.create();
    
	$scope.addPhoto = function(addPhotoForm) {
		
		
		photoService.add({
			galleryId: addPhotoForm.galleryId,
			title: addPhotoForm.title,
			description: addPhotoForm.description,
			imageSrc: ""
		})
		.success(function(addedPhoto) {
			
			if ($scope.files[0] !== undefined) {
			
				photoService.editImage({
					photoId: addedPhoto.id,
					imageSrc: $scope.files[0]
				})
				.success(function(editedPhoto) {
					
					editedPhoto.modified = true;
					
					$scope.data.photos.push(editedPhoto);
					
					$scope.hideAddPhotoWindow();
					
				});
				
			} else {
				addedPhoto.modified = true;
				
				$scope.data.photos.push(addedPhoto);
				
				$scope.hideAddPhotoWindow();
			}
			
		});

	};
	
	/* Add photo form */
	$scope.showAddPhotoWindow = function() {
		
		addPhotoFormFactory.reset($scope.addPhotoForm);
		
		$scope.addPhotoWindowVisible = true;
	};
	
	$scope.hideAddPhotoWindow = function() {
		
		addPhotoFormFactory.reset($scope.addPhotoForm);
		
		//TODO clear file its should be done with angular directive
		if($) {
			$("#photoAddImgSrc").val("").change();
		}
		$scope.files = [];
		
		$scope.addPhotoWindowVisible = false;
	};
	
	$scope.getAddPhotoWindowClass = function() {
		return $scope.addPhotoWindowVisible ? "display-block" : "display-none"; 
	};
	
	
	//------------------------ EDIT PHOTO --------------------------

	$scope.editPhotoForm = editPhotoFormFactory.create();
	
	$scope.editPhoto = function(editPhotoForm) {
		
		photoService.edit({
			id: editPhotoForm.id,
			title: editPhotoForm.title,
			galleryId: editPhotoForm.galleryId,
			description: editPhotoForm.description,
			imageSrc: editPhotoForm.imageSrc
		})
		.success(function(editedPhoto) {
			
			if ($scope.files[0] !== undefined) {
				
			
				photoService.editImage({
					photoId: editPhotoForm.id,
					imageSrc: $scope.files[0]
				})
				.success(function(editedImagePhoto) {
					
					editedPhoto.imageSrc = editedImagePhoto.imageSrc;
					
					updateViewAfterSuccessEdit(editedPhoto)
				});
			
			} else {
				
				updateViewAfterSuccessEdit(editedPhoto)
			}
		});
		
		
		function updateViewAfterSuccessEdit(editPhoto) {
			$scope.selectedPhotos = [];
			
			for (var photo in $scope.data.photos) {
				
				if ($scope.data.photos[photo].id === editPhoto.id) {
					$scope.data.photos.splice(photo, 1);
				}
			}
			
			editPhoto.modified = true;
			
			$scope.data.photos.push(editPhoto);
			
			$scope.hideEditPhotoWindow();
		}
	};
	
	
	
	/* Edit Photo form */
	$scope.showEditPhotoWindow = function() {
		
		editPhotoFormFactory.set($scope.editPhotoForm, $scope.selectedPhotos[0]);
		
		$scope.editPhotoWindowVisible = true;

	}
	
	$scope.hideEditPhotoWindow = function() {
		
		editPhotoFormFactory.reset($scope.editPhotoForm);
		
		//TODO clear file its should be done with angular directive
		if($) {
			$("#photoAddImgSrc").val("").change();
		}
		$scope.files = [];
		
		$scope.editPhotoWindowVisible = false;
	};
	
	$scope.getEditPhotoWindowClass = function() {
		return $scope.editPhotoWindowVisible ? "display-block" : "display-none"; 
	};
	
	
	//------------------------ DELETE PHOTO --------------------------
	
	$scope.deletePhoto = function() {
		
		var ids = [];
		
		for(var i = 0; i < $scope.selectedPhotos.length; i++) {
			ids.push($scope.selectedPhotos[i].id);
		}

		photoService.remove({
			ids: ids
		})
		.success(function(removedPhotos) {
			
			$scope.selectedPhotos = [];
			
			for (var removedPhoto in removedPhotos) {
				
				for (var photo in $scope.data.photos) {
					
					if ($scope.data.photos[photo].id === removedPhotos[removedPhoto].id) {
						$scope.data.photos.splice(photo, 1);
					}
				}
			}
			
			$scope.hideDeletePhotoWindow();
		});
		
	};
	
	/* Delete photo form */
	$scope.showDeletePhotoWindow = function() {
		$scope.deletePhotoWindowVisible = true;
	};
	
	$scope.hideDeletePhotoWindow = function() {
		$scope.deletePhotoWindowVisible = false;
	};
	
	$scope.getDeletePhotoWindowClass = function() {
		return $scope.deletePhotoWindowVisible ? "display-block" : "display-none"; 
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

	$scope.getImageVisible = function(photo) {
		return !photo.visible ? "display-none" : "display-block";
	};
	
	$scope.getImageHidden = function(photo) {
		return photo.visible ? "display-none" : "display-block";
	};
	
}]);

