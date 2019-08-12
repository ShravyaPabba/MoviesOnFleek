package com.example.pabbashravya.moviesonfleek.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.paging.DataSource;

import java.util.List;



@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<MovieEntity> moviesList);

    @Query("SELECT id,overview,releaseDate,runtime FROM movies WHERE id = :id")
    LiveData<MovieDetail> getMovieById(Integer id);

    @Query("SELECT id,title,posterPath FROM movies")
    DataSource.Factory<Integer,MovieBrief> getMoviesList();
}
