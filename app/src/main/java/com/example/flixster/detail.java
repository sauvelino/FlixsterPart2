package com.example.flixster;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.Model.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class detail extends YouTubeBaseActivity {
TextView tvTitle,overview,pop,dat,lang;
RatingBar ratingBar;
ImageView btn;
Movie m;
YouTubePlayerView youtube;
public static final String YoutubeApi="AIzaSyDN_oNWvzUoDu3uBoutO9i62r80uSP8Qqk";
public static final String video_trailer="https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
       // m= Parcels.unwrap(i.getParcelableExtra("movie"));
        String titre=i.getStringExtra("title");
        String over=i.getStringExtra("overview");
        float rating=i.getFloatExtra("rating",90);
        int t=i.getIntExtra("id",89);
        String gh=i.getStringExtra("img");
        String datt=i.getStringExtra("release");
        String popp=i.getStringExtra("popularity");
        String langg=i.getStringExtra("lang");


        youtube=findViewById(R.id.player);
        tvTitle=findViewById(R.id.tvtitle);
        overview=findViewById(R.id.tvoverView);
        ratingBar=findViewById(R.id.ratingBar);
        btn=findViewById(R.id.img_detail);
        pop=findViewById(R.id.pop);
        dat=findViewById(R.id.dat);
        lang=findViewById(R.id.lang);

        AsyncHttpClient query=new AsyncHttpClient();
        query.get(String.format(video_trailer,t),new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray jsonArray= response.getJSONArray("results");
                    if(jsonArray.length()==0){return;}

                    JSONObject movie=jsonArray.getJSONObject(0);
                    String keyy= movie.getString("key");
                    initializeYoutube(keyy);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
       /* tvTitle.setText(m.getTitle());
        overview.setText(m.getOverView());
        ratingBar.setRating(  m.getVote_average()); */

     //   /*
         tvTitle.setText(titre);
         overview.setText(over);
         ratingBar.setRating(rating);
         pop.setText(popp);
         dat.setText(datt);
         lang.setText(langg);


        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(1000));
        Glide.with(this)
                .load(gh)
                .apply(requestOptions)
                .into(btn);

    }


    private void initializeYoutube(final String keyy) {
        youtube.initialize(YoutubeApi, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("smile","Succes");

                youTubePlayer.loadVideo(keyy);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("smile","failed");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailmenu,menu);
        return  true;
    }



    public void home(View view) {
        Intent h=new Intent(detail.this,recyclerview.class);
        startActivity(h);
        finish();
    }

    public void share(View view) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Share");
        //don't work yet
        String link="https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        intent.putExtra(Intent.EXTRA_TEXT,link);
        startActivity(Intent.createChooser(intent,"Share via"));
    }
}
