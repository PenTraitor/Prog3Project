package com.example.myapplication.controller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavFragmentTicTacToe extends Fragment {

    public BottomNavFragmentTicTacToe() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_nav_tictactoe, container, false);

        BottomNavigationView bottomNav = view.findViewById(R.id.nav_view_tictactoe);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home_tictactoe) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                return true;
            }
            return false;
        });
        return view;
    }
}
