package com.accengage.samples.jsweb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.webview);
        //myWebView.loadUrl("file:///android_asset/index.html");
        myWebView.loadUrl("file:///android_asset/mobile.html");

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
            }
        });
    }

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(android.os.Message message) {
            myWebView.setVisibility(View.GONE);
            mButton.setVisibility(View.VISIBLE);
        }
    };

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void closeInApp() {
            Message messageForHandler = mHandler.obtainMessage();
            messageForHandler.sendToTarget();
        }

        @JavascriptInterface
        public void openSettings() {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }

        @JavascriptInterface
        public void openCamera() {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
        }
    }
}
