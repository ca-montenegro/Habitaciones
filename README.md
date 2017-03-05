#API Rest Habitaciones
## Introducción
Basándonos en la premisa la cual dicta que la relación Cliente-Servidor debe estar separada mediante interfaces uniformes, se espera que el cliente no conozca la forma de obtención de la información y cómo se almacena la misma en el servidor. Asimismo, el servidor no conoce la implementación a usar para presentar la información al cliente. Por tal motivo, se hace uso de intercambio de objetos de tipo JSON, en donde las interfaces uniformes hacen la correcta conversión de los atributos en propiedades de objeto tipo JSON con el fin de ser reconocidas y representadas en dicho formato. 
En la aplicación se hace uso de tres entidades las cuales contienen como atributo un ID autogenerado para identificar cada registro.
Para transmitir la información de servidor-cliente se realiza el correcto mapeo del objeto a formato tipo JSON estableciendo la siguiente estructura de visualización:
 
```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

[API para cada recurso](https://github.com/Uniandes-isis2603/habitaciones_01/wiki/Dise%C3%B1o%20API%20Rest#api-recursos) 
