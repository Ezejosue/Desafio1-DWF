# Sistema de Gestión de Recursos Humanos

## Descripción del Proyecto

Este proyecto consiste en el desarrollo de un sistema web para la gestión de recursos humanos de la Universidad Don Bosco. La aplicación permite gestionar la información de los empleados, como su nombre, correo electrónico, número de teléfono, salario, y departamento al que pertenecen. El sistema está desarrollado en Java, siguiendo el patrón de diseño MVC (Modelo-Vista-Controlador), y utiliza JDBC para la conexión a la base de datos.

## Integrantes del Equipo

- **Josue Ezequiel Avalos Avalos**
- **Rolin Alberto Azmitia Maldonado**

## Tecnologías Utilizadas

- **Java EE**: Lenguaje de programación principal para la implementación del sistema.
- **Servlets**: Utilizados como controladores en la arquitectura MVC.
- **JDBC**: Utilizado para la conexión y consultas a la base de datos MySQL.
- **MySQL**: Sistema de gestión de bases de datos relacional donde se almacena la información de los empleados y departamentos.
- **JSP (JavaServer Pages)**: Utilizado para la creación de las vistas de la aplicación.
- **Bootstrap**: Framework CSS utilizado para diseñar una interfaz de usuario atractiva y responsiva.
- **JSTL (JavaServer Pages Standard Tag Library)**: Utilizada para la manipulación de datos en las vistas JSP.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- **model**: Contiene las clases POJO (Plain Old Java Object) que representan las entidades del sistema (e.g., `Employee`, `Department`).
- **controller**: Contiene los servlets que actúan como controladores para manejar las peticiones del usuario y comunicarse con el modelo y la vista (e.g., `EmployeeController`, `DepartmentController`).
- **view**: Contiene los archivos JSP que forman la interfaz de usuario del sistema.

## Configuración del Proyecto

1. **Base de Datos**: 
    - Crear una base de datos llamada `hr_system`.
    - Importar el archivo SQL que contiene las tablas `employees` y `departments`.

2. **Conexión a la Base de Datos**:
    - Configurar la clase `DatabaseConnection` en el paquete `util` con los detalles de tu servidor MySQL:
    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/hr_system";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    ```

3. **Compilación y Despliegue**:
    - Compilar el proyecto utilizando un IDE como Eclipse o IntelliJ IDEA.
    - Desplegar la aplicación en un servidor de aplicaciones compatible con Java EE, como Payara o Apache Tomcat.

## Uso de la Aplicación

Una vez desplegado el sistema:

1. **Inicio**: La página principal lista todos los empleados registrados en el sistema.
2. **Agregar/Editar Empleado**: A través de un formulario, los usuarios pueden agregar o editar la información de los empleados.
3. **Eliminar Empleado**: Los usuarios pueden eliminar empleados seleccionados de la lista.

## Validaciones y Seguridad

- Todas las consultas a la base de datos están parametrizadas para prevenir inyecciones SQL.
- El sistema incluye validaciones básicas para la entrada de datos, tanto en el lado del cliente como en el servidor.

## Buenas Prácticas

El proyecto sigue las buenas prácticas de programación, incluyendo el uso correcto del patrón MVC, y comentarios en el código para facilitar su comprensión y mantenimiento.
