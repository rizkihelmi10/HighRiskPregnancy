package com.kamus.highriskpregnancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class question3 extends AppCompatActivity {
    private Button continueButton;
    private RadioGroup answerRadioGroup,answerRadioGroup2,answerRadioGroup3,answerRadioGroup4,answerRadioGroup5,answerRadioGroup6
            ,answerRadioGroup7,answerRadioGroup8,answerRadioGroup9,answerRadioGroup10,answerRadioGroup11,answerRadioGroup12;
    private RadioButton optionA, optionB, optionA2, optionB2, optionC2, optionD2, optionA3, optionB3, optionA4, optionB4, optionC4, optionA5,
            optionB5,optionA6,optionB6, optionA7, optionB7,optionA8, optionB8,optionA9,optionB9,optionC9,optionA10,optionB10
            ,optionA11, optionB11,optionA12,optionB12, optionE2, optionD4, optionC6, optionC7, optionC11;
    private EditText editTextOptionA;
    private EditText editTextOptionA12;
    private EditText editTextOptionA5;
    private Button resetButton1,resetbutton2,resetbutton3;
    int calctotalscore3;
    int score1;
    int score2;
    String childkey2;
    String childkey1;
    String childkey3;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        Intent intent = getIntent();
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        childkey2 = intent.getStringExtra("childkey2");
        score2 = intent.getIntExtra("score2",0);
        Toast.makeText(this,"score 1 = " +score1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 1 = " +childkey1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 2 = " +score2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 2 = " +childkey2, Toast.LENGTH_SHORT).show();

        continueButton = findViewById(R.id.continueButton);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        answerRadioGroup2 = findViewById(R.id.answerRadioGroup2);
        answerRadioGroup3 = findViewById(R.id.answerRadioGroup3);
        answerRadioGroup4 = findViewById(R.id.answerRadioGroup4);
        answerRadioGroup5 = findViewById(R.id.answerRadioGroup5);
        answerRadioGroup6 = findViewById(R.id.answerRadioGroup6);
        answerRadioGroup7 = findViewById(R.id.answerRadioGroup7);
        answerRadioGroup8 = findViewById(R.id.answerRadioGroup8);
        answerRadioGroup9 = findViewById(R.id.answerRadioGroup9);
        answerRadioGroup10 = findViewById(R.id.answerRadioGroup10);
        answerRadioGroup11 = findViewById(R.id.answerRadioGroup11);
        answerRadioGroup12 = findViewById(R.id.answerRadioGroup12);

        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionA2 = findViewById(R.id.optionA2);
        optionB2 = findViewById(R.id.optionB2);
        optionC2 = findViewById(R.id.optionC2);
        optionD2 = findViewById(R.id.optionD2);
        optionE2 = findViewById(R.id.optionE2);
        optionA3 = findViewById(R.id.optionA3);
        optionB3 = findViewById(R.id.optionB3);
        optionA4 = findViewById(R.id.optionA4);
        optionB4 = findViewById(R.id.optionB4);
        optionC4 = findViewById(R.id.optionC4);
        optionD4 = findViewById(R.id.optionD4);
        optionA5 = findViewById(R.id.optionA5);
        optionB5 = findViewById(R.id.optionB5);
        optionA6 = findViewById(R.id.optionA6);
        optionB6 = findViewById(R.id.optionB6);
        optionC6 = findViewById(R.id.optionC6);
        optionA7 = findViewById(R.id.optionA7);
        optionB7 = findViewById(R.id.optionB7);
        optionC7 = findViewById(R.id.optionC7);
        optionA8 = findViewById(R.id.optionA8);
        optionB8 = findViewById(R.id.optionB8);
        optionA9 = findViewById(R.id.optionA9);
        optionB9 = findViewById(R.id.optionB9);
        optionC9 = findViewById(R.id.optionC9);
        optionA10 = findViewById(R.id.optionA10);
        optionB10 = findViewById(R.id.optionB10);
        optionA11 = findViewById(R.id.optionA11);
        optionB11 = findViewById(R.id.optionB11);
        optionC11 = findViewById(R.id.optionC11);
        optionA12 = findViewById(R.id.optionA12);
        optionB12 = findViewById(R.id.optionB12);

        editTextOptionA = findViewById(R.id.editTextOptionA);
        editTextOptionA12 = findViewById(R.id.edittextanswerA12);
        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
        savetofb();
        //cancelselection();
        radiofunc();


    }
    public void radiofunc() {

        optionA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA.setVisibility(View.INVISIBLE);
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) optionB.getLayoutParams();
                    layoutParams.setMargins(20,0,0,0);


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
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) optionB5.getLayoutParams();
                    layoutParams.setMargins(20,0,0,0);


                }
            }
        });
        optionA12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOptionA12.setVisibility(View.VISIBLE);


                } else {
                    editTextOptionA12.setVisibility(View.INVISIBLE);
                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) optionB12.getLayoutParams();
                    layoutParams.setMargins(20,0,0,0);

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
                         answerRadioGroup10.getCheckedRadioButtonId() == -1|| answerRadioGroup11.getCheckedRadioButtonId() == -1|| answerRadioGroup12.getCheckedRadioButtonId() == -1
                ) {
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
                    if ("Ya".equals(selectedAnswer1)) {
                        editTextOptionA = findViewById(R.id.editTextOptionA);
                        String editTextValue1 = editTextOptionA.getText().toString().trim();

                        AnswerModel3 answerModel3 = new AnswerModel3();
                        answerModel3.setAnswer1("Ya" + editTextValue1);
                        answerModel3.setAnswer2(selectedAnswer2);
                        answerModel3.setAnswer3(selectedAnswer3);
                        answerModel3.setAnswer4(selectedAnswer4);
                        answerModel3.setAnswer5(selectedAnswer5);
                        answerModel3.setAnswer6(selectedAnswer6);
                        answerModel3.setAnswer7(selectedAnswer7);
                        answerModel3.setAnswer8(selectedAnswer8);
                        answerModel3.setAnswer9(selectedAnswer9);
                        answerModel3.setAnswer10(selectedAnswer10);
                        answerModel3.setAnswer11(selectedAnswer11);
                        answerModel3.setAnswer12(selectedAnswer12);


                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel3);


                    } else if ( "Ya".equals(selectedAnswer5)) {
                        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
                        String editTextValue5 = editTextOptionA5.getText().toString().trim();

                        AnswerModel3 answerModel3 = new AnswerModel3();
                        answerModel3.setAnswer1(selectedAnswer1);
                        answerModel3.setAnswer2(selectedAnswer2);
                        answerModel3.setAnswer3(selectedAnswer3);
                        answerModel3.setAnswer4(selectedAnswer4);
                        answerModel3.setAnswer5("Ya" + editTextValue5);
                        answerModel3.setAnswer6(selectedAnswer6);
                        answerModel3.setAnswer7(selectedAnswer7);
                        answerModel3.setAnswer8(selectedAnswer8);
                        answerModel3.setAnswer9(selectedAnswer9);
                        answerModel3.setAnswer10(selectedAnswer10);
                        answerModel3.setAnswer11(selectedAnswer11);
                        answerModel3.setAnswer12(selectedAnswer12);


                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel3);


                    } else if ("Ya".equals(selectedAnswer5)) {
                        editTextOptionA12 = findViewById(R.id.editTextOptionA12);
                        String editTextValue12 = editTextOptionA12.getText().toString().trim();

                        AnswerModel3 answerModel3 = new AnswerModel3();
                        answerModel3.setAnswer1(selectedAnswer1);
                        answerModel3.setAnswer2(selectedAnswer2);
                        answerModel3.setAnswer3(selectedAnswer3);
                        answerModel3.setAnswer4(selectedAnswer4);
                        answerModel3.setAnswer5(selectedAnswer5);
                        answerModel3.setAnswer6(selectedAnswer6);
                        answerModel3.setAnswer7(selectedAnswer7);
                        answerModel3.setAnswer8(selectedAnswer8);
                        answerModel3.setAnswer9(selectedAnswer9);
                        answerModel3.setAnswer10(selectedAnswer10);
                        answerModel3.setAnswer11(selectedAnswer11);
                        answerModel3.setAnswer12("Ya" + editTextValue12);


                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel3);
                        
                    } else {
                        AnswerModel3 answerModel3 = new AnswerModel3();
                        answerModel3.setAnswer1(selectedAnswer1);
                        answerModel3.setAnswer2(selectedAnswer2);
                        answerModel3.setAnswer3(selectedAnswer3);
                        answerModel3.setAnswer4(selectedAnswer4);
                        answerModel3.setAnswer5(selectedAnswer5);
                        answerModel3.setAnswer6(selectedAnswer6);
                        answerModel3.setAnswer7(selectedAnswer7);
                        answerModel3.setAnswer8(selectedAnswer8);
                        answerModel3.setAnswer9(selectedAnswer9);
                        answerModel3.setAnswer10(selectedAnswer10);
                        answerModel3.setAnswer11(selectedAnswer11);
                        answerModel3.setAnswer12(selectedAnswer12);

                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel3);

                    }

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
   /* public void cancelselection(){
        CompoundButton.OnCheckedChangeListener radioButtonCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (buttonView.getId() == R.id.optionA2) {
                        resetButton1.setVisibility(View.VISIBLE);
                        resetButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetButton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                            }

                        });
                    }if (buttonView.getId() == R.id.optionB2) {
                        resetButton1.setVisibility(View.VISIBLE);
                        resetButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetButton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();

                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionC2) {
                        resetButton1.setVisibility(View.VISIBLE);
                        resetButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetButton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionD2) {
                        resetButton1.setVisibility(View.VISIBLE);
                        resetButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetButton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup2.clearCheck();
                                answerRadioGroup2a.clearCheck();
                                answerRadioGroup2b.clearCheck();
                                answerRadioGroup2d.clearCheck();
                                answerRadioGroup2e.clearCheck();
                                optionA2.setEnabled(true);
                                optionB2.setEnabled(true);
                                optionC2.setEnabled(true);
                                optionD2.setEnabled(true);
                                optionE2.setEnabled(true);


                            }


                        });
                        optionA2.setEnabled(false);
                        optionB2.setEnabled(false);
                        optionC2.setEnabled(false);
                        optionD2.setEnabled(false);
                        optionE2.setEnabled(false);
                    }
                    if (buttonView.getId() == R.id.optionA4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
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
                                optionA4.setEnabled(true);
                                optionB4.setEnabled(true);
                                optionC4.setEnabled(true);
                            }

                        });
                        optionA4.setEnabled(false);
                        optionB4.setEnabled(false);
                        optionC4.setEnabled(false);
                    }
                    if (buttonView.getId() == R.id.optionA9) {
                        resetbutton3.setVisibility(View.VISIBLE);
                        resetbutton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton3.setVisibility(View.INVISIBLE);
                                answerRadioGroup9.clearCheck();
                                answerRadioGroup9a.clearCheck();
                                answerRadioGroup9b.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionB9) {
                        resetbutton3.setVisibility(View.VISIBLE);
                        resetbutton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton3.setVisibility(View.INVISIBLE);
                                answerRadioGroup9.clearCheck();
                                answerRadioGroup9a.clearCheck();
                                answerRadioGroup9b.clearCheck();}

                        });
                    }
                    if (buttonView.getId() == R.id.optionC9) {
                        resetbutton3.setVisibility(View.VISIBLE);
                        resetbutton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton3.setVisibility(View.INVISIBLE);
                                answerRadioGroup9.clearCheck();
                                answerRadioGroup9a.clearCheck();
                                answerRadioGroup9b.clearCheck();
                                optionA9.setEnabled(true);
                                optionB9.setEnabled(true);
                                optionC9.setEnabled(true);
                            }


                        });
                        optionA9.setEnabled(false);
                        optionB9.setEnabled(false);
                        optionC9.setEnabled(false);
                    }
                }

            }
        };
        optionA2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionD2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionE2.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionA4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionA9.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB9.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC9.setOnCheckedChangeListener(radioButtonCheckedChangeListener);

    }*/
    private void getscore(){
        Map<RadioButton, Integer> radioButtonScores = new HashMap<>();
        radioButtonScores.put(optionA, 2);
        radioButtonScores.put(optionB, 0);
        radioButtonScores.put(optionA2, 1);
        radioButtonScores.put(optionB2, 1);
        radioButtonScores.put(optionC2, 1);
        radioButtonScores.put(optionD2, 0);
        radioButtonScores.put(optionA3, 4);
        radioButtonScores.put(optionB3, 0);
        radioButtonScores.put(optionA4, 2);
        radioButtonScores.put(optionB4, 2);
        radioButtonScores.put(optionC4, 2);
        radioButtonScores.put(optionD4, 0);
        radioButtonScores.put(optionA5, 1);
        radioButtonScores.put(optionB5, 0);
        radioButtonScores.put(optionA6, 2);
        radioButtonScores.put(optionB6, 2);
        radioButtonScores.put(optionC6, 0);
        radioButtonScores.put(optionA7, 2);
        radioButtonScores.put(optionB7, 2);
        radioButtonScores.put(optionC7, 0);
        radioButtonScores.put(optionA8, 4);
        radioButtonScores.put(optionB8, 0);
        radioButtonScores.put(optionA9, 1);
        radioButtonScores.put(optionB9, 1);
        radioButtonScores.put(optionC9, 0);
        radioButtonScores.put(optionA10, 1);
        radioButtonScores.put(optionB10, 0);
        radioButtonScores.put(optionA11, 2);
        radioButtonScores.put(optionB11, 2);
        radioButtonScores.put(optionC11, 0);
        radioButtonScores.put(optionA12, 2);
        radioButtonScores.put(optionB12, 0);
        calctotalscore3 = 0;

        for (Map.Entry<RadioButton, Integer> entry : radioButtonScores.entrySet()) {
            RadioButton radioButton = entry.getKey();
            int score = entry.getValue();

            if (radioButton.isChecked()) {
                calctotalscore3 += score;
            }
        }





    }
    private void saveAnswersToFirebase(AnswerModel3 answerModel3) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        answerModel3.setScore(calctotalscore3);
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerModel3);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkey3 = childSnapshot.getKey(); // Get the key of the child node
                    AnswerModel2 answerModel = childSnapshot.getValue(AnswerModel2.class);

                    // Do something with the retrieved data and child key
                    Log.d("FirebaseData2", "Child Key: " + childkey3);
                    Log.d("FirebaseData2", "Answer Model: " + answerModel.toString());
                    Toast.makeText(getApplicationContext(), "total score :" + calctotalscore3, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "key:" + childkey3, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(question3.this, question4.class);
                    intent.putExtra("score2", score2);
                    intent.putExtra("childkey2",childkey2);
                    intent.putExtra("score1", score1);
                    intent.putExtra("childkey1",childkey1);
                    intent.putExtra("score3", calctotalscore3);
                    intent.putExtra("childkey3",childkey3);
                   startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}