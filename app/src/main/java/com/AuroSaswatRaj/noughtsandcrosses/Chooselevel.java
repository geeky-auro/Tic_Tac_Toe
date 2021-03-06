package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Chooselevel extends AppCompatActivity {

    static Boolean easyc=false,diffc=false;
    static int cutc=0;
    private LinearLayout easylayout,hardlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
        setContentView(R.layout.activity_chooselevel);
        getSupportActionBar().hide();
        statusbarColor1();

        ImageButton easypic = findViewById(R.id.easymode);
        ImageButton hardpic = findViewById(R.id.hardmode);




        //Defining Layouts

        easylayout=findViewById(R.id.EASEBACK);
        hardlayout=findViewById(R.id.DIFFBACK);
        hardlayout.setBackground(null);
        easylayout.setBackground(null);
        easyc=false;
        diffc=false;
        cutc=0;




    }
    public void goback(View view)
    {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationX",-50f,0f);
        animY.setDuration(500);//1sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
       finish();
    }
    public void easycomputermode(View view)
    {
       easyc=true;
       diffc=false;
       cutc=1;
       if (easyc)
       {
           hardlayout.setBackground(null);
            easylayout.setBackgroundColor(Color.parseColor("#CAD5E2"));

       }

    }
    public void hardcomputermode(View view)
    {
        easyc=false;
        diffc=true;
        cutc=1;
        if (diffc)
        {
        easylayout.setBackground(null);
        hardlayout.setBackgroundColor(Color.parseColor("#CAD5E2"));
        }
    }
    public void play(View view)
    {

        if(easyc)
        {
            Intent intent=new Intent(Chooselevel.this,TicTacAI.class);
            startActivity(intent);
            finish();
        }
        if (diffc)
        {
            Intent intent=new Intent(Chooselevel.this,Tic_Tac_AI_Hard.class);
            startActivity(intent);
            finish();
        }
    }
    private void statusbarColor1()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
           window.setStatusBarColor(Color.parseColor("#BF3325"));
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


//
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
            BackgroundSoundService.backgroundMusic.start();
        }
    }



    //    @Override
//    protected void onStop() {
//        super.onStop();
//        if (Settings.musicimagecount%2==0)
//        {
//            BackgroundSoundService.gamebackground.pause();
//        }
//    }
}