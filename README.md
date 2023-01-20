# Movie Metadata Service

This application is a RESTful service that allows users to retrieve information about movies based on various criteria such as year, title, cast members and genre. It also allows users to add new movies to the database. The service uses MongoDB as the database and Spring Boot for the REST API.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
+ Java 11 or later
+ MongoDB
+ Gradle
+ Spring Boot

### Installing
1. Clone the repository to your local machine
     `git clone https://github.com/username/movie-metadata-service.git`
2. Import the project in your preferred IDE
3. Create a .env file in the root directory of the project and store your MongoDB credentials as follows:
   + MONGO_USERNAME=<your_username>
   + MONGO_PASSWORD=<your_password>
4. In the **EnvProperties.class** file, specify the location of your .env file.

### Running the Application
1. Start MongoDB.
2. Run `gradle bootRun` in the root directory of the project.
2. The application will start on `http://localhost:8080/`

## Service Class
The service class, MovieServiceImpl, contains methods for searching movies by year, title, cast members and decade. The class also contains a method for creating a new movie.

- `getMoviesByYear(int year)`: Returns a list of movies that were released in the specified year.
- `getMoviesByTitle(String title)`: Returns a list of movies that match the specified title.
- `getMoviesByCast(String castName)`: Returns a list of movies that have at least one cast member with the specified name.
- `getMoviesByDecade(int decade)`: Returns a list of movies that were released between the specified decade (e.g. between 1980-1989 for a decade of 1980).
- `createMovie(Movie movie)`: Saves the specified movie to the MongoDB database.

## Controller Class
The controller class, MovieController, contains methods that handle HTTP requests and returns HTTP responses. Below are the endpoints used: 

- `GET movie/year/{year}`: Returns a list of movies that were released in the specified year.
- `GET movie/title/{title}`: Returns a list of movies that match the specified title.
- `GET movie/cast/{castName}`: Returns a list of movies that have at least one cast member with the specified name.
- `GET movie/decade/{decade}`: Returns a list of movies that were released between the specified decade.
- `POST /movie`: Saves the movie sent in the request body to the MongoDB database.

## Repository Class
The repository class **MovieRepository.java** extends MongoRepository and provides methods to perform CRUD operations on the movies collection in the MongoDB database.

## Note

This application also provides a feature of capitalizing the first letter of the title of the movie and this can be done by using the WordUtils class.

The application also uses the logging feature to log information about the requests and responses to the API endpoints.

Overall, the Movie Metadata Service is a simple yet powerful tool for retrieving information about movies using a variety of different parameters. Whether you're looking for movies by year, title, cast member, or decade, this service has got you covered.

You can test the endpoints by using any API testing tools such as postman or insomnia.

Hope you enjoy using this application and find it helpful!