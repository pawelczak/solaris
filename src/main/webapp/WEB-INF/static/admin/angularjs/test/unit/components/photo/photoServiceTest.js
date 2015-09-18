describe('Tests for photoService', function() {

    var photoService,
    	photoListApiUrl = "/admin/api/photo/list",
	    photoAddApiUrl = "/admin/api/photo/add",
	    photoEditApiUrl = "/admin/api/photo/edit",
	    photoEditImageApiUrl = "/admin/api/photo/editImage",
	    photoDeleteApiUrl = "/admin/api/photo/delete";

    describe('Define service methods', function() {

        beforeEach(module('solarisAdmin'));

        beforeEach(inject(function($injector) {
        	photoService = $injector.get('photoService');
        }));

        it('should have methods defined', function() {
        	expect(photoService.findAll).toBeDefined();
        	expect(photoService.add).toBeDefined();
        	expect(photoService.edit).toBeDefined();
        	expect(photoService.editImage).toBeDefined();
        	expect(photoService.remove).toBeDefined();
        });

    });

    describe(' [$httpBackend] ', function() {

        var $httpBackend;

        beforeEach(module('solarisAdmin'));

        beforeEach(inject(function($injector) {
        	photoService = $injector.get('photoService');
            $httpBackend = $injector.get('$httpBackend');
        }));

        afterEach(function() {
            $httpBackend.verifyNoOutstandingExpectation();
            $httpBackend.verifyNoOutstandingRequest();
        });

        describe('Testing HTTP GET requests using $httpBackend', function() {

            beforeEach(function() {
                $httpBackend.when('GET', photoListApiUrl).respond({
                });
            });

            it('should make a GET request', function() {
                $httpBackend.expectGET(photoListApiUrl);
                photoService.findAll();
                $httpBackend.flush();
            });

        });        

    });
    
    
    describe(' [spies] ', function() {

        var httpMock;

        beforeEach(module('solarisAdmin', function($provide) {
            httpMock = jasmine.createSpyObj('$http', ['get', 'post']);
            $provide.value('$http', httpMock);
        }));

        beforeEach(inject(function($injector) {
        	photoService = $injector.get('photoService');
        }));

        describe('Using spies to test HTTP GET requests', function() {

            it('should make a GET request (spy)', function() {
            	photoService.findAll();
                expect(httpMock.get).toHaveBeenCalled();
            });

        });

        describe('Using spies to test HTTP POST requests', function() {
        	
        	var config = {
    			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    			transformRequest: jasmine.any(Function)
    		};
        	
        	
            it('should make a method "add" call', function() {
            	var addPhotoForm = {
        			title: "Photo title",
        			galleryId: 42,
        			description: "Photo desc",
        			imageSrc: "/src/img.jpg"
        		};
            	
                photoService.add(addPhotoForm);
                expect(httpMock.post).toHaveBeenCalledWith(photoAddApiUrl, addPhotoForm, config);
            });
            
            
            it('should make a method "edit" call', function() {
            	var editPhotoForm = {
        			id: 2,
        			title: "Photo title",
        			galleryId: 42,
        			description: "Photo desc",
        			imageSrc: "/src/img.jpg"
        		};
            	
            	photoService.edit(editPhotoForm);
                expect(httpMock.post).toHaveBeenCalledWith(photoEditApiUrl, editPhotoForm, config);
            });
            
            it('should make a method "editImage" call', function() {
            	var editPhotoForm = {
        			photoId: 2,
        			imageSrc: "/src/img.jpg"
        		};
            	
            	photoService.edit(editPhotoForm);
                expect(httpMock.post).toHaveBeenCalledWith(photoEditApiUrl, editPhotoForm, config);
            });
            
            it('should make a method "remove" call', function() {
            	var removePhotoForm = {
        			ids: [1, 5, 9]
        		};
            	
            	photoService.remove(removePhotoForm);
                expect(httpMock.post).toHaveBeenCalledWith(photoDeleteApiUrl, removePhotoForm, config);
            });

        });
        
    });
    
});