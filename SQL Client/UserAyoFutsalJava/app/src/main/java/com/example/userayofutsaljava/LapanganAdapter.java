package com.example.userayofutsaljava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LapanganAdapter extends ArrayAdapter<Lapangan> {
    private List<Lapangan> lapangans;
    private Context context;

    public LapanganAdapter(Context context, int resource, List<Lapangan> lapangans){
        super(context,resource,lapangans);
        this.context = context;
        this.lapangans = lapangans;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listViewItem = inflater.inflate(R.layout.lapangan,null,true);
        TextView lpgId = (TextView) listViewItem.findViewById(R.id.lpgId);
        TextView lpgName = (TextView) listViewItem.findViewById(R.id.lpgName);
        TextView lpgStatus = (TextView) listViewItem.findViewById(R.id.lpgStatus);

        Lapangan lapangan = lapangans.get(position);

        lpgId.setText("ID : " + Integer.toString(lapangan.getId_lap()));
        lpgName.setText("Nama Lapangan :\n- " + lapangan.getName());
        lpgStatus.setText("Status :\n- " + lapangan.getStatus());

        return listViewItem;

    }

}

