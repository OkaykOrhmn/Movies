package com.rhmn.movies.fragments.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.movies.databinding.SlideRowBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

    private ArrayList<String> sliderModels = new ArrayList<>();
    private Context context;
    private int position;


    public SliderAdapter(ArrayList<String> sliderModels){
        this.sliderModels=sliderModels;
//        this.onSelectedItem= onSelectedItem;
    }


    @NonNull
    @Override
    public SliderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        SlideRowBinding v = SlideRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SliderAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position = position;

        String item = sliderModels.get(position);

//        holder.binding.nameSlider.setText(item.getName());
        Picasso.get().load(item).into(holder.binding.imageSlider);


    }

    public int position(){

        return position;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public SlideRowBinding binding;

        public ViewHolder(@NonNull SlideRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
