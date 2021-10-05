package com.market.shoome.ui.init.signUp;

public class phn {

    public phn(){}

    public boolean checkNumber(String code, String phn){
        return checkValidity(code+phn);
    }

    private boolean checkValidity(String phn){
        return phn.length() <= 15 && phn.length() >= 4;
    }
}