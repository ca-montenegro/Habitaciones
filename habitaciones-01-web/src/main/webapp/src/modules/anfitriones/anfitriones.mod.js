(function (ng){
    const mod = ng.module("anfitrionModule", ['ui.router']);
    mod.constant("anfitrionesContext", "api/anfitriones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/anfitriones/';
            $urlRouterProvider.otherwise("/anfitrionesList");
            
            $stateProvider.state('anfitriones', {
                url: '/anfitriones',
                abstract: true,
                resolve: {
                    anfitriones: ['$http', 'anfitrionContext',
                        function ($http, anfitrionContext) {
                            return $http.get(anfitrionContext);
                        }, ]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'anfitriones.html',
                        controller: ['$scope', 'anfitriones', function ($scope, anfitriones) {
                                $scope.anfitrionesRecords = anfitriones.data;
                            }]
                    }
                }
            }).state('anfitrionesList', {
                url: '/lista',
                parent: 'anfitriones',
                views: {
                    'listView': {
                        templateUrl: basePath + 'anfitriones.list.html'
                    }
                }
            }).state('anfitrionesDetail', {
                url: '/{anfitrionId:int}/detail',
                parent: 'anfitriones',
                param: {
                    anfitrionId: null
                },
                views: {
                    'listView': {
                       resolve: {
                    anfitriones: ['$http', function ($http) {
                            return $http.get('data/anfitriones.json');
                        }]
                },
                        templateUrl: 'src/modules/anfitriones/viviendadasAnfitrion.html',
                        controller: ['$scope', '$stateParams','anfitriones', function ($scope, $params) {
                                $scope.currentAnfitrion = $scope.anfitrionesRecords[$params.anfitrionId-1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'anfitriones.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAnfitrion = $scope.anfitrionesRecords[$params.anfitrionId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);


