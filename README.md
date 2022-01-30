# movie-metadata-service
`Clone this respository, do not fork`

### Description
A misguided venture to restore VideoKing Superstore to its former glory is launching soon. They need to build a web-service on the backend to support searching and retrieving data relating to the movies it has available in its catalogue.

### Requirements
* Service responds to HTTP GET operations to retrieve movie metadata based on search parameters
  * Returns a collection of movie metadata
  * Supports getting movie metadata by
    * Title
    * Year
    * \* Decade (Optional)
    * \* Cast member (Optional)
  * Use java streams processing to force movie titles to Title Case in the response
  * Unit test the class performing this transformation
  

  ` * Bonus points for supporting this feature`

### Constraints
*	Using Spring Boot, Log4j2, Jackson, MongoDB, OpenFeign, Gradle, JDK 11 (specifically)
*	Create unit tests using Junit 5
*	Showcase clean coding skillset (SOLID principles, etc)

### Additional Bonus Tasks
* Create a sequence diagram of the flow from HTTP Request to HTTP Response
* Add support for adding movies using an HTTP POST

### Steps to Run
1. Clone this repository, do not fork
2. Modify application.yaml with the provided MongoDB credentials
3. Execute `gradle bootRun`
