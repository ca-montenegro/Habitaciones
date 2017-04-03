(function (ng) {
    // Definición del módulo
    var mod = ng.module("Viviendas2Module", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/viviendas2/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/viviendas2");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('viviendas2', {
                // Url que aparecerá en el browser
                url: '/viviendas2',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    books: ['$http', function ($http) {
                            return $http.get('data/viviendas.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'viviendas2.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: ['$scope', 'viviendas2', function ($scope, viviendas2) {
                        $scope.viviendas2 = viviendas2.data;
                    }]              
            });
        }
    ]);
})(window.angular);
