(function (ng){
    var mod = ng.module("viviendaModule", ['ui.router']);
    mod.constant("viviendasContext", "api/viviendas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/viviendas/';
            $urlRouterProvider.otherwise("/viviendasList");

            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    viviendas: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: ['$scope', 'viviendas', function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data;
                            }]
                    }
                }
            }).state('viviendasList', {
                url: '/lista',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html'
                    }
                }
            }).state('viviendaDetail', {
                url: '/{viviendaId:int}/detail',
                parent: 'viviendas',
                param: {
                    viviendaId: null
                },
                views: {
                    'listView': {
                       resolve: {
                    viviendas: ['$http', function ($http) {
                            return $http.get('data/viviendas.json');
                        }]
                },
                        templateUrl: 'src/modules/viviendas/habitacionesVivienda.list.html',
                        controller: ['$scope', '$stateParams','viviendas', function ($scope, $params) {
                                $scope.currentVivienda = $scope.viviendasRecords[$params.viviendaId-1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'viviendas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentVivienda = $scope.viviendasRecords[$params.viviendaId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);