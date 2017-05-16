/* 
 * To change this license head        // Internal modules dependencies       
er, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    const app = angular.module('mainApp', [
        // External dependencies
        'ui.router',

        // Internal modules dependencies       
        'viviendaModule',
        'reservasModule',
        'habitacionModule',
        'usuarioModule',
        'MainLogin',
        'anfitrionModule'

    ]); 
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
