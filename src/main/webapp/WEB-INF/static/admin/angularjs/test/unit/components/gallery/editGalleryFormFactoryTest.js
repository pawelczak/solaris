describe("Tests for editGalleryFormFactory", function() {
	
	var editGalleryFormFactory,
		emptyForm;
	
	
	beforeEach(function() {
		angular.mock.module("solarisAdmin");
		emptyForm = {
				id: 0,
				name: "",
				description: "",
				visible: false
		};
		
	});
	
    beforeEach(inject(function($injector) {
    	editGalleryFormFactory = $injector.get('editGalleryFormFactory');
    }));
	
    
    
    it("should have scpecific fields", function() {
    	
    	//execute
		var actualAddForm = editGalleryFormFactory.create();
		
		//assert
		expect(actualAddForm.id).toEqual(emptyForm.id);
		expect(actualAddForm.name).toEqual(emptyForm.name);
		expect(actualAddForm.description).toEqual(emptyForm.description);
		expect(actualAddForm.visible).toEqual(emptyForm.visible);
    });
    
	it("should create blank addForm", function() {
		
		//execute
		var actualAddForm = editGalleryFormFactory.create();
		
		//assert
		expect(actualAddForm).toEqual(emptyForm);
	})
	
	it("should reset form", function() {
		var editForm = {
				id: 4,
				name: "gr8 name",
				description: "desc",
				visible: true
		};
		
		//execute
		editGalleryFormFactory.reset(editForm);
		
		//assert
		expect(editForm).toEqual(emptyForm);
	});
	
	it("should be able to set form", function() {
		var gallery = {
				id: 22,
				name: "new gallery name",
				description: "new gallery desc",
				visible: true
		},
		form = editGalleryFormFactory.create();
		
		//execute
		editGalleryFormFactory.set(form, gallery);
		
		//assert
		expect(form.id).toEqual(gallery.id);
		expect(form.name).toEqual(gallery.name);
		expect(form.description).toEqual(gallery.description);
		expect(form.visible).toEqual(gallery.visible);
		
	});
    
    
});