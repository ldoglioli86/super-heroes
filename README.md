# Super Heroes API

This project is an example Rest API developed using Java, Spring Boot, H2 Database, JUnit, Redis and Docker.


### **_How to run..._**

Install Docker
[https://docs.docker.com/engine/install/](http://localhost:8080/swagger-ui)

Clone Git project...

Move to project folder...

`mvn clean install -DskipTests`

`docker-compose build`

`docker-compose up`

### **_Using the API..._**

Open in your browser

[http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui)

Use `/login/authenticate` endpoint to get the JWT token to authenticate the API

`{
"username": "ldoglioli",
"password": "luciano"
}`
 
Are the only valid credentials in the First version of this API.

You will obtain a response similar to:

`{
"jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZG9nbGlvbGkiLCJyb2xlIjp7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9LCJleHAiOjE2NzU0MzkwNTgsImlhdCI6MTY3NTM1MjY1OH0.poxLefF881UgX17WlunWSZzOqf-8bAfQdACGppDZloM"
}`

Then you will copy the generated token, you will click in _Authorize_ button on top of the page and then you will paste the value and click in _Authorize_ 
After that, you will be authorized to use all services documented by `Swagger-UI`.

### **_Publisher endpoints_**

GET
`/publisher`

POST
`/publisher`

GET
`/publisher/{id}`

DELETE
`/publisher/{id}`


### **_Super Heroes endpoints_**

GET
`/super-heroes/{id}`


PUT
`/super-heroes/{id}`


DELETE
`/super-heroes/{id}`


GET
`/super-heroes`


POST
`/super-heroes`


GET
`/super-heroes/search`


### **_API Considerations_**

* API database will be empty when you run the application, so you should add first Publishers, and then Super Heroes. 
After restart the API, Db will be empty again.
* You can create a Super Heroe without Publisher.

### _**Possible improvements**_

* Create Interfaces for service and repository classes.
* Mock Redis connection to run tests without a Redis Server.
* Add more Unit and Integration tests.
* Users CRUD to retrieve user information from DB.
