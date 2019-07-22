package com.example.flixster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


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
    private static final String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movie;
    private Switch sw;

    @SuppressLint("WrongConstant")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //night mode
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.NightAppTheme);
        } else {
            setTheme(R.style.AppTheme);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sw = findViewById(R.id.sw);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            sw.setChecked(true);
        }
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });
        /*
        //for the actionBar
        ActionBar  actionBar=getSupportActionBar();
        actionBar.setTitle("FlixerS");
        */


        toolbar.setTitle("FlixerS");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_subscriptions_black_24dp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
// */
        //to set the adapter in the recylerview
        RecyclerView rvmovie = findViewById(R.id.rv_movie);
        movie = new ArrayList<>();
        final MovieAdapter movieAdapter = new MovieAdapter(this, movie);
        rvmovie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvmovie.setAdapter(movieAdapter);
        AsyncHttpClient query = new AsyncHttpClient();
        query.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray MovieJsonArray = response.getJSONArray("results");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.mainmenu,menu);

        return true;
    }

     /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_switch:

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    } */

    private void restartApp() {
        Intent MN = new Intent(getApplicationContext(), recyclerview.class);
        startActivity(MN);
        finish();
    }
}

