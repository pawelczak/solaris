describe("Tests for addPhotoFormFactory", function() {
	
	var addPhotoFormFactory,
		emptyForm;
	
	
	beforeEach(function() {
		angular.mock.module("solarisAdmin");
		emptyForm = {
				title: "",
				galleryId: 0,
				description: "",
				imageSrc: ""
		};
	});
	
    beforeEach(inject(function($injector) {
    	addPhotoFormFactory = $injector.get('addPhotoFormFactory');
    }));
	
	
    it("should have scpecific fields", function() {
    	
    	//execute
		var actualAddForm = addPhotoFormFactory.create();
		
		//assert
		expect(actualAddForm.title).toEqual(emptyForm.title);
		expect(actualAddForm.galleryId).toEqual(emptyForm.galleryId);
		expect(actualAddForm.description).toEqual(emptyForm.description);
		expect(actualAddForm.imageSrc).toEqual(emptyForm.imageSrc);
    });
    
	it("should create blank addForm", function() {
		
		//execute
		var actualAddForm = addPhotoFormFactory.create();
		
		//assert
		expect(actualAddForm).toEqual(emptyForm);
	})
	
	it("should reset form", function() {
		var addForm = {
				title: "gr8 name",
				galleryId: 6,
				description: "desc",
				imageSrc: ""
		};
		
		//execute
		addPhotoFormFactory.reset(addForm);
		
		//assert
		expect(addForm).toEqual(emptyForm);
	});
	
});
