package com.googles.menucafe;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final int VERSION          = 1;

    public DBHelper(Context context) {
        super( context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE db_pesanan(id integer primary key autoincrement, no_meja int not null,no_urut text not null , nama text not null, tanggal datetime not null,harga integer not null, totalharga integer not null, pesanan text not null ,menu_1 int not null,menu_2 int not null,menu_3 int not null,menu_4 int not null,menu_5 int not null,menu_6 int not null,menu_7 int not null,menu_8 int not null);";

        Log.d("DATABASE_CREATE", "onCreate"+ sql);
        db.execSQL( sql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO-Auto Generated
    }
}

