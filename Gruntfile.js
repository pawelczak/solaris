module.exports = function(grunt) {
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),    
    
    /**
    * Tests
    */
    karma: {
        unit: {
            configFile: "karma.conf.js"
        }
    },
    connect: {
        server: {
            options: {
                port: 8000,
                base: "src"
            }
        }
    },
    protractor: {
        e2e: {
            options: {
                args: {
                    specs: [
                            'src/main/webapp/WEB-INF/static/admin/angularjs/test/e2e/route/*.js',
                            'src/main/webapp/WEB-INF/static/site/angularjs/test/e2e/route/*.js',
                            'src/main/webapp/WEB-INF/static/site/angularjs/test/e2e/components/**/*.js'
                            ]
                },
                configFile: "protractor.conf.js",
                keepAlive: false
            }
        }
    },
    protractor_webdriver: {
        start: {
            //options: "./node_modules/protractor/bin/",
            command: "webdriver-manager start"
        }
    }
    
  });
  
  
  require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);
  

  //TEST
  grunt.registerTask('unit-test', ['karma:unit']);
  grunt.registerTask('e2e-test', ['connect:server', 'protractor_webdriver:start', 'protractor:e2e']);
  
  grunt.registerTask('test', ['unit-test', 'e2e-test']);
  
};

