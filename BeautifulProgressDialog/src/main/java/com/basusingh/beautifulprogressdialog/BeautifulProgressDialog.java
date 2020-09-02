package com.basusingh.beautifulprogressdialog;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.util.Objects;

public class BeautifulProgressDialog {

    public static final String withImage = "withImage";
    public static final String withGIF = "withGIF";
    public static final String withLottie = "withLottie";
    // public static final String withProgressBar = "withProgressBar";
    private static String viewType;

    private Uri gifLocation;

    private static boolean lottieCompactPadding = false;
    LottieAnimationView viewAnimationView;

    private Activity mContext;
    private Dialog alertDialog;
    View dialogView;

    CardView parent;

    private int topPadding, bottomPadding, leftPadding, rightPadding, overallPadding;

    LinearLayout viewInteriorLayout;
    ImageView viewImageView;
    TextView viewMessage;
    // ProgressBar viewProgressBar;

    @SuppressLint("ResourceAsColor")
    public BeautifulProgressDialog(Activity activity, String type, String message){
        this.mContext = activity;


        /**
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        alertDialog = dialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView = LayoutInflater.from(activity).inflate(R.layout.layout_progress_dialog, null);
        alertDialog.setView(dialogView);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         **/

        alertDialog  = new Dialog(activity, R.style.RoundedCornersDialog_Mid);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogView = LayoutInflater.from(activity).inflate(R.layout.layout_progress_dialog, null);
        alertDialog.setContentView(dialogView);
        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (viewAnimationView.isAnimating()){
                    viewAnimationView.cancelAnimation();
                }
            }
        });

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        setUpPadding();


        parent = dialogView.findViewById(R.id.parent);
        parent.setElevation(3f);
        parent.setRadius(15f);

        viewInteriorLayout= dialogView.findViewById(
                R.id.interior_layout);
        viewImageView = dialogView.findViewById(R.id.imageView);
        viewMessage = dialogView.findViewById(R.id.message);
        viewAnimationView = dialogView.findViewById(R.id.lottie);
        //viewProgressBar = dialogView.findViewById(R.id.progressBar);

        viewAnimationView.setRepeatCount(ValueAnimator.INFINITE);

        viewType = type;
        switch (viewType){
            case withImage:
            case withGIF:
                viewImageView.setVisibility(View.VISIBLE);
                viewAnimationView.setVisibility(View.GONE);
                //viewProgressBar.setVisibility(View.GONE);
                break;
            case withLottie:
                viewImageView.setVisibility(View.GONE);
                viewAnimationView.setVisibility(View.VISIBLE);
                viewAnimationView.setRepeatCount(ValueAnimator.INFINITE);
                //viewProgressBar.setVisibility(View.GONE);
                break;
            //case withProgressBar:
                //viewImageView.setVisibility(View.GONE);
                //viewAnimationView.setVisibility(View.GONE);
                //viewProgressBar.setVisibility(View.VISIBLE);
                //break;
        }

        if(message != null){
            viewMessage.setText(message);
            viewMessage.setVisibility(View.VISIBLE);
            viewInteriorLayout.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        } else {
            viewMessage.setVisibility(View.GONE);
            viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
            if(!viewType.equalsIgnoreCase(withLottie)){
                viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
            } else {
                if (lottieCompactPadding) {
                    viewInteriorLayout.setPadding(0, 0, 0, 0);
                } else {
                    viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
                }

         }
        }

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
     * Set custom font for the message
     * @param font
     */

    public void setFont(String font){
        try{
            Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                    font);
            viewMessage.setTypeface(face);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Set view type. Must be one of the defined.
     * @param type
     * @throws Exception
     */

    public void setViewType(String type){
        viewType = type;
        switch (type){
            case withImage:
            case withGIF:
                viewImageView.setVisibility(View.VISIBLE);
                viewAnimationView.setVisibility(View.GONE);
                //viewProgressBar.setVisibility(View.GONE);
                break;
            case withLottie:
                viewImageView.setVisibility(View.GONE);
                viewAnimationView.setVisibility(View.VISIBLE);
               // viewProgressBar.setVisibility(View.GONE);
                break;
            //case withProgressBar:
                //viewImageView.setVisibility(View.GONE);
                //viewAnimationView.setVisibility(View.GONE);
                //viewProgressBar.setVisibility(View.VISIBLE);
                //break;
        }
    }

    /**
     * Get status of Progress Dialog
     * @return
     */

    public boolean isShowing(){
        return alertDialog.isShowing();
    }

    /**
     * Set color of the message
     * @param color
     */
    public void setMessageColor(int color){
        viewMessage.setTextColor(color);
    }

    /**
     * Set color of the progress bar.
     * @param color
     */

    private void setProgressBarColor(int color){
        //viewProgressBar.getIndeterminateDrawable().setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
    }

    /**
     * Set background color of the Layout
     * @param color
     */
    @SuppressLint("ResourceAsColor")
    public void setLayoutColor(int color){
        parent.setCardBackgroundColor(color);
    }

    /**
     * Set corner radius of the Layout
     * @param radius
     */

    //TODO
    private void setLayoutRadius(float radius){
        parent.setRadius(radius);
    }

    /**
     * Set elevation of the layout
     * @param elevation
     */

    //TODO
    private void setLayoutElevation(float elevation){
        parent.setElevation(elevation);
    }

    /**
     * Set message to be displayed in the progress dialog
     * @param value
     */

    public void showMessage(boolean value) {
        if(value){
            viewMessage.setVisibility(View.VISIBLE);
            viewInteriorLayout.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        } else {
            viewMessage.setVisibility(View.GONE);
            if(!viewType.equalsIgnoreCase(withLottie)){
                viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
            } else {
                if (lottieCompactPadding) {
                    viewInteriorLayout.setPadding(0, 0, 0, 0);
                } else {
                    viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
                }
            }
        }
    }

    /**
     * Set message to be displayed in the progress dialog
     * @param text
     */

    public void setMessage(String text){
        viewMessage.setText(text);
        viewMessage.setVisibility(View.VISIBLE);
        viewInteriorLayout.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
    }

    /**
     * Remove message to be displayed in the progress dialog
     */

    public void removeMessage(){
        viewMessage.setVisibility(View.GONE);
        if(!viewType.equalsIgnoreCase(withLottie)){
            viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
        } else {
            if (lottieCompactPadding) {
                viewInteriorLayout.setPadding(0, 0, 0, 0);
            } else {
                viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
            }
        }
    }

    /**
     * Set Image Drawable resources. User must set withImage as view type
     * @param drawable
     * @throws Exception
     */

    public void setImageLocation(Drawable drawable){
        viewImageView.setImageDrawable(drawable);
    }

    /**
     * Set Image Bitmap resource. User must set withImage as view type
     * @param bitmap
     * @throws Exception
     */

    public void setImageLocation(Bitmap bitmap){
        viewImageView.setImageBitmap(bitmap);
    }

    /**
     * Set Image int resource. User must set withImage as view type
     * @param location
     * @throws Exception
     */

    public void setImageLocation(Uri location){
        viewImageView.setImageURI(location);
    }

    /**
     * Set GIF int resource. User must set withGIF as view type
     * @param gifLocation
     * @throws Exception
     */

    public void setGifLocation(Uri gifLocation){
        this.gifLocation = gifLocation;
    }

    /**
     * Set Lottie string resource. User must set withLottie as view type
     * @param url
     * @throws Exception
     */

    public void setLottieLocation(String url){
        viewAnimationView.setAnimation(url);
    }

    /**
     * Set loop for Lottie animation
     * @param value
     */

    public void setLottieLoop(boolean value){
        viewAnimationView.setRepeatCount(value ? ValueAnimator.INFINITE: 0);
    }

    public void setLottieCompactPadding(boolean value){
        lottieCompactPadding = value;
        if (lottieCompactPadding) {
            viewInteriorLayout.setPadding(0, 0, 0, 0);
        } else {
            viewInteriorLayout.setPadding(overallPadding, overallPadding, overallPadding, overallPadding);
        }
    }

    /**
     * Set progress dialog's cancelable feature if user touches outside. default set to false
     * @param value
     */

    public void setCancelableOnTouchOutside(boolean value){
        alertDialog.setCanceledOnTouchOutside(value);
    }

    /**
     * Set progress dialog's cancelable feature. default set to false
     * @param value
     */

    public void setCancelable(boolean value){
        alertDialog.setCancelable(false);
    }

    /**
     * Show progress dialog. Make sure view type is set and if message is set to yes, then message cannot be null
     * @throws Exception
     */


    /**
     * Show progress dialog
     */

    public void show(){
        if(viewType == null){
            Log.e("View Type", "Null");
            return;
        }
        if (viewType.equalsIgnoreCase(withLottie) && viewAnimationView != null){
            viewAnimationView.playAnimation();
        }
        if(viewType.equalsIgnoreCase(withGIF) && gifLocation != null){
            Glide.with(mContext)
                    .load(gifLocation)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(viewImageView);
        }
        alertDialog.show();
    }

    /**
     * Dismiss progress dialog
     */

    public void dismiss(){
        if (viewType.equalsIgnoreCase(withLottie) && viewAnimationView != null){
            viewAnimationView.cancelAnimation();
        }
        if(viewType.equalsIgnoreCase(withGIF)){
            try{
                ((GifDrawable)viewImageView.getDrawable()).stop();
            } catch (Exception e){
                e.printStackTrace();
            }
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
