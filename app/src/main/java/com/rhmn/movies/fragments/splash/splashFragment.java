package com.rhmn.movies.fragments.splash;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.rhmn.movies.R;
import com.rhmn.movies.databinding.FragmentSplashBinding;
import com.rhmn.movies.fragments.home.HomeViewModel;
import com.rhmn.movies.utils.Connectivity;

import java.util.Objects;


public class splashFragment extends Fragment {

    private FragmentSplashBinding binding ;
    private HomeViewModel homeViewMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewMode = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);



    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding =  FragmentSplashBinding.inflate(inflater, container, false);

        if (Connectivity.isConnected(getContext())){

            Handler h = new Handler();
            Runnable r = () -> {

                homeViewMode.getGenres();
                homeViewMode.CallAllMovies();

            }; h.postDelayed(r, 1000);

        }else{
            Dialog dialog = new Dialog(getContext(), R.style.Dialog);
            dialog.setContentView(R.layout.connectiom_dialog);
            dialog.show();

            MaterialButton tryA = dialog.findViewById(R.id.tryA);
            MaterialButton exit = dialog.findViewById(R.id.exit);
            tryA.setClickable(true);

            tryA.setOnClickListener(view -> {
                requireActivity().recreate();
                tryA.setClickable(false);
                dialog.dismiss();

            });

            exit.setOnClickListener(view -> {
                requireActivity().finish();
            });


        }

        homeViewMode.connect.observe(getViewLifecycleOwner(), aBoolean -> {
            if (!aBoolean){

            }
        });

        homeViewMode.success.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean){
                Navigation.findNavController(requireView()).popBackStack();
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);

            }else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });



        return binding.getRoot();

    }
}