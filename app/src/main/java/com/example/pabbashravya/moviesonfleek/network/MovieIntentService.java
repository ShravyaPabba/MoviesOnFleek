package com.example.pabbashravya.moviesonfleek.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.pabbashravya.moviesonfleek.AppExecutors;

public class MovieIntentService extends IntentService {

    public MovieIntentService(){
        super("MovieIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i("MovieIntentService","Intent service started");
        AppExecutors executors = AppExecutors.getInstance();
        NetworkUtils networkUtils = NetworkUtils.getInstance(this,executors);
        networkUtils.fetchMoviesList();
    }
}
