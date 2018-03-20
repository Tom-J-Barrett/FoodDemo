package com.example.tom13.fooddemo.foodLog;

import java.util.Date;

/**
 * Created by tom13 on 18/03/2018.
 */

public interface FoodLog {
    String getFood();
    double getCalories();
    Date getTimestamp();
    int getId();
}
