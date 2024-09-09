# ntt-manik-jain
NTTData Coding assessment for Cricket website https://www.espncricinfo.com/

API specifications:
1. # Fetch all matches
   - fetch match with Live score
   - current state : OnGoing, Delayed, Finished, Cancelled, etc.
   - Top scorers and wicket takers so far
   - Team run-rate, required run-rate
  
2. # Fetch per match record
  - Which players are currently batting and their live scores
  - which bowler(s) is/are performing well and their per over analysis

3. # Get Team rankings
  - Based on match type: ODI, T20, Test match; how is each team performing 
  - geography wise team performance

4. # Player Rankings
  - Based on match type: ODI, T20, Test match; who are the top performers across these match types
     
5. # Create new series
  - When a new series starts, we need to maintain record for this new series. For these, we need to Create new series catering to information like: 
  Which teams are participating, which players are in playing 15 and playing 11, the venue, and dates

6. # push live match updates
  In order to have live and real time updates, we will have to have a fault tolerant and quick to respond system that 
  a) feeds real time match updates to the consumers -> We can use Kafka with load balancers to achieve this
  b) maintains fault tolerancy so that any failures are quick to resolve -> Kafka Dead Letter Queue and Retry Mechanisms can be used 
  c) scales under heavy load -> working with service discovery apps like Eureka client, we can scale when load goes over and beyond

7. # Update records
  The system shall allow:
  - Update player records
  - update team records
  - update real time match records

8. # Delete Records
    The system shall allow for removing unwanted
  - player records
  - match records

# This app deals with use case #4.
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
