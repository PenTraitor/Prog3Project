/**
 * Erkennung von Gesten
 *
 * <p>Erstellt von sklinker</p>
 *
 * @author sklinker
 * @version 1.0
 * @since 2025-09-01
 */
package com.example.myapplication.visualization;

import android.content.Context;

import android.os.Handler;
import android.os.Looper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

//used code from https://pcfandroid.wordpress.com/2011/07/17/swipe-with-android-android-tutorial/
public class DirectionSwipeDetector extends GestureDetector.SimpleOnGestureListener{

    private static final long SWIPE_COOLDOWN_MS = 300; // cooldown duration
    private boolean swipeLocked = false;

    private static final int SWIPE_MIN_DISTANCE = 50;

    private static final int SWIPE_MAX_OFF_PATH = 250;

    private static final int SWIPE_THRESHOLD_VELOCITY = 50;


    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (swipeLocked) return false;

        float dX = e2.getX()-e1.getX();

        float dY = e1.getY()-e2.getY();

        if (Math.abs(dY)<SWIPE_MAX_OFF_PATH &&

            Math.abs(velocityX)>=SWIPE_THRESHOLD_VELOCITY &&

            Math.abs(dX)>=SWIPE_MIN_DISTANCE ) {
            swipeLocked = true;
            if (dX>0) {

                onSwipeRight();
            } else {

                onSwipeLeft();

            }
            unlockSwipeAfterDelay();
            return true;

        } else if (Math.abs(dX)<SWIPE_MAX_OFF_PATH &&

                    Math.abs(velocityY)>=SWIPE_THRESHOLD_VELOCITY &&

                    Math.abs(dY)>=SWIPE_MIN_DISTANCE ) {
            swipeLocked = true;
            if (dY>0) {

               onSwipeUp();

            } else {

               onSwipeDown();

            }
            unlockSwipeAfterDelay();
            return true;

        }

        return false;

    }

    private void unlockSwipeAfterDelay() {
        handler.postDelayed(() -> swipeLocked = false, SWIPE_COOLDOWN_MS);
    }



        // These 4 get override
    public void onSwipeRight() {}
    public void onSwipeLeft() {}
    public void onSwipeUp() {}
    public void onSwipeDown() {}


}

