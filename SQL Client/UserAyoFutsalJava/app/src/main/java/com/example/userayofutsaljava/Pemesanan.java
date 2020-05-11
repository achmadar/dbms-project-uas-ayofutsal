package com.example.userayofutsaljava;

import org.json.JSONException;
import org.json.JSONObject;

public class Pemesanan {
    private int id_pesanan;
    private String nama;
    private String lapangan;
    private String mulai_jam;
    private String selesai_jam;
    private String tanggal;
    private String catatan;
    private String status_bayar;
    private int type;
    public static final int INSERT_TYPE=1;
    public static final int UPDATE_TYPE=2;
    public static final int DELETE_TYPE=3;

    public static Pemesanan generateInsertObject(String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan)
    {
        return new Pemesanan (-1, nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, "menunggu", Pemesanan.INSERT_TYPE);
    }

    public static Pemesanan generateUpdateObject(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan)
    {
        return new Pemesanan (id_pesanan, nama, lapangan, mulai_jam, selesai_jam, tanggal, catatan, "menunggu", Pemesanan.UPDATE_TYPE);
    }

    public static Pemesanan generateDeleteObject(int id_pesanan){
        return new Pemesanan(id_pesanan, null, null, null, null,
                null, null, null, Pemesanan.DELETE_TYPE);
    }

    public JSONObject getJsonProduct(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("status_bayar","menunggu");
            switch (type) {
                case Pemesanan.INSERT_TYPE:
                    obj.put("nama", this.nama);
                    obj.put("lapangan", this.lapangan);
                    obj.put("mulai_jam", this.mulai_jam);
                    obj.put("selesai_jam", this.selesai_jam);
                    obj.put("tanggal", this.tanggal);
                    obj.put("catatan", this.catatan);
                    break;
                case Pemesanan.UPDATE_TYPE:
                    obj.put("id_pesanan", this.id_pesanan);
                    obj.put("nama", this.nama);
                    obj.put("lapangan", this.lapangan);
                    obj.put("mulai_jam", this.mulai_jam);
                    obj.put("selesai_jam", this.selesai_jam);
                    obj.put("tanggal", this.tanggal);
                    obj.put("catatan", this.catatan);
                    obj.put("status bayar", "lunas");
                    break;
                case Pemesanan.DELETE_TYPE:
                    obj.put("id_pesanan",this.id_pesanan);
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    public String getJsonString() {
        return getJsonProduct().toString();
    }

    // Untuk sqlite (tanpa type)
    public Pemesanan(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar){
        this.id_pesanan = id_pesanan;
        this.nama = nama;
        this.lapangan = lapangan;
        this.mulai_jam = mulai_jam;
        this.selesai_jam = selesai_jam;
        this.tanggal = tanggal;
        this.catatan = catatan;
        this.status_bayar = status_bayar;
    }

    // Untuk generate object dan server (dengan type)
    public Pemesanan(int id_pesanan, String nama, String lapangan, String mulai_jam, String selesai_jam, String tanggal, String catatan, String status_bayar, int type) {
        this.id_pesanan = id_pesanan;
        this.nama = nama;
        this.lapangan = lapangan;
        this.mulai_jam = mulai_jam;
        this.selesai_jam = selesai_jam;
        this.tanggal = tanggal;
        this.catatan = catatan;
        this.status_bayar = status_bayar;
        this.type = type;
    }

    public int getId_pesanan() {
        return id_pesanan;
    }

    public String getNama() {
        return nama;
    }

    public String getLapangan() {
        return lapangan;
    }

    public String getMulai_jam() {
        return mulai_jam;
    }

    public String getSelesai_jam() {
        return selesai_jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getCatatan() {
        return catatan;
    }

    public int getType() {
        return type;
    }


}
