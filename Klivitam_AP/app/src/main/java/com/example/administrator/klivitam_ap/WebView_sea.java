package com.example.administrator.klivitam_ap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.example.administrator.klivitam_ap.R.layout.webview;

/**
 * Created by Administrator on 2016/5/21.
 */
public class WebView_sea extends Activity {
    private WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(webview);
        web=(WebView) findViewById(R.id.web_view);

        Bundle bundle = this.getIntent().getExtras();;
        String Url=bundle.getString("URL");
        Log.i("标记", "地址是："+Url);
        web.loadUrl(Url);
        web.setWebViewClient(new WebViewClient());
    }
}
