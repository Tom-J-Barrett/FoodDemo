package com.example.tom13.fooddemo.views.gestures;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.tom13.fooddemo.views.FoodLogsActivity;

/**
 * Created by tom13 on 18/03/2018.
 */

public class FlingGesture extends Activity implements GestureDetector.OnGestureListener {

    private FoodLogsActivity foodLogsActivity;

    public FlingGesture(FoodLogsActivity foodLogsActivity) {
        this.foodLogsActivity = foodLogsActivity;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        foodLogsActivity.onFling();
        return true;
    }
}
