package com.paychex.moviemetadataservicepyx.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection ="movies")
public class Movie {
    @Id
    @JsonView
    private ObjectId id;
    private String title;
    private int year;
    private List<String> cast;
    private String genre;
    public Movie(String title, int year, List<String> cast, String genre) {
        this.title=title;
        this.year=year;
        this.cast = cast;
        this.genre = genre;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



    public List<String> getCast() {
        return cast;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
