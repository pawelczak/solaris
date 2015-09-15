/* Angularjs controller galleryController tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("galleryController", function() {
	
	
	
	var mockScope = {},
		controller,
		galleryService;
	
	var galleryList = [
       {
		    id: 52,
		    name: "Alpy Dachstein",
		    description: "Alpy",
		    featuredImageSrc: "29.jpg"

		},
		{
		    id: 53,
		    name: "Alpy Mont Blanc",
		    description: "Alpy",
		    featuredImageSrc: "32.jpg"
		}
                    ];
	
    //------------------------ CONFIG --------------------------	
	
	beforeEach(function() {
		
		angular.mock.module("solarisSite");
		
		angular.mock.module(function($provide) {
			
			
			//Mock galleryService Implementation returning a promise
			$provide.value('galleryService', {
				findAll: function() {
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
		controller = $controller("galleryController", {
			$scope: mockScope
		})
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("Iniatl galleries should be loaded", function() {
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	
	
	it("should be able to load galleries", function() {
		
		mockScope.data.galleries = [];
		expect(mockScope.data.galleries.length).toEqual(0);
		
		mockScope.loadGalleries();
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	
	
});



