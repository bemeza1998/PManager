/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/12/2022 12:56:02                          */
/*==============================================================*/


drop table if exists ACT_EMPRESA;

drop table if exists ACT_ERROR_QA;

drop index IDX_JEFATURA_SIGLAS on ACT_JEFATURA;

drop table if exists ACT_JEFATURA;

drop table if exists ACT_OBSERVACION;

drop table if exists ACT_PRODUCTO;

drop table if exists ACT_PROYECTO;

drop table if exists ACT_REGISTRO_MODIFICACION;

drop table if exists SEG_PERFIL;

drop table if exists SEG_REGISTRO_SESION;

drop index IDX_USUARIO_MAIL on SEG_USUARIO;

drop table if exists SEG_USUARIO;

/*==============================================================*/
/* Table: ACT_EMPRESA                                           */
/*==============================================================*/
create table ACT_EMPRESA
(
   COD_EMPRESA          int not null auto_increment,
   NOMBRE_EMPRESA       varchar(128) not null,
   DIRECCION            varchar(512),
   TELEFONO             varchar(16),
   MAIL                 varchar(64) not null,
   CLIENTE_ACTIVO       varchar(1) not null default 'S' comment 'Indica si al empresa es un cliente activo, con el fin de no mostrar todas las empresa al momente de crear un proyecto.',
   FECHA_INGRESO        datetime not null,
   primary key (COD_EMPRESA)
);

alter table ACT_EMPRESA comment 'Tabla para almacenar las empresas relacionadas a un proyecto';

/*==============================================================*/
/* Table: ACT_ERROR_QA                                          */
/*==============================================================*/
create table ACT_ERROR_QA
(
   COD_ERROR_QA         int not null auto_increment,
   COD_PRODUCTO         int,
   COD_USUARIO          varchar(64),
   ERROR_REPORTADO      varchar(512) not null,
   ESTADO_ERROR         varchar(3) not null,
   FECHA_ERROR          datetime not null,
   primary key (COD_ERROR_QA)
);

alter table ACT_ERROR_QA comment 'Tabla para registrar los errores reportados por QA';

/*==============================================================*/
/* Table: ACT_JEFATURA                                          */
/*==============================================================*/
create table ACT_JEFATURA
(
   COD_JEFATURA         int not null auto_increment,
   SIGLAS               varchar(4) not null,
   NOMBRE               varchar(32) not null,
   primary key (COD_JEFATURA)
);

alter table ACT_JEFATURA comment 'Almacena las jefaturas de la empresa.';

/*==============================================================*/
/* Index: IDX_JEFATURA_SIGLAS                                   */
/*==============================================================*/
create unique index IDX_JEFATURA_SIGLAS on ACT_JEFATURA
(
   SIGLAS
);

/*==============================================================*/
/* Table: ACT_OBSERVACION                                       */
/*==============================================================*/
create table ACT_OBSERVACION
(
   COD_OBSERVACION      int not null auto_increment,
   COD_PRODUCTO         int,
   COD_USUARIO          varchar(64),
   TEXTO                varchar(512) not null,
   FECHA_CREACION       datetime not null,
   primary key (COD_OBSERVACION)
);

alter table ACT_OBSERVACION comment 'Tabla para almacenar las observaciones sobre un producto.';

