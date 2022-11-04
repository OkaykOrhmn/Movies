package com.rhmn.movies.fragments.home;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arindicatorview.ARIndicatorView;
import com.google.android.material.button.MaterialButton;
import com.rhmn.movies.R;
import com.rhmn.movies.databinding.FragmentHomeBinding;
import com.rhmn.movies.fragments.home.adapter.AllMoviesAdapter;
import com.rhmn.movies.fragments.home.adapter.GenreAdapter;
import com.rhmn.movies.fragments.home.adapter.OnItemSelect;
import com.rhmn.movies.fragments.home.adapter.SliderAdapter;
import com.rhmn.movies.models.ResponceGeners;
import com.rhmn.movies.utils.Connectivity;
import com.rhmn.movies.utils.RtlGridLayoutManager;
import com.rhmn.movies.utils.SnapToBlock;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements OnItemSelect {

    private static final String TAG = "KIANOOSH";
    private FragmentHomeBinding binding ;
    private ArrayList<String> sliderModels = new ArrayList<>();
    private ArrayList<Integer> intOfNs = new ArrayList<>();
    private ArrayList<ResponceGeners> geners = new ArrayList<>();
    private SliderAdapter sliderAdapter;
    private GenreAdapter genreAdapter;
    private AllMoviesAdapter allMoviesAdapter;
    private HomeViewModel homeViewModel;
    private int lastSelectedIndex = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding.drawerMenu.getRoot().setOnClickListener(null);
        //profile
        binding.drawerMenu.home.setOnClickListener(view -> {

            binding.drawerLayout.closeDrawer(binding.drawerMenu.getRoot());
        });
        binding.drawerMenu.exit.setOnClickListener(view -> {
            requireActivity().finish();
        });


        homeViewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> {
            binding.swipe.setRefreshing(aBoolean);
            Log.d(TAG, "b: "+aBoolean);

        });

        homeViewModel.arrayListMutableLiveData.observe(getViewLifecycleOwner(), responceGeners -> {

            LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getContext(),
                    RecyclerView.HORIZONTAL,false);

            binding.genre.setLayoutManager(horizontalLayoutManager2);
            genreAdapter = new GenreAdapter(responceGeners, this::onItem);

            binding.genre.setAdapter(genreAdapter);
            Log.d(TAG, "onChanged: " + responceGeners.size());

        });

        homeViewModel.allMoviesMutableLiveData.observe(getViewLifecycleOwner(), dataMovies -> {


            int numberOfColumns = 2;
            GridLayoutManager gridLayoutManager = new RtlGridLayoutManager(getContext(), numberOfColumns);

            LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getContext(),
                    RecyclerView.VERTICAL,false);

            binding.recAllMovies.setLayoutManager(gridLayoutManager);
            allMoviesAdapter = new AllMoviesAdapter(dataMovies);

            binding.recAllMovies.setAdapter(allMoviesAdapter);
            Log.d(TAG, "movies: " + dataMovies.size());

        });

        ArrayList<String> strings = homeViewModel.strings;
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(),
                    RecyclerView.HORIZONTAL,false);

        binding.viewPager.setLayoutManager(horizontalLayoutManager);
        sliderAdapter = new SliderAdapter(strings);
        binding.viewPager.setAdapter(sliderAdapter);
//        SnapToBlock snapToBlock = new SnapToBlock(1);
//        snapToBlock.attachToRecyclerView(binding.viewPager);
        binding.arIndicator.attachTo(binding.viewPager, true);


/*            if (firstSliders.size() == 0){
//                firstSliders.clear();
                firstSliders.addAll(strings);

                sliderModels.clear();
                intOfNs.clear();
                for (int i =0; i<strings.size(); i++){
                    Random r = new Random();
                    n = r.nextInt(10);
                    if (dontAgain(n)){
                        sliderModels.add(firstSliders.get(n));
                        intOfNs.add(n);
                    }


                }

            }*/

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.swipe.setColorSchemeColors(getResources().getColor(R.color.red)
                , getResources().getColor(R.color.yellow));
        binding.swipe.setEnabled(true);
        binding.swipe.setOnRefreshListener(() -> {
            Log.d(TAG, "ref: ");
            homeViewModel.CallAllMovies();

        });

        homeViewModel.connect.observe(getViewLifecycleOwner(), aBoolean -> {
            if (!aBoolean) {

                Dialog dialog = new Dialog(getContext(), R.style.Dialog);
                dialog.setContentView(R.layout.connectiom_dialog);
                dialog.show();

                MaterialButton tryA = dialog.findViewById(R.id.tryA);
                MaterialButton exit = dialog.findViewById(R.id.exit);
                tryA.setClickable(true);

                tryA.setOnClickListener(view1 -> {
                    tryA.setClickable(false);
                    dialog.dismiss();
//                    homeViewModel.CallAllMovies();

                });


                exit.setOnClickListener(view1 -> {
                    requireActivity().finish();
                });
            }


        });

        binding.searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                homeViewModel.CallSearch(binding.searchEdit.getText().toString());
            }
        });



        //drawer
        binding.drawerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(binding.drawerMenu.getRoot());
                binding.drawerMenu.getRoot().setOnClickListener(null);
            }
        });



    }

/*    public boolean dontAgain(int n){
        if(intOfNs.contains(n)){
            return false;
        }else{
            return true;
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume1: "+binding.arIndicator.getSelectedPosition());
        binding.arIndicator.setSelectedPosition(0);
        Log.d(TAG, "onResume2: "+binding.arIndicator.getSelectedPosition());

    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onItem(int position, String key) {
        if (key.equals("All")){
            homeViewModel.CallAllMovies();
            allMoviesAdapter.notifyDataSetChanged();

        }else{
            homeViewModel.callGenreMovie(key);
            allMoviesAdapter.notifyDataSetChanged();

        }


    }
}