/* Angularjs controller photoController tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("PhotoController tests", function() {
	
	
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
				galleryId: 53
			})
			
	      	return null;
		});
		
	});
	
	beforeEach(angular.mock.inject(function($controller, $rootScope, _$routeParams_, _photoService_) {
	
		$routeParams = _$routeParams_;
		photoService = _photoService_;
		
		mockScope = $rootScope.$new();
		controller = $controller("photoController", {
			$scope: mockScope
		});
		
	}));
	
	
    //------------------------ TESTS --------------------------
	
	it("Should have contextPath defined", function() {
		expect(mockScope.contextPath).toBeDefined();
	});
	
	if("should have routeParams initialized", function() {
		expect(mockScope.galleryId).toEqual(53);
	});
	
	it("Initial photos and gallery should be loaded", function() {
		expect(mockScope.data.gallery).toEqual(photoServiceResponse.gallery);
		expect(mockScope.data.photos).toEqual(photoServiceResponse.photos);
	});
	
	
	it("should be able to load photos and selected gallery", function() {
		
		mockScope.data.photos = [];
		mockScope.data.gallery = {};
		expect(mockScope.data.photos.length).toEqual(0);
		expect(mockScope.data.gallery).toEqual({});
		
		mockScope.loadPhotos();
		expect(mockScope.data.gallery).toEqual(photoServiceResponse.gallery);
		expect(mockScope.data.photos).toEqual(photoServiceResponse.photos);
	});
	
	it("should be possible to assign specific class to photo block depending on position", function() {
		
		expect(mockScope.getBlockClassName).toBeDefined();
		expect(mockScope.getBlockClassName(0)).toEqual("left-block");
		expect(mockScope.getBlockClassName(1)).toEqual("middle-block");
		expect(mockScope.getBlockClassName(2)).toEqual("right-block");
	});
	
});
