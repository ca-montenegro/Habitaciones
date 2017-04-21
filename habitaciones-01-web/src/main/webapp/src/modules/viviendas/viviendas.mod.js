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
                    },
                    'detailView': {
                        templateUrl: basePath + 'botonBuscar.html'
                    },
                    'extraView': {
                        templateUrl: basePath + 'botonAgregar.html'
                    }
                }
            }).state('agregarVivienda', {
                url: '/agregar',
                parent: 'viviendas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'agregarVivienda.html',
                        controller: ['$scope', '$http', '$state', 'viviendas', 'viviendasContext',
                            function ($scope, $http, $state,  viviendas, viviendasContext) {
                                /**
                                $scope.tempVivienda = {
                                    anfitrion= {},
                                    capacidad: '',
                                    ciudad: '',
                                    descripcion: '',
                                    direccion: '',
                                    idVivienda: '',
                                    imagen: '',
                                    numeroTelefono: '',
                                    valorDiario: ''                                    
                                };
                                console.log($scope.tempVivienda);
                                $scope.agregarVivienda = function () {
                                    
                                    $http.get("api/viviendas/"+$scope.id).then(
                                            function(response){
                                                
                                                $scope.data = response.data;
                                        anfitri = {
                                            "correo": data.correo,
                                            "direccion": data.direccion,
                                            "image": data.image,
                                            "nombre": data.nombre,
                                            "telefono": data.telefono
                                        }})
                                    $scope.tempVivienda.anfitrion=anfitri;
                                    tempVivienda = $scope.tempVivienda;
                                    console.log(tempVivienda);
                                    
                                    return $http.post(viviendasContext, tempVivienda)
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
                                    var types = ["info", "danger", "warning", "success"];
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
                                
                                var self = this;
                                function responseError(response) {
                                    
                                    self.showError(response.data);
                                }**/
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
                    },
                    'listView': {
                        templateUrl: basePath + 'habitacionesVivienda.list.html',
                        controller: ['$scope', 'viviendaActual', function ($scope, viviendaActual) {
                                $scope.viviendaActual = viviendaActual.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);
