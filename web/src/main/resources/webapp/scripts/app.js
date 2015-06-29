'use strict';
/**
 * @ngdoc overview
 * @name timetracker
 * @description
 * # timetracker
 *
 * Main module of the application.
 */
angular
  .module('timetracker', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'angular-loading-bar',
    'restangular'
  ])
  .config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider','RestangularProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider, RestangularProvider) {

    RestangularProvider.setBaseUrl("http://localhost:8081/api")

    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state('dashboard', {
        url:'/dashboard/{email}/{date}',
        controller: 'MainCtrl as main',
        templateUrl: 'views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'timetracker',
                    files:[
                    'scripts/directives/header/header.js',
                    'scripts/directives/header/header-notification/header-notification.js',
                    'scripts/directives/sidebar/sidebar.js',
                    'scripts/directives/sidebar/sidebar-search/sidebar-search.js',
                    'scripts/directives/projectButtons/projectButtons.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["webjars/angular-toggle-switch/1.3.0/angular-toggle-switch.min.js",
                          "webjars/angular-toggle-switch/1.3.0/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['webjars/angular-animate/1.4.0/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['webjars/angular-cookies/1.4.0/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['webjars/angular-resource/1.4.0/angular-resource.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['webjars/angular-sanitize/1.4.0/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['webjars/angular-touch/1.4.0/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home/{track}',
        //controller:'MainCtrl as home',
        templateUrl:'views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'timetracker',
              files:[
              'scripts/controllers/main.js',
              'scripts/services/main.js',
              'scripts/directives/timeline/timeline.js',
              'scripts/directives/notifications/notifications.js',
              'scripts/directives/chat/chat.js',
              'scripts/directives/dashboard/stats/stats.js',
              'scripts/directives/projectButtons/projectButtons.js'
              ]
            })
          }
        }
      })
      .state('dashboard.detail', {
        url:'/detail',
        controller: 'TrackDetailCtrl as detail',
        templateUrl: 'views/dashboard/detail.html',
        resolve: {
            loadMyFiles:function($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name:'timetracker',
                    files:[
                        'scripts/controllers/main.js',
                        'scripts/services/main.js'
                    ]
                })
            }
        }
      })
      .state('dashboard.form',{
        templateUrl:'views/form.html',
        url:'/form'
      })
      .state('dashboard.blank',{
        templateUrl:'views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'views/pages/login.html',
        url:'/login',
        controller: 'LoginCtrl',
        resolve: {
            loadMyFiles:function($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name:'timetracker',
                    files:[
                        'scripts/controllers/main.js'
                    ]
                })
            }
        }
    })
      .state('dashboard.chart',{
        templateUrl:'views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'webjars/angular-chart.js/0.7.1/dist/angular-chart.min.js',
                'webjars/angular-chart.js/0.7.1/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'timetracker',
                files:['scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.table',{
        templateUrl:'views/table.html',
        url:'/table'
    })
      .state('dashboard.panels-wells',{
          templateUrl:'views/ui-elements/panels-wells.html',
          url:'/panels-wells'
      })
      .state('dashboard.buttons',{
        templateUrl:'views/ui-elements/buttons.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'views/ui-elements/notifications.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'views/ui-elements/typography.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

    
