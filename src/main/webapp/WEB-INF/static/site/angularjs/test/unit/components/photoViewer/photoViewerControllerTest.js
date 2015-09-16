/* Angularjs controller photoViewerController tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("PhotoViewerController tests", function() {
	
	
	var mockScope = {},
		controller,
		photoService;
	
	var photoServiceResponse = {
	    gallery: {

		    id: 53,
		    name: "Alpy Mont Blanc"

		},
		photos: [
		{

		    id: 32,
		    title: "Glacier de Tete Rousse",
		    description: "Zachod z Glacier de Tete Rousse",
		    imageSrc: "32.jpg"

		},
		{

		    id: 33,
		    title: "Mont Blanc de Courmayeur",
		    description: "Mont Blanc de Courmayeur (4748)",
		    imageSrc: "33.jpg"

		},
		{

		    id: 34,
		    title: "Aiguille de Bionnassay",
		    description: "Polnocne zbocze Aiguille de Bionnassay",
		    imageSrc: "34.jpg"

		}
	    ]

	};
	
	//------------------------ CONFIG --------------------------	
	
	beforeEach(function() {
		
		angular.mock.module("solarisSite");
		
		angular.mock.module(function($provide) {
			
			
			//Mock photoService Implementation returning a promise
			$provide.value('photoService', {
				findAllByGalleryId: function() {
					return { 
						success: function(callback) {return callback(photoServiceResponse);}
					};
				}
			});
			
			//Mock $routeParams
			$provide.value('$routeParams', {
				galleryId: 53,
				photoId: 33
			})
			
	      	return null;
		});
		
	});
	
	beforeEach(angular.mock.inject(function($controller, $rootScope, _$routeParams_, _photoService_) {
	
		$routeParams = _$routeParams_;
		photoService = _photoService_;
		
		mockScope = $rootScope.$new();
		controller = $controller("photoViewerController", {
			$scope: mockScope
		})
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("Should have contextPath defined", function() {
		expect(mockScope.contextPath).toBeDefined();
	});
	
	if("should have routeParams initialized", function() {
		expect(mockScope.galleryId).toEqual(53);
		expect(mockScope.photoId).toEqual(33);
	});
	
	it("Initial photos should be loaded", function() {
		expect(mockScope.data.photos.length).toEqual(photoServiceResponse.photos.length);
	});
	
	
	it("should be able to load photos", function() {
		
		mockScope.data.photos = [];
		expect(mockScope.data.photos.length).toEqual(0);
		
		mockScope.loadPhotos();
		expect(mockScope.data.photos.length).toEqual(photoServiceResponse.photos.length);
	});
	
	it("should have selected photo selected to second photo", function() {
		expect(mockScope.selectedPhotoIndex).toEqual(1); 
	});
	
	it("should be possible to update selected Photo", function() {
		mockScope.selectedPhotoIndex = 0;
		expect(mockScope.selectedPhotoIndex).toEqual(0);
		
		mockScope.updateSelectedPhotoIndex();
		expect(mockScope.selectedPhotoIndex).toEqual(1);
	});
		
	it("should be possible to change photo to next", function() {
		expect(mockScope.selectedPhotoIndex).toEqual(1);
		mockScope.nextPhoto();
		expect(mockScope.selectedPhotoIndex).toEqual(2);
	});
	
	it("should be possible to change photo to previous", function() {
		expect(mockScope.selectedPhotoIndex).toEqual(1);
		mockScope.prevPhoto();
		expect(mockScope.selectedPhotoIndex).toEqual(0);
	});
	
	it("should hide next button when last photo is viewed", function() {
		expect(mockScope.selectedPhotoIndex).toEqual(1);
		expect(mockScope.nextPhotoButtonClassName()).toEqual("display-block");
		
		mockScope.nextPhoto();
		
		expect(mockScope.nextPhotoButtonClassName()).toEqual("display-none");
	});
	
	it("should hide prev button when first photo is viewed", function() {
		expect(mockScope.selectedPhotoIndex).toEqual(1);
		expect(mockScope.prevPhotoButtonClassName()).toEqual("display-block");
		
		mockScope.prevPhoto();
		
		expect(mockScope.prevPhotoButtonClassName()).toEqual("display-none");
	});
	
	
});

