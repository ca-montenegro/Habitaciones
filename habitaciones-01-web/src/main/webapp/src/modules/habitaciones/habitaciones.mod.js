(function (ng){
    var mod = ng.module("habitacionModule", ['ui.router']);
    mod.constant("habitacionesContext", "api/habitaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/habitaciones/';
            $urlRouterProvider.otherwise("/habitacionesList");

            $stateProvider.state('habitaciones', {
                url: '/habitaciones',
                abstract: true,
                resolve: {
                    habitaciones: ['$http', function ($http) {
                            return $http.get('data/habitaciones.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'habitaciones.html',
                        controller: ['$scope', 'habitaciones', function ($scope, habitaciones) {
                                $scope.habitacionesRecords = habitaciones.data;
                            }]
                    }
                }
            }).state('habitacionesList', {
                url: '/lista',
                parent: 'habitaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'habitaciones.list.html'
                    }
                }
            }).state('habitacionDetail', {
                url: '/{habitacionId:int}/detail',
                parent: 'habitaciones',
                param: {
                    habitacionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'habitaciones.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentHabitacion = $scope.habitacionesRecords[$params.habitacionId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);