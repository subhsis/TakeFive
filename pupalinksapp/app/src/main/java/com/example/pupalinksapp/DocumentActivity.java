package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.barteksc.pdfviewer.PDFView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static com.example.pupalinksapp.Connection.connect;

public class DocumentActivity extends AppCompatActivity {

    String name="";
    PDFView pdfView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        pdfView = findViewById(R.id.pdfview);

        name = Objects.requireNonNull(getIntent().getExtras()).getString("name");
        String url = connect+"storage/doc/"+name;

        new Retrivepdfdat().execute(url);

    }
    @SuppressLint("StaticFieldLeak")
    class Retrivepdfdat extends AsyncTask<String,Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if(httpURLConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
           pdfView.fromStream(inputStream).load();
        }
    }

}
