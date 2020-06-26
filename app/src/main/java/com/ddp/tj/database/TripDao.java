package com.ddp.tj.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ddp.tj.trip.Trip;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TripDao {
    @Query("SELECT * FROM Trip")
    List<Trip> getAllTrips();

    @Insert
    void insertAll(Trip... trips);

    @Insert
    void insertTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Update
    void updateAll(Trip... trips);

    @Delete
    void deleteTrip(Trip trip);

    @Delete
    void deleteAll(Trip... trips);

}
