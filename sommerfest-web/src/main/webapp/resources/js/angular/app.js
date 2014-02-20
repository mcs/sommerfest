'use strict';

var sommerfestApp = angular.module('sommerfest', ['ngRoute', 'sommerfestServices']);

sommerfestApp.serviceUrl = function (relativePath) {
    return window.sommerfestServiceUrl + relativePath;
};

sommerfestApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.
        when('/index', {templateUrl: 'partials/index.xhtml'}).
        when('/bar', {templateUrl: 'partials/bar.xhtml', controller: 'CocktailbarController'}).
        when('/rampe', {templateUrl: 'partials/rampe.xhtml', controller: 'RampeController'}).
        when('/logistik', {templateUrl: 'partials/logistik.xhtml', controller: 'LogistikController'}).
        when('/produkte', {templateUrl: 'partials/produkte.xhtml', controller: 'ProductManagementController'}).
        otherwise({redirectTo: '/index'});
}]);
