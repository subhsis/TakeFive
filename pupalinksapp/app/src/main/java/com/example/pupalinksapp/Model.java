package com.example.pupalinksapp;

import android.view.Display;

public class Model {
    String image_url;
    String id;
    String title;
    String adId;

//
//    public Model(){
//
//    }

    public Model(String image,String id,String title,String adId){
        this.adId = adId;
        this.id = id;
        this.image_url=image;
        this.title = title;

    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
