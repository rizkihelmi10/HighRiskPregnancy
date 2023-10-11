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

public class question2 extends AppCompatActivity {
    private TextView questionTextView1, questionTextView2; // Add other TextViews as needed
    private RadioGroup answerRadioGroup, answerRadioGroup2,answerRadioGroup3,answerRadioGroup4,answerRadioGroup5
            ,answerRadioGroup6,answerRadioGroup7,answerRadioGroup8,answerRadioGroup9,answerRadioGroup10,answerRadioGroup11,answerRadioGroup12; // Add other RadioGroups as needed
    private RadioButton optionA,optionA2, optionB, optionA3, optionB2, optionA4,optionA5,optionA6,optionA7,
            optionA8,optionA9,optionA10,optionA11,optionA12,optionB3,optionB4,optionB5,optionB6,optionB7,optionB8,optionB9,
            optionB10,optionB11, optionB12; // Add other RadioButtons as needed
    private EditText editTextOptionA, editTextOptionA2,editTextOptionA3,editTextOptionA4,editTextOptionA5,editTextOptionA6,editTextOptionA7,
            editTextOptionA8,editTextOptionA9,editTextOptionA10,editTextOptionA11,editTextOptionA12; // Add other EditTexts as needed
    private Button continueButton;
    int calctotalscore2;
    int score1;
    String childkey2;
    String childkey1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        Intent intent = getIntent();
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        Toast.makeText(this,"score 1 = " +score1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"childkey 1 = " +childkey1, Toast.LENGTH_SHORT).show();

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
        optionA2 = findViewById(R.id.optionA2);
        optionA3 = findViewById(R.id.optionA3);
        optionA4 = findViewById(R.id.optionA4);
        optionA5 = findViewById(R.id.optionA5);
        optionA6 = findViewById(R.id.optionA6);
        optionA7 = findViewById(R.id.optionA7);
        optionA8 = findViewById(R.id.optionA8);
        optionA9 = findViewById(R.id.optionA9);
        optionA10 = findViewById(R.id.optionA10);
        optionA11 = findViewById(R.id.optionA11);
        optionA12 = findViewById(R.id.optionA12);

        optionB = findViewById(R.id.optionB);
        optionB2 = findViewById(R.id.optionB2);
        optionB3 = findViewById(R.id.optionB3);
        optionB4 = findViewById(R.id.optionB4);
        optionB5 = findViewById(R.id.optionB5);
        optionB6 = findViewById(R.id.optionB6);
        optionB7 = findViewById(R.id.optionB7);
        optionB8 = findViewById(R.id.optionB8);
        optionB9 = findViewById(R.id.optionB9);
        optionB10 = findViewById(R.id.optionB10);
        optionB11 = findViewById(R.id.optionB11);
        optionB12 = findViewById(R.id.optionB12);

