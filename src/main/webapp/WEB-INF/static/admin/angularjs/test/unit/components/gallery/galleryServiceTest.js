describe('Tests for galleryService ', function() {

    var galleryService,
    	galleryListApiUrl = "/admin/api/gallery/list",
    	galleryAddApiUrl = "/admin/api/gallery/add",
    	galleryEditApiUrl = "/admin/api/gallery/edit",
    	galleryDeleteApiUrl = "/admin/api/gallery/delete",
    	galleryList = [
	                    {id: 1, name: "Tatry #12344", description: null, visible: false},
	                    {id: 2, name: "Tatry #2", description: null, visible: false}
	                    ];

    describe('method is defined', function() {

        beforeEach(module('solarisAdmin'));

        beforeEach(inject(function($injector) {
        	galleryService = $injector.get('galleryService');
        }));

        it('should have methods defined', function() {
            expect(galleryService.findAll).toBeDefined();
            expect(galleryService.add).toBeDefined();
            expect(galleryService.edit).toBeDefined();
            expect(galleryService.remove).toBeDefined();
        });

    });

    describe(' [$httpBackend] ', function() {

        var $httpBackend;

        beforeEach(module('solarisAdmin'));

        beforeEach(inject(function($injector) {
        	galleryService = $injector.get('galleryService');
            $httpBackend = $injector.get('$httpBackend');
        }));

        afterEach(function() {
            $httpBackend.verifyNoOutstandingExpectation();
            $httpBackend.verifyNoOutstandingRequest();
        });

        describe('Testing HTTP GET requests using $httpBackend', function() {

            beforeEach(function() {
                $httpBackend.when('GET', galleryListApiUrl).respond({
                    foo: "bar"
                });
            });

            it('should make a GET request', function() {
                $httpBackend.expectGET(galleryListApiUrl);
                galleryService.findAll();
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
        	galleryService = $injector.get('galleryService');
        }));

        describe('Using spies to test HTTP GET requests', function() {
        	
            it('should make a GET request (spy)', function() {
            	galleryService.findAll();
                expect(httpMock.get).toHaveBeenCalled();
            });

        });

        describe('Using spies to test HTTP POST requests', function() {
        	
        	var config = {
    			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    			transformRequest: jasmine.any(Function)
    		};
        	
        	
            it('should make a method "add" call', function() {
            	var addGalleryForm = {
        			name: "Polnische Tatre",
        			description: "Polnische Tatre sind sehr schon.",
        			visible: false
        		};
            	
                galleryService.add(addGalleryForm);
                expect(httpMock.post).toHaveBeenCalledWith(galleryAddApiUrl, addGalleryForm, config);
            });
            
            
            it('should make a method "edit" call', function() {
            	var editGalleryForm = {
        			id: 4,
        			name: "Polnische Tatre",
        			description: "Polnische Tatre sind sehr schon.",
        			visible: false
        		};
            	
                galleryService.edit(editGalleryForm);
                expect(httpMock.post).toHaveBeenCalledWith(galleryEditApiUrl, editGalleryForm, config);
            });
            
            it('should make a method "remove" call', function() {
            	var removeGalleryForm = {
        			ids: [1, 5, 9]
        		};
            	
                galleryService.remove(removeGalleryForm);
                expect(httpMock.post).toHaveBeenCalledWith(galleryDeleteApiUrl, removeGalleryForm, config);
            });

        });


    });
    
});

