# Challenge Backend Java - Servicio de Usuarios

Este proyecto es el **Microservicio de Usuarios** como parte de un challenge técnico. Es un servicio reactivo que gestiona usuarios y se comunica con el `servicio-productos` para obtener información de productos.

---

## 🚀 Tecnologías Implementadas

Este proyecto demuestra una arquitectura moderna y reactiva de microservicios, cubriendo los siguientes puntos:

* **Java 17:** Uso de `Records` para DTOs inmutables.
* **Spring Boot 3:** Framework principal.
* **Spring WebFlux:** Stack 100% reactivo y no bloqueante para los endpoints (Requisito Opcional).
* **Programación Funcional:**
    * Uso de `Mono` y `Flux` (Project Reactor) para manejar flujos de datos asíncronos.
    * Uso de `Streams` y expresiones `Lambda` para la lógica de negocio.
* **Comunicación de Microservicios:**
    * **`WebClient`** para llamadas HTTP reactivas y no bloqueantes al `servicio-productos` (Requisito Opcional).
    * Configuración externalizada en `application.properties` para la URL del cliente.
* **Pruebas Unitarias:**
    * **JUnit 5** para la estructura de los tests.
    * **Mockito** para simular (mockear) la dependencia de `WebClient`.
    * **`reactor-test`** (StepVerifier) para probar `Mono` y `Flux` de forma declarativa.
* **Manejo de Errores:**
    * Implementación de un `GlobalExceptionHandler` (`@RestControllerAdvice`) para centralizar las respuestas de error (Mejora).
    * Excepciones personalizadas (`UserNotFoundException`).

---

## 📋 Cómo Ejecutar

1.  **Clonar el repositorio:**
    ```bash
    git clone [TU_LINK_DE_GITHUB_AQUI]
    ```
2.  **Navegar al directorio:**
    ```bash
    cd servicio-usuarios
    ```
3.  **Ejecutar el proyecto (requiere Maven):**
    ```bash
    mvn spring-boot:run
    ```
4.  El servicio estará corriendo en `http://localhost:8080`.

---

## 📦 Endpoints de la API

* `GET /users`
    * Devuelve un `Flux` (lista) de todos los usuarios.
* `GET /users/{id}`
    * Devuelve un `Mono` (un) usuario por su ID.
* `GET /users/productos-de-otro-microservicio`
    * **Endpoint de comunicación:** Llama al `servicio-productos` (`:8081`) y devuelve un `Flux` de todos los productos.

### Ejemplos de Prueba (Endpoints)

Asegúrate de que ambos servicios estén corriendo (`:8080` y `:8081`).

* **Probar `GET /users/{id}` (Éxito):**
    * [http://localhost:8080/users/1](http://localhost:8080/users/1)
* **Probar `GET /users/{id}` (Error 404):**
    * [http://localhost:8080/users/999](http://localhost:8080/users/999)
* **Probar Comunicación (Llamada a Productos):**
    * [http://localhost:8080/users/products-from-other-service](http://localhost:8080/users/products-from-other-service)