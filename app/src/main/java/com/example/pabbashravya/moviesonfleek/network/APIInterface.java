package com.example.pabbashravya.moviesonfleek.network;


import com.example.pabbashravya.moviesonfleek.database.MovieDetail;
import com.example.pabbashravya.moviesonfleek.database.MoviesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("movie/popular")
    Call<MoviesListResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieDetail> getMovieDetails(@Path("id") Integer movieId, @Query("api_key") String apiKey);
}
