package com.example.tom13.fooddemo.storage;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;

/**
 * Created by tom13 on 06/03/2018.
 */

public class DAOFactory {

    public DAO getDAO(Context context) throws CouchbaseLiteException {
        return CouchbaseDAO.getInstance(context);
    }
}
