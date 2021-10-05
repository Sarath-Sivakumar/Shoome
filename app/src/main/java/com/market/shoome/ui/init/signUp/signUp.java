package com.market.shoome.ui.init.signUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;
import com.market.shoome.R;
import com.market.shoome.db.childNames;
import com.market.shoome.utils.utils;

public class signUp extends AppCompatActivity {

    private ImageButton back;
    private CountryCodePicker ccp;
    private EditText phn, OTP, pass1, pass2;
    private Button getOtp, verify, confirm;
    private LinearLayout phone, otp, pass;
    private final phn checkPhn = new phn();
    private final utils utils = new utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }

    private void init(){
        back=findViewById(R.id.cBack);
        ccp = findViewById(R.id.ccp);
        phn = findViewById(R.id.phone);
        getOtp = findViewById(R.id.getOtp);
        phone = findViewById(R.id.phoneLayout);
        otp = findViewById(R.id.otpLayout);
        pass = findViewById(R.id.passwordLayout);
        phone.setVisibility(View.VISIBLE);
        otp.setVisibility(View.GONE);
        pass.setVisibility(View.GONE);
        setCCP();
        getOtp.setOnClickListener(v->getPhn());
        back.setOnClickListener(v->finish());

        verify = findViewById(R.id.verify);
        OTP = findViewById(R.id.otp);

        pass1 = findViewById(R.id.password);
        pass2 = findViewById(R.id.rePassword);
        confirm = findViewById(R.id.login);
    }

    private void setCCP(){
        ccp.detectLocaleCountry(true);
        ccp.detectNetworkCountry(true);
        ccp.detectSIMCountry(true);
        ccp.setAutoDetectedCountry(true);
        ccp.setDetectCountryWithAreaCode(true);
    }

    private void showOTP(){
        phone.setVisibility(View.GONE);
        otp.setVisibility(View.VISIBLE);
        pass.setVisibility(View.GONE);
    }

    private void showPass(){
        phone.setVisibility(View.GONE);
        otp.setVisibility(View.GONE);
        pass.setVisibility(View.VISIBLE);
    }

    private void getPhn(){
        if (!phn.getText().toString().trim().isEmpty()) {
            if (checkPhn.checkNumber(ccp.getSelectedCountryCode(), phn.getText().toString())) {
                showOTP();
                new otp(this, ccp.getSelectedCountryCodeWithPlus()+ phn.getText().toString(),
                        OTP, verify);
            }
        }else{
            utils.snackbar(phn,"Field is empty");
        }
    }

    private void setPass(){
        showPass();
        //add password to db
        confirm.setOnClickListener(v->DB());
    }

    private void DB(){
        if (pass1.getText().toString().equals(pass2.getText().toString())) {
            FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
            assert fUser != null;
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(childNames.users)
                    .child(fUser.getPhoneNumber()).child(childNames.password);
            ref.setValue(pass1);
        }else{
            utils.snackbar(confirm, "Passwords do not match.");
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setPass();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("showPass"));
    }
}