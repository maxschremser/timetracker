'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('timetracker')
    .directive('projectButtons',function() {
    	return {
  		templateUrl:'scripts/directives/projectButtons/projectButtons.html',
  		restrict:'E',
  		replace:true,
  		scope: {
            'model':'='
  		}
  	}
  });
