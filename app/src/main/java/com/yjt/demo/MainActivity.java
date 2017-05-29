package com.yjt.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CustomProgressBarListener {

    private CustomProgressBar customProgressBar;
    private int               progress;
    private MainHandler       handler;

    @Override
    public Bitmap setOnDefaultImage() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.backgound);
    }

    @Override
    public Bitmap setOnThumbImage() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.thumb);
    }

    @Override
    public Bitmap setOnResultImage() {
        return BitmapFactory.decodeResource(getResources(), R.mipmap.right);
    }

    private static class MainHandler extends Handler {

        private WeakReference<Context> reference;

        MainHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = (MainActivity) reference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        if (activity.progress < 100) {
                            activity.progress += 10;
                            activity.customProgressBar.setProgress(activity.progress);
                            sendEmptyMessageDelayed(0, 300);
                        } else {
                            activity.customProgressBar.setState(103);
                        }
                        break;

                    default:
                        break;
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new MainHandler(this);
        customProgressBar = (CustomProgressBar) findViewById(R.id.customProgressBar);
        customProgressBar.setCustomProgressBarListener(this);
        customProgressBar.setState(101);
        customProgressBar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customProgressBar:
                customProgressBar.setProgress(0);
                customProgressBar.setState(102);
                handler.sendEmptyMessageDelayed(0, 500);
                break;
        }
    }
}
