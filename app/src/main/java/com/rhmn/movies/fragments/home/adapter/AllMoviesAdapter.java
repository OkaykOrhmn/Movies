package com.rhmn.movies.fragments.home.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.movies.R;
import com.rhmn.movies.models.DataMovies;
import com.rhmn.movies.databinding.CardMoviesBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllMoviesAdapter extends RecyclerView.Adapter<AllMoviesAdapter.ViewHolder> {

    private ArrayList<DataMovies> dataMovies = new ArrayList<>();
    private Context context;


    public AllMoviesAdapter(ArrayList<DataMovies> dataMovies){
        this.dataMovies=dataMovies;
//        this.onSelectedItem= onSelectedItem;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        CardMoviesBinding v = CardMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AllMoviesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DataMovies item = dataMovies.get(position);

        Picasso.get().load(item.poster).into(holder.binding.mainPoster);
        holder.binding.imdbRate.setText(item.imdb);
        holder.binding.nameMovie.setText(item.title);

        holder.binding.cardMovie.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", item.id);
            Navigation.findNavController(view).navigate(R.id.movieFragment, bundle);
        });




    }

    @Override
    public int getItemCount() {
        return dataMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardMoviesBinding binding;

        public ViewHolder(@NonNull CardMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
