# REST API using Akka Http and Swagger UI with Docker Swarm (Single node)

Keywords: Akka Http, Swagger UI, Docker Swarm

* This repository contains a template for a Data Science SDK application defining a REST API and generating swagger ui with akka http in Ubuntu environment.
* You can read more about Swagger UI from [here](https://swagger.io/),  Akka HTTP from [here](https://doc.akka.io/docs/akka-http/current/) and Docker Swarm from [here](https://docs.docker.com/engine/swarm/).
* All the books are downloaded from [here](http://www.openculture.com/free_textbooks)

### Run the template
  - start the brownbag application
  ```
  ./start_app.sh
  ```
  - stop the application
  ```
  ./stop_app.sh
  ```
### Browse Swagger API Page 
  - Hit the URI: [Swagger Doc](http://brownbag:9001/swagger-ui/index.html)
  - Default port is 9001. Edit application.conf to change the port.
  - Using the Swagger Chrome Plugin, use this link: http://brownbag:9001/api-docs/swagger.json

