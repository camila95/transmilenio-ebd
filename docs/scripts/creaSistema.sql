/*==============================================================*/
/* DBMS name:      TransmiTunja                      */
/* last version:     28-03-2019                     */
/*==============================================================*/
/*CREATE USER TRANSMI IDENTIFIED BY Asdf1234$ DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS ACCOUNT UNLOCK;
GRANT 
CREATE CLUSTER, CREATE SESSION, 
CREATE ANY CONTEXT, CREATE SEQUENCE, 
CREATE JOB, CREATE OPERATOR, 
CREATE TABLE, CREATE DIMENSION, 
CREATE TRIGGER, 
CREATE VIEW, CREATE PUBLIC SYNONYM, 
CREATE PROCEDURE,  CREATE INDEXTYPE, 
CREATE TYPE, CREATE ROLE
--CREATE MATERIALIZAED VIEW, CREATE DIRECTORY,
TO TRANSMI;
*/

/*==============================================================*/
/* SECUENCIAS                                                   */
/*==============================================================*/
CREATE SEQUENCE SEQ_OPE_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_TIES_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_TRON_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_EST_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_VAG_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_RUT_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_RUTEST_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_HOR_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_RUTHOR_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_RUTALIM_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_TIPA_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_PAR_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_PARRUTALIM_ID START WITH 1 NOCACHE ORDER;
CREATE SEQUENCE SEQ_PARHOR_ID START WITH 1 NOCACHE ORDER;


/*==============================================================*/
/* Table: OPERADOR                                              */
/*==============================================================*/
CREATE TABLE OPERADOR
(
   ID_OPERADOR          INTEGER        NOT NULL,
   NOMBRE               VARCHAR2(30)   NOT NULL,
   TELEFONO             VARCHAR2(15)   NOT NULL,
   DIRECCION            VARCHAR2(45)   NOT NULL,
   REPRESENTANTE        VARCHAR2(30)   NOT NULL,
   PAGINA_WEB           VARCHAR2(100)                    
);
COMMENT ON TABLE OPERADOR IS 'TABLA QUE ALMACENA LOS OPERADORES';
COMMENT ON COLUMN OPERADOR.ID_OPERADOR IS
	'Identificacion del operador';
COMMENT ON COLUMN OPERADOR.NOMBRE IS
	'Nombre de la empresa operadora';
COMMENT ON COLUMN OPERADOR.TELEFONO IS
	'Telefono de la empresa operadora';
COMMENT ON COLUMN OPERADOR.DIRECCION IS
	'Direccion de la empresa operadora';
COMMENT ON COLUMN OPERADOR.REPRESENTANTE IS
	'Nombre del representante lega de la empresa operadora';
COMMENT ON COLUMN OPERADOR.PAGINA_WEB IS
	'Url de la paina web de la empresa operadora';	
	
	
/*==============================================================*/
/* Table: TIPO_ESTACION                                         */
/*==============================================================*/
CREATE TABLE TIPO_ESTACION
(
   ID_TIPO_ESTA         INTEGER        NOT NULL,
   NOMBRE               VARCHAR2(30)   NOT NULL
);
COMMENT ON TABLE TIPO_ESTACION IS 'TABLA QUE ALMACENA LOS TIPOS DE ESTACION';
COMMENT ON COLUMN TIPO_ESTACION.ID_TIPO_ESTA IS
	'Identificacion del tipo de estacion';	
COMMENT ON COLUMN TIPO_ESTACION.NOMBRE IS
	'Nombre del tipo de estacion';	

	
/*==============================================================*/
/* Table: TRONCAL                                               */
/*==============================================================*/
CREATE TABLE TRONCAL
(
   ID_TRONCAL           INTEGER        NOT NULL,
   NOMBRE_VIA           VARCHAR2(30)   NOT NULL,
   ZONA                 VARCHAR2(2)    NOT NULL,
   COLOR_ZONA           VARCHAR2(15)   NOT NULL,
   NOMBRE_ZONA          VARCHAR2(30)   NOT NULL,
   ID_OPERADOR          INTEGER        NOT NULL
);
COMMENT ON TABLE TRONCAL IS 'TABLA QUE ALMACENA LAS TRONCALES';
COMMENT ON COLUMN TRONCAL.ID_TRONCAL IS
	'Identificacion de la troncal';
