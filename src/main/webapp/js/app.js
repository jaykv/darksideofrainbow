

var hello = angular.module('hello', [])
    .controller('home', function($scope, $http) {
        var self = this;
        $http.get('/resource/').then(function(response) {
           self.greeting = response.data;
        });
    });


var album = angular.module('albumsApp', ['spotify'])
    .config(function (SpotifyProvider) {
        SpotifyProvider.setClientId('4659df16b6564ff8b10af7e82504d247');
        SpotifyProvider.setRedirectUri('http://localhost:8080/callback');
        SpotifyProvider.setScope('');
        // If you already have an auth token
        //SpotifyProvider.setAuthToken('<AUTH_TOKEN>');
    })
    .controller('albums', ['Spotify', '$scope', '$http', '$rootScope', function(Spotify, $scope, $http, $rootScope) {
        $rootScope.albumsList = [];

        var loadAlbums = function(callback) {
            $http.get('/api/albums').then(function (data) {
                $rootScope.albumsList = data.data._embedded.albums;
                console.log(this.albumsList);
            });
        };

        loadAlbums();

        $scope.login = function () {
            Spotify.login().then(function (data) {
                console.log(data);
                console.log("You are now logged in");
                $scope.loadSpotifyAlbums();
            }, function () {
                console.log('didn\'t log in');
            })
        };

        $scope.loadSpotifyAlbums = function(callback) {
            Spotify.getNewReleases({limit:5}).then(function (data) {
                console.log(data);
                $rootScope.albumsList = data.albums.items;
            });
        };
    }]);
