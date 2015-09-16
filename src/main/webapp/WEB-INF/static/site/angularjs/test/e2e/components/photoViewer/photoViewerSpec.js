describe('Single photo viewer spec test', function() {

    beforeEach(function() {
        browser.driver.manage().window().maximize();
    });

    it('should have scope values initialized', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery/56/36');
        
        //given
        var title = element.all(by.binding('data.photos[selectedPhotoIndex].title')).first(),
        	photoImage = element.all(by.css('.photoViewer img')).first();
    
    
        //assert
        expect(title.getText()).toEqual('Zdjecie Alp Bernejskich')
        expect(photoImage.getAttribute("src")).toEqual("http://localhost:8080/solaris/photos/36.jpg");

    });
    
    
    it('should be possible to change from single photo list view to photo list view', function () {
        
        
        browser.get('http://localhost:8080/solaris/photo/#/gallery/56/36');
        
        
        element.all(by.repeater('photo in data.photos')).then(function(photo) {
        
            //given
            var button = element.all(by.css(".link-back  a")).first();
        
            //execute
            button.click();
        
            //assert
            expect(browser.getLocationAbsUrl()).toContain('/gallery/56');
        });
        
    });


});

