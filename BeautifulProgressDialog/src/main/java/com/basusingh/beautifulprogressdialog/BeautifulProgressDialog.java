package com.basusingh.beautifulprogressdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.net.URI;

public class BeautifulProgressDialog {

    private static final String withImage = "withImage";
    private static final String withGIF = "withGIF";
    private static final String withLotte = "withLotte";
    private static String viewType;

    private static int imageCustomType;
    private static Drawable mImageDrawable;
    private static Bitmap mImageBitmap;
    private static int mImageInt;

    private static int gifCustomType;
    private static int mGifUriInt;
    private static String mGifUriString;

    private static String mLotteUrl;

    private static String mMessage;
    private static boolean showMessage = false;
    private static boolean cancelableOnTouchOutside = false;
    private static boolean cancelable = false;
    private Context mContext;
    private Activity mActivity;
    private Dialog mDialog;
    private int cardViewColor = android.R.color.white;
    private float cardViewRadius = 15f;
    private float cardViewElevation = 3f;

    public BeautifulProgressDialog(Activity activity, String type, String message) throws Exception{
        this.mActivity = activity;
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
        if(message == null){
            removeMessage();
        } else {
            showMessage(true);
            mMessage = message;
        }
        mDialog  = new Dialog(mActivity);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.layout_progress_dialog);
    }

    /**
     * Set background color of the Layout
     * @param color
     */
    public void setLayoutColor(int color){
        this.cardViewColor = color;
    }

    /**
     * Set corner radius of the Layout
     * @param radius
     */

    public void setLayoutRadius(float radius){
        this.cardViewRadius = radius;
    }

    /**
     * Set elevation of the layout
     * @param elevation
     */

    public void setLayoutElevation(float elevation){
        this.cardViewElevation = elevation;
    }

    /**
     * Set message to be displayed in the progress dialog
     * @param value
     */

    public void showMessage(boolean value) {
        showMessage = value;
    }

    /**
     * Set message to be displayed in the progress dialog
     * @param text
     */

    public void setMessage(String text){
        showMessage = true;
        mMessage = text;
    }

    /**
     * Remove message to be displayed in the progress dialog
     */

    public void removeMessage(){
        showMessage = false;
        mMessage = null;
    }

    /**
     * Set Image Drawable resources. User must set withImage as view type
     * @param drawable
     * @throws Exception
     */

    public void setImageLocation(Drawable drawable) throws Exception{
        if(!viewType.equalsIgnoreCase(withImage)){
            throw new Exception("Must set view type to Image");
        }
        mImageDrawable = drawable;
        imageCustomType = 1;
    }

    /**
     * Set Image Bitmap resource. User must set withImage as view type
     * @param bitmap
     * @throws Exception
     */

    public void setImageLocation(Bitmap bitmap) throws Exception{
        if(!viewType.equalsIgnoreCase(withImage)){
            throw new Exception("Must set view type to Image");
        }
        mImageBitmap = bitmap;
        imageCustomType = 2;
    }

    /**
     * Set Image int resource. User must set withImage as view type
     * @param location
     * @throws Exception
     */

    public void setImageLocation(int location) throws Exception{
        if(!viewType.equalsIgnoreCase(withImage)){
            throw new Exception("Must set view type to Image");
        }
        mImageInt = location;
        imageCustomType = 3;
    }

    /**
     * Set GIF int resource. User must set withGIF as view type
     * @param gifLocation
     * @throws Exception
     */

    public void setGifLocation(int gifLocation) throws Exception{
        if(!viewType.equalsIgnoreCase(withGIF)){
            throw new Exception("Must set view type to GIF");
        }
        mGifUriInt = gifLocation;
        gifCustomType = 1;
    }

    /**
     * Set GIF string resource (URL or local). User must set withGIF as view type
     * @param gifLocation
     * @throws Exception
     */

    public void setGifLocation(String gifLocation) throws Exception{
        if(!viewType.equalsIgnoreCase(withGIF)){
            throw new Exception("Must set view type to GIF");
        }
        mGifUriString = gifLocation;
        gifCustomType = 2;
    }

    /**
     * Set Lotte string resource. User must set withLotte as view type
     * @param url
     * @throws Exception
     */

    public void setLotteLocation(String url) throws Exception{
        if(!viewType.equalsIgnoreCase(withLotte)){
            throw new Exception("Must set view type to Lotte");
        }
        mLotteUrl = url;
    }

    /**
     * Set progress dialog's cancelable feature if user touches outside. default set to false
     * @param value
     */

    public void setCancelableOnTouchOutside(boolean value){
        cancelableOnTouchOutside = value;
    }

    /**
     * Set progress dialog's cancelable feature. default set to false
     * @param value
     */

    public void setCancelable(boolean value){
        cancelable = value;
    }

    /**
     * Show progress dialog. Make sure view type is set and if message is set to yes, then message cannot be null
     * @throws Exception
     */

    @SuppressLint("ResourceAsColor")
    public void setUpProgressDialog() throws Exception{
        if(viewType == null){
            throw new Exception("Must set view type first");
        }
        if(showMessage && mMessage == null){
            throw new Exception("Must set message");
        }
        mDialog.setCancelable(cancelable);
        mDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);

        CardView parent = mDialog.findViewById(R.id.parent);
        parent.setBackgroundColor(cardViewColor);
        parent.setRadius(cardViewRadius);
        parent.setElevation(cardViewElevation);

        LinearLayout interiorLayout = mDialog.findViewById(R.id.interior_layout);
        ImageView imageView = mDialog.findViewById(R.id.imageView);
        TextView message = mDialog.findViewById(R.id.message);



        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);
        Glide.with(activity)
                .load(R.drawable.loading)
                .placeholder(R.drawable.loading)
                .centerCrop()
                .crossFade()
                .into(imageViewTarget);
    }

    public void show(){
        mDialog.show();
    }

    public void dismiss(){
        mDialog.dismiss();
    }

}
