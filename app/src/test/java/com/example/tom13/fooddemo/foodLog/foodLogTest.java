package com.example.tom13.fooddemo.foodLog;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by tom13 on 20/03/2018.
 */

public class foodLogTest {

    private FoodLog foodLog;

    private static final String food = "banana";
    private static final Double calories = 59.03;
    private static final Date timestamp = new Date();
    private static final int id = 0;

    @Before
    public void setUp() {
        foodLog = new FoodLogImpl.FoodLogBuilder("banana")
                                    .withCalories(59.03)
                                    .withTimestamp(timestamp)
                                    .build();
    }

    @Test
    public void shouldAssertCorrectValues() {
        assert food.equals(foodLog.getFood());
        assert calories == foodLog.getCalories();
        assert timestamp.equals(foodLog.getTimestamp());
        assert id == foodLog.getId();
    }
}
