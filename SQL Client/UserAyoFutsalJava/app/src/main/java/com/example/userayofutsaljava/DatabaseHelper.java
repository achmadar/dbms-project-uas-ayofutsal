package com.example.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_ayofutsal";
    public static final String TABLE_NAME = "tbl_pemesanan";
    public static final String COL_1 = "username";
    public static final String COL_2 = "lapangan";
    public static final String COL_3 = "mulai_jam";
    public static final String COL_4 = "selesai_jam";
    public static final String COL_5 = "tanggal";
    public static final String COL_6 = "catatan";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (username TEXT PRIMARY KEY,lapangan INTEGER,mulai_jam TEXT,selesai_jam TEXT,tanggal TEXT,catatan TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username,int lapangan,String mulai_jam,String selesai_jam,String tanggal,String catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,lapangan);
        contentValues.put(COL_3,mulai_jam);
        contentValues.put(COL_4,selesai_jam);
        contentValues.put(COL_5,tanggal);
        contentValues.put(COL_6,catatan);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String username,int lapangan,String mulai_jam,String selesai_jam,String tanggal,String catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,lapangan);
        contentValues.put(COL_3,mulai_jam);
        contentValues.put(COL_4,selesai_jam);
        contentValues.put(COL_5,tanggal);
        contentValues.put(COL_6,catatan);
        int result = db.update(TABLE_NAME, contentValues, "username = ?",new String[] { username });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,"username = ?" ,new String[] { username });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}