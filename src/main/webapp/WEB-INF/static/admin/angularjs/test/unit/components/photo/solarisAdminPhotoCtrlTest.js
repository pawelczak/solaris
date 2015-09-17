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
	                    {id: 1, title: "Great Photo", gallery: {id: 3, name: "gallery #1"}, description: "Photo desc", imageSrc: "folder/image.jpg" },
	                    {id: 2, title: "Awesome picture.", gallery: {id: 4, name: "gallery #2"}, description: "Photo desc #2", imageSrc: "folder/image2.jpg" }
                    ],
	
        galleryList = [
	                    {id: 1, name: "Tatry #12344", description: null, visible: false},
	                    {id: 2, name: "Tatry #2", description: null, visible: false}
	                    ];
	
	var photo = photoList[0],
		photoChangedImage = photoList[0];
	
	photoChangedImage.imageSrc = "changedImage.jpg";
	
	
	//------------------------ CONFIG --------------------------	
	
	beforeEach(function() {
	
		angular.mock.module("solarisAdmin");
		
		angular.mock.module(function($provide) {
			
			
			//Mock photoService Implementation returning a promise
			$provide.value('photoService', {
				findAll: function() {
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
				editImage: function(reqData) { 
					return { 
						success: function(callback) {return callback(photoChangedImage);}
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
				findAll: function() {
					return { 
						success: function(callback) {return callback(galleryList);}
					};
				}
			});
			
			//Mock addPhotoFormFactory implementation
			$provide.value('addPhotoFormFactory', {
				create: function() {
					var form = {};
					
					form.title = "";
					form.galleryId = 0;
					form.description = "";
					form.imageSrc = "";
					
					return form;
				},
				
				reset: function(form) {
					form.title = "";
					form.galleryId = 0;
					form.description = "";
					form.imageSrc = "";
				}
			});
			
			//Mock addPhotoFormFactory implementation
			$provide.value('editPhotoFormFactory', {
				create: function() {
					var form = {};
					
					form.id = 0;
					form.title = "";
					form.galleryId = 0;
					form.description = "";
					form.imageSrc = "";
					
					return form;
				},
				
				reset: function(form) {
					
					form.id = 0;
					form.title = "";
					form.galleryId = 0;
					form.description = "";
					form.imageSrc = "";
				},
				
				set: function(form, photo) {
					form.id = photo.id;
					form.title = photo.title
					form.galleryId = photo.gallery.id;
					form.description = photo.description;
					form.imageSrc = photo.imageSrc;
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
		
		
		// Jasmine spy over the method findAll of photoService. 
		// Since we provided a fake response already we can just call through. 
		spyOn(photoService, 'findAll').and.callThrough();
		
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
	
	
	//------------------------ ADD PHOTO --------------------------
	
	
	it("should add new photo - no image file", function() {
		
		var addPhotoForm = {
			title: photoList[0].title,
			galleryId: photoList[0].gallery.id,
			description: photoList[0].description,
			imageSrc: photoList[0].imageSrc
		};
		
		//empty photo list
		mockScope.files = [];
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		//add new photo
		mockScope.addPhoto(addPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photo.id);
		expect(mockScope.data.photos[0].title).toEqual(photo.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(photo.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(photo.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(photo.imageSrc);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
	});
	
	it("should add new photo - with image file", function() {
		
		var addPhotoForm = {
			title: photoChangedImage.title,
			galleryId: photoChangedImage.gallery.id,
			description: photoChangedImage.description,
			imageSrc: photoChangedImage.imageSrc
		};
		
		//empty photo list
		mockScope.files = ["files"];
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		 
		//add new photo
		mockScope.addPhoto(addPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photoChangedImage.id);
		expect(mockScope.data.photos[0].title).toEqual(photoChangedImage.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(photoChangedImage.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(photoChangedImage.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(photoChangedImage.imageSrc);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
	});
	
	it("should show add photo window", function() {
		
		//execute
		mockScope.showAddPhotoWindow();
		
		//assert
		expect(mockScope.addPhotoWindowVisible).toEqual(true);
		
		expect(mockScope.addPhotoForm.title).toEqual("");
		expect(mockScope.addPhotoForm.galleryId).toEqual(0);
		expect(mockScope.addPhotoForm.description).toEqual("");
		expect(mockScope.addPhotoForm.imageSrc).toEqual("");
	});
	
	it("should hide add photo window", function() {
		
		//execute
		mockScope.hideAddPhotoWindow();
		
		//assert
		expect(mockScope.addPhotoWindowVisible).toEqual(false);
		
		expect(mockScope.addPhotoForm.title).toEqual("");
		expect(mockScope.addPhotoForm.galleryId).toEqual(0);
		expect(mockScope.addPhotoForm.description).toEqual("");
		expect(mockScope.addPhotoForm.imageSrc).toEqual("");
	});
	
	it("When add photo window is hidden it should have proper css styles", function() {
		mockScope.hideAddPhotoWindow();
		expect(mockScope.getAddPhotoWindowClass()).toEqual("display-none");
	});
	
	it("When add photo window is show it should have proper css styles", function() {
		
		//execute
		mockScope.showAddPhotoWindow();
		
		//assert
		expect(mockScope.getAddPhotoWindowClass()).toEqual("display-block");
	});

	
	
	//------------------------ EDIT PHOTO --------------------------
	
	it("should edit existing photo - photo image is not changing", function() {
		
		var editPhotoForm = {
			id: photo.id,
			title: photo.title,
			galleryId: photo.gallery.id,
			description: photo.description,
			imageSrc: photo.imageSrc
			
		},
			actualPhotos = {
			id: 1,
			title: "nice photo",
			gallery: {
				id: 4,
				name: "Tatry #12344"
			},
			description: "photo desc",
			imageSrc: "photo src"
		};
		
		
		 
		//init photo list with one photo
		mockScope.data.photos = [actualPhotos];
		mockScope.selectedPhotos.push(editPhotoForm.id);
		mockScope.files = [];
		
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(actualPhotos.id);
		expect(mockScope.data.photos[0].title).toEqual(actualPhotos.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(actualPhotos.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(actualPhotos.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(actualPhotos.imageSrc);
		
		//edit existing photo
		mockScope.editPhoto(editPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photo.id);
		expect(mockScope.data.photos[0].title).toEqual(photo.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(photo.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(photo.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(photo.imageSrc);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		expect(mockScope.selectedPhotos.length).toEqual(0);
		
		expect(mockScope.editPhotoWindowVisible).toEqual(false);
	});
	
	
	
	it("should edit existing photo - photo image is changing", function() {
		
		var editPhotoForm = {
			id: photoChangedImage.id,
			title: photoChangedImage.title,
			galleryId: photoChangedImage.gallery.id,
			description: photoChangedImage.description,
			imageSrc: photoChangedImage.imageSrc
			
		},
			actualPhotos = {
			id: photoChangedImage.id,
			title: "nice photo",
			gallery: {
				id: 4,
				name: "Tatry #12344"
			},
			description: "photo desc",
			imageSrc: "photo src"
		};
		
		
		 
		//init photo list with one photo
		mockScope.data.photos = [actualPhotos];
		mockScope.selectedPhotos.push(editPhotoForm); 
		mockScope.files = ["files"];
		
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photoChangedImage.id);
		expect(mockScope.data.photos[0].title).toEqual(actualPhotos.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(actualPhotos.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(actualPhotos.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(actualPhotos.imageSrc);
		
		//edit existing photo
		mockScope.editPhoto(editPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photoChangedImage.id);
		expect(mockScope.data.photos[0].title).toEqual(photoChangedImage.title);
		expect(mockScope.data.photos[0].gallery.id).toEqual(photoChangedImage.gallery.id);
		expect(mockScope.data.photos[0].description).toEqual(photoChangedImage.description);
		expect(mockScope.data.photos[0].imageSrc).toEqual(photoChangedImage.imageSrc);
		expect(mockScope.data.photos[0].modified).toEqual(true);
		expect(mockScope.selectedPhotos.length).toEqual(0);
		
		expect(mockScope.editPhotoWindowVisible).toEqual(false);
	});
	

	it("Show edit photo window", function() {
		
		//given
		mockScope.selectedPhotos.push(photo);
		
		//execute
		mockScope.showEditPhotoWindow();
		
		//assert
		expect(mockScope.editPhotoWindowVisible).toEqual(true);
		
		expect(mockScope.editPhotoForm.id).toEqual(photo.id);
		expect(mockScope.editPhotoForm.title).toEqual(photo.title);
		expect(mockScope.editPhotoForm.galleryId).toEqual(photo.gallery.id);
		expect(mockScope.editPhotoForm.description).toEqual(photo.description);
		expect(mockScope.editPhotoForm.imageSrc).toEqual(photo.imageSrc);
	}); 
	
	it("Hide edit photo window", function() {
		
		//execute
		mockScope.hideEditPhotoWindow();
		
		//assert
		expect(mockScope.editPhotoWindowVisible).toEqual(false);

		expect(mockScope.editPhotoForm.id).toEqual(0);
		expect(mockScope.editPhotoForm.title).toEqual("");
		expect(mockScope.editPhotoForm.galleryId).toEqual(0);
		expect(mockScope.editPhotoForm.description).toEqual("");
		expect(mockScope.editPhotoForm.imageSrc).toEqual("");
	});
	
	it("When edit photo window is hidden it should have proper css styles", function() {
		
		//execute
		mockScope.hideEditPhotoWindow();
		
		//assert
		expect(mockScope.getEditPhotoWindowClass()).toEqual("display-none");
	});

	it("When edit photo window is visible it should have proper css styles", function() {
		
		//given
		mockScope.selectedPhotos.push(photo);
		
		//execute
		mockScope.showEditPhotoWindow();
		
		//asser
		expect(mockScope.getEditPhotoWindowClass()).toEqual("display-block");
	}); 
	
	
	//------------------------ DELETE PHOTO --------------------------
	
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

