package com.example.tom13.fooddemo.storage;

import android.content.Context;

/**
 * Created by tom13 on 06/03/2018.
 * Factory class to provide extensibility in changed data access object implementation.
 */

public class DAOFactory {

    public DAO getDAO(Context context){
        return SqlLiteDAO.getInstance(context);
    }
}