/*==============================================================*/
/* Table: ACT_PRODUCTO                                          */
/*==============================================================*/
create table ACT_PRODUCTO
(
   COD_PRODUCTO         int not null auto_increment,
   COD_USUARIO          varchar(64) not null,
   COD_JEFATURA         int,
   COD_PROYECTO         int,
   NOMBRE               varchar(256) not null,
   MES                  int not null,
   SEMANA               date not null,
   FECHA_ESTIMADA_ENTREGA date,
   HORAS_ESTIMADAS      numeric(2,0),
   FECHA_REAL_ENTREGA   datetime,
   PORCENTAJE_CUMPLIMIENTO numeric(5,2),
   CRONOGRAMA           smallint not null default 0,
   ENTREGADO_QA         numeric(1,0) default 0,
   QA_ERRORES_REPORTADOS numeric(2,0) default 0,
   QA_ERROES_CORREGIDOS numeric(2,0) default 0,
   QA_OBSERVACIONES     varchar(516),
   QA_ESTADO            varchar(3),
   FECHA_CREACION       datetime not null,
   ESTADO_SOLICITUD_MODIFICACION varchar(3) default 'NOS',
   FECHA_SOLICITUD_MODIFICACION datetime,
   COMENTARIO_SOLICITUD_MODIFICACION varchar(512),
   NOMBRE_USUARIO_COMPLETO varchar(64) not null,
   APLAZADO             varchar(2) default 'NO',
   primary key (COD_PRODUCTO, COD_USUARIO)
);

alter table ACT_PRODUCTO comment 'Almacena los productos creados por el personal de la empresa';

/*==============================================================*/
/* Table: ACT_PROYECTO                                          */
/*==============================================================*/
create table ACT_PROYECTO
(
   COD_PROYECTO         int not null auto_increment,
   COD_USUARIO          varchar(64),
   COD_JEFATURA         int,
   COD_EMPRESA          int,
   IDENTIFICADOR_PROYECTO varchar(5) not null,
   NOMBRE               varchar(64) not null,
   DESCRIPCION          varchar(128) not null,
   ESTADO               varchar(3) not null comment 'Estados del proyecto
            ACT Activo
            INC Inactivo',
   VALOR_ENTREGA        numeric(15,2) not null default 0,
   DIAS_CONTRATO        int not null default 0,
   VALOR_DIA            numeric(10,2) default 0 comment 'Formula: (VALOR_ENTREGA / VALOR_DIA)',
   VALOR_HORA           numeric(6,2) default 0 comment 'Formula: (VALOR_DIA / 8)',
   FECHA_CREACION       datetime not null,
   FECHA_INICIO         date,
   FECHA_FINALIZACION   date,
   ESTADO_SOLICITUD_MODIFICACION varchar(3),
   FECHA_SOLICITUD_MODIFICACION datetime,
   COMENTARIO_SOLICITUD_MODIFICACION varchar(512),
   NOMBRE_USUARIO_COMPLETO varchar(64) not null,
   primary key (COD_PROYECTO)
);

alter table ACT_PROYECTO comment 'Almacena los proyectos de la empresa.';

/*==============================================================*/
/* Table: ACT_REGISTRO_MODIFICACION                             */
/*==============================================================*/
create table ACT_REGISTRO_MODIFICACION
(
   COD_REG_MODIFICACION int not null auto_increment,
   COD_REGISTRO_MODIFICADO int not null,
   COD_USUARIO          varchar(64) not null,
   TIPO_TABLA           varchar(32) not null comment 'Corresponde a a la tabla ACT_PRODUCTO o ACT_PROYECTO',
   FECHA_MODIFICACION   datetime not null,
   COMENTARIO           varchar(512),
   primary key (COD_REG_MODIFICACION)
);

alter table ACT_REGISTRO_MODIFICACION comment 'Almacena las modificaciones realizadas en los productos y pr';

/*==============================================================*/
/* Table: SEG_PERFIL                                            */
/*==============================================================*/
create table SEG_PERFIL
(
   COD_PERFIL           varchar(3) not null,
   NOMBRE               varchar(32) not null,
   DESCRIPCION          varchar(128) not null,
   ESTADO               varchar(3) not null comment 'Estado de vigencia del perfil. 
            ACT Activo
            INA Inactivo',
   primary key (COD_PERFIL)
);

alter table SEG_PERFIL comment 'Almacena los diferentes perfiles de acceso que pueden ser as';

