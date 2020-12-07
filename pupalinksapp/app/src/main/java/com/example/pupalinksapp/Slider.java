package com.example.pupalinksapp;

public class Slider {
    private  String image;
    private  String title;
    String id;

    public Slider(String image, String title,String id) {
        this.image = image;
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
