# Sistema de Gestión de Estudiantes

Aplicación web desarrollada con Spring Boot para administrar alumnos, con control de acceso por roles (ADMIN y USER) y base de datos en MySQL.

## Tecnologías usadas
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL y Bootstrap 5

## Cómo está organizado el proyecto
- **controller:** Recibe las peticiones de la página web.
- **service:** Tiene la lógica principal del programa.
- **repository:** Se conecta con la base de datos.
- **model:** Guarda las clases principales (como Alumno y Usuario).

## Pasos para ejecutarlo
1. Descarga o copia la carpeta del proyecto en tu computador.
2. Abre MySQL y crea una base de datos con el nombre `evalucion_db`.
3. Abre el archivo `src/main/resources/application.properties` y revisa que tu usuario y contraseña de MySQL coincidan con los tuyos.
4. Abre el proyecto en Eclipse (o el programa que uses), busca la clase principal `AppEstudiantilApplication` y ejecútala.
5. Abre tu navegador de internet y entra a `http://localhost:8080/login` para empezar a usar la aplicación.
