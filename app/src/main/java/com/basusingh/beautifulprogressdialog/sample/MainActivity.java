package com.basusingh.beautifulprogressdialog.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.basusingh.beautifulprogressdialog.BeautifulProgressDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BeautifulProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Beautiful Progress Dialog");
        }

        Button image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withImage, "Please wait");
                progressDialog.setImageLocation(getResources().getDrawable(R.drawable.burger_logo));
                progressDialog.setLayoutColor(getResources().getColor(R.color.cream));
                progressDialog.setUpProgressDialog();
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
        Button gif = findViewById(R.id.gif);
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withGIF, null);
                Uri myUri = Uri.fromFile(new File("//android_asset/sample_gif_1.gif"));
                progressDialog.setGifLocation(myUri);
                progressDialog.setLayoutColor(getResources().getColor(R.color.white));
                progressDialog.setUpProgressDialog();
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });
        Button lottie = findViewById(R.id.lottie);
        lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withLottie, null);
                progressDialog.setLottieLocation("lottie_1.json");
                progressDialog.setLottieLoop(true);
                progressDialog.setLottieCompactPadding(true);
                progressDialog.setLayoutColor(getResources().getColor(R.color.white));
                progressDialog.setUpProgressDialog();
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 8000);
            }
        });


    }

}