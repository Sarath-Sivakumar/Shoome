package com.market.shoome.utils;

import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class utils {

    public utils(){}
    public void snackbar(View v, String s){
        Log.e("Snackbar",s);
        Snackbar.make(v,s,Snackbar.LENGTH_SHORT).show();
    }
}
