package com.example.tom13.fooddemo.storage;

import com.example.tom13.fooddemo.foodLog.FoodLog;

import java.util.Date;
import java.util.List;

/**
 * Created by tom13 on 18/03/2018.
 */

public interface DAO {
    void addFoodLog(FoodLog foodLog);
    List<FoodLog> getLogsByDay(Date date);
    List<FoodLog> getLogsByWeek(Date date);
    List<FoodLog> getLogsByMonth(Date date);
    void deleteFoodLogs(List<FoodLog> foodLogs);
    void deleteDb();
}
