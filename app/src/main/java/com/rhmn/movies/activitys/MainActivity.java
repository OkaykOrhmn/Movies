package com.rhmn.movies.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.rhmn.movies.R;
import com.rhmn.movies.databinding.ActivityMainBinding;
import com.rhmn.movies.fragments.home.HomeViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    NavController navController ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(HomeViewModel.class);

        //nav controller
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);

    }
}