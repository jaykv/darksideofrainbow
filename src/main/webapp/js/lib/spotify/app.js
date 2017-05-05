
var album = angular.module('albumsApp', ['spotify'])
    .config(function (SpotifyProvider) {
        SpotifyProvider.setClientId('');//todo: finish this
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
