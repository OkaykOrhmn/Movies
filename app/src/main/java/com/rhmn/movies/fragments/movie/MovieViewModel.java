package com.rhmn.movies.fragments.movie;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rhmn.movies.api.ResponceMovie;
import com.rhmn.movies.repositorys.MoviesRepository;
import com.rhmn.movies.utils.Connectivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final MoviesRepository moviesRepository = new MoviesRepository();
    public MutableLiveData<ResponceMovie> responceMovieMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> connect = new MutableLiveData<>();

    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    public void getDetails(int id){
        loading.setValue(true);
        connect.setValue(true);
        if (Connectivity.isConnected(context)){
            Call<ResponceMovie> call = moviesRepository.callMovie(id);
            call.enqueue(new Callback<ResponceMovie>() {
                @Override
                public void onResponse(@NonNull Call<ResponceMovie> call, @NonNull Response<ResponceMovie> response) {
                    responceMovieMutableLiveData.setValue(response.body());
                    loading.setValue(false);
                }

                @Override
                public void onFailure(@NonNull Call<ResponceMovie> call, @NonNull Throwable t) {
                    loading.setValue(false);

                }
            });
        }else{

            loading.setValue(false);
            connect.setValue(false);

        }
    }
}
