package com.basusingh.beautifulprogressdialog.sample;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withImage, null);
                progressDialog.setImageLocation(getResources().getDrawable(R.drawable.ic_hand_black));
                progressDialog.setLayoutColor(getResources().getColor(R.color.white));
                progressDialog.setLayoutRadius(15f);
                progressDialog.setLayoutElevation(3f);
                progressDialog.setUpProgressDialog();
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 5000);
            }
        });
        Button gif = findViewById(R.id.gif);
        gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withGIF, "Please wait");
                progressDialog.setLayoutColor(getResources().getColor(R.color.black));
                progressDialog.setLayoutRadius(15f);
                progressDialog.setLayoutElevation(3f);
            }
        });
        Button lottie = findViewById(R.id.lottie);
        lottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new BeautifulProgressDialog(MainActivity.this, BeautifulProgressDialog.withLottie, null);
                openOtpVerificationDialog();
            }
        });


    }

    private void openOtpVerificationDialog(){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_progress_dialog, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog;
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }

}