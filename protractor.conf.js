exports.config = {
	seleniumAddress: "http://localhost:4444/wd/hub",
	specs: ['src/main/webapp/WEB-INF/static/site/angularjs/test/e2e/route/*.js'],
    framework: "jasmine2",
    //rootElement: '', //needs to be define when ng-app is not located in html tag

	onPrepare: function() {
		browser.driver.manage().window().maximize();
		//browser.get('http://localhost:8080/solaris/');
	}
    
};

/*
exports.config = {
  directConnect: true,

  capabilities: {
    'browserName': 'chrome'
  },

  framework: 'jasmine2',

  specs: ['angularjs/test/e2e/route/routeIT.js'],

  onPrepare: function() {
    browser.driver.manage().window().maximize();
    //browser.get('http://juliemr.github.io/protractor-demo');
  }
};*/