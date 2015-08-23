angular.module("solarisAdmin")
	
.controller("solarisAdminPhotoCtrl", function($scope, photoService, galleryService) {
	
	
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
		
		photoService.getList().success(function(data) {
			$scope.data.photos = data;
		});
	};
	

	//------------------------ GALLERY --------------------------
	
	//TODO prepare/use simple gallery list view
	$scope.loadGalleryList = function(callback) {
		
		if(typeof $scope.data.galleryList !== "object" || $scope.data.galleryList.length === 0) {
		
			galleryService.getList().success(function(galleryList) {
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
	
	$scope.addPhoto = function(addPhotoForm) {

		
		if (addPhotoForm.title == undefined) {
			addPhotoForm.title = "";
		}
		
		if (addPhotoForm.description == undefined) {
			addPhotoForm.description = "";
		}

		
		photoService.add({
			galleryId: addPhotoForm.galleryId,
			title: addPhotoForm.title,
			description: addPhotoForm.description,
			imageSrc: ""
		})
		.success(function(addedPhoto) {
			
			
			photoService.editImage({
				photoId: addedPhoto.id,
				imageSrc: $scope.files[0]
			})
			.success(function(editedPhoto) {
				
				addPhotoForm.title = "";
				addPhotoForm.galleryId = 0;
				addPhotoForm.description = "";
				addPhotoForm.imageSrc = "";
				//Clear file input
				$("#photoAddImgSrc").val("");
				
				addedPhoto.modified = true;
				
				$scope.data.photos.push(editedPhoto);
				
				$scope.hideAddPhotoWindow();
				
			});
			
		});

	};
	
	/* Add photo form */
	$scope.showAddPhotoWindow = function() {
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
			galleryId: editPhotoForm.galleryId,
			description: editPhotoForm.description,
			imageSrc: editPhotoForm.imageSrc
		})
		.success(function(editedPhoto) {
			
			$scope.selectedPhotos = [];
			
			for (var photo in $scope.data.photos) {
				
				if ($scope.data.photos[photo].id === editedPhoto.id) {
					$scope.data.photos.splice(photo, 1);
				}
			}
			editPhotoForm.title = "";
			editPhotoForm.galleryId = 0;
			editPhotoForm.description = "";
			editPhotoForm.imageSrc = "";
			
			editedPhoto.modified = true;
			
			$scope.data.photos.push(editedPhoto);
			
			$scope.hideEditPhotoWindow();
		});
		
	};
	
	/* Edit Photo form */
	$scope.showEditPhotoWindow = function() {
		

		//Edit photo form set values
		//ng-value directive doesn't work
		$("#editPhotoFormId").val($scope.selectedPhotos[0].id).trigger("change");
		$("#editPhotoFormTitle").val($scope.selectedPhotos[0].title).trigger("change");
		$("#editPhotoFormDescription").val($scope.selectedPhotos[0].description).trigger("change");
		$("#editPhotoFormImageSrc").val($scope.selectedPhotos[0].imageSrc).trigger("change");
		
		$("#editPhotoFormGalleryId option").each(function() {
			var $this = $(this),
				value = $this.attr("value");

			if(value == $scope.selectedPhotos[0].gallery.id) {
				$this.attr('selected', 'selected');
			}
		});

		$scope.editPhotoWindowVisible = true;

		
	}
	
	$scope.hideEditPhotoWindow = function() {
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
	
});

