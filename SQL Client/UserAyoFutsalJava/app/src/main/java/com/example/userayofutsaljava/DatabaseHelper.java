package com.example.userayofutsaljava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ayofutsal.db";
    public static final String TABLE_NAME_PEMESANAN = "tbl_pemesanan";
    public static final String TABLE_NAME_LAPANGAN = "tbl_lapangan";
    public static final String COL_1 = "id_pesanan";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "lapangan";
    public static final String COL_4 = "mulai_jam";
    public static final String COL_5 = "selesai_jam";
    public static final String COL_6 = "tanggal";
    public static final String COL_7 = "catatan";
    public static final String COL_8 = "status_bayar";
    public static final String COL_9 = "id_lap";
    public static final String COL_10 = "name";
    public static final String COL_11 = "status";

    public DatabaseHelper(LihatJadwal context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DatabaseHelper(MenuLapangan context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME_PEMESANAN +"(" +
                "id_pesanan INTEGER, nama TEXT, lapangan TEXT, mulai_jam TEXT, selesai_jam TEXT, " +
                "tanggal TEXT, catatan TEXT, status_bayar TEXT)");

        db.execSQL("create table " + TABLE_NAME_LAPANGAN +"(id_lap INTEGER, name TEXT, status TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PEMESANAN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_LAPANGAN);
        onCreate(db);
    }

    public void emptyData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_PEMESANAN);
        db.execSQL("DELETE FROM " + TABLE_NAME_LAPANGAN);
    }

    // ----- SQLite Pesanan

    public boolean insertPesanan(int id_pesanan, String nama, String lapangan, String mulai_jam,
                              String selesai_jam, String tanggal,  String catatan, String status_bayar)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id_pesanan);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,lapangan);
        contentValues.put(COL_4,mulai_jam);
        contentValues.put(COL_5,selesai_jam);
        contentValues.put(COL_6,tanggal);
        contentValues.put(COL_7,catatan);
        contentValues.put(COL_8,status_bayar);
        long result = db.insert(TABLE_NAME_PEMESANAN,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updatePesanan(String id_pesanan, String nama, String lapangan, String mulai_jam,
                                 String selesai_jam, String tanggal,  String catatan, String status_bayar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id_pesanan);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,lapangan);
        contentValues.put(COL_4,mulai_jam);
        contentValues.put(COL_5,selesai_jam);
        contentValues.put(COL_6,tanggal);
        contentValues.put(COL_7,catatan);
        contentValues.put(COL_8,status_bayar);
        int result = db.update(TABLE_NAME_PEMESANAN, contentValues, "id_pesanan = ?",new String[] { id_pesanan });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deletePesanan(String id_pesanan) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME_PEMESANAN,"id_pesanan = ?" ,new String[] { id_pesanan });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllPesanan() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_PEMESANAN,null);
        return res;
    }

    // ---- SQLite Lapangan

    public boolean insertLapangan(int id_lap, String name, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,id_lap);
        contentValues.put(COL_10,name);
        contentValues.put(COL_11,status);
        long result = db.insert(TABLE_NAME_LAPANGAN,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateLapangan(String id_lap, String name, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_9,id_lap);
        contentValues.put(COL_10,name);
        contentValues.put(COL_11,status);
        int result = db.update(TABLE_NAME_LAPANGAN, contentValues, "id_lap = ?",new String[] { id_lap });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteLapangan(String id_lap) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME_LAPANGAN,"id_lap = ?" ,new String[] { id_lap });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllLapangan() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LAPANGAN,null);
        return res;
    }

}
