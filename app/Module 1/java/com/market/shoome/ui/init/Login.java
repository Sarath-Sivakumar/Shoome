package com.market.shoome.ui.init;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.hbb20.CountryCodePicker;
import com.market.shoome.R;

public class Login extends AppCompatActivity {

    private CountryCodePicker ccp;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);
        init();
    }

    private void init(){
        back = findViewById(R.id.back);
        back.setOnClickListener(v->finish());
    }
}