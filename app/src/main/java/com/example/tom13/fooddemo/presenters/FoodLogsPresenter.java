package com.example.tom13.fooddemo.presenters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom13 on 06/03/2018.
 */

public class FoodLogsPresenter {

    public FoodLogsPresenter() {

    }

    public List<String> spinnerContents(){
        List<String> spinnerContents = new ArrayList<>();
        spinnerContents.add("Day");
        spinnerContents.add("Week");
        spinnerContents.add("Month");
        return spinnerContents;
    }
}
