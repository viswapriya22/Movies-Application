package com.paychex.moviemetadataservicepyx.controller;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.service.MovieService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import java.util.List;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Test
    void findByYear_success() {
        List<Movie> movies = Arrays.asList(new Movie("shawshank", 2020, Arrays.asList("Lex Barker", "Mamie Van Doren"), "Thriller"),
                new Movie("hello",2020, Arrays.asList("Patty Duke","Victor Jory"), "Family"));
        when(movieService.getMoviesByYear(anyInt())).thenReturn(movies);
        ResponseEntity<List<Movie>> response = movieController.findByYear(2020);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
        }
    @Test
    void findByYear_failure() {
        when(movieService.getMoviesByYear(anyInt())).thenReturn(List.of());
        ResponseEntity<List<Movie>> response = movieController.findByYear(345);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findByTitle_success() {
        List<Movie> movies = Arrays.asList(new Movie("A Race for a Kiss", 1994,Arrays.asList("Katharine Ross","Sue Lyon"), "Action"),
                new Movie("A Race for a Kiss",2013, Arrays.asList("Sidney Poitier", "Telly Savalas"), "Drama"));
        when(movieService.getMoviesByTitle(anyString())).thenReturn(movies);
        ResponseEntity<List<Movie>> response = movieController.findByTitle("A Race for a Kiss");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void findByTitle_failure() {
        when(movieService.getMoviesByTitle(anyString())).thenReturn(List.of());
        ResponseEntity<List<Movie>> response = movieController.findByTitle("Nothing But The Truth");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void findByCastName_success(){
        List<Movie> movies = Arrays.asList(new Movie("Don't Bother To Knock", 1923, Arrays.asList("Dan Dailey","Anthony Quinn"), "Romance"),
                new Movie("Nightfall", 1984, Arrays.asList("Anthony Quinn","Aldo Ray"), "Children"));
        when(movieService.getMoviesByCast(anyString())).thenReturn(movies);
        ResponseEntity<List<Movie>> response = movieController.findByCastName("Anthony Quinn");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void findByCastName_failure(){
        when(movieService.getMoviesByCast(anyString())).thenReturn(List.of());
        ResponseEntity<List<Movie>> response = movieController.findByCastName("Anne Bancroft");
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    void findByDecade_success(){
        List<Movie> movies = Arrays.asList(new Movie("Don't Bother To Knock", 1923, Arrays.asList("Dan Dailey","Anthony Quinn"),"Horror"),
                new Movie("Nightfall", 1924, Arrays.asList("Anthony Quinn","Aldo Ray"),"Crime"));
        when(movieService.getMoviesByDecade(anyInt())).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.findByDecade(1920);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }

    @Test
    void findByDecade_failure(){
        when(movieService.getMoviesByDecade(anyInt())).thenReturn(List.of());

        ResponseEntity<List<Movie>> response = movieController.findByDecade(1900);
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }

    @Test
    void findAllMovies() {
        ObjectId id1 = new ObjectId();
        Movie movie1 = new Movie( "The Matrix", 1999, Arrays.asList("Keanu Reeves", "Laurence Fishburne"), "Sci-Fi");
        movie1.setId(id1);
        ObjectId id2 = new ObjectId();
        Movie movie2 = new Movie("Jurassic park", 1993, Arrays.asList("Sam Neill", "Laura Dern"), "Adventure");
        movie2.setId(id2);
        List<Movie> movies = Arrays.asList(movie1, movie2);
        when(movieService.getAllMoviesAvailable()).thenReturn(movies);

        ResponseEntity<List<Movie>> responseEntity = movieController.findAllMovies();
        List<Movie> actualMovie = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(actualMovie, movies);
    }
    @Test
    public void testCreateMovie() {
        Movie movie = new Movie("The Shawshank Redemption", 1994, Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama");
        Movie expectedMovie = new Movie("The Shawshank Redemption", 1994, Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama");
        ObjectId objectId = new ObjectId();
        expectedMovie.setId(objectId);
        when(movieService.createMovie(movie)).thenReturn(expectedMovie);

        ResponseEntity<Movie> responseEntity = movieController.createMovie(movie);
        Movie actualMovie = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedMovie, actualMovie);
    }

}