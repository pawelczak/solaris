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
			
			//Mock $sce service
			$provide.value('$sce', {
				trustAsHtml: function(html) { return html;}
			});
			
	      	return null;
		});
		
	});
	
	beforeEach(angular.mock.inject(function($controller, $rootScope, _galleryService_, _$sce_) {

		$sce = _$sce_;
		galleryService = _galleryService_;
		
		mockScope = $rootScope.$new();
		controller = $controller("galleryController", {
			$scope: mockScope
		});
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("should have contextPath defined", function() {
		expect(mockScope.contextPath).toBeDefined();
	});
	
	it("should have searchPhrase defined and empty", function() {
		expect(mockScope.searchPhrase).toBeDefined();
		expect(mockScope.searchPhrase).toEqual("");
	});
	
	it("inital galleries should be loaded", function() {
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	
	it("should be able to load galleries", function() {
		
		mockScope.data.galleries = [];
		expect(mockScope.data.galleries.length).toEqual(0);
		
		mockScope.loadGalleries();
		expect(mockScope.data.galleries.length).toEqual(2);
	});
	
	it("should be possible to assign specific class to gallery block depending on position", function() {
		
		expect(mockScope.getBlockClassName).toBeDefined();
		expect(mockScope.getBlockClassName(0)).toEqual("left-block");
		expect(mockScope.getBlockClassName(1)).toEqual("middle-block");
		expect(mockScope.getBlockClassName(2)).toEqual("right-block");
	});
	
	it("should be possible to higlight text", function() {
		
		var text = "Apples and oranges",
			search = "es";
		
		expect(mockScope.highlight).toBeDefined();
		expect(mockScope.highlight(text)).toEqual(text);
		expect(mockScope.highlight(text, search)).toEqual('Appl<span class="highlightedText">es</span> and orang<span class="highlightedText">es</span>');
	});
	
});



