package com.example.userayofutsaljava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btn_jadwal;
    public Button btn_Carapesan;
    public Button btn_MenuLapangan;
    public Button btn_booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_jadwal = (Button)findViewById(R.id.btn_jadwal);
        btn_jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_jadwal = new Intent(MainActivity.this, LihatJadwal.class);
                startActivity(btn_jadwal);
            }
        });

        Button btn_carapesan = (Button)findViewById(R.id.btn_Carapesan);
        btn_carapesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_carapesan = new Intent(MainActivity.this, CaraPesan.class);
                startActivity(btn_carapesan);
            }
        });

        Button btn_MenuLapangan = (Button)findViewById(R.id.btn_MenuLapangan);
        btn_MenuLapangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_MenuLapangan = new Intent(MainActivity.this, MenuLapangan.class);
                startActivity(btn_MenuLapangan);
            }
        });

        Button btn_booking = (Button)findViewById(R.id.btn_booking);
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_booking = new Intent(MainActivity.this, FormulirBooking.class);
                startActivity(btn_booking);
            }
        });

        Button btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btn_edit = new Intent(MainActivity.this, EditUser.class);
                startActivity(btn_edit);
            }
        });

    }

}
