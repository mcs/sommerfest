'use strict';

function log() {
    var args;
    if (console && typeof console.log === "function") {
        args = Array.prototype.slice.call(arguments);
        console.log.apply(console, args);
    }
}

var bundle = {
    'ORDERED': 'bestellt',
    'SENT': 'unterwegs',
    'DELIVERED': 'zugestellt'
};