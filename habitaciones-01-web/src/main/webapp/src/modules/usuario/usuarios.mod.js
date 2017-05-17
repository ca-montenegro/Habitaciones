/* 
 * Copyright (C) 2017 c.penaloza.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */


/**
 * Modulo usuario de la aplicación.
 * S
 * @param {type} ng
 * @returns {undefined}
 */
(function (ng) {
    /**
     * Modulo usuario
     * @type type
     */
    const mod = ng.module('usuarioModule', ['ui.router']);
    mod.constant('usuarioContext', 'api/usuarios');
    mod.config(['$stateProvider', '$urlRouterProvider',
        /**
         * 
         * @param {type} $stateProvider
         * @param {type} $urlRouterProvider
         * @returns {undefined}
         */
        function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/usuario/';
            $urlRouterProvider.otherwise('/usuariosList');
            /**
             * Proveedor de estados
             * @param {type} $scope
             * @param {type} usuarios
             * @returns {undefined}
             */
            $stateProvider.state('usuarios', {
                url: '/usuarios',
                abstract: true,
                /**
                 * Resolve
                 */
                resolve: {
                    usuarios: ['$http', 'usuarioContext',
                        /**
                         * 
                         * @param {type} $http
                         * @param {type} usuarioContext
                         * @returns {unresolved}
                         */
                        function ($http, usuarioContext) {
                            return $http.get(usuarioContext);
                        }, ]
                },
                /**
                 * views
                 */
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: ['$scope', 'usuarios',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} usuarios
                             * @returns {undefined}
                             */
                                    function ($scope, usuarios) {
                                $scope.usuariosRecords = usuarios.data;
                            }, ]
                    },
                },
                /**
                 * state usuariosList
                 * Principal modulo
                 */
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuario.list.html'

                    }
                },
                /**
                 * state usuarioAdminLogin
                 * Login del admin con controlador. 
                 * usuario = JaimeAdmin contra = 1234
                 */
            }).state('usuarioAdminLogin', {
                url: '/adminLogin',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'modal.html',
                        controller: ['$scope', '$http', '$state',
                            '$window', 'usuarios', 'usuarioContext',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} $window
                             * @param {type} usuarios
                             * @param {type} usuarioContext
                             * @returns {undefined}
                             */
                                    function ($scope, $http, $state,
                                    $window, usuarios, usuarioContext) {
                                $scope.usuariosRecords = usuarios.data;

                                $scope.tempAdmin = {
                                    usuario: '',
                                    contrasenha: '',
                                };
                                console.log($scope.tempAdmin);
                                $scope.ingresar = function () {

                                    tempAdmin = $scope.tempAdmin;
                                    if ('JaimeAdmin' == tempAdmin.usuario) {

                                        if (1234 == tempAdmin.contrasenha) {

                                            $window.alert('Bienvenido');

                                            $state.go('usuariosList');

                                        }
                                    }
                                }
                            }, ]
                    },
                },
                /**
                 * estado para registrar
                 * state registrarUsuario
                 */
            }).state('registrarUsuario', {
                url: '/registrarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'registrar.html',
                        controller: ['$scope', '$http', '$state',
                            'usuarios', 'usuarioContext',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} usuarios
                             * @param {type} usuarioContext
                             * @returns {undefined}
                             */
                                    function ($scope, $http, $state,
                                    usuarios, usuarioContext) {
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
                                    image: '',

                                };
                                console.log($scope.tempUser);
                                $scope.registrar = 
                                        /**
                                         * 
                                         * @returns {unresolved}
                                         */
                                        function () {

                                    tempUser = $scope.tempUser;
                                    console.log(tempUser);
                                    return $http.post(usuarioContext, tempUser)
                                            .then(function () {
                                                // $http.post es una promesa
                                                // cuando termine bien, cambie de estado
                                                $state.go('viviendasList');
                                                console.log('check');
                                            },);
                                }

                            },]
                    },
                },

                /**
                 * Ingresarusuario
                 * se ingresa un usuaroi
                 * Entra un usuario a ver reservas
                 * cambia de estado
                 * @returns {undefined}
                 */
            }).state('ingresarUsuario', {
                url: '/ingresarUsuario',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ingresoUsuario.html',
                        controller: ['$scope', '$http', '$state',
                            '$window', 'usuarios', 'usuarioContext',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} $window
                             * @param {type} usuarios
                             * @param {type} usuarioContext
                             * @returns {undefined}
                             */
                                    function ($scope, $http, $state,
                                    $window, usuarios, usuarioContext) {
                                $scope.usuariosRecords = usuarios.data;
                                usuariosRecords = $scope.usuariosRecords;
                                console.log(usuariosRecords.length);

                                $scope.tempCliente = {
                                    usuario: '',
                                    contrasenha: '',
                                };
                                console.log($scope.tempCliente);
                                $scope.ingresarUsuario = 
                                        /**
                                         * 
                                         * @returns {undefined}
                                         */
                                        function () {

                                    tempCliente = $scope.tempCliente;
                                    const sizeRec = usuariosRecords.length;
                                    let encontrado = false;
                                        for (let j = 0; j < sizeRec; j++) {

                                        console.log(usuariosRecords[j]);
                                        if (usuariosRecords[j].usuario ==
                                                tempCliente.usuario) {

                                            if (usuariosRecords[j].contrasenha ==
                                                    tempCliente.contrasenha) {

                                                $window.alert('Bienvenido');
                                                encontrado = true;
                                                $state.go('reservasList');
                                                break;
                                            }
                                        }
                                    }
                                    if (!encontrado) {
                                        window.alert('No se reconoce el \n\
                                        usuario o contraseña');
                                    }
                                }
                            }, ]
                    },
                },

               
              /**
               * Modificar usuario
               * modifica el usuario
               * param
               * usuarioID
               * @returns {undefined}
               */ 
            }).state('modificarUsuario', {
                url: '{usuarioId: int}/modificarUsuario',
                parent: 'usuarios',
                param: {
                    usuarioId: null,
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'modificarUsuario.html',
                        controller: ['$scope', '$http', '$state',
                            'usuarios', 'usuarioContext', '$stateParams',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} usuarios
                             * @param {type} usuarioContext
                             * @param {type} $params
                             * @returns {undefined}
                             */
                                    function ($scope, $http, $state,
                                    usuarios, usuarioContext, $params) {
                                //$scope.usuariosRecords = usuarios.data;

                                $scope.tempUserMo = {
                                    numeroID: '',
                                    tipoID: '',
                                    nombre: '',
                                    usuario: '',
                                    contrasenha: '',
                                    correo: '',
                                    direccion: '',
                                    telefono: '',
                                    numeroTarjeta: '',
                                    image:'',
                                };
                                console.log($scope.tempUserMo);
                                $scope.update = 
                                        /**
                                         * 
                                         * @returns {unresolved}
                                         */
                                        function () {

                                    tempUserMo = $scope.tempUserMo;
                                    console.log(tempUserMo);
                                    return $http.put(usuarioContext + "/" + $params.usuarioId, tempUserMo)
                                            .then(
                                            /**
                                             * 
                                             * @returns {undefined}
                                             */
                                            function () {
                                                // $http.post es una promesa
                                                // cuando termine bien, cambie de estado
                                                $state.go('usuariosList');
                                                console.log('check');

                                            }, );
                                }
                            }, ]
                    },
                },

                /**
                 * Estado detail de usuario
                 * muestra los detalles del usuario
                 * param
                 * usuarioID
                 */
            }).state('usuarioDetail', {
                url: '/{usuarioId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuarioId: null,
                },
                resolve: {
                    currentUsuario: ['$http', 'usuarioContext', '$stateParams',
                        /**
                         * 
                         * @param {type} $http
                         * @param {type} usuarioContext
                         * @param {type} $params
                         * @returns {unresolved}
                         */
                        function ($http, usuarioContext, $params) {
                            return $http.get(usuarioContext + '/'
                                    + $params.usuarioId);
                        }, ]
                },
                views: {
                    listView: {
                        templateUrl: basePath + 'usuario.list.html',
                        resolve: {
                            usuarios: ['$http', 'usuarioContext',
                                /**
                                 * Funcion
                                 * Dobdle funcion
                                 * @param {type} $http
                                 * @param {type} usuarioContext
                                 * @returns {unresolved}
                                 */
                                        function ($http, usuarioContext) {
                                    return $http.get(usuarioContext);
                                }, ]
                        },
                    },
                    detailView: {

                        templateUrl: basePath + 'usuario.detail.html',
                        controller: ['$scope', 'currentUsuario',
                            /**
                             * 
                             * @param {type} $scope
                             * @param {type} currentUsuario
                             * @returns {undefined}
                             */
                                    function ($scope, currentUsuario) {
                                console.log(currentUsuario.data);
                                $scope.currentUsuario = currentUsuario.data;
                            }, ]
                    },
                },
            });
        }]);
})(window.angular);

