(function (ng) {
    // Definición del módulo
    var mod = ng.module("principalModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/principal/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('principal', {
                // Url que aparecerá en el browser
                url: '/habitaciones',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
               
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'principal.html'
            });
        }
    ]);
})(window.angular);
