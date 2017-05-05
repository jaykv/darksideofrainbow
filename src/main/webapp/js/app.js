

var main = angular.module('mainApp', ['datatables', 'datatables.bootstrap'])
    .controller('mainController', ['DTOptionsBuilder', '$scope', '$http', '$rootScope', function(DTOptionsBuilder, $scope, $http, $rootScope) {
        $scope.albumList = [];

        $http.get('/api/albums').then(function(response) {
            $scope.albumList = response.data._embedded.Album;
        });

        $scope.formatDate = function(date) {
            return date.monthValue + "/" + date.dayOfMonth + "/" + date.year;
        }
    }]);