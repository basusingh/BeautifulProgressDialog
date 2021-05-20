package com.basusingh.beautifulprogressdialog.sample;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                }, 5000);
            }
        });

        FrameLayout gif = findViewById(R.id.gif);
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withGIF, "Please wait");
                Uri myUri = Uri.fromFile(new File("//android_asset/gif_food_and_smile.gif"));
                progressDialog.setGifLocation(myUri);
                progressDialog.setLayoutColor(getResources().getColor(R.color.BeautifulProgressDialogBg));
                progressDialog.setMessageColor(getResources().getColor(R.color.white));
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 5000);
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
                }, 5000);
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
    }

    @Override
    public void onPause(){
        super.onPause();
    }

}