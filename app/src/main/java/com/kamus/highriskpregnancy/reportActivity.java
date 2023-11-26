package com.kamus.highriskpregnancy;

import static com.itextpdf.io.font.otf.LanguageTags.TODO;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.UnitValue;

import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

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
    TextView NoHPPasien, textFKTP;
    int score5;
    int score4;
    int score1;
    int score2;
    int score3;
    int totalscore;
    String childkey2;
    String childkey1;
    String childkey3;
    String childkey4;
    String childkey5;
    TextView tindakan1TextView;
    TextView tindakan2TextView, NoKTPPasien, kunjungan;
    TextView tindakan3TextView, pilihrumahsakit, keterangan1, keterangan2, keternagan3, keterangan4, keterangan5, statuskehamilan;
    EditText rumahsakittujuan;
    Button save;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String pdfFilePath;
    private View viewToConvert;
    private String patientChildkey;
    private String statusChildkey;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);
        Intent intent = getIntent();
        patientChildkey = intent.getStringExtra("childkeyPatient");
        score1 = intent.getIntExtra("score1", 0);
        childkey1 = intent.getStringExtra("childkey1");
        childkey2 = intent.getStringExtra("childkey2");
        score2 = intent.getIntExtra("score2", 0);
        childkey3 = intent.getStringExtra("childkey3");
        score3 = intent.getIntExtra("score3", 0);
        score4 = intent.getIntExtra("score4", 0);
        childkey4 = intent.getStringExtra("childkey4");
        score5 = intent.getIntExtra("score5", 0);
        childkey5 = intent.getStringExtra("childkey5");
        statusChildkey = intent.getStringExtra("childkeystatust");
        Log.d("what is passed?", "onCreate: " + score3);
        Log.d("what is passed?", "onCreate: " + childkey2);
        Log.d("what is passed?", "onCreate: " + childkey3);
        Log.d("what is passed?", "onCreate: " + score4);
        Log.d("what is passed?", "onCreate: " + score5);
        Log.d("what is passed?", "onCreate: " + patientChildkey);
        Log.d("Childkeystatonrep", "onDataChange: " + statusChildkey);

        score1TextView = findViewById(R.id.score1);
        score2TextView = findViewById(R.id.score2);
        score3TextView = findViewById(R.id.score3);
        score4TextView = findViewById(R.id.score4);
        score5TextView = findViewById(R.id.score5);
        totalScoreTextView = findViewById(R.id.scoretotal);
        NomorPasien = findViewById(R.id.textViewNomorPasien);
        UsiaPasien = findViewById(R.id.textViewUsiaPasien);
        NoHPPasien = findViewById(R.id.textViewNomorHP);
        NoKTPPasien = findViewById(R.id.textViewNomorKTP);
        kunjungan = findViewById(R.id.textViewKunjungan);
        save = findViewById(R.id.buttonpdf);

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
        textFKTP = findViewById(R.id.fktp);

        retrieveFB();

        try {
            filledInfo();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //  View view = getLayoutInflater().inflate(R.layout.activity_report, null);
        viewToConvert = getWindow().getDecorView().findViewById(R.id.reportview);


        // Generate a PDF


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePdf();
                retrievetoken();// Call generatePdf with the 'view' you want to convert to PDF
                //  downloadPdf(view);
            }
        });

    }

    private void generatePdf() {
        viewToConvert.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        viewToConvert.layout(0, 0, viewToConvert.getMeasuredWidth(), viewToConvert.getMeasuredHeight());
        int width = viewToConvert.getWidth();
        int height = viewToConvert.getHeight();
        Log.d("WHY kot 2", "Width: " + width + ", Height: " + height);

        try {
            String pdfFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/output.pdf";
            File pdfFile = new File(pdfFilePath);

            // Make sure the parent directory exists
            pdfFile.getParentFile().mkdirs();
            // Create a new PDF document
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(pdfFile));

            // Create a new PDF document
            Document document = new Document(pdfDocument, PageSize.A4);

            // Convert the view to a Bitmap and draw it on the PDF
         /*   viewToConvert.setDrawingCacheEnabled(true);
            viewToConvert.buildDrawingCache();
            Bitmap bitmap = viewToConvert.getDrawingCache();*/
            viewToConvert.setDrawingCacheEnabled(true);
            viewToConvert.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            File imageFile = new File(this.getFilesDir(), "my_image.png");
            try (FileOutputStream out = new FileOutputStream(imageFile)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // Use PNG or JPEG as needed
            } catch (IOException e) {
                e.printStackTrace();
            }
            Canvas canvas = new Canvas(bitmap);
            viewToConvert.draw(canvas);
            Image img = new Image((ImageDataFactory.create(bitmapToByteArray(bitmap))));
            img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());

            // Add the image to the document
            document.add(img);

            // Close the document
            document.close();
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(pdfFile));
            sendBroadcast(intent);
            Log.d("WHY kot", "downloadPdf: " + "Success");

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("WHY kot", "downloadPdf: " + e);
        }
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public void downloadPdf() {
        if (pdfFilePath != null) {
            File pdfFile = new File(pdfFilePath);

            if (pdfFile.exists()) {
                Uri pdfUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", pdfFile);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(pdfUri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Add read permission

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Handle the case where a PDF viewer is not available
                    // You can prompt the user to install a PDF viewer app.
                }
            } else {
                // Handle the case where the PDF file doesn't exist
                // You can display a message to the user.
            }
        }
    }

    public void filledInfo() throws ParseException {
        totalscore = score1 + score2 + score3 + score4 + score5;
        // String score1str = getString()

        String namadokter = db.getResult(dbhelper, "name", null);
        String namapasien = db.getPatientinfo(dbhelper, "PatientName", null);
        String nomorpasien = db.getPatientinfo(dbhelper, "PatientID", null);
        String DOB = db.getPatientinfo(dbhelper, "date", null);
        String Nohp = db.getPatientinfo(dbhelper, "PhoneNUmber", null);
        String NoKTP = db.getPatientinfo(dbhelper, "Identity", null);
        String Kunjungan = db.getPatientinfo(dbhelper, "Visit", null);
        int umur = calculateAge(DOB);

        score1TextView.setText(String.valueOf(score1));
        score2TextView.setText(String.valueOf(score2));
        score3TextView.setText(String.valueOf(score3));
        score4TextView.setText(String.valueOf(score4));
        score5TextView.setText(String.valueOf(score5));
        Namadokter.setText("Nama Dokter : " + namadokter);
        NamaPasien.setText("Nama Pasien : " + namapasien);
        NomorPasien.setText("Nomor Pasien : " + nomorpasien);
        NoHPPasien.setText("NomorHP : " + Nohp);
        UsiaPasien.setText("Usia : " + String.valueOf(umur));
        NoKTPPasien.setText("Nomor KTP : " + NoKTP);
        kunjungan.setText("Pemeriksaan ke : " + Kunjungan);
        totalScoreTextView.setText(String.valueOf(totalscore));
        if (totalscore < 3) {
            tindakan3TextView.setVisibility(View.VISIBLE);
            tindakan3TextView.setText("Rencanakan persalinan di FKTP");
            pilihrumahsakit.setVisibility(View.INVISIBLE);
            rumahsakittujuan.setVisibility(View.INVISIBLE);

        } else if (totalscore >= 4 && totalscore <= 7) {
            tindakan2TextView.setVisibility(View.VISIBLE);
            tindakan2TextView.setText("Rujuk ke SpOG di FKRTL I/II untuk perencanaan persalinan di FKRTL I/II dan \n kontrasepsi pasca salin");

        } else {
            tindakan1TextView.setVisibility(View.VISIBLE);
            tindakan1TextView.setText("Rujuk ke SpOG di FKRTL III untuk perencanaan persalinan di FKRTL III dan \n kontrasepsi pasca salin");
        }

    }

    public void retrieveFB() {
        DatabaseReference answersRef = database.getReference("answers");
        Log.d("Masihada?", "retrieveFB: " + childkey1);
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
                    String answer4e = specificChild.child("answer4e").getValue(String.class);
                    if (answer1.equals("Dibawah 20 tahun") || answer1.equals("Diatas 35 tahun")) {
                        keteranganBuilder.append("Usia : ").append(answer1).append("\n");
                    }

                    if (answer2.equals("Dibawah 22.5 cm") || answer2.equals("Diatas 30 cm")) {
                        keteranganBuilder.append("Lingkar lengan atas : ").append(answer2).append("\n");
                    }

                    if (answer3.equals("Unwanted Pregnancy") || answer3a.equals("Unsupported") || answer3b.equals("KDRT") || answer3c.equals("Tuna Wisma") || !answer3e.isEmpty()) {
                        keteranganBuilder.append("Psikososial: ").append(answer3).append(",  ").append(answer3a).append(", ").append(answer3b).append(", ").append(answer3c).append(", ").append(answer3e).append("\n");
                    }

                    if (answer4.equals("Merokok") || answer4a.equals("Alkohol") || answer4b.equals("Obat berbahaya") || answer4c.equals("Zat berbahaya paparan dari tempat kerja") || !answer4e.isEmpty()) {
                        keteranganBuilder.append("Zat bahaya: ").append(answer4).append(", ").append(answer4a).append(", ").append(answer4b).append(", ").append(answer4c).append(", ").append(answer4e).append("\n");
                    }

                    if (answer5.equals("Ya") ) {
                        keteranganBuilder.append("ANC pertama kali lebih dari 20 minggu usia kehamilan").append("\n");
                    }
                    if(answer6.equals("Tidak")){
                        keteranganBuilder.append("Tidak pernah ANC").append("\n");

                    }

                    if (answer7.equals("Primigravida")) {
                        keteranganBuilder.append("Status kehamilan: ").append(answer7).append("\n");
                    }
                    if (answer7.equals("Grande Multipara (gravida>5)")) {
                        keteranganBuilder.append("Status kehamilan : ").append(answer7).append("\n");

                    }
                    if (answer7.equals("Nullipara")) {
                        keteranganBuilder.append("Status kehamilan : ").append(answer7).append("\n");

                    }

                    if (answer8.equals("Ya")) {
                        keteranganBuilder.append("Riwayat infertil").append("\n");
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

                  /*  String answer1 = specificChild.child("answer1").getValue(String.class);
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
                }*/
                    for (int i = 1; i <= 12; i++) {
                        String answer = specificChild.child("answer" + i).getValue(String.class);
                        if (answer != null && !answer.equals("Tidak")) {
                            keteranganBuilder2.append(answer).append("\n");
                        }
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
                    if (!answer1.equals("Tidak")) {
                        keteranganBuilder3.append(answer1).append("\n");
                    }
                    if (answer2.contains("kehamilan") || answer2.equals("Pertumbuhan janin terhambat")) {
                        keteranganBuilder3.append(answer2).append("\n");
                    }
                    if (answer3.equals("Ya")) {
                        keteranganBuilder3.append("Rhesus isoimunisasi").append("\n ");
                    }
                    if (answer4.equals("Ya")) {
                        keteranganBuilder3.append("hipertensi").append("\n");
                    }
                    if (answer4.contains("Preklamsia")) {
                        keteranganBuilder3.append(answer3).append("\n");
                    }
                    if (!answer5.equals("Tidak")) {
                        keteranganBuilder3.append("Kelainan bayi : ").append(answer5).append("\n");
                    }
                    if (answer6.contains("plasenta") || answer6.contains("Plasenta")) {
                        keteranganBuilder3.append(answer6).append("\n");
                    }
                    if (answer7.equals("Still birth (kematioan janin dalam kandungan setelah 24 minggu") || answer7.equals("Neonatal death (kematian bayi saat persalinan sampai usia 28 hari)")) {
                        keteranganBuilder3.append(answer7).append("\n");
                    }
                    if (answer9.equals("Induksi") || answer9.equals("Augmentasi persalinan")) {
                        keteranganBuilder3.append(answer9).append("\n");
                    }
                    if (answer8.equals("Ya")) {
                        keteranganBuilder3.append("Operasi sesar").append("\n");
                    }
                    if (answer10.equals("Ya")) {
                        keteranganBuilder3.append("Distosia bahu").append("\n");
                    }
                    if (answer11.equals("Ekstraksi vakum") || answer11.equals("Forcep")) {
                        keteranganBuilder3.append(answer11).append("\n");
                    }
                    if (!answer12.equals("Tidak")) {
                        keteranganBuilder3.append("Pendarahan post partum : ").append(answer12).append("\n");
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
                    String[] answerDescriptions = {
                            "Anemia", "Penyakit jantung", "Penyakit syaraf", "Penyakit hati",
                            "Penyakit ginjal", "Penyakit kelainan darah", "Penyakit kelainan endokrin",
                            "Penyakit autoimune dan jaringan ikat", "Penyakit kanker",
                            "Penyakit Infeksi(HIV, Sifilis, Hepatitis, TORCH, Penyakit Menular Seksual)",
                            "Penyakit kelainan uterus, serviks dan vagina", "Penyakit kelainan mental"
                    };
                    for (int i = 1; i <= 12; i++) {
                        String answer = specificChild.child("answer" + i).getValue(String.class);
                        if (answer != null && !answer.equals("Tidak")) {
                            switch (i) {
                                case 1:
                                    keteranganBuilder4.append("Anemia: ");
                                    break;
                                case 2:
                                    keteranganBuilder4.append("Penyakit jantung: ");
                                    break;
                                case 3:
                                    keteranganBuilder4.append("Penyakit syaraf: ");
                                    break;
                                case 4:
                                    keteranganBuilder4.append("Penyakit hati: ");
                                    break;
                                case 5:
                                    keteranganBuilder4.append("Penyakit ginjal: ");
                                    break;
                                case 6:
                                    keteranganBuilder4.append("Penyakit kelainan darah: ");
                                    break;
                                case 7:
                                    keteranganBuilder4.append("Penyakit kelainan endokrin: ");
                                    break;
                                case 8:
                                    keteranganBuilder4.append("Penyakit autoimune dan jaringan ikat: ");
                                    break;
                                case 9:
                                    keteranganBuilder4.append("Penyakit kanker: ");
                                    break;
                                case 10:
                                    keteranganBuilder4.append("Penyakit Infeksi(HIV, Sifilis, Hepatitis, TORCH, Penyakit Menular Seksual): ");
                                    break;
                                case 11:
                                    keteranganBuilder4.append("Penyakit kelainan uterus, serviks dan vagina: ");
                                    break;
                                case 12:
                                    keteranganBuilder4.append("Penyakit kelainan mental: ");
                                    break;
                            }

                            if (answer.equals("Not Identified")) {
                                keteranganBuilder4.append(answer).append("\n");
                            } else if (!answer.equals("Tidak")) {
                                keteranganBuilder4.append(answer).append("\n");
                            }
                        }


                  /*  for (int i = 1; i <= 12; i++) {
                        String answer = specificChild.child("answer" + i).getValue(String.class);
                        if (answer != null) {
                            if (answer.equals("Not Identified")) {
                                keteranganBuilder4.append(answerDescriptions[i - 1]).append(" : ").append(answer).append("\n");
                            } else if (!answer.equals("Tidak")) {
                                keteranganBuilder4.append(answer).append("\n");
                            }
                        }
                    }*/
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
                    for(int i = 1 ; i<=13 ; i ++){
                        String answer = specificChild.child("answer" + i).getValue(String.class);
                        if(answer!=null && !answer.contains("Tidak")){
                            switch (i) {
                                case 1:
                                    keteranganBuilder5.append("Ancaman abortus trimester 1: ");
                                    break;
                                case 2:
                                    keteranganBuilder5.append("Perkembangan Kehamilan: ");
                                    break;
                                case 3:
                                    keteranganBuilder5.append("Rhesus isoimmunisasi: ");
                                    break;
                                case 4:
                                    keteranganBuilder5.append("Hipertensi dalam kehamilan: ");
                                    break;
                                case 5:
                                    keteranganBuilder5.append("Kelainan bayi: ");
                                    break;
                                case 6:
                                    keteranganBuilder5.append("Multiple Pregnancy: ");
                                    break;
                                case 7:
                                    keteranganBuilder5.append("Kelainan letak/posisi dan presentasi: ");
                                    break;
                                case 8:
                                    keteranganBuilder5.append("Perdarahan antepartum: ");
                                    break;
                                case 9:
                                    keteranganBuilder5.append("Plasenta akreta: ");
                                    break;
                                case 10:
                                    keteranganBuilder5.append("KPD, Kelainan air ketuban(Oligo/polihydramnion): ");
                                    break;
                                case 11:
                                    keteranganBuilder5.append("Kelahiran premature mengancam: ");
                                    break;
                                case 12:
                                    keteranganBuilder5.append("CPD: ");
                                    break;
                                case 13:
                                    keteranganBuilder5.append("Kelainan jalan lahir: ");
                            }
                            if (answer.equals("Not Identified")) {
                                keteranganBuilder5.append(answer).append("\n");
                            } else if (!answer.contains("Tidak")) {
                                keteranganBuilder5.append(answer).append("\n");
                            }
                        }
                    }

           /*         if (answer1.equals("Not Identified")) {
                        keteranganBuilder5.append("ancaman abortus trimester 1 : ").append(answer1).append("\n");
                    } else if (!answer1.equals("Tidak") && answer1.equals("Ya")) {
                        keteranganBuilder5.append("ancaman abortus trimester 1").append("\n");
                    }

                    if (answer2.equals("Not Identified")) {
                        keteranganBuilder5.append("Perkembangan Kehamilan : ").append(answer2).append("\n");
                    } else if (answer2.equals("Kecil masa kehamilan") || answer2.equals("Pertumbuhan janin terhambat") || answer2.equals("Besar masa kehamilan")) {
                        keteranganBuilder5.append(answer2).append("\n");
                    }

                    if (answer3.equals("Not Identified")) {
                        keteranganBuilder5.append("Rhesus Isoimmunisasi : ").append(answer3).append("\n");
                    } else if (answer3.equals("Ya")) {
                        keteranganBuilder5.append("Rhesus isoimunisasi").append("\n");
                    }
                    if (answer4.equals("Not Identified")) {
                        keteranganBuilder5.append("Hipertensi dalam kehamilan : ").append(answer4).append("\n");
                    } else if (!answer4.equals("Tidak") && answer4.equals("Ya")) {
                        keteranganBuilder5.append("hipertensi").append("\n");
                    }

                    if (answer5.equals("Not Identified")) {
                        keteranganBuilder5.append("Kelainan Bayi : ").append(answer5).append("\n");
                    } else if (!answer5.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan bayi : ").append(answer5).append("\n");
                    }

                    if (answer8.equals("Not Identified")) {
                        keteranganBuilder5.append("Perdarahan antepartum : ").append(answer8).append("\n");
                    } else if (answer8.equals("Plasenta preiva") || answer8.equals("Solusio plasenta")) {
                        keteranganBuilder5.append(answer8).append(", ");
                    }

                    if (answer7.equals("Not Identified")) {
                        keteranganBuilder5.append("Kelainan Letak : ").append(answer7).append("\n");
                    } else if (!answer7.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan letak : ").append(answer7).append("\n");
                    }

                    if (answer6.equals("Not Identified")) {
                        keteranganBuilder5.append("Multiple Pregnancy : ").append(answer6).append("\n");
                    } else if (!answer6.equals("Tidak")) {
                        keteranganBuilder5.append("Multiple Pregnancy : ").append(answer6).append("\n");
                    }

                    if (answer9.equals("Not Identified")) {
                        keteranganBuilder5.append("Plasenta Akreta : ").append(answer9).append("\n");
                    } else if (answer9.equals("Ya")) {
                        keteranganBuilder5.append("Plasenta akreta").append("\n");
                    }

                    if (answer10.equals("Not Identified")) {
                        keteranganBuilder5.append("Kelainan air ketuban : ").append(answer10).append("\n");
                    } else if (!answer10.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan air ketuban : ").append(answer10).append("\n");
                    }

                    if (answer11.equals("Not Identified")) {
                        keteranganBuilder5.append("Kelahiran premature mengancam : ").append(answer11).append("\n");
                    } else if (answer11.equals("Ya")) {
                        keteranganBuilder5.append("Premature mengancam").append("\n");
                    }

                    if (answer12.equals("Not Identified")) {
                        keteranganBuilder5.append("CPD : ").append(answer12).append("\n");
                    } else if (answer12.equals("Ya")) {
                        keteranganBuilder5.append("CPD").append("\n");
                    }

                    if (answer13.equals("Not Identified")) {
                        keteranganBuilder5.append("Kelainan jalan lahir : ").append(answer13).append("\n");
                    } else if (!answer13.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan jalan lahir : ").append(answer13).append("\n");
                    }*/


               /*     if (!answer1.equals("Tidak")  && answer1.equals("Ya")) {
                        keteranganBuilder5.append("ancaman abortus trimester 1").append("\n");
                    } else if(answer1.equals("Not Identified")) {
                        keteranganBuilder5.append("ancaman abortus trimester 1 : ").append(answer1).append("\n");
                    }

                    if (answer2.contains("kehamilan") || answer2.equals("Pertumbuhan janin terhambat")) {
                        keteranganBuilder5.append(answer2).append("\n");
                    } else if(answer2.equals("Not Identified")) {
                        keteranganBuilder5.append("Perkembangan Kehamilan : ").append(answer2).append("\n");
                    }

                    if (answer3.equals("Ya")) {
                        keteranganBuilder5.append("Rhesus isoimunisasi").append("\n");
                    } else  if(answer3.equals("Not Identified")){
                        keteranganBuilder5.append("Rhesus Isoimmunisasi : ").append(answer3).append("\n");
                    }

                    if (!answer4.equals("Tidak")&& answer4.equals("Ya")) {
                        keteranganBuilder5.append("hipertensi").append("\n");
                    } else if (answer4.contains("Preklamsia")) {
                        keteranganBuilder5.append(answer4).append("\n");
                    } else if(answer4.equals("Not Identified")){
                        keteranganBuilder5.append("Hipertensi dalam kehamilan : ").append(answer4).append("\n");
                    }

                    if (!answer5.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan bayi : ").append(answer5).append("\n");
                    } else if(answer5.equals("Not Identified")){
                        keteranganBuilder5.append("Kelainan Bayi : ").append(answer5).append("\n");
                    }

                    if (answer8.contains("plasenta") || answer8.contains("Plasenta")) {
                        keteranganBuilder5.append(answer8).append(", ");
                    } else if(answer8.equals("Not Identified")){
                        keteranganBuilder5.append("Perdarahan antepartum : ").append(answer8).append("\n");
                    }

                    if (!answer7.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan letak : ").append(answer7).append("\n");
                    } else if(answer7.equals("Not Identified")){
                        keteranganBuilder5.append("Kelainan Letak : ").append(answer7).append("\n");
                    }

                    if (!answer6.equals("Tidak")) {
                        keteranganBuilder5.append("Multiple Pregnancy : ").append(answer6).append("\n");
                    } else if(answer6.equals("Not Identified")){
                        keteranganBuilder5.append("Multiple Pregnancy : ").append(answer6).append("\n");
                    }

                    if (answer9.equals("Ya")) {
                        keteranganBuilder5.append("Plasenta akreta").append("\n");
                    } else if(answer9.equals("Not Identified")){
                        keteranganBuilder5.append("Plasenta Akreta : ").append(answer9).append("\n");
                    }

                    if (!answer10.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan air ketuban : ").append(answer10).append("\n");
                    } else if(answer10.equals("Not Identified")){
                        keteranganBuilder5.append("Kelainan air ketuban : ").append(answer10).append("\n");
                    }

                    if (answer11.equals("Ya")) {
                        keteranganBuilder5.append("Premature mengancam").append("\n ");
                    } else if(answer11.equals("Not Identified")){
                        keteranganBuilder5.append("Kelahiran premature mengancam : ").append(answer11).append("\n");
                    }

                    if (answer12.equals("Ya")) {
                        keteranganBuilder5.append("CPD").append("\n");
                    } else if(answer12.equals("Not Identified")){
                        keteranganBuilder5.append("CPD : ").append(answer12).append("\n ");
                    }

                    if (!answer13.equals("Tidak")) {
                        keteranganBuilder5.append("Kelainan jalan lahir : ").append(answer13).append("\n");
                    } else if(answer13.equals("Not Identified")){
                        keteranganBuilder5.append("Kelainan jalan lahir : ").append(answer13).append("\n ");
                    }
                }*/
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

        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textFKTP = findViewById(R.id.fktp);
                StringBuilder fktp = new StringBuilder();
                if (dataSnapshot.exists()) {
                    Log.d("What's", "onDataChange: " + patientChildkey);
                    DataSnapshot specificChild = dataSnapshot.child(patientChildkey);
                    String Response8 = specificChild.child("response8").getValue(String.class);
                    Log.d("What's", "onDataChange: " + Response8);
                    if (Response8 != null) {
                        fktp.append("FKTP/FKRTL : " + Response8);
                        Log.d("What's", "onDataChange: " + Response8);


                    }
                    String newfktp = fktp.toString().trim();
                    textFKTP.setText(newfktp);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        answersRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statuskehamilan = findViewById(R.id.status);
                StringBuilder status = new StringBuilder();
                if (dataSnapshot.exists()) {
                    DataSnapshot specificChild = dataSnapshot.child(statusChildkey);
                    String data = specificChild.child("data").getValue(String.class);

                    if (data != null) {
                        status.append(data);


                    }
                    String status1 = status.toString().trim();
                    statuskehamilan.setText(status1);
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
                        Intent intent = new Intent(reportActivity.this, MainActivity.class);
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

    public void retrievetoken() {

        String userId = db.getPatientinfo(dbhelper, "PatientName", null);
        ; // Replace with the user's name or ID
        // This will hold the FCM token
        rumahsakittujuan = findViewById(R.id.isisendiri);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tokens");
        Query userTokenQuery = databaseReference.child(userId);

        userTokenQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userToken = dataSnapshot.getValue(String.class);
                    Log.d("FCM Token", "Token found for user: " + userId);
                    Log.d("FCM Token", "Token: " + userToken);
                    try {
                        SendNotification sendNotificationTask = new SendNotification(reportActivity.this, userToken, "Rujuk ke FKRTL I/II", "Anda harus segera pergi ke RS" + rumahsakittujuan.getText().toString(), totalscore);
                        sendNotificationTask.execute();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Log.d("FCM Token", "Token not found for user: " + userId);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("FCM Token", "Error retrieving FCM token: " + databaseError.getMessage());
            }
        });
    }


    private class SendNotification extends AsyncTask<Void, Void, Void> {


        String SERVER_KEY = "AAAA80rcNRc:APA91bH8itjJJD5I4jUyFkBqdH5OzhcIYiFZgMAs-2-_4YNIPW4ybESMQo0rGmenoagjv9Lq_P8_yN3oU-2bZG79C-felCPTjmpj3_rRCDH6-YsKiAH40ddMlL7O9BkDke7qbnazodnz";
        String API_URL = "https://fcm.googleapis.com/fcm/send";
        private String userToken;
        private String title;
        private String message;
        private int totalscores;
        private Context context;

        SendNotification(Context context, String userToken, String title, String message, int totalscore) {
            this.userToken = userToken;
            this.title = title;
            this.message = message;
            this.totalscores = totalscore;
            this.context = context;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                // Your notification sending logic here, using userToken, title, and message
                URL url = new URL(API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
                conn.setRequestProperty("Content-Type", "application/json");

                JSONObject json = new JSONObject();
                json.put("to", userToken.trim());
                JSONObject notification = new JSONObject();
                notification.put("title", title);
                notification.put("body", message);
                json.put("notification", notification);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.custom_notification_layout);

                // Determine the background color based on the condition
                boolean isConditionMet = totalscores > 7;
                Log.d("Berapasih", "doInBackground: " + totalscores);// Replace this with your condition

                int backgroundColor;
                if (isConditionMet) {
                    backgroundColor = R.color.colorRed;
                } else {
                    backgroundColor = R.color.colorYellow;
                }

                // Set the background color in the custom layout
                remoteViews.setInt(R.id.notification_layout, "setBackgroundColor", ContextCompat.getColor(context, backgroundColor));

                // Apply the custom layout to the notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher_patient)
                        .setCustomContentView(remoteViews)
                        .setAutoCancel(false)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

                // Create a unique notification ID to update or cancel the notification later
                int notificationId = 1;

                // Show the notification
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                if (ActivityCompat.checkSelfPermission(reportActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                }
                notificationManager.notify(0, builder.build());



                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    Log.d("Notification", "send notification. Response code: " + responseCode);
                } else {
                    // Handle the case where the notification sending failed
                    Log.d("Notification", "Failed to send notification. Response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Notification", "Error sending notification: " + e.getMessage());
            }
            return null;
        }
    }
}