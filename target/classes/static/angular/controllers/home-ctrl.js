angular.module('mainModule')
    .controller('HomeCtrl', function ($scope, NewsService) {
        NewsService.getAllNews(function (isSuccess, data) {
            if (isSuccess) {
                $scope.news = data;
            }
        });
        
        $scope.searchNow = function () {
            NewsService.searchForNews(function (isSuccess, data) {
                if (isSuccess) {
                    angular.forEach(data, function (newsEntity) {
                        $scope.news.push(newsEntity)
                    });
                }
            });
        }
    });

