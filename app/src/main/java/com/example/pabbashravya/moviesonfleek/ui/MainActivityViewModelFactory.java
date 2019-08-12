package com.example.pabbashravya.moviesonfleek.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.pabbashravya.moviesonfleek.MovieRepository;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository movieRepository;

    public MainActivityViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(movieRepository);
    }
}
