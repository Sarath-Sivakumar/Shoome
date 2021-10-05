package com.market.shoome.ui.init;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.market.shoome.R;
import com.market.shoome.ui.init.login.Login;
import com.market.shoome.ui.init.signUp.signUp;

public class initOptions extends AppCompatActivity {

    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_options_activity);
        init();
    }

    private void init(){
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        login.setOnClickListener(v->startActivity(new Intent(this, Login.class)));
        signup.setOnClickListener(v->startActivity(new Intent(this, signUp.class)));
    }
}