package com.rhmn.movies.fragments.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.movies.databinding.GenreBinding;

import java.util.ArrayList;

public class DetailMovieAdapter extends RecyclerView.Adapter<DetailMovieAdapter.ViewHolder> {

    private ArrayList<String> all = new ArrayList<>();
    private Context context;

    public DetailMovieAdapter(ArrayList<String> all){
        this.all = all;

    }

    @NonNull
    @Override
    public DetailMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        GenreBinding v = GenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DetailMovieAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMovieAdapter.ViewHolder holder, int position) {

        holder.binding.buttonGenre.setText(all.get(position));

    }

    @Override
    public int getItemCount() {
        return all.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public GenreBinding binding;

        public ViewHolder(@NonNull GenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
