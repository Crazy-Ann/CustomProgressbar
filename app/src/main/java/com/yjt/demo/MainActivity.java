package com.yjt.demo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomProgressBar customProgressBar;
    private int               progress;
    private MainHandler       handler;

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
                            activity.customProgressBar.setIndeterminateDrawable(activity.getDrawable(R.mipmap.ic_launcher_round));
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
        customProgressBar.setIndeterminate(false);
        customProgressBar.setState(101);
        customProgressBar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customProgressBar:
                customProgressBar.setProgress(0);
                customProgressBar.setProgress(progress);
                customProgressBar.setState(102);
                handler.sendEmptyMessageDelayed(0, 500);
                break;
        }
    }
}
