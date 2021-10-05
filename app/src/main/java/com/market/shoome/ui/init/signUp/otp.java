package com.market.shoome.ui.init.signUp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.market.shoome.ui.launch.Launch;
import com.market.shoome.utils.utils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class otp {

    private String otpId;
    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final Activity activity;
    private final Button verify;
    private final EditText otp;
    private final utils u = new utils();

    public otp(Activity activity, String number, EditText otp, Button Verify) {
        this.activity = activity;
        this.verify = Verify;
        this.otp = otp;
        sendOtp(number);
    }

    private void sendOtp(String number) {
        auth.useAppLanguage();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        phnCred(phoneAuthCredential);
                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                        super.onCodeAutoRetrievalTimeOut(s);
                    }

                    @Override
                    public void onCodeSent(@NonNull String s,
                                           @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpId = s;
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        u.snackbar(otp, e.getMessage());
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        verify.setOnClickListener(v -> {
            if (otp.getText().toString().trim().isEmpty() || otp.getText().toString().trim().length() != 6) {
                utils u = new utils();
                u.snackbar(otp, "Check OTP again");
            } else {
                PhoneAuthProvider.getCredential(otpId, otp.getText().toString());
            }
        });
    }

    private void phnCred(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                callPass();
            } else {
                u.snackbar(otp, Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    private void callPass() {
        Intent intent = new Intent("showPass");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
    }
}