# spring-boot-clean-architecture-test
Arquitectura hexagonal como ejemplo de arquitectura limpia

## Tabla de contenidos:
---

- [Descripción y contexto](#descripción-y-contexto)
- [Tecnologías](#tecnologías)
- [Arquitecturas y patrones utilizados](#arquitecturas-y-patrones-utilizados)
- [Autor/es](#autores)

## Descripción y contexto
---
Aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que: 
  - Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
  - Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Consta de una base de datos donde se encuentra la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. Ejemplo de la tabla y sus campos:
 
<p align="center"><img src="https://www.soy3eres.es/tabla%20PRICES.PNG"/></p> 

## Tecnologías
---
  - Spring Boot
  - Java17
  - Base de datos H2
  - Swagger2
  - JUnit,Mockito

#### Dependencias Maven de proyecto
  <p align="center"><img src="https://www.soy3eres.es/dependencias%20maven.PNG"/></p>
  
## Arquitecturas y patrones utilizados
---
Este proyecto está basado en la arquitectura hexagonal, la cual se centra en separar la lógica empresarial central de las dependencias externas, permitiendo dotar nuestra aplicación de distintas capas con su propia responsabilidad. De esta manera consigue desacoplar las capas de la aplicación facilitando que evolucionen de manera aislada. Además, tener el sistema separado por responsabilidades nos facilitará la reutilización.
<p align="center"><img src="https://www.soy3eres.es/arquitectura%20hexagonal.png"/></p>

A través de esta arquitectura se logra que el dominio sea el núcleo de todas las capas, y que no se acople a nada externo, tal como exige el DDD.

#### Extructura del proyecto
<p align="center"><img src="https://www.soy3eres.es/extructura%20de%20proyecto.PNG"/></p>

En el proyecto tenemos el negocio, que básicamernte son <b>casos de uso</b> y <b>entidades de negocio</b>, donde los casos de uso son las funcionalidades. En la periferia del negocio tenemos los <b>puertos</b> representados por las interfaces. 

Independiente a este núcleo, tenemos los <b>adaptadores</b>, divididos en adaptadores de entrada y de salida. Los adaptadores de entrada son aquellos a través de los cuales se introducen datos a la aplicación (ApiRest, Frontend, etc), los de salida permiten a la aplicación comunicarse con el mundo exterior (conexión a base de datos o acceso al mecanismo de persistencia, otras API que consuman este servicio, etc).

Las interfaces (<b>puertos</b>) son implementadas por los <b>adaptadores</b> y son usadas por los <b>casos de uso</b>. Esta arquitectura permite que el dominio no dependa de nada, quedando totalmente desacoplado.

En el pom del proyecto tenemos las dependencias necesarias: JPA para el sistema de persistencia con la base de datos, MVC para la creación del API Rest, H2 como base de datos, finalmente Junit y Mockito para los test.

En el fichero de configuración del proyecto tenemos configurado el DataSource de la base de datos H2.
Contamos con un fichero de inicialización (initilization.java) para introducir los datos de prueba en la base de datos.

## Autor/es
---
Ricardo Rodríguez Reyes
