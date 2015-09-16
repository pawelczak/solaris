angular.module("solarisSite")
.config(["$routeProvider", function($routeProvider) {
	
	$routeProvider.when("/gallery", {
		controller: "galleryController",
		templateUrl: "../static/site/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/gallery/:galleryId", {
		controller: "photoController",
		templateUrl: "../static/site/angularjs/view/photo/main.html"
	});
	
	$routeProvider.when("/gallery/:galleryId/:photoId", {
		controller: "photoViewerController",
		templateUrl: "../static/site/angularjs/view/photoViewer/main.html"
	});

	$routeProvider.otherwise({
		redirectTo: "/gallery"
	});
	
}]);

