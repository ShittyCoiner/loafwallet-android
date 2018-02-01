package com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by Fabian on 28.01.2018.
 */

public class QRActivity extends Activity {
//public static QRReader.OnQrFoundListener qrFoundListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        Log.d("resultxxx", "_"+scanResult);
        if (scanResult != null) {
         //   qrFoundListener.onDetected(scanResult.getContents());
            finish();
        }
        // else continue with any other code you need in the method

    }
}
