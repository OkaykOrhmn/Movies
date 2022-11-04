package com.rhmn.movies.fragments.movie.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.movies.R;
import com.rhmn.movies.databinding.PostersBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {

    private ArrayList<String> posters;
    private Context context;
    private Activity activity;

    public PostersAdapter(ArrayList<String> posters, Activity activity){
        this.posters = posters;
        this.activity = activity;

    }

    @NonNull
    @Override
    public PostersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        PostersBinding v = PostersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PostersAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostersAdapter.ViewHolder holder, int position) {

        Picasso.get().load(posters.get(position)).into(holder.binding.poster);
        holder.binding.button.setOnClickListener(view -> {

            Dialog d = new Dialog(context, R.style.Dialog);
            d.setContentView(R.layout.poster_dialog);
            ImageView imageView = d.findViewById(R.id.image);
            Picasso.get().load(posters.get(position)).into(imageView);

//            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//            lp.copyFrom(d.getWindow().getAttributes());
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            d.getWindow().setAttributes(lp);
            d.show();

        });
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public PostersBinding binding;

        public ViewHolder(@NonNull PostersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }    }
}
