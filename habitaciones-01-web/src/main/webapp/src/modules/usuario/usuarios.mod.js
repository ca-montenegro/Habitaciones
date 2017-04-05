(function (ng){
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuarioContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuario/';
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                resolve: {
                    usuarios: ['$http', function ($http) {
                            return $http.get('data/datos.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios) {
                                $scope.usuariosRecords = usuarios.data;
                            }]
                    }
                }
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuario.list.html'
                    }
                }
            }).state('usuarioAdminLogin', {
                url: '/adminLogin',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'modal.html'
                    }
                }
            }).state('usuarioDetail',{
                url:'/{usuarioId:int}/detail',
                parent: 'usuarios',
                param:{
                    usuarioId: null
                },
                views:{
                    listView:{
                        
                      templateUrl: basePath + 'usuario.list.html'  
                    },
                    detailView:{
                       
                        templateUrl: basePath + 'usuario.detail.html',
                        controller: ['$scope','usuarios', '$stateParams', function ($scope, usuarios,$params) {
                             
                                $scope.currentUsuario = $scope.usuariosRecords[$params.usuarioId-1];
                            }]
                    }
                }
            });
            
        }]);
})(window.angular);