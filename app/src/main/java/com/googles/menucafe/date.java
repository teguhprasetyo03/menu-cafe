package com.googles.menucafe;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class date {
    public String tanggal() {
        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat( "d/M/yyyy h:m:s a" );
        String strdate1 = sdf1.format( c1.getTime() );

        return strdate1;
    }
}
