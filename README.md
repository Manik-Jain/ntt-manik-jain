# ntt-manik-jain
NTTData Coding assessment for Cricket website https://www.espncricinfo.com/

As per the requirements, this application :

1. is highly scalable and comes with inbuilt support for service registery with Eureka Client
2. provides observability with Zipkin and micrometer
3. has good test coverage
4. has default actuator support enabled for application monitoring and management
5. uses H2 database for CRUD operations
6. has Swagger Doc integration
7. ensures input validation and sanitation
8. relies on Controller Advice for centralized error handling
9. utilizes established design patterns with Java8 and above
10. has good logging capabilities with meaningful error messages

# Steps to run application
1. git clone from master
2. execute maven build on the clone repository
3. Run the main application
4. For H2 credentials, use admin
5. Upon application boot, the DB will be created with proper relationships
6. Load Swagger UI from : http://localhost:8080/swagger-ui/index.html
7. Execute CRUD operations for PlayerController
8. For country codes, this version supports only a few countries which can be checked from within enums. Make sure to provide correct country codes or you'll see BadRequest error
