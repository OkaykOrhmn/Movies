package com.rhmn.movies.fragments.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rhmn.movies.models.DataMovies;
import com.rhmn.movies.repositorys.GenreRepository;
import com.rhmn.movies.repositorys.MoviesRepository;
import com.rhmn.movies.models.ResponceAllMovies;
import com.rhmn.movies.models.ResponceGeners;
import com.rhmn.movies.utils.Connectivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = "HomeViewModel";
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final GenreRepository genreRepository = new GenreRepository();
    private final MoviesRepository moviesRepository = new MoviesRepository();

    public MutableLiveData<ArrayList<ResponceGeners>> arrayListMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<DataMovies>> allMoviesMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> connect = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> singleGenre = new MutableLiveData<>();

    public ArrayList<String> strings = new ArrayList<>();
    private ArrayList<ResponceGeners> groupOfGenres = new ArrayList<>();


    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    //Genres
    public void getGenres( ){

        if (groupOfGenres.size() == 0  ){
            //call api
            callAllGenre();
        }else {
            //return value
            arrayListMutableLiveData.setValue(groupOfGenres)  ;
        }


    }
    private void callAllGenre(){
        if (Connectivity.isConnected(context)){
//            connect.setValue(true);
            Call<ArrayList<ResponceGeners>> call = genreRepository.callSendActivationCode();
            call.enqueue(new Callback<ArrayList<ResponceGeners>>() {
                @Override
                public void onResponse(Call<ArrayList<ResponceGeners>> call, Response<ArrayList<ResponceGeners>> response) {
                    if (response.isSuccessful()){
                        groupOfGenres.addAll(response.body());
                        Log.d(TAG, "onResponse: " + response.body().size());
                        response.body().get(0).setId(0);
                        response.body().get(0).setName("All");
                        arrayListMutableLiveData.setValue(response.body());
                        for (int i = 0; i<response.body().size(); i++){
                            response.body().get(i).setClick(false);
                        }

                    }else{
                        success.setValue(false);

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ResponceGeners>> call, Throwable t) {
                    success.setValue(false);

                }
            });
        }else{
//            connect.setValue(false);
            success.setValue(false);

        }
    }

    //movies
    public void CallAllMovies(){
        if (Connectivity.isConnected(context)) {
            loading.setValue(true);
            connect.setValue(true);
            Call<ResponceAllMovies> call = moviesRepository.callAllMovies();
            call.enqueue(new Callback<ResponceAllMovies>() {
                @Override
                public void onResponse(Call<ResponceAllMovies> call, Response<ResponceAllMovies> response) {
                    Log.d(TAG, "onResponseAll: ");
                    allMoviesMutableLiveData.setValue(response.body().dataMovies);
                    strings.clear();
                    for (int i =0; i<response.body().dataMovies.size(); i++) {
                        strings.add(response.body().dataMovies.get(i).poster);
                    }
                    Collections.shuffle(strings);
                    success.setValue(true);
                    loading.setValue(false);
                }

                @Override
                public void onFailure(Call<ResponceAllMovies> call, Throwable t) {
                    Log.d(TAG, "onFailureAll: ");
                    success.setValue(false);
                    loading.setValue(false);
                }
            });
        }else{
            connect.setValue(false);
            success.setValue(false);
            loading.setValue(false);

        }
    }

    //SearchMovies
    public void CallSearch(String name){
        if (Connectivity.isConnected(context)) {
            connect.setValue(true);
            loading.setValue(true);
            Call<ResponceAllMovies> call = moviesRepository.callSearch(name);
            call.enqueue(new Callback<ResponceAllMovies>() {
                @Override
                public void onResponse(Call<ResponceAllMovies> call, Response<ResponceAllMovies> response) {

                    allMoviesMutableLiveData.setValue(response.body().dataMovies);
                    success.setValue(true);
                    loading.setValue(false);
                    singleGenre.setValue(false);

                }

                @Override
                public void onFailure(Call<ResponceAllMovies> call, Throwable t) {
                    success.setValue(false);
                    loading.setValue(false);

                }
            });
        }else{
            connect.setValue(false);
            success.setValue(false);
            loading.setValue(false);

        }
    }

    public void callGenreMovie(String genre){
        if (Connectivity.isConnected(context)){
            Call<ResponceAllMovies> call = moviesRepository.callGenre(genre);
            call.enqueue(new Callback<ResponceAllMovies>() {
                @Override
                public void onResponse(Call<ResponceAllMovies> call, Response<ResponceAllMovies> response) {
                    allMoviesMutableLiveData.setValue(response.body().dataMovies);
                    singleGenre.setValue(true);
                }

                @Override
                public void onFailure(Call<ResponceAllMovies> call, Throwable t) {

                }
            });
        }else{

        }
    }

}
