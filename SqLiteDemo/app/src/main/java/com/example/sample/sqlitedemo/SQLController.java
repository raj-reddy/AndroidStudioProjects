package com.example.sample.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class SQLController {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SQLController(Context context) {
        this.context = context;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return  this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, name);
        contentValues.put(dbHelper.JOB, job);
        database.insert(dbHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBHelper.ID, DBHelper.NAME, DBHelper.JOB };
        Cursor cursor = database.query(DBHelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String name, String job) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, name);
        contentValues.put(DBHelper.JOB, job);
        int i = database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper.ID + " = " + id, null);
        return i;
    }

    public void delete(long id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper.ID + "=" + id, null);
    }
}
