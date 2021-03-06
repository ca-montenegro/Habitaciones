(function (ng) {
    const mod = ng.module("reservasModule", ['ui.router']);
    /**
     * Recurso de reserva, lee directamente del back-end, para su correcto funcionamiento ejecutar el script en la carpeta /data/ScriptViviendas.sql 
     */
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservas");

            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                resolve: {
                    reservas: ['$http', 'reservasContext', function ($http,reservasContext) {
                            return $http.get(reservasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: ['$scope', 'reservas', function ($scope, reservas) {
                                $scope.reservasRecords = reservas.data;
                            }]
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('reservasDetail', {
                url: '/{codigoReserva:int}/detail',
                parent: 'reservas',
                param: {
                    codigoReserva: null
                },
                views : {
                    'listView' : {
                        resolve: {
                     reservas: ['$http', 'reservasContext', function ($http, reservasContext) {
                             return $http.get(reservasContext);
                     }]
                }, },
                    'detailView': {
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.codigoReserva-1];
                            }]
                    } 
                }

            });
        }]);
})(window.angular);
