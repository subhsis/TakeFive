package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Home extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button play,pause;


   @SuppressLint("StaticFieldLeak")
   public static RelativeLayout relativeLayout;
   public static int count=0;
   public static int countVideo=0;
   public static int countAudio = 0;
   public static int countdocument = 0;
   public static int countmore = 0;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Homefragment()).commit();
        play = findViewById(R.id.button_play);
        pause = findViewById(R.id.button_pause);
        relativeLayout = findViewById(R.id.song_navigate);





        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Audioplayer.player1.setPlayWhenReady(false);
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plain();

            }

            private void plain() {

                if (Audioplayer.player1 != null){
                    Audioplayer.player1.setPlayWhenReady(true);
                }
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
            }
        });


    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceType")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment =null;
            switch (menuItem.getItemId()){
                case R.id.home:
                    fragment = new Homefragment();
                    count = count+1;
                    break;
                case R.id.video:
                    fragment = new Videofragment();
                    countVideo = countVideo+1;
                    break;
                case R.id.audio:
                    fragment = new Musicfragment();
                    countAudio = countAudio+1;
                    break;
                case R.id.document:
                    fragment = new Documentfragment();
                    countdocument = countdocument+1;
                    break;
                case R.id.more:
                    fragment = new Morefragment();
                    countmore = countmore+1;
                    break;
            }

            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

}
