package com.example.tom13.fooddemo.backgroundProcesses;

import com.example.tom13.fooddemo.presenters.FoodLogsPresenter;

/**
 * Created by tom13 on 29/03/2018.
 */

public class CallbackRunnable implements Runnable {

    private final Runnable runnable;

    private final FoodLogsPresenter foodLogsPresenter;

    public CallbackRunnable (Runnable runnable, FoodLogsPresenter foodLogsPresenter) {
        this.runnable = runnable;
        this.foodLogsPresenter = foodLogsPresenter;
    }
    @Override
    public void run() {
        runnable.run();
        //foodLogsPresenter.updateDate();
    }
}
