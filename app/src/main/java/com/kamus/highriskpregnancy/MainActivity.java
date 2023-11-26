package com.kamus.highriskpregnancy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button cekrisiko;
    Button akun;
    Button history;
    MySQLiteHelper dbhelper;
    dbActivities db;
    boolean isloggedin;
    TextView greetings;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);
        setContentView(R.layout.activity_main);
        cekrisiko = findViewById(R.id.cekrisiko);
        akun = findViewById(R.id.account);
        history = findViewById(R.id.history);
        greetings = findViewById(R.id.greeting);
        checkConfigDB();
        greetings();
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
     /*   if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            // You already have the camera permission.
            // You can proceed with capturing images.
        }*/

        cekrisiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, choosePatient.class);
                startActivity(intent);
            }
        });
    }
    public void greetings(){
        String namadokter = db.getResult(dbhelper,"name",null);
        greetings.setText("Welcome,  " + namadokter);

    }


    public void checkConfigDB() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);

        int iCount = db.countConfigDB(dbhelper);
        if (isFirstRun) {
            if (iCount == 0) {
                callclassExecute("Config", "yes");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirstRun", false);
                editor.apply();}
            else {
                Intent intent = new Intent(MainActivity.this, loginPage.class);
                startActivity(intent);
            }

        }
    }
    public void callclassExecute(String clss, String sfromMain) {
        Log.i("callclassExecute", "execute");


        if (clss.equals("Config")) {
            Intent intent = new Intent(this, signupact.class);
            intent.putExtra("sfromMain", sfromMain);
            startActivity(intent);

            if (sfromMain.contains("yes"))
                finish();
        }



    }
    @Override
    public void onBackPressed() {
        // Show an AlertDialog to confirm the action
        if (getClass() == MainActivity.class) {
            new AlertDialog.Builder(this)
                    .setTitle("Exit Confirmation")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Finish the current activity (close it)
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Dismiss the dialog and do nothing
                            dialog.dismiss();
                        }
                    })
                    .show();
        }else{
            super.onBackPressed();
        }
    }
}