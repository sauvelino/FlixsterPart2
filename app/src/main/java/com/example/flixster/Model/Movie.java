package com.example.flixster.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath,Backposter;
    String title;
    String OverView;

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath=jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        OverView=jsonObject.getString("overview");
        Backposter=jsonObject.getString("backdrop_path");
    }

    public static List<Movie> fromJsonArray(JSONArray moviejsonArray) throws JSONException {
List<Movie> movies=new ArrayList<>();
for(int i=0;i<moviejsonArray.length();i++){
    movies.add(new Movie(moviejsonArray.getJSONObject(i)));
}
   return movies;}

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath) ;
    }

    public String getBackPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",Backposter) ;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return OverView;
    }
}
