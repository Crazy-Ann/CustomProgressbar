package com.yjt.demo;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface CustomProgressBarListener {

    Bitmap setOnDefaultImage();

    Bitmap setOnThumbImage();
    
    Bitmap setOnResultImage();
}
