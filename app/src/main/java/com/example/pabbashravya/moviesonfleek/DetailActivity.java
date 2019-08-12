package com.example.pabbashravya.moviesonfleek;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pabbashravya.moviesonfleek.database.MovieDatabase;
import com.example.pabbashravya.moviesonfleek.database.MovieDetail;
import com.example.pabbashravya.moviesonfleek.network.NetworkUtils;
import com.example.pabbashravya.moviesonfleek.ui.DetailActivityViewModel;
import com.example.pabbashravya.moviesonfleek.ui.DetailViewModelFactory;


public class DetailActivity extends AppCompatActivity {

    private int mMovieId;
    private TextView mOverviewTextView;
    private TextView mReleaseDateTextView;
    private TextView mRuntimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar actionBar=this.getSupportActionBar();
        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        mMovieId=getIntent().getIntExtra("MOVIE_ID",0);

        mOverviewTextView=findViewById(R.id.overview);
        mReleaseDateTextView=findViewById(R.id.release_date);
        mRuntimeTextView=findViewById(R.id.runtime);

        MovieDatabase movieDatabase = MovieDatabase.getInstance(this);
        AppExecutors appExecutors = AppExecutors.getInstance();
        NetworkUtils networkUtils = NetworkUtils.getInstance(this,appExecutors);
        MovieRepository movieRepository = MovieRepository.getInstance(movieDatabase.movieDao(),networkUtils,appExecutors);
        DetailViewModelFactory factory = new DetailViewModelFactory(mMovieId,movieRepository);
        DetailActivityViewModel viewModel = ViewModelProviders.of(this,factory).get(DetailActivityViewModel.class);

        viewModel.getMovieDetail().observe(this, new Observer<MovieDetail>() {
            @Override
            public void onChanged(@Nullable MovieDetail movieDetail) {
                if(movieDetail!=null)
                    bindDetails(movieDetail);
            }
        });
    }

    private void bindDetails(MovieDetail movieDetail){

        mOverviewTextView.setText("Overview:\n" + movieDetail.getOverview());
        mReleaseDateTextView.setText("Release Date:" + movieDetail.getReleaseDate());
        Integer runtime = movieDetail.getRuntime();
        String detailsString = "";
        if (runtime != null && runtime != 0) {
            if (runtime < 60) {
                detailsString += runtime + " min(s)";
            } else {
                detailsString += runtime / 60 + " hr " + runtime % 60 + " mins";
            }
        } else {
            detailsString += "-";
        }

        mRuntimeTextView.setText("Runtime:" + detailsString);

    }


}
