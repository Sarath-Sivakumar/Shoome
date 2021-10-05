package com.market.shoome.ui.launch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.market.shoome.R;
import com.market.shoome.ui.init.initOptions;
import com.market.shoome.ui.main.homeActivity;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        //String uDevice = Build.MANUFACTURER + Build.DEVICE + Build.FINGERPRINT;

        Thread t = new Thread(() -> {
            try {
                sleep(2 * 1000);

                if (fUser != null) {

                } else {
                    startActivity(new Intent(this, initOptions.class));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}