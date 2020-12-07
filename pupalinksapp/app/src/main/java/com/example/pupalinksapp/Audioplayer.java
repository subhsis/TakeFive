package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.util.CircularArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.util.Util;
import java.util.Objects;

import static com.example.pupalinksapp.Musicfragment.audioplayurls;

public class Audioplayer extends AppCompatActivity {
    //public PlayerView playerView;
    public static Intent intentService;
    @SuppressLint("StaticFieldLeak")
    public static SimpleExoPlayer player1;
    public  static String id;
    ImageView imageView;
    String image_url;
    public static CircularArray<CircularaudioModel> array;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioplayer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

      //  playerView= findViewById(R.id.player);
        imageView = findViewById(R.id.imageaudio);
        try {
            id= null;
            image_url = Objects.requireNonNull(getIntent().getExtras()).getString("image");

            id = Objects.requireNonNull(getIntent().getExtras()).getString("id");
            //foreground service call in  activity
            intentService = new Intent(this, Audioplayerservece.class);
            Util.startForegroundService(this, intentService);
        }catch (Exception ignored){

        }
        Glide.with(this).load(image_url).into(imageView);
      // playerView.setPlayer(player1);

        /* setting audio item
        to circular array to play playlist reapitedly
         */
        array = new CircularArray<>();
        array.addFirst(new CircularaudioModel(id));
        for(int i=0;i<audioplayurls.size(); i++) {
            array.addLast(new CircularaudioModel(audioplayurls.get(i).audiourl));
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