COMMENT ON COLUMN TRONCAL.NOMBRE_VIA IS
	'Nombre de la via por la que circula la troncal';
COMMENT ON COLUMN TRONCAL.ZONA IS
	'Zona de la troncal';
COMMENT ON COLUMN TRONCAL.COLOR_ZONA IS
	'Color de la zona de la troncal';
COMMENT ON COLUMN TRONCAL.NOMBRE_ZONA IS
	'Nombre de la zona de la troncal';
COMMENT ON COLUMN TRONCAL.ID_OPERADOR IS
	'Identificacion del operador en la troncal';

	
/*==============================================================*/
/* Table: ESTACION                                              */
/*==============================================================*/
CREATE TABLE ESTACION
(
   ID_ESTACION          INTEGER              NOT NULL,
   NOMBRE               VARCHAR2(30)         NOT NULL,
   DIRECCION            VARCHAR2(45)         NOT NULL,
   LOCALIDAD            VARCHAR2(30)         NOT NULL,
   LATITUD              VARCHAR2(10)         ,
   LONGITUD             VARCHAR2(10)         ,
   ESTA_INCIAL          NUMBER               NOT NULL,
   ESTA_FINAL           NUMBER               NOT NULL,
   ORDEN                NUMBER               NOT NULL,
   ID_TRONCAL           INTEGER              NOT NULL,
   ID_TIPO_ESTA         INTEGER              NOT NULL
);
COMMENT ON TABLE ESTACION IS 'TABLA QUE ALMACENA LAS ESTACIONES';
COMMENT ON COLUMN ESTACION.ID_ESTACION IS
	'Identificacion de la estacion';
COMMENT ON COLUMN ESTACION.NOMBRE IS
	'Nombre de la estacion';
COMMENT ON COLUMN ESTACION.DIRECCION IS
	'Direccion de la estacion';
COMMENT ON COLUMN ESTACION.LOCALIDAD IS
	'Localidad de la estacion';
COMMENT ON COLUMN ESTACION.LATITUD IS
	'Latitud de la estacion';
COMMENT ON COLUMN ESTACION.LONGITUD IS
	'Longitud de la estacion';
COMMENT ON COLUMN ESTACION.ESTA_INCIAL IS
	'Valor de la estacion incial(1 SI, 0 NO)';
COMMENT ON COLUMN ESTACION.ESTA_FINAL IS
	'Valor de la estacion final(1 SI, 0 NO)';
COMMENT ON COLUMN ESTACION.ORDEN IS
	'Orden de las estaciones en la troncal';
COMMENT ON COLUMN ESTACION.ID_TRONCAL IS
	'Identificacion de la troncal a la que pertenece la estacion';
COMMENT ON COLUMN ESTACION.ID_TIPO_ESTA IS
	'Identificacion del tipo de estacion';

	
/*==============================================================*/
/* Table: VAGON                                                 */
/*==============================================================*/
CREATE TABLE VAGON
(
   ID_VAGON             INTEGER        NOT NULL,
   NOMBRE               VARCHAR2(30)   NOT NULL,
   ID_ESTACION          INTEGER        NOT NULL
);
COMMENT ON TABLE VAGON IS 'TABLA QUE ALMACENA LOS VAGONES';
COMMENT ON COLUMN VAGON.ID_VAGON IS
	'Identificacion del vagon';
COMMENT ON COLUMN VAGON.NOMBRE IS
	'Nombre del vagon';
COMMENT ON COLUMN VAGON.ID_ESTACION IS
	'Identificacion de la estacion';
	

/*==============================================================*/
/* Table: RUTA                                                  */
/*==============================================================*/
CREATE TABLE RUTA
(
   ID_RUTA              INTEGER         NOT NULL,
   NOMBRE               VARCHAR2(30)    NOT NULL,
   SENTIDO              VARCHAR2(2)     NOT NULL
);
COMMENT ON TABLE RUTA IS 'TABLA QUE ALMACENA LAS RUTAS';
COMMENT ON COLUMN RUTA.ID_RUTA IS
	'Identificacion de la ruta';
