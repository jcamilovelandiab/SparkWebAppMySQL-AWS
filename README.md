## Prerrequisites

If you want to download and run the source code, it is necessary to have installed java 1.8 and Apache Maven 3.6.0  on the computer where the program will run.

## Heroku

The web application is deployed in heroku. To visit the website go to the following link. [SparkWebApp](https://sparkwebapplab.herokuapp.com/inputdata "Heroku Page")

## Running the project

To download the project dependencies the following line must be executed.
```
mvn clean compile assembly:single
```

To execute the project
```
mvn exec:java -Dexec.mainClass="edu.escuelaing.arem.services.SparkWebApp"
```

To open the website locally go to
```
http://localhost:4567/inputdata
```

Execute this line to see the java documentation.
```
mvn javadoc:jar
```

## Author

Juan Camilo Velandia Botello - Escuela Colombiana de Ingeniería Julio Garavito, Colombia.

## License

This project is under the Apache license - see [LICENSE](LICENSE.md) for more details.
