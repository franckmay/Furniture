package com.shashank.platform.furnitureecommerceappui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.platform.furnitureecommerceappui.data.dbHelpers.FurnitureDbHelper;

import java.util.regex.Pattern;


public class ConnexionActivity extends AppCompatActivity {

    private EditText mail;
    private EditText pwd;
    private ImageView img;
    private TextView err;
    private Button con;
    private FurnitureDbHelper emdb;
    private Intent intent;
    private Cursor cursor;
    private LottieAnimationView lott;
    private LinearLayout ll;

    private static final String pass_regex = "^([A-Za-z]{3,40})([ \\t][A-Za-z]{3,40})*$";
    private static final String email_regex = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_connexion);
        intent = getIntent();
        emdb = new FurnitureDbHelper(this);
        mail = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        con = findViewById(R.id.login);

        lott = findViewById(R.id.lottie_duc);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connecter(v);
            }
        });

    }

    /**
     * methode de connexion- verifie si les identifiants existent
     */
    private void connecter(View v) {

        if (champsCorrect() != 2)
            Snackbar.make(v, "Verifiez les valeurs entrées et essayez encore.", Snackbar.LENGTH_LONG).setAction("", null).show();
        else {
            String m = mail.getText().toString();
            String p = pwd.getText().toString();
            boolean res = emdb.checkUser(m, p);
            if (res) {
                OpenHomePage();

            } else {
                Snackbar.make(v, "identifiant et/ou mot de passe incorrects.", Snackbar.LENGTH_LONG).setAction("", null).show();
            }
        }

    }

    public void OpenSignupPage(View view) {
        startActivity(new Intent(ConnexionActivity.this, InscriptionActivity.class));
    }

    public void OpenHomePage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                lott.setVisibility(View.VISIBLE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                startActivity(new Intent(ConnexionActivity.this, Main2Activity.class));
                startActivity(new Intent(ConnexionActivity.this, HomeActivity.class));
                finish();
            }
        }, 3000);
    }

    public int champsCorrect() {
        int number_of_matches = 0;
        if (Pattern.matches(pass_regex, pwd.getText().toString()))
            number_of_matches++;
        if (Pattern.matches(email_regex, mail.getText().toString()))
            number_of_matches++;

        return number_of_matches;
    }

    public void skip(View view) {
        startActivity(new Intent(ConnexionActivity.this, HomeActivity.class));
        finish();
    }
}