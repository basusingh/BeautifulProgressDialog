package com.basusingh.beautifulprogressdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.net.URI;

public class BeautifulProgressDialog {

    private static final String withImage = "withImage";
    private static final String withGIF = "withGIF";
    private static final String withLotte = "withLotte";
    private static String viewType;
    private static Drawable mDrawable;
    private static Bitmap mBitmap;
    private static URI mGifUri;
    private static String mLotteUrl;
    private static String mMessage;
    private static boolean showMessage = false;
    private static boolean cancelableOnTouchOutside = false;
    private static boolean cancelable = false;
    private Context mContext;
    private Activity mActivity;
    private Dialog mDialog;

    public BeautifulProgressDialog(Activity activity, String type) throws Exception{
        this.mActivity = activity;
        mDialog  = new Dialog(mActivity);
        switch (type){
            case withImage:
                viewType = withImage;
                break;
            case withGIF:
                viewType = withGIF;
                break;
            case withLotte:
                viewType = withLotte;
                break;
            default:
                throw new Exception("Type must be of the one defined");
        }
    }

    public void showMessage(boolean value) {
        showMessage = value;
    }

    public void setMessage(String text){
        showMessage = true;
        mMessage = text;
    }

    public void removeMessage(){
        showMessage = false;
        mMessage = null;
    }

    public void setImageDrawable(Drawable drawable) throws Exception{
        if(!viewType.equalsIgnoreCase(withImage)){
            throw new Exception("Must set view type to Image");
        }
        mDrawable = drawable;
    }

    public void setImageBitmap(Bitmap bitmap) throws Exception{
        if(!viewType.equalsIgnoreCase(withImage)){
            throw new Exception("Must set view type to Image");
        }
        mBitmap = bitmap;
    }

    public void setImageGIF(URI uri) throws Exception{
        if(!viewType.equalsIgnoreCase(withGIF)){
            throw new Exception("Must set view type to GIF");
        }
        mGifUri = uri;
    }

    public void setLotteFile(String url) throws Exception{
        if(!viewType.equalsIgnoreCase(withLotte)){
            throw new Exception("Must set view type to Lotte");
        }
        mLotteUrl = url;
    }

    public void setCancelableOnTouchOutside(boolean value){
        cancelableOnTouchOutside = value;
    }

    public void setCancelable(boolean value){
        cancelable = value;
    }

    public void showProgressDialog() {


        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        mDialog.setContentView(R.layout.layout_progress_dialog);

        ImageView gifImageView = mDialog.findViewById(R.id.custom_loading_imageView);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        Glide.with(activity)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);

        mDialog.show();
    }

}
