angular.module("solarisAdmin", ["ngRoute", "pagingFilters"])
.config(function($routeProvider) {
	
	$routeProvider.when("/article", {
		templateUrl: "../static/admin/angularjs/view/article/main.html"
	});

	$routeProvider.when("/gallery", {
		templateUrl: "../static/admin/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/photo", {
		templateUrl: "../static/admin/angularjs/view/photo/main.html"
	});

	$routeProvider.otherwise({
		templateUrl: "../static/admin/angularjs/view/dashboard.html"
	});
	
});