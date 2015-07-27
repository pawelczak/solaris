angular.module("solarisAdmin")
.config(function() {
	
})	
.run(function() {
	
})
.constant("pageSize", 10)
.constant("orderByProperty", 'name')
.controller("solarisAdminGalleryCtrl", function($scope, galleryService, pageSize, orderByProperty) {

	
	$scope.data = {};
	$scope.data.galleries = [];
	$scope.addGalleryWindowVisible = false;
	$scope.editGalleryWindowVisible = false; 
	$scope.deleteGalleryWindowVisible = false;
	$scope.selectedGalleries = [];
	
	//Paging
	$scope.selectedPage = 1;
	$scope.pageSize = pageSize;
	$scope.pageSizes = [5, 10, 20, 50];
	
	//Ordering
	$scope.orderByProperty = orderByProperty;
	$scope.orderList = ["name", "description"];
	

	$scope.loadGalleries = function() {
		
		galleryService.getList().success(function(data) {
			$scope.data.galleries = data;
		});
	};
	
	
	if ($scope.data.galleries.length === 0) {
		$scope.loadGalleries();
	}

	
	//------------------------ ADD GALLERY --------------------------
	
	$scope.addGallery = function(addGalleryForm) {
		
		if (addGalleryForm.desc == undefined) {
			addGalleryForm.desc = "";
		}
		
		galleryService.add({
			name: addGalleryForm.name,
			description: addGalleryForm.desc,
			visible: addGalleryForm.visible
		}).success(function(addedGallery) {
			
			addGalleryForm.name = "";
			addGalleryForm.desc = "";
			addGalleryForm.visible = false;
			
			addedGallery.modified = true;
			
			$scope.data.galleries.push(addedGallery);
			
			$scope.hideAddGalleryWindow();
		});
		
	};
	
	/* Add gallery form */
	$scope.showAddGalleryWindow = function() {
		$scope.addGalleryWindowVisible = true;
	}
	
	$scope.hideAddGalleryWindow = function() {
		$scope.addGalleryWindowVisible = false;
	}
	
	$scope.getAddGalleryWindowClass = function() {
		return $scope.addGalleryWindowVisible ? "display-block" : "display-none"; 
	}
	
	
	//------------------------ EDIT GALLERY --------------------------
	
	$scope.editGallery = function(editGalleryForm) {
		
		galleryService.edit({
			id: editGalleryForm.id,
			name: editGalleryForm.name,
			description: editGalleryForm.desc,
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
		
		//Edit gallery form set values
		//ng-value directive doesn't work
		$("#editGalleryFormId").val($scope.selectedGalleries[0].id).trigger("change");
		$("#editGalleryFormName").val($scope.selectedGalleries[0].name).trigger("change");
		$("#editGalleryFormDesc").val($scope.selectedGalleries[0].description).trigger("change");
		$("#editGalleryFormVisible").val($scope.selectedGalleries[0].visible).trigger("change");
		
		$scope.editGalleryWindowVisible = true;
	}
	
	$scope.hideEditGalleryWindow = function() {
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
	}
	
	$scope.getPageClass = function(page) {
		return $scope.selectedPage == page ? "btn-primary" : "";
	}
	
	$scope.setPageSize = function(size) {
		$scope.selectedPage = 1;
		$scope.pageSize = size;
	}
	
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
	
	
});

