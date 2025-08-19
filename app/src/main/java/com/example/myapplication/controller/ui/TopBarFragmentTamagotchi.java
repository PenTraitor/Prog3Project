package com.example.myapplication.controller.ui;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.controller.TamagotchiListener;

public class TopBarFragmentTamagotchi extends Fragment{

    public TopBarFragmentTamagotchi(){}

    private TamagotchiListener listener;
    private ProgressBar progressBar_hunger;
    private ProgressBar progressBar_energy;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TamagotchiListener) {
            listener = (TamagotchiListener) context;
        } else {
            throw new ClassCastException(context + " must implement TamagotchiListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topbar_tamagotchi, container, false);
        progressBar_hunger = view.findViewById(R.id.progressBar_hunger);
        progressBar_energy = view.findViewById(R.id.progressBar_energy);

        // sofort Werte setzen
        if (getActivity() instanceof TamagotchiListener listener) {
            updateProgressBars(listener);
        }

        return view;
    }

    public void updateProgressBars(TamagotchiListener listener) {
        progressBar_hunger.setProgress(listener.getHunger());
        progressBar_energy.setProgress(listener.getEnergy());
    }
}
