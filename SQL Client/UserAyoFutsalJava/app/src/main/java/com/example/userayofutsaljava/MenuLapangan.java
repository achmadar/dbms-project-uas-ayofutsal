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

public class MenuLapangan extends AppCompatActivity {
    public static DatabaseHelper db;
    private ListView listViewLapangan;
    private List<Lapangan> lapangans;
    private LapanganAdapter lapanganAdapter;
    private Button buttonSync2;

//    private BroadcastReceiver broadcastReceiver;

//    public static final String DATA_SAVED_BROADCAST = "net.vokasi.datasaved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lapangan);

        db = new DatabaseHelper(this);
        lapangans = new ArrayList<>();
        buttonSync2 = (Button) findViewById(R.id.buttonSync2);
        listViewLapangan = (ListView) findViewById(R.id.listViewLapangan);

        loadLapangans();
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

    private void loadLapangans(){
        lapangans.clear();
        Cursor cursor = db.getAllLapangan();
        if (cursor.moveToFirst()){
            do {
                Lapangan lapangan = new Lapangan(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_9)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_10)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_11))
                );
                lapangans.add(lapangan);
            } while (cursor.moveToNext());
        }

        lapanganAdapter= new LapanganAdapter(this, R.layout.lapangan, lapangans);
        listViewLapangan.setAdapter(lapanganAdapter);
    }

    public void sync(){
        buttonSync2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiConnect(MenuLapangan.this).execute(ApiConnect.SYNC_ACTION_LAPANGAN+"");
                loadLapangans();
                finish();
                startActivity(getIntent());
            }
        });
    }
}
