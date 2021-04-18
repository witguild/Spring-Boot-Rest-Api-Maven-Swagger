#Spring-Boot-Rest-Api-Maven-Swagger 

#Prime Number Service

#Calculate and return a collection of all prime numbers up to a specified upper limit in number

- Start your service as an simple java application to build service 'mvn clean install'. 
  Use prime-number-service-1.0.0.jar file in /target folder or,
  docker file and jar is available at /docker folder.
 

- Start service by executing jar command
  
  java -jar prime-number-service-1.0.0.jar

- Start service by docker go to docker folder and run below commands:

  docker build --pull -t prime-number-service-1.0.0 .  

  docker run -p 8080:8080 prime-number-service-1.0.0


#OpenAPI for API Documentation
  
- Api docs json  
  http://localhost:8080/api/v1/prime/api-docs

- You can view the api documentation at:
 
  http://localhost:8080/api/v1/prime/swagger-ui.html

- Change default port value in application.properties

- Api is accessible at 

  GET: http://localhost:8080/api/v1/prime/list/11

