package com.example.userayofutsaljava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormulirBooking extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText inpNama, inpLapangan, inpMulai, inpSelesai, inpTanggal, inpCatatan;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_booking);

//        myDb = new DatabaseHelper(this);
        inpNama = (EditText)findViewById(R.id.inpNama);
        inpLapangan = (EditText)findViewById(R.id.inpLapangan);
        inpMulai = (EditText)findViewById(R.id.inpMulai);
        inpSelesai = (EditText)findViewById(R.id.inpSelesai);
        inpTanggal = (EditText)findViewById(R.id.inpTanggal);
        inpCatatan = (EditText)findViewById(R.id.inpCatatan);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        addAction();

    }

    public void showMessage(String title,String Message){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void addAction(){
        btnAdd.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pemesanan pemesanan = Pemesanan.generateInsertObject(
                            inpNama.getText().toString(),
                            inpLapangan.getText().toString(),
                            inpMulai.getText().toString(),
                            inpSelesai.getText().toString(),
                            inpTanggal.getText().toString(),
                            inpCatatan.getText().toString()
                    );
                    new ApiConnect(FormulirBooking.this,pemesanan).execute(ApiConnect.INSERT_ACTION_PEMESANAN+"");
                    Intent back = new Intent(FormulirBooking.this, MainActivity.class);
                    startActivity(back);
                }
            }
        );

    }

}
