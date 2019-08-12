package com.example.pabbashravya.moviesonfleek;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pabbashravya.moviesonfleek.database.MovieBrief;


public class MovieAdapter extends PagedListAdapter<MovieBrief,MovieAdapter.MovieViewHolder>{
    private Context mContext;
    private static final String IMAGE_LOADING_BASE_URL_500="https://image.tmdb.org/t/p/w500/";

    public MovieAdapter(Context context) {
        super(new DiffUtil.ItemCallback<MovieBrief>() {
                  @Override
                  public boolean areItemsTheSame(MovieBrief oldItem, MovieBrief newItem) {
                      return oldItem.getId()==newItem.getId();
                  }

                  @Override
                  public boolean areContentsTheSame(MovieBrief oldItem, MovieBrief newItem) {
                      return oldItem.equals(newItem);
                  }
              });

                mContext = context;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieBrief movie=getItem(position);
            holder.bind(movie);
    }


    class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView movieImageView;
        TextView movieTitleTextView;
        CardView movieCard;
        MovieBrief movie;

        public MovieViewHolder(View view){
            super(view);

            movieCard=view.findViewById(R.id.item_card);
            movieImageView=view.findViewById(R.id.movieImage);
            movieTitleTextView=view.findViewById(R.id.movieTitle);

            movieCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("MOVIE_ID", movie.getId());
                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(MovieBrief movie) {
            this.movie=movie;
            Glide.with(mContext.getApplicationContext()).load(IMAGE_LOADING_BASE_URL_500 + movie.getPosterPath())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(movieImageView);

            if (movie.getTitle() != null)
                movieTitleTextView.setText(movie.getTitle());
            else
                movieTitleTextView.setText("");

        }
    }


}
