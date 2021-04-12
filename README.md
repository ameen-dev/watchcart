# Watchcart

Watchcart is a Java application which exposes a rest endpoint to add watches to the cart and calculate the total price.

## Rest endpoints

Following is the endpoint exposed. It takes a list which has the IDs of watches as the parameter. Ex: ["001","002"]

```
localhost:8080/checkout
```

## Watch catalogue pre-load

The file "data.sql" that is present in the resources folder is used to pre-load the watch catalogue data into h2 database.
The database could be viewed by using the following URL.

```
http://localhost:8080/h2-console
```
Use "jdbc:h2:mem:admin" as JDBC URL, "admin" as username and "admin" as password when prompted. These are configured in "application.properties" file in resources folder.

## Unit test cases
The unit test cases could be found in "WatchcartServiceTests" file and it covers happy path, corner cases and exceptions.

## Integration test cases
The integration test cases could be found in "WatchcartApplicationTests" file and it covers happy path, corner cases and exceptions.

## Build command
Install maven and run "mvn clean install" which will build the project, run the test cases and generate a JAR file in the target folder. This project is mvn build tested.

## How did I approach the problem

```
1) I needed a pre populated data to work upon. So I used spring and 'data.sql' to do it.
2) Once I had the data, I then statred project setup like controller, service, repository, bean, etc.
3) After this was done I exposed a root controller to check if the annotations were working.
4) Then I exposed the "checkout" API and tested it.
5) Now comes the logic that goes into "checkout" API. I used JPA Repository object to fetch the whole object and performed manipulations on it to compute the final price.
6) Then I implemented exception handling in the controller.
7) After this, I tested the application through postman by giving various inputs.
8) Then I wrote 2 test files - one for unit testing and the other for integration testing and passed the various parameters I used in postman.
9) Finally, I made sure that my project is buildable and packagable by Maven.
10) Then, I committed the project into my github and updated readme file.
```
