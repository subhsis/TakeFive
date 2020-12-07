package com.example.pupalinksapp;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import static com.example.pupalinksapp.Audioplayer.array;
import static com.example.pupalinksapp.Audioplayer.player1;
import static com.example.pupalinksapp.Channel.PLAYBACK_CHANNEL_ID;
import static com.example.pupalinksapp.Channel.PLAYBACK_NOTIFICATION_ID;
import static com.example.pupalinksapp.Home.relativeLayout;



public class Audioplayerservece extends Service implements EventListener {
    // public static SimpleExoPlayer player;
    PlayerNotificationManager playerNotificationManager;
    Intent intent;



    @Override
    public void onCreate() {
        super.onCreate();
        final Context context = this;

        player1 = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        DefaultDataSourceFactory sourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"audio_demo"));
        // MediaSource mediaSource = new ExtractorMediaSource.Factory(sourceFactory).createMediaSource(Uri.parse(String.valueOf(id)));
        ConcatenatingMediaSource concatenatingMediaSource = new ConcatenatingMediaSource();
         //concatenatingMediaSource.addMediaSource(mediaSource);
        for(int i=0;i<array.size(); i++){
            MediaSource mediaSource = new ExtractorMediaSource.Factory(sourceFactory).createMediaSource(Uri.parse(String.valueOf(array.get(i).audioplay)));
            concatenatingMediaSource.addMediaSource(mediaSource);
        }
        player1.prepare(concatenatingMediaSource);
        player1.setPlayWhenReady(true);


        //.......player notification manager to create a notification bar on service on going.........
        playerNotificationManager = PlayerNotificationManager.createWithNotificationChannel(this, PLAYBACK_CHANNEL_ID, R.string.audio_em, PLAYBACK_NOTIFICATION_ID, new PlayerNotificationManager.MediaDescriptionAdapter() {
            @Override
            public String getCurrentContentTitle(Player player) {
                return null;
            }

            @android.support.annotation.Nullable
            @Override
            public PendingIntent createCurrentContentIntent(Player player) {
                intent = new Intent(context,Audioplayer.class);
                return PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            }

            @android.support.annotation.Nullable
            @Override
            public String getCurrentContentText(Player player) {
                return null;
            }

            @android.support.annotation.Nullable
            @Override
            public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
                return  null;
            }
        });


        //.......player notificationlistner for audioplay....... that use to catch the music and play on background  service
        playerNotificationManager.setNotificationListener(new PlayerNotificationManager.NotificationListener() {
            @Override
            public void onNotificationStarted(int notificationId, Notification notification) {
                startForeground(notificationId,notification);

            }

            @Override
            public void onNotificationCancelled(int notificationId) {
                stopSelf();
                //........layout from home activity.....
                relativeLayout.setVisibility(View.GONE);

            }
        });

        playerNotificationManager.setPlayer(player1);
        player1.prepare(concatenatingMediaSource);
        player1.setPlayWhenReady(true);



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        playerNotificationManager.setPlayer(null);
        player1.release();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {


    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
