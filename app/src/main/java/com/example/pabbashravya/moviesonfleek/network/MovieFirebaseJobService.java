package com.example.pabbashravya.moviesonfleek.network;

import android.util.Log;

import com.example.pabbashravya.moviesonfleek.AppExecutors;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MovieFirebaseJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {

        AppExecutors executors = AppExecutors.getInstance();
        NetworkUtils networkInstance = NetworkUtils.getInstance(this.getApplicationContext(), executors);
        Log.i(getClass().getSimpleName(),"Firebase job started");
        networkInstance.fetchMoviesList();
        jobFinished(job,false);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
