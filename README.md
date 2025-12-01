# Microservicios de Productos e Inventario

El siguiente proyecto contiene 2 carpetas que corresponden a los 2 microservicios solicitados:  
**product-service** (microservicio de productos)  
**inventory-service** (microservicio de inventario)

Cada microservicio está construido con Spring Boot y utiliza las siguientes dependencias:

## Dependencias utilizadas

### Versión general utilizada:
- Java 17  
- Spring Boot 3.2.x

### Microservicio 1: product-service
Dependencias principales:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- h2 database
- lombok

### Microservicio 2: inventory-service
Dependencias principales:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- h2 database
- lombok
- spring-boot-starter-validation
- spring-boot-starter-actuator
- Dependencias para realizar llamadas HTTP al microservicio de productos

---

# Endpoints del Microservicio de Productos

Base URL: `http://localhost:8081/api/products`

## Crear un producto
**POST** `/api/products`  
Body de ejemplo:
```json
{
  "name": "Camiseta médica azul",
  "price": 45000
}
```

## Obtener todos los productos
**GET** `/api/products`

## Obtener un producto por ID
**GET** `/api/products/{id}`

## Actualizar un producto
**PUT** `/api/products/{id}`  
Body de ejemplo:
```json
{
  "name": "Camiseta médica azul actualizada",
  "price": 48000
}
```

## Eliminar un producto
**DELETE** `/api/products/{id}`

# Endpoints del Microservicio de Inventario

Base URL: `http://localhost:8082/api/inventarios`

## Consultar inventario de un producto por ID
**GET** `/api/inventarios/{productoId}`

Ejemplo de respuesta:
```json
{
  "data": {
    "type": "inventarios",
    "id": "1",
    "attributes": {
      "producto_id": 1,
      "cantidad": 10
    }
  }
}
```

## Crear inventario para un producto
**POST** `/api/inventarios/{productoId}/create`  
Body de ejemplo:
```json
{
  "quantity": 10
}
```

## Reducir cantidad por compra
**POST** `/api/inventarios/{productoId}/purchase`  
Body de ejemplo:
```json
{
  "quantity": 2
}
```
Este endpoint actualiza la cantidad restante e imprime un mensaje en consola simulando la emisión de un evento.

# Notas finales

- Ambos microservicios usan una base de datos H2 en memoria.
- Se recomienda iniciar primero `product-service` y luego `inventory-service`.
