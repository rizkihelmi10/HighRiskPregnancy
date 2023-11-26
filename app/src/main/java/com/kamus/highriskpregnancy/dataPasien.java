package com.kamus.highriskpregnancy;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;


import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.text.TextRecognition;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;


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
    private PreviewView viewFinder;
    private ImageCapture imageCapture;
    private TextRecognizer textRecognizer;
    private EditText editTextCardNumber;
    private Button captureButton;
    private Executor executor = Executors.newSingleThreadExecutor();
    private ActivityResultLauncher<Intent> cameraLauncher;
    private EditText FKTP;
    private TextView FKTPText;
    String childkeyPatient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pasien);
        db = new dbActivities(); // Initialize dbActivities
        dbhelper = new MySQLiteHelper(this);
        imageCapture = new ImageCapture.Builder().build();


        noPasienEditText = findViewById(R.id.editTextText);
        namaPasienEditText = findViewById(R.id.editTextText2);
        kunjunganKeEditText = findViewById(R.id.editTextText3);
        nomorKTPEditText = findViewById(R.id.editTextText4);
        nomorHpEditText = findViewById(R.id.editTextText5);
        tanggalLahirEditText = findViewById(R.id.editTextText7);
        FKTP = findViewById(R.id.editTextText31);
        FKTPText = findViewById(R.id.textView51);

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
                String fktp = FKTP.getText().toString();
                if(validateEditTextFields()){
                db.SavePatientDB(dbhelper, noPasien,namaPasien,kunjunganKe,nomorKTP,nomorHp,tanggalLahir,alamat);
                Log.d("What saved inside DB", "onClick: " + noPasien);
                Log.d("What saved inside DB", "onClick: " + namaPasien);
                Log.d("What saved inside DB", "onClick: " + kunjunganKe);
                AnswerPatient answerPatient = new AnswerPatient();
                    answerPatient.setResponse1(noPasien);
                    answerPatient.setResponse2(namaPasien);
                    answerPatient.setResponse3(kunjunganKe);
                    answerPatient.setResponse4(nomorKTP);
                    answerPatient.setResponse5(nomorHp);
                    answerPatient.setResponse6(tanggalLahir);
                    answerPatient.setResponse7(alamat);
                    answerPatient.setResponse8(fktp);
                saveAnswersToFirebase(answerPatient);
                }
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
        String fktp = FKTP.getText().toString().trim();

        // Check if any of the fields are empty
        if (TextUtils.isEmpty(noPasien) || TextUtils.isEmpty(namaPasien) ||
                TextUtils.isEmpty(kunjunganKe) || TextUtils.isEmpty(nomorKTP) ||
                TextUtils.isEmpty(nomorHp) || TextUtils.isEmpty(tanggalLahir) ||
                TextUtils.isEmpty(alamat) || TextUtils.isEmpty(fktp) ) {
            return false; // At least one field is empty
        }

        // All fields are filled
        return true;
    }
    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();
        preview.setSurfaceProvider(viewFinder.getSurfaceProvider());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            imageCapture = new ImageCapture.Builder()
                    .setTargetRotation(getDisplay().getRotation())
                    .build();
        }

        Camera camera = (Camera) cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }
    public void openCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1);
        } else {
            Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // The image was successfully captured.
            // You can access the captured image in the 'data' Intent or use its URI.

            // Save the image to a file
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            File imageFile = saveImageToFile(imageBitmap);

            // Process the image for text recognition
            try {
                processImageForText(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private File saveImageToFile(Bitmap imageBitmap) {
        File filesDir = getFilesDir();
        File imageFile = new File(filesDir, "captured_image.jpg");

        try (FileOutputStream out = new FileOutputStream(imageFile)) {
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageFile;
    }
    private void saveAnswersToFirebase(AnswerPatient answerPatient) {
        // Get a reference to your Firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference answersRef = database.getReference("answers");
        DatabaseReference newAnswerRef = answersRef.push();

        // Save the data to Firebase
        newAnswerRef.setValue(answerPatient);
        Query query = answersRef.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    childkeyPatient = childSnapshot.getKey(); // Get the key of the child node
                    AnswerPatient answerPatient = childSnapshot.getValue(AnswerPatient.class);
                    Toast.makeText(getApplicationContext(), "key:" + childkeyPatient, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dataPasien.this,afterDataFilled.class);
                    intent.putExtra("childkeyPatient", childkeyPatient);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    /* private void captureImage() {
        File outputDirectory = getOutputDirectory();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
        String fileName = "IMG_" + sdf.format(System.currentTimeMillis()) + ".jpg";
        File file = new File(outputDirectory, fileName);


        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

        imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                try {
                    processImageForText(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                exception.printStackTrace();
            }
        });
    }*/
    private void processImageForText(File imageFile) throws IOException {
        nomorKTPEditText = findViewById(R.id.editTextText4);
        InputImage inputImage;
        try {
            inputImage = InputImage.fromFilePath(this, Uri.fromFile(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        com.google.mlkit.vision.text.TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        Task<Text> result = recognizer.process(inputImage);
        result.addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(Text visionText) {
                String recognizedText = visionText.getText();
                String ktpNumber =  extractIdCardNumber(recognizedText);

                if (ktpNumber != null) {
                    showToast("KTP Number: " + ktpNumber);
                    nomorKTPEditText.setText(ktpNumber);
                } else {
                    showToast("KTP Number not found");
                }
            }
        });
        // Initialize a TextRecognizer

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private File getOutputDirectory() {
        File mediaDir = new File(getExternalMediaDirs()[0], "YourAppDirectoryName");
        if (!mediaDir.exists()) {
            if (!mediaDir.mkdirs()) {
                return getFilesDir();
            }
        }
        return mediaDir;
    }
    private String extractIdCardNumber(String recognizedText) {
        // This example assumes a 16-digit number.
        String ktpPattern = "\\d{16}";
        String match = "NIK : 123456789012345";

        // Create a Pattern object for the regular expression
        Pattern pattern = Pattern.compile(ktpPattern);

        // Use a Matcher to find the pattern in the recognized text
        Matcher matcher = pattern.matcher(match);

        if (matcher.find()) {
            // Extract the KTP number from the recognized text
            String ktpNumber = matcher.group();
            return ktpNumber;
        } else {
            // KTP number not found
            return null;
        }
    }
}