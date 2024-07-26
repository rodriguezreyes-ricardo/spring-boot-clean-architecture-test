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

## Autor/es
---
Ricardo Rodríguez Reyes
