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
    }

    @Override
    public List<FoodLog> getLogsByDay(Date date) {
        String query = "Select rowid, * FROM FoodLog WHERE Timestamp = '" + parseDate(date) + "';";

        return selectQuery(query);
    }

    @Override
    public List<FoodLog> getLogsByWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfWeek = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startOfWeek);
        calendar2.add(Calendar.WEEK_OF_MONTH, 1);
        Date endOfWeek = calendar2.getTime();

        String query = "Select rowid, * FROM FoodLog WHERE Timestamp BETWEEN '" + parseDate(startOfWeek) + "' AND '" + parseDate(endOfWeek) + "';";

        return selectQuery(query);
    }

    @Override
    public List<FoodLog> getLogsByMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        Date startOfMonth = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(startOfMonth);
        calendar2.add(Calendar.MONTH, 1);
        Date endOfMonth = calendar2.getTime();

        String query = "Select rowid, * FROM FoodLog WHERE Timestamp BETWEEN '" + parseDate(startOfMonth) + "' AND '" + parseDate(endOfMonth) + "';";

        return selectQuery(query);
    }

    private List<FoodLog> selectQuery(String query) {
        foodLogs = new ArrayList<>();
        Cursor resultSet = database.rawQuery(query, null);
        while(resultSet.moveToNext()) {
            try {
                foodLogs.add(new FoodLogImpl(resultSet.getInt(0), resultSet.getString(1), resultSet.getDouble(2), dateFormat.parse(resultSet.getString(3))));
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
        SQLiteStatement stmt = database.compileStatement("DELETE FROM FoodLog WHERE rowid = ?");
        for(FoodLog foodLog: foodLogs) {
            stmt.bindString(1, String.valueOf(foodLog.getId()));
            stmt.execute();
        }
    }

    @Override
    public void deleteDb() {

    }
}
