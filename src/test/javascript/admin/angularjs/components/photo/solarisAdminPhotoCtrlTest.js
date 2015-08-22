/* Angularjs controller solarisAdminPhotoCtrl tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("solarisAdmiPhotoCtrl", function() {

	var mockScope = {},
		controller,
		photoService;

	var photoList = [
	                    {id: 1, title: "Great Photo", gallery: {id: 3, name: "gallery #1"} },
	                    {id: 2, title: "Awesome picture.", gallery: {id: 4, name: "gallery #2"} }
                    ],
	
        galleryList = [
	                    {id: 1, name: "Tatry #12344", description: null, visible: false},
	                    {id: 2, name: "Tatry #2", description: null, visible: false}
	                    ];
	
	var photo = photoList[0];
	
	
	//------------------------ CONFIG --------------------------	
	
	beforeEach(function() {
	
		angular.mock.module("solarisAdmin");
		
		angular.mock.module(function($provide) {
			
			
			//Mock photoService Implementation returning a promise
			$provide.value('photoService', {
				getList: function() {
					return { 
						success: function(callback) {return callback(photoList);}
					};
				},
				add: function(reqData) { 
					return { 
						success: function(callback) {return callback(photo);}
					};
				},
				edit: function(reqData) { 
					return { 
						success: function(callback) {return callback(photo);}
					};
				},
				remove: function(reqData) { 
					return { 
						success: function(callback) {return callback(photoList);}
					};
				}
			});
		
			//Mock galleryService Implementation returning a promise
			$provide.value('galleryService', {
				getList: function() {
					return { 
						success: function(callback) {return callback(galleryList);}
					};
				}
			});
			
	      	return null;
		});
		
	});
	
	beforeEach(angular.mock.inject(function(_photoService_, $controller, $rootScope) {
	
		
		photoService = _photoService_;
		
		mockScope = $rootScope.$new();
		controller = $controller("solarisAdminPhotoCtrl", {
			$scope: mockScope
		})
		
	}));
		
	
    //------------------------ TESTS --------------------------	
	
	it(" should have base variables initialized", function() {
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
		expect(mockScope.editPhotoWindowVisible).toEqual(false);
		expect(mockScope.deletePhotoWindowVisible).toEqual(false);
		expect(mockScope.selectedPhotos.length).toEqual(0);
	});
	
	it("should call the photo Service to retrieve the photo list", function() {
		
		
		// Jasmine spy over the method getList of photoService. 
		// Since we provided a fake response already we can just call through. 
		spyOn(photoService, 'getList').and.callThrough();
		
		expect(mockScope.data.photos.length).toEqual(2);
	});
	
	
	it("should load new photo list", function() {
		
		//empty photo list
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		//get new photo list
		mockScope.loadPhotos();
		expect(mockScope.data.photos.length).toEqual(2);
	});
	
	it("should be able to load gallery list", function() {
	
		//empty photo list
		mockScope.data.galleryList = [];
		expect(mockScope.data.galleryList.length).toEqual(0);
		
		//get new photo list
		mockScope.loadGalleryList();
		expect(mockScope.data.galleryList.length).toEqual(2);
	});
	
	
	it("should add new photo", function() {
		
		var addPhotoForm = {
			title: photoList[0].title,
			galleryId: photoList[0].gallery.id
		};
		
		//empty gallery list
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		//add new gallery
		mockScope.addPhoto(addPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photo.id);
		expect(mockScope.data.photos[0].title).toEqual(photo.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(photo.gallery.id);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
	});
	
	it("should show add photo window", function() {
		mockScope.showAddPhotoWindow();
		expect(mockScope.addPhotoWindowVisible).toEqual(true);
	});
	
	it("should hide add photo window", function() {
		mockScope.hideAddPhotoWindow();
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
	});
	
	it("When add photo window is hidden it should have proper css styles", function() {
		mockScope.hideAddPhotoWindow();
		expect(mockScope.getAddPhotoWindowClass()).toEqual("display-none");
	});

	
	
	it("should edit existing photo", function() {
		
		var editPhotoForm = {
			id: photo.id,
			title: photo.title,
			galleryId: photo.gallery.id
		},
			actualPhotos = {
			id: 1,
			title: "nice photo",
			gallery: {
				id: 4,
				name: "Tatry #12344"
			}
		};
		
		
		 
		//init photo list with one photo
		mockScope.data.photos = [actualPhotos];
		mockScope.selectedPhotos.push(editPhotoForm.id);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(actualPhotos.id);
		expect(mockScope.data.photos[0].title).toEqual(actualPhotos.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(actualPhotos.gallery.id);
		
		//edit existing photo
		mockScope.editPhoto(editPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(editPhotoForm.id);
		expect(mockScope.data.photos[0].title).toEqual(editPhotoForm.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(editPhotoForm.galleryId);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		expect(mockScope.selectedPhotos.length).toEqual(0);
		
		expect(mockScope.editPhotoWindowVisible).toEqual(false);
	});
	
	//TODO Missing jquery lib
	/*
	it("Show edit photo window", function() {
		mockScope.showEditPhotoWindow();
		expect(mockScope.editPhotoWindowVisible).toEqual(true);
	}); */
	
	it("Hide edit photo window", function() {
		mockScope.hideEditPhotoWindow();
		expect(mockScope.editPhotoWindowVisible).toEqual(false);
	});
	
	it("When edit photo window is hidden it should have proper css styles", function() {
		mockScope.hideEditPhotoWindow();
		expect(mockScope.getEditPhotoWindowClass()).toEqual("display-none");
	});

	//TODO Missing jquery lib
	/*
	it("When edit gallery window is visible it should have proper css styles", function() {
		mockScope.showEditGalleryWindow();
		expect(mockScope.getEditGalleryWindowClass()).toEqual("display-block");
	}); */
	
	
	it("should delete selected photos", function() {
		
		//empty photo list
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		//get new photo list
		mockScope.loadPhotos(); 
		
		mockScope.selectedPhotos.push(photoList[0]);
		mockScope.selectedPhotos.push(photoList[1]);
		
		expect(mockScope.data.photos.length).toEqual(2);
		expect(mockScope.selectedPhotos.length).toEqual(2);
		
		
		//delete selected photos
		mockScope.deletePhoto();
		
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.selectedPhotos.length).toEqual(0);
		
		expect(mockScope.deletePhotoWindowVisible).toEqual(false);
		
	});
	
	
	it("Show delete photo window", function() {
		mockScope.showDeletePhotoWindow();
		expect(mockScope.deletePhotoWindowVisible).toEqual(true);
	});
	
	it("Hide delete photo window", function() {
		mockScope.hideDeletePhotoWindow();
		expect(mockScope.deletePhotoWindowVisible).toEqual(false);
	});
	
	it("When delete photo window is hidden it should have proper css styles", function() {
		mockScope.hideDeletePhotoWindow();
		expect(mockScope.getDeletePhotoWindowClass()).toEqual("display-none");
	});
	
	it("When delete photo window is visible it should have proper css styles", function() {
		mockScope.showDeletePhotoWindow();
		expect(mockScope.getDeletePhotoWindowClass()).toEqual("display-block");
	});
	
	
	 
	it("should be able to select photos to edit/remove", function() {
		
		mockScope.selectedPhotos = [];
		expect(mockScope.selectedPhotos.length).toEqual(0);
		
		mockScope.toggleSelection(photoList[0]);
		mockScope.toggleSelection(photoList[1]);
		expect(mockScope.selectedPhotos.length).toEqual(2);
		
		mockScope.toggleSelection(photoList[0]);
		expect(mockScope.selectedPhotos.length).toEqual(1); 

	});
	
});

