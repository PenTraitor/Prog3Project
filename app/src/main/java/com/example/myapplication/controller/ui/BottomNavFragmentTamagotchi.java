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
import com.example.myapplication.controller.NumbersliderActivity;
import com.example.myapplication.controller.TicTacToeActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavFragmentTamagotchi extends Fragment {

    public BottomNavFragmentTamagotchi() {
        // leerer Standardkonstruktor nötig
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_nav_tamagotchi, container, false);

        BottomNavigationView bottomNav = view.findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_tictactoe) {
                startActivity(new Intent(getActivity(), TicTacToeActivity.class));
                return true;
            } else if (id == R.id.nav_2048) {
                startActivity(new Intent(getActivity(), NumbersliderActivity.class));
                return true;
            }
            return false;
        });

        return view;
    }
}
