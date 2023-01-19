package com.paychex.moviemetadataservicepyx.controller;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Log4j2
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Movie>> findByYear(@PathVariable int year) {
        log.info("Fetching movies by year: {}", year);
        List<Movie> movies = movieService.getMoviesByYear(year);
        if (movies.isEmpty()) {
            log.info("No movies found for year: {}", year);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("Returning movies for year: {}", year);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Movie>> findByTitle(@PathVariable String title) {
        log.info("Fetching movies by title: {}", title);
        List<Movie> movies = movieService.getMoviesByTitle(title);
        if (movies.isEmpty()) {
            log.info("No movies found for title: {}", title);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("Returning movies for title: {}", title);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    @GetMapping("/cast/{castName}")
    public ResponseEntity<List<Movie>> findByCastName(@PathVariable String castName){
        log.info("Fetching movies with cast member: {}", castName);
        List<Movie> movies = movieService.getMoviesByCast(castName);
        if(movies.isEmpty()){
            log.info("No movies found for cast member: {}", castName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("Returning movies for cast member: {}", castName);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }

    @GetMapping("/decade/{decade}")
    public ResponseEntity<List<Movie>> findByDecade(@PathVariable int decade){
        log.info("Fetching movies for decade: {}", decade);
        List<Movie> movies = movieService.getMoviesByDecade(decade);
        if(movies.isEmpty()){
            log.info("No movies found for decade: {}", decade);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("Returning movies for decade: {}", decade);
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }

    }

}
