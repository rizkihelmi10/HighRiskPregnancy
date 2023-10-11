package com.kamus.highriskpregnancy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class signupact extends AppCompatActivity {
    Button btnSign;
    CheckBox Dokter;
    CheckBox Bidan ;
    CheckBox DokterUmum;
    TextView signup;
    TextView pekerjaan;
    TextView Nama;
    EditText usrname;
    MySQLiteHelper dbhelper;
    dbActivities db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupact);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);

        setContentView(R.layout.activity_signupact);
        btnSign = findViewById(R.id.buttonsignup);
        Dokter = findViewById(R.id.checkBoxDrOb);
        Bidan = findViewById(R.id.checkBoxBid);
        DokterUmum = findViewById(R.id.checkBoxDumum);
        Nama = findViewById(R.id.textNama);
        usrname = findViewById(R.id.editTextNama);
        signup = findViewById(R.id.textsignup);
        pekerjaan= findViewById(R.id.textPekerjaan);

        saveinfo();; // Set up the SignUp button listener

        // Optional: Finish the current activity so the user cannot go back to it





    }
    public void saveinfo(){
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText txtname = findViewById(R.id.editTextNama);
                final CheckBox DokterOb = findViewById(R.id.checkBoxDrOb);
                final CheckBox DokterUm = findViewById(R.id.checkBoxDumum);
                final CheckBox Bidan = findViewById(R.id.checkBoxBid);
                StringBuilder selectedServices = new StringBuilder();
                if (DokterOb.isChecked()) {
                    selectedServices.append("Dokter Obgyn");
                }

                if (DokterUm.isChecked()) {
                    selectedServices.append("Dokter Umum");
                }

                if (Bidan.isChecked()) {
                    selectedServices.append("Bidan");
                }
                db.SaveDB(dbhelper,0, txtname.getText().toString(),selectedServices.toString());
                Log.d("tesignup", "onClick: ");
                Intent intent = new Intent(signupact.this, loginPage.class);
                startActivity(intent);

            }
        });
    }
}