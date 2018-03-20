package com.example.tom13.fooddemo.foodLog;

import java.util.Date;

/**
 * Created by tom13 on 18/03/2018.
 */

public class FoodLogImpl implements FoodLog {

    private int id;
    private String food;
    private double calories;
    private Date timestamp;

    public FoodLogImpl (int id, String food, double calories, Date timestamp) {
        this.id = id;
        this.food = food;
        this.calories = calories;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getFood() {
        return food;
    }

    public double getCalories() {
        return calories;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
