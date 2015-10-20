/* Angularjs controller adminController tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("adminController", function() {
	
	
	
	var mockScope = {},
		controller;
	
	
    //------------------------ CONFIG --------------------------	
	
	beforeEach(angular.mock.module("solarisAdmin"));
	
	beforeEach(angular.mock.inject(function($controller, $rootScope) {
		
		mockScope = $rootScope.$new();
		controller = $controller("adminController", {
			$scope: mockScope
		})
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("Variables", function() {
		expect(mockScope.imageVisible).toEqual("/static/admin/image/visible.png");
	});
	
	
	it("Methods", function() {
		expect(mockScope.getImageVisible()).toEqual("/static/admin/image/visible.png");
	});
	
	
});



