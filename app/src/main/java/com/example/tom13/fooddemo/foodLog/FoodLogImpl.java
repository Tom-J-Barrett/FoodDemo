package com.example.tom13.fooddemo.foodLog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tom13 on 18/03/2018.
 * Implementation of FoodLog interface.
 */

public class FoodLogImpl implements FoodLog {

    private int id;
    private String food;
    private double calories;
    private Date timestamp;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());

    private FoodLogImpl (FoodLogBuilder foodLogBuilder) {
        this.id = foodLogBuilder.id;
        this.food = foodLogBuilder.food;
        this.calories = foodLogBuilder.calories;
        this.timestamp = foodLogBuilder.timestamp;
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

    @Override
    public String toString() {
        return formatDate(this.getTimestamp()) + "\n" + this.getFood().toUpperCase() + " / " + this.getCalories() + " Calories";
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static class FoodLogBuilder {
        private int id = 0;
        private String food;
        private double calories;
        private Date timestamp;

        public FoodLogBuilder(String food) {
            this.food = food;
        }

        public FoodLogBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public FoodLogBuilder withCalories(double calories) {
            this.calories = calories;
            return this;
        }

        public FoodLogBuilder withTimestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public FoodLog build() {
            return new FoodLogImpl(this);
        }

    }
}
