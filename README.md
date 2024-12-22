# Technical Details
1. Java 23
2. Spring Boot 3.4.1
3. Maven

# Initial setup
* Clone service-shuffle repository(https://github.com/KorshunovMykyta/service-shuffle.git)
* Clone service-log repository(https://github.com/KorshunovMykyta/service-log.git)
* Run ServiceShuffleApplication from 'service-shuffle' project - Service will start at port 8081(to change the port update 'server.port' property in application.properties file)
* Run ServiceLogApplication from 'service-log' project - Service will start at port 8083(to change the port update 'server.port' property in application.properties file)

---

* To ensure that everything works properly install Postman(https://www.postman.com/downloads/)
* Run Postman and create new HTTP request
* Setup post request - localhost:8081/api/shuffle
* Set body:
```
{
   "number":11
}
```
* Click send button
* Verify Response status is 200 and Response body is not empty

![](/Users/nikitakorshunov/Desktop/Screenshot 2024-12-22 at 20.02.30.png)


---
### Also project has unit and integration tests which are located in corresponding directories under the test package