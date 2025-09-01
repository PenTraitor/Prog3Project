package com.example.myapplication.controller;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import com.example.myapplication.R;

public class NeutralAnimation implements AnimationStrategy {
    @Override
    public void startAnimation(ImageView spriteView) {
        spriteView.setImageResource(R.drawable.anim_neutral);
        spriteView.post(() -> {
            AnimationDrawable animation = (AnimationDrawable) spriteView.getDrawable();
            if (animation != null) animation.start();
        });
    }
}