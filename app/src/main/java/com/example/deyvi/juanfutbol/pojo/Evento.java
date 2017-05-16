package com.example.deyvi.juanfutbol.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by deyvi on 26/04/2017.
 */

public class Evento implements Serializable {
    public String name;
    public String category;
    public String type;
    public String video;
    public long dateUnix;
    public String image;
    public String place;
    public String description;
    public Double lat;
    public Double lon;

    public Evento(String name, String category, String type, String video, long dateUnix, String image, String place, String description, Double lat, Double lon) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.video = video;
        this.dateUnix = dateUnix;
        this.image = image;
        this.place = place;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public long getDateUnix() {
        return dateUnix;
    }

    public void setDateUnix(long dateUnix) {
        this.dateUnix = dateUnix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
