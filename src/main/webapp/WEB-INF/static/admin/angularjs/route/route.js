angular.module("solarisAdmin")
.config(["$routeProvider", function($routeProvider) {
	
	$routeProvider.when("/article", {
		controller: "solarisAdminArticleCtrl",
		templateUrl: "../static/admin/angularjs/view/article/main.html"
	});

	$routeProvider.when("/gallery", {
		controller: "solarisAdminGalleryCtrl",
		templateUrl: "../static/admin/angularjs/view/gallery/main.html"
	});
	
	$routeProvider.when("/photo", {
		controller: "solarisAdminPhotoCtrl",
		templateUrl: "../static/admin/angularjs/view/photo/main.html"
	});
	
	$routeProvider.when("/dashboard", {
		templateUrl: "../static/admin/angularjs/view/dashboard/dashboard.html"
	})

	$routeProvider.otherwise({
		redirectTo: "/dashboard"
	});
	
}]);

