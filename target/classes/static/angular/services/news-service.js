angular.module('mainModule')
    .service('NewsService', function ($http, $location) {
        var service = this;

        var contextPath = $location.protocol() + "://" + $location.host() + ":" + $location.port() + '/';

        service.getAllNews = function (callback) {
            var req = {
                method: 'GET',
                url: contextPath + 'api/news/all'
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });

        };

        service.searchForNews = function (callback) {
            var req = {
                method: 'GET',
                url: contextPath + 'api/news/search'
            };

            $http(req).then(function (resp) {
                callback(true, resp.data)
            }, function () {
                callback(false)
            });
        };

    });