COMMENT ON COLUMN RUTA.NOMBRE IS
	'Nombre de la ruta';
COMMENT ON COLUMN RUTA.SENTIDO IS
	'Sentido de orientacion de la ruta';
	

/*==============================================================*/
/* Table: RUTA_ESTA                                             */
/*==============================================================*/
CREATE TABLE RUTA_ESTA
(
   ID_RUTA_ESTA         INTEGER       NOT NULL,
   ORDEN                NUMBER        NOT NULL,
   ID_VAGON             INTEGER       NOT NULL,
   ID_RUTA              INTEGER       NOT NULL
);
COMMENT ON TABLE RUTA_ESTA IS 'TABLA QUE ALMACENA LAS RUTAS DE LA ESTACION';
COMMENT ON COLUMN RUTA_ESTA.ID_RUTA_ESTA IS
	'Identificacion de la ruta en las estaciones';
COMMENT ON COLUMN RUTA_ESTA.ORDEN IS
	'Orden de la ruta en cada estacion';
COMMENT ON COLUMN RUTA_ESTA.ID_VAGON IS
	'Identificacion del vagon en la ruta';
COMMENT ON COLUMN RUTA_ESTA.ID_RUTA IS
	'Identificacion de la ruta';


/*==============================================================*/
/* Table: HORARIO                                               */
/*==============================================================*/
CREATE TABLE HORARIO
(
   ID_HORARIO           INTEGER        NOT NULL,
   RANGO                VARCHAR2(1)    NOT NULL,
   HORA_COMIENZO        DATE           NOT NULL,
   HORA_FIN             DATE           NOT NULL
);
COMMENT ON TABLE HORARIO IS 'TABLA QUE ALMACENA LOS HORARIOS';
COMMENT ON COLUMN HORARIO.ID_HORARIO IS
	'Identificacion del horario';
COMMENT ON COLUMN HORARIO.RANGO IS
	'Rango de los dias en los que pasa la ruta';
COMMENT ON COLUMN HORARIO.HORA_COMIENZO IS
	'Hora de incio de la ruta';
COMMENT ON COLUMN HORARIO.HORA_FIN IS
	'Hora final de la ruta';
	

/*==============================================================*/
/* Table: RUTA_HORA                                             */
/*==============================================================*/
CREATE TABLE RUTA_HORA
(
   ID_RUTA_HORA        INTEGER          NOT NULL,
   ID_HORARIO          INTEGER          NOT NULL,
   ID_RUTA             INTEGER          NOT NULL
);
COMMENT ON TABLE RUTA_HORA IS 'TABLA QUE ALMACENA LOS HORARIOS DE LAS RUTAS';
COMMENT ON COLUMN RUTA_HORA.ID_RUTA_HORA IS
	'Identificacion de la ruta en un horario';
COMMENT ON COLUMN RUTA_HORA.ID_HORARIO IS
	'Identificacion del horario';
COMMENT ON COLUMN RUTA_HORA.ID_RUTA IS
	'Identificacion de la ruta';


/*==============================================================*/
/* Table: RUTA_ALIMEN                                           */
/*==============================================================*/
CREATE TABLE RUTA_ALIMEN
(
   ID_RUTA_ALIMEN       INTEGER          NOT NULL,
   CODIGO               VARCHAR2(5)      NOT NULL,
   NOMBRE               VARCHAR2(30)     NOT NULL,
   ID_ESTACION          INTEGER          NOT NULL,
   ID_OPERADOR          INTEGER          NOT NULL
);
COMMENT ON TABLE RUTA_ALIMEN IS 'TABLA QUE ALMACENA LAS RUTAS ALIMENTADORAS';
COMMENT ON COLUMN RUTA_ALIMEN.ID_RUTA_ALIMEN IS
	'Identificacion de la ruta alimentadora';
COMMENT ON COLUMN RUTA_ALIMEN.CODIGO IS
	'Codigo de la ruta alimentadora';
COMMENT ON COLUMN RUTA_ALIMEN.NOMBRE IS
	'Nombre de la ruta alimentadora';
