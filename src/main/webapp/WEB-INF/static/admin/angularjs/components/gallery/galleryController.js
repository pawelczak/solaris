angular.module("solarisAdmin")
.config(function() {
	
})	
.run(function() {
	
})
.constant("galleryPageSize", 10)
.constant("galleryPageSizeFilterValues", [5, 10, 20, 50])
.constant("galleryOrderByProperty", "name")
.constant("galleryOrderByPropertyFilterValues", ["name", "description"])
.controller("galleryController", ["$scope", "$location", "galleryService", "addGalleryFormFactory", "editGalleryFormFactory",
        "galleryPageSize", "galleryPageSizeFilterValues", "galleryOrderByProperty", "galleryOrderByPropertyFilterValues",
        function($scope, $location, galleryService,
		addGalleryFormFactory, editGalleryFormFactory,
		galleryPageSize, galleryPageSizeFilterValues, galleryOrderByProperty, galleryOrderByPropertyFilterValues) {

	
	$scope.addGalleryWindowVisible = false;
	$scope.editGalleryWindowVisible = false; 
	$scope.deleteGalleryWindowVisible = false;
	$scope.selectedGalleries = [];
	
	//Paging
	$scope.selectedPage = 1;
	$scope.numberOfPages = 1;
	$scope.pageSize = galleryPageSize;
	$scope.pageSizeFilterValues = galleryPageSizeFilterValues;
	
	//Ordering
	$scope.orderByProperty = galleryOrderByProperty;
	$scope.orderByPropertyFilterValues = galleryOrderByPropertyFilterValues;
	
	
	$scope.initDataScope = function() {
		
		
		if (typeof $scope.data !== "object") {
			$scope.data = {};
		}
		
		if (typeof $scope.data.galleries !== "array") {
			$scope.loadGalleries();
		}
		
	}

	$scope.loadGalleries = function() {
		
		galleryService.findAll().success(function(data) {
			$scope.data.galleries = data;
		});
	};
	
	
	$scope.initDataScope();
	

	
	//------------------------ ADD GALLERY --------------------------
	
	$scope.addGalleryForm = addGalleryFormFactory.create();
	
	$scope.addGallery = function(addGalleryForm) {
		
		
		galleryService.add({
			name: addGalleryForm.name,
			description: addGalleryForm.description,
			visible: addGalleryForm.visible
		}).success(function(addedGallery) {
			
			addedGallery.modified = true;
			
			$scope.data.galleries.push(addedGallery);
			
			$scope.hideAddGalleryWindow();
		});
		
	};
	
	/* Add gallery form */
	$scope.showAddGalleryWindow = function() {
		
		addGalleryFormFactory.reset($scope.addGalleryForm);
		
		$scope.addGalleryWindowVisible = true;
	}
	
	$scope.hideAddGalleryWindow = function() {
		
		addGalleryFormFactory.reset($scope.addGalleryForm);
		
		$scope.addGalleryWindowVisible = false;
	}
	
	$scope.getAddGalleryWindowClass = function() {
		return $scope.addGalleryWindowVisible ? "display-block" : "display-none"; 
	}
	
	
	//------------------------ EDIT GALLERY --------------------------
	
	$scope.editGalleryForm = editGalleryFormFactory.create();
	
	$scope.editGallery = function(editGalleryForm) {
		
		galleryService.edit({
			id: editGalleryForm.id,
			name: editGalleryForm.name,
			description: editGalleryForm.description,
			visible: editGalleryForm.visible
		})
		.success(function(editedGallery) {
			
			$scope.selectedGalleries = [];
			
			for (var gallery in $scope.data.galleries) {
				
				if ($scope.data.galleries[gallery].id === editedGallery.id) {
					$scope.data.galleries.splice(gallery, 1);
				}
			}

			editedGallery.modified = true;
			
			$scope.data.galleries.push(editedGallery);
			
			$scope.hideEditGalleryWindow();
		});
		
	};
	
	/* Edit gallery form */
	$scope.showEditGalleryWindow = function() {
		
		editGalleryFormFactory.set($scope.editGalleryForm, $scope.selectedGalleries[0]);
		
		$scope.editGalleryWindowVisible = true;
	}
	
	$scope.hideEditGalleryWindow = function() {
		
		editGalleryFormFactory.reset($scope.editGalleryForm);
		
		$scope.editGalleryWindowVisible = false;
	};
	
	$scope.getEditGalleryWindowClass = function() {
		return $scope.editGalleryWindowVisible ? "display-block" : "display-none"; 
	};
	
	
	//------------------------ DELETE GALLERY --------------------------
	
	$scope.deleteGallery = function() {
		
		var ids = [];
		
		for(var i = 0; i < $scope.selectedGalleries.length; i++) {
			ids.push($scope.selectedGalleries[i].id);
		}

		galleryService.remove({
			ids: ids
		})
		.success(function(removedGalleries) {
			
			$scope.selectedGalleries = [];
			
			for (var removedGallery in removedGalleries) {
				
				for (var gallery in $scope.data.galleries) {
					
					if ($scope.data.galleries[gallery].id === removedGalleries[removedGallery].id) {
						$scope.data.galleries.splice(gallery, 1);
					}
				}
			}
			
			//set photo selected gallery id to all
			$scope.setPhotoSelectedGalleryId(undefined);
			
			$scope.hideDeleteGalleryWindow();
		});
		
	};
	
	/* Delete gallery form */
	$scope.showDeleteGalleryWindow = function() {
		$scope.deleteGalleryWindowVisible = true;
	};
	
	$scope.hideDeleteGalleryWindow = function() {
		$scope.deleteGalleryWindowVisible = false;
	};
	
	$scope.getDeleteGalleryWindowClass = function() {
		return $scope.deleteGalleryWindowVisible ? "display-block" : "display-none"; 
	};
	

	//------------------------ PAGING --------------------------
	
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
		$scope.pageSize = size;
	};
	
	$scope.isPreviousButtonDisabled = function() {
		return $scope.selectedPage == 1;
	};
	
	$scope.isNextButtonDisabled = function() {
		$scope.numberOfPages = $scope.getNumberOfPages();
		
		return $scope.selectedPage == $scope.numberOfPages;
	};
	
	$scope.getNumberOfPages = function() {
		if ($scope.data.galleries !== undefined && $scope.data.galleries.length > 0) {
			return Math.ceil($scope.data.galleries.length/$scope.pageSize);
		} else {
			return 1;
		}
	};
	
	//------------------------ MISC --------------------------
	
	$scope.setOrderBy = function(property) {
		$scope.selectedPage = 1;
		$scope.orderByProperty = property;
	}
	
	$scope.toggleSelection = function toggleSelection(gallery) {
		var idx = $scope.selectedGalleries.indexOf(gallery);

		if (idx > -1) {
			// is currently selected
			$scope.selectedGalleries.splice(idx, 1);
		} else {
			// is newly selected
			$scope.selectedGalleries.push(gallery);
		}
	};
	
	$scope.setPhotoLocationWithGalleryId = function(galleryId) {
		
		if (galleryId != undefined) {
			$scope.$parent.photoSelectedGalleryId = galleryId;
		}
		
		$location.path("/photo/");
		
	};
	
	/* Disables/Enables action buttons e.g. edit delete */
	$scope.isActionButtonDisabled = function() {
		return $scope.selectedGalleries.length < 1;
	}

	$scope.getImageVisible = function(gallery) {
		return !gallery.visible ? "display-none" : "display-block";
	};
	
	$scope.getImageHidden = function(gallery) {
		return gallery.visible ? "display-none" : "display-block";
	};
	
	
}]);

