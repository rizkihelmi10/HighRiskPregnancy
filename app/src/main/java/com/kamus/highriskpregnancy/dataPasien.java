package com.kamus.highriskpregnancy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class dataPasien extends AppCompatActivity {
    private EditText noPasienEditText;
    private EditText namaPasienEditText;
    private EditText kunjunganKeEditText;
    private EditText nomorKTPEditText;
    private EditText nomorHpEditText;
    private EditText tanggalLahirEditText;
    private EditText alamatEditText;
    private Button continueButton;
    private Calendar myCalendar;
    MySQLiteHelper dbhelper;
    dbActivities db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);

        noPasienEditText = findViewById(R.id.editTextText);
        namaPasienEditText = findViewById(R.id.editTextText2);
        kunjunganKeEditText = findViewById(R.id.editTextText3);
        nomorKTPEditText = findViewById(R.id.editTextText4);
        nomorHpEditText = findViewById(R.id.editTextText5);
        tanggalLahirEditText = findViewById(R.id.editTextText7);
        alamatEditText = findViewById(R.id.editTextText6);
        continueButton = findViewById(R.id.button);
        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        tanggalLahirEditText.setOnClickListener(v -> {
            new DatePickerDialog(
                    dataPasien.this,
                    dateSetListener,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show();
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText views
                String noPasien = noPasienEditText.getText().toString();
                String namaPasien = namaPasienEditText.getText().toString();
                String kunjunganKe = kunjunganKeEditText.getText().toString();
                String nomorKTP = nomorKTPEditText.getText().toString();
                String nomorHp = nomorHpEditText.getText().toString();
                String tanggalLahir = tanggalLahirEditText.getText().toString();
                String alamat = alamatEditText.getText().toString();
                if(validateEditTextFields()){
                db.SavePatientDB(dbhelper, noPasien,namaPasien,kunjunganKe,nomorKTP,nomorHp,tanggalLahir,alamat);
                Log.d("What saved inside DB", "onClick: " + noPasien);
                Log.d("What saved inside DB", "onClick: " + namaPasien);
                Log.d("What saved inside DB", "onClick: " + kunjunganKe);
                Intent intent = new Intent(dataPasien.this,question1.class);
                startActivity(intent);}
                else{
                    Toast.makeText(getApplicationContext(), "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; // Change this format as needed
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggalLahirEditText.setText(sdf.format(myCalendar.getTime()));
    }
    private boolean validateEditTextFields() {
        // Get the text from EditText fields
        String noPasien = noPasienEditText.getText().toString().trim();
        String namaPasien = namaPasienEditText.getText().toString().trim();
        String kunjunganKe = kunjunganKeEditText.getText().toString().trim();
        String nomorKTP = nomorKTPEditText.getText().toString().trim();
        String nomorHp = nomorHpEditText.getText().toString().trim();
        String tanggalLahir = tanggalLahirEditText.getText().toString().trim();
        String alamat = alamatEditText.getText().toString().trim();

        // Check if any of the fields are empty
        if (TextUtils.isEmpty(noPasien) || TextUtils.isEmpty(namaPasien) ||
                TextUtils.isEmpty(kunjunganKe) || TextUtils.isEmpty(nomorKTP) ||
                TextUtils.isEmpty(nomorHp) || TextUtils.isEmpty(tanggalLahir) ||
                TextUtils.isEmpty(alamat)) {
            return false; // At least one field is empty
        }

        // All fields are filled
        return true;
    }
}