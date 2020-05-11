package com.example.userayofutsaljava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class LihatJadwal extends AppCompatActivity {
    public static DatabaseHelper db;
    private ListView listViewPemesanan;
    private List<Pemesanan> pemesanans;
    private PemesananAdapter pemesananAdapter;
    private Button buttonSync;

//    private BroadcastReceiver broadcastReceiver;

//    public static final String DATA_SAVED_BROADCAST = "net.vokasi.datasaved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_jadwal);

        db = new DatabaseHelper(this);
        pemesanans = new ArrayList<>();
        buttonSync = (Button) findViewById(R.id.buttonSync);
        listViewPemesanan = (ListView) findViewById(R.id.listViewPemesanan);

        loadPemesanans();
        sync();

//        broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                loadProducts();
//            }
//        };
//
//        registerReceiver(broadcastReceiver, new IntentFilter(DATA_SAVED_BROADCAST));

    }

    private void loadPemesanans(){
        pemesanans.clear();
        Cursor cursor = db.getAllPesanan();
        if (cursor.moveToFirst()){
            do {
                Pemesanan pemesanan = new Pemesanan(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_1)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_2)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_3)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_4)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_5)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_6)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_7)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_8))
                );
                pemesanans.add(pemesanan);
            } while (cursor.moveToNext());
        }

        pemesananAdapter= new PemesananAdapter(this, R.layout.pemesanan, pemesanans);
        listViewPemesanan.setAdapter(pemesananAdapter);
    }

    public void sync(){
        buttonSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiConnect(LihatJadwal.this).execute(ApiConnect.SYNC_ACTION_PEMESANAN+"");
                loadPemesanans();
                finish();
                startActivity(getIntent());
            }
        });
    }
}
