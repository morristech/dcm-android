package com.ucb.dcm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.androiddata.DBObject;

import java.util.ArrayList;

/**
 * Created by kurtguenther on 6/6/13.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "DCM.db";

    //private static final String TAG = "LocalDBHelper";

    // Make a singleton
    private static DBHelper mSharedService;

    public static DBHelper getSharedService() {
        return mSharedService;
    }

    public static void Initialize(Context context) {
        mSharedService = new DBHelper(context);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        for(DBObject table : getTables()){
            db.execSQL(table.getCreateScript());
        }
    }

    public DBObject[] getTables(){
        return new DBObject[]{new Show(), new Venue(), new Performance()};
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.dropDatabase(db);
        onCreate(db);
    }

    public void dropDatabase(SQLiteDatabase db) {
        for(DBObject table : getTables()){
            db.execSQL(table.getDropScript());
        }
    }

}
