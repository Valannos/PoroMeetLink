**The Poro-MeetLink Project v0.7.3**

- Backend : *SpringBoot v2.0.3*

- Frontend : *Angular 6*

- RDMS : *MySQL 5.7*

- To allow Hibernate & Liquibase to access database you need to change login/password in *application.yml* and *liquibase.properties*

- Compile project without frontend for developpement (faster)

```
mvn clean package -Dskip.npm
```

- Compile project with frontend for production

```
mvn clean package
```

- serve angular on port 4200 (SpringBoot project need to be started either from IDE or .jar)

```
cd pml
ng serve
```

3rd year group project for IMIE Rennes (FR) school. 
Other contributors : Julien Niord, Juliette Saint-Jalme & Carl-Eric Celoy