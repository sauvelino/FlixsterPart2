package com.example.flixster.Model;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
@Parcel
public class Movie implements Parcelable {
    String posterPath,Backposter;
    String dateRelease,popularity,lang;
    String title;
    String OverView;
    long vote_average;
    int id;

    public Movie() {
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        dateRelease=jsonObject.getString("release_date");
        popularity=jsonObject.getString("popularity");
        posterPath=jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        OverView=jsonObject.getString("overview");
        Backposter=jsonObject.getString("backdrop_path");
        vote_average=jsonObject.getLong("vote_average");
        id=jsonObject.getInt("id");
        lang=jsonObject.getString("original_language");
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

    public float getVote_average() {
        return vote_average;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.Backposter);
        dest.writeString(this.title);
        dest.writeString(this.OverView);
        dest.writeLong(this.vote_average);
    }

    protected Movie(android.os.Parcel in) {
        this.posterPath = in.readString();
        this.Backposter = in.readString();
        this.title = in.readString();
        this.OverView = in.readString();
        this.vote_average = in.readLong();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(android.os.Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
