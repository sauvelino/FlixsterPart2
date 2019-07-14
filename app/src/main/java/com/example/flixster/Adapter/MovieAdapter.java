package com.example.flixster.Adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.Model.Movie;
import com.example.flixster.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
Context context;
List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie= movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
TextView tv_title,tv_overview;
ImageView poster;
RelativeLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title= itemView.findViewById(R.id.tv_title);
            tv_overview= itemView.findViewById(R.id.tv_overview);
            poster= itemView.findViewById(R.id.img_poster);

        }



        public void bind(Movie movie) {
            tv_title.setText(movie.getTitle());
            tv_overview.setText(movie.getOverView());
            String imgurl=movie.getPosterPath();
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                imgurl=movie.getBackPosterPath();
            }
            Glide.with(context).load(imgurl).into(poster);

        }
    }
}
