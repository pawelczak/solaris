describe('Admin routes', function() {
	
	var $route,
		$rootScope,
		$location,
		$httpBackend;
  
		
	beforeEach(function() {
		
		angular.mock.module('solarisAdmin');
		
		angular.mock.inject(function($injector){
			$route = $injector.get('$route');
			$rootScope = $injector.get('$rootScope');
			$location = $injector.get('$location');
			$httpBackend = $injector.get('$httpBackend');
			
			$httpBackend.when('GET', '../static/admin/angularjs/view/dashboard/dashboard.html').respond('dashboard');
			$httpBackend.when('GET', '../static/admin/angularjs/view/article/main.html').respond('article');
			$httpBackend.when('GET', '../static/admin/angularjs/view/gallery/main.html').respond('gallery');
			$httpBackend.when('GET', '../static/admin/angularjs/view/photo/main.html').respond('photo');
		});
	});
	  
	
	
	it('should navigate to dashboard', function() {
		
		$rootScope.$apply(function() {
			$location.path('/dashboard');
		});
	
		expect($location.path()).toBe('/dashboard');
		expect($route.current.templateUrl).toBe('../static/admin/angularjs/view/dashboard/dashboard.html');
	});
	
	it('should navigate to gallery', function() {
		
		$rootScope.$apply(function() {
			$location.path('/gallery');
		});
	
		expect($location.path()).toBe('/gallery');
		expect($route.current.templateUrl).toBe('../static/admin/angularjs/view/gallery/main.html');
		expect($route.current.controller).toBe('solarisAdminGalleryCtrl');
	});
	
	
	it('should navigate to photo view', function() {
		
		$rootScope.$apply(function() {
			$location.path('/photo');
		});
	
		expect($location.path()).toBe('/photo');
		expect($route.current.templateUrl).toBe('../static/admin/angularjs/view/photo/main.html');
		expect($route.current.controller).toBe('solarisAdminPhotoCtrl');
	});
	
	
	it('should navigate to article view', function() {
		
		$rootScope.$apply(function() {
			$location.path('/article');
		});
	
		expect($location.path()).toBe('/article');
		expect($route.current.templateUrl).toBe('../static/admin/angularjs/view/article/main.html');
		expect($route.current.controller).toBe('solarisAdminArticleCtrl');
	});
	

	it('should redirect NOT REGISTERED urls to gallery', function() {
		
		$rootScope.$apply(function() {
			$location.path('/other');
		});
		
		expect($location.path()).toBe('/dashboard');
		expect($route.current.templateUrl).toBe('../static/admin/angularjs/view/dashboard/dashboard.html');
	});
	
});


