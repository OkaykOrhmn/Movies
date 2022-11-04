package com.rhmn.movies.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponceMovie {

    @SerializedName("title")
    public String title = "";

    @SerializedName("poster")
    public String poster = "";


    @SerializedName("year")
    public String year = "";


    @SerializedName("rated")
    public String rated = "";


    @SerializedName("released")
    public String released = "";


    @SerializedName("runtime")
    public String runtime = "";


    @SerializedName("director")
    public String director = "";

    @SerializedName("writer")
    public String writer = "";

    @SerializedName("actors")
    public String actors = "";

    @SerializedName("plot")
    public String plot = "";

    @SerializedName("country")
    public String country = "";

    @SerializedName("awards")
    public String awards = "";

    @SerializedName("metascore")
    public String meta = "";

    @SerializedName("imdb_rating")
    public String imdb_rating = "";

    @SerializedName("imdb_votes")
    public String imdb_votes = "";

    @SerializedName("imdb_id")
    public String imdb_id = "";

    @SerializedName("type")
    public String type = "";


    @SerializedName("genres")
    public ArrayList<String> genres = new ArrayList<>();


    @SerializedName("images")
    public ArrayList<String> images = new ArrayList<>();


}
