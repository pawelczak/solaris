// Karma configuration
// Generated on Fri Jul 17 2015 13:07:25 GMT+0200 (Środkowoeuropejski czas letni)

module.exports = function(config) {
  config.set({

    // base path that will be used to resolve all patterns (eg. files, exclude)
    basePath: '',


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine-jquery', 'jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'src/main/webapp/WEB-INF/static/common/angularjs/angular.min.js',
      'src/main/webapp/WEB-INF/static/common/angularjs/angular-route.min.js',
      'src/main/webapp/WEB-INF/static/common/angularjs/*/*.js',
      'src/test/javascript/admin/angularjs/angular-mocks.js',
      
      //ADMIN
      'src/main/webapp/WEB-INF/static/admin/angularjs/admin-app.js',
      'src/main/webapp/WEB-INF/static/admin/angularjs/route/route.js',
      'src/main/webapp/WEB-INF/static/admin/angularjs/components/*.js',
      'src/main/webapp/WEB-INF/static/admin/angularjs/components/*/*.js',
      'src/test/javascript/admin/config.js',
      'src/test/javascript/admin/angularjs/components/*.js',
      'src/test/javascript/admin/angularjs/components/*/*.js',
      
      'src/main/webapp/WEB-INF/static/admin/angularjs/test/unit/route/*.js',
      
      //SITE
      'src/main/webapp/WEB-INF/static/site/angularjs/site-app.js',
      'src/main/webapp/WEB-INF/static/site/angularjs/route/galleryRoute.js',
      'src/main/webapp/WEB-INF/static/site/angularjs/components/*.js',
      'src/main/webapp/WEB-INF/static/site/angularjs/components/*/*.js',
      'src/test/javascript/site/config.js',
      'src/test/javascript/site/angularjs/components/*.js',
      'src/test/javascript/site/angularjs/components/*/*.js',
      
      'src/main/webapp/WEB-INF/static/site/angularjs/test/unit/route/*.js'
    ],


    // list of files to exclude
    exclude: [
    ],


    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },


    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress'],


    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['Chrome'],


    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false
  })
}
