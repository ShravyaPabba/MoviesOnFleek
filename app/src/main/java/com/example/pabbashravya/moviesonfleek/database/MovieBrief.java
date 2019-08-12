package com.example.pabbashravya.moviesonfleek.database;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;



public class MovieBrief {

    @Ignore
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("id")
    private Integer id;
    @Ignore
    @SerializedName("video")
    private Boolean video;
    @Ignore
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("title")
    private String title;
    @Ignore
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;
    @Ignore
    @SerializedName("original_title")
    private String originalTitle;
    @Ignore
    @SerializedName("backdrop_path")
    private String backdropPath;
    @Ignore
    @SerializedName("adult")
    private Boolean adult;
    @Ignore
    @SerializedName("overview")
    private String overview;
    @Ignore
    @SerializedName("release_date")
    private String releaseDate;

    @Ignore
    public MovieBrief(Integer voteCount, Integer id, Boolean video, Double voteAverage,
                      String title, Double popularity, String posterPath, String originalLanguage,
                      String originalTitle, String backdropPath,
                      Boolean adult, String overview, String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.video = video;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public MovieBrief(Integer id,String title,String posterPath){
        this.id=id;
        this.title=title;
        this.posterPath=posterPath;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
