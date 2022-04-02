package com.shashank.platform.furnitureecommerceappui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Payement extends AppCompatActivity {


    private LottieAnimationView lott, lot_load;
    private Button bnt;
    private EditText mt;
    private TextView tv;
    ProgressDialog progressDialog;
    Dialog dialog1, dialog2;
    private String codedepayement, codeussdi, MONTANTSCAN, montantPayement, NUMERO = "656832774";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payement);

        Intent intent = getIntent();
        montantPayement = intent.getStringExtra("somme");


        lott = findViewById(R.id.lottiedon2);
        lot_load = findViewById(R.id.lottiedonSucces);
        bnt = findViewById(R.id.button);
        mt = findViewById(R.id.montant);
        tv = findViewById(R.id.textViewpay);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.waiting));
        dialog1 = new Dialog(this);
        dialog1.setContentView(R.layout.custom_dialog_insert);
        dialog2 = new Dialog(this);
        dialog2.setContentView(R.layout.custom_dialog_user);

        mt.setText(montantPayement);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });


        //////////////////// procecus de paiement /////////////////////////////////////////////////////

        Button getcode = dialog1.findViewById(R.id.getcode);
        EditText code = dialog1.findViewById(R.id.code);
        ImageView sumitcode = dialog1.findViewById(R.id.sumitcode);

        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
                dialog2.show();
            }
        });

        sumitcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codedepayement = code.getText().toString();
                dialog1.dismiss();
                progressDialog.show();
                suite();
            }
        });
        LinearLayout paieorange = dialog2.findViewById(R.id.paieorange);
        paieorange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
                codeussdi = "#150*1*1*" + NUMERO + "*" + MONTANTSCAN + "#";
                suite();
            }
        });
    }

    public void donBackbutton(View view) {
        Intent intent = new Intent(Payement.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void suite() {
        mt.animate().translationX(-1400).setDuration(1300).setStartDelay(200);
        lott.animate().translationX(1400).setDuration(1000).setStartDelay(500);
        tv.animate().translationX(1400).setDuration(1000).setStartDelay(500);
        progressDialog.dismiss();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lot_load.setVisibility(View.VISIBLE);
            }
        }, 1800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(Payement.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        }, 4000);
    }
}