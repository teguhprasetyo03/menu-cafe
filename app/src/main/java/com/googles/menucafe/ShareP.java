package com.googles.menucafe;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareP {

    public static final String DATA_APP = "Shared_Prefences";

    SharedPreferences sp;
    SharedPreferences.Editor spEdit;

    public ShareP(Context context){
        sp = context.getSharedPreferences( DATA_APP, Context.MODE_PRIVATE );
        sp.edit();
    }

    public void saveUser(String data, String val) {
        spEdit.putString( data, val ).commit();
    }

    public String getData(String data){
        return sp.getString( data , "");
    }
}
