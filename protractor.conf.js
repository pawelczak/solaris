exports.config = {
	seleniumAddress: "http://localhost:4444/wd/hub",
	specs: [
	        //'src/main/webapp/WEB-INF/static/admin/angularjs/test/e2e/route/*.js',
	        //'src/main/webapp/WEB-INF/static/site/angularjs/test/e2e/route/*.js'
	        'src/main/webapp/WEB-INF/static/site/angularjs/test/e2e/components/**/*.js'
	        ],
	framework: "jasmine2",
	//rootElement: '', //needs to be define when ng-app is not located in html tag
	
	onPrepare: function() {
		browser.driver.manage().window().maximize();
	}

};

