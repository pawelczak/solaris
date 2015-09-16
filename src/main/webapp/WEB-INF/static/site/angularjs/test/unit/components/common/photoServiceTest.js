describe("PhotoService tests", function() {
    
    
    var presentationListService,
        $httpBackend,
        galleryId,
        url;
    
    beforeEach(function() {

        angular.mock.module("solarisSite");
    });
    
    beforeEach(angular.mock.inject(function($injector) {
        
        photoService = $injector.get('photoService');
        $httpBackend = $injector.get('$httpBackend');
        galleryId = 2;
        url = "/api/gallery/" + galleryId + "/photos";
    }));

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    describe('Testing HTTP GET requests using $httpBackend', function() {

        beforeEach(function() {
            $httpBackend.when('GET', url).respond({
                foo: "bar"
            });
        });

        it('should make a GET request', function() {
            $httpBackend.expectGET(url);
            photoService.findAllByGalleryId(galleryId);
            $httpBackend.flush();
        });

    });
    
});


