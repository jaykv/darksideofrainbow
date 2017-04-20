var admin = angular.module('albumAdmin', [])
    .controller('albumCtrl', function($scope, $http) {

        var deleteAlbum = function(id) {
          $http.post('/admin/albums/delete/' + id).then(function(data) {
                console.log("deleted");
            }, function () {
                console.log('error: deleting');
            });
        };


        $http.get('/resource/').then(function(response) {
            self.greeting = response.data;
        });
    });