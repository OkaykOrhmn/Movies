package com.rhmn.movies.api;

import com.rhmn.movies.models.ResponceAllMovies;
import com.rhmn.movies.models.ResponceGeners;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServices {
    //https://moviesapi.ir/api/v1/genres
    @GET("/api/v1/genres")
    Call<ArrayList<ResponceGeners>> getGeners();

    //https://moviesapi.ir/api/v1/movies?page={page}
    @GET("/api/v1/movies?page=")
    Call<ResponceAllMovies> getAllMovies(@Query("page") int page);

    //https://moviesapi.ir/api/v1/movies/{movie_id}
    @GET("/api/v1/movies/{id}")
    Call<ResponceMovie> getMovie(@Path("id") int id);

    //https://moviesapi.ir/api/v1/movies?q={name}&page={page}
    @GET("/api/v1/movies?q=&page=")
    Call<ResponceAllMovies> getSearch(@Query("page")int page, @Query("q") String name);

    //https://moviesapi.ir/api/v1/genres/{genre_id}/movies?page={page}
    @GET("/api/v1/genres/{genre_id}/movies?page=")
    Call<ResponceAllMovies> getGenreMovie(@Path("genre_id") String genre, @Query("page") int page);

}
