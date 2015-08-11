'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'ngResource',
  // Our app/modules our app/modules
  'home',
  'admin',
  'goods'

]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
    // admin code
    when('/admin/users',{
          templateUrl: 'app/modules/admin/views/adm-user-page.html'
        }).
        when('/admin/shops',{
          templateUrl: 'app/modules/admin/views/adm-shops-page.html'
        }).
        when('/admin/statistics',{
          templateUrl: 'app/modules/admin/views/adm-stat-page.html'
        }).
        when('/user/backet',{
          templateUrl: 'app/modules/admin/views/user-backet-page.html'
        }).
        when('/user/purchases',{
          templateUrl: 'app/modules/admin/views/user-all-purchases-page.html'
        }).
        when('/user/profile/edit',{
          templateUrl: 'app/modules/admin/views/edit-user-page.html'
        }).
        when('/shop/orders',{
          templateUrl: 'app/modules/admin/views/shop-orders-page.html'
        }).
        when('/shop/products',{
          templateUrl: 'app/modules/admin/views/shop-products-page.html'
        }).
         when('/shop/profile/edit',{
          templateUrl: 'app/modules/admin/views/shop-admin-edit-profile.html'
        }).
        when('/home/service',{
            templateUrl: 'app/modules/admin/views/services-page.html'
        }).

      // Our roots
      when('/home', {
        templateUrl: 'app/modules/home/views/main.html'
      }).
      //when('/home/:categoryId', {
      //  templateUrl: 'app/modules/home/views/chosen.html'
      //}).
      //when('/home/chosen',{
      // templateUrl: 'app/modules/home/views/chshop.html'
      //}).
      when('/stuff', {
        templateUrl: 'app/modules/home/views/stuff.html'
      }).
      when('/stuff/:goodId', {
        templateUrl: 'app/modules/goods/views/characteristic-of-product.html'
      }).
      when('/ordering',{
        templateUrl: 'app/modules/goods/views/ordering.html'
      }).
      when('/admin', {
        templateUrl: 'app/modules/admin/views/main.html'
      }).
      when('/character', {
        templateUrl: 'app/modules/goods/views/characteristic-of-product.html'
      }).
      otherwise({
        redirectTo: '/home'
      });
  }]);
