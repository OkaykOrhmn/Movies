package com.rhmn.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponceAllMovies {

    @SerializedName("data")
    public ArrayList<DataMovies> dataMovies = new ArrayList<>();

}
