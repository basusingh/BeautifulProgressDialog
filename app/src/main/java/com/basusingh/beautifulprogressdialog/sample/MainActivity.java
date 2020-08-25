package com.basusingh.beautifulprogressdialog.sample;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.basusingh.beautifulprogressdialog.BeautifulProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {

    BeautifulProgressDialog progressDialog;
    View colorBackground;
    ImageView backgroundImage;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundImage = findViewById(R.id.backgroundImage);
        colorBackground = findViewById(R.id.colorBackground);

        animationDrawable = (AnimationDrawable) colorBackground.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(3000);

        backgroundImage.animate()
                .alpha(0.56f)
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(500)
                .setListener(null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.bg_small);
                Blurry.with(getApplicationContext()).radius(70).from(icon).into(backgroundImage);
            }
        }, 500);

        FrameLayout image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withImage, "Please wait");
                progressDialog.setImageLocation(getResources().getDrawable(R.drawable.burger_logo));
                progressDialog.setLayoutColor(getResources().getColor(R.color.cream));
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
        FrameLayout gif = findViewById(R.id.gif);
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withGIF, null);
                Uri myUri = Uri.fromFile(new File("//android_asset/sample_gif_1.gif"));
                progressDialog.setGifLocation(myUri);
                progressDialog.setLayoutColor(getResources().getColor(R.color.white));
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
        FrameLayout lottie = findViewById(R.id.lottie);
        lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withLottie, null);
                progressDialog.setLottieLocation("lottie_1.json");
                progressDialog.setLottieLoop(true);
                progressDialog.setLayoutColor(getResources().getColor(R.color.white));
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
        /**
        Button progressBar = findViewById(R.id.progressBar);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withProgressBar, null);
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
         **/



    }

    @Override
    public void onResume(){
        super.onResume();
        if(animationDrawable != null && !animationDrawable.isRunning()){
            animationDrawable.start();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(animationDrawable != null && animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }

}