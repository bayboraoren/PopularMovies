package com.popularmovies.domain;

import android.graphics.Bitmap;

/**
 * Created by baybora on 9/8/15.
 */
public class Trailer {

    private String key;
    private String name;
    private String type;
    private Bitmap thumbnail;

    public Trailer(String name){
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
