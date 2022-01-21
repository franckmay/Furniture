package com.shashank.platform.furnitureecommerceappui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //hide status bar
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();

        nav= findViewById(R.id.bottom_nav);
        nav.setSelectedItemId(R.id.nav_home);

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_like:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_explore:
                        fragment = new ExplorerFragment();
                        break;
                    case R.id.nav_account:
                        fragment = new PanierFragment();
                        break;

                }
getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                return true;
            }
        });
    }


    public void ouvrirPanier(View view){
        startActivity(new Intent(HomeActivity.this, CartActivity.class));
        finish();
    }
}