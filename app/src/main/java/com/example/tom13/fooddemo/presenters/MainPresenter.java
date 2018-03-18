package com.example.tom13.fooddemo.presenters;

import android.content.Context;
import android.content.Intent;

import com.example.tom13.fooddemo.views.CaptureImageActivity;
import com.example.tom13.fooddemo.views.FoodLogsActivity;


/**
 * Created by tom13 on 06/03/2018.
 */

public class MainPresenter {

    private Intent intent;
    private Context context;

    public MainPresenter(Context context) {
        this.context=context;
    }

    public void takePhoto() {
        intent = new Intent(context, CaptureImageActivity.class);
        context.startActivity(intent);
    }

    public void userLogs() {
        intent = new Intent(context, FoodLogsActivity.class);
        context.startActivity(intent);
    }

}