/*==============================================================*/
/* Table: SEG_REGISTRO_SESION                                   */
/*==============================================================*/
create table SEG_REGISTRO_SESION
(
   COD_REGISTRO_SESION  int not null auto_increment,
   COD_USUARIO          varchar(64) not null,
   FECHA_CONEXION       datetime not null,
   IP_CONEXION          varchar(30),
   RESULTADO            varchar(3) not null comment 'Resultado del intento de inicio de sesión.
            SAT Satisfactorio
            FAL Fallido',
   primary key (COD_REGISTRO_SESION)
);

alter table SEG_REGISTRO_SESION comment 'Almacena los intentos de inicio de sesión de los usuarios en';

/*==============================================================*/
/* Table: SEG_USUARIO                                           */
/*==============================================================*/
create table SEG_USUARIO
(
   COD_USUARIO          varchar(64) not null,
   COD_JEFATURA         int not null,
   COD_PERFIL           varchar(3),
   NOMBRE               varchar(32) not null,
   APELLIDO             varchar(32) not null,
   MAIL                 varchar(64) not null,
   CLAVE                varchar(128) not null,
   ESTADO               varchar(3) not null comment 'Estado del usuario en el sistema.  
            ACT Activo, estado válido para que u usuario pueda iniciar sessión.
            INA Inactivo, Similar a eliminado; es un estado final.
            BLO Bloqueado, cuando el número de intentos fallidos ha superado el límite establecido.',
   FECHA_CREACION       datetime not null,
   FECHA_CAMBIO_CLAVE   datetime,
   FECHA_ULTIMA_SESION  datetime,
   primary key (COD_USUARIO, COD_JEFATURA)
);

alter table SEG_USUARIO comment 'Almacena la información de los usuarios del sistema.';

/*==============================================================*/
/* Index: IDX_USUARIO_MAIL                                      */
/*==============================================================*/
create unique index IDX_USUARIO_MAIL on SEG_USUARIO
(
   MAIL
);

alter table ACT_ERROR_QA add constraint FK_FK_ERROR_QA_A_PRODUCTO foreign key (COD_PRODUCTO, COD_USUARIO)
      references ACT_PRODUCTO (COD_PRODUCTO, COD_USUARIO) on delete restrict on update restrict;

alter table ACT_OBSERVACION add constraint FK_FK_OBSERVACION_A_PRODUCTO foreign key (COD_PRODUCTO, COD_USUARIO)
      references ACT_PRODUCTO (COD_PRODUCTO, COD_USUARIO) on delete restrict on update restrict;

alter table ACT_PRODUCTO add constraint FK_PRODUCTO_A_PROYECTO foreign key (COD_PROYECTO)
      references ACT_PROYECTO (COD_PROYECTO) on delete restrict on update restrict;

alter table ACT_PRODUCTO add constraint FK_PRODUCTO_A_USUARIO foreign key (COD_USUARIO, COD_JEFATURA)
      references SEG_USUARIO (COD_USUARIO, COD_JEFATURA) on delete restrict on update restrict;

alter table ACT_PROYECTO add constraint FK_FK_PROYECTO_A_EMPRESA foreign key (COD_EMPRESA)
      references ACT_EMPRESA (COD_EMPRESA) on delete restrict on update restrict;

alter table ACT_PROYECTO add constraint FK_PROYECTO_A_USUARIO foreign key (COD_USUARIO, COD_JEFATURA)
      references SEG_USUARIO (COD_USUARIO, COD_JEFATURA) on delete restrict on update restrict;

alter table SEG_USUARIO add constraint FK_USUARIO_A_JEFATURA foreign key (COD_JEFATURA)
      references ACT_JEFATURA (COD_JEFATURA) on delete restrict on update restrict;

alter table SEG_USUARIO add constraint FK_USUARIO_A_PERFIL foreign key (COD_PERFIL)
      references SEG_PERFIL (COD_PERFIL) on delete restrict on update restrict;

