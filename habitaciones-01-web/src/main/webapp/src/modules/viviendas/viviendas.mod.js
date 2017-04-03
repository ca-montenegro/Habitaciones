(function (ng) {
    var mod = ng.module("viviendasModule", ['ui.router']);
    mod.constant("viviendasContext", "api/viviendas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/viviendas/';
            $urlRouterProvider.otherwise("/listaViviendas");

            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    authors: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: ['$scope', 'viviendas', function ($scope, authors) {
                                $scope.viviendasRecords = viviendas.data;
                            }]
                    }
                }
            }).state('habitacionesVivienda', {
                url: '/{idVivienda:int}/habitaciones',
                parent: 'authors',
                param: {
                    idVivienda: null
                },
                views: {
                    'listView': {
                        resolve: {
                            habitaciones: ['$http', function ($http) {
                                    return $http.get('data/habitaciones.json');
                                }]
                        },
                        templateUrl: basePath + 'vivienda-habitaciones.html',
                        controller: ['$scope', 'habitaciones', '$stateParams', function ($scope, habitaciones, $params) {
                                $scope.habitacionesRecords = habitaciones.data;
                                $scope.viviendaActual = $scope.viviendasRecords[$params.authorId - 1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'authors.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAuthor = $scope.authorsRecords[$params.authorId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);