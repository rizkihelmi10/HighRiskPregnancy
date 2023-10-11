package com.kamus.highriskpregnancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class question4 extends AppCompatActivity {
    private RadioGroup answerRadioGroup1;
    private RadioGroup answerRadioGroup2;
    private RadioGroup answerRadioGroup3;
    private RadioGroup answerRadioGroup4;
    private RadioGroup answerRadioGroup5;
    private RadioGroup answerRadioGroup6;
    private RadioGroup answerRadioGroup7;
    private RadioGroup answerRadioGroup8;
    private RadioGroup answerRadioGroup9;
    private RadioGroup answerRadioGroup10;
    private RadioGroup answerRadioGroup11;
    private RadioGroup answerRadioGroup12;

    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC1;
    private RadioButton optionA2;
    private RadioButton optionB2;
    private RadioButton optionC2;
    private RadioButton optionA3;
    private RadioButton optionB3;
    private RadioButton optionC3;
    private RadioButton optionA4;
    private RadioButton optionB4;
    private RadioButton optionC4;
    private RadioButton optionA5;
    private RadioButton optionB5;
    private RadioButton optionC5;
    private RadioButton optionA6;
    private RadioButton optionB6;
    private RadioButton optionC6;
    private RadioButton optionA7;
    private RadioButton optionB7;
    private RadioButton optionC7;
    private RadioButton optionA8;
    private RadioButton optionB8;
    private RadioButton optionC8;
    private RadioButton optionA9;
    private RadioButton optionB9;
    private RadioButton optionC9;
    private RadioButton optionA10;
    private RadioButton optionB10;
    private RadioButton optionC10;
    private RadioButton optionA11;
    private RadioButton optionB11;
    private RadioButton optionC11;
    private RadioButton optionA12;
    private RadioButton optionB12;
    private RadioButton optionC12;

    private EditText editTextOptionA2;
    private EditText editTextOptionA3;
    private EditText editTextOptionA4;
    private EditText editTextOptionA5;
    private EditText editTextOptionA6;
    private EditText editTextOptionA7;
    private EditText editTextOptionA8;
    private EditText editTextOptionA9;
    private EditText editTextOptionA10;
    private EditText editTextOptionA11;
    private EditText editTextOptionA12;

    private Button continueButton;
    int calctotalscore4;
    int score1;
    int score2;
    int score3;
    String childkey2;
    String childkey1;
    String childkey3;
    String childkey4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question4);
        Intent intent = getIntent();
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        childkey2 = intent.getStringExtra("childkey2");
        score2 = intent.getIntExtra("score2",0);
        childkey3 = intent.getStringExtra("childkey3");
        score3 = intent.getIntExtra("score3",0);
        Log.d("what is passed?", "onCreate: " + score3);
        Log.d("what is passed?", "onCreate: " + childkey2);
        Log.d("what is passed?", "onCreate: " + childkey3);

        Toast.makeText(this,"score 1 = " +score1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 1 = " +childkey1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 2 = " +score2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 2 = " +childkey2, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"score 3 = " +score3, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 3 = " +childkey3, Toast.LENGTH_SHORT).show();
        answerRadioGroup1 = findViewById(R.id.answerRadioGroup);
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

        // Initialize RadioButtons for each answer option
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC1 = findViewById(R.id.optionC);
        optionA2 = findViewById(R.id.optionA2);
        optionB2 = findViewById(R.id.optionB2);
        optionC2 = findViewById(R.id.optionC2);
        optionA3 = findViewById(R.id.optionA3);
        optionB3 = findViewById(R.id.optionB3);
        optionC3 = findViewById(R.id.optionC3);
        optionA4 = findViewById(R.id.optionA4);
        optionB4 = findViewById(R.id.optionB4);
        optionC4 = findViewById(R.id.optionC4);
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

        // Initialize EditTexts for specific questions
        editTextOptionA2 = findViewById(R.id.editTextOptionA2);
        editTextOptionA3 = findViewById(R.id.editTextOptionA3);
        editTextOptionA4 = findViewById(R.id.editTextOptionA4);
        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
        editTextOptionA6 = findViewById(R.id.editTextOptionA6);
        editTextOptionA7 = findViewById(R.id.editTextOptionA7);
        editTextOptionA8 = findViewById(R.id.editTextOptionA8);
        editTextOptionA9 = findViewById(R.id.editTextOptionA9);
        editTextOptionA10 = findViewById(R.id.editTextOptionA10);
        editTextOptionA11 = findViewById(R.id.editTextOptionA11);
        editTextOptionA12 = findViewById(R.id.editTextOptionA12);

        // Initialize Continue Button
        continueButton = findViewById(R.id.continueButton);
        radiofunc();
        savetofb();

    }
    public void radiofunc(){

        optionA2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA2.setVisibility(View.VISIBLE);

                }else{
                    editTextOptionA2.setVisibility(View.INVISIBLE);
                }
            }
        });
        optionA3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA3.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA3.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA4.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA4.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA5.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA5.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA6.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA6.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA7.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA7.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA8.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA8.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA9.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA9.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA10.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA10.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA11.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA11.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionA12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA12.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA12.setVisibility(View.INVISIBLE);


                }
            }
        });


    }
    public void savetofb () {
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerRadioGroup1.getCheckedRadioButtonId() == -1 || answerRadioGroup2.getCheckedRadioButtonId() == -1 || answerRadioGroup5.getCheckedRadioButtonId() == -1
                        || answerRadioGroup6.getCheckedRadioButtonId() == -1 || answerRadioGroup7.getCheckedRadioButtonId() == -1 || answerRadioGroup3.getCheckedRadioButtonId() == -1
                        || answerRadioGroup4.getCheckedRadioButtonId() == -1 || answerRadioGroup8.getCheckedRadioButtonId() == -1 || answerRadioGroup9.getCheckedRadioButtonId() == -1
                        || answerRadioGroup10.getCheckedRadioButtonId() == -1 || answerRadioGroup11.getCheckedRadioButtonId() == -1 || answerRadioGroup12.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Anda harus mengisi semua pertanyaan", Toast.LENGTH_LONG).show();
                } else {
                    getscore();
                    String selectedAnswer1 = getSelectedAnswer(answerRadioGroup1);
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
                     if ( "Ya".equals(selectedAnswer2)) {
                        editTextOptionA2 = findViewById(R.id.editTextOptionA2);
                        String editTextValue2 = editTextOptionA2.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2("Ya, " + editTextValue2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);


                    } else if ("Ya".equals(selectedAnswer3)) {
                        editTextOptionA3 = findViewById(R.id.editTextOptionA3);
                        String editTextValue3 = editTextOptionA3.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3("Ya, " + editTextValue3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer4)) {
                        editTextOptionA4 = findViewById(R.id.editTextOptionA4);
                        String editTextValue4 = editTextOptionA4.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4("Ya, " + editTextValue4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer5)) {
                        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
                        String editTextValue5 = editTextOptionA5.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5("Ya, " + editTextValue5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer6)) {
                        editTextOptionA6 = findViewById(R.id.editTextOptionA6);
                        String editTextValue6 = editTextOptionA6.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6("Ya, " + editTextValue6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer7)) {
                        editTextOptionA7 = findViewById(R.id.editTextOptionA7);
                        String editTextValue7 = editTextOptionA7.getText().toString().trim();

                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7("Ya, " + editTextValue7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer8)) {
                        editTextOptionA8 = findViewById(R.id.editTextOptionA8);
                        String editTextValue8 = editTextOptionA8.getText().toString().trim();
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8("Ya, " + editTextValue8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);

                    }else if("Ya".equals(selectedAnswer9)){
                        editTextOptionA9 = findViewById(R.id.editTextOptionA9);
                        String editTextValue9 = editTextOptionA9.getText().toString().trim();
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9("Ya, " + editTextValue9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    }else if("Ya".equals(selectedAnswer10)){
                        editTextOptionA10 = findViewById(R.id.editTextOptionA10);
                        String editTextValue10 = editTextOptionA10.getText().toString().trim();
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10("Ya, " + editTextValue10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);

                    } else if ( "Ya".equals(selectedAnswer11)) {
                        editTextOptionA11 = findViewById(R.id.editTextOptionA11);
                        String editTextValue11 = editTextOptionA11.getText().toString().trim();
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11("Ya, " + editTextValue11);
                        answerModel4.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel4);
                    } else if ("Ya".equals(selectedAnswer12)) {
                        editTextOptionA12 = findViewById(R.id.editTextOptionA12);
                        String editTextValue12 = editTextOptionA12.getText().toString().trim();
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12("Ya, " + editTextValue12);
                        saveAnswersToFirebase(answerModel4);
                    } else {
                        AnswerModel4 answerModel4 = new AnswerModel4();
                        answerModel4.setAnswer1(selectedAnswer1);
                        answerModel4.setAnswer2(selectedAnswer2);
                        answerModel4.setAnswer3(selectedAnswer3);
                        answerModel4.setAnswer4(selectedAnswer4);
                        answerModel4.setAnswer5(selectedAnswer5);
                        answerModel4.setAnswer6(selectedAnswer6);
                        answerModel4.setAnswer7(selectedAnswer7);
                        answerModel4.setAnswer8(selectedAnswer8);
                        answerModel4.setAnswer9(selectedAnswer9);
                        answerModel4.setAnswer10(selectedAnswer10);
                        answerModel4.setAnswer11(selectedAnswer11);
                        answerModel4.setAnswer12(selectedAnswer12);

                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel4);

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
    private void getscore(){
        Map<RadioButton, Integer> radioButtonScores = new HashMap<>();
        radioButtonScores.put(optionA, 4);
        radioButtonScores.put(optionB, 0);
        radioButtonScores.put(optionC1, 4);
        radioButtonScores.put(optionA2, 4);
        radioButtonScores.put(optionB2, 0);
        radioButtonScores.put(optionC2, 4);
        radioButtonScores.put(optionA3, 4);
        radioButtonScores.put(optionB3, 0);
        radioButtonScores.put(optionC3, 4);
        radioButtonScores.put(optionA4, 4);
        radioButtonScores.put(optionB4, 0);
        radioButtonScores.put(optionC4, 4);
        radioButtonScores.put(optionA5, 4);
        radioButtonScores.put(optionB5, 0);
        radioButtonScores.put(optionC5, 4);
        radioButtonScores.put(optionA6, 4);
        radioButtonScores.put(optionB6, 0);
        radioButtonScores.put(optionC6, 4);
        radioButtonScores.put(optionA7, 4);
        radioButtonScores.put(optionB7, 0);
        radioButtonScores.put(optionC7, 4);
        radioButtonScores.put(optionA8, 4);
        radioButtonScores.put(optionB8, 0);
        radioButtonScores.put(optionC8, 4);
        radioButtonScores.put(optionA9, 4);
        radioButtonScores.put(optionB9, 0);
        radioButtonScores.put(optionC9, 4);
        radioButtonScores.put(optionA10, 4);
        radioButtonScores.put(optionB10, 0);
        radioButtonScores.put(optionC10, 4);
        radioButtonScores.put(optionA11, 4);
        radioButtonScores.put(optionB11, 0);
        radioButtonScores.put(optionC11, 4);
        radioButtonScores.put(optionA12, 4);
        radioButtonScores.put(optionB12, 0);
        radioButtonScores.put(optionC12, 4);
        calctotalscore4 = 0;

        for (Map.Entry<RadioButton, Integer> entry : radioButtonScores.entrySet()) {
            RadioButton radioButton = entry.getKey();
            int score = entry.getValue();

            if (radioButton.isChecked()) {
                calctotalscore4 += score;
            }
        }





    }
    private void saveAnswersToFirebase(AnswerModel4 answerModel4) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        answerModel4.setScore(calctotalscore4);
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerModel4);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkey4 = childSnapshot.getKey(); // Get the key of the child node
                    AnswerModel4 answerModel = childSnapshot.getValue(AnswerModel4.class);

                    // Do something with the retrieved data and child key
                    Log.d("FirebaseData2", "Child Key: " + childkey4);
                    Log.d("FirebaseData2", "Answer Model: " + answerModel.toString());
                    Toast.makeText(getApplicationContext(), "total score :" + calctotalscore4, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "key:" + childkey4, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(question4.this, question5.class);
                    intent.putExtra("score2", score2);
                    intent.putExtra("childkey2",childkey2);
                    intent.putExtra("score1", score1);
                    intent.putExtra("childkey1",childkey1);
                    intent.putExtra("score3", score3);
                    intent.putExtra("childkey3",childkey3);
                    intent.putExtra("score4", calctotalscore4);
                    intent.putExtra("childkey4",childkey4);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}