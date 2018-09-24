**The Poro-MeetLink Project v0.7.3**

- Backend : *SpringBoot v2.0.3*

- Frontend : *Angular 6*

- RDMS : *PostgreSQL 10*

- To allow Hibernate & Liquibase to access database you need to change login/password in *application.yml* and *liquibase.properties*

- A Tomcat 8.5 server is needed to deploy project however an embbeded Tomcat can also be employed changing configuration in POM.xml and Application.java 

- Compile project without frontend for developpement (faster)

```
mvn clean package -Dskip.npm
```

- Compile project with frontend for production

```
mvn clean package
```

- serve angular on port 4200 (SpringBoot project need to be started)

```
cd pml
ng serve
```

3rd year group project for IMIE Rennes (FR) school. 
Other contributors : Julien Niord, Juliette Saint-Jalme & Carl-Eric Celoy