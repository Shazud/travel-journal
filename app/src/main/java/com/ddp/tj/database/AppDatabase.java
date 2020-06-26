package com.ddp.tj.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.ddp.tj.trip.Trip;

@Database(entities = {Trip.class}, version=1)
@TypeConverters({CalendarConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract TripDao tripDao();

}
