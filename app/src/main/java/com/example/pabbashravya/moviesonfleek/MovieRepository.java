package com.example.pabbashravya.moviesonfleek;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.pabbashravya.moviesonfleek.database.MovieBrief;
import com.example.pabbashravya.moviesonfleek.database.MovieDao;
import com.example.pabbashravya.moviesonfleek.database.MovieDetail;
import com.example.pabbashravya.moviesonfleek.database.MovieEntity;
import com.example.pabbashravya.moviesonfleek.network.NetworkUtils;

import java.util.List;

public class MovieRepository {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static MovieRepository sInstance;
    private final MovieDao mMovieDao;
    private final NetworkUtils mNetworkInstance;
    private final AppExecutors mExecutors;
    private boolean mInitialized = false;

    private MovieRepository(MovieDao movieDao,
                               NetworkUtils networkUtils,
                               AppExecutors executors) {
        mMovieDao = movieDao;
        mNetworkInstance = networkUtils;
        mExecutors = executors;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        LiveData<List<MovieEntity>> networkData = mNetworkInstance.getDownloadedMovies();
        networkData.observeForever(new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable final List<MovieEntity> newMoviesFromNetwork) {
                mExecutors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("MovieRepository","Values inserted into db from network");
                        mMovieDao.bulkInsert(newMoviesFromNetwork);
                    }
                });
            }
        });
    }

    public synchronized static MovieRepository getInstance(
            MovieDao movieDao, NetworkUtils networkUtils,
            AppExecutors executors) {

        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MovieRepository(movieDao, networkUtils,
                        executors);

            }
        }
        return sInstance;
    }

    private synchronized void initializeData(){
        if(mInitialized)
            return;
        Log.i("MovieRepository","initializeData");
        mInitialized = true;
        mNetworkInstance.scheduleRecurringMoviesSyncService();
        mNetworkInstance.startFetchWeatherService();
    }

    public LiveData<PagedList<MovieBrief>> getMoviesList(){
        Log.i("MovieRepository","getMoviesList");
        initializeData();
        return new LivePagedListBuilder<>(mMovieDao.getMoviesList(),10).build();
    }

    public LiveData<MovieDetail> getMovieById(Integer id){
        initializeData();
        mNetworkInstance.fetchMovieDetails(id);
        return mMovieDao.getMovieById(id);
    }

}
