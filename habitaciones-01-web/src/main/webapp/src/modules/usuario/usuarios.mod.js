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
                        usuarios: ['$http', 'usuarioContext', function ($http, usuarioContext) {
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
        url: '/adminLogin',
                parent: 'usuarios',
                views: {
                'listView': {
                templateUrl: basePath + 'modal.html'
                }
                }
        }).state('registrarUsuario', {
        url:'/registrarUsuario',
                parent: 'usuarios',
                views: {
                'listView': {
                templateUrl: basePath + 'registrar.html',
                        controller: ['$scope', 'usuarios', function ($scope, usuarios, $http, usuarioContext) {
                        $scope.usuariosRecords = usuarios.data;
                               
                                $scope.registrar = function(usuarioContext, $http){

                               
                                        $scope.tempUser = {
                                                numeroID : $scope.numeroID,
                                                tipoID :$scope.tipoID,
                                                nombre : $scope.nombre,
                                                usuario : $scope.usrname,
                                                contrasenha :$scope.psw2,
                                                correo :$scope.correo,
                                                direccion : $scope.direccion,
                                                telefono : $scope.telefono,
                                                numeroTarjeta : $scope.numeroTarjeta,
                                                image : "https://randomuser.me/api/portraits/men/63.jpg"

                                        };
                                        if (true){
                                tempUser = $scope.tempUser;
                                        return $http.post(usuarioContext, tempUser)
                                        .then(function () {
                                        // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        //$state.go('reviewsList');
                                        console.log('check');
                                        }, responseError);
                                        console.log(tempUser);
                                }
                                }
                        }]
                        }
                        }


                        }).state('usuarioDetail', {
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
                                        usuarios :['$http', 'usuarioContext', function ($http, usuarioContext){
                                        return $http.get(usuarioContext);
                                        }]
                                        },
                                },
                                        detailView:{

                                        templateUrl: basePath + 'usuario.detail.html',
                                                controller: ['$scope', 'currentUsuario', function ($scope, currentUsuario) {
                                                console.log(currentUsuario.data);
                                                        $scope.currentUsuario = currentUsuario.data;
                                                }]
                                        }
                                }
                        });
                        }]);
                        })(window.angular);