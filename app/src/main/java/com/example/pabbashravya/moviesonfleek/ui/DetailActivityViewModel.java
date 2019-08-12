package com.example.pabbashravya.moviesonfleek.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.pabbashravya.moviesonfleek.MovieRepository;
import com.example.pabbashravya.moviesonfleek.database.MovieDetail;

public class DetailActivityViewModel extends ViewModel {

    private final LiveData<MovieDetail> mMovieDetail;

    private final Integer mId;
    private final MovieRepository mMovieRepository;

    public DetailActivityViewModel(Integer mId, MovieRepository mMovieRepository) {
        this.mId = mId;
        this.mMovieRepository = mMovieRepository;

        mMovieDetail = mMovieRepository.getMovieById(mId);
    }

    public LiveData<MovieDetail> getMovieDetail() {
        return mMovieDetail;
    }

}
