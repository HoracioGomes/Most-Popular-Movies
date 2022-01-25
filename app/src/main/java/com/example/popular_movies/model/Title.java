package com.example.popular_movies.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Title implements Serializable {
    @SerializedName("image")
    @Expose
    private Image image;

    @SerializedName("title")
    @Expose
    private String title;

    public Title(Image image, String title) {
        this.image = image;
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
