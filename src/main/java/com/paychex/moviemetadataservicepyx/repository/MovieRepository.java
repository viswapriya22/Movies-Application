package com.paychex.moviemetadataservicepyx.repository;

import com.paychex.moviemetadataservicepyx.domain.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    List<Movie> findByYear(int year);
    List<Movie> findByTitle (String movieTitle);
    List<Movie> findByCastContaining (String castName);
    List<Movie> findByYearBetween (int decadeFrom, int decadeTo);
    List<Movie> findAll ();

}
