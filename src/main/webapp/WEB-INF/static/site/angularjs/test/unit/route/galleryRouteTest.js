describe('Gallery Routes', function(){
	var $route,
		$rootScope,
		$location,
		$httpBackend;
  
		
	beforeEach(function() {
		
		angular.mock.module('solarisSite');
		
		angular.mock.inject(function($injector){
			$route = $injector.get('$route');
			$rootScope = $injector.get('$rootScope');
			$location = $injector.get('$location');
			$httpBackend = $injector.get('$httpBackend');
			  
			$httpBackend.when('GET', '../static/site/angularjs/view/gallery/main.html').respond('gallery');
			$httpBackend.when('GET', '../static/site/angularjs/view/photo/main.html').respond('gallery/1');
			$httpBackend.when('GET', '../static/site/angularjs/view/photoViewer/main.html').respond('gallery/1/2');
		});
	});
	  
	
	it('should navigate to gallery', function() {
		
		$rootScope.$apply(function() {
			$location.path('/gallery');
		});
	
		expect($location.path()).toBe('/gallery');
		expect($route.current.templateUrl).toBe('../static/site/angularjs/view/gallery/main.html');
		expect($route.current.controller).toBe('galleryController');
	});
	
	
	it('gallery/:id should navigate to photo view', function() {
		
		$rootScope.$apply(function() {
			$location.path('/gallery/1');
		});
	
		expect($location.path()).toBe('/gallery/1');
		expect($route.current.templateUrl).toBe('../static/site/angularjs/view/photo/main.html');
		expect($route.current.controller).toBe('photoController');
	});
	
	
	it('gallery/:id should navigate to photo view', function() {
		
		$rootScope.$apply(function() {
			$location.path('/gallery/1/2');
		});
	
		expect($location.path()).toBe('/gallery/1/2');
		expect($route.current.templateUrl).toBe('../static/site/angularjs/view/photoViewer/main.html');
		expect($route.current.controller).toBe('photoViewerController');
	});
	

	it('should redirect NOT REGISTERED urls to gallery', function() {
		
		$rootScope.$apply(function() {
			$location.path('/other');
		});
		
		expect($location.path()).toBe('/gallery');
		expect($route.current.templateUrl).toBe('../static/site/angularjs/view/gallery/main.html');
		expect($route.current.controller).toBe('galleryController');
	});
	
});


