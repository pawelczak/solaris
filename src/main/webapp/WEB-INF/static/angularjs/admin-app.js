angular.module("solarisAdmin", ["ngRoute"])
.config(function($routeProvider) {
	
	$routeProvider.when("/article", {
		templateUrl: "../static/angularjs/admin/view/article/list.html"
	});

	$routeProvider.when("/gallery", {
		templateUrl: "../static/angularjs/admin/view/gallery/main.html"
	});
	
	$routeProvider.when("/photo", {
		templateUrl: "../static/angularjs/admin/view/photo/list.html"
	});

	$routeProvider.otherwise({
		templateUrl: "../static/angularjs/admin/view/dashboard.html"
	});
	
});