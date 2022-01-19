package com.shashank.platform.furnitureecommerceappui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import com.airbnb.lottie.LottieAnimationView;

public class LoadActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private static int SPLASH_TIME_OUT = 10000;
    private ImageView logo;
    TextView name;
    private LottieAnimationView lott, lot_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        logo = findViewById(R.id.im_logo);
        name = findViewById(R.id.tv_logo);
        lott = findViewById(R.id.lottie);
        lot_load = findViewById(R.id.lottie_load);


        logo.animate().translationX(1400).setDuration(1300).setStartDelay(2500);
        name.animate().translationX(-1400).setDuration(1000).setStartDelay(3000);
        lott.animate().translationX(1400).setDuration(1000).setStartDelay(4800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lot_load.setVisibility(View.VISIBLE);
            }
        },6000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home = new Intent(LoadActivity.this,ConnexionActivity.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME_OUT);

    }}