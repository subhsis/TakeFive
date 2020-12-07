package com.example.pupalinksapp;

import android.support.annotation.NonNull;

public class AudioModel {
    private String image_url;
    String id;
    private String title;
    private String format;

   public AudioModel(String image_url, String id, String title, String format){
        this.image_url = image_url;
        this.id = id;
        this.title = title;
        this.format = format;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


}
class SortView implements Comparable<SortView>{

    private Float val;
    private String id;

    public SortView(Float val, String id){
        this.val = val;
        this.id = id;
    }



    @Override
    public int compareTo(@NonNull SortView o) {
        if (val > o.val) {
            return 1;
        }
        else if (val < o.val) {
            return -1;
        }
        else {
            return 0;
        }

    }
    @Override
    public String toString(){
        return this.id;
    }
}
