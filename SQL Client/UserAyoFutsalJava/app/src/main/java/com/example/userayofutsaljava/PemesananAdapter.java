package com.example.userayofutsaljava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PemesananAdapter extends ArrayAdapter<Pemesanan> {
    private List<Pemesanan> pemesanans;
    private Context context;

    public PemesananAdapter(Context context, int resource, List<Pemesanan> pemesanans){
        super(context,resource,pemesanans);
        this.context = context;
        this.pemesanans = pemesanans;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.pemesanan,null,true);
        TextView prdId = (TextView) listViewItem.findViewById(R.id.prdId);
        TextView prdNama = (TextView) listViewItem.findViewById(R.id.prdNama);
        TextView prdLapangan = (TextView) listViewItem.findViewById(R.id.prdLapangan);
        TextView prdJam = (TextView) listViewItem.findViewById(R.id.prdJam);
        TextView prdTanggal = (TextView) listViewItem.findViewById(R.id.prdTanggal);
        TextView prdCatatan = (TextView) listViewItem.findViewById(R.id.prdCatatan);

        Pemesanan pemesanan = pemesanans.get(position);

        prdId.setText("ID " + Integer.toString(pemesanan.getId_pesanan()));
        prdNama.setText("Nama : " + pemesanan.getNama());
        prdLapangan.setText("Lapangan : " + pemesanan.getLapangan());
        prdJam.setText("Jam : " + pemesanan.getMulai_jam() + " - " + pemesanan.getSelesai_jam());
        prdTanggal.setText("Tanggal : " + pemesanan.getTanggal());
        prdCatatan.setText("Catatan : " + pemesanan.getCatatan());

        return listViewItem;

    }

}

