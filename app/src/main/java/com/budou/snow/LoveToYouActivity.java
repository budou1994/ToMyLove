package com.budou.snow;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import heartview.HeartView;

public class LoveToYouActivity extends AppCompatActivity {


    HeartView heartView;
    private TextView tv_text;
    private TextView tv_text_1;
    private TextView tv_text_2;
    private int clo = 0;

    private RelativeLayout countDown;
    // 倒计时
    private TextView daysTv, hoursTv, minutesTv, secondsTv;
    private long mDay = 115;
    private long mHour = 20;
    private long mMin = 37;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                daysTv.setText(mDay + "");
                hoursTv.setText(mHour + "");
                minutesTv.setText(mMin + "");
                secondsTv.setText(mSecond + "");
                if (mDay == 0 && mHour == 0 && mMin == 0 && mSecond == 0) {
                    countDown.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_love_to_you);

        heartView = (HeartView) findViewById(R.id.surfaceView);
        tv_text = (TextView) findViewById(R.id.myword);
        tv_text_1 = (TextView) findViewById(R.id.myword_1);
        tv_text_2 = (TextView) findViewById(R.id.myword_2);
        shark();

        countDown = (RelativeLayout) findViewById(R.id.countdown_layout);
        daysTv = (TextView) findViewById(R.id.days_tv);
        hoursTv = (TextView) findViewById(R.id.hours_tv);
        minutesTv = (TextView) findViewById(R.id.minutes_tv);
        secondsTv = (TextView) findViewById(R.id.seconds_tv);

        startRun();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        heartView.reDraw();
        return super.onTouchEvent(event);

    }

    public void reDraw(View v) {

        heartView.reDraw();
    }

    private void shark() {
        Timer timer = new Timer();
        TimerTask taskcc = new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        if (clo == 0) {
                            clo = 1;
                            tv_text.setTextColor(Color.TRANSPARENT);
                            tv_text_1.setTextColor(Color.TRANSPARENT);
                            tv_text_2.setTextColor(Color.TRANSPARENT);
                        } else {
                            if (clo == 1) {

                                clo = 2;
                                tv_text.setTextColor(Color.YELLOW);
                                tv_text_1.setTextColor(Color.YELLOW);
                                tv_text_2.setTextColor(Color.YELLOW);
                            } else if (clo == 2) {

                                clo = 3;
                                tv_text.setTextColor(Color.RED);
                                tv_text_1.setTextColor(Color.RED);
                                tv_text_2.setTextColor(Color.RED);

                            } else if (clo == 3) {

                                clo = 4;
                                tv_text.setTextColor(Color.BLACK);
                                tv_text_1.setTextColor(Color.BLACK);
                                tv_text_2.setTextColor(Color.BLACK);
                            } else if (clo == 4) {

                                clo = 5;
                                tv_text.setTextColor(Color.WHITE);
                                tv_text_1.setTextColor(Color.WHITE);
                                tv_text_2.setTextColor(Color.WHITE);
                            } else if (clo == 5) {

                                clo = 6;
                                tv_text.setTextColor(Color.GREEN);
                                tv_text_1.setTextColor(Color.GREEN);
                                tv_text_2.setTextColor(Color.GREEN);
                            } else if (clo == 6) {

                                clo = 7;
                                tv_text.setTextColor(Color.MAGENTA);
                                tv_text_1.setTextColor(Color.MAGENTA);
                                tv_text_2.setTextColor(Color.MAGENTA);
                            } else if (clo == 7) {

                                clo = 8;
                                tv_text.setTextColor(Color.CYAN);
                                tv_text_1.setTextColor(Color.CYAN);
                                tv_text_2.setTextColor(Color.CYAN);
                            } else if (clo == 8) {

                                clo = 9;
                                tv_text.setTextColor(Color.DKGRAY);
                                tv_text_1.setTextColor(Color.DKGRAY);
                                tv_text_2.setTextColor(Color.DKGRAY);
                            } else if (clo == 9) {

                                clo = 10;
                                tv_text.setTextColor(Color.GRAY);
                                tv_text_1.setTextColor(Color.GRAY);
                                tv_text_2.setTextColor(Color.GRAY);
                            } else if (clo == 10) {

                                clo = 11;
                                tv_text.setTextColor(Color.LTGRAY);
                                tv_text_1.setTextColor(Color.LTGRAY);
                                tv_text_2.setTextColor(Color.LTGRAY);
                            } else {
                                clo = 0;
                                tv_text.setTextColor(Color.BLUE);
                                tv_text_1.setTextColor(Color.BLUE);
                                tv_text_2.setTextColor(Color.BLUE);
                            }
                        }
                    }
                });
            }
        };
        timer.schedule(taskcc, 1, 1500);  //<span style="color: rgb(85, 85, 85); font-family: 'microsoft yahei'; font-size: 15px; line-height: 35px;">第二个参数分别是delay（多长时间后执行），第三个参数是：duration（执行间隔）单位为：ms</span>
    }

    /**
     * 开启计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond++;
        if (mSecond > 60) {
            mSecond = 0;
            mMin++;
            if (mMin > 60) {
                mMin = 0;
                mHour++;
                if (mHour > 24) {
                    mHour = 0;
                    // 倒计时结束
                    mDay++;
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.create();
            dialog.setTitle("我的水平太菜了，请见谅。");
            dialog.setMessage("这是送你的礼物,虽然我现在还是写不出来更棒的东西，但是这个是我亲手做的。" +
                    "这里面我不会的地方都是我一行行的看着别人代码敲出来的，希望你喜欢！");
            dialog.setIcon(R.drawable.tomylove);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "狠心离开", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    finish();
                    System.exit(0);
//                    dialog.dismiss();
                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "我听完这首歌", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
