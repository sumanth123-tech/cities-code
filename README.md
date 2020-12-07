# Software Engineer Code Challenge with Spring Boot

### Tech Stack
Technologies used for current task

* [Java 1.8]
* [Spring Boot, Spring Boot Web,embedded Tomcat Server]
* [Maven]
* [Swagger, Log 4j]

### How to run the application
Import the project into any IDE( Eclipse, IntelliJ etc)
Run the main class  CitiesApplication as Java Application 
Note: Can also run the project as a Spring Boot App if STS is installed on your system.
### Swagger to view the documentation and test api
Once the application is up, you can find the detailed documentation and test apis at 
*http://localhost:8080/swagger-ui.html 
# Rest Apis Info
The following things illustrate Requests and Response:
## A GET API  which accepts the origin and destination cities as request params
### Case :1 When a direct route exists  
Request url: http://localhost:8080/connected?origin=Boston&destination=New York
Response : yes
### Case :2 When destination reached through series of routes 
Request url: http://localhost:8080/connected?origin=Boston&destination=newark
Response : yes
###  Case :3 When no route exists 
Request url: http://localhost:8080/connected?origin=Trenton&destination=Philadelphia
Response : yes
### Case : 4 Invalid Input
Request url: http://localhost:8080/connected?origin=123&destination=Philadelphia
Response: no
