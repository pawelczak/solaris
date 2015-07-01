angular.module("solarisAdmin")
	
.controller("solarisAdminGalleryCtrl", function($scope, $http) {

	$scope.data = {};
	$scope.data.galleries = [];
	$scope.data.addWindowVisible = false; //TODO remove data prefix
	$scope.data.deleteWindowVisible = false;
	
	
	$scope.selectedGalleries = [];
	
	

	
	$scope.loadGalleries = function() {
		
		$http.get(contextPath + "/admin/api/gallery/list")
		.success(function(data) {
			
			$scope.data.galleries = data;
			
		});
		
	};
	
	//TODO move to init block
	if ($scope.data.galleries.length === 0) {
		$scope.loadGalleries();
	}
	
	$scope.addGallery = function(addGalleryForm) {
		
		
		$http({
			method: "post",
			url: contextPath + "/admin/api/gallery/add",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    },
			data: {
				name: addGalleryForm.name,
				description: addGalleryForm.desc,
				visible: addGalleryForm.visible
			}
			
		})
		.success(function(addedGallery) {
			
			//clear form
			addGalleryForm.name = "";
			addGalleryForm.desc = "";
			addGalleryForm.visible = false;
			
			addedGallery.modified = true;
			
			$scope.data.galleries.push(addedGallery);
			
			$scope.hideAddGalleryWindow();
		});
		
	};
	
	
	$scope.editGallery = function(editGalleryForm) {
		
		
		$http({
			method: "post",
			url: contextPath + "/admin/api/gallery/edit",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		        var str = [];
		        for(var p in obj)
		        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        return str.join("&");
		    },
			data: {
				id: editGalleryForm.id,
				name: editGalleryForm.name,
				description: editGalleryForm.desc,
				visible: editGalleryForm.visible
			}
			
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
	
	$scope.deleteGallery = function() {
		
		var ids = [];
		
		for(var i = 0; i < $scope.selectedGalleries.length; i++) {
			ids.push($scope.selectedGalleries[i].id);
		}
		
		
		$http({
			method: "post",
			url: contextPath + "/admin/api/gallery/delete",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		    transformRequest: function(obj) {
		        var str = [];
		        
		        for(var p in obj) {
		        	
		        	if (!(obj[p] instanceof Array)) {
		        		str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		        	} else {
		        		
		        		for (var element in obj[p]) {
		        			str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p][element]));
		        		}
		        	}
		        	
		        }
		        
		        return str.join("&");
		    },
			data: {
				ids: ids
			}
			
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
	
	
	/* Add gallery form */
	$scope.showAddGalleryWindow = function() {
		$scope.data.addWindowVisible = true;
	}
	
	$scope.hideAddGalleryWindow = function() {
		$scope.data.addWindowVisible = false;
	}
	
	$scope.getAddGalleryWindowClass = function() {
		return $scope.data.addWindowVisible ? "display-block" : "display-none"; 
	}

	
	/* Delete gallery form */
	$scope.showDeleteGalleryWindow = function() {
		$scope.data.deleteWindowVisible = true;
	}
	
	$scope.hideDeleteGalleryWindow = function() {
		$scope.data.deleteWindowVisible = false;
	}
	
	$scope.getDeleteGalleryWindowClass = function() {
		return $scope.data.deleteWindowVisible ? "display-block" : "display-none"; 
	}
	
	
	/* Edit gallery form */
	$scope.showEditGalleryWindow = function() {
		
		//Edit gallery form set values
		//ng-value directive doesn't work
		$("#editGalleryFormId").val($scope.selectedGalleries[0].id).trigger("change");
		$("#editGalleryFormName").val($scope.selectedGalleries[0].name).trigger("change");
		$("#editGalleryFormDesc").val($scope.selectedGalleries[0].description).trigger("change");
		$("#editGalleryFormVisible").val($scope.selectedGalleries[0].visible).trigger("change");
		
		$scope.data.editWindowVisible = true;
	}
	
	$scope.hideEditGalleryWindow = function() {
		$scope.data.editWindowVisible = false;
	}
	
	$scope.getEditGalleryWindowClass = function() {
		return $scope.data.editWindowVisible ? "display-block" : "display-none"; 
	}
	
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

