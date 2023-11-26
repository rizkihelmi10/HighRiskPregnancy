package com.kamus.highriskpregnancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class afterDataFilled extends AppCompatActivity {
    private EditText editText_a, editText_b, editText_c, editText_d, editText_e;
    private Button calculateButton, continueButton;
    private TextView statusTextView;
    String childkeystatus;
    String patientChildkey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_data_filled);
        Intent intent = getIntent();
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_c = findViewById(R.id.editText_c);
        editText_d = findViewById(R.id.editText_d);
        editText_e = findViewById(R.id.editText_e);

        calculateButton = findViewById(R.id.calculateButton);
        continueButton = findViewById(R.id.continueButton);
        statusTextView = findViewById(R.id.statusTextView);
        patientChildkey = intent.getStringExtra("childkeyPatient");

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateStatus();
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nostatus = statusTextView.getText().toString();
                AnswerDataFilled answerDataFilled = new AnswerDataFilled();
                answerDataFilled.setdata(nostatus);
                saveAnswersToFirebase(answerDataFilled);



            }
        });
    }
    private void saveAnswersToFirebase(AnswerDataFilled answerDataFilled) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerDataFilled);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkeystatus = childSnapshot.getKey(); // Get the key of the child node
                    AnswerDataFilled answerDataFilled = childSnapshot.getValue(AnswerDataFilled.class);
                    Toast.makeText(getApplicationContext(), "key:" + childkeystatus, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(afterDataFilled.this,question1.class);
                    intent.putExtra("childkeystatus", childkeystatus);
                    Log.d("Childkeystat", "onDataChange: " + childkeystatus);
                    intent.putExtra("childkeyPatient", patientChildkey);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void calculateStatus() {
        // Get values from EditText fields
        int a = Integer.parseInt(editText_a.getText().toString());
        int b = Integer.parseInt(editText_b.getText().toString());
        int c = Integer.parseInt(editText_c.getText().toString());
        int d = Integer.parseInt(editText_d.getText().toString());
        int e = Integer.parseInt(editText_e.getText().toString());

        // Calculate the status
        String status = "G" + a + "P" + b + c + d + e;

        // Display the status
        statusTextView.setText("Pregnancy Status: " + status);

        // Show the continue button
        continueButton.setVisibility(View.VISIBLE);
        calculateButton.setVisibility(View.INVISIBLE);
    }
}