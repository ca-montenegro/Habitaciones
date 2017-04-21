

(function (ng){
    var login = ng.module('AgregarVivienda', ['ui.router']);
    login.controller('MainController', function ($scope, $http, $window,$state) {
    
	$scope.agregar = function(){
            $http.get("api/viviendas/"+$scope.id).then(
                    function(response){
                        
                        $scope.data = response.data;
                var anfitrion = {
                    "correo": data.correo,
                    "direccion": data.direccion,
                    "image": data.image,
                    "nombre": data.nombre,
                    "telefono": data.telefono
                }
      
                var obj ={
                    "anfitrion": anfitrion,
                    "capacidad": $scope.capacidad,
                    "ciudad": $scope.ciudad,
                    "descripcion": $scope.descripcion,
                    "direccion": $scope.direccion,
                    "imagen": "https://a0.muscache.com/im/pictures/42492006/d656f7da_original.jpg?aki_policy=large",
                    "valorDiario": $scope.precio
                }
            
                $http.post('/api/viviendas', obj).then($state.go("viviendasList"), $window.alert("Error agregando la vivienda :("));
            }
                    )   
        }
    });
})(window.angular);
