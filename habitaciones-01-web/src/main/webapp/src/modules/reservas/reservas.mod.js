(function (ng) {
    var mod = ng.module("reservasModule", ['ui.router']);
    /**
     * Recurso de reserva, lee directamente del back-end, para su correcto funcionamiento ejecutar el script en la carpeta /data/ScriptViviendas.sql 
     */
    mod.constant("reservasContext", "api/reservas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reservas/';
            $urlRouterProvider.otherwise("/reservas");

            $stateProvider.state('reservas', {
                url: '/reservas',
                abstract: true,
                resolve: {
                    reservas: ['$http', 'reservasContext', function ($http,reservasContext) {
                            return $http.get(reservasContext);
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'reservas.html',
                        controller: ['$scope', 'reservas', function ($scope, reservas) {
                                $scope.reservasRecords = reservas.data;
                            }]
                    }
                }
            }).state('reservasList', {
                url: '/list',
                parent: 'reservas',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reservas.list.html'
                    }
                }
            }).state('registrarReserva', {
                url: '/registrarReserva',
                parent : 'reservas',
                views: {
                    'listView' : {
                        templateUrl: basePath + 'nuevaReserva.html',
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
            }).state('reservasDetail', {
                url: '/{codigoReserva:int}/detail',
                parent: 'reservas',
                param: {
                    codigoReserva: null
                },
                views : {
                    'listView' : {
                        resolve: {
                     reservas: ['$http', 'reservasContext', function ($http, reservasContext) {
                             return $http.get(reservasContext);
                     }]
                }, },
                    'detailView': {
                        templateUrl: basePath + 'reservas.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentReserva = $scope.reservasRecords[$params.codigoReserva-1];
                            }]
                    } 
                }

            });
        }]);
})(window.angular);
