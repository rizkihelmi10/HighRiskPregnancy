package com.kamus.highriskpregnancy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reportActivity extends AppCompatActivity {
    MySQLiteHelper dbhelper;
    dbActivities db;
    TextView score1TextView;
    TextView score2TextView;
    TextView score3TextView;
    TextView score4TextView;
    TextView score5TextView;
    TextView totalScoreTextView;
    TextView Namadokter;
    TextView NamaPasien;
    TextView NomorPasien;
    TextView UsiaPasien;
    TextView NoHPPasien;
    int score5;
    int score4;
    int score1;
    int score2;
    int score3;
    String childkey2;
    String childkey1;
    String childkey3;
    String childkey4;
    String childkey5;
    TextView tindakan1TextView;
    TextView tindakan2TextView;
    TextView tindakan3TextView, pilihrumahsakit, keterangan1, keterangan2, keternagan3, keterangan4, keterangan5;
    EditText rumahsakittujuan;
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);
        Intent intent = getIntent();
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        childkey2 = intent.getStringExtra("childkey2");
        score2 = intent.getIntExtra("score2",0);
        childkey3 = intent.getStringExtra("childkey3");
        score3 = intent.getIntExtra("score3",0);
        score4 = intent.getIntExtra("score4",0);
        childkey4 = intent.getStringExtra("childkey4");
        score5 = intent.getIntExtra("score5",0);
        childkey5 = intent.getStringExtra("childkey5");
        Log.d("what is passed?", "onCreate: " + score3);
        Log.d("what is passed?", "onCreate: " + childkey2);
        Log.d("what is passed?", "onCreate: " + childkey3);
        Log.d("what is passed?", "onCreate: " + score4);
        Log.d("what is passed?", "onCreate: " + score5);

        score1TextView = findViewById(R.id.score1);
        score2TextView = findViewById(R.id.score2);
        score3TextView = findViewById(R.id.score3);
        score4TextView = findViewById(R.id.score4);
        score5TextView = findViewById(R.id.score5);
        totalScoreTextView = findViewById(R.id.scoretotal);
        NomorPasien = findViewById(R.id.textViewNomorPasien);
        UsiaPasien = findViewById(R.id.textViewUsiaPasien);
        NoHPPasien = findViewById(R.id.textViewNomorHP);

        tindakan1TextView = findViewById(R.id.tindakan1);
        tindakan2TextView = findViewById(R.id.tindakan2);
        tindakan3TextView = findViewById(R.id.tindakan3);
        Namadokter = findViewById(R.id.textViewNamaDokter);
        NamaPasien = findViewById(R.id.textViewNamaPasien);
        keterangan1 = findViewById(R.id.keterangan1);
        keterangan2 = findViewById(R.id.keterangan2);
        keternagan3 = findViewById(R.id.keterangan3);
        keterangan4 = findViewById(R.id.keterangan4);
        keterangan5 = findViewById(R.id.keterangan5);
        pilihrumahsakit = findViewById(R.id.Pilihrumahsakittext);
        rumahsakittujuan = findViewById(R.id.isisendiri);
       retrieveFB();

        try {
            filledInfo();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void filledInfo() throws ParseException {
        int totalscore = score1+score2+score3+score4+score5;
       // String score1str = getString()

        String namadokter = db.getResult(dbhelper,"name",null);
        String namapasien = db.getPatientinfo(dbhelper,"PatientName",null);
        String nomorpasien = db.getPatientinfo(dbhelper,"PatientID", null);
        String DOB = db.getPatientinfo(dbhelper,"date",null);
        String Nohp = db.getPatientinfo(dbhelper,"PhoneNUmber",null);
        int umur = calculateAge(DOB);

        score1TextView.setText(String.valueOf(score1));
        score2TextView.setText(String.valueOf(score2));
        score3TextView.setText(String.valueOf(score3));
        score4TextView.setText(String.valueOf(score4));
        score5TextView.setText(String.valueOf(score5));
        Namadokter.setText("Nama Dokter : dr. "+namadokter);
        NamaPasien.setText("Nama Pasien : " + namapasien);
        NomorPasien.setText("Nomor Pasien : "+nomorpasien);
        NoHPPasien.setText("NomorHP : " + Nohp);
        UsiaPasien.setText("Usia : " +String.valueOf(umur));
        totalScoreTextView.setText(String.valueOf(totalscore));
        if(totalscore < 3){
            tindakan3TextView.setVisibility(View.VISIBLE);
            tindakan3TextView.setText("Rencanakan persalinan di FKTP");
            pilihrumahsakit.setVisibility(View.INVISIBLE);
            rumahsakittujuan.setVisibility(View.INVISIBLE);
            
        } else if (totalscore >= 4 && totalscore <= 7) {
            tindakan2TextView.setVisibility(View.VISIBLE);
            tindakan2TextView.setText("Rujuk ke SpOG di FKRTL I/II untuk perencanaan persalinan di FKRTL I/II dan kontrasepsi pasca salin");

        } else  {
            tindakan1TextView.setVisibility(View.VISIBLE);
            tindakan1TextView.setText("Rujuk ke SpOG di FKRTL III untuk perencanaan persalinan di FKRTL III dan kontrasepsi pasca salin");
        }

    }

    public void retrieveFB(){
        DatabaseReference answersRef = database.getReference("answers");
        Log.d("Masihada?", "retrieveFB: "+ childkey1);
        DatabaseReference answersRef1 = answersRef.child(childkey1);
        DatabaseReference answersRef2 = answersRef.child(childkey2);
        DatabaseReference answersRef3 = answersRef.child(childkey3);
        DatabaseReference answersRef4 = answersRef.child(childkey4);
        DatabaseReference answersRef5 = answersRef.child(childkey5);



        keterangan4 = findViewById(R.id.keterangan4);
        keterangan5 = findViewById(R.id.keterangan5);

        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder keteranganBuilder = new StringBuilder();
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(childkey1);

                    String answer1 = specificChild.child("answer1").getValue(String.class);
                    String answer2 = specificChild.child("answer2").getValue(String.class);
                    String answer3 = specificChild.child("answer3").getValue(String.class);
                    String answer3a = specificChild.child("answer3a").getValue(String.class);
                    String answer3b = specificChild.child("answer3b").getValue(String.class);
                    String answer3c = specificChild.child("answer3c").getValue(String.class);
                    String answer3d = specificChild.child("answer3d").getValue(String.class);
                    String answer3e = specificChild.child("answer3e").getValue(String.class);
                    String answer4 = specificChild.child("answer4").getValue(String.class);
                    String answer4a = specificChild.child("answer4a").getValue(String.class);
                    String answer4b = specificChild.child("answer4b").getValue(String.class);
                    String answer4c = specificChild.child("answer4c").getValue(String.class);
                    String answer5 = specificChild.child("answer5").getValue(String.class);
                    String answer6 = specificChild.child("answer6").getValue(String.class);
                    String answer7 = specificChild.child("answer7").getValue(String.class);
                    String answer8 = specificChild.child("answer8").getValue(String.class);
                    if (answer1.equals("Dibawah 20 tahun") || answer1.equals("Diatas 35 tahun")) {
                        keteranganBuilder.append("Usia : ").append(answer1).append(", ");
                    }

                    if (answer2.equals("Dibawah 22.5 cm") || answer2.equals("Diatas 30 cm")) {
                        keteranganBuilder.append("Lingkar lengan atas : ").append(answer2).append(", ");
                    }

                    if (answer3.equals("Unwanted Pregnancy") || answer3a.equals("Unsuppoerted") || answer3b.equals("KDRT") || answer3c.equals("Tuna Wisma")) {
                        keteranganBuilder.append("Psikososial: ").append(answer3).append(", ").append(answer3a).append(", ").append(answer3b).append(", ").append(answer3c).append(", ");
                    }

                    if (answer4.equals("Merokok") || answer4a.equals("Alkohol") || answer4b.equals("Obat berbahaya") || answer4c.equals("Zat berbahaya paparan dari tempat kerja")) {
                        keteranganBuilder.append("Zat bahaya: ").append(answer4).append(", ").append(answer4a).append(", ").append(answer4b).append(", ").append(answer4c).append(", ");
                    }

                    if (answer5.equals("Ya") || answer6.equals("Ya")) {
                        keteranganBuilder.append("ANC pertama 20 minggu lalu : ").append(answer5).append(", ").append("Tidak pernah ANC :").append(answer6).append(", ");
                    }

                    if (answer7.equals("Primigravida")) {
                        keteranganBuilder.append("Primigravida: ").append(answer7).append(", ");
                    }
                    if( answer7.equals("Grande Multipara (gravida>5)")){
                        keteranganBuilder.append("Grande Multipara (gravida>5) : ").append(answer7).append(", ");

                    }

                    if (answer8.equals("Ya")) {
                        keteranganBuilder.append("Riwayat infertil : ").append(answer8).append(", ");
                    }
                }

                String keterangan1str = keteranganBuilder.toString().trim();
                if (!keterangan1str.isEmpty()) {
                    Log.d("APa didalamnya", "onDataChange: " + keterangan1str);
                    keterangan1.setText(keterangan1str);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder keteranganBuilder2 = new StringBuilder();
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(childkey2);

                    String answer1 = specificChild.child("answer1").getValue(String.class);
                    String answer2 = specificChild.child("answer2").getValue(String.class);
                    String answer3 = specificChild.child("answer3").getValue(String.class);
                    String answer4 = specificChild.child("answer4").getValue(String.class);
                    String answer5 = specificChild.child("answer5").getValue(String.class);
                    String answer6 = specificChild.child("answer6").getValue(String.class);
                    String answer7 = specificChild.child("answer7").getValue(String.class);
                    String answer8 = specificChild.child("answer8").getValue(String.class);
                    String answer9 = specificChild.child("answer9").getValue(String.class);
                    String answer10 = specificChild.child("answer10").getValue(String.class);
                    String answer11 = specificChild.child("answer11").getValue(String.class);
                    String answer12 = specificChild.child("answer12").getValue(String.class);
                    if (answer1.contains("Ya, ")) {
                        keteranganBuilder2.append(answer1).append(", ");
                    }
                    if (answer2.contains("Ya, ")) {
                        keteranganBuilder2.append(answer2).append(", ");
                    }
                    if (answer3.contains("Ya, ")) {
                        keteranganBuilder2.append(answer3).append(", ");
                    }
                    if (answer4.contains("Ya, ")) {
                        keteranganBuilder2.append(answer4).append(", ");
                    }
                    if (answer5.contains("Ya, ")) {
                        keteranganBuilder2.append(answer5).append(", ");
                    }
                    if (answer6.contains("Ya, ")) {
                        keteranganBuilder2.append(answer6).append(", ");
                    }
                    if (answer7.contains("Ya, ")) {
                        keteranganBuilder2.append(answer7).append(", ");
                    }
                    if (answer8.contains("Ya, ")) {
                        keteranganBuilder2.append(answer8).append(", ");
                    }
                    if (answer9.contains("Ya, ")) {
                        keteranganBuilder2.append(answer9).append(", ");
                    }
                    if (answer10.contains("Ya, ")) {
                        keteranganBuilder2.append(answer10).append(", ");
                    }
                    if (answer11.contains("Ya, ")) {
                        keteranganBuilder2.append(answer11).append(", ");
                    }
                    if (answer12.contains("Ya, ")) {
                        keteranganBuilder2.append(answer12).append(", ");
                    }
                }

                String keterangan2str = keteranganBuilder2.toString().trim();
                if (!keterangan2str.isEmpty()) {
                    keterangan2.setText(keterangan2str);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder keteranganBuilder3 = new StringBuilder();
                keternagan3 = findViewById(R.id.keterangan3);
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(childkey3);

                    String answer1 = specificChild.child("answer1").getValue(String.class);
                    String answer2 = specificChild.child("answer2").getValue(String.class);
                    String answer3 = specificChild.child("answer3").getValue(String.class);
                    String answer4 = specificChild.child("answer4").getValue(String.class);
                    String answer5 = specificChild.child("answer5").getValue(String.class);
                    String answer6 = specificChild.child("answer6").getValue(String.class);
                    String answer7 = specificChild.child("answer7").getValue(String.class);
                    String answer8 = specificChild.child("answer8").getValue(String.class);
                    String answer9 = specificChild.child("answer9").getValue(String.class);
                    String answer10 = specificChild.child("answer10").getValue(String.class);
                    String answer11 = specificChild.child("answer11").getValue(String.class);
                    String answer12 = specificChild.child("answer12").getValue(String.class);
                    if (answer1.equals("Ya")) {
                        keteranganBuilder3.append("Abortus : ").append(answer1).append(", ");
                    }
                    if (answer2.contains("kehamilan" )|| answer2.equals("Pertumbuhan janin terhambat")) {
                        keteranganBuilder3.append(answer2).append(", ");
                    }
                    if (answer3.equals("Ya")) {
                        keteranganBuilder3.append("Rhesus isoimunisasi : ").append(answer2).append(", ");
                    }
                    if (answer4.equals("Ya")) {
                        keteranganBuilder3.append("hipertensi : ").append(answer3).append(", ");
                    }
                    if (answer4.contains("Preklamsia")) {
                        keteranganBuilder3.append(answer3).append(", ");
                    }
                    if (answer5.equals("Ya")) {
                        keteranganBuilder3.append("Kelainan bayi : ").append(answer4).append(", ");
                    }
                    if (answer6.contains("plasenta") || answer6.contains("Plasenta")) {
                        keteranganBuilder3.append(answer6).append(", ");
                    }
                    if (answer7.equals("Still birth (kematioan janin dalam kandungan setelah 24 minggu") || answer7.equals("Neonatal death (kematian bayi saat persalinan sampai usia 28 hari)")) {
                        keteranganBuilder3.append(answer7).append(", ");
                    }
                    if (answer8.equals("Induksi") || answer9.equals("Augmentasi persalinan")) {
                        keteranganBuilder3.append(answer8).append(", ");
                    }
                    if (answer9.equals("Ya")) {
                        keteranganBuilder3.append("Operasi sesar : ").append(answer9).append(", ");
                    }
                    if (answer10.equals("Ya")) {
                        keteranganBuilder3.append("Distosia bahu : ").append(answer10).append(", ");
                    }
                    if (answer11.equals("Ekstraksi vakum") || answer11.equals("Vorcep")) {
                        keteranganBuilder3.append(answer11).append(", ");
                    }
                    if (answer12.equals("Ya")) {
                        keteranganBuilder3.append("Pendarahan post partum : ").append(answer12).append(", ");
                    }
                }

                String keterangan3str = keteranganBuilder3.toString().trim();
                if (!keterangan3str.isEmpty()) {
                    keternagan3.setText(keterangan3str);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder keteranganBuilder4 = new StringBuilder();
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(childkey4);

                    String answer1 = specificChild.child("answer1").getValue(String.class);
                    String answer2 = specificChild.child("answer2").getValue(String.class);
                    String answer3 = specificChild.child("answer3").getValue(String.class);
                    String answer4 = specificChild.child("answer4").getValue(String.class);
                    String answer5 = specificChild.child("answer5").getValue(String.class);
                    String answer6 = specificChild.child("answer6").getValue(String.class);
                    String answer7 = specificChild.child("answer7").getValue(String.class);
                    String answer8 = specificChild.child("answer8").getValue(String.class);
                    String answer9 = specificChild.child("answer9").getValue(String.class);
                    String answer10 = specificChild.child("answer10").getValue(String.class);
                    String answer11 = specificChild.child("answer11").getValue(String.class);
                    String answer12 = specificChild.child("answer12").getValue(String.class);
                    if (answer1.contains("Ya, ") || answer1.contains("Not")) {
                        keteranganBuilder4.append(answer1).append(", ");
                    }
                    if (answer2.contains("Ya, ")|| answer2.contains("Not")) {
                        keteranganBuilder4.append(answer2).append(", ");
                    }
                    if (answer3.contains("Ya, ")|| answer3.contains("Not")) {
                        keteranganBuilder4.append(answer3).append(", ");
                    }
                    if (answer4.contains("Ya, ")|| answer4.contains("Not")) {
                        keteranganBuilder4.append(answer4).append(", ");
                    }
                    if (answer5.contains("Ya, ")|| answer5.contains("Not")) {
                        keteranganBuilder4.append(answer5).append(", ");
                    }
                    if (answer6.contains("Ya, ")|| answer6.contains("Not")) {
                        keteranganBuilder4.append(answer6).append(", ");
                    }
                    if (answer7.contains("Ya, ")|| answer7.contains("Not")) {
                        keteranganBuilder4.append(answer7).append(", ");
                    }
                    if (answer8.contains("Ya, ")|| answer8.contains("Not")) {
                        keteranganBuilder4.append(answer8).append(", ");
                    }
                    if (answer9.contains("Ya, ")|| answer9.contains("Not")) {
                        keteranganBuilder4.append(answer9).append(", ");
                    }
                    if (answer10.contains("Ya, ")|| answer10.contains("Not")) {
                        keteranganBuilder4.append(answer10).append(", ");
                    }
                    if (answer11.contains("Ya, ")|| answer11.contains("Not")) {
                        keteranganBuilder4.append(answer11).append(", ");
                    }
                    if (answer12.contains("Ya, ")|| answer12.contains("Not")) {
                        keteranganBuilder4.append(answer12).append(", ");
                    }
                }

                String keterangan4str = keteranganBuilder4.toString().trim();
                if (!keterangan4str.isEmpty()) {
                    keterangan4.setText(keterangan4str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder keteranganBuilder5 = new StringBuilder();
                keterangan5 = findViewById(R.id.keterangan5);
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(childkey5);

                    String answer1 = specificChild.child("answer1").getValue(String.class);
                    String answer2 = specificChild.child("answer2").getValue(String.class);
                    String answer3 = specificChild.child("answer3").getValue(String.class);
                    String answer4 = specificChild.child("answer4").getValue(String.class);
                    String answer5 = specificChild.child("answer5").getValue(String.class);
                    String answer6 = specificChild.child("answer6").getValue(String.class);
                    String answer7 = specificChild.child("answer7").getValue(String.class);
                    String answer8 = specificChild.child("answer8").getValue(String.class);
                    String answer9 = specificChild.child("answer9").getValue(String.class);
                    String answer10 = specificChild.child("answer10").getValue(String.class);
                    String answer11 = specificChild.child("answer11").getValue(String.class);
                    String answer12 = specificChild.child("answer12").getValue(String.class);
                    String answer13 = specificChild.child("answer13").getValue(String.class);
                    if (answer1.equals("Ya")|| answer1.contains("Not")) {
                        keteranganBuilder5.append("ancaman Abortus : ").append(answer1).append(", ");
                    }
                    if (answer2.contains("kehamilan" )|| answer2.equals("Pertumbuhan janin terhambat")|| answer2.contains("Not")) {
                        keteranganBuilder5.append(answer2).append(", ");
                    }
                    if (answer3.equals("Ya")|| answer3.contains("Not")) {
                        keteranganBuilder5.append("Rhesus isoimunisasi : ").append(answer2).append(", ");
                    }
                    if (answer4.equals("Ya")|| answer4.contains("Not")) {
                        keteranganBuilder5.append("hipertensi : ").append(answer3).append(", ");
                    }
                    if (answer4.contains("Preklamsia")|| answer4.contains("Not")) {
                        keteranganBuilder5.append(answer3).append(", ");
                    }
                    if (answer5.equals("Ya")|| answer5.contains("Not")) {
                        keteranganBuilder5.append("Kelainan bayi : ").append(answer4).append(", ");
                    }
                    if (answer8.contains("plasenta") || answer8.contains("Plasenta")|| answer8.contains("Not")) {
                        keteranganBuilder5.append(answer8).append(", ");
                    }
                    if (answer7.equals("Ya")|| answer7.contains("Not")) {
                        keteranganBuilder5.append("Kelainan letak : ").append(answer7).append(", ");
                    }
                    if (answer6.equals("Ya")|| answer6.contains("Not")) {
                        keteranganBuilder5.append("Multiple Pregnancy").append(answer6).append(", ");
                    }
                    if (answer9.equals("Ya")|| answer9.contains("Not")) {
                        keteranganBuilder5.append("Plasenta akreta : ").append(answer9).append(", ");
                    }
                    if (answer10.equals("Ya")|| answer10.contains("Not")) {
                        keteranganBuilder5.append("Kelainan air ketuban : ").append(answer10).append(", ");
                    }
                    if (answer11.equals("Ya")|| answer11.contains("Not")) {
                        keteranganBuilder5.append("Premature mengancam : ").append(answer11).append(", ");
                    }
                    if (answer12.equals("Ya")|| answer12.contains("Not")) {
                        keteranganBuilder5.append("CPD : ").append(answer12).append(", ");
                    }
                    if (answer13.equals("Ya")|| answer13.contains("Not")) {
                        keteranganBuilder5.append("Kelainan jalan lahir : ").append(answer13).append(", ");
                    }
                }

                String keterangan5str = keteranganBuilder5.toString().trim();
                if (!keterangan5str.isEmpty()) {
                    keterangan5.setText(keterangan5str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
       /* public void calcdate(String[] args) {
            // Example date of birth retrieved from the database as a string
            String dobString = "1990-05-15"; // Format: yyyy-MM-dd

            try {
                // Step 1: Convert the DOB string to a Date object
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dob = sdf.parse(dobString);

                // Step 2: Calculate the age
                int age = calculateAge(dob);

                // Step 3: Display the age
                System.out.println("Age: " + age + " years");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }*/

    public static int calculateAge(String dobString) throws ParseException {
        // Create a SimpleDateFormat with the expected date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Parse the DOB string to a Date object
        Date dob = sdf.parse(dobString);

        // Get the current date
        Date currentDate = new Date();

        // Calculate the difference in milliseconds between current date and DOB
        long diffInMilliseconds = currentDate.getTime() - dob.getTime();

        // Calculate the age based on milliseconds (assuming an average year of 365.25 days)
        long millisecondsPerYear = (long) (365.25 * 24 * 60 * 60 * 1000);
        int age = (int) (diffInMilliseconds / millisecondsPerYear);

        return age;
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
                        Intent intent = new Intent(reportActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        startActivity(intent);
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