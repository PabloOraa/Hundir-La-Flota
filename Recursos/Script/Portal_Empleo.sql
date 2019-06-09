/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     15/05/2019 09:57:42                          */
/*==============================================================*/


alter table CURRICULUM
   drop constraint FK_CURRICUL_TIENE_PERSONA;

alter table EMPRESA
   drop constraint FK_EMPRESA_INHERITAN_USUARIO;

alter table OFERTA
   drop constraint FK_OFERTA_CREAN_EMPRESA;

alter table PERSONA
   drop constraint FK_PERSONA_INHERITAN_USUARIO;

alter table SE_INSCRIBEN
   drop constraint FK_SE_INSCR_SE_INSCRI_PERSONA;

alter table SE_INSCRIBEN
   drop constraint FK_SE_INSCR_SE_INSCRI_OFERTA;

drop index TIENE_FK;

drop table CURRICULUM cascade constraints;

drop index INHERITANCE_2_FK;

drop table EMPRESA cascade constraints;

drop index CREAN_FK;

drop table OFERTA cascade constraints;

drop index INHERITANCE_1_FK;

drop table PERSONA cascade constraints;

drop index SE_INSCRIBEN2_FK;

drop index SE_INSCRIBEN_FK;

drop table SE_INSCRIBEN cascade constraints;

drop table USUARIO cascade constraints;

