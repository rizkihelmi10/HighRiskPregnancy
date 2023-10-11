package com.kamus.highriskpregnancy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class question1 extends AppCompatActivity {
    private TextView questionTextView1, questionTextView2, questionTextView3, questionTextView4, questionTextView5, questionTextView6, questionTextView7;
    private RadioGroup answerRadioGroup, answerRadioGroup2, answerRadioGroup3, answerRadioGroup4, answerRadioGroup5, answerRadioGroup6, answerRadioGroup7, answerRadioGroup3a,answerRadioGroup3b,answerRadioGroup3c,answerRadioGroup3d,answerRadioGroup3e,
            answerRadioGroup4a,answerRadioGroup4b,answerRadioGroup4c,answerRadioGroup4d,answerRadioGroup4e, answerRadioGroup8;
    private RadioButton optionA, optionB, optionC, optionA2, optionB2, optionC2, optionA3, optionB3, optionC3, optionD3, optionF3, optionE3,
            optionA4, optionB4, optionC4, optionD4, optionE4, optionF4,
            optionA5, optionB5,
            optionA6, optionB6,
            optionA7, optionB7, optionC7, optionA8, optionB8;

    private Button continueButton, resetbutton1, resetbutton2;
    private EditText editTextOptionD3, editTextOptionF3, editTextOptionE3, editTextOptionD4, editTextOptionF4, editTextOptionE4,editTextForCheckBox1,editTextForCheckBox2;
    int score [];
    int calctotalscore;
    int [] totalscore = new int[24];
  //  private HashMap<RadioButton, Integer> radioButtonScores = new HashMap<>();
  private RadioButton lastClickedRadioButton;
    private int lastCheckedId = -1;
    private RadioButton lastSelectedRadioButton = null;
    String childKey;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_question1);
        // Initialize Views
        questionTextView1 = findViewById(R.id.questionTextView1);
        questionTextView2 = findViewById(R.id.questionTextView2);
        questionTextView3 = findViewById(R.id.questionTextView3);
        questionTextView4 = findViewById(R.id.questionTextView4);
        questionTextView5 = findViewById(R.id.questionTextView5);
        questionTextView6 = findViewById(R.id.questionTextView6);
        questionTextView7 = findViewById(R.id.questionTextView7);
        editTextForCheckBox1 = findViewById(R.id.editTextForCheckBox1);
        editTextForCheckBox2 = findViewById(R.id.editTextForCheckBox2);

        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        answerRadioGroup2 = findViewById(R.id.answerRadioGroup2);
        answerRadioGroup3 = findViewById(R.id.answerRadioGroup3);
        answerRadioGroup4 = findViewById(R.id.answerRadioGroup4);
        answerRadioGroup5 = findViewById(R.id.answerRadioGroup5);
        answerRadioGroup6 = findViewById(R.id.answerRadioGroup6);
        answerRadioGroup7 = findViewById(R.id.answerRadioGroup7);
        answerRadioGroup8 = findViewById(R.id.answerRadioGroup8);
        answerRadioGroup3a = findViewById(R.id.answerRadioGroup3a);
        answerRadioGroup3b = findViewById(R.id.answerRadioGroup3b);
        answerRadioGroup3c = findViewById(R.id.answerRadioGroup3c);
        answerRadioGroup3d = findViewById(R.id.answerRadioGroup3d);
        answerRadioGroup3e = findViewById(R.id.answerRadioGroup3e);
        answerRadioGroup4a = findViewById(R.id.answerRadioGroup4a);
        answerRadioGroup4b = findViewById(R.id.answerRadioGroup4b);
        answerRadioGroup4c = findViewById(R.id.answerRadioGroup4c);
        answerRadioGroup4d = findViewById(R.id.answerRadioGroup4d);
        answerRadioGroup4e = findViewById(R.id.answerRadioGroup4e);


        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionA2 = findViewById(R.id.optionA2);
        optionB2 = findViewById(R.id.optionB2);
        optionC2 = findViewById(R.id.optionC2);
        optionA3 = findViewById(R.id.optionA3);
        optionB3 = findViewById(R.id.optionB3);
        optionA4 = findViewById(R.id.optionA4);
        optionB4 = findViewById(R.id.optionB4);
        optionC4 = findViewById(R.id.optionC4);
        optionD4 = findViewById(R.id.optionD4);
        optionE4 = findViewById(R.id.optionE4);
        optionF4 = findViewById(R.id.optionF4);
        optionC3 = findViewById(R.id.optionC3);
        optionD3 = findViewById(R.id.optionD3);
        optionE3 = findViewById(R.id.optionE3);
        optionF3 = findViewById(R.id.optionF3);

        optionA5 = findViewById(R.id.optionA5);
        optionB5 = findViewById(R.id.optionB5);
        optionA6 = findViewById(R.id.optionA6);
        optionB6 = findViewById(R.id.optionB6);
        optionA7 = findViewById(R.id.optionA7);
        optionB7 = findViewById(R.id.optionB7);
        optionC7 = findViewById(R.id.optionC7);
        optionA8 = findViewById(R.id.optionA8);
        optionB8 = findViewById(R.id.optionB8);


        continueButton = findViewById(R.id.continueButton);
        resetbutton1 = findViewById(R.id.resetbutton1);
        resetbutton2 = findViewById(R.id.resetbutton2);
        cancelselection();
        radiofunc();
        savetofb();







    }

    public void radiofunc(){

        optionF3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextForCheckBox1.setVisibility(View.VISIBLE);



                }else{
                    editTextForCheckBox1.setVisibility(View.INVISIBLE);


                }
            }
        });
        optionF4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextForCheckBox2.setVisibility(View.VISIBLE);

                }else{
                    editTextForCheckBox2.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
    public void cancelselection(){
        CompoundButton.OnCheckedChangeListener radioButtonCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (buttonView.getId() == R.id.optionA3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                            }

                        });
                    }if (buttonView.getId() == R.id.optionB3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionC3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionD3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionE3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                                optionA3.setEnabled(true);
                                optionB3.setEnabled(true);
                                optionC3.setEnabled(true);
                                optionD3.setEnabled(true);
                                optionE3.setEnabled(true);
                                optionF3.setEnabled(true);
                            }

                        });
                        optionA3.setEnabled(false);
                        optionB3.setEnabled(false);
                        optionC3.setEnabled(false);
                        optionD3.setEnabled(false);
                        optionE3.setEnabled(false);
                        optionF3.setEnabled(false);
                    }
                    if (buttonView.getId() == R.id.optionF3) {
                        resetbutton1.setVisibility(View.VISIBLE);
                        resetbutton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton1.setVisibility(View.INVISIBLE);
                                answerRadioGroup3.clearCheck();
                                answerRadioGroup3a.clearCheck();
                                answerRadioGroup3b.clearCheck();
                                answerRadioGroup3c.clearCheck();
                                answerRadioGroup3d.clearCheck();
                                answerRadioGroup3e.clearCheck();
                            }

                        });
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
                                answerRadioGroup4c.clearCheck();
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
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
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
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
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionD4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
                            }

                        });
                    }
                    if (buttonView.getId() == R.id.optionE4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
                                optionA4.setEnabled(true);
                                optionB4.setEnabled(true);
                                optionC4.setEnabled(true);
                                optionD4.setEnabled(true);
                                optionE4.setEnabled(true);
                                optionF4.setEnabled(true);
                            }


                        });
                        optionA4.setEnabled(false);
                        optionB4.setEnabled(false);
                        optionC4.setEnabled(false);
                        optionD4.setEnabled(false);
                        optionE4.setEnabled(false);
                        optionF4.setEnabled(false);
                    }
                    if (buttonView.getId() == R.id.optionF4) {
                        resetbutton2.setVisibility(View.VISIBLE);
                        resetbutton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                resetbutton2.setVisibility(View.INVISIBLE);
                                answerRadioGroup4.clearCheck();
                                answerRadioGroup4a.clearCheck();
                                answerRadioGroup4b.clearCheck();
                                answerRadioGroup4c.clearCheck();
                                answerRadioGroup4d.clearCheck();
                                answerRadioGroup4e.clearCheck();
                            }

                        });
                    }



                }

            }
        };
        optionA3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionD3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionE3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionF3.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionA4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionB4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionC4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionD4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionE4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);
        optionF4.setOnCheckedChangeListener(radioButtonCheckedChangeListener);

    }
    private boolean areAllQuestionsAnswered() {
        return isRadioGroupAnswered(answerRadioGroup) && isRadioGroupAnswered(answerRadioGroup2)&& isRadioGroupAnswered(answerRadioGroup3)
                && isRadioGroupAnswered(answerRadioGroup4)&& isRadioGroupAnswered(answerRadioGroup5)&& isRadioGroupAnswered(answerRadioGroup6)&& isRadioGroupAnswered(answerRadioGroup7);
        // Add more RadioGroups as needed
    }

    // Helper function to check if a RadioGroup has at least one RadioButton selected
    private boolean isRadioGroupAnswered(RadioGroup radioGroup) {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return selectedRadioButtonId != -1;
    }
    public void savetofb (){
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (answerRadioGroup.getCheckedRadioButtonId() == -1 || answerRadioGroup2.getCheckedRadioButtonId() == -1 || answerRadioGroup5.getCheckedRadioButtonId() == -1
                        || answerRadioGroup6.getCheckedRadioButtonId() == -1|| answerRadioGroup7.getCheckedRadioButtonId() == -1 || answerRadioGroup8.getCheckedRadioButtonId() == -1 ) {
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
                    String selectedAnswer3a = getSelectedAnswer(answerRadioGroup3a);
                    String selectedAnswer3b = getSelectedAnswer(answerRadioGroup3b);
                    String selectedAnswer3c = getSelectedAnswer(answerRadioGroup3c);
                    String selectedAnswer3d = getSelectedAnswer(answerRadioGroup3d);
                    String selectedAnswer3e = getSelectedAnswer(answerRadioGroup3e);
                    String selectedAnswer4a = getSelectedAnswer(answerRadioGroup4a);
                    String selectedAnswer4b = getSelectedAnswer(answerRadioGroup4b);
                    String selectedAnswer4c = getSelectedAnswer(answerRadioGroup4c);
                    String selectedAnswer4d = getSelectedAnswer(answerRadioGroup4d);
                    String selectedAnswer4e = getSelectedAnswer(answerRadioGroup4e);
                    if ("Lainnya".equals(selectedAnswer3e)) {
                        EditText edittextlain1 = findViewById(R.id.editTextForCheckBox2);
                        String editTextValue1 = edittextlain1.getText().toString().trim();
                        AnswerModel answerModel = new AnswerModel();
                        answerModel.setAnswer1(selectedAnswer1);
                        answerModel.setAnswer2(selectedAnswer2);
                        answerModel.setAnswer3(selectedAnswer3);
                        answerModel.setAnswer4(selectedAnswer4);
                        answerModel.setAnswer5(selectedAnswer5);
                        answerModel.setAnswer6(selectedAnswer6);
                        answerModel.setAnswer7(selectedAnswer7);
                        answerModel.setAnswer8(selectedAnswer8);
                        answerModel.setAnswer3a(selectedAnswer3a);
                        answerModel.setAnswer3b(selectedAnswer3b);
                        answerModel.setAnswer3c(selectedAnswer3c);
                        answerModel.setAnswer3d(selectedAnswer3d);
                        answerModel.setAnswer3e("lainya," + editTextValue1);
                        answerModel.setAnswer4a(selectedAnswer4a);
                        answerModel.setAnswer4b(selectedAnswer4b);
                        answerModel.setAnswer4c(selectedAnswer4c);
                        answerModel.setAnswer4d(selectedAnswer4d);
                        answerModel.setAnswer4e(selectedAnswer4e);
                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel);


                    } else if ("Lainnya".equals(selectedAnswer4e)) {
                        EditText edittextlain2 = findViewById(R.id.editTextForCheckBox2);
                        String editTextValue2 = edittextlain2.getText().toString().trim();
                        AnswerModel answerModel = new AnswerModel();
                        answerModel.setAnswer1(selectedAnswer1);
                        answerModel.setAnswer2(selectedAnswer2);
                        answerModel.setAnswer3(selectedAnswer3);
                        answerModel.setAnswer4(selectedAnswer4);
                        answerModel.setAnswer5(selectedAnswer5);
                        answerModel.setAnswer6(selectedAnswer6);
                        answerModel.setAnswer7(selectedAnswer7);
                        answerModel.setAnswer8(selectedAnswer8);
                        answerModel.setAnswer3a(selectedAnswer3a);
                        answerModel.setAnswer3b(selectedAnswer3b);
                        answerModel.setAnswer3c(selectedAnswer3c);
                        answerModel.setAnswer3d(selectedAnswer3d);
                        answerModel.setAnswer3e(selectedAnswer3e);
                        answerModel.setAnswer4a(selectedAnswer4a);
                        answerModel.setAnswer4b(selectedAnswer4b);
                        answerModel.setAnswer4c(selectedAnswer4c);
                        answerModel.setAnswer4d(selectedAnswer4d);
                        answerModel.setAnswer4e("Lainnya" + editTextValue2);
                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel);


                    } else {
                        AnswerModel answerModel = new AnswerModel();
                        answerModel.setAnswer1(selectedAnswer1);
                        answerModel.setAnswer2(selectedAnswer2);
                        answerModel.setAnswer3(selectedAnswer3);
                        answerModel.setAnswer4(selectedAnswer4);
                        answerModel.setAnswer5(selectedAnswer5);
                        answerModel.setAnswer6(selectedAnswer6);
                        answerModel.setAnswer7(selectedAnswer7);
                        answerModel.setAnswer8(selectedAnswer8);
                        answerModel.setAnswer3a(selectedAnswer3a);
                        answerModel.setAnswer3b(selectedAnswer3b);
                        answerModel.setAnswer3c(selectedAnswer3c);
                        answerModel.setAnswer3d(selectedAnswer3d);
                        answerModel.setAnswer3e(selectedAnswer3e);
                        answerModel.setAnswer4a(selectedAnswer4a);
                        answerModel.setAnswer4b(selectedAnswer4b);
                        answerModel.setAnswer4c(selectedAnswer4c);
                        answerModel.setAnswer4d(selectedAnswer4d);
                        answerModel.setAnswer4e(selectedAnswer4e);
                        // Save the data to Firebase
                        saveAnswersToFirebase(answerModel);

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
        radioButtonScores.put(optionC, 2);
        radioButtonScores.put(optionA2, 2);
        radioButtonScores.put(optionB2, 0);
        radioButtonScores.put(optionC2, 2);
        radioButtonScores.put(optionA3, 3);
        radioButtonScores.put(optionB3, 3);
        radioButtonScores.put(optionC3, 3);
        radioButtonScores.put(optionD3, 3);
        radioButtonScores.put(optionF3, 3);
        radioButtonScores.put(optionE3, 0);
        radioButtonScores.put(optionA4, 2);
        radioButtonScores.put(optionB4, 2);
        radioButtonScores.put(optionC4, 2);
        radioButtonScores.put(optionD4, 2);
        radioButtonScores.put(optionE4, 0);
        radioButtonScores.put(optionF4, 2);
        radioButtonScores.put(optionA5, 2);
        radioButtonScores.put(optionB5, 0);
        radioButtonScores.put(optionA6, 3);
        radioButtonScores.put(optionB6, 0);
        radioButtonScores.put(optionA7, 4);
        radioButtonScores.put(optionB7, 4);
        radioButtonScores.put(optionC7, 0);
        radioButtonScores.put(optionA8, 4);
        radioButtonScores.put(optionB8,0);
         calctotalscore = 0;

        for (Map.Entry<RadioButton, Integer> entry : radioButtonScores.entrySet()) {
            RadioButton radioButton = entry.getKey();
            int score = entry.getValue();

            if (radioButton.isChecked()) {
                calctotalscore += score;
            }
        }





    }
    private int calculateTotalScore(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
    private void saveAnswerToFirebase(String answerKey, String answerText) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("Section 1");

        // Create a map to save the answer with the key
        Map<String, String> answerMap = new HashMap<>();
        answerMap.put(answerKey, answerText);

        // Save the data to Firebase
        answersRef.child("user_id").setValue(answerMap);
    }
    private void saveAnswersToFirebase(AnswerModel answerModel) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        answerModel.setScore(calctotalscore);
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerModel);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childKey = childSnapshot.getKey(); // Get the key of the child node
                    AnswerModel answerModel = childSnapshot.getValue(AnswerModel.class);

                    // Do something with the retrieved data and child key
                    Log.d("FirebaseData", "Child Key: " + childKey);
                    Log.d("FirebaseData", "Answer Model: " + answerModel.toString());
                    Toast.makeText(getApplicationContext(), "total score :" + calctotalscore, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "key:" + childKey, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(question1.this, question2.class);
                    intent.putExtra("score1", calctotalscore);
                    intent.putExtra("childkey1",childKey);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
    public void onBackPressed() {
        // Show an AlertDialog to confirm the action
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Ingin kembali? jawaban anda tidak akan tersimpan")
                .setPositiveButton("Beranda", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Finish the current activity (close it)

                        Intent intent = new Intent(question1.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
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
    }
}