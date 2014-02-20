'use strict';

angular.module('sommerfestServices', ['ngResource'])
    .factory('Product', function ($resource) {
        return $resource(sommerfestApp.serviceUrl("/products/:productId"), {}, {
        });
    })
    .factory('Order', function ($resource) {
        var order = $resource(sommerfestApp.serviceUrl("/orders/:target"), {}, {
            update: {
                method: 'PUT'
            }
        });
        //noinspection JSUnusedGlobalSymbols
        order.prototype.displayState = function () {
            return bundle[this.state];
        };
        return order
    });
