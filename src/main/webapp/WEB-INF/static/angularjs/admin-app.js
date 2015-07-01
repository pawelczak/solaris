angular.module("solarisAdmin", ["ngRoute"])
.config(function($routeProvider) {
	
	$routeProvider.when("/article", {
		templateUrl: "../static/angularjs/view/article/list.html"
	});

	$routeProvider.when("/gallery", {
		templateUrl: "../static/angularjs/view/gallery/list.html"
	});
	
	$routeProvider.when("/photo", {
		templateUrl: "../static/angularjs/view/photo/list.html"
	});

	$routeProvider.otherwise({
		templateUrl: "../static/angularjs/view/dashboard.html"
	});
	
});