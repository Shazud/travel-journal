package com.ddp.tj.trip;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class Trip {
    private String title;
    private String destination;
    private Bitmap picture;
    private Double price;
    private Double rating;
    private boolean favorite;
    private String type;
    private Calendar startDate;
    private Calendar endDate;

    public Trip(){

    }

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

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Trip copyTrip() {
        Trip trip = new Trip();
        trip.title = title.concat("");
        trip.price = price + 0;
        trip.favorite = false;
        trip.startDate = (Calendar)startDate.clone();
        trip.endDate = (Calendar)endDate.clone();
        trip.picture = picture.copy(picture.getConfig(), picture.isMutable());
        trip.destination = destination.concat("");
        trip.type = type.concat("");
        trip.rating = rating + 0;
        return trip;
    }
}
