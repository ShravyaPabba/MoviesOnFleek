package com.example.pabbashravya.moviesonfleek.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.util.Log;

import com.example.pabbashravya.moviesonfleek.MovieRepository;
import com.example.pabbashravya.moviesonfleek.database.MovieBrief;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private final MovieRepository movieRepository;
    private final LiveData<PagedList<MovieBrief>> moviesList;


    public MainActivityViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        Log.i("MainViewModel","ViewModel constructor");
        moviesList = movieRepository.getMoviesList();
    }

    public LiveData<PagedList<MovieBrief>> getMoviesList() {
        Log.i("MainViewModel","Getmovieslist");
        return moviesList;
    }

}
