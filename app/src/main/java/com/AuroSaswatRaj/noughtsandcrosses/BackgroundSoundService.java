package com.AuroSaswatRaj.noughtsandcrosses;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BackgroundSoundService extends Service {

    public static MediaPlayer backgroundMusic;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Random random=new Random();
        int chooseMusic=random.nextInt(2);
        switch (chooseMusic) {
            case 0:{
                backgroundMusic =MediaPlayer.create(this,R.raw.realbackgroundgamemusic1);
            break;}
            case 1:{ backgroundMusic =MediaPlayer.create(this,R.raw.realbackgroundgamemusic);
            break;
            }
        }
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(100,100);
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundMusic.start();
        return startId;
    }

    @Override
    public void onStart(Intent intent, int startId) {

    }

    @Override
    public void onDestroy() {
        backgroundMusic.stop();
        backgroundMusic.release();
    }



    @Override
    public void onLowMemory() {

    }
}
