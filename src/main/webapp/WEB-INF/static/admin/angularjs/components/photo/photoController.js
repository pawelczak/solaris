angular.module("solarisAdmin")
.constant("PHOTO_ORDER_PROPERTY", "title")
.constant("PHOTO_ORDER_PROPERTY_FILTER_VALUES", ["title", "description"])
.constant("PHOTO_PAGE_SIZE", 10)
.constant("PHOTO_PAGE_SIZE_FILTER_VALUES", [5, 10, 20, 50])
.controller("photoController", ["$scope", "photoService", "galleryService", "addPhotoFormFactory", "editPhotoFormFactory",
      "PHOTO_ORDER_PROPERTY", "PHOTO_ORDER_PROPERTY_FILTER_VALUES", "PHOTO_PAGE_SIZE", "PHOTO_PAGE_SIZE_FILTER_VALUES",
      function($scope, photoService, galleryService, addPhotoFormFactory, editPhotoFormFactory,
	  PHOTO_ORDER_PROPERTY, PHOTO_ORDER_PROPERTY_FILTER_VALUES, PHOTO_PAGE_SIZE, PHOTO_PAGE_SIZE_FILTER_VALUES) {
	
	
	//------------------------ VARIABLES --------------------------
	
	$scope.selectedPage = 1;
	$scope.numberOfPages = 1;
	
	$scope.addPhotoWindowVisible = false;
	$scope.editPhotoWindowVisible = false;
	$scope.deletePhotoWindowVisible = false;
	$scope.selectedPhotos = [];
	
	var photos;
	
	
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

			photos = data;
			
			$scope.updatePhotos();
		});
	};
	
	$scope.updatePhotos = function() {
		if ($scope.photoSelectedGalleryId != undefined) {
			var results = [];
			for (var i = 0, length = photos.length; i < length; i += 1) {
				if (photos[i].gallery.id == $scope.photoSelectedGalleryId) {
					results.push(photos[i]);
				}
			}
			
			$scope.data.photos = results;
			
		} else {
			$scope.data.photos = photos;
		}
		
	};
	
	$scope.clearPhotos = function() {
		photos = [];
		$scope.data.photos = [];
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
					
					photos.push(editedPhoto);
					
					$scope.updatePhotos();
				
					$scope.setPhotoSelectedGalleryId(editedPhoto.gallery.id);
					
					$scope.hideAddPhotoWindow();
					
				});
				
			} else {
				addedPhoto.modified = true;
				
				//$scope.data.photos.push(addedPhoto);
				
				photos.push(addedPhoto);
				
				$scope.updatePhotos();
				
				$scope.setPhotoSelectedGalleryId(addedPhoto.gallery.id);
				
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
			
			for (var photo in photos) {
				
				if (photos[photo].id === editPhoto.id) {
					photos.splice(photo, 1);
				}
			}
			
			editPhoto.modified = true;
			
			//$scope.data.photos.push(editPhoto);
			
			photos.push(editPhoto);
			
			$scope.updatePhotos();
			
			$scope.setPhotoSelectedGalleryId(editPhoto.gallery.id);
			
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
				
				for (var photo in photos) {
					
					if (photos[photo].id === removedPhotos[removedPhoto].id) {
						photos.splice(photo, 1);
					}
				}
			}
			
			$scope.updatePhotos();
			
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
	
	$scope.clearSelectedPhotos = function () {
		$scope.selectedPhotos = [];
	};
	
	/*
	 * Is it needed??
	$scope.setGallery = function(gallery) {
		
		//TODO
		$scope.photoSelectedGalleryId = gallery.id;
		$scope.$parent.photoSelectedGalleryId = gallery.id;
		
		$scope.clearSelectedPhotos();
	};
	*/
	
	$scope.setPhotoSelectedGalleryId = function(galleryId) {
		
		$scope.photoSelectedGalleryId = galleryId;
		$scope.$parent.photoSelectedGalleryId = galleryId;
		
		$scope.updatePhotos();
		
		$scope.selectedPage = 1;
	};
	
	
	/* Disables/Enables action buttons e.g. edit delete */ 
	$scope.isActionButtonDisabled = function() {
		return $scope.selectedPhotos.length < 1;
	};

	$scope.getImageVisible = function(photo) {
		return !photo.visible ? "display-none" : "display-block";
	};
	
	$scope.getImageHidden = function(photo) {
		return photo.visible ? "display-none" : "display-block";
	};
	

	
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
		//$scope.$parent.$parent.photoOrderByProperty = property;
		
		$scope.setPhotoOrderBy(property);
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

