package com.example.tom13.fooddemo.calorieEstimation;

import android.os.AsyncTask;

import com.example.tom13.fooddemo.backgroundProcesses.CalorieEstimation;
import com.example.tom13.fooddemo.presenters.CaptureImagePresenter;

/**
 * Created by tom13 on 16/03/2018.
 */

public class CalorieEstimationFactory {

    private String query;
    private CaptureImagePresenter captureImagePresenter;

    public CalorieEstimationFactory(String query, CaptureImagePresenter captureImagePresenter) {
        this.query = query;
        this.captureImagePresenter = captureImagePresenter;
    }

    public AsyncTask<Void, Void, String> getCalorieEstimator() {
        return new CalorieEstimation(query, captureImagePresenter);
    }
}
