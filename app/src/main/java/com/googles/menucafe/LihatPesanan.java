package com.googles.menucafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LihatPesanan extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper dbHelper;
    TextView nama, no_meja, tanggal, totalharga, pesanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lihat_pesanan );

        dbHelper = new DBHelper( this );

        nama = findViewById( R.id.d_nama );
        no_meja = findViewById( R.id.d_meja );
        tanggal = findViewById( R.id.d_tanggal );
        pesanan = findViewById( R.id.textView22 );
        totalharga = findViewById( R.id.d_harga );

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        cursor = db.rawQuery( "SELECT * FROM db_pesanan WHERE no_meja = '" + getIntent().getStringExtra( "no_meja" ) + "'", null );

        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition( 0 );
            no_meja.setText( cursor.getString( 1 ).toString() );
            nama.setText( cursor.getString( 3 ).toString() );
            tanggal.setText( cursor.getString( 4 ).toString() );
            totalharga.setText( cursor.getString( 5 ).toString() );
            pesanan.setText( cursor.getString( 7 ).toString() );
        }

    }

}
