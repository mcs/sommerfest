'use strict';

angular.module('sommerfestServices', ['ngResource'])
    .factory('Product', function ($resource) {
        return $resource(java.contextPath + '/rest/products/:productId', {}, {
        });
    })
    .factory('Order', function ($resource) {
        return $resource(java.contextPath + "/rest/orders/:target", {}, {
            update: {method: 'PUT'}
        })
    });
