package com.example.tom13.fooddemo.storage;

/**
 * Created by tom13 on 06/03/2018.
 */

import android.content.Context;
import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.DataSource;
import com.couchbase.lite.Database;
import com.couchbase.lite.DatabaseConfiguration;
import com.couchbase.lite.Dictionary;
import com.couchbase.lite.Expression;
import com.couchbase.lite.MutableDocument;
import com.couchbase.lite.Query;
import com.couchbase.lite.QueryBuilder;
import com.couchbase.lite.Result;
import com.couchbase.lite.ResultSet;
import com.couchbase.lite.SelectResult;
import com.example.tom13.fooddemo.foodLog.FoodLog;

import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Singleton implementation
 */

public class CouchbaseDAO implements DAO{

    private Database database;
    private final String dbName = "foodLogDb";
    private static CouchbaseDAO instance = null;
    private Context context;

    protected CouchbaseDAO(Context context) throws CouchbaseLiteException {
        DatabaseConfiguration config = new DatabaseConfiguration(context);
        database = new Database(dbName, config);
    }

    public static DAO getInstance(Context context) throws CouchbaseLiteException {
        if(instance == null) {
            instance = new CouchbaseDAO(context);
        }
        return instance;
    }

    public void addFoodLog(FoodLog foodLog) {
        MutableDocument newTask = new MutableDocument();
        newTask.setString("food", foodLog.getFood());
        newTask.setString("calories", Double.toString(foodLog.getCalories()));
        newTask.setDate("timestamp", foodLog.getTimestamp());

        try {
            database.save(newTask);
        } catch (CouchbaseLiteException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public List<FoodLog> getLogsByDay(Date date) {
        return null;
    }

    @Override
    public List<FoodLog> getLogsByWeek(Date date) {
        return null;
    }

    @Override
    public List<FoodLog> getLogsByMonth(Date date) {
        return null;
    }

    public List<FoodLog> queryDb() throws CouchbaseLiteException {
        Query query = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.database(database))
                .where(Expression.property("food").equalTo(Expression.string("banana")))
                .limit(Expression.intValue(10));
        ResultSet rs = query.execute();
        for (Result result : rs) {
            Dictionary all = result.getDictionary(dbName);
            Log.i("Sample", String.format("food -> %s", all.getString("food")));
        }
        return null;
    }

    @Override
    public void deleteFoodLogs(List<FoodLog> foodLogs) {

    }

    @Override
    public void deleteDb() {

    }
}
