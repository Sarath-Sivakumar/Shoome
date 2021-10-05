package com.market.shoome.ui.init.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.hbb20.CountryCodePicker;
import com.market.shoome.R;

public class Login extends AppCompatActivity {

    private CountryCodePicker ccp;
    private ImageButton back;
    private LinearLayout phn, otp, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);
        init();
        setCCP();
    }

    private void init(){
        back = findViewById(R.id.back);
        ccp = findViewById(R.id.ccp);
        back.setOnClickListener(v->finish());
    }

    private void setCCP(){
        ccp.detectLocaleCountry(true);
        ccp.detectNetworkCountry(true);
        ccp.detectSIMCountry(true);
        ccp.setAutoDetectedCountry(true);
        ccp.setDetectCountryWithAreaCode(true);
    }

    private void login(){


    }
}