# Transmilenio
Proyecto del sistema de transmilenio.

## Requisitos
* Java 8.0_201
* Gradle 5.3.1
* Apache Maven 3.6.0
* Oracle DB 12.1.0.2

## Instalación
### Compilar el "ojdbc8" en el repositorio local Maven
La razón para usar el repositorio local de maven es que el controlador jdbc de Oracle no es de acceso público. Tendremos que descargar el controlador de Oracle e instalarlo en nuestro repositorio local.
```bash
$ mvn install:install-file -Dfile=libs/ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
```

### Oracle DB 12.1.0.2 con Docker
1. Clonar repositorio **docker-images** de Oracle, crear imagen y correr el contenedor.
```bash
$ git clone https://github.com/oracle/docker-images.git
```
2. Descargar los binarios desde: https://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html (Parte 1 y 2).
3. Copiar ambos archivos a: docker-images/OracleDatabase/SingleInstance/dockerfiles/12.1.0.2/
4. Construir imagen Docker.
```bash
$ cd docker-images/OracleDatabase/SingleInstance/dockerfiles
$ ./buildDockerImage.sh -v 12.1.0.2 -s -i
```
5. Ejecutar contenedor
```bash
$ docker volume create oracledb-vol
$ docker run -d --name oracledb \
    -p 1521:1521 -p 5500:5500 \
    -e ORACLE_SID=orcl \
    -e ORACLE_PDB=pdb1 \
    -e ORACLE_PWD=Oracle123 \
    -e ORACLE_CHARACTERSET=AL32UTF8 \
    -e ORACLE_ALLOW_REMOTE=true \
    -v oracledb-vol:/opt/oracle/oradata \
    oracle/database:12.1.0.2-se2
```
>Para utilizar sqlplus dentro del contenedor usar el comando:
```bash
$ docker exec -it oracledb sqlplus system/Oracle123@pdb1
```
6. Crear usuario.
```sql
SQL> CREATE USER transmilenio IDENTIFIED BY Oracle123;
SQL> GRANT CONNECT TO transmilenio;
SQL> GRANT CONNECT, RESOURCE, DBA TO transmilenio;
SQL> GRANT UNLIMITED TABLESPACE TO transmilenio;
```
## Referencias
* https://www.dennis-stepp.com/post/flywaygradle/
* https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/
* https://www.oracle.com/technetwork/es/articles/datawarehouse/oracle12c-docker-win10-4485487-esa.html
* https://github.com/oracle/docker-images/blob/master/OracleDatabase/SingleInstance/README.md