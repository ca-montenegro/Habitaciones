(function (ng){
var login = ng.module('MainLogin', ['ui.router']);
login.controller('MainController', function ($scope, $http, $window,$state) {
    
	$scope.loge = function(){
	console.log($scope.usrname1);
	},

	$scope.verificar = function(){
      
    //$window.alert("hola");
    $http.get("data/datos.json")
    .then(
      //ok
      function(response){
        
        $scope.datos = response.data;
        var encontrado = false;
        console.log($scope.datos.length);
        for(var i = 0; i<$scope.datos.length; i++)
        {
        	$scope.usuario = (response.data[i].usuario);
        	if($scope.usuario==$scope.usrname1 && !encontrado){
        		$scope.contrasenha = (response.data[i].contrasenha);
        		if($scope.psw == $scope.contrasenha){
        			$window.alert("Bienvenido");	
        			encontrado = true;
        			$state.go("usuariosList");
        			break;	
        		}
        	}
        }
        if(!encontrado){
        		window.alert("No se reconoce el usuario o contraseña");
                    }
        /**$scope.usuario = (response.data[1].usuario);
        $scope.contrasenha = (response.data[1].contrasenha);
        if($scope.usuario==$scope.usrname1)
        	if($scope.psw == $scope.contrasenha)
        		window.alert("bienvenido");
        else if ($scope.usuario!=$scope.usrname1)
        	window.alert("No se reconoce el usuario o constraseña");
        */
        
      },
      //error
      function(response)
      {
        $scope.datos = response.data;
      });
    }
  });
})(window.angular);
