describe('Gallery List spec test', function() {

    beforeEach(function() {
        browser.driver.manage().window().maximize();
    });

    it('should have scope values initialized', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/');
        
        
        element.all(by.repeater('gallery in data.galleries')).then(function(gallery) {
        
            //given
            var name = element.all(by.binding('gallery.name')).first(),
            	featuredImage = element.all(by.css('.gallery-block img')).first();
        
        
            //assert
            expect(name.getText()).toEqual('Alpy Bernejskie')
            expect(featuredImage.getAttribute("src")).toEqual("http://localhost:8080/solaris/photos/36.jpg");

        });
        
    });
    
    
    it('should be possible to change from gallery list view to photo list view', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery');
        
        
        element.all(by.repeater('gallery in data.galleries')).then(function(gallery) {
        
            //given
            var button = element.all(by.css(".gallery-block  a")).first();
        
            //execute
            button.click();
        
            //assert
            expect(browser.getLocationAbsUrl()).toContain('/gallery/56');
        });
        
    });



});

