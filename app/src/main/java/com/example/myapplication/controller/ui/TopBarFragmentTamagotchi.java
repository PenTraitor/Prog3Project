package com.example.myapplication.controller.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.TamagotchiObserver;
import com.example.myapplication.model.Tamagotchi;

public class TopBarFragmentTamagotchi extends Fragment implements TamagotchiObserver {

    private ProgressBar progressbarHunger;
    private ProgressBar progressbarEnergy;

    public TopBarFragmentTamagotchi() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topbar_tamagotchi, container, false);

        progressbarHunger = view.findViewById(R.id.progressBar_satisfaction);
        progressbarEnergy = view.findViewById(R.id.progressBar_energy);

        // Observer registrieren
        Tamagotchi.getInstance().addObserver(this);

        // Initialwerte
        onTamagotchiChanged(Tamagotchi.getInstance());

        return view;
    }

    @Override
    public void onTamagotchiChanged(Tamagotchi tamagotchi) {
        progressbarHunger.setProgress(tamagotchi.getSatisfaction());
        progressbarEnergy.setProgress(tamagotchi.getEnergy());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Tamagotchi.getInstance().removeObserver(this);
    }
}
