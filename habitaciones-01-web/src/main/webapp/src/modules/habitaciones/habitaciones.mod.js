(function (ng){
    var mod = ng.module('habitacionModule', ['ui.router']);
    mod.constant('habitacionesContext', 'api/habitaciones');
    mod.config(['$stateProvider', '$urlRouterProvider', 
        function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/habitaciones/';
            $urlRouterProvider.otherwise('/habitacionesList');

            $stateProvider.state('habitaciones', {
                url: '/habitaciones',
                abstract: true,
                resolve: {
                    habitaciones: ['$http','habitacionesContext', 
                        function ($http, habitacionesContext) {
                            return $http.get(habitacionesContext);
                        },]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'habitaciones.html',
                        controller: ['$scope', 'habitaciones', 
                                    function ($scope, habitaciones) {
                                $scope.habitacionesRecords = habitaciones.data;
                            },]
                    },
                },
            }).state('habitacionesList', {
                url: '/lista',
                parent: 'habitaciones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'habitaciones.list.html',
                    },
                }
            }).state('habitacionDetail', {
                url: '/{habitacionId:int}/detail',
                parent: 'habitaciones',
                param: {
                    habitacionId: null
                },
                resolve:{
                    habitacionActual:['$http', 'habitacionesContext', '$stateParams',
                    function($http, habitacionesContext, $params) {
                        return $http.get(habitacionesContext + '/' + $params.habitacionId);
                    }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'habitaciones.detail.html',
                        controller: ['$scope', 'habitacionActual',
                                    function ($scope, habitacionActual)
                            { $scope.habitacionActual = habitacionActual.data;
                            },]
                    },                    
                }
            });
        }]);
})(window.angular);