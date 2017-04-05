(function (ng) {
    var mod = ng.module("reservasModule", ['ui.router']);
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservas");

            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                resolve: {
                    reservas: ['$http', function ($http) {
                            return $http.get('data/reservas.json');
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
                views: {
                   'listView': {
                       resolve: {
                    reservas: ['$http', function ($http) {
                            return $http.get('data/reservas.json');
                        }]
                },
                        templateUrl: 'src/modules/reservas/reservas.list.html',
                        controller: ['$scope', '$stateParams','reservas', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.codigoReserva-1];
                            }]
                    },
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
