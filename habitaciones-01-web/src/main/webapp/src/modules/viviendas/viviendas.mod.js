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
    /**
     * Modulo viviendas
     * @type {type}
     */
    /**
     * Constante module
     * @type {type}
     */
    const mod = ng.module('viviendaModule', ['ui.router']);
    /**
     * Se definen las constantes
     */
    mod.constant('viviendasContext', 'api/viviendas');
    mod.constant('reservasContext', 'api/reservas');
    mod.config(['$stateProvider', '$urlRouterProvider',
        /**
         * Funcion
         * @param {type} $stateProvider
         * @param {type} $urlRouterProvider
         * @return {undefined}
         */
        function ($stateProvider, $urlRouterProvider) {
            const basePath = 'src/modules/viviendas/';
            $urlRouterProvider.otherwise('/viviendasList');
            
            /**
             * Proveedor de estado
             * @param {type} $scope
             * @param {type} viviendas
             * @return {undefined}
             */
            $stateProvider.state('viviendas', {
                url: '/viviendas',
                abstract: true,
                resolve: {
                    viviendas: ['$http', 'viviendasContext',
                        /**
                         * Get actual
                         * @param {type} $http
                         * @param {type} viviendasContext
                         * @return {unresolved}
                         */
                        function ($http, viviendasContext) {
                            return $http.get(viviendasContext);},],
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'viviendas.html',
                        controller: ['$scope', 'viviendas',
                                    /**
                                     * Get actual
                                     * @param {type} $scope
                                     * @param {type} viviendas
                                     * @return {undefined}
                                     */
                            function ($scope, viviendas) {
                                $scope.viviendasRecords = viviendas.data},], 
                    },
                },
                /**
                 * Estado lista
                 * Principal del modulo
                 * 
                 */
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
                /**
                 * Estado buscar
                 * No se ha implementado completamente
                 */
            }).state('buscarVivienda', {
                url: '/filtrar',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath+'viviendas.list.html',
                    },
                    'detailView': {
                        templateUrl: basePath+'buscarVivienda.html',
                    },
                    'extraView': {
                        templateUrl: basePath+'botonAgregar.html',
                    },
                },
                /**
                 * Estado detalle
                 * Funcion borrar
                 * Funcion modificar
                 * Llama a agregar habitacion
                 * @param {type} $scope
                 * @param {type} viviendaActual
                 * @return {undefined}
                 */
            }).state('viviendaDetail', {
                url: '/{viviendaId:int}/detail',
                parent: 'viviendas',
                param: {
                    viviendaId: null,
                },
                resolve: {
                    viviendaActual: 
                            ['$http', 'viviendasContext', '$stateParams',
                        /**
                         * Resolve actual
                         * @param {type} $http
                         * @param {type} viviendasContext
                         * @param {type} $params
                         * @return {unresolved}
                         */
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+
                                    '/'+$params.viviendaId);
                        }
                        ,],
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'viviendas.detail.html',
                        controller: ['$scope', 'viviendaActual',
                            '$stateParams','$state',
                            '$http','viviendasContext',
                            /**
                             * Funcion obtener contexto
                             * @param {type} $scope
                             * @param {type} viviendaActual
                             * @param {type} $params
                             * @param {type} $state
                             * @param {type} $http
                             * @param {type} viviendasContext
                             * @return {undefined}
                             */
                            function ($scope, viviendaActual,$params,
                            $state, $http, viviendasContext) {
                                
                                $scope.viviendaActual =  viviendaActual.data;
                                /**
                                 * Constante con la vivienda a eliminar
                                 * @type {type}
                                 */
                                const vivi = $scope.viviendaActual;
                                $scope.eliminar = function(){
                                    const nuevoContext = viviendasContext+
                                            '/'+vivi.idVivienda;
                                    console.log(nuevoContext);
                                    /**
                                     * Llama al delete, cambia el estado
                                     */
                                    return $http.delete(nuevoContext).
                                            then(function () {
                                        // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        location.reload();
                                    },);
                                }
                            },],
                    },
                    'listView': {
                        templateUrl: basePath +
                                'habitacionesVivienda.list.html',
                        controller: ['$scope', 'viviendaActual',
                                    /**
                                     * Funcion vivienda actual
                                     * @param {type} $scope
                                     * @param {type} viviendaActual
                                     * @return {undefined}
                                     */
                            function ($scope, viviendaActual) {
                                $scope.viviendaActual = viviendaActual.data;
                            },],
                    },
                    'extraView': {         
                    },
                },
                /**
                 * Agrega habitacion
                 * Vuelve a lista
                 * @return {undefined}
                 */
            }).state('agregarHabitacion', {
                url: '/{viviendaId:int}/agregarHabitacion',
                parent: 'viviendas',
                resolve: {
                    viviendaActual: 
                            ['$http','viviendasContext','$stateParams',
                        /**
                         * Funcion get actual
                         * @param {type} $http
                         * @param {type} viviendasContext
                         * @param {type} $params
                         * @return {unresolved}
                         */
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+
                                    '/'+$params.viviendaId);
                        }
                        ,],
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarHabitacion.html',
                        controller: ['$scope', '$http',
                            '$state','viviendas',
                            'viviendasContext','$stateParams',
                            /**
                             * Funcion para agregar habitacion
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} viviendas
                             * @param {type} viviendasContext
                             * @param {type} $params
                             * @return {undefined}
                             */
                            function ($scope, $http, $state,
                            viviendas, viviendasContext, $params) {
                                
                                /**
                                 * Constante id vivienda actual
                                 * @type {type}
                                 */
                                const idActual = $params.viviendaId;
                                $scope.tempHabitacion = {
                                    'area': '',
                                    'capacidad': '',
                                    'descripcion': '',
                                    'imagen': 
                                            'https:/'+'/a0.muscache.com'+
                                            '/im/pictures/42492006'+
                                            '/d656f7da_original.jpg'+
                                            '?aki_policy=large',
                                    'valorDiario': '',
                                };
                                
                                /**
                                 * Agrega la habitacion
                                 * @return {unresolved}
                                 */
                                $scope.agregarHabitacion = function () {
                                    
                                    tempHabitacion = $scope.tempHabitacion;
                                    console.log($scope.tempHabitacion);
                                    const nuevoContext = viviendasContext+
                                            '/'+idActual+'/habitaciones';
                                    console.log(nuevoContext);
                                    return $http.post
                                    (nuevoContext,tempHabitacion)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        //$state.go('viviendasList');
                                        $state.go('viviendasList');
                                        location.reload();
                                    },);
                                    
                                }
                            },],
                    },
                },
                /**
                 * Estado modificar reserva
                 * @return {undefined}
                 */
            }).state('modificarVivienda', {
                url: '/{viviendaId:int}/modificarVivienda',
                parent: 'viviendas',
                resolve: {
                    viviendaActual: 
                            ['$http','viviendasContext','$stateParams',
                        /**
                         * Get vivienda actual
                         * @param {type} $http
                         * @param {type} viviendasContext
                         * @param {type} $params
                         * @return {unresolved}
                         */
                        function ($http, viviendasContext, $params) {
                            return $http.get(viviendasContext+
                                    '/'+$params.viviendaId);
                        }
                        ,],
                },
                views: {
                    'listView': {
                        templateUrl: basePath+'modificarVivienda.html',
                        controller: ['$scope','viviendaActual',
                            '$state','$http','viviendasContext',
                            /**
                             * Funcion modifivar vivienda
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} viviendas
                             * @param {type} viviendasContext
                             * @return {undefined}
                             */
                            function ($scope, viviendaActual,
                            $state, $http, viviendasContext) {
                                $scope.viviendaActual=viviendaActual.data;
                                const viv = $scope.viviendaActual;
                                console.log(viv);
                                $scope.tempVivienda = {
                                    "capacidad": viv.capacidad,
                                    "ciudad": viv.ciudad,
                                    "descripcion": viv.descripcion,
                                    "direccion": viv.direccion,
                                    "idVivienda": viv.idVivienda,
                                    "imagen": viv.imagen,
                                    "valorDiario": viv.valorDiario,
                                };
                                /**
                                 * Modifica la vivienda
                                 * @return {unresolved}
                                 */
                                $scope.modificarVivienda = function () {
                                    
                                    tempVivienda = $scope.tempVivienda;
                                    console.log(tempVivienda);
                                    const nuevoContext = 
                                            viviendasContext+'/'+tempVivienda.idVivienda;
                                    console.log(nuevoContext)
                                    return $http.put(nuevoContext,tempVivienda)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('viviendasList');
                                        location.reload();
                                    },);          
                                }
                            },],
                    },
                },
            }).state('registrarReserva', {
                url: '/{idVivienda:int}/\n\
{valorDiario:int}/registrarReserva',
                parent : 'reservas',
                param: {
                    idVivienda : null
                },
                views: {
                    'listView': {
                        templateUrl:'src/modules/reservas/nuevaReserva.html',
                        controller: ['$scope', '$http', '$state', 
                            'reservas','reservasContext','$stateParams',
                            /**
                             * Funcion guardar reserva
                             * @param {type} $scope
                             * @param {type} $http
                             * @param {type} $state
                             * @param {type} reservas
                             * @param {type} reservasContext
                             * @param {type} $params
                             * @return {viviendas.modL#26.viviendas.modL#26#L#48
                             * .viviendas.modL#26#L#48#controller-5}
                             */
                            function ($scope, $http, $state,
                            reservas, reservasContext, $params) {
                                
                                
                                $scope.tempReserva = {
                                    fechaInicio: '',
                                    fechaFin: '',  
                                    estado:'H',
                                    "vivienda" :
                                            {
                                                "idVivienda" : $params.idVivienda,
                                        "valorDiario" : $params.valorDiario                                        
                                    }           
                                };
                                console.log($scope.tempReserva);
                                /**
                                 * Funcion agregar reserva
                                 * @return {unresolved}
                                 */
                                $scope.agregarReserva = function () {
                                    
                                    tempReserva = $scope.tempReserva;
                                    console.log($scope.tempReserva);
                                    
                                    return $http.post
                                    (reservasContext+'/6', tempReserva)
                                            .then(function () {
                                                // $http.post es una promesa
                                        // cuando termine bien, cambie de estado
                                        $state.go('reservasList');
                                        console.log('check');
                                    }, responseError);
                                    
                                }
                                
                                this.closeAlert = function (index) {
                                    $scope.alerts.splice(index, 1);
                                };
                                
                                // Función showMessage: Recibe el mensaje en
                                //  String y su tipo con el fin de almacenarlo
                                //   en el array $scope.alerts.
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
            
            });
        }]);
})(window.angular);
