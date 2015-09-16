describe('GalleryService tests', function() {

    var httpMock,
        galleryService;

    beforeEach(module('solarisSite', function($provide) {
        httpMock = jasmine.createSpyObj('$http', ['get']);
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
});