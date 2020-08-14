package com.basusingh.beautifulprogressdialog;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.io.File;
import java.net.URI;
import java.util.Objects;

public class BeautifulProgressDialog {

    public static final String withImage = "withImage";
    public static final String withGIF = "withGIF";
    public static final String withLottie = "withLottie";
    private static String viewType;

    private static int rounderCorner = 2;
    public static int CORNER_TYPE_LOW = 1;
    public static int CORNER_TYPE_MED = 2;
    public static int CORNER_TYPE_HIGH = 3;
    public static int CORNER_TYPE_ULTRA_HIGH = 4;

    private static int imageCustomType;
    private static Drawable mImageDrawable;
    private static Bitmap mImageBitmap;
    private static Uri mImageUri;

    private static Uri mGifUri;

    private static String mLottieUrl;
    private static boolean lottieLoop = true;
    private static boolean lottieCompactPadding = true;
    LottieAnimationView animationView;

    private static String mFontFamily = null;

    private static String mMessage;
    private static boolean showMessage = false;
    private static boolean cancelableOnTouchOutside = false;
    private static boolean cancelable = false;
    private Activity mContext;
    private Dialog alertDialog;
    View dialogView;

    private int cardViewColor = android.R.color.white;
    private float cardViewRadius = 15f;
    private float cardViewElevation = 3f;

    private int topPadding, bottomPadding, leftPadding, rightPadding, overallPadding;

