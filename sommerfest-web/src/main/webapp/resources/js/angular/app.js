'use strict';

// Declare app level module which depends on filters, and services
//angular.module('sommerfest', ['myApp.filters', 'myApp.services', 'myApp.directives']).config(
//    ['$routeProvider',
//        function ($routeProvider) {
//            $routeProvider.when('/view1', {templateUrl: 'partials/partial1.html', controller: MyCtrl1});
//            $routeProvider.when('/view2', {templateUrl: 'partials/partial2.html', controller: MyCtrl2});
//            $routeProvider.otherwise({redirectTo: '/view1'});
//        }
//    ]
//);

var sommerfestApp = angular.module('sommerfest', ['sommerfestServices']);
sommerfestApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/index', {templateUrl: 'partials/index.xhtml'}).
        when('/bar', {templateUrl: 'partials/bar.xhtml', controller: 'CocktailbarController'}).
        when('/rampe', {templateUrl: 'partials/rampe.xhtml', controller: 'RampeController'}).
        when('/logistik', {templateUrl: 'partials/logistik.xhtml', controller: 'LogistikController'}).
        when('/produkte', {templateUrl: 'partials/produkte.xhtml', controller: 'ProductManagementController'}).
        otherwise({redirectTo: '/index'});
}]);
