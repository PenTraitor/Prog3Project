package com.example.myapplication.controller;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.example.myapplication.R;

public class HappyAnimation implements AnimationStrategy {
    @Override
    public void startAnimation(ImageView spriteView) {
        spriteView.setImageResource(R.drawable.anim_happy);
        spriteView.post(() -> {
            AnimationDrawable animation = (AnimationDrawable) spriteView.getDrawable();
            if (animation != null) animation.start();
        });
    }
}