package nl.hu.zrb.movieapp;

/**
 * Created by JZuurbier on 28-12-2016.
 */

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movieList = new ArrayList<>();

    public void add(Movie m){
        movieList.add(0,m);
        notifyItemInserted(0);
    }

    public void clear() {
        movieList.clear();
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titleAndYear;
        public ImageView poster;

        public MyViewHolder(View view) {
            super(view);
            titleAndYear = (TextView) view.findViewById(R.id.textView1);
            poster = (ImageView) view.findViewById(R.id.imageView1);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleAndYear.setText(movie.title + ", " + movie.year);
        new ImageLoader(holder.poster).execute(movie.posterUrl);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}

