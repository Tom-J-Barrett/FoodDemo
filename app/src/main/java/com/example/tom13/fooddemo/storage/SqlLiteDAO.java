package com.example.tom13.fooddemo.storage;

/**
 * Created by tom13 on 06/03/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.tom13.fooddemo.foodLog.FoodLog;
import com.example.tom13.fooddemo.foodLog.FoodLogImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Singleton implementation
 */

public class SqlLiteDAO implements DAO{

    private final String dbName = "foodLogDb";
    private static SqlLiteDAO instance = null;
    private List<FoodLog> foodLogs = null;
    private SQLiteDatabase database;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault());

    protected SqlLiteDAO(Context context) {
        database = context.openOrCreateDatabase("foodLogDb", MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS FoodLog(Food TEXT, Calories REAL, Timestamp TEXT);");
    }

    public static DAO getInstance(Context context) {
        if(instance == null) {
            instance = new SqlLiteDAO(context);
        }
        return instance;
    }

    public void addFoodLog(FoodLog foodLog) {
        SQLiteStatement stmt = database.compileStatement("INSERT INTO FoodLog VALUES (?, ?, ?)");
        stmt.bindString(1, foodLog.getFood());
        stmt.bindString(2, String.valueOf(foodLog.getCalories()));
        stmt.bindString(3, parseDate(foodLog.getTimestamp()));
        stmt.execute();
        System.out.println("HI");
    }

    @Override
    public List<FoodLog> getLogsByDay(Date date) {
        String query = "Select * FROM FoodLog WHERE Timestamp = '" + parseDate(date) + "';";

        return selectQuery(query);
    }

    @Override
    public List<FoodLog> getLogsByWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date newDate = calendar.getTime();

        String query = "Select * FROM FoodLog WHERE Timestamp BETWEEN '" + parseDate(date) + "' AND '" + parseDate(newDate) + "';";

        return selectQuery(query);
    }

    @Override
    public List<FoodLog> getLogsByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        Date newDate = calendar.getTime();

        String query = "Select * FROM FoodLog WHERE Timestamp BETWEEN '" + parseDate(date) + "' AND '" + parseDate(newDate) + "';";

        return selectQuery(query);
    }

    private List<FoodLog> selectQuery(String query) {
        foodLogs = new ArrayList<>();
        Cursor resultSet = database.rawQuery(query, null);
        while(resultSet.moveToNext()) {
            try {
                System.out.println("BYE");
                foodLogs.add(new FoodLogImpl(resultSet.getString(0), resultSet.getDouble(1), dateFormat.parse(resultSet.getString(2))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return foodLogs;
    }

    private String parseDate(Date date) {
        return dateFormat.format(date);
    }

    @Override
    public void deleteFoodLogs(List<FoodLog> foodLogs) {

    }

    @Override
    public void deleteDb() {

    }
}
