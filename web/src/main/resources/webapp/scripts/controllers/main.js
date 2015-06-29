'use strict';
/**
 * @ngdoc function
 * @name timetracker.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the timetracker
 */
angular.module('timetracker')
  // LoginCtrl controller
  .controller('LoginCtrl', function($scope, $state, $filter) {
    $scope.details = {}

    $scope.login = function (form) {
      if (form == undefined || form.email == undefined || form.password == undefined)
        return

      if (form.email.trim() != "" && form.password.trim() != "") {
        console.log("user:" + form.email);
        $state.go('dashboard.home', {"email":form.email, "date":$scope.today})
      }
    }
  })
  // MainCtrl controller
  .controller('MainCtrl', function($scope,$position,$stateParams,$filter,DateApi) {
      var main = this;
      main.email = $stateParams.email
      main.date = $stateParams.date || $filter('date')(new Date(), 'yyyy-MM-dd')
      main.today = $filter('date')(new Date(), 'yyyy-MM-dd')
      console.log("MainCtrl: " + JSON.stringify(main))
      main.setCurrentTrack = function(track) {
          if (track && track.name && track.project) {
              main.track = track
          } else {
              main.track = ""
          }
      }

      function init() {
          DateApi.list(main.date).getList().then(function (tracks) {
              console.log("tracks: " + tracks.length)
              main.tracks = tracks
          })
      }

      init()
  })

  // TaskDetailCtrl controller
  .controller('TrackDetailCtrl', function($scope, $state, $stateParams, $filter, DateApi) {
      var detail = this

      detail.track = $stateParams.track

      function now() {
          return $filter('date')(new Date(), 'HH:mm:ss')
      }

      detail.addTask = function (track, task) {
        track.tasks = []
        task.running = true
        task.time = now()
        track.tasks.push(task)
        console.log("validating track: " + JSON.stringify(track));
        DateApi.save("today", track);
        $state.go('dashboard.home', $stateParams)
      }
  })

;
