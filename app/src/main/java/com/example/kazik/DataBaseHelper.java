package com.example.kazik;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "money.db";
    public static final String TABLE_USERS = "Money";
    private static final int SCHEME = 1;

    public static final String  COLUMN_MONEY = "money";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEME);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" + COLUMN_MONEY + " INTEGER);");
        this.db = db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }

    public void setMoney(SQLiteDatabase db, int money){

        ContentValues values = new ContentValues();
        values.put(COLUMN_MONEY, money);
        db.insert(TABLE_USERS, null, values);
//        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_MONEY + ") VALUES (" + money + ");");
    }
    public int getMoney() {
        int sum = 0;
        if (db != null) {
            String query = "SELECT SUM(money) FROM " + TABLE_USERS;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                sum = cursor.getInt(0);
            }
            cursor.close();
        }
        return sum;
    }
}
