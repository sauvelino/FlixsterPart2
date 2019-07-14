package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.flixster.Adapter.MovieAdapter;
import com.example.flixster.Model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class recyclerview extends AppCompatActivity {
    private  static final String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movie;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView rvmovie=findViewById(R.id.rv_movie);
        movie=new ArrayList<>();
        final MovieAdapter movieAdapter=new MovieAdapter(this,movie);
        rvmovie.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvmovie.setAdapter(movieAdapter);
        AsyncHttpClient query=new AsyncHttpClient();
        query.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray MovieJsonArray=response.getJSONArray("results");
                     movie.addAll(Movie.fromJsonArray(MovieJsonArray));
                     movieAdapter.notifyDataSetChanged();
                    //Log.d("Smile",movie.toString());
                } catch (JSONException e) {
                    e.printStackTrace();

                }
               // super.onSuccess(statusCode,headers,response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
