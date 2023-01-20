package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void getMoviesByYear_success() {
        List<Movie> movies = Arrays.asList(new Movie("test movie", 2020, Arrays.asList("Patty Duke","Victor Jory"),"Horror"),
                new Movie("another test", 2020, Arrays.asList("Lex Barker", "Mamie Van Doren"),"Thriller"));
        when(movieRepository.findByYear(anyInt())).thenReturn(movies);

        List<Movie> result = movieService.getMoviesByYear(2020);

        assertEquals(2, result.size());
        assertEquals("Test Movie", result.get(0).getTitle());
        assertEquals("Another Test", result.get(1).getTitle());
    }

    @Test
    void getMoviesByYear_failure() {
        when(movieRepository.findByYear(anyInt())).thenReturn(List.of());
        List<Movie> result = movieService.getMoviesByYear(2020);
        assertTrue(result.isEmpty());
    }

    @Test
    void getMoviesByTitle_success() {
        List<Movie> movies = Arrays.asList(new Movie("The Avengers", 2020, Arrays.asList("Katharine Ross","Sue Lyon"),"Fantasy"));
        when(movieRepository.findByTitle(anyString())).thenReturn(movies);

        List<Movie> result = movieService.getMoviesByTitle("The Avengers");

        assertEquals(1, result.size());
        assertEquals("The Avengers", result.get(0).getTitle());
    }
    @Test
    void getMoviesByTitle_failure(){
        when(movieRepository.findByTitle(anyString())).thenReturn(List.of());
        List<Movie>result = movieService.getMoviesByTitle("Hello");
        assertTrue(result.isEmpty());
    }

    @Test
    void getMoviesByCast_success(){
        List<Movie> movies = Arrays.asList(new Movie("Wedding Bride", 2020, Arrays.asList("Patty Duke","Victor Jory"),"Sci-Fi"),
                new Movie("Superman", 2020, Arrays.asList("Lex Barker", "Patty Duke"),"Drama"));
        when(movieRepository.findByCastContaining(anyString())).thenReturn(movies);

        List<Movie> result = movieService.getMoviesByCast("Patty Duke");

        assertEquals(2, result.size());
        assertEquals(result,movies);
    }

    @Test
    void getMoviesByCast_failure(){
        when(movieRepository.findByCastContaining(anyString())).thenReturn(List.of());
        List<Movie>result = movieService.getMoviesByCast("Andrew Garfield");
        assertTrue(result.isEmpty());
    }

    @Test
    void getMoviesByDecade_success() {
        List<Movie> movies = Arrays.asList(new Movie("test movie", 2020, Arrays.asList("Patty Duke","Victor Jory"),"Romance"),
                new Movie("another test", 2022, Arrays.asList("Lex Barker", "Mamie Van Doren"),"Regional"));
        when(movieRepository.findByYearBetween(anyInt(),anyInt())).thenReturn(movies);

        List<Movie> result = movieService.getMoviesByDecade(2020);

        assertEquals(2, result.size());
        assertEquals(result,movies);
    }

    @Test
    void getMoviesByDecade_failure() {
        when(movieRepository.findByYearBetween(anyInt(),anyInt())).thenReturn(List.of());
        List<Movie> result = movieService.getMoviesByDecade(1901);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllMoviesAvailable(){
        ObjectId id1 = new ObjectId();
        Movie movie1 = new Movie( "The Matrix", 1999, Arrays.asList("Keanu Reeves", "Laurence Fishburne"), "Sci-Fi");
        movie1.setId(id1);
        ObjectId id2 = new ObjectId();
        Movie movie2 = new Movie("Jurassic park", 1993, Arrays.asList("Sam Neill", "Laura Dern"), "Adventure");
        movie2.setId(id2);
        List<Movie> movies = Arrays.asList(movie1, movie2);
        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMoviesAvailable();

        assertEquals(2,result.size());
        assertEquals(result,movies);
    }

    @Test
    void createMovieTest(){
        Movie movie = new Movie("the shawshank redemption", 1994, Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama");
        Movie expectedMovie = new Movie("The Shawshank Redemption", 1994, Arrays.asList("Tim Robbins", "Morgan Freeman"), "Drama");
        ObjectId objectId = new ObjectId();
        expectedMovie.setId(objectId);
        when(movieRepository.save(movie)).thenReturn(expectedMovie);
        Movie result = movieService.createMovie(movie);
        assertEquals(result,expectedMovie);
    }

}
