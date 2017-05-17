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
 * Modulo viviendas de la aplicación
 * S
 * @param {type} ng
 * @return {undefined}
 */
(function (ng){
    const mod = ng.module('viviendaModule', ['ui.router']);
    mod.constant('viviendasContext', 'api/viviendas');
    mod.constant('reservasContext', 'api/reservas');
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
                            return $http.get(viviendasContext);},],
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: ['$scope', 'viviendas', 
                            function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data},], 
                    },
                },
            }).state('viviendasList', {
                url: '/lista',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html',
                    },
                    'detailView': {
                        //templateUrl: basePath + 'botonBuscar.html'
                    },
                    'extraView': {
                    },
                },
            }).state('buscarVivienda', {
                url: '/filtrar',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'viviendas.list.html',
                    },
                    'detailView': {
                        templateUrl: basePath + 'buscarVivienda.html',
                    },
                    'extraView': {
                        templateUrl: basePath + 'botonAgregar.html',
                    },
                },
            }).state('viviendaDetail', {
                url: '/{viviendaId:int}/detail',
                parent: 'viviendas',
                param: {
                    viviendaId: null,
                },
                resolve: {
                    viviendaActual: 
                            ['$http', 'viviendasContext', '$stateParams',
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+
                                    '/'+$params.viviendaId);
                        }
                        ,],
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viviendas.detail.html',
                        controller: ['$scope', 'viviendaActual','$stateParams','$state','$http', 'viviendasContext',
                            function ($scope, viviendaActual,$params, $state, $http, viviendasContext) {
                                
                                $scope.viviendaActual =  viviendaActual.data;
                                let vivi = $scope.viviendaActual;
                                console.log(vivi);
                                
                                $scope.eliminar = function(){
                                    console.log(vivi);
                                const nuevoContext = viviendasContext+
                                            '/'+vivi.idVivienda;
                                    console.log(nuevoContext);
                                return $http.delete(nuevoContext).then(function () {
                                                    // $http.post es una promesa
                                                    // cuando termine bien, cambie de estado
                                                    $state.go('viviendasList');
                                                    console.log('Hola carlitos');
                                                },);
                            }
                            },],
                    },
                    'listView': {
                        templateUrl: basePath +
                                'habitacionesVivienda.list.html',
                        controller: ['$scope', 'viviendaActual',
                            function ($scope, viviendaActual) {
                                $scope.viviendaActual = viviendaActual.data;
                            },],
                    },
                    'extraView': {         
                    },
                },
            }).state('eliminarVivienda', {
                url: '/{viviendaId:int}/eliminar',
                parent: 'viviendas', views: {
                    'listView': {
                        templateUrl: basePath + 'agregarHabitacion.html',
                        controller: ['$scope', '$http',
                            '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,
                            viviendas, viviendasContext) {
                                console.log("entra");
                                $scope.eliminarVivienda = function () {
                                    console.log("entra1");
                                    tempHabitacion = $scope.tempHabitacion;
                                    console.log($scope.tempHabitacion);
                                    const nuevoContext = viviendasContext+
                                            '/'+$params.viviendaId;
                                    return $http.delete(nuevoContext)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        console.log('check');
                                    },);
                                    
                                }
                            },],
                    },
                },
            }).state('agregarHabitacion', {
                url: '/{viviendaId:int}/agregarHabitacion',
                parent: 'viviendas', views: {
                    'listView': {
                        templateUrl: basePath + 'agregarHabitacion.html',
                        controller: ['$scope', '$http',
                            '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,
                            viviendas, viviendasContext) {
                                
                                $scope.tempHabitacion = {
                                    'area': '',
                                    'capacidad': '',
                                    'descripcion': '',
                                    'imagen': 
                                            'https:/'+'/a0.muscache.com/im/pictures/42492006'+
                                            '/d656f7da_original.jpg?aki_policy=large',
                                    'valorDiario': '',
                                };
                                console.log($scope.tempHabitacion);
                                $scope.agregarHabitacion = function () {
                                    console.log(idV);
                                
                                    tempHabitacion = $scope.tempHabitacion;
                                    console.log($scope.tempHabitacion);
                                    const nuevoContext = viviendasContext+
                                            '/'+idV+'/habitaciones';
                                    return $http.post(nuevoContext, tempHabitacion)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        console.log('check');
                                    },);
                                    
                                }
                            },],
                    },
                },
            }).state('modificarVivienda', {
                url: '/{viviendaId:int}/modificarVivienda',
                parent: 'viviendas',
                resolve: {
                    viviendaActual: 
                            ['$http', 'viviendasContext', '$stateParams',
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+
                                    '/'+$params.viviendaId);
                        }
                        ,],
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'modificarVivienda.html',
                        controller: ['$scope', '$http',
                            '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,
                            viviendas, viviendasContext) {
                                
                                $scope.tempVivienda = {
                                    "capacidad": viviendaActual.capacidad,
                                    "ciudad": viviendaActual.ciudad,
                                    "descripcion": viviendaActual.descripcion,
                                    "direccion": viviendaActual.direccion,
                                    "idVivienda": viviendaActual.idVivienda,
                                    "imagen": viviendaActual.imagen,
                                    "valorDiario": viviendaActual.valorDiario,
                                };
                                console.log($scope.tempVivienda);
                                $scope.modificarVivienda = function () {
                                    
                                    tempVivienda = $scope.tempVivienda;
                                    console.log($scope.tempVivienda);
                                    const nuevoContext = viviendasContext+1;
                                    return $http.put(nuevoContext, tempVivienda)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        console.log('check');
                                    },);          
                                }
                            },],
                    },
                },
            }).state('registrarReserva', {
                url: '/registrarReserva',
                parent : 'reservas',
                views: {
                    'listView': {
                        templateUrl:'src/modules/reservas/nuevaReserva.html',
                        controller: ['$scope', '$http', '$state', 'reservas', 'reservasContext',
                            function ($scope, $http, $state,  reservas, reservasContext) {
                                
                                $scope.tempReserva = {
                                    fechaInicio: '',
                                    fechaFin: '',  
                                    estado:'H',
                                    'habitacion': {
                                        'area': 30,
                                        'capacidad': 2,
                                        'descripcion': 'bonita',
                                        'imagen': 'http:/'+'\n\
   /www.casacumbrero.com/images/casa_rural_habitaciones_1_b.jpg',
                                        'valorDiario': 858
                                    },
                                    'multa': {
                                        'codigoMulta': 101
                                    },
                                    'vivienda': {
                                        'anfitrion': {
                                            'correo': 'hola2@hola.com',
                                            'direccion': 'calle 2 No 2.2',
                                            'nombre': 'David',
                                            'numeroID': 2,
                                            'telefono': 6876188,
                                            'tipoID': 'Cedula',
                                            'puntuacion': 10
                                        },
                                        'capacidad': 8,
                                        'ciudad': 'Cali',
                                        'descripcion': 'Casa grande',
                                        'direccion': 'Calle 15',
                                        'idVivienda': 2,
                                        'imagen': 'https:/'+
                                                '/a0.muscache.com/im/pictures/'+
                                                '25735497/948807b4_original.jpg?aki_policy=large',
                                        'numeroHabitaciones':1,
                                        'valorDiario':458
                                    }
                                };
                                console.log($scope.tempReserva);
                                $scope.agregarReserva = function () {
                                    
                                    tempReserva = $scope.tempReserva;
                                    console.log($scope.tempReserva);
                                    
                                    return $http.post(reservasContext
                                            +'/6', tempReserva)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        console.log('check');
                                    },);
                                }
                            }],
                    },
                }
            
            });
        }],);
})(window.angular);
