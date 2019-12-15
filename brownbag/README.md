# Change me 

Template with Akka, DataScience SDK and Swagger UI #

* This repository contains a template for a Spark application defining a REST API and generating swagger ui with akka http.
* You can read more about Swagger UI from [here](https://swagger.io/) and Akka HTTP from [here](https://doc.akka.io/docs/akka-http/current/). 

### Build Code
* Compile and Test
```
sbt clean compile test
```
* Assembly to a single jar 
```
sbt clean assembly
```
### Build Docker Image
```
cd Docker
cp ../target/scala-2.11/brownbag-assembly-0.1.jar app.jar
docker build -t my-swagger-akka-app:0.0.1 .
```
**You can change the tag to use different name and version.**

### Run

* From SBT Console
```
sbt run
```
* From Docker
```
  docker run -p 9001:9001 my-swagger-akka-app:0.0.1
```
**Use your image name and version name used during docker build.**

* From Command Line
```
java -cp target/scala-2.11/swagger-ui-akka-http-assembly-1.0.jar Boot
```
*  From IDE
  In the IDE, select object Boot, right click and then select Run 'Boot'.

### Browse Swagger API Page (Local) 
  - Replace host name for remote host
  - Hit the URI: [Swagger Doc](http://localhost:9001/swagger-ui/index.html)
  - Default port is 9001. Edit application.conf to change the port.
  - Using the Swagger Chrome Plugin, use this link: http://localhost:9001/api-docs/swagger.json

### Change Default Port
Edit resources/application.conf