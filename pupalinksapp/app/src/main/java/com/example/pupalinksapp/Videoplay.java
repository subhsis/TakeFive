package com.example.pupalinksapp;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.pupalinksapp.Audioplayer.intentService;
import static com.example.pupalinksapp.Audioplayer.player1;
import static com.example.pupalinksapp.Connection.connect;
import static com.example.pupalinksapp.Home.count;
import static com.example.pupalinksapp.Home.countAudio;
import static com.example.pupalinksapp.Home.countVideo;
import static com.example.pupalinksapp.Home.countdocument;
import static com.example.pupalinksapp.Home.countmore;
import static com.example.pupalinksapp.ViewAdapter.Video_id;


public class Videoplay extends AppCompatActivity {
    VideoView videoView;
    JsonObjectRequest jsonObjectRequest2;
    RequestQueue requestQueue2;
    public MediaController controller = null;
    String id,id2;
    private long presedBackTime;
    private Toast backToast;
    public static double videoduration;
    public  static double aDduration;
    RelativeLayout banneradview;
    Button addelet;
    ImageView imageView;
    String content_type,Adpath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //full screen resulation for video view
        videoView = findViewById(R.id.myVideo);
        banneradview = findViewById(R.id.videoad);
        addelet = findViewById(R.id.addelete);
        imageView = findViewById(R.id.banner_ad);
        controller = new MediaController(this);
        controller.setMediaPlayer(mcEvents);
        ImageAd();

        addelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                banneradview.setVisibility(View.GONE);
            }
        });

    }



    /*onstart video play through mediacontroler....
    and its control its behaviour  on player-pause & on-player-resume
     */
    @Override
    protected void onStart() {
        super.onStart();
        id2 = Objects.requireNonNull(getIntent().getExtras()).getString("adId");
        id = Objects.requireNonNull(getIntent().getExtras()).getString("id");
        String adresss = connect+"getStream.php?id="+id;
        Uri uri = Uri.parse(adresss);
        videoView.setVideoURI(uri);
        controller.setAnchorView(videoView);
        videoView.setMediaController(controller);
        videoView.start();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(player1!=null){
            player1.setPlayWhenReady(false);
        }else{
            videoView.start();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private MediaController.MediaPlayerControl mcEvents = new MediaController.MediaPlayerControl() {
        public void start() {
            videoView.start();
        }

        public void seekTo(int pos) {
            videoView.seekTo(pos);
        }

        public void pause() {
            videoView.pause();
        }

        public boolean isPlaying() {
            return videoView.isPlaying();
        }

        public int getDuration() {
            return videoView.getDuration();
        }

        public int getCurrentPosition() {
            return videoView.getCurrentPosition();
        }

        @Override
        public int getBufferPercentage() {
            return 0;
        }

        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }

        public boolean canSeekBackward() {
            return true;
        }

        public boolean canPause() {
            return true;
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        videoduration=videoView.getCurrentPosition()/1000.0;
        videoView.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityData();
    }

    @Override
    public void onBackPressed() {
        if(presedBackTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast=Toast.makeText(getBaseContext(),"Pressed Back Again To Exit!",Toast.LENGTH_SHORT);
            backToast.show();
        }
        presedBackTime  = System.currentTimeMillis();

    }
    /*on acityvgity pause its send data to server
    in videiduration-time video_ad_duration_time video_id and video_ad _id
     */
    private void ActivityData() {

        String url = connect+"actyvity.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            @Override
            protected Map<String, String> getParams() {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Video_id",Video_id);
                    jsonObject.put("video_duration",Videoplay.videoduration);
                    jsonObject.put("video_ad_duration",Videoplay.aDduration);
                    jsonObject.put("video_ad_id",id2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Map<String,String> params = new HashMap<>();
                params.put("Homefragment", String.valueOf(count));
                params.put("Videofargment", String.valueOf(countVideo));
                params.put("Documentfragment", String.valueOf(countdocument));
                params.put("Morefragment", String.valueOf(countmore));
                params.put("Videodata",jsonObject.toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void ImageAd() {
        String Url =connect+"get_random_imagead.php";
        jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET,Url,null, new Response.Listener<JSONObject>() {

            public void onResponse(JSONObject response) {

                try {
                    content_type = response.getString("contentType");
                    Adpath = response.getString("path");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // view setup for data
                try {
                    Glide.with(Videoplay.this).load(connect + Adpath).into(imageView);
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue2 = Volley.newRequestQueue(Objects.requireNonNull(Videoplay.this));
        requestQueue2.add(jsonObjectRequest2);
    }


}
