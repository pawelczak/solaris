describe('Navigation And Routing', function () {

	beforeEach(function () {
		browser.get('http://localhost:8080/solaris/admin/');
	});

	describe(' - login - ', function() {
		
		it('should default to dashboard page', function () {
            expect(browser.getLocationAbsUrl()).toBe('/login');
		});
		
	});
	
	describe(' - default - ', function() {
	
		it('should default to dashboard page', function () {
            expect(browser.getLocationAbsUrl()).toBe('/dashboard');
		});
	
		
		it('should redirect to dashboard page if an unknown url is provided', function () {
            browser.get('http://localhost:8080/solaris/admin/#/dummy');
            expect(browser.getLocationAbsUrl()).toBe('/dashboard');
		});
		
	});
	
	describe(' - view - ', function() {
		
		it('should have dashboard page', function () {
			
			browser.get('http://localhost:8080/solaris/admin/#/dashboard');
			
		    expect(browser.getLocationAbsUrl()).toBe('/dashboard');
		});
		
		it('should have gallery page', function () {
			
			browser.get('http://localhost:8080/solaris/admin/#/gallery');
			
            expect(browser.getLocationAbsUrl()).toBe('/gallery');
		});
		
		
		it('should have photo page', function () {
			
			browser.get('http://localhost:8080/solaris/admin/#/photo');
			
            expect(browser.getLocationAbsUrl()).toBe('/photo');
		});

		
		it('should have article page', function () {
			
			browser.get('http://localhost:8080/solaris/admin/#/article');
			
		    expect(browser.getLocationAbsUrl()).toBe('/article');
		});

	});

	
});



