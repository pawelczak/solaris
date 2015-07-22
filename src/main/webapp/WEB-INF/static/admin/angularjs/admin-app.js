angular.module("solarisAdmin", ["ngRoute"])
.config(function($routeProvider) {
	
	$routeProvider.when("/article", {
		templateUrl: "../static/admin/angularjs/view/article/list.html"
	});

	$routeProvider.when("/gallery", {
		templateUrl: "../static/admin/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/photo", {
		templateUrl: "../static/admin/angularjs/view/photo/list.html"
	});

	$routeProvider.otherwise({
		templateUrl: "../static/admin/angularjs/view/dashboard.html"
	});
	
});