describe('Navigation And Routing', function () {

	beforeEach(function () {
		browser.get('http://localhost:8080/solaris/photo');
	});

	describe(' - default - ', function() {
	
		it('should default to gallery page', function () {
            expect(browser.getLocationAbsUrl()).toBe('/gallery');
		});
	
		it('should redirect to gallery page if an unknown url is provided', function () {
            browser.get('http://localhost:8080/solaris/photo#/dummy');
            expect(browser.getLocationAbsUrl()).toBe('/gallery');
		});
			
	});

	

/*
	it('should show a load button on home page', function () {
		var button = element(by.buttonText('load'));
		expect(button.isPresent()).toBeTruthy();
	});

	it('should navigate to emcees page on click of the load button', function () {
		var button = element(by.buttonText('load'));
		button.click();
		expect(browser.getLocationAbsUrl()).toContain('/emcees/1');
	});
*/
});



