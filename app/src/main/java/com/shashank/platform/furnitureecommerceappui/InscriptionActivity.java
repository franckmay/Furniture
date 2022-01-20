package com.shashank.platform.furnitureecommerceappui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.platform.furnitureecommerceappui.data.dbHelpers.FurnitureDbHelper;

import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {
    private EditText user_name, user_email, user_phone, user_pass; //to be read from input fields
    private Button save;
    private static final String name_regex = "^([A-Za-z]{3,40})([ \\t][A-Za-z]{3,40})*$";
    private static final String email_regex = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";
    private static final String phone_regex = "^[0-9]{7,}$";
    FurnitureDbHelper emdb;
    private AppCompatImageView img;
    private CardView card;
    private LottieAnimationView lott;
    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_inscription);
        emdb = new FurnitureDbHelper(this);

        user_name = findViewById(R.id.et_ins_name);
        user_email = findViewById(R.id.et_ins_email);
        user_pass = findViewById(R.id.et_ins_pass);
        user_phone = findViewById(R.id.et_ins_tel);
        save = findViewById(R.id.insc_save);
//        img = findViewById(R.id.aciv_ins);
        card = findViewById(R.id.card_inscr);
        lott = findViewById(R.id.lottie_duck);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (champsCorrects() != 3)
                    Snackbar.make(v, "Verifiez les valeurs entrées et essayez encore.", Snackbar.LENGTH_LONG).setAction("", null).show();
                else {

                    boolean res = emdb.checkUser(user_email.getText().toString());

                    if (res) {
                        Snackbar.make(v, "Un utilsateur est déja enregistré avec cet email.", Snackbar.LENGTH_LONG).setAction("", null).show();
                    } else {
                        if (emdb.addUser(user_name.getText().toString(), user_email.getText().toString(), user_pass.getText().toString(), user_phone.getText().toString())) {
                            Log.i("user ---", "USER ENTERED SUCCESSFULLY");
                            Snackbar.make(v, "UTILISATEUR ENREGISTRE AVEC SUCCES.", Snackbar.LENGTH_LONG).setAction("", null).show();
                            OpenStartingPage(v);
                        } else {
                            Snackbar.make(v, "FAILED TO ENTER CURRENT USER. TRY AGAIN LATER.", Snackbar.LENGTH_LONG).setAction("", null).show();
                        }
                    }
                }
            }
        });
    }

    public void OpenStartingPage(View view) {
        card.animate().translationX(-1400).setDuration(1000).setStartDelay(100);
        save.animate().translationX(1400).setDuration(500).setStartDelay(500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lott.setVisibility(View.VISIBLE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(InscriptionActivity.this, ConnexionActivity.class).putExtra("email", user_email.getText().toString()));
                finish();
            }
        }, 3000);
    }

    public void OpenConnexionPage(View view) {
        startActivity(new Intent(InscriptionActivity.this, ConnexionActivity.class));
        finish();
    }

    public int champsCorrects() {
        int number_of_matches = 0;
        if (Pattern.matches(name_regex, user_name.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(email_regex, user_email.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(phone_regex, user_phone.getText().toString()))
            number_of_matches++;

        Log.i("opps ---", "done bro :)" + number_of_matches);
        return number_of_matches;
    }

}