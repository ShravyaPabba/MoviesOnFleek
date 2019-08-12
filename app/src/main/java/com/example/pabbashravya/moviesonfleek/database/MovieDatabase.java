package com.example.pabbashravya.moviesonfleek.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MovieEntity.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieDatabase sInstance;

    public static MovieDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class,"movie").build();
            }
        }
        return sInstance;
    }


    public abstract MovieDao movieDao();
}
