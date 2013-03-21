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
        // create new order based on the product
        var order = {
            product: angular.copy(product),
            amount: "" + product.amount,
            target: target
        };
        // products don't have an amount, so delete it
        delete order.product.amount;
        // execute REST call
        order = Order.save(order, function () {
            $scope.orders = Order.query({target: target});
        });
        // reset "view product"'s amount to 1
        product.amount = 1;
    };

    $scope.storno = function (order) {
        Order.delete({target: order.id}, function () {
            $scope.orders = Order.query({target: target});
        });
    }
}
CocktailbarController.$inject = ['$scope', 'Product', 'Order'];


function RampeController($scope, Order) {
    function fetchOrders() {
        $scope.orders = Order.query({state: 'ORDERED'});
    }

    fetchOrders();

    $scope.processSent = function (order) {
        order.state = 'SENT';
        order.$update(function () {
            fetchOrders();
        });
    }
}
RampeController.$inject = ['$scope', 'Order'];


function ProductManagementController($scope, Product) {
    $scope.products = Product.query();
    $scope.newProduct = new Product();

    $scope.delete = function (product) {
        Product.delete({productId: product.id}, function () {
            $scope.products = Product.query();
        }, function (e) {
            console.log("Error: %o", e);
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

