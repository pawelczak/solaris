describe("GallerySearchFilter test", function() {

	var gallerySearchFilter;
	
	beforeEach(function() {
		
		angular.mock.module("solarisSite");
		
		angular.mock.inject(function($filter) {
			gallerySearchFilter = $filter("gallerySearchFilter");
		});
		
	});
	
	
	it("should return data when not search phrase", function() {
		
		//given
		var data = [{name: "apple"}, {name: "orange"}];
		
		//execute
		var actualData = gallerySearchFilter(data);
		
		//assert
		expect(data).toEqual(actualData);
	});
	
	it("should return data when search phrase is empty", function() {
		
		//given
		var data = [{name: "apple"}, {name: "orange"}],
			searchPhrase = "";
		
		//execute
		var actualData = gallerySearchFilter(data, searchPhrase);
		
		//assert
		expect(data).toEqual(actualData);
	});
	
	if("should return data that match search phrase", function() {
		//given
		var data = [{name: "apple"}, {name: "orange"}, {name: "prune"}],
			searchPhrase = "p",
			expectedData = [{name: "apple"}, {name: "prune"}];
		
		//execute
		var actualData = gallerySearchFilter(data, searchPhrase);
		
		//assert
		expect(expectedData).toEqual(actualData);
		
	});
	
});
