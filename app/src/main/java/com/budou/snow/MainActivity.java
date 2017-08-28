package com.budou.snow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import snowview.FlowersView;

public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     */
    private FlowersView mFlowerView;
    // 屏幕宽度
    public static int screenWidth;
    // 屏幕高度
    public static int screenHeight;
    Timer myTimer = null;
    TimerTask mTask = null;
    private static final int SNOW_BLOCK = 1;
    private Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            mFlowerView.inva();
        }
    };
    Button btn_next;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this,TypeTextViewActivity.class));
//        WebView webView = (WebView) findViewById(R.id.webview);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/qx/index.html");
        initFlower();
        initBtn();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Message msg = new Message();
                msg.what = SNOW_BLOCK;
                mHandler.sendMessage(msg);
            }
        }.start();
    }

    private void initFlower() {
        mFlowerView = (FlowersView) findViewById(R.id.flowerview);
        screenWidth = getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();
        screenHeight = getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();

        DisplayMetrics dis = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dis);
        float de = dis.density;
        mFlowerView.setWH(screenWidth, screenHeight, de);
        mFlowerView.loadFlower();
        mFlowerView.addRect();

        myTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = SNOW_BLOCK;
                mHandler.sendMessage(msg);
            }
        };
        myTimer.schedule(mTask, 3000,10);
    }


    private void initBtn() {
        btn_next = (Button) findViewById(R.id.btn_click);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TypeTextViewActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFlowerView.recly();
    }
}
