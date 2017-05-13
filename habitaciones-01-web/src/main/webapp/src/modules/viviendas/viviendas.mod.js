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

(function (ng){
    const mod = ng.module('viviendaModule', ['ui.router']);
    mod.constant('viviendasContext', 'api/viviendas');
    mod.config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/viviendas/';
            $urlRouterProvider.otherwise('/viviendasList');
            
            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    viviendas: ['$http', 'viviendasContext',
                        function ($http, viviendasContext) {
                            return $http.get(viviendasContext);}]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: ['$scope', 'viviendas', 
                            function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data}]  
                    },
                },
            }).state('viviendasList', {
                url: '/lista',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'botonBuscar.html'
                    },
                    'extraView': {
                        templateUrl: basePath + 'botonAgregar.html'
                    },
                }
            }).state('agregarVivienda', {
                url: '/agregar',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarVivienda.html',
                        controller: ['$scope', '$http', '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,
                            viviendas, viviendasContext) {
                                
                            }]
                    }
                }
            }).state('buscarVivienda', {
                url: '/filtrar',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'buscarVivienda.html'
                    },
                    'extraView': {
                        templateUrl: basePath + 'botonAgregar.html'
                    },
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
                    },
                    'listView': {
                        templateUrl: basePath + 'habitacionesVivienda.list.html',
                        controller: ['$scope', 'viviendaActual', function ($scope, viviendaActual) {
                                $scope.viviendaActual = viviendaActual.data;
                            }]
                    },
                    'extraView': {
                        templateUrl: basePath + 'botonAgregarHabitacion.html'
                    },
                }
            }).state('agregarHabitacion', {
                url: '/agregarHabitacion',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarHabitacion.html',
                        controller: ['$scope', '$http', '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,  viviendas, viviendasContext) {
                                
                                $scope.tempHabitacion = {
                                    'area': '',
                                    'capacidad': '',
                                    'descripcion': '',
                                    'imagen': 'https://a0.muscache.com/im/pictures/42492006/d656f7da_original.jpg?aki_policy=large',
                                    'valorDiario': ''                                    
                                };
                                console.log($scope.tempHabitacion);
                                $scope.agregarHabitacion = function () {
                                    
                                    tempHabitacion = $scope.tempHabitacion;
                                    console.log($scope.tempHabitacion);
                                    
                                    return $http.post(viviendasContext+'/2'+'/habitaciones', tempHabitacion)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        console.log('check');
                                    }, responseError);
                                    
                                }
                                
                                this.closeAlert = function (index) {
                                    $scope.alerts.splice(index, 1);
                                };
                                
                                // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
                                function showMessage(msg, type) {
                                    const types = 
                                            ['info', 'danger', 'warning', 'success'];
                                    if (types.some(function (rc) {
                                        return type === rc;
                                    })) {
                                        $scope.alerts.push({type: type, msg: msg});
                                    }
                                }
                                
                                this.showError = function (msg) {
                                    showMessage(msg, 'danger');
                                };
                                
                                this.showSuccess = function (msg) {
                                    showMessage(msg, 'success');
                                };
                                
                                const self = this;
                                function responseError(response) {
                                    
                                    self.showError(response.data);
                                }
                            }]
                    },
                }
            }).state('registrarReserva', {
                url: '/registrarReserva',
                parent : 'reservas',
                views: {
                    'listView' : {
                        templateUrl: 'src/modules/reservas/nuevaReserva.html',
                        controller: ['$scope', '$http', '$state', 'reservas', 'reservasContext',
                                function ($scope, $http, $state, reservas, reservasContext) {
                        $scope.tempUser = {
                            fechaInicio: '',
                            fechaFin: '',
                            costo: '',
                            estado: '',
                            multa: '',
                            habitacion: '',
                            vivienda: ''
                        };
                        
                        console.log($scope.tempUser);
                        $scope.registrar = function() {
                            
                            tempUser = $scope.tempUser;
                            console.log(tempUser);
                            if(true){
                                return $http.post(reservasContext, tempUser)
                                        .then(function()
                                {
                                    $state.go('reservasList');
                                    console.log('check');
                                }, responseError);
                            }
                        }
                        this.closeAlert = function (index) {
                            $scope.alerts.splice(index, 1);
                        };
                        
                        function showMessage(msg, type) {
                            var types = ["info", "danger", "warning", "success"];
                            if(types.some(function (rc) {
                                return type === rc;
                            })) {
                                $scope.alerts.push({type: type, msg: msg});
                            }
                        }
                        
                        this.showError = function(msg) {
                            showMessage(msg, "danger");
                        };
                        
                        this.showSuccess = function(msg) {
                            showMessage(msg, "success");
                        };
                        
                        var self = this;
                        function responseError(response) {
                            self.showError(response.data);
                        }
                        }]
                    }
                }
            
            });
        }]);
})(window.angular);
