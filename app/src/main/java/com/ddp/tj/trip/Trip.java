package com.ddp.tj.trip;

import android.graphics.Bitmap;
import android.graphics.Picture;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.ddp.tj.database.CalendarConverter;

import java.util.Calendar;

@Entity(tableName = "Trip")
public class Trip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int tripID;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "destination")
    private String destination;
    @Ignore
    private Bitmap picture;
    @ColumnInfo(name = "price")
    private Double price;
    @ColumnInfo(name = "rating")
    private Double rating;
    @ColumnInfo(name = "favorite")
    private boolean favorite;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "startDate")
    @TypeConverters(CalendarConverter.class)
    private Calendar startDate;
    @ColumnInfo(name = "endDate")
    @TypeConverters(CalendarConverter.class)
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

    public Double getRating() {
        return rating;
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

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public Trip copyTrip() {
        Trip trip = new Trip();
        trip.tripID = tripID;
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
