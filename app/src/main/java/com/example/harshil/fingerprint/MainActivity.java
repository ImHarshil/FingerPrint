package com.example.harshil.fingerprint;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.multidots.fingerprintauth.AuthErrorCodes;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FingerPrintAuthCallback
{
    private FingerPrintAuthHelper mFingerPrintAuthHelper;
    private android.widget.ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, (FingerPrintAuthCallback) this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mFingerPrintAuthHelper.stopAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {

    }

    @Override
    public void onNoFingerPrintRegistered() {

    }

    @Override
    public void onBelowMarshmallow() {

    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        finish();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        switch (errorCode) {    //Parse the error code for recoverable/non recoverable error.
            case AuthErrorCodes.CANNOT_RECOGNIZE_ERROR:
                Toast.makeText(getApplicationContext(),"unknown user",Toast.LENGTH_LONG).show();
                //Cannot recognize the fingerprint scanned.
                break;
            case AuthErrorCodes.NON_RECOVERABLE_ERROR:
                //This is not recoverable error. Try other options for user authentication. like pin, password.
                Toast.makeText(getApplicationContext(),"unknown user",Toast.LENGTH_LONG).show();

                break;
            case AuthErrorCodes.RECOVERABLE_ERROR:
                //Any recoverable error. Display message to the user.
                Toast.makeText(getApplicationContext(),"unknown user",Toast.LENGTH_LONG).show();

                break;
        }
    }
}
