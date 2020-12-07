package com.example.lbltakefive.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import com.example.lbltakefive.Databases.DatabaseHelper;
import com.example.lbltakefive.R;
import com.example.lbltakefive.Takefivepage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Succefulactivity extends AppCompatActivity {
    String possibleEmail;
    DatabaseHelper databaseHelper;
    String username = "";
    String password = "";


    Button button,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succefulactivity);
        button = findViewById(R.id.add_new);
        exit = findViewById(R.id.exit);
        databaseHelper = new DatabaseHelper(this);



        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
            }
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createPdf();
               new Sendmaial().execute();
               finish();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Succefulactivity.this, Takefivepage.class);
                startActivity(intent);
            }
        });
    }




    public static class Sendmaial extends AsyncTask<String,Integer,Integer> {


        @Override
        protected Integer doInBackground(String... strings) {
            final String username = "subhasis.mondol@pupalinks.com";
            final String password = "9735518364@subha";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtpout.secureserver.net");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("subhasis.mondol@pupalinks.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("subhasis391@gmail.com"));
                message.setSubject("Testing Subject");
                message.setText("Dear Mail Crawler,"
                        + "\n\n No spam to my email, please!");

                MimeBodyPart messageBodyPart;
                Multipart multipart = new MimeMultipart();
                messageBodyPart = new MimeBodyPart();
                String filename="MyBackUp.csv";
                File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);



                String fileName2 = "newFile.csv";
                FileDataSource source =  new FileDataSource(String.valueOf(filelocation));
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName2);
                multipart.addBodyPart(messageBodyPart);

                message.setContent(multipart);

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException  e) {
                throw new RuntimeException(e);
            }
            return 1;
        }
    }


    private void createPdf() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor c;
        try {
            c = sqLiteDatabase.rawQuery("select * from Employee", null);
            int rowcount;
            int colcount;
            File sdCardDir = Environment.getExternalStorageDirectory();
            String filename = "MyBackUp.csv";
            // the name of the file to export with
            File saveFile = new File(sdCardDir, filename);
            FileWriter fw = new FileWriter(saveFile);

            BufferedWriter bw = new BufferedWriter(fw);
            rowcount = c.getCount();
            colcount = c.getColumnCount();
            if (rowcount > 0) {
                c.moveToFirst();

                for (int i = 0; i < colcount; i++) {
                    if (i != colcount - 1) {

                        bw.write(c.getColumnName(i) + ",");

                    } else {

                        bw.write(c.getColumnName(i));

                    }
                }
                bw.newLine();

                for (int i = 0; i < rowcount; i++) {
                    c.moveToPosition(i);

                    for (int j = 0; j < colcount; j++) {
                        if (j != colcount - 1)
                            bw.write(c.getString(j) + ",");
                        else
                            bw.write(c.getString(j));
                    }
                    bw.newLine();
                }
                bw.flush();

            }
        } catch (Exception ex) {
            if (sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();

            }

        }
    }

}
