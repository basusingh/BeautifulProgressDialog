package com.basusingh.beautifulprogressdialog;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class BeautifulProgressDialog {

    private static BeautifulProgressDialog INSTANCE = null;

    public static final String withImage = "withImage";
    public static final String withGIF = "withGIF";
    public static final String withLotte = "withLotte";
    public static Drawable mDrawable;
    public static Bitmap mBitmap;

    private BeautifulProgressDialog(){

    }

    public static BeautifulProgressDialog getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeautifulProgressDialog();
        }
        return(INSTANCE);
    }

    public void withImageDrawable(Drawable drawable){
        mDrawable = drawable;
    }

    public void withImageBitmap(Bitmap bitmap){
        mBitmap = bitmap;
    }

    
}
