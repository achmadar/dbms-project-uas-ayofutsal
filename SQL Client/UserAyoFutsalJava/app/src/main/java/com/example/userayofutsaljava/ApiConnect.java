package com.example.userayofutsaljava;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiConnect extends AsyncTask<String,String,String> {
    private ProgressDialog pd;
    private Context context;
    private Pemesanan pemesanan;
    private Lapangan lapangan;
    private int action;

    public static final int INSERT_ACTION_PEMESANAN = 1;
    public static final int UPDATE_ACTION_PEMESANAN = 2;
    public static final int DELETE_ACTION_PEMESANAN = 3;
    public static final int VIEW_ACTION_PEMESANAN = 4;
    public static final int SYNC_ACTION_PEMESANAN = 5;
    public static final int SYNC_ACTION_LAPANGAN = 6;

    protected void onPreExecute(){
        super.onPreExecute();
        pd = new ProgressDialog(context);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
        this.action = Integer.parseInt(params[0]);
        switch (action){
//            case ApiConnect.VIEW_ACTION_PEMESANAN :
//                return readPemesananAPI();
//            case ApiConnect.UPDATE_ACTION_PEMESANAN :
//                return updatePemesananAPI();
//            case ApiConnect.DELETE_ACTION_PEMESANAN :
//                return deletePemesananAPI();
            case ApiConnect.INSERT_ACTION_PEMESANAN :
                return insertPemesananAPI();
            case ApiConnect.SYNC_ACTION_PEMESANAN :
                return syncPemesananAPI();
            case ApiConnect.SYNC_ACTION_LAPANGAN :
                return syncLapanganAPI();
            default:
                return "something is went wrong";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (pd.isShowing()){
            pd.dismiss();
        }
        switch (action){
            case ApiConnect.VIEW_ACTION_PEMESANAN:
                ((FormulirBooking)context).showMessage("Data",result);
                break;
            default:
                Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
    }

//    private String JSONDecoderPemesanan(String in){
//        try {
//            JSONObject reader = new JSONObject(in);
//            JSONArray records = reader.getJSONArray("records");
//            String result = "";
//            for(int i=0;i<records.length();i++){
//                JSONObject items = records.getJSONObject(i);
//                int id_pesanan = items.getInt("id_pesanan");
//                String nama = items.getString("nama");
//                String lapangan = items.getString("lapangan");
//                String mulai_jam = items.getString("mulai_jam");
//                String selesai_jam = items.getString("selesai_jam");
//                String tanggal = items.getString("tanggal");
//                String catatan = items.getString("catatan");
//                String status_bayar = items.getString("status_bayar");
//                result = result+
//                        "ID : "+id_pesanan+
//                        "\nNama : "+nama+
//                        "\nLapangan : "+lapangan+
//                        "\nJam : "+mulai_jam+" - "+selesai_jam+
//                        "\nTanggal : "+tanggal+
//                        "\nCatatan : "+catatan+
//                        "\n\n";
//            }
//            Log.d("result",result);
//            return result;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("result","NULL");
//            return null;
//        }
//    }

//    private String readPemesananAPI(){
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//        try {
//            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_pemesanan/read.php");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//            InputStream stream = connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(stream));
//            StringBuffer buffer = new StringBuffer();
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line+"\n");
//                Log.d("Response: ", "> " + line);
//            }
//            return JSONDecoderPemesanan(buffer.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    private String insertPemesananAPI(){
        HttpURLConnection connection = null;
        if (pemesanan == null){
            return "Isi semua inputan !";
        }
        try{
            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_pemesanan/create.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setDoOutput(true);
            String jsonString = pemesanan.getJsonString();
            OutputStream os = connection.getOutputStream();
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Log.d("response",response.toString());
            return response.toString();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

//    private String updatePemesananAPI(){
//        HttpURLConnection connection = null;
//        if (pemesanan == null){
//            return "product is null";
//        }
//        try{
//            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_pemesanan/update.php");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setDoOutput(true);
//            String jsonString = pemesanan.getJsonString();
//            OutputStream os = connection.getOutputStream();
//            byte[] input = jsonString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            Log.d("response",response.toString());
//            return response.toString();
//
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

//    private String deletePemesananAPI(){
//        HttpURLConnection connection = null;
//        if (pemesanan == null){
//            return "product is null";
//        }
//        try{
//            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_pemesanan/delete.php");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setDoOutput(true);
//            String jsonString = pemesanan.getJsonString();
//            OutputStream os = connection.getOutputStream();
//            byte[] input = jsonString.getBytes("utf-8");
//            os.write(input, 0, input.length);
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            Log.d("response",response.toString());
//            return response.toString();
//
//        }catch (MalformedURLException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    // Pemesanan Tabel

    private String JSONDecoderPemesananSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            LihatJadwal.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_pesanan = items.getInt("id_pesanan");
                String nama = items.getString("nama");
                String lapangan = items.getString("lapangan");
                String mulai_jam = items.getString("mulai_jam");
                String selesai_jam = items.getString("selesai_jam");
                String tanggal = items.getString("tanggal");
                String catatan = items.getString("catatan");
                String status_bayar = items.getString("status_bayar");

                boolean isSynced = LihatJadwal.db.insertPesanan(id_pesanan, nama, lapangan, mulai_jam,
                        selesai_jam, tanggal, catatan, status_bayar);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("result","NULL");
            return null;
        }
    }

    private String syncPemesananAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_pemesanan/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderPemesananSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Lapangan Tabel

    private String JSONDecoderLapanganSync(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONArray records = reader.getJSONArray("records");
            String result = "";
            MenuLapangan.db.emptyData();
            for(int i=0;i<records.length();i++){
                JSONObject items = records.getJSONObject(i);
                int id_lap = items.getInt("id_lap");
                String name = items.getString("name");
                String status = items.getString("status");
                boolean isSynced = MenuLapangan.db.insertLapangan(id_lap, name, status);
                if(isSynced == true)
                    result = "Data Synced";
                else
                    result = "Data Not Synced";
            }
            Log.d("result",result);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("result","NULL");
            return null;
        }
    }

    private String syncLapanganAPI(){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://192.168.1.3/SQL%20Server/API/tbl_lapangan/read.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Response: ", "> " + line);
            }
            return JSONDecoderLapanganSync(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ApiConnect(Context context){
        this.context = context;
    }

    public ApiConnect(Context context,Pemesanan pemesanan){
        this.context = context;
        this.pemesanan = pemesanan;
    }

    public ApiConnect(Context context,Lapangan lapangan){
        this.context = context;
        this.lapangan = lapangan;
    }

}
