package com.rhmn.movies.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataMovies {


        @SerializedName("id")
        public int id = 0;

        @SerializedName("title")
        public String title = "";

        @SerializedName("poster")
        public String poster = "";

        @SerializedName("year")
        public String year = "";

        @SerializedName("country")
        public String country = "";

        @SerializedName("imdb_rating")
        public String imdb = "";

/*
            "id": 1,
            "title": "The Shawshank Redemption",
            "poster": "https://moviesapi.ir/images/tt0111161_poster.jpg",
            "year": "1994",
            "country": "USA",
            "imdb_rating": "9.3",
            "genres": [
            "Crime",
            "Drama"
            ],
            "images": [
            "https://moviesapi.ir/images/tt0111161_screenshot1.jpg",
            "https://moviesapi.ir/images/tt0111161_screenshot2.jpg",
            "https://moviesapi.ir/images/tt0111161_screenshot3.jpg"
            ]*/


//        @SerializedName("genres")
//    public Genres genres = new Genres();
//
//        public class Genres{
//
//            ArrayList<String> genre = new ArrayList<>();
//        }
//
//
//        @SerializedName("images")
//    public Images images = new Images();
//
//        public class Images{
//
//            ArrayList<String> images = new ArrayList<>();
//        }




}
