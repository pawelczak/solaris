describe("Tests for editPhotoFormFactory", function() {
	
	var editPhotoFormFactory,
		emptyForm;
	
	
	beforeEach(function() {
		angular.mock.module("solarisAdmin");
		emptyForm = {
				id: 0,
				title: "",
				galleryId: 0,
				description: "",
				imageSrc: ""
		};
		
	});
	
    beforeEach(inject(function($injector) {
    	editPhotoFormFactory = $injector.get('editPhotoFormFactory');
    }));
	
    
    
    it("should have scpecific fields", function() {
    	
    	//execute
		var actualEditForm = editPhotoFormFactory.create();
		
		//assert
		expect(actualEditForm.id).toEqual(emptyForm.id);
		expect(actualEditForm.title).toEqual(emptyForm.title);
		expect(actualEditForm.galleryId).toEqual(emptyForm.galleryId);
		expect(actualEditForm.description).toEqual(emptyForm.description);
		expect(actualEditForm.imageSrc).toEqual(emptyForm.imageSrc);
    });
    
	it("should create blank addForm", function() {
		
		//execute
		var actualEditForm = editPhotoFormFactory.create();
		
		//assert
		expect(actualEditForm).toEqual(emptyForm);
	})
	
	it("should reset form", function() {
		var editForm = {
				id: 0,
				title: "Photo title",
				galleryId: 6,
				description: "Nice view",
				imageSrc: "/img/src.jpg"
		};
		
		//execute
		editPhotoFormFactory.reset(editForm);
		
		//assert
		expect(editForm).toEqual(emptyForm);
	});
	
	it("should be able to set form", function() {
		var photo = {
				id: 0,
				title: "Photo title",
				gallery: {id: 6},
				description: "Nice view",
				imageSrc: "/img/src.jpg"
		},
		form = editPhotoFormFactory.create();
		
		//execute
		editPhotoFormFactory.set(form, photo);
		
		//assert
		expect(form.id).toEqual(photo.id);
		expect(form.name).toEqual(photo.name);
		expect(form.name).toEqual(photo.name);
		expect(form.description).toEqual(photo.description);
		expect(form.visible).toEqual(photo.visible);
		
	});
    
    
});

