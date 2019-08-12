package com.example.pabbashravya.moviesonfleek;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.pabbashravya.moviesonfleek.database.MovieBrief;
import com.example.pabbashravya.moviesonfleek.database.MovieDatabase;
import com.example.pabbashravya.moviesonfleek.network.NetworkUtils;
import com.example.pabbashravya.moviesonfleek.ui.MainActivityViewModel;
import com.example.pabbashravya.moviesonfleek.ui.MainActivityViewModelFactory;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MovieAdapter mAdapter;
    private RecyclerView mMoviesList;
    private int mPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesList=findViewById(R.id.rv_movies);
        mMoviesList.setLayoutManager(new LinearLayoutManager(this));
        mMoviesList.setHasFixedSize(true);

        mAdapter=new MovieAdapter(this);
        mMoviesList.setAdapter(mAdapter);

        MovieDatabase movieDatabase = MovieDatabase.getInstance(this);
        AppExecutors appExecutors = AppExecutors.getInstance();
        NetworkUtils networkUtils = NetworkUtils.getInstance(this,appExecutors);
        MovieRepository movieRepository = MovieRepository.getInstance(movieDatabase.movieDao(),networkUtils,appExecutors);
        MainActivityViewModelFactory factory = new MainActivityViewModelFactory(movieRepository);
        MainActivityViewModel viewModel = ViewModelProviders.of(this,factory).get(MainActivityViewModel.class);
        Log.i("MainActivity","ViewModel created");
        viewModel.getMoviesList().observe(this, new Observer<PagedList<MovieBrief>>() {
            @Override
            public void onChanged(@Nullable PagedList<MovieBrief> movieBriefs) {
                mAdapter.submitList(movieBriefs);
            }

        });

    }

}
