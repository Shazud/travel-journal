package com.ddp.tj.trip;

import android.graphics.Bitmap;

public class Trip {
    private String name;
    private String destination;
    private Bitmap picture;
    private double price;
    private double rating;
    private boolean favorite;

    public Trip(String name, String destination, Bitmap picture, double price, double rating, boolean favorite) {
        this.name = name;
        this.destination = destination;
        this.picture = picture;
        this.price = price;
        this.rating = rating;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
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
