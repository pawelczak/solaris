angular.module("solarisSite", ["ngRoute"])
.config(function($routeProvider) {
	
	$routeProvider.when("/gallery", {
		templateUrl: "../static/site/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/gallery/:galleryId", {
		templateUrl: "../static/site/angularjs/view/photo/main.html"
	});
	
	$routeProvider.when("/gallery/:galleryId/:photoId", {
		templateUrl: "../static/site/angularjs/view/photo/photoViewer.html"
	});

	$routeProvider.otherwise({
		templateUrl: "../static/site/angularjs/view/gallery/main.html"
	});
	
});