package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Fullscreen_splash extends AppCompatActivity {

    private ImageView rocksplash,scissorsplash,papersplash;
    private TextView roshambotext;
    private Animation topscreen,bottomscreen,fadeanim,slidein,slideinright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.start();
        }
        setContentView(R.layout.activity_fullscreen_splash);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.parseColor("#2f5d62"));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#2f5d62"));
        }

        rocksplash=findViewById(R.id.rocksplash);
        papersplash=findViewById(R.id.papersplash);
        scissorsplash=findViewById(R.id.scissorsplash);
        roshambotext=findViewById(R.id.roshambotext);

        topscreen= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomscreen= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fadeanim=AnimationUtils.loadAnimation(this,R.anim.left_animation);
        slidein=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        slideinright=AnimationUtils.loadAnimation(this,R.anim.slide_in_right);

        scissorsplash.setAnimation(slidein);
        papersplash.setAnimation(slidein);

        rocksplash.setAnimation(slideinright);

        roshambotext.setAnimation(fadeanim);

        Thread thread=new Thread(){

            public void run()
            {
                try {
                    sleep(3000);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent=new Intent(Fullscreen_splash.this,ChooselevelRoshambo.class);
                    startActivity(intent);
                    finish();
                }

            }


        };thread.start();




    }

    @Override
    public void onBackPressed() {
        finish();
    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (Settings.musicimagecount%2==0)
//        {
//            BackgroundSoundService.gamebackground.start();
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.start();
        }
    }



}