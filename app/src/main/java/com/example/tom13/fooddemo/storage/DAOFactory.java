package com.example.tom13.fooddemo.storage;

import android.content.Context;

/**
 * Created by tom13 on 06/03/2018.
 */

public class DAOFactory {

    public DAO getDAO(Context context){
        return SqlLiteDAO.getInstance(context);
    }
}
