package com.shashank.platform.furnitureecommerceappui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView nav;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();

        nav = findViewById(R.id.bottom_nav);
        nav.setSelectedItemId(R.id.nav_home);
        img = findViewById(R.id.imv_profil);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirProfil(v);
            }
        });
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_like:
                        fragment = new FavorisFragment();
                        break;
                    case R.id.nav_explore:
                        fragment = new ExplorerFragment();
                        break;

                    case R.id.nav_panier:
                        startActivity(new Intent(HomeActivity.this, CartActivity.class));
                        finish();
                        break;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                }
                return true;
            }
        });
    }


    public void ouvrirPanier(View view) {
        startActivity(new Intent(HomeActivity.this, CartActivity.class));
    }

    public void ouvrirProfil(View view) {
        startActivity(new Intent(HomeActivity.this, ProfilActivity.class));
    }


}