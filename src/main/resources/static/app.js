// create the module and name it app
// create the module and name it app
// also include ngRoute for all our routing needs
var app = angular.module('app', ['ngRoute','ui.calendar']);

// configure our routes
app.config(function($routeProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            templateUrl : 'views/home.html',
            controller  : 'homeController'
        })

        // route for the about page
        .when('/treatments', {
            templateUrl : 'views/treatments.html',
            controller  : 'aboutController'
        })

        .when('/treatments/laserHairRemoval', {
            templateUrl : 'views/laserHairRemovalTreatment.html'
        })

        .when('/treatments/hairLoose', {
            templateUrl : 'views/hairLooseTreatment.html'
        })

        .when('/treatments/bodyCosmetic', {
            templateUrl : 'views/bodyCosmetic.html'
        })

        .when('/treatments/peelTreatment', {
            templateUrl : 'views/peelTreatment.html'
        })

        .when('/treatments/eyeCosmetic', {
            templateUrl : 'views/eyeCosmetic.html'
        })

        .when('/visitAppointment/:treatmentId', {
            templateUrl : 'views/visitAppointment.html',
            controller : 'visitAppointmentController',
            resolve:{
                factory:checkRouting
            }
        })

        .when('/login', {
            templateUrl : 'views/login.html',
            controller  : 'loginController'
        })

        .when('/userHome', {
            templateUrl : 'views/userHome.html',
            controller  : 'userController',
            resolve:{
                factory:checkRouting
            }
        })

        .when('/doctorHome', {
            templateUrl : 'views/doctorHome.html',
            controller  : 'doctorHomeController',
            resolve:{
                factory:checkRoutingForDoctor
            }
        })

        // route for the contact page
        .when('/contact', {
            templateUrl : 'views/contact.html',
            controller  : 'contactController'
        })
        .otherwise({redirectTo:'/'});

});
var checkRouting= function ($q, $rootScope, $location,$http,$window) {
    console.log($rootScope);
    if ($window.sessionStorage.getItem('userInfo-token')!= undefined || $window.sessionStorage.getItem('userInfo-token')!="") {
        $http.post('/checkToken',{token:$window.sessionStorage.getItem('userInfo-token')}).then(function(response) {
            return true;
        }).catch(function (reason) {
            console.log(reason);
            $location.path("/login");
            return false;
        });
    } else {
        $location.path("/login");
    }
};

var checkRoutingForDoctor= function ($q, $rootScope, $location,$http,$window) {
    console.log($rootScope);
    if ($window.sessionStorage.getItem('userInfo-token')!= undefined || $window.sessionStorage.getItem('userInfo-token')!="") {
        $http.post('/checkTokenForDoctor',{token:$window.sessionStorage.getItem('userInfo-token')}).then(function(response) {
            return true;
        }).catch(function (reason) {
            console.log(reason);
            $location.path("/login");
            return false;
        });
    } else {
        $location.path("/login");
    }
};


// create the controller and inject Angular's $scope


app.controller('aboutController', function($scope) {
    $scope.message = 'Look! I am an about page.';
});

/*
app.controller('contactController', function($scope) {
    $scope.message = 'Contact us! JK. This is just a demo.';
});*/
