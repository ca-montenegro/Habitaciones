(function (ng) {
    var mod = ng.module("viviendasModule", ['ui.router']);

    mod.constant("contextoViviendas", "api/viviendas");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/viviendas/';
            $urlRouterProvider.otherwise("/viviendas");

            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    authors: ['$http', 'contextoViviendas', function ($http, contextoViviendas) {
                            return $http.get(contextoViviendas);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viv.html',
                        controller: ['$scope', 'viviendas', function ($scope, viviendas) {
                                $scope.authorsRecords = viviendas.data;
                            }]
                    },
                    'childrenView': {
                        templateUrl: basePath + 'viv.html'
                    }
                }
            }).state('listaViviendas', {
                url: '/lista',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.html'
                    }
                }
            }).state('habitacionesVivienda', {
                url: '/{idVivienda:int}/habitaciones',
                parent: 'viviendas',
                param: {
                    idVivienda: null
                },
                resolve: {
                    viviendaActual: ['$http', 'contextoViviendas', '$stateParams', function ($http, contextoViviendas, $params) {
                            return $http.get(contextoViviendas + '/' + $params.idVivienda);
                        }]
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'vivienda-habitaciones.html',
                        controller: ['$scope', 'viviendaActual', function ($scope, viviendaActual) {
                                $scope.viviendaActual = viviendaActual.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);