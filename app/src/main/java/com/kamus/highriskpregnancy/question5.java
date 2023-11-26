package com.kamus.highriskpregnancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class question5 extends AppCompatActivity {
    private RadioGroup answerRadioGroup, answerRadioGroup2, answerRadioGroup2a, answerRadioGroup2b,
            answerRadioGroup2d, answerRadioGroup2e, answerRadioGroup2f, answerRadioGroup3, answerRadioGroup4,
            answerRadioGroup4a, answerRadioGroup4b, answerRadioGroup5, answerRadioGroup6, answerRadioGroup7,
            answerRadioGroup8, answerRadioGroup9, answerRadioGroup10, answerRadioGroup11, answerRadioGroup12,
            answerRadioGroup13,answerRadioGroup4c;
    private RadioButton optionA, optionB, optionC, optionA2, optionB2, optionC2, optionD2, optionE2, optionF2, optionA3, optionB3, optionC3,
            optionA4, optionB4, optionC4, optionD4,optionE4, optionA5, optionB5, optionC5, optionA6, optionB6, optionC6,
            optionA7, optionB7, optionC7, optionA8, optionB8, optionC8,optionD8, optionA9, optionB9, optionC9, optionA10,
            optionB10, optionC10, optionA11, optionB11, optionC11, optionA12, optionB12, optionC12, optionA13,
            optionB13, optionC13;
    private EditText editTextForCheckBox1, editTextOptionA5, editTextOptionA10,editTextOptionA6,editTextOptionA13,editTextOptionA7 ;
    private Button resetbutton1, continueButton,resetbutton2;
    int calctotalscore5;
    int score4;
    int score1;
    int score2;
    int score3;
    String childkey2;
    String childkey1;
    String childkey3;
    String childkey4;
    String childkey5;
    String patientChildkey;
    String statusChildkey;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);
        Intent intent = getIntent();
        patientChildkey = intent.getStringExtra("childkeyPatient");
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        childkey2 = intent.getStringExtra("childkey2");
        score2 = intent.getIntExtra("score2",0);
        childkey3 = intent.getStringExtra("childkey3");
        score3 = intent.getIntExtra("score3",0);
        score4 = intent.getIntExtra("score4",0);
        childkey4 = intent.getStringExtra("childkey4");
        statusChildkey = intent.getStringExtra("childkeystatust");
        Log.d("what is passed?", "onCreate: " + score3);
        Log.d("what is passed?", "onCreate: " + childkey2);
        Log.d("what is passed?", "onCreate: " + childkey3);
        Log.d("what is passed?", "onCreate: " + score4);
        Log.d("Childkeystatonq5", "onDataChange: " + statusChildkey);

        Toast.makeText(this,"score 1 = " +score1, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this,"childkey 1 = " +childkey1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 2 = " +score2, Toast.LENGTH_SHORT).show();
    //    Toast.makeText(this,"childkey 2 = " +childkey2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 3 = " +score3, Toast.LENGTH_SHORT).show();
  //      Toast.makeText(this,"childkey 3 = " +childkey3, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 4 = " +score4, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this,"childkey 4 = " +childkey4, Toast.LENGTH_SHORT).show();
        resetbutton2 = findViewById(R.id.resetbutton2);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        answerRadioGroup2 = findViewById(R.id.answerRadioGroup2);
        answerRadioGroup3 = findViewById(R.id.answerRadioGroup3);
        answerRadioGroup4 = findViewById(R.id.answerRadioGroup4);
        answerRadioGroup4a = findViewById(R.id.answerRadioGroup4a);
        answerRadioGroup4b = findViewById(R.id.answerRadioGroup4b);
        answerRadioGroup5 = findViewById(R.id.answerRadioGroup5);
        answerRadioGroup6 = findViewById(R.id.answerRadioGroup6);
        answerRadioGroup7 = findViewById(R.id.answerRadioGroup7);
        answerRadioGroup8 = findViewById(R.id.answerRadioGroup8);
        answerRadioGroup9 = findViewById(R.id.answerRadioGroup9);
        answerRadioGroup10 = findViewById(R.id.answerRadioGroup10);
        answerRadioGroup11 = findViewById(R.id.answerRadioGroup11);
        answerRadioGroup12 = findViewById(R.id.answerRadioGroup12);
        answerRadioGroup13 = findViewById(R.id.answerRadioGroup13);
        answerRadioGroup4c = findViewById(R.id.answerRadioGroup4c);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionA2 = findViewById(R.id.optionA2);
        optionB2 = findViewById(R.id.optionB2);
        optionC2 = findViewById(R.id.optionC2);
        optionD2 = findViewById(R.id.optionD2);
        optionE2 = findViewById(R.id.optionE2);
        optionA3 = findViewById(R.id.optionA3);
        optionB3 = findViewById(R.id.optionB3);
        optionC3 = findViewById(R.id.optionC3);
        optionA4 = findViewById(R.id.optionA4);
        optionB4 = findViewById(R.id.optionB4);
        optionC4 = findViewById(R.id.optionC4);
        optionD4 = findViewById(R.id.optionD4);
        optionA5 = findViewById(R.id.optionA5);
        optionB5 = findViewById(R.id.optionB5);
        optionC5 = findViewById(R.id.optionC5);
        optionA6 = findViewById(R.id.optionA6);
        optionB6 = findViewById(R.id.optionB6);
        optionC6 = findViewById(R.id.optionC6);
        optionA7 = findViewById(R.id.optionA7);
        optionB7 = findViewById(R.id.optionB7);
        optionC7 = findViewById(R.id.optionC7);
        optionA8 = findViewById(R.id.optionA8);
        optionB8 = findViewById(R.id.optionB8);
        optionC8 = findViewById(R.id.optionC8);
        optionD8 = findViewById(R.id.optionD8);
        optionA9 = findViewById(R.id.optionA9);
        optionB9 = findViewById(R.id.optionB9);
        optionC9 = findViewById(R.id.optionC9);
        optionA10 = findViewById(R.id.optionA10);
        optionB10 = findViewById(R.id.optionB10);
        optionC10 = findViewById(R.id.optionC10);
        optionA11 = findViewById(R.id.optionA11);
        optionB11 = findViewById(R.id.optionB11);
        optionC11 = findViewById(R.id.optionC11);
        optionA12 = findViewById(R.id.optionA12);
        optionB12 = findViewById(R.id.optionB12);
        optionC12 = findViewById(R.id.optionC12);
        optionA13 = findViewById(R.id.optionA13);
        optionB13 = findViewById(R.id.optionB13);
        optionC13 = findViewById(R.id.optionC13);
        optionE4 = findViewById(R.id.optionE4);

        editTextForCheckBox1 = findViewById(R.id.editTextForCheckBox1);
        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
        editTextOptionA10 = findViewById(R.id.editTextOptionA10);
        editTextOptionA6 = findViewById(R.id.editTextOptionA6);
        editTextOptionA7 = findViewById(R.id.editTextOptionA7);
        editTextOptionA13 = findViewById(R.id.editTextOptionA13marked);

        resetbutton1 = findViewById(R.id.resetbutton1);
        continueButton = findViewById(R.id.continueButton);
      //  cancelselection();
        radiofunc();
        savetofb();




    }
    public void cancelselection(){
        CompoundButton.OnCheckedChangeListener radioButtonCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (buttonView.getId() == R.id.optionA2) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                answerRadioGroup2f.clearCheck();
                            }

                        });
                    }if (buttonView.getId() == R.id.optionB2) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                answerRadioGroup2f.clearCheck();

                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionC2) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                answerRadioGroup2f.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionD2) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                answerRadioGroup2f.clearCheck();
                                optionA2.setEnabled(true);
                                optionB2.setEnabled(true);
                                optionC2.setEnabled(true);
                                optionD2.setEnabled(true);
                                optionE2.setEnabled(true);
                                optionF2.setEnabled(true);


                            }


                        });
                        optionA2.setEnabled(false);
                        optionB2.setEnabled(false);
                        optionC2.setEnabled(false);
                        optionD2.setEnabled(false);
                        optionE2.setEnabled(false);
                        optionF2.setEnabled(true);
                    }
                   /* if (buttonView.getId() == R.id.optionF2) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                answerRadioGroup2f.clearCheck();
                            }

                        });
                    }*/
                    if (buttonView.getId() == R.id.optionA4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionB4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionC4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                                optionA4.setEnabled(true);
                                optionB4.setEnabled(true);
                                optionC4.setEnabled(true);
                                optionD4.setEnabled(true);

                            }

                        });
                        optionA4.setEnabled(false);
                        optionB4.setEnabled(false);
                        optionC4.setEnabled(false);
                        optionD4.setEnabled(true);
                    }
                }

            }
        };
        optionA2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionD2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionE2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionF2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionA4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionD4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);


    }
    public void radiofunc() {

        optionA10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA10.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA10.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA5.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA5.setVisibility(View.INVISIBLE);


                }
            }
        });

        optionA6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA6.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA6.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA13.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA13.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA7.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA7.setVisibility(View.INVISIBLE);


                }
            }
        });
    }

    public void savetofb (){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerRadioGroup.getCheckedRadioButtonId() == -1 || answerRadioGroup3.getCheckedRadioButtonId() == -1||  answerRadioGroup5.getCheckedRadioButtonId() == -1
                        || answerRadioGroup6.getCheckedRadioButtonId() == -1|| answerRadioGroup7.getCheckedRadioButtonId() == -1|| answerRadioGroup8.getCheckedRadioButtonId() == -1
                        ||
                        answerRadioGroup10.getCheckedRadioButtonId() == -1|| answerRadioGroup11.getCheckedRadioButtonId() == -1|| answerRadioGroup12.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(),"Anda harus mengisi semua pertanyaan",Toast.LENGTH_LONG).show();
                }else {
                    getscore();
                    String selectedAnswer1 = getSelectedAnswer(answerRadioGroup);
                    String selectedAnswer2 = getSelectedAnswer(answerRadioGroup2);
                    String selectedAnswer3 = getSelectedAnswer(answerRadioGroup3);
                    String selectedAnswer4 = getSelectedAnswer(answerRadioGroup4);
                    String selectedAnswer5 = getSelectedAnswer(answerRadioGroup5);
                    String selectedAnswer6 = getSelectedAnswer(answerRadioGroup6);
                    String selectedAnswer7 = getSelectedAnswer(answerRadioGroup7);
                    String selectedAnswer8 = getSelectedAnswer(answerRadioGroup8);
                    String selectedAnswer9 = getSelectedAnswer(answerRadioGroup9);
                    String selectedAnswer10 = getSelectedAnswer(answerRadioGroup10);
                    String selectedAnswer11 = getSelectedAnswer(answerRadioGroup11);
                    String selectedAnswer12 = getSelectedAnswer(answerRadioGroup12);
                    String selectedAnswer13 = getSelectedAnswer(answerRadioGroup13);
                    AnswerModel5 answerModel5 = new AnswerModel5();
                    answerModel5.setAnswer1(selectedAnswer1);
                    answerModel5.setAnswer2(selectedAnswer2);
                    answerModel5.setAnswer3(selectedAnswer3);
                    answerModel5.setAnswer4(selectedAnswer4);
                    answerModel5.setAnswer5(selectedAnswer5);
                    answerModel5.setAnswer6(selectedAnswer6);
                    answerModel5.setAnswer7(selectedAnswer7);
                    answerModel5.setAnswer8(selectedAnswer8);
                    answerModel5.setAnswer9(selectedAnswer9);
                    answerModel5.setAnswer10(selectedAnswer10);
                    answerModel5.setAnswer11(selectedAnswer11);
                    answerModel5.setAnswer12(selectedAnswer12);
                    answerModel5.setAnswer13(selectedAnswer13);

                    if ("Ya".equals(selectedAnswer5)) {
                        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
                        String editTextValue5 = editTextOptionA5.getText().toString().trim();
                        answerModel5.setAnswer5(editTextValue5);
                    }

                    if ("Ya".equals(selectedAnswer6)) {
                        editTextOptionA6 = findViewById(R.id.editTextOptionA6);
                        String editTextValue6 = editTextOptionA6.getText().toString().trim();
                        answerModel5.setAnswer6(editTextValue6);
                    }

                    if ("Ya".equals(selectedAnswer7)) {
                        editTextOptionA7 = findViewById(R.id.editTextOptionA7);
                        String editTextValue7 = editTextOptionA7.getText().toString().trim();
                        answerModel5.setAnswer7(editTextValue7);
                    }

                    if ("Ya".equals(selectedAnswer10)) {
                        editTextOptionA10 = findViewById(R.id.editTextOptionA10);
                        String editTextValue10 = editTextOptionA10.getText().toString().trim();
                        answerModel5.setAnswer10(editTextValue10);
                    }

                    if ("Ya".equals(selectedAnswer13)) {
                        editTextOptionA13 = findViewById(R.id.editTextOptionA13marked);

                        String editTextValue13 = editTextOptionA13.getText().toString().trim();
                        Log.d("edittext13", "onClick: "+ editTextValue13);
                        answerModel5.setAnswer13(editTextValue13);
                    }

// Save the data to Firebase
                    saveAnswersToFirebase(answerModel5);
                }

            }
        });
    }
    private String getSelectedAnswer(RadioGroup radioGroup) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            return radioButton.getText().toString();
        } else {
            return ""; // Handle the case where no answer is selected
        }
    }
    private void getscore(){
        Map<RadioButton, Integer> radioButtonScores = new HashMap<>();
        radioButtonScores.put(optionA, 3);
        radioButtonScores.put(optionB, 0);
        radioButtonScores.put(optionA2, 4);
        radioButtonScores.put(optionB2, 4);
        radioButtonScores.put(optionC2, 4);
        radioButtonScores.put(optionD2, 0);
        radioButtonScores.put(optionE2, 4);
        radioButtonScores.put(optionA3, 4);
        radioButtonScores.put(optionB3, 0);
        radioButtonScores.put(optionC3, 4);
        radioButtonScores.put(optionA4, 1);
        radioButtonScores.put(optionB4, 4);
        radioButtonScores.put(optionC4, 4);
        radioButtonScores.put(optionD4, 0);
        radioButtonScores.put(optionE4, 4);
        radioButtonScores.put(optionA5, 4);
        radioButtonScores.put(optionB5, 0);
        radioButtonScores.put(optionC5, 4);
        radioButtonScores.put(optionA6, 4);
        radioButtonScores.put(optionB6, 0);
        radioButtonScores.put(optionC6, 4);
        radioButtonScores.put(optionA7, 4);
        radioButtonScores.put(optionB7, 0);
        radioButtonScores.put(optionC7, 4);
        radioButtonScores.put(optionA8, 5);
        radioButtonScores.put(optionB8, 5);
        radioButtonScores.put(optionC8, 0);
        radioButtonScores.put(optionD8, 5);
        radioButtonScores.put(optionA9, 8);
        radioButtonScores.put(optionB9, 0);
        radioButtonScores.put(optionC9, 8);
        radioButtonScores.put(optionA10, 4);
        radioButtonScores.put(optionB10, 0);
        radioButtonScores.put(optionC10, 4);
        radioButtonScores.put(optionA11, 4);
        radioButtonScores.put(optionB11, 0);
        radioButtonScores.put(optionC11, 4);
        radioButtonScores.put(optionA12, 4);
        radioButtonScores.put(optionB12, 0);
        radioButtonScores.put(optionC12, 4);
        calctotalscore5 = 0;

        for (Map.Entry<RadioButton, Integer> entry : radioButtonScores.entrySet()) {
            RadioButton radioButton = entry.getKey();
            int score = entry.getValue();

            if (radioButton.isChecked()) {
                calctotalscore5 += score;
            }
        }





    }
    private void saveAnswersToFirebase(AnswerModel5 answerModel5) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        answerModel5.setScore(calctotalscore5);
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerModel5);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkey5 = childSnapshot.getKey(); // Get the key of the child node
                    AnswerModel5 answerModel = childSnapshot.getValue(AnswerModel5.class);

                    // Do something with the retrieved data and child key
                    Log.d("FirebaseData2", "Child Key: " + childkey5);
                    Log.d("FirebaseData2", "Answer Model: " + answerModel.toString());
                    Toast.makeText(getApplicationContext(), "total score :" + calctotalscore5, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(getApplicationContext(), "key:" + childkey5, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(question5.this, reportActivity.class);
                    intent.putExtra("score2", score2);
                    intent.putExtra("childkey2",childkey2);
                    intent.putExtra("score1", score1);
                    intent.putExtra("childkey1",childkey1);
                    intent.putExtra("score3", score3);
                    intent.putExtra("childkey3",childkey3);
                    intent.putExtra("score4", score4);
                    intent.putExtra("childkey4",childkey4);
                    intent.putExtra("childkey5",childkey5);
                    intent.putExtra("score5", calctotalscore5);
                    intent.putExtra("childkeyPatient", patientChildkey);
                    intent.putExtra("childkeystatust", statusChildkey);
                    Log.d("Childkeystatonq5", "onDataChange: " + statusChildkey);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}