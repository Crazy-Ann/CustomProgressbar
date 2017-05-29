package com.yjt.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;


public class CustomProgressBar extends ProgressBar {

    private Context context;
    private Paint   paint;
    private int     progress;
    private int     state;
    private int     thumb;

    private static final int STATE_DEFAULT     = 101;
    private static final int STATE_DOWNLOADING = 102;
    private static final int STATE_DOWNLOADED  = 103;

    private CustomProgressBarListener customProgressBarListener;


    public CustomProgressBar(Context context) {
        this(context, null);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setIndeterminate(false);
        setIndeterminateDrawable(ContextCompat.getDrawable(context, android.R.drawable.progress_indeterminate_horizontal));
        setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.layer_list_blue));
        setMax(100);

        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(sp2px(context, 24));
        paint.setTypeface(Typeface.MONOSPACE);
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        this.progress = progress;
    }

    public synchronized void setState(int state) {
        this.state = state;
        invalidate();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state) {
            case STATE_DEFAULT: {
//                setProgress(100);
//                paint.setColor(Color.WHITE);
//                String text1 = "Default Image";
//                Rect rect1 = new Rect();
//                paint.getTextBounds(text1, 0, text1.length(), rect1);
//                canvas.drawText(text1, (getWidth() / 2) - rect1.centerX(), (getHeight() / 2) - rect1.centerY(), paint);
                Bitmap icon = null;
                if (customProgressBarListener != null) {
                    icon = customProgressBarListener.setOnDefaultImage();
                } else {
                    //default image
                }
                float iconX = (getWidth() / 2) - icon.getWidth() / 2;
                float iconY = (getHeight() / 2) - icon.getHeight() / 2;
                canvas.drawBitmap(icon, iconX, iconY, paint);
                if (!icon.isRecycled()) {
                    icon.isRecycled();
                }
                break;
            }
            case STATE_DOWNLOADING: {
                String text2 = String.format("%d", progress);
                Rect   rect2 = new Rect();
                paint.getTextBounds(text2, 0, text2.length(), rect2);
                canvas.drawText(text2, (getWidth() / 2) - rect2.centerX(), (getHeight() / 2) - rect2.centerY(), paint);
                break;
            }
            case STATE_DOWNLOADED: {
//                setProgress(100);
//                paint.setColor(Color.WHITE);
//                String text3 = "thumb Image";
//                Rect rect3 = new Rect();
//                paint.getTextBounds(text3, 0, text3.length(), rect3);
//                canvas.drawText(text3, (getWidth() / 2) - rect3.centerX(), (getHeight() / 2) - rect3.centerY(), paint);
                Bitmap icon2 = null;
                if (customProgressBarListener != null) {
                    icon2 = customProgressBarListener.setOnThumbImage();
                } else {
                    //default image
                }
                float icon2X = (getWidth() / 2) - icon2.getWidth() / 2;
                float icon2Y = (getHeight() / 2) - icon2.getHeight() / 2;
                canvas.drawBitmap(icon2, icon2X, icon2Y, paint);
                if (!icon2.isRecycled()) {
                    icon2.isRecycled();
                }
                Bitmap icon3 = null;
                if (customProgressBarListener != null) {
                    icon3 = customProgressBarListener.setOnResultImage();
                } else {
                    //default image
                }
                float icon3X = (getWidth() / 2) - icon3.getWidth() / 2;
                float icon3Y = (getHeight() / 2) - icon3.getHeight() / 2;
                canvas.drawBitmap(icon3, icon3X, icon3Y, paint);
                if (!icon3.isRecycled()) {
                    icon3.isRecycled();
                }

                break;
            }
            default:
                break;
        }
    }

    public void setCustomProgressBarListener(CustomProgressBarListener customProgressBarListener) {
        this.customProgressBarListener = customProgressBarListener;
    }

    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
