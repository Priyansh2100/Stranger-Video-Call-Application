package com.example.strangers.models;

import android.webkit.JavascriptInterface;

import com.example.strangers.activities.CallActivity;

public class InterfaceJava {

    CallActivity callActivity;    // peer connecte  hoga vaise he hame notijfication milegi

    public InterfaceJava(CallActivity callActivity) {
        this.callActivity = callActivity;
    }

    @JavascriptInterface
    public void onPeerConnected(){
        callActivity.onPeerConnected();
    }

}