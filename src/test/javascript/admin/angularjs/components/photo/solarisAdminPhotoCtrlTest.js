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
	                    ];
	
	var photo = {id: 1, gallery_id: 3, title: "Great Photo"};
	
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
	
	

});

