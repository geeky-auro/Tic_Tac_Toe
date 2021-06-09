package com.AuroSaswatRaj.noughtsandcrosses;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class BackgroundSoundService extends Service {

    public static MediaPlayer gamebackground;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        gamebackground=MediaPlayer.create(this,R.raw.realbackgroundgamemusic);
        gamebackground.setLooping(true);
        gamebackground.setVolume(100,100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        gamebackground.start();
        return startId;
    }

    @Override
    public void onStart(Intent intent, int startId) {

    }

    @Override
    public void onDestroy() {
        gamebackground.stop();
        gamebackground.release();
    }



    @Override
    public void onLowMemory() {

    }
}