        editTextOptionA = findViewById(R.id.editTextOptionA);
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
        continueButton = findViewById(R.id.continueButton);
        radiofunc();
        savetofb();



    }

    public void radiofunc(){

        optionA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextOptionA.setVisibility(View.VISIBLE);



                }else{
                    editTextOptionA.setVisibility(View.INVISIBLE);


                }
            }
        });
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
    private boolean areAllQuestionsAnswered() {
        boolean isRadioGroupAnswered =isRadioGroupAnswered(answerRadioGroup) && isRadioGroupAnswered(answerRadioGroup)&& isRadioGroupAnswered(answerRadioGroup3)&& isRadioGroupAnswered(answerRadioGroup4)
                && isRadioGroupAnswered(answerRadioGroup5)&& isRadioGroupAnswered(answerRadioGroup6)&& isRadioGroupAnswered(answerRadioGroup7)&& isRadioGroupAnswered(answerRadioGroup8)
                && isRadioGroupAnswered(answerRadioGroup9)&& isRadioGroupAnswered(answerRadioGroup10)&& isRadioGroupAnswered(answerRadioGroup11)&& isRadioGroupAnswered(answerRadioGroup12)
                ;
        boolean areEditTextAnswered = isEditTextAnswered(editTextOptionA) && isEditTextAnswered(editTextOptionA2) && isEditTextAnswered(editTextOptionA3) && isEditTextAnswered(editTextOptionA4)
                && isEditTextAnswered(editTextOptionA5) && isEditTextAnswered(editTextOptionA6) && isEditTextAnswered(editTextOptionA7) && isEditTextAnswered(editTextOptionA8) && isEditTextAnswered(editTextOptionA9) && isEditTextAnswered(editTextOptionA10) && isEditTextAnswered(editTextOptionA11)
                && isEditTextAnswered(editTextOptionA12);
        return isRadioGroupAnswered && areEditTextAnswered;
        // Add more RadioGroups as needed
    }

    // Helper function to check if a RadioGroup has at least one RadioButton selected
    private boolean isRadioGroupAnswered(RadioGroup radioGroup) {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return selectedRadioButtonId != -1;
    }
    private boolean isEditTextAnswered(EditText editText) {
        String text = editText.getText().toString().trim();
        return !text.isEmpty();
    }
    public void savetofb (){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerRadioGroup.getCheckedRadioButtonId() == -1 || answerRadioGroup2.getCheckedRadioButtonId() == -1 || answerRadioGroup5.getCheckedRadioButtonId() == -1
                        || answerRadioGroup6.getCheckedRadioButtonId() == -1|| answerRadioGroup7.getCheckedRadioButtonId() == -1|| answerRadioGroup3.getCheckedRadioButtonId() == -1
                        || answerRadioGroup4.getCheckedRadioButtonId() == -1|| answerRadioGroup8.getCheckedRadioButtonId() == -1|| answerRadioGroup9.getCheckedRadioButtonId() == -1
                        || answerRadioGroup10.getCheckedRadioButtonId() == -1|| answerRadioGroup11.getCheckedRadioButtonId() == -1|| answerRadioGroup12.getCheckedRadioButtonId() == -1) {
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
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1("Ya, " + editTextValue1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);

                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel2);



                    } else if ( "Ya".equals(selectedAnswer2)) {
                        editTextOptionA2 = findViewById(R.id.editTextOptionA2);
                        String editTextValue2 = editTextOptionA2.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2("Ya, " + editTextValue2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);


                    } else if ("Ya".equals(selectedAnswer3)) {
                        editTextOptionA3 = findViewById(R.id.editTextOptionA3);
                        String editTextValue3 = editTextOptionA3.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3("Ya, " + editTextValue3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer4)) {
                        editTextOptionA4 = findViewById(R.id.editTextOptionA4);
                        String editTextValue4 = editTextOptionA4.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4("Ya, " + editTextValue4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer5)) {
                        editTextOptionA5 = findViewById(R.id.editTextOptionA5);
                        String editTextValue5 = editTextOptionA5.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5("Ya, " + editTextValue5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer6)) {
                        editTextOptionA6 = findViewById(R.id.editTextOptionA6);
                        String editTextValue6 = editTextOptionA6.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6("Ya, " + editTextValue6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer7)) {
                        editTextOptionA7 = findViewById(R.id.editTextOptionA7);
                        String editTextValue7 = editTextOptionA7.getText().toString().trim();

                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7("Ya, " + editTextValue7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer8)) {
                        editTextOptionA8 = findViewById(R.id.editTextOptionA8);
                        String editTextValue8 = editTextOptionA8.getText().toString().trim();
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8("Ya, " + editTextValue8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);

                    }else if("Ya".equals(selectedAnswer9)){
                        editTextOptionA9 = findViewById(R.id.editTextOptionA9);
                        String editTextValue9 = editTextOptionA9.getText().toString().trim();
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9("Ya, " + editTextValue9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    }else if("Ya".equals(selectedAnswer10)){
                        editTextOptionA10 = findViewById(R.id.editTextOptionA10);
                        String editTextValue10 = editTextOptionA10.getText().toString().trim();
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10("Ya, " + editTextValue10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);

                    } else if ( "Ya".equals(selectedAnswer11)) {
                        editTextOptionA11 = findViewById(R.id.editTextOptionA11);
                        String editTextValue11 = editTextOptionA11.getText().toString().trim();
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11("Ya, " + editTextValue11);
                        answerModel2.setAnswer12(selectedAnswer12);
                        saveAnswersToFirebase(answerModel2);
                    } else if ("Ya".equals(selectedAnswer12)) {
                        editTextOptionA12 = findViewById(R.id.editTextOptionA12);
                        String editTextValue12 = editTextOptionA12.getText().toString().trim();
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12("Ya, " + editTextValue12);
                        saveAnswersToFirebase(answerModel2);
                    } else {
                        AnswerModel2 answerModel2 = new AnswerModel2();
                        answerModel2.setAnswer1(selectedAnswer1);
                        answerModel2.setAnswer2(selectedAnswer2);
                        answerModel2.setAnswer3(selectedAnswer3);
                        answerModel2.setAnswer4(selectedAnswer4);
                        answerModel2.setAnswer5(selectedAnswer5);
                        answerModel2.setAnswer6(selectedAnswer6);
                        answerModel2.setAnswer7(selectedAnswer7);
                        answerModel2.setAnswer8(selectedAnswer8);
                        answerModel2.setAnswer9(selectedAnswer9);
                        answerModel2.setAnswer10(selectedAnswer10);
                        answerModel2.setAnswer11(selectedAnswer11);
                        answerModel2.setAnswer12(selectedAnswer12);

                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel2);

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
        radioButtonScores.put(optionA, 2);
        radioButtonScores.put(optionB, 0);
        radioButtonScores.put(optionA2, 2);
        radioButtonScores.put(optionB2, 0);
        radioButtonScores.put(optionA3, 2);
        radioButtonScores.put(optionB3, 0);
        radioButtonScores.put(optionA4, 1);
        radioButtonScores.put(optionB4, 0);
        radioButtonScores.put(optionA5, 1);
        radioButtonScores.put(optionB5, 0);
        radioButtonScores.put(optionA6, 2);
        radioButtonScores.put(optionB6, 0);
        radioButtonScores.put(optionA7, 2);
        radioButtonScores.put(optionB7, 0);
        radioButtonScores.put(optionA8, 2);
        radioButtonScores.put(optionB8, 0);
        radioButtonScores.put(optionA9, 1);
        radioButtonScores.put(optionB9, 0);
        radioButtonScores.put(optionA10, 2);
        radioButtonScores.put(optionB10, 0);
        radioButtonScores.put(optionA11, 4);
        radioButtonScores.put(optionB11, 0);
        radioButtonScores.put(optionA12, 2);
        radioButtonScores.put(optionB12, 0);
        calctotalscore2 = 0;

        for (Map.Entry<RadioButton, Integer> entry : radioButtonScores.entrySet()) {
            RadioButton radioButton = entry.getKey();
            int score = entry.getValue();

            if (radioButton.isChecked()) {
                calctotalscore2 += score;
            }
        }





    }
    private void saveAnswersToFirebase(AnswerModel2 answerModel2) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        answerModel2.setScore(calctotalscore2);
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerModel2);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkey2 = childSnapshot.getKey(); // Get the key of the child node
                    AnswerModel2 answerModel = childSnapshot.getValue(AnswerModel2.class);

                    // Do something with the retrieved data and child key
                    Log.d("FirebaseData2", "Child Key: " + childkey2);
                    Log.d("FirebaseData2", "Answer Model: " + answerModel.toString());
                    Toast.makeText(getApplicationContext(), "total score :" + calctotalscore2, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "key:" + childkey2, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(question2.this, question3.class);
                    intent.putExtra("score2", calctotalscore2);
                    intent.putExtra("childkey2",childkey2);
                    intent.putExtra("score1", score1);
                    intent.putExtra("childkey1",childkey1);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}