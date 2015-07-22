/* Angularjs controller solarisAdminGalleryCtrl tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("solarisAdminGalleryCtrl", function() {
	
	
	
	var mockScope = {},
		controller,
		galleryService;
	
	var galleryList = [
	                    {id: 1, name: "Tatry #12344", description: null, visible: false},
	                    {id: 2, name: "Tatry #2", description: null, visible: false}
	                    ];
	
	var gallery = {id: 2, name: "Tatry #2", description: null, visible: false};
	
    //------------------------ CONFIG --------------------------	
	
	beforeEach(function() {
		
		angular.mock.module("solarisAdmin");
		
		angular.mock.module(function($provide) {
			
			
			//Mock galleryService Implementation returning a promise
			$provide.value('galleryService', {
				getList: function() {
					return { 
						success: function(callback) {return callback(galleryList);}
					};
				},
				add: function(reqData) { 
					return { 
						success: function(callback) {return callback(gallery);}
					};
				},
				edit: function(reqData) { 
					return { 
						success: function(callback) {return callback(gallery);}
					};
				},
				remove: function(reqData) { 
					return { 
						success: function(callback) {return callback(galleryList);}
					};
				}
			});
		
	      	return null;
		});
		
	});
	
	beforeEach(angular.mock.inject(function(_galleryService_, $controller, $rootScope) {

		
		galleryService = _galleryService_;
		
		mockScope = $rootScope.$new();
		controller = $controller("solarisAdminGalleryCtrl", {
			$scope: mockScope
		})
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("Variables", function() {
		
		expect(mockScope.addGalleryWindowVisible).toEqual(false);
		expect(mockScope.editGalleryWindowVisible).toEqual(false);
		expect(mockScope.deleteGalleryWindowVisible).toEqual(false);
		expect(mockScope.selectedGalleries.length).toEqual(0);
	});
	
	it("should call the galleryService to retrieve the gallery list", function() {
		
		
		// Jasmine spy over the getList service. 
		// Since we provided a fake response already we can just call through. 
		spyOn(galleryService, 'getList').and.callThrough();
		
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	
	
	it("should load new gallery list", function() {
		
		//empty gallery list
		mockScope.data.galleries = [];
		expect(mockScope.data.galleries.length).toEqual(0);
		
		//get new gallery list
		mockScope.loadGalleries();
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	

	it("should add new gallery", function() {
		
		var addGalleryForm = {
			name: "Polnische Tatre",
			description: "Polnische Tatre sind sehr schon.",
			visible: false
		};
		
		//empty gallery list
		mockScope.data.galleries = [];
		expect(mockScope.data.galleries.length).toEqual(0);
		
		//add new gallery
		mockScope.addGallery(addGalleryForm);
		expect(mockScope.data.galleries.length).toEqual(1);
		expect(mockScope.data.galleries[0].id).toEqual(2);
		expect(mockScope.data.galleries[0].name).toEqual("Tatry #2");
		expect(mockScope.data.galleries[0].description).toEqual(null);
		expect(mockScope.data.galleries[0].visible).toEqual(false);
		expect(mockScope.data.galleries[0].modified).toEqual(true);
		
		expect(mockScope.addGalleryWindowVisible).toEqual(false);
	});
	
	
	it("Show add gallery window", function() {
		mockScope.showAddGalleryWindow();
		expect(mockScope.addGalleryWindowVisible).toEqual(true);
	});
	
	it("Hide add gallery window", function() {
		mockScope.hideAddGalleryWindow();
		expect(mockScope.addGalleryWindowVisible).toEqual(false);
	});
	
	it("When add gallery window is hidden it should have proper css styles", function() {
		mockScope.hideAddGalleryWindow();
		expect(mockScope.getAddGalleryWindowClass()).toEqual("display-none");
	});
	
	it("When add gallery window is visible it should have proper css styles", function() {
		mockScope.showAddGalleryWindow();
		expect(mockScope.getAddGalleryWindowClass()).toEqual("display-block");
	});
	
	
	
	it("should edit existing gallery", function() {
		
		var editGalleryForm = {
			id: 2,
			name: "Tatry #2",
			description: null,
			visible: false
		},
			actualGallery = {
			id: 2,
			name: "Pireneje",
			description: "aASD",
			visible: true
		};
		
		
		
		//init gallery list with one gallery
		mockScope.data.galleries = [actualGallery];
		mockScope.selectedGalleries.push(2);
		expect(mockScope.data.galleries.length).toEqual(1);
		expect(mockScope.data.galleries[0].id).toEqual(2);
		expect(mockScope.data.galleries[0].name).toEqual("Pireneje");
		expect(mockScope.data.galleries[0].description).toEqual("aASD");
		expect(mockScope.data.galleries[0].visible).toEqual(true);
		
		//edit existing gallery
		mockScope.editGallery(editGalleryForm);
		expect(mockScope.data.galleries.length).toEqual(1);
		expect(mockScope.data.galleries[0].id).toEqual(2);
		expect(mockScope.data.galleries[0].name).toEqual("Tatry #2");
		expect(mockScope.data.galleries[0].description).toEqual(null);
		expect(mockScope.data.galleries[0].visible).toEqual(false);
		expect(mockScope.data.galleries[0].modified).toEqual(true);
		expect(mockScope.selectedGalleries.length).toEqual(0);
		
		expect(mockScope.editGalleryWindowVisible).toEqual(false);
	});
	
	//TODO Missing jquery lib
	/*
	it("Show edit gallery window", function() {
		mockScope.showEditGalleryWindow();
		expect(mockScope.editGalleryWindowVisible).toEqual(true);
	}); */
	
	it("Hide edit gallery window", function() {
		mockScope.hideEditGalleryWindow();
		expect(mockScope.editGalleryWindowVisible).toEqual(false);
	});
	
	it("When edit gallery window is hidden it should have proper css styles", function() {
		mockScope.hideEditGalleryWindow();
		expect(mockScope.getEditGalleryWindowClass()).toEqual("display-none");
	});

	//TODO Missing jquery lib
	/*
	it("When edit gallery window is visible it should have proper css styles", function() {
		mockScope.showEditGalleryWindow();
		expect(mockScope.getEditGalleryWindowClass()).toEqual("display-block");
	}); */
	
	
	
	it("should delete selected galleries", function() {
		
		//empty gallery list
		mockScope.data.galleries = [];
		expect(mockScope.data.galleries.length).toEqual(0);
		
		//get new gallery list
		mockScope.loadGalleries(); 
		
		mockScope.selectedGalleries.push(galleryList[0]);
		mockScope.selectedGalleries.push(galleryList[1]);
		
		expect(mockScope.data.galleries.length).toEqual(2);
		expect(mockScope.selectedGalleries.length).toEqual(2);
		
		
		//delete selected galleries
		mockScope.deleteGallery();
		
		expect(mockScope.data.galleries.length).toEqual(1);
		expect(mockScope.selectedGalleries.length).toEqual(0);
		
		expect(mockScope.deleteGalleryWindowVisible).toEqual(false);
		
	});
	
	
	it("Show delete gallery window", function() {
		mockScope.showDeleteGalleryWindow();
		expect(mockScope.deleteGalleryWindowVisible).toEqual(true);
	});
	
	it("Hide delete gallery window", function() {
		mockScope.hideDeleteGalleryWindow();
		expect(mockScope.deleteGalleryWindowVisible).toEqual(false);
	});
	
	it("When delete gallery window is hidden it should have proper css styles", function() {
		mockScope.hideDeleteGalleryWindow();
		expect(mockScope.getDeleteGalleryWindowClass()).toEqual("display-none");
	});
	
	it("When delete gallery window is visible it should have proper css styles", function() {
		mockScope.showDeleteGalleryWindow();
		expect(mockScope.getDeleteGalleryWindowClass()).toEqual("display-block");
	});
	
	
	
	it("should be able to select galleries to edit/remove", function() {
		
		mockScope.selectedGalleries = [];
		expect(mockScope.selectedGalleries.length).toEqual(0);
		
		mockScope.toggleSelection(galleryList[0]);
		mockScope.toggleSelection(galleryList[1]);
		expect(mockScope.selectedGalleries.length).toEqual(2);
		
		mockScope.toggleSelection(galleryList[0]);
		expect(mockScope.selectedGalleries.length).toEqual(1); 

	});
	
	
});


