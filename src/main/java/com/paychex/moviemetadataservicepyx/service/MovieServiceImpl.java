package com.paychex.moviemetadataservicepyx.service;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import com.paychex.moviemetadataservicepyx.repository.MovieRepository;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
                    m.setTitle(WordUtils.capitalize(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.findByTitle(title);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalize(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByCast(String castName) {
        List<Movie> movies = movieRepository.findByCastContaining(castName);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalize(m.getTitle()));
                })
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesByDecade(int decade) {
        List<Movie> movies = movieRepository.findByYearBetween(decade, decade+9);
        return movies.stream()
                .peek(m -> {
                    m.setTitle(WordUtils.capitalize(m.getTitle()));
                })
                .collect(Collectors.toList());
    }
}
