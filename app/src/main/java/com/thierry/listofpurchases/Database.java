package com.thierry.listofpurchases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ListOfPurchases";
    private static final int VERSION = 1;

    public Database(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    //mandatory method
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Products( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "category TEXT NOT NULL ) ");
    }

    //mandatory method
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
