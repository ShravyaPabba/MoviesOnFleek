package com.example.pabbashravya.moviesonfleek.database;

import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class MovieDetail {

    @Ignore
    @SerializedName("adult")
    private Boolean adult;
    @Ignore
    @SerializedName("backdrop_path")
    private String backdropPath;
    @Ignore
    @SerializedName("budget")
    private Integer budget;
    @Ignore
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private Integer id;
    @Ignore
    @SerializedName("imdb_id")
    private String imdbId;
    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;
    @Ignore
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @Ignore
    @SerializedName("popularity")
    private Double popularity;
    @Ignore
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @Ignore
    @SerializedName("revenue")
    private Integer revenue;
    @SerializedName("runtime")
    private Integer runtime;
    @Ignore
    @SerializedName("status")
    private String status;
    @Ignore
    @SerializedName("tagline")
    private String tagline;
    @Ignore
    @SerializedName("title")
    private String title;
    @Ignore
    @SerializedName("video")
    private Boolean video;
    @Ignore
    @SerializedName("vote_average")
    private Double voteAverage;
    @Ignore
    @SerializedName("vote_count")
    private Integer voteCount;

    @Ignore
    public MovieDetail(Boolean adult, String backdropPath, Integer budget, String homepage, Integer id, String imdbId, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String releaseDate, Integer revenue, Integer runtime, String status, String tagline, String title, Boolean video, Double voteAverage, Integer voteCount) {
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.budget = budget;

        this.homepage = homepage;
        this.id = id;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public MovieDetail(Integer id,String overview,String releaseDate,Integer runtime){
        this.id=id;
        this.overview=overview;
        this.releaseDate=releaseDate;
        this.runtime=runtime;
    }
    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }


    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