COMMENT ON COLUMN RUTA_ALIMEN.ID_ESTACION IS
	'Identificacion de la estacion en la que salen las rutas alimentadoras';


/*==============================================================*/
/* Table: TIPO_PARADERO                                         */
/*==============================================================*/
CREATE TABLE TIPO_PARADERO
(
   ID_TIPO_PARADERO     INTEGER         NOT NULL,
   NOMBRE               VARCHAR2(30)    NOT NULL,
   FIGURA               VARCHAR2(15)    NOT NULL
);
COMMENT ON TABLE RUTA_ALIMEN IS 'TABLA QUE ALMACENA LOS TIPO DE PARADERO';
COMMENT ON COLUMN TIPO_PARADERO.ID_TIPO_PARADERO IS
	'Identificacion del tipo de paradero';
COMMENT ON COLUMN TIPO_PARADERO.NOMBRE IS
	'Nombre del tipo de paradero';
COMMENT ON COLUMN TIPO_PARADERO.FIGURA IS
	'Figura del tipo de paradero';
	
	
/*==============================================================*/
/* Table: PARADERO                                              */
/*==============================================================*/
CREATE TABLE PARADERO
(
   ID_PARADERO          INTEGER          NOT NULL,
   NOMBRE               VARCHAR2(30)     NOT NULL,
   DIRECCION            VARCHAR2(45)     NOT NULL,
   LATITUD              VARCHAR2(10)     ,
   LONGITUD             VARCHAR2(10)     ,
   ID_TIPO_PARADERO     INTEGER          NOT NULL
);
COMMENT ON TABLE PARADERO IS 'TABLA QUE ALMACENA LOS PARADEROS';
COMMENT ON COLUMN PARADERO.ID_PARADERO IS
	'Identificacion del paradero';
COMMENT ON COLUMN PARADERO.NOMBRE IS
	'Nombre del paradero';
COMMENT ON COLUMN PARADERO.DIRECCION IS
	'Direccion del paradero';
COMMENT ON COLUMN PARADERO.LATITUD IS
	'Latitud del paradero';
COMMENT ON COLUMN PARADERO.LONGITUD IS
	'Longitud del paradero';
COMMENT ON COLUMN PARADERO.ID_TIPO_PARADERO IS
	'Identificacion del tipo de paradero';

/*==============================================================*/
/* Table: PAR_RUT_ALIM                                          */
/*==============================================================*/
CREATE TABLE PAR_RUT_ALIM
(
   ID_PAR_RUT_ALIM      INTEGER          NOT NULL,
   ORDEN                NUMBER           NOT NULL,
   ID_PARADERO          INTEGER          NOT NULL,
   ID_RUT_ALIM          INTEGER          NOT NULL
);

COMMENT ON COLUMN PAR_RUT_ALIM.ID_PAR_RUT_ALIM IS
	'Identificacion de la ruta alimentadora que del paradero';
COMMENT ON COLUMN PAR_RUT_ALIM.ORDEN IS
	'Orden de la ruta alimentadora en el paradero';
COMMENT ON COLUMN PAR_RUT_ALIM.ID_PARADERO IS
	'Identificacion del paradero';
COMMENT ON COLUMN PAR_RUT_ALIM.ID_RUT_ALIM IS
	'Identificacion de la ruta alimentadora';

/*==============================================================*/
/* Table: PARA_HORA                                             */
/*==============================================================*/
CREATE TABLE PARA_HORA
(
   ID_PARA_HORA         INTEGER         NOT NULL,
   ID_PARADERO          INTEGER         NOT NULL,
   ID_HORARIO           INTEGER         NOT NULL
);
COMMENT ON TABLE PARA_HORA IS 'TABLA QUE ALMACENA LOS HORARIOS DE LOS PARADEROS';
COMMENT ON COLUMN PARA_HORA.ID_PARA_HORA IS
	'Identificacion del paradero en un horario';
COMMENT ON COLUMN PARA_HORA.ID_PARADERO IS
	'Identificacion del paradero';
COMMENT ON COLUMN PARA_HORA.ID_HORARIO IS
	'Identificacion del horario';