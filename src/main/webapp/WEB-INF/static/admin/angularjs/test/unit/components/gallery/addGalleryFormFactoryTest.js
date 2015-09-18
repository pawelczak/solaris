describe("Tests for addGalleryFormFactory", function() {
	
	var addGalleryFormFactory,
		emptyForm;
	
	
	beforeEach(function() {
		angular.mock.module("solarisAdmin");
		emptyForm = {
				name: "",
				description: "",
				visible: false
		};
	});
	
    beforeEach(inject(function($injector) {
    	addGalleryFormFactory = $injector.get('addGalleryFormFactory');
    }));
	
	
    it("should have scpecific fields", function() {
    	
    	//execute
		var actualAddForm = addGalleryFormFactory.create();
		
		//assert
		expect(actualAddForm.name).toEqual(emptyForm.name);
		expect(actualAddForm.description).toEqual(emptyForm.description);
		expect(actualAddForm.visible).toEqual(emptyForm.visible);
    });
    
	it("should create blank addForm", function() {
		
		//execute
		var actualAddForm = addGalleryFormFactory.create();
		
		//assert
		expect(actualAddForm).toEqual(emptyForm);
	})
	
	it("should reset form", function() {
		var addForm = {
				name: "gr8 name",
				description: "desc",
				visible: true
		};
		
		//execute
		addGalleryFormFactory.reset(addForm);
		
		//assert
		expect(addForm).toEqual(emptyForm);
	});
	
});