/*==============================================================*/
/* Table: CURRICULUM                                            */
/*==============================================================*/
create table CURRICULUM 
(
   ID_CURRICULUM        CHAR(9)              not null,
   DNI                  VARCHAR2(9)          not null,
   FECHA_DE_ACTUALIZACION DATE,
   EXPERIENCIA_LABORAL  CLOB,
   FORMACION            CLOB,
   IDIOMAS              CLOB,
   GUSTOS_Y_AFICIONES   CLOB,
   OTROS_DATOS          CLOB,
   constraint PK_CURRICULUM primary key (ID_CURRICULUM)
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create index TIENE_FK on CURRICULUM 
(
   DNI ASC
);

/*==============================================================*/
/* Table: EMPRESA                                               */
/*==============================================================*/
create table EMPRESA 
(
   CIF                  INTEGER              not null,
   NOMBRE_USUARIO       VARCHAR2(20)         not null,
   INFORMACION_ADICIONAL CLOB,
   PERSONA_DE_CONTACTO  VARCHAR2(50),
   URL                  CLOB,
   constraint PK_EMPRESA primary key (CIF)
);

/*==============================================================*/
/* Index: INHERITANCE_2_FK                                      */
/*==============================================================*/
create index INHERITANCE_2_FK on EMPRESA (
   NOMBRE_USUARIO ASC
);

/*==============================================================*/
/* Table: OFERTA                                                */
/*==============================================================*/
create table OFERTA 
(
   ID_OFERTA            INTEGER              not null,
   CIF                  INTEGER              not null,
   PUESTO               VARCHAR2(50)         not null,
   DESCRIPCION          CLOB,
   NUMERO_VACANTES      INTEGER,
   UBICACION            VARCHAR2(40),
   FECHA_CREACION       DATE,
   REQUISITOS           CLOB,
   SALARIO              NUMBER(7,2),
   JORNADA              VARCHAR2(10),
   VACACIONES           INTEGER,
   NUMERO_INSCRITOS     INTEGER,
   constraint PK_OFERTA primary key (ID_OFERTA)
);

/*==============================================================*/
/* Index: CREAN_FK                                              */
/*==============================================================*/
create index CREAN_FK on OFERTA (
   CIF ASC
);

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA 
(
   DNI                  VARCHAR2(9)          not null,
   NOMBRE_USUARIO       VARCHAR2(20)         not null,
   PRIMER_APELLIDO      VARCHAR2(20),
   SEGUNDO_APELLIDO     VARCHAR2(20),
   FECHA_NACIMIENTO     DATE,
   NACIONALIDAD         VARCHAR2(15),
   PERMISO_DE_CONDUCIR  SMALLINT, /*0 no 1 si*/
   constraint PK_PERSONA primary key (DNI)
);

/*==============================================================*/
/* Index: INHERITANCE_1_FK                                      */
/*==============================================================*/
create index INHERITANCE_1_FK on PERSONA (
   NOMBRE_USUARIO ASC
);

/*==============================================================*/
/* Table: SE_INSCRIBEN                                          */
/*==============================================================*/
create table SE_INSCRIBEN 
(
   DNI                  VARCHAR2(9)          not null,
   ID_OFERTA            INTEGER              not null,
   ID_CURRICULUM        CHAR(9),
   FECHA                DATE,
   constraint PK_SE_INSCRIBEN primary key (DNI, ID_OFERTA)
);

/*==============================================================*/
/* Index: SE_INSCRIBEN_FK                                       */
/*==============================================================*/
create index SE_INSCRIBEN_FK on SE_INSCRIBEN (
   DNI ASC
);

/*==============================================================*/
/* Index: SE_INSCRIBEN2_FK                                      */
/*==============================================================*/
create index SE_INSCRIBEN2_FK on SE_INSCRIBEN (
   ID_OFERTA ASC
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO 
(
   NOMBRE_USUARIO       VARCHAR2(20)         not null,
   NOMBRE               VARCHAR2(50),
   CONTRASENA           VARCHAR2(54)         not null
      constraint CKC_CONTRASENA_USUARIO check (length(CONTRASENA) >= 8),
   DIRECCION            VARCHAR2(40),
   CIUDAD               VARCHAR2(40),
   TELEFONO             CHAR(9),
   CORREO_ELECTRONICO   VARCHAR2(50),
   IMAGEN               CLOB,
   constraint PK_USUARIO primary key (NOMBRE_USUARIO)
);

alter table CURRICULUM
   add constraint FK_CURRICUL_TIENE_PERSONA foreign key (DNI)
      references PERSONA (DNI)  ON DELETE CASCADE;

alter table EMPRESA
   add constraint FK_EMPRESA_INHERITAN_USUARIO foreign key (NOMBRE_USUARIO)
      references USUARIO (NOMBRE_USUARIO) ON DELETE CASCADE;

alter table OFERTA
   add constraint FK_OFERTA_CREAN_EMPRESA foreign key (CIF)
      references EMPRESA (CIF)  ON DELETE CASCADE;

alter table PERSONA
   add constraint FK_PERSONA_INHERITAN_USUARIO foreign key (NOMBRE_USUARIO)
      references USUARIO (NOMBRE_USUARIO)  ON DELETE CASCADE;

alter table SE_INSCRIBEN
   add constraint FK_SE_INSCR_SE_INSCRI_PERSONA foreign key (DNI)
      references PERSONA (DNI)  ON DELETE CASCADE;

alter table SE_INSCRIBEN
   add constraint FK_SE_INSCR_SE_INSCRI_OFERTA foreign key (ID_OFERTA)
      references OFERTA (ID_OFERTA)  ON DELETE CASCADE;

alter table SE_INSCRIBEN
   add constraint FK_SE_INSCRI_CURRICULUM foreign key (ID_CURRICULUM)
      references CURRICULUM (ID_CURRICULUM)  ON DELETE CASCADE;


/*==============================================================*/
/* INSERCI?N DE DATOS                                           */
/*==============================================================*/
/*USUARIOS*/
INSERT INTO USUARIO (NOMBRE_USUARIO,NOMBRE,CONTRASENA,DIRECCION,CIUDAD,TELEFONO,CORREO_ELECTRONICO)
VALUES('Solinfo', 'Soluciones Informáticas S.A.', '1197359662', 'Glorieta Embajadores 9', 'Madrid', 663541259, 'empresa@solucionesinformaticas.es'); /*Empresa*/

INSERT INTO USUARIO
VALUES ('JuanPe87', 'Juan', '1671701740', 'Gamazo 37 1 Derecha', 'Valladolid', 654125875, 'JuanPerez@gmail.es',null); /*Persona*/

INSERT INTO USUARIO
VALUES('B12', 'B12', '1101961262', 'Pio del Rio Hortega 8, 1º Izquierda', 'Valladolid', 983574865, 'informacion@telestant.com',null); /*Empresa*/

INSERT INTO USUARIO
VALUES ('PedRa', 'Pedro', '1648981048', 'Calle Vive 13 2ºA', 'Valencia', 725641534, 'PedroRF@hotmail.com',null); /*Persona*/

INSERT INTO USUARIO
VALUES ('GearSolid', 'Gear Solid', '-476110061', 'General Primo de Rivera 2 I', 'Valladolid', 983552415, 'empresa@gearsolid.com',null); /*Empresa*/

INSERT INTO USUARIO
VALUES ('Rolo', 'Rosa', '-1566497751', 'Calle del Real Madrid 15 2 1', 'Madrid', 652123252, 'RosaLo2001@gmail.com',null); /*Persona*/

INSERT INTO USUARIO
VALUES ('FrutasPaqui', 'Fruteria Paquita', '-256559752', 'Calle de Gabilondo 8', 'Valladolid', 666512348, 'frutasPaValladolid@hotmail.es',null);/*Empresa*/

INSERT INTO USUARIO
VALUES ('LauraGoPe', 'Laura', '-57079649', 'Calle Elvira 14', 'Granada', 645152465, 'LauraGoPe@yahoo.es',null);/*Persona*/

INSERT INTO USUARIO
VALUES ('TurboLimpieza', 'Turbo Limpieza', '-1020227877', 'Calle de Pelayo 15 2A', 'Valencia', 633548798, 'Limpiezasinfin@gmail.es',null);/*Empresa*/

INSERT INTO USUARIO
VALUES ('Albetito45', 'Alberto', '1197601007', 'Calle Veracruz 45 2I', 'Granada', 634158795, 'Albertito@hotmail.es',null); /*Persona*/

/*PERSONAS*/
INSERT INTO PERSONA
VALUES('71178445T','JuanPe87','Perez', 'Guerrero', '15/02/1995', 'Española', 1);

INSERT INTO PERSONA
VALUES('09254875F','Rolo', 'Lopez', 'Hermoso', '20/05/1965', 'Colombiana',0);

INSERT INTO PERSONA
VALUES('32548751N','LauraGoPe','Gomez', 'Perez', '25/01/2001', 'Francesa',1);

INSERT INTO PERSONA
VALUES('21154789B','Albetito45','Dominguez', 'Gonzalez', '30/03/1980', 'Española',0);

INSERT INTO PERSONA
VALUES('71179288Z','PedRa', 'Ramirez', 'De Pablos', '24/07/1992', 'Española',0);

/*CURRICULUM*/
INSERT INTO CURRICULUM
VALUES ('1','71178445T','30/04/2014',null,null,'Español, Inglés, Francés', null, null);

INSERT INTO CURRICULUM
VALUES('2','71178445T','12/05/2019','2 años de atención al cliente en Orange',null, 'Español, Inglés, Francés, Checo, Italiano', null, null);

INSERT INTO CURRICULUM
VALUES('3','21154789B','01/01/2018','Programador senior en Java con experiencia demostrable de más de 5 años','Ingeniera informática','Español, Chino', null, null);

INSERT INTO CURRICULUM
VALUES('4','32548751N','25/01/2019',null,'Bachillerato', 'Español, Francés', null, null);

INSERT INTO CURRICULUM
VALUES('5','32548751N','20/04/2019',null,'Bachillerato','Español, Inglés, Francés', null, null);

INSERT INTO CURRICULUM
VALUES('6','09254875F','05/06/2001','Limpiadora del hogar durante más de 15 años.',null, 'Español', null, null);

INSERT INTO CURRICULUM
VALUES('7','09254875F','24/02/2003','Limpiadora del hogar durante más de 15 años. Desde 1990 como teleoperador en Vodafone.',null,'Español', null, null);

/*EMPRESA*/
INSERT INTO EMPRESA
VALUES(115487,'Solinfo', 'Empresa lider informática en el desarrollo de aplicaciones para empresas de automoción.', 'Laura Perez', null);

INSERT INTO EMPRESA
VALUES(857455,'B12', 'Empresa de telecomunicaciones en televenta para grandes compañías externas como Yoigo.', 'Luis Gomez', null);

INSERT INTO EMPRESA
VALUES(654484,'GearSolid', 'Desarrolladora creada por universitarios para la creación de diferentes experiencias móviles', 'Pedro Javier', null);

INSERT INTO EMPRESA
VALUES(325264,'FrutasPaqui', 'Fruteria de Valladolid centrada en el buen trato al cliente y la mejor calida posible.', 'Juan Perez', null);

INSERT INTO EMPRESA
VALUES(784554,'TurboLimpieza', 'Empresa lider en la limpieza rápida y eficiente de cualquier local.', 'Maria Eugenia', null);

/*OFERTAS*/
INSERT INTO OFERTA
VALUES(1,857455, 'Teleoperador captación frio','Se busca teleoperador con experiencia en captación de clientes con llamadas en frio para una empresa nacional de gran importancia.', 3, 'Valladolid', SYSDATE, 'Un año de experiencia en el mundo de televentas. Se valora idiomas', 1200.00, 'Completa', 30, 25);

INSERT INTO OFERTA
VALUES(2,784554, 'Secretario','Se busca secretario para la gestión de documentos y llamadas de clientes a la empresa.',1, 'Valencia', '10/05/2019', 'Experiencia en el trato con el cliente. Inglés certificado como idioma mínimo adicional al espa?ol', 1400, 'Completa',30, 105);

INSERT INTO OFERTA
VALUES(3,784554, 'Limpiador','Se busca limpiador de tarde para los locales requeridos.', 5, 'Madrid', '03/05/2019', 'Conocimiento de las técnicas eficaces de limpieza para una mayor eficiencia.', 1100.00, 'Parcial', 31, 15);

INSERT INTO OFERTA
VALUES(4,654484, 'Programador Junior','Se busca programador junior en Java, Kotlin y Swift para el desarrollo de nuevas experiencias móviles.', 2, 'Valladolid', SYSDATE, 'Un año de experiencia en programación móvil. Titulo certificado de Ingl?s', 1500, 'Completa', 30, 23);

INSERT INTO OFERTA
VALUES(5,115487, 'Programador Senior','Se busca programador senior con varios años de experiencia en .NET y las bases de datos de SQL Server con gesti?n en Azure.', 4, 'Madrid', SYSDATE, 'Ninguno', 2500, 'Completa', 30, 38);

INSERT INTO OFERTA
VALUES(6,115487, 'Administrativo','Se busca adminsitrativo para la gestión de documentos oficiales de la compañía.', 1, 'Madrid', '15/02/2019', 'Valorable idiomas. Requiere la carrera de economicas.', 1350, 'Completa', 30, 4);

INSERT INTO OFERTA
VALUES(7,857455, 'Teleoperador','Se busca teleoperador para un nuevo proyecto "crosslink" de una empresa de gran importancia a nivel nacional.', 6, 'Valladolid', '13/05/2019', 'Ninguna', 1200, 'Completa', 30, 76);

INSERT INTO OFERTA
VALUES(8,325264, 'Dependiente de tienda','Se busca dependiente de la nueva tienda que se abrirá en Parquesol para entrar a trabajar el próximo 15 de junio.', 2, 'Valladolid', '30/04/2019', 'Valorable experiencia en otras tiendas.', 1600, 'Completa', 32, 56);

INSERT INTO OFERTA
VALUES(9,784554, 'Limpiador','Se busca limpiador para las oficinas centrales de Valencia.', 1, 'Valencia', '10/04/2019', 'Ninguna', 1400, 'Completa', 30, 4);

/*SE INSCRIBEN*/
INSERT INTO SE_INSCRIBEN
VALUES('71178445T',1, 2,SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('71178445T',7, 2,SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('71178445T',8, 2,SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('21154789B',4, 3, SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('21154789B',5, 3, SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('32548751N',7,5,'13/05/2019');

INSERT INTO SE_INSCRIBEN
VALUES('09254875F',3,6,'20/02/2019');

INSERT INTO SE_INSCRIBEN
VALUES('09254875F',1,7, SYSDATE);

INSERT INTO SE_INSCRIBEN
VALUES('71179288Z',7,null,'02/05/2019');

INSERT INTO SE_INSCRIBEN
VALUES('71179288Z',9,null,'15/04/2019');