package com.example.pabbashravya.moviesonfleek.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.pabbashravya.moviesonfleek.MovieRepository;

public class DetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MovieRepository mRepository;
    private final Integer mId;

    public DetailViewModelFactory(Integer mId,MovieRepository mRepository) {
        this.mRepository = mRepository;
        this.mId = mId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DetailActivityViewModel(mId,mRepository);
    }
}
