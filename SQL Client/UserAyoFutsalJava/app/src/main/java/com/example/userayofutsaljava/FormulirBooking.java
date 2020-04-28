package com.example.userayofutsaljava;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormulirBooking extends AppCompatActivity {

    com.example.sqlitetest.DatabaseHelper myDb;
    EditText inpUsername, inpLapangan, inpMulai, inpSelesai, inpTanggal, inpCatatan;
    Button btnAdd;
    Button btnEdit;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_booking);
        myDb = new com.example.sqlitetest.DatabaseHelper(this);
        inpUsername = (EditText)findViewById(R.id.inpUsername);
        inpLapangan = (EditText)findViewById(R.id.inpLapangan);
        inpMulai = (EditText)findViewById(R.id.inpMulai);
        inpSelesai = (EditText)findViewById(R.id.inpSelesai);
        inpTanggal = (EditText)findViewById(R.id.inpTanggal);
        inpCatatan = (EditText)findViewById(R.id.inpCatatan);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnView= (Button)findViewById(R.id.btnView);

        addAction();
        editAction();
        showAllAction();
    }

    public void addAction() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                inpUsername.getText().toString(),
                                Integer.parseInt(inpLapangan.getText().toString()),
                                inpMulai.getText().toString(),
                                inpSelesai.getText().toString(),
                                inpTanggal.getText().toString(),
                                inpCatatan.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(FormulirBooking.this,
                                    "Pesanan Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(FormulirBooking.this,
                                    "Pesanan Gagal Ditambahkan",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void editAction() {
        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(
                                inpUsername.getText().toString(),
                                Integer.parseInt(inpLapangan.getText().toString()),
                                inpMulai.getText().toString(),
                                inpSelesai.getText().toString(),
                                inpTanggal.getText().toString(),
                                inpCatatan.getText().toString() );
                        if(isUpdated == true)
                            Toast.makeText(FormulirBooking.this,
                                    "Pesanan Berhasil Diedit",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(FormulirBooking.this,
                                    "Pesanan Gagal Diedit",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void showAllAction() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Username : "+ res.getString(0)+"\n");
                            buffer.append("Lapangan : "+ res.getInt(1)+"\n");
                            buffer.append("Mulai Jam : "+ res.getString(2)+"\n");
                            buffer.append("Selesai Jam : "+ res.getString(3)+"\n");
                            buffer.append("Tanggal : "+ res.getString(4)+"\n");
                            buffer.append("Catatan : "+ res.getString(5)+"\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

}
