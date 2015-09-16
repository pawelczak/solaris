describe('Photo list spec test', function() {

    beforeEach(function() {
        browser.driver.manage().window().maximize();
    });

    it('should have scope values initialized', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery/56');
        
        
        element.all(by.repeater('photo in data.photos')).then(function(photo) {
        
            //given
            var galleryName = element.all(by.binding('data.gallery.name')).first(),
            	photoImage = element.all(by.css('.photo-block img')).first();
        
        
            //assert
            expect(galleryName.getText()).toEqual('Alpy Bernejskie')
            expect(photoImage.getAttribute("src")).toEqual("http://localhost:8080/solaris/photos/36.jpg");

        });
        
    });
    
    it('should be possible to change from photo list view to single photo view', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery/56');
        
        
        element.all(by.repeater('photo in data.photos')).then(function(photo) {
        
            //given
            var button = element.all(by.css(".photo-block  a")).first();
        
            //execute
            button.click();
        
            //assert
            expect(browser.getLocationAbsUrl()).toContain('/gallery/56/36');
        });
        
    });
    
    it('should be possible to go back and change to gallery list view', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery/56');
        
        
        element.all(by.repeater('photo in data.photos')).then(function(photo) {
        
            //given
            var button = element.all(by.css(".link-back  a")).first();
        
            //execute
            button.click();
        
            //assert
            expect(browser.getLocationAbsUrl()).toContain('/gallery');
        });
        
    });
    
    



});

