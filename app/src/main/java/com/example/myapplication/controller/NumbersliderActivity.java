package com.example.myapplication.controller;

import android.os.Bundle;
import com.example.myapplication.R;
import androidx.appcompat.app.AppCompatActivity;


public class NumbersliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Verknüpft die Activity mit dem Haupt-Layout, das die drei FragmentContainerViews enthält
        setContentView(R.layout.activity_numberslider);
    }

}