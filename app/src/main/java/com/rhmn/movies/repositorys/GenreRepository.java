package com.rhmn.movies.repositorys;

import com.rhmn.movies.models.ResponceGeners;
import com.rhmn.movies.utils.Tools;

import java.util.ArrayList;

import retrofit2.Call;

public class GenreRepository {
    public Call<ArrayList<ResponceGeners>> callSendActivationCode(){
        //callApi
        Call<ArrayList<ResponceGeners>> call = Tools.getApiServicesInstance().getGeners();

        return call;
    }

}
