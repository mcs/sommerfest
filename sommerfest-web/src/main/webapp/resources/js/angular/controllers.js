'use strict';

function CocktailbarController($scope, Product, Order) {
    var target = "Die Cocktailbar";
    $scope.orders = Order.query({target: target});
    $scope.products = Product.query(function () {
        var i;
        for (i = 0; i < $scope.products.length; i += 1) {
            $scope.products[i].amount = 1;
        }
    });

    $scope.incAmount = function (elem) {
        elem.amount += 1;
    }

    $scope.decAmount = function (elem) {
        elem.amount = Math.max(1, elem.amount - 1);
    }

    $scope.order = function (product) {
        var order = {
            product: angular.copy(product),
            amount: "" + product.amount,
            target: target
        };
        delete order.product.amount;
        product.amount = 1;
        order = Order.save(order, function () {
            $scope.orders = Order.query({target: target});
        });
    };

    $scope.storno = function (order) {
        Order.delete({target: order.id}, function () {
            $scope.orders = Order.query({target: target});
        });
    }
}
CocktailbarController.$inject = ['$scope', 'Product', 'Order'];


function RampeController($scope) {
    $scope.orders = [
        {name: "Cocktailgläser", amount: 2, target: "Cocktailbar"},
        {name: "Havanna Club Rum", amount: 4, target: "Bar 1"}
    ];
}
RampeController.$inject = ['$scope'];