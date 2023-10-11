package com.kamus.highriskpregnancy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginPage extends AppCompatActivity {
    TextView login;
    TextView Nama;
    EditText usrname;
    MySQLiteHelper dbhelper;
    dbActivities db;
    Button buttonlogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login = findViewById(R.id.textlogin);
        Nama = findViewById(R.id.textNama);
        usrname = findViewById(R.id.editTextNama);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);
        buttonlogin = findViewById(R.id.btnlogin);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usrname.getText().toString();
                Log.d("validate login", "onClick:  check db" + username);
                if (username.isEmpty()) {
                    Toast.makeText(loginPage.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbhelper.validateLogin(username)) {
                        Toast.makeText(loginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginPage.this, MainActivity.class);
                        startActivity(intent);

                        // Redirect to another activity or perform other actions upon successful login
                    } else {
                        Toast.makeText(loginPage.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}