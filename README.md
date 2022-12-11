# PManager - backend

El proyecto fue realizado con Java 11 y el framework SpringBoot versión 2.7.5

## Estructura de directorios 

Para trabajar de una forma mas eficiente, se utilizó una arquitecuta de N-capas, en donde la primera de estas, es la capa de modelo, luego tenemos la capa DAO, seguida de la capa de servicios y por último, la capa de recursos, acontinuación, se detallas más de cada una de estas.
Se dividio en proyecto en dos módulos principales los cuales son actividades y seguridad.

## Módulos

### Capa model

Se tienen las clases correspondientes a las tablas de la base de datos, siendo las siguientes para el modulo de ACTIVIDADES:
  - Empresa: clase que representa a la entidad ACT_EMPRESA de la BD.
  - ErrorQa: clase que representa a la entidad ACT_ERROR_QA de la BD.
  - Jefatura: clase que representa a la entidad ACT_JEFATURA de la BD.
  - Observacion: clase que representa a la entidad ACT_OBSERVACION de la BD.
  - Producto: clase que representa a la entidad ACT_PRODUCTO de la BD.  
  - ProductoPK: clase para representar la clave primaria de un Producto, ya que esta es compuesta.
  - Proyecto: clase que representa a la entidad ACT_PROYECTO de la BD.  
  - RegistroModificacion: clase que representa a la entidad ACT_REGISTRO_MODIFICACION de la BD.  
  
 Para el módulo de SEGURIDAD son las siguientes:
  - Perfil: clase que representa a la entidad SEG_PERFIL de la BD.
  - RegistroSesion: clase que representa a la entidad SEG_REGISTRO_SESION de la BD.
  - Usuario: clase que representa a la entidad SEG_USUARIO de la BD.
  - UsuarioPK: clase para representar la clave primaria de un Usuario, ya que esta es compuesta.
  
### Capa dao
 
Capa en donde se crear las interfaces para interactuar con la base de datos, para cada modelo, debe haber una interfaz que permita acceder a la base de datos, a excepción de los siguientes:
  - ProductoCustomRepository: interfaz definida para realizar consultar personalizadas a la base de datos, se definen solamente los métodos.
  - ProductoCustomRepositoryImpl: clase en donde se implenta al interfaz ProductoCustomRepository y se estrucutura la consulta personalizada a la BD.
  
### Capa services
   
Capa en donde se establece la lógica necesaria a realizar para ejecutar un proceso, por ejemplo, para crear un producto, es necesario establecer todos sus atributos como la fecha de creación, estado QA, etc.
   
### Capa resources

Capa en donde se exponen los servicios implementados como API REST para que puedan ser consumidos por los usuarios, es importante señalar que si se desea que un rol acceda a un servicios REST, es necesario lo siguiente:
- @PreAuthorize("hasAnyRole('ADM','ALP')"): es necesario colocar el rol en la anotación para que este pueda acceder, caso contrario se lanza un error 403 (No autorizado), en este caso, al endpoint puede acceder el ADM (ADMINISTRADOR) y el ALP (ANALISTA DE PROYECTOS).

## Directorios extras

### Directorio dto

Representan los objetos que van a ser enviados por medio del API REST, representan a las clases de modelo establecidas solo que sin todos los atributos, existen atributos que el usuario no debe conocer.

### Directorio enums

Continen enumeraciones sobre los estados de ciertas entidades, por ejemplo un enum puede represtar los estados de un Usuario (ACT: Activo, INA: Inactivo)

### Directorio mapper

Contiene las clases necesarias para construtir un dto a partir de un modelo o viceversa haciendo uso del patron de diseño Builder.

### Directorio utils

Contiene clases útiles para ciertas funciones, como por ejemplo, una clase para enivar el JWT al frontend.

## Directorio config

Contiene clases necesarias para que el proyecto pueda funcionar correctamente:
  - CorsConfiguration: configuración del tipo de solicitudes aceptadas al API REST.
  - EmailConfig: configuración del servicio de email para enivar correos al crear usuarios.
  - JwtAuthenticationFilter: configuración para la validación del JWT.
  - JwtProvider: configuración para la creación del JWT.
  - SecurityAdapter: configuración para validar que se acceda al API solamente autenticado y con JWT.
  
## Directorio batch TODO

Directorio para implementar el proceso batch para que los productos que no han sido completados en su semana, se aplacen a la siguiente.

## aplication.properties

Se establecen los valores necesarios para conectar a la BD, se recomienda usar secrets, tambien se establecen los valores para enviar un correo y el tiempo de vida de un JWT.
  
  
  
  
