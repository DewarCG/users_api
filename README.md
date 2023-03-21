# Ejercicio API Usuarios

Este ejercicio expone una API Rest que permite registrar un usuario con una lista de telefonos asociados.

## Descargar proyecto

```bash
git clone git@github.com:DewarCG/users_api
```

### Configuracion

El archivo de configuracion se encuentra en la ruta `src/main/resources/application.yaml`.
La propiedad para configurar el patron de expresion regular para la contraseña es: `users.password_regex`
La propiedad para configurar la clave privada para firmar los tokens JWT es: `jwt.secret`

### Probar endpoint de registro

En el archivo `test_requests.http` o `test_requests.md` puedes encontrar las pruebas E2E para probar el endpoint de registro: `/api/v1/users`.

## Tecnologias empleadas

1. JDK 8+
2. Gradle
3. Spring Boot
4. Spring Data bajo Hibernate
5. Base de datos H2 en memoria

## Arquitectura

El proyecto esta compuesto por tres capas: Capa controlador, capa de negocio y capa de acceso a datos o repositorios.

### Capa controlador

Se encarga de recibir y enviar las peticiones HTTP al cliente, esta capa solo debe tener comunicacion con la capa de negocio.

### Capa de negocio

Es la encargada de realizar todas las validaciones y operaciones de negocio. Solo debe tener comunicacion con la capa de repositorio o con su misma capa.

### Capa de datos o repositorios

Es la capa encargada de realizar operaciones contra una fuente de datos, para este caso una base de datos relacional en memoria: H2.

## Diagramas

### Secuencia de registro de usuario

![Secuencia](doc/sequence_diagram.svg)

### Flujo de registro de usuario

![Flujo](doc/flowchart2.svg)
