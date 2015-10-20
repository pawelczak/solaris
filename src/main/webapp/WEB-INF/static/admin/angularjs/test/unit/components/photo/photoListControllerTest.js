/* Angularjs controller photoListController tests
 * 
 * @author Łukasz Pawełczak
 * 
 */
describe("Tests photoListController", function() {
	
	
	
	var mockScope = {},
		controller;
	
	
    //------------------------ CONFIG --------------------------	
	
	beforeEach(angular.mock.module("solarisAdmin"));
	
	beforeEach(angular.mock.inject(function($controller, $rootScope) {
		
		mockScope = $rootScope.$new();
		controller = $controller("photoListController", {
			$scope: mockScope
		})
		
	}));
	
	
    //------------------------ TESTS --------------------------	
	
	it("base variables should be defined", function() {
		expect(mockScope.selectedPage).toEqual(1);
		expect(mockScope.numberOfPages).toEqual(1);
	});
	
	
	describe("Order", function() {
	
		it("filter values should be defined", function() {
			expect(mockScope.photoOrderByPropertyFilterValues).toContain("title");
			expect(mockScope.photoOrderByPropertyFilterValues).toContain("description");
		});
		
		it("property should be defined", function() {
			expect(mockScope.photoOrderByProperty).toEqual("title");
		});
		
		it("should be possible to set order", function() {
			
			/*
			//given
			mockScope.selectedPage = 2;
			mockScope.photoOrderByProperty = "title";
			
			//execute
			mockScope.setOrderBy("description");
			
			//assert
			photoOrderByProperty
			expect(mockScope.selectedPage).toEqual(1);
			expect(mockScope.photoOrderByProperty).toEqual("description");
			expect(mockScope.$parent.$parent.photoOrderByProperty).toEqual("description");
			*/
		});
		
	});
	
	
	describe("Paging", function() {
		
		
		it("filter values should be defined", function() {
			expect(mockScope.photoPageSizeFilterValues).toContain(5);
			expect(mockScope.photoPageSizeFilterValues).toContain(10);
			expect(mockScope.photoPageSizeFilterValues).toContain(20);
			expect(mockScope.photoPageSizeFilterValues).toContain(50);
		});
		
		it("pageSize should be defined", function() {
			expect(mockScope.photoPageSize).toEqual(10);
		});
		
		it("should be possible to select page", function() {
			
			//given
			mockScope.selectedPage = 3;
			
			//execute
			mockScope.selectPage(2);
			
			//assert
			expect(mockScope.selectedPage).toEqual(2);
		});
		
		it("should be possible to select next page", function() {
			
			//given
			mockScope.selectedPage = 1;
			
			//execute
			mockScope.selectNextPage();
			
			//assert
			expect(mockScope.selectedPage).toEqual(2);
		});
		
		it("should be possible to select previous page", function() {
			
			//given
			mockScope.selectedPage = 3;
			
			//execute
			mockScope.selectPreviousPage();
			
			//assert
			expect(mockScope.selectedPage).toEqual(2);
		});		
		
		it("should return btn class when selected page matches", function() {
			//given
			mockScope.selectedPage = 2;
			
			//execute
			var pageClass = mockScope.getPageClass(2);
			
			//assert
			expect(pageClass).toEqual("btn-primary");
		});
		
		it("should not return btn class when selected page matches", function() {
			//given
			mockScope.selectedPage = 3;
			
			//execute
			var pageClass = mockScope.getPageClass(2);
			
			//assert
			expect(pageClass).toEqual("");
		});
		
		
		describe("previous button ", function() {
		
			it("should show previous button enabled", function() {
				
				//given
				mockScope.selectedPage = 1;
				
				//execute & assert
				expect(mockScope.isPreviousButtonDisabled()).toEqual(true);
			});
			
			it("should show previous button disabled", function() {
				
				//given
				mockScope.selectedPage = 3;
				
				//execute & assert
				expect(mockScope.isPreviousButtonDisabled()).toEqual(false);
			});
		
		});
		
		
		describe("next button ", function() {
			
			beforeEach(function() {
				mockScope.data = {};
				mockScope.data.photos = [{}]; //one element
				mockScope.photoPageSize = 10;
			});
			
			it("should show next button enabled", function() {
				
				//given
				mockScope.selectedPage = 1;
				
				//execute & assert
				expect(mockScope.isNextButtonDisabled()).toEqual(true);
			});
			
			it("should show next button disabled", function() {
				
				//given
				mockScope.selectedPage = 3;
				 
				//execute & assert
				expect(mockScope.isNextButtonDisabled()).toEqual(false);
			});
			
		});
		
	});
	
	
	
});



