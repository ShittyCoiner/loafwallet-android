package com.breadwallet.presenter.activities.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import com.breadwallet.R;
import com.breadwallet.presenter.activities.util.BRActivity;
import com.breadwallet.tools.security.BitcoinUrlHandler;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.platform.tools.BRBitId;


/**
 * BreadWallet
 * <p/>
 * Created by Mihail Gutan on <mihail@breadwallet.com> 3/29/17.
 * Copyright (c) 2017 breadwallet LLC
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p/>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public class ScanQRActivity extends BRActivity {
    private SurfaceView mySurfaceView;

    private static final String TAG = ScanQRActivity.class.getName();
    //    private boolean dataProcessing = false;
    private ImageView cameraGuide;
    private TextView descriptionText;
    private long lastUpdated;

    private boolean handlingCode;
    public static boolean appVisible = false;
    private static ScanQRActivity app;

    public static ScanQRActivity getApp() {
        return app;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();




    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        Log.d("resultxxx", "_"+scanResult);
        if (scanResult != null && scanResult.getContents() != null) {
            String data = scanResult.getContents();
            if (BitcoinUrlHandler.isBitcoinUrl(data) || BRBitId.isBitId(data)) {
                handleDetected(data);
            }
        }else{
          //  handleDetected(" ");
        }
        finish();
        // else continue with any other code you need in the method

    }



    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.fade_down, 0);
        super.onBackPressed();
    }

    private void handleDetected(String data) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", data);
        setResult(Activity.RESULT_OK, returnIntent);

    }


}