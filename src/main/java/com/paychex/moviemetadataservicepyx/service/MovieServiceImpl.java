package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.paychex.moviemetadataservicepyx.utility.WordUtils;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMoviesByYear(int year) {
        List<Movie>movies = movieRepository.findByYear(year);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalizeTitle(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalizeTitle(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByCast(String castName) {
        List<Movie> movies = movieRepository.findByCastContaining(castName);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalizeTitle(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByDecade(int decade) {
        List<Movie> movies = movieRepository.findByYearBetween(decade, decade+9);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalizeTitle(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMoviesAvailable() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalizeTitle(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public Movie createMovie(Movie movie) {
        movie.setTitle(WordUtils.capitalizeTitle(movie.getTitle()));
        return movieRepository.save(movie);
    }
}
