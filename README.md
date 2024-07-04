
---

# Foro-Hub API

Último challenge para la ruta Back End Spring en ONE G6.

Esta documentación detalla las rutas y funcionalidades de la API para la aplicación de foro utilizando Spring Boot.

## Swagger UI OpenAPI

Puedes acceder a la documentación completa de la API utilizando Swagger UI. A continuación se detalla cómo acceder y utilizar la documentación:

- **URL Swagger UI**: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

## Funcionalidades Principales

### Autenticación y Autorización

La API utiliza JWT token Bearer para autenticación y autorización. Las siguientes rutas están disponibles para gestionar usuarios:

- `POST /auth/register`: Registrar un nuevo usuario.
- `POST /auth/login`: Iniciar sesión y obtener un token JWT.

### Operaciones CRUD para Tópicos del Foro

Las siguientes rutas permiten operaciones CRUD sobre los tópicos del foro:

- `GET /topicos`: Obtener todos los tópicos.
- `GET /topicos/{id}`: Obtener un tópico específico por ID.
- `POST /topicos`: Crear un nuevo tópico.
- `PUT /topicos/{id}`: Actualizar un tópico existente por ID.
- `DELETE /topicos/{id}`: Eliminar un tópico por ID.

### Ejemplos de Uso

A continuación se presentan ejemplos de uso utilizando cURL:

#### Registro de Usuario

```bash
curl -X POST "http://localhost:8081/auth/register" -H "Content-Type: application/json" -d '{
  "nombre": "usuario",
  "email": "usuario@example.com",
  "contrasena": "contraseña"
}'
```

#### Inicio de Sesión

```bash
curl -X POST "http://localhost:8081/auth/login" -H "Content-Type: application/json" -d '{
  "email": "usuario@example.com",
  "contrasena": "contraseña"
}'
```

#### Creación de Tópico

```bash
curl -X POST "http://tu-host/api/topics" -H "Authorization: Bearer token-jwt" -H "Content-Type: application/json" -d '{
  "titulo": "Título del Tópico",
  "mensaje": "Contenido del tópico"
  "autorId": 1,
  "cursoId":1
}'
```

---