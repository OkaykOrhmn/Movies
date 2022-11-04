package com.rhmn.movies.fragments.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.movies.R;
import com.rhmn.movies.models.ResponceGeners;
import com.rhmn.movies.databinding.GenreBinding;

import java.util.ArrayList;
import java.util.Objects;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private static final String TAG = "KIANOOSH";
    private ArrayList<ResponceGeners> responceGeners = new ArrayList<>();
    private Context context;
    private OnItemSelect onItemSelect;
    private boolean isClicked;
    private int clickPosition;


    public GenreAdapter(ArrayList<ResponceGeners> responceGeners, OnItemSelect onItemSelect){
        this.responceGeners=responceGeners;
        this.onItemSelect = onItemSelect;
//        this.onSelectedItem= onSelectedItem;
    }


    @NonNull
    @Override
    public GenreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        GenreBinding v = GenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ResponceGeners item = responceGeners.get(position);

        holder.binding.buttonGenre.setText(item.getName());
//        holder.binding.buttonGenre.setBackgroundColor(ContextCompat.getColor(context, R.color.gray));



        if (isClicked) {

            if (clickPosition == position) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.binding.buttonGenre.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.yellow)));
                    holder.binding.buttonGenre.setTextColor(ContextCompat.getColor(context, R.color.yellow));

                }

            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.binding.buttonGenre.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white_30)));
                    holder.binding.buttonGenre.setTextColor(ContextCompat.getColor(context, R.color.white_30));

                }

            }
        }


        holder.binding.buttonGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < responceGeners.size(); i++) {
                    if (i == position){
                        responceGeners.get(i).setClick(true);

                    }else{

                        responceGeners.get(i).setClick(false);
                        Log.d(TAG, "onClick: "+i+"b"+responceGeners.get(i).isClick());
                    }


                }

                isClicked = true;
                clickPosition = position;
                notifyDataSetChanged();
                onItemSelect.onItem(position, holder.binding.buttonGenre.getText().toString());

                Log.d(TAG, "pos: "+position+"b:"+item.isClick());
            }
        });






//        holder.binding.buttonGenre.setOnClickListener(view -> {
//
//
//            onItemSelect.onItem(position, holder.binding.buttonGenre.getText().toString());
//            Log.d("RHMN", "setOnClick: ");
//        });







    }


    @Override
    public int getItemCount() {
        return responceGeners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public GenreBinding binding;

        public ViewHolder(@NonNull GenreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
