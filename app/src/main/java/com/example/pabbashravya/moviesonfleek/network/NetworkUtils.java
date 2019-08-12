package com.example.pabbashravya.moviesonfleek.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.pabbashravya.moviesonfleek.AppExecutors;
import com.example.pabbashravya.moviesonfleek.R;
import com.example.pabbashravya.moviesonfleek.database.MovieBrief;
import com.example.pabbashravya.moviesonfleek.database.MovieDetail;
import com.example.pabbashravya.moviesonfleek.database.MovieEntity;
import com.example.pabbashravya.moviesonfleek.database.MoviesListResponse;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUtils {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static NetworkUtils sInstance;
    private final Context mContext;
    private final AppExecutors mExecutors;
    private final List<MovieEntity> mMovieEntities = new ArrayList<>();
    private final MutableLiveData<List<MovieEntity>> mDownloadedMovies;

    private static final int SYNC_INTERVAL_HOURS = 24;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS / 24;

    private NetworkUtils(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        mDownloadedMovies = new MutableLiveData<List<MovieEntity>>();
    }

    public LiveData<List<MovieEntity>> getDownloadedMovies() {
        return mDownloadedMovies;
    }


    public static NetworkUtils getInstance(Context context, AppExecutors executors) {

        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkUtils(context.getApplicationContext(), executors);
            }
        }
        return sInstance;

    }

    public void fetchMoviesList() {

        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.i("NetworkUtils", "fetchMoviesList");
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<MoviesListResponse> moviesCall = apiService
                        .getPopularMovies(mContext.getResources().getString(R.string.API_KEY));
                moviesCall.enqueue(new Callback<MoviesListResponse>() {
                    @Override
                    public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                        if (response.body() == null) return;
                        if (response.body().getResults() == null) return;

                        for (MovieBrief movieBrief : response.body().getResults()) {
                            if (movieBrief != null && movieBrief.getPosterPath() != null) {
                                mMovieEntities.add(new MovieEntity(movieBrief.getId(), movieBrief.getPosterPath(), movieBrief.getTitle(),
                                        null, null, null));
                            }
                        }
                        Log.i("NetworkUtils", "Added list of muvis from network");
                        Log.i("NetworkUtils", "setting live data");
                        mDownloadedMovies.postValue(mMovieEntities);
                    }

                    @Override
                    public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                        call.cancel();
                    }
                });

            }

        });

    }

    public void fetchMovieDetails(final Integer movieId) {

        mExecutors.networkIO().execute(new Runnable() {
            @Override
            public void run() {
                APIInterface apiService = APIClient.getClient().create(APIInterface.class);
                Call<MovieDetail> movieDetailCall = apiService
                        .getMovieDetails(movieId, mContext.getResources().getString(R.string.API_KEY));
                movieDetailCall.enqueue(new Callback<MovieDetail>() {
                    @Override
                    public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                        if (response.body() == null) return;
                        MovieDetail movieDetail = response.body();
                        mMovieEntities
                                .add(new MovieEntity(movieDetail.getId(), movieDetail.getPosterPath(), movieDetail.getTitle()
                                        , movieDetail.getOverview(), movieDetail.getReleaseDate(), movieDetail.getRuntime()));
                        Log.i("NetworkUtils","setting live details");
                        mDownloadedMovies.postValue(mMovieEntities);

                    }

                    @Override
                    public void onFailure(Call<MovieDetail> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });

    }


    public void startFetchWeatherService() {
        Intent intentToFetch = new Intent(mContext, MovieIntentService.class);
        mContext.startService(intentToFetch);
        Log.d("NetworkUtils", "Service created");
    }

    public void scheduleRecurringMoviesSyncService(){
        Log.i("NetworkUtils","scheduleservice method");
        Driver driver = new GooglePlayDriver(mContext);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job syncMoviesJob = dispatcher.newJobBuilder()
                .setService(MovieFirebaseJobService.class)
                .setTag("movies_sync")
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(SYNC_INTERVAL_SECONDS,SYNC_INTERVAL_SECONDS+SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(syncMoviesJob);

    }
}
