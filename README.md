# Intercorp REST api

## Descripción:
El siguiente proyecto fue desarrollado a partir del uso del framework spring boot, en Java 8 con base de datos MYSql.
El mismo se desplego en un servidor Heroku por su simplicidad de uso y por su plataforma robusta y económica.

Plataforma:

    - Java 8
    - Spring Boot
    - MySQL 8

Dependencias:
    
    - Swagger
    - ModelMapper
    - Lombook

El endpoint root para acceder a los distintos servicios desplegados es: 

    https://intercorp-rest-api-amanca.herokuapp.com/cliente

##Desarrollo

Para el siguiente proyecto se desarrollaron 3 endpoints manejados por un solo controlador, que cumplen con lo requerido.
Corresponden a 2 método de acceso por verbo GET y un método de acceso por verbo POST:

## A - .../listclientes (GET)
    
Permite lista los clientes registrados en la aplicacion, exponiendo los siguientes datos de los mismos:

    - Nombre
    - Apellido
    - Edad
    - Fecha de nacimiento
    - Fecha de presunta muerte


## B - .../kpideclientes (GET)

Permite obtener información sobre la media y el desvio estandar calculado a partir de la edad de los clientes registrados:
Los datos que lo conforman són:
    
    - Promedio
    - Desvio

## C - .../creacliente (POST)

Permite agregar nuevos usuarios al sistema. A partir de un formulario simple se requieren los datos del usuario.
Al completar la carga, el mismo será almacenado en la DB.
Para ello debera pasarse por payload la siguiente estructura

```
{
    "nombre": "{NOMBRE_CLIENTE}",
    "apellido": "{APELLIDO_CLIENTE}",
    "edad": {EDAD_CLIENTE},
    "fechaNacimiento": "{FECHA_DE_NACIMIENTO}" --> formato año-mes-dia ("yyyy-MM-dd")  
}
```

## * Para mayor información, una vez desplegado el proyecto de forma local ingresar a la documentación de Swagger.

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    
o desde: 

[https://intercorp-rest-api-amanca.herokuapp.com/swagger-ui.html](https://intercorp-rest-api-amanca.herokuapp.com/swagger-ui.html)
