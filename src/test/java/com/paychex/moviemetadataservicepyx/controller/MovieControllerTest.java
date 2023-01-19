package com.paychex.moviemetadataservicepyx.controller;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.service.MovieService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
        List<Movie> movies = Arrays.asList(new Movie("shawshank", 2020, Arrays.asList("Lex Barker", "Mamie Van Doren")),
                new Movie("hello",2020, Arrays.asList("Patty Duke","Victor Jory")));
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
        List<Movie> movies = Arrays.asList(new Movie("A Race for a Kiss", 1994,Arrays.asList("Katharine Ross","Sue Lyon")),
                new Movie("A Race for a Kiss",2013, Arrays.asList("Sidney Poitier", "Telly Savalas")));
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
        List<Movie> movies = Arrays.asList(new Movie("Don't Bother To Knock", 1923, Arrays.asList("Dan Dailey","Anthony Quinn")),
                new Movie("Nightfall", 1984, Arrays.asList("Anthony Quinn","Aldo Ray")));
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
        List<Movie> movies = Arrays.asList(new Movie("Don't Bother To Knock", 1923, Arrays.asList("Dan Dailey","Anthony Quinn")),
                new Movie("Nightfall", 1924, Arrays.asList("Anthony Quinn","Aldo Ray")));
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
}