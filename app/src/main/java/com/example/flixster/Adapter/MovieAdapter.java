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
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.Model.Movie;
import com.example.flixster.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case 1:
                View v1 = inflater.inflate(R.layout.populate,parent,false);
                viewHolder = new ViewHolderPopulate(v1);
                break;
            case 0:
                View v2 = inflater.inflate(R.layout.custom,parent,false);
                viewHolder = new ViewHolderBasic(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case 1:
                ViewHolderPopulate holderPopulate = (ViewHolderPopulate) holder;
                holderPopulate.bind(position);
                break;
            case 0:
                ViewHolderBasic holderBasic = (ViewHolderBasic) holder;
                holderBasic.bind(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getVote_average()> 5.0)
            return 1;
        else
            return 0;
    }


    /*
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        return new ViewHolder(view);
    }



    public void onBindViewHolder(@NonNull ViewHolderBasic holder, int position) {
        Movie movie= movies.get(position);
        holder.bind(movie);
    }*/

    class ViewHolderBasic extends RecyclerView.ViewHolder{
        TextView tv_title,tv_overview;
        ImageView poster;
        public ViewHolderBasic(@NonNull View itemView) {
            super(itemView);
            tv_title= itemView.findViewById(R.id.tv_title);
            tv_overview= itemView.findViewById(R.id.tv_overview);
            poster= itemView.findViewById(R.id.img_poster);
        }

        public void bind(int position) {
            tv_title.setText(movies.get(position).getTitle());
            tv_overview.setText(movies.get(position).getOverView());
            String imgurl= movies.get(position).getPosterPath();

                if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    imgurl = movies.get(position).getBackPosterPath();
                }
                Glide.with(context).load(imgurl).into(poster);
        }
    }



    class ViewHolderPopulate extends RecyclerView.ViewHolder{
        ImageView poster;
        public ViewHolderPopulate(@NonNull View itemView) {
            super(itemView);
            poster= itemView.findViewById(R.id.populate);
        }

        public void bind(int position) {
            String imgurl= movies.get(position).getPosterPath();


            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imgurl = movies.get(position).getBackPosterPath();
            }
            Glide.with(context).load(imgurl).into(poster);
        }
    }
}
