angular.module("solarisAdmin")
.config(["$routeProvider", function($routeProvider) {
	
	$routeProvider.when("/article", {
		controller: "articleController",
		templateUrl: "../static/admin/angularjs/view/article/main.html"
	});

	$routeProvider.when("/gallery", {
		controller: "galleryController",
		templateUrl: "../static/admin/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/photo", {
		controller: "photoController",
		templateUrl: "../static/admin/angularjs/view/photo/main.html"
	});
	
	$routeProvider.when("/dashboard", {
		templateUrl: "../static/admin/angularjs/view/dashboard/dashboard.html"
	})

	$routeProvider.otherwise({
		redirectTo: "/dashboard"
	});
	
}]);

