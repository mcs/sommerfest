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


function RampeController($scope, Order) {
    $scope.orders = Order.query();
}
RampeController.$inject = ['$scope', 'Order'];


function ProductManagementController($scope, Product) {
    $scope.products = Product.query();
    $scope.newProduct = new Product();

    $scope.delete = function (product) {
        Product.delete({productId: product.id}, function () {
            $scope.products = Product.query();
        });
    };

    $scope.create = function () {
        Product.save($scope.newProduct, function () {
            $scope.products = Product.query();
        });
        $scope.newProduct = "";
    }
}
ProductManagementController.$inject = ['$scope', 'Product'];

