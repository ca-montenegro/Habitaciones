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
                    viviendas: ['$http', 'viviendasContext',function ($http, viviendasContext) {
                            return $http.get(viviendasContext);
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
                resolve: {
                    viviendaActual: ['$http', 'viviendasContext', '$stateParams',function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+'/'+$params.viviendaId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viviendas.detail.html',
                        controller: ['$scope', 'viviendaActual', function ($scope, viviendaActual) {
                                $scope.viviendaActual =  viviendaActual.data;
                            }]
                    }
                    
                }
                
            });
        }]);
})(window.angular);