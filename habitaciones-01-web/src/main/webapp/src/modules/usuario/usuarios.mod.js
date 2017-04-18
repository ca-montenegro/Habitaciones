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
                    usuarios: ['$http','usuarioContext', function ($http,usuarioContext) {
                            return $http.get(usuarioContext);
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
                url: '/10/adminLogin',
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
                resolve : {
                    currentUsuario : ['$http', 'usuarioContext', '$stateParams', function ($http, usuarioContext, $params){
                            return $http.get(usuarioContext + '/' + $params.usuarioId);
                    }]
                },
                views:{
                    listView:{
                      templateUrl: basePath + 'usuario.list.html', 
                      resolve : {
                          usuarios :['$http', 'usuarioContext', function ($http,usuarioContext){
                                  return $http.get(usuarioContext);
                          }]
                      },
                    },
                    detailView:{
                       
                        templateUrl: basePath + 'usuario.detail.html',
                        controller: ['$scope','currentUsuario', function ($scope,currentUsuario) {
                                console.log(currentUsuario.data);
                                $scope.currentUsuario = currentUsuario.data;
                                
                            }]
                    }
                }
            });
            
        }]);
})(window.angular);