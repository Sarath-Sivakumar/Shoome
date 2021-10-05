package com.market.shoome.ui.init;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.market.shoome.R;

public class signUp extends AppCompatActivity {

    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init(){
        back=findViewById(R.id.cBack);
        back.setOnClickListener(v->finish());
    }
}