package com.example.tom13.fooddemo.storage;

import android.content.Context;

import com.example.tom13.fooddemo.foodLog.FoodLog;
import com.example.tom13.fooddemo.foodLog.FoodLogImpl;
import com.example.tom13.fooddemo.views.CaptureImageActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Created by tom13 on 20/03/2018.
 */

public class storageTest {

    private DAO dao;
    private List<FoodLog> foodLogs;
    private Date date;
    private FoodLog foodLog;
    private FoodLog testLog = null;

    @Before
    public void setup() {
        dao = mock(SqlLiteDAO.class);
        date = new Date();
        foodLog = new FoodLogImpl(0, "test", 0.0, date);
    }

    @Test
    public void shouldGetListOfFoodLogs() {
        foodLogs = dao.getLogsByDay(date);
        foodLogs = dao.getLogsByWeek(date);
        foodLogs = dao.getLogsByMonth(date);
    }

    @Test
    public void shouldInsertFoodLogToDao() {
        dao.addFoodLog(foodLog);
        setTestLog();
        assert foodLogs.contains(testLog);
    }

    @Test
    public void shouldDeleteFoodLog() {
        List<FoodLog> foodLogs = new ArrayList<>();
        foodLogs.add(testLog);
        dao.deleteFoodLogs(foodLogs);
        setTestLog();
        assert testLog.equals(null);
    }

    private void setTestLog() {
        foodLogs = dao.getLogsByDay(date);
        foodLogs.forEach(f -> {
            if(f.getFood().equals("test")) {
                testLog = f;
            }
        });
    }
}
