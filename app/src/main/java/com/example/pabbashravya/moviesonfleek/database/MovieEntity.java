package com.example.pabbashravya.moviesonfleek.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieEntity {

    @PrimaryKey
    private int id;
    private String posterPath;
    private String title;
    private String overview;
    private String releaseDate;
    private Integer runtime;

    public MovieEntity(Integer id, String posterPath, String title, String overview, String releaseDate, Integer runtime) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
    }

    public Integer getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getRuntime() {
        return runtime;
    }
}