    public BeautifulProgressDialog(Activity activity, String type, String message){
        this.mContext = activity;
        switch (type){
            case withImage:
                viewType = withImage;
                break;
            case withGIF:
                viewType = withGIF;
                break;
            case withLottie:
                viewType = withLottie;
                break;
        }
        if(message == null){
            removeMessage();
        } else {
            showMessage(true);
            mMessage = message;
        }
        alertDialog  = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView = LayoutInflater.from(activity).inflate(R.layout.layout_progress_dialog, null);
        alertDialog.setContentView(dialogView);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (animationView.isAnimating()){
                    animationView.cancelAnimation();
                }
            }
        });


        /**
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        dialogView = LayoutInflater.from(activity).inflate(R.layout.layout_progress_dialog, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         **/

    }

    /**
     * Convert padding from dp to px.
     */

    private void setUpPadding(){
        topPadding = dpToPx(15);
        bottomPadding = dpToPx(15);
        leftPadding = dpToPx(30);
        rightPadding = dpToPx(30);
        overallPadding = dpToPx(20);
    }

    /**
     * Set rounded corner type
     * @param type
     */

    //TODO
    private void setRoundedCorner(int type){
        if(type <= 4 && type>=1){
            rounderCorner = type;
        }
    }

    /**
     * Set custom font for the message
     * @param font
     */

    public void setFont(String font){
        mFontFamily = font;
    }

    /**
     * Set view type. Must be one of the defined.
     * @param type
     * @throws Exception
     */

    public void setViewType(String type) throws Exception{
        switch (type){
            case withImage:
            case withGIF:
            case withLottie:
                viewType = type;
                break;
            default:
                throw new Exception("Type must be of the one defined");
        }
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

    //TODO
    private void setLayoutRadius(float radius){
        this.cardViewRadius = radius;
    }

    /**
     * Set elevation of the layout
     * @param elevation
     */

    //TODO
    private void setLayoutElevation(float elevation){
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

    public void setImageLocation(Drawable drawable){
        mImageDrawable = drawable;
        imageCustomType = 1;
    }

    /**
     * Set Image Bitmap resource. User must set withImage as view type
     * @param bitmap
     * @throws Exception
     */

    public void setImageLocation(Bitmap bitmap){
        mImageBitmap = bitmap;
        imageCustomType = 2;
    }

    /**
     * Set Image int resource. User must set withImage as view type
     * @param location
     * @throws Exception
     */

    public void setImageLocation(Uri location){
        mImageUri = location;
        imageCustomType = 3;
    }

    /**
     * Set GIF int resource. User must set withGIF as view type
     * @param gifLocation
     * @throws Exception
     */

    public void setGifLocation(Uri gifLocation){
        mGifUri = gifLocation;
    }

    /**
     * Set Lottie string resource. User must set withLottie as view type
     * @param url
     * @throws Exception
     */

    public void setLottieLocation(String url){
        mLottieUrl = url;
    }

    /**
     * Set loop for Lottie animation
     * @param value
     */

    public void setLottieLoop(boolean value){
        lottieLoop = value;
    }

    public void setLottieCompactPadding(boolean value){
        lottieCompactPadding = value;
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
    public void setUpProgressDialog(){
        if(viewType == null){
            Log.e("View Type", "Null");
            return;
        }
        if(showMessage && mMessage == null){
            Log.e("Show Message", "Error");
            return;
        }
        alertDialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        alertDialog.setCancelable(cancelable);
        setUpPadding();

        CardView parent;
        /**
        parent = new CardView(new ContextThemeWrapper(mContext, R.style.RoundedCornersDialog_High));
        switch (rounderCorner){
            case 1:
                parent = new CardView(new ContextThemeWrapper(mContext, R.style.RoundedCornersDialog_Low));
                break;
            case 2:
                parent = new CardView(new ContextThemeWrapper(mContext, R.style.RoundedCornersDialog_Mid));
                break;
            case 3:
                parent = new CardView(new ContextThemeWrapper(mContext, R.style.RoundedCornersDialog_High));
                break;
            case 4:
                parent = new CardView(new ContextThemeWrapper(mContext, R.style.RoundedCornersDialog_Ultra_High));
                break;
        }
         **/
        parent = dialogView.findViewById(R.id.parent);
        parent.setBackgroundColor(cardViewColor);
        parent.setElevation(cardViewElevation);

        LinearLayout interiorLayout = dialogView.findViewById(R.id.interior_layout);
        ImageView imageView = dialogView.findViewById(R.id.imageView);
        TextView message = dialogView.findViewById(R.id.message);
        animationView = dialogView.findViewById(R.id.lottie);

        switch (viewType){
            case withImage:
                imageView.setVisibility(View.VISIBLE);
                animationView.setVisibility(View.GONE);
                switch (imageCustomType){
                    case 1:
                        imageView.setImageDrawable(mImageDrawable);
                        break;
                    case 2:
                        imageView.setImageBitmap(mImageBitmap);
                        break;
                    case 3:
                        imageView.setImageURI(mImageUri);
                        break;
                }
                break;
            case withGIF:
                imageView.setVisibility(View.VISIBLE);
                animationView.setVisibility(View.GONE);
                Glide.with(mContext)
                        .load(mGifUri)
                        .into(imageView);
                break;
            case withLottie:
                imageView.setVisibility(View.GONE);
                animationView.setVisibility(View.VISIBLE);
                animationView.setAnimation(mLottieUrl);
                animationView.setRepeatCount(lottieLoop ? ValueAnimator.INFINITE: 0);
                break;
        }


        if(showMessage){
            message.setText(mMessage);
            message.setVisibility(View.VISIBLE);
            if(mFontFamily != null){
                try{
                    Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                            mFontFamily);
                    message.setTypeface(face);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            interiorLayout.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        } else {
            message.setVisibility(View.GONE);
            if(!viewType.equalsIgnoreCase(withLottie)){
                interiorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
            } else {
                if (lottieCompactPadding) {
                    interiorLayout.setPadding(0, 0, 0, 0);
                } else {
                    interiorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
                }
            }
        }
    }

    /**
     * Show progress dialog
     */

    public void show(){
        if (viewType.equalsIgnoreCase(withLottie) && animationView != null){
            animationView.playAnimation();
        }
        alertDialog.show();
    }

    /**
     * Dismiss progress dialog
     */

    public void dismiss(){
        if (viewType.equalsIgnoreCase(withLottie) && animationView != null){
            animationView.cancelAnimation();
        }
        alertDialog.dismiss();
    }

    /**
     * Convert padding from dp to px.
     */

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
