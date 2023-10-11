package com.kamus.highriskpregnancy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class choosePatient extends AppCompatActivity {
    Button pasienbaru, pasienlama;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_patient);
        pasienbaru = findViewById(R.id.buttonnew);
        pasienlama = findViewById(R.id.buttonold);


        pasienbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choosePatient.this, dataPasien.class);
                startActivity(intent);


            }
        });
        pasienlama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(choosePatient.this,checkPatient.class);
                startActivity(intent);*/

            }
        });

    }
}