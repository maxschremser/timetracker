'use strict';

/**
 * @ngdoc directive
 * @name Date REST API Implementation
 * @description
 * # adminPosHeader
 */
angular.module('timetracker')
    .factory('DateApi', function(Restangular){
        console.log("DateApi call")
        return {
            list:list,
            save:save
        }

        function list(datetime) {
            console.log("get track for datetime: " + datetime);
            return Restangular.all("date/" + datetime);
        }

        function save(datetime, track) {
            console.log("saving track for datetime: " + datetime);
            Restangular.all("date").all(datetime).post(track);
        }
    });


