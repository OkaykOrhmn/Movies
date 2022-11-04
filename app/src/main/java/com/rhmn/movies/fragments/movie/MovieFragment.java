package com.rhmn.movies.fragments.movie;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;
import com.rhmn.movies.R;
import com.rhmn.movies.databinding.FragmentMovieBinding;
import com.rhmn.movies.fragments.movie.adapter.DetailMovieAdapter;
import com.rhmn.movies.fragments.movie.adapter.PostersAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieFragment extends Fragment {

    private static final String TAG = "MovieFragment";
    private FragmentMovieBinding binding;
    private MovieViewModel movieViewModel;
    private ArrayList<String> stringArrayList = new ArrayList<>();
    private DetailMovieAdapter detailMovieAdapter ;
    private PostersAdapter postersAdapter ;
    private int id = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMovieBinding.inflate(inflater, container, false);
        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        binding.moreRel.setVisibility(View.VISIBLE);


        binding.groupButtons.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked){
                if (checkedId == R.id.actors){
                    binding.actorsRel.setVisibility(View.VISIBLE);

                    binding.posterRel.setVisibility(View.INVISIBLE);
                    binding.moreRel.setVisibility(View.INVISIBLE);
                }

                if (checkedId == R.id.more){
                    binding.moreRel.setVisibility(View.VISIBLE);

                    binding.actorsRel.setVisibility(View.INVISIBLE);
                    binding.posterRel.setVisibility(View.INVISIBLE);
                }

                if (checkedId == R.id.posters){
                    binding.posterRel.setVisibility(View.VISIBLE);

                    binding.moreRel.setVisibility(View.INVISIBLE);
                    binding.actorsRel.setVisibility(View.INVISIBLE);
                }

            }

            Log.d(TAG, "isChecked: " + isChecked);
            Log.d(TAG, "checkedId: "+ checkedId);
        });

        assert getArguments() != null;
        id = getArguments().getInt("id");
        movieViewModel.getDetails(id);

        movieViewModel.connect.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                binding.all.setVisibility(View.VISIBLE);
            }else{
                binding.all.setVisibility(View.INVISIBLE);
                Dialog dialog = new Dialog(getContext(), R.style.Dialog);
                dialog.setContentView(R.layout.connectiom_dialog);
                dialog.show();

                MaterialButton tryA = dialog.findViewById(R.id.tryA);
                MaterialButton exit = dialog.findViewById(R.id.exit);
                tryA.setClickable(true);

                tryA.setOnClickListener(view1 -> {
                    tryA.setClickable(false);
                    dialog.dismiss();
                    movieViewModel.getDetails(id);


                });


                exit.setOnClickListener(view1 -> {
                    requireActivity().finish();
                });
            }
        });


        binding.swipe.setEnabled(true);
        binding.swipe.setOnRefreshListener(() -> {
            binding.swipe.setRefreshing(false);
        });

        movieViewModel.responceMovieMutableLiveData.observe(getViewLifecycleOwner(), responceMovie -> {
            binding.nameMovie.setText(responceMovie.title);
            Picasso.get().load(responceMovie.poster).into(binding.posterMovie);
            binding.posterMovie.setOnClickListener(view -> {

                Dialog d = new Dialog(getContext(), R.style.Dialog);
                d.setContentView(R.layout.main_poster_dialog);
                ImageView imageView = d.findViewById(R.id.image);
                Picasso.get().load(responceMovie.poster).into(imageView);

//                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(d.getWindow().getAttributes());
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//                d.getWindow().setAttributes(lp);
                d.show();

                RelativeLayout relativeLayout = d.findViewById(R.id.a);
                relativeLayout.setOnClickListener(view1 -> {
                    d.dismiss();
                });

                imageView.setOnClickListener(view12 -> {
                    d.dismiss();
                });

            });

            binding.year.setText(responceMovie.year);
            binding.imdb.setText(responceMovie.imdb_rating);

            binding.directorsDetails.setText(responceMovie.director);
            binding.writersDetails.setText(responceMovie.writer);
            binding.actorsDetails.setText(responceMovie.actors);

            binding.details.setText(responceMovie.plot);
            binding.awardsDetails.setText(responceMovie.awards);

            binding.score.setText(responceMovie.meta);
            binding.votes.setText(responceMovie.imdb_votes);

            binding.rated.setText(responceMovie.rated);

            stringArrayList.clear();
            stringArrayList.addAll(responceMovie.genres);
            stringArrayList.add(responceMovie.type);
            stringArrayList.add(responceMovie.country);
            stringArrayList.add(responceMovie.released);
            stringArrayList.add(responceMovie.runtime);

            LinearLayoutManager hor = new LinearLayoutManager(getContext(),
                    RecyclerView.HORIZONTAL,false);

            binding.movieGenreTime.setLayoutManager(hor);
            detailMovieAdapter = new DetailMovieAdapter(stringArrayList);

            binding.movieGenreTime.setAdapter(detailMovieAdapter);
            Log.d(TAG, "onChanged: " + stringArrayList.size());

            LinearLayoutManager postersLayout = new LinearLayoutManager(getContext(),
                    RecyclerView.HORIZONTAL,false);

            binding.recPosters.setLayoutManager(postersLayout);
            postersAdapter = new PostersAdapter(responceMovie.images, requireActivity());

            binding.recPosters.setAdapter(postersAdapter);
            Log.d(TAG, "onChanged: " + responceMovie.images.size());



        });

        movieViewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> {
            binding.swipe.setRefreshing(aBoolean);
            if (aBoolean){
                binding.all.setVisibility(View.INVISIBLE);
            }else {
                binding.all.setVisibility(View.VISIBLE);
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment

    }
}