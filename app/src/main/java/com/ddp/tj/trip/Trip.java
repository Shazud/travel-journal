package com.ddp.tj.trip;

import android.graphics.Bitmap;

public class Trip {
    private String title;
    private String destination;
    private Bitmap picture;
    private Double price;
    private Double rating;
    private boolean favorite;

    public Trip(String name, String destination, Bitmap picture, double price, double rating, boolean favorite) {
        this.title = name;
        this.destination = destination;
        this.picture = picture;
        this.price = price;
        this.rating = rating;
        this.favorite = favorite;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
