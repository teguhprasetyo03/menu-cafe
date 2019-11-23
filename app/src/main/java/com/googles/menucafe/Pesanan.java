package com.googles.menucafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static java.lang.Integer.*;

public class Pesanan extends AppCompatActivity {
    String[] daftar;
    String[] daftar_2;
    ListView ListView01;
    protected Cursor cursor;
    DBHelper dbHelper;

    public static Pesanan ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        ma = this;
        dbHelper = new DBHelper(this);
        RefreshList();
    }


    public void RefreshList(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM db_pesanan",null);
        daftar = new String[cursor.getCount()];
        daftar_2 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString( 1 );
            daftar_2[cc] = cursor.getString( 2 );
        }
        ListView01 = (ListView)findViewById(R.id.ListView01 );
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar_2));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3)
            {
                final String selection = daftar_2[arg2];
                final String id_selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Pesanan", "Hapus Pesanan"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Pesanan.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatPesanan.class);
                                i.putExtra("no_meja", id_selection );
                                startActivity(i);
                                break;
                            case 1 :
                                SQLiteDatabase db = dbHelper.getReadableDatabase();
                                db.execSQL("DELETE FROM db_pesanan WHERE no_meja = '"+id_selection +"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
}
