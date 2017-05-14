(function (ng) {
    let mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuarioContext", "api/usuarios");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/usuario/';
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
                url: '/registrarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'registrar.html',
                        controller: ['$scope', '$http', '$state', 'usuarios', 'usuarioContext',
                            function ($scope, $http, $state,  usuarios, usuarioContext) {
                                //$scope.usuariosRecords = usuarios.data;

                                $scope.tempUser = {
                                    numeroID: '',
                                    tipoID: '',
                                    nombre: '',
                                    usuario: '',
                                    contrasenha: '',
                                    correo: '',
                                    direccion: '',
                                    telefono: '',
                                    numeroTarjeta: '',
                                    image: ""

                                };
                                console.log($scope.tempUser);
                                $scope.registrar = function () {

                                    tempUser = $scope.tempUser;
                                    console.log(tempUser);
                                    if (true) {
                                        return $http.post(usuarioContext, tempUser)
                                                .then(function () {
                                                    // $http.post es una promesa
                                                    // cuando termine bien, cambie de estado
                                                    $state.go('usuariosList');
                                                    console.log('check');
                                                }, responseError);

                                    }
                                }

                                this.closeAlert = function (index) {
                                    $scope.alerts.splice(index, 1);
                                };

                                // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
                                function showMessage(msg, type) {
                                    const types = ["info", "danger", "warning", "success"];
                                    if (types.some(function (rc) {
                                        return type === rc;
                                    })) {
                                        $scope.alerts.push({type: type, msg: msg});
                                    }
                                }

                                this.showError = function (msg) {
                                    showMessage(msg, "danger");
                                };

                                this.showSuccess = function (msg) {
                                    showMessage(msg, "success");
                                };

                                let self = this;
                                function responseError(response) {

                                    self.showError(response.data);
                                }
                            }]
                    }
                }


            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null
                },
                resolve: {
                    currentUsuario: ['$http', 'usuarioContext', '$stateParams', function ($http, usuarioContext, $params) {
                            return $http.get(usuarioContext + '/' + $params.usuarioId);
                        }]
                },
                views: {
                    listView: {
                        templateUrl: basePath + 'usuario.list.html',
                        resolve: {
                            usuarios: ['$http', 'usuarioContext', function ($http, usuarioContext) {
                                    return $http.get(usuarioContext);
                                }]
                        },
                    },
                    detailView: {

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