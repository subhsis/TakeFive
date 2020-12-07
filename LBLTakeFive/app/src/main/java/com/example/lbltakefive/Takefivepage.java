package com.example.lbltakefive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Takefivepage extends AppCompatActivity {
    EditText name,employee_no,job_location,job_description;
    Button next,date_picker;
    String Username,Uemployee_no,Udate,ujob_location,Ujob_description;
    TextView selected_date;
    CircleImageView profile_image;
    byte[] imagedata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takefivepage);
        name = findViewById(R.id.name);
        employee_no = findViewById(R.id.employee_no);
        job_location = findViewById(R.id.job_location);
        job_description = findViewById(R.id.job_decs);
        selected_date = findViewById(R.id.seleted_date);
        date_picker = findViewById(R.id.date_picker);
        profile_image = findViewById(R.id.profile_image);


        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
                selected_date.setText(currentDateTimeString);

            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 captureImage();
            }
        });


        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (validate()) {
                   Intent intent = new Intent(Takefivepage.this, Takefivepage2.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   intent.putExtra("name",Username);
                   intent.putExtra("employee_name",Uemployee_no);
                   intent.putExtra("date",Udate);
                   intent.putExtra("job_location",ujob_location);
                   intent.putExtra("job_description",Ujob_description);
                   intent.putExtra("profileimage",imagedata);
                   startActivity(intent);
                   finish();
               }
            }
        });
    }

    private void captureImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
       else {
            Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takepicture, 101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        Bitmap imagebitmap = (Bitmap) extras.get("data");
        imagedata = getimagebyte(imagebitmap);
        profile_image.setImageBitmap(imagebitmap);
    }

    private byte[] getimagebyte(Bitmap imagebitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imagebitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    private boolean validate() {
        boolean valid = true;
        Username = name.getText().toString().trim();
        Uemployee_no = employee_no.getText().toString().trim();
        Udate = selected_date.getText().toString().trim();
        ujob_location = job_location.getText().toString().trim();
        Ujob_description = job_description.getText().toString().trim();

        if (Username.isEmpty()){
            Toast.makeText(this,"Name must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if(Uemployee_no.isEmpty()){
            Toast.makeText(this,"Employee_no must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if (Udate.isEmpty()){
            Toast.makeText(this,"Date must be fill",Toast.LENGTH_LONG).show();
            valid= false;
        }else if (ujob_location.isEmpty()){
            Toast.makeText(this,"Please enter job location",Toast.LENGTH_LONG).show();
            valid = false;
        }else if (Ujob_description.isEmpty()){
            Toast.makeText(this,"Please enter job description",Toast.LENGTH_LONG).show();
            valid = false;

        }
        return valid;

    }
}
