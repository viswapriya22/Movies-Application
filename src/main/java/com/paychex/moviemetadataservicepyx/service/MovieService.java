package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import java.util.List;

public interface MovieService {

        List<Movie> getMoviesByTitle(String title);
        List<Movie> getMoviesByYear(int year);
        List<Movie> getMoviesByCast(String castName);
        List<Movie> getMoviesByDecade(int decadeFrom);
        List<Movie> getAllMoviesAvailable();
        Movie createMovie(Movie movie);
}
