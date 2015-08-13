/* Angularjs controller solarisAdminGalleryCtrl tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("solarisAdmiPhotoCtrl", function() {

	var mockScope = {},
		controller,
		photoService;

	var photoList = [
	                    {id: 1, gallery_id: 3, title: "Great Photo"},
	                    {id: 2, gallery_id: 3, title: "Awesome picture."}
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
		
	
	
	it(" should have base variables initialized", function() {
		expect(mockScope.addGalleryWindowVisible).toEqual(false);
		expect(mockScope.editGalleryWindowVisible).toEqual(false);
		expect(mockScope.deleteGalleryWindowVisible).toEqual(false);
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
			galleryId: photoList[0].galleryId
		};
		
		//empty gallery list
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		//add new gallery
		mockScope.addPhoto(addPhotoForm);
		expect(mockScope.data.photos.length).toEqual(1);
		expect(mockScope.data.photos[0].id).toEqual(photo.id);
		expect(mockScope.data.photos[0].title).toEqual(photo.title);
		expect(mockScope.data.photos[0].galleryId).toEqual(photo.galleryId);
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

});

