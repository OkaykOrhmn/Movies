package com.rhmn.movies.repositorys;

import com.rhmn.movies.api.ResponceMovie;
import com.rhmn.movies.models.ResponceAllMovies;
import com.rhmn.movies.utils.Tools;

import retrofit2.Call;

public class MoviesRepository {
    public Call<ResponceAllMovies> callAllMovies(){
        //callApi
        Call<ResponceAllMovies> call = Tools.getApiServicesInstance().getAllMovies(1);

        return call;
    }

    public Call<ResponceAllMovies> callSearch(String name){
        //callApi
        Call<ResponceAllMovies> call = Tools.getApiServicesInstance().getSearch(1, name);

        return call;
    }

    public Call<ResponceAllMovies> callGenre(String genre){
        //callApi
        Call<ResponceAllMovies> call = Tools.getApiServicesInstance().getGenreMovie(genre,  1);

        return call;
    }

    public Call<ResponceMovie> callMovie(int id){
        //callApi
        Call<ResponceMovie> call = Tools.getApiServicesInstance().getMovie(id);

        return call;
    }
}
