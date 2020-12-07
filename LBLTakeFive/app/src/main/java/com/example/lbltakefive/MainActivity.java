package com.example.lbltakefive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.lbltakefive.Databases.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transtion);
        logo.startAnimation(myanim);

        Permissions.verifyStoragePermissions(this);

        final Intent intent = new Intent(this,Takefivepage.class);
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }

        };
        thread.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
//        String sql = "DELETE FROM Employee WHERE date <= date('now','-2 day')";
//        database.execSQL(sql);
    }
}
