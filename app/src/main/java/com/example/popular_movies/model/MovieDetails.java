package com.example.popular_movies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieDetails implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("releaseDate")
    @Expose
    private String releasedDate;

    @SerializedName("title")
    @Expose
    private Title title;

    public MovieDetails(String id, String releasedDate, Title title) {
        this.id = id;
        this.releasedDate = releasedDate;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
