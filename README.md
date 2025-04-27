# Servicios-Rest-Full
Este repositorio contiene servicios RESTful de productos y facturación, con un cliente web para interactuar con ellos

## Estructura del Proyecto

El proyecto está dividido en tres módulos principales:

1. **`facturacion-service`**: Servicio RESTful encargado de la gestión de facturas. Permite generar facturas a partir de productos
2. **`producto-service`**: Servicio RESTful encargado de la gestión de productos. Permite agregar, eliminar y listar productos en un sistema.
3. **`integrador-web`**: Cliente web para interactuar con los servicios `facturacion-service` y `producto-service`. 

## Requisitos

- JDK 11 o superior.
- Maven o Gradle para la gestión de dependencias.
- Dependencias de Spring Boot para los servicios RESTful.

## Instrucciones de ejecución
Desde consola Windows puedes ejecutarlo yendo a tu carpeta donde estan los archivos 
-**C:\Users\Nombre_deUsuario\Carpeta_archivos\Carpeta_facturacion-service**
-**mvn spring-boot:run** 
1. **`facturacion-service`**:  **http://localhost:8080/swagger-ui/index.html (PaginaSwagger)**
2. **`producto-service`**:  **http://localhost:8081/swagger-ui/index.html (PaginaSwagger)**
3. **`integrador-web`**:  **VisualStudioCode>click derecho>LiveServer**

Es importante tomar en cuneta que sin **`mvn spring-boot:run`** no corren las paginas de servicio y por lo tanto no cargan bien los datos en el integrador
