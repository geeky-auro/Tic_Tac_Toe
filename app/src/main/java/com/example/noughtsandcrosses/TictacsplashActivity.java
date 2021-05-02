package com.example.noughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class TictacsplashActivity extends AppCompatActivity {


    private Animation top,bottomslide,slideinleft;
    private TextView legacyTitle,quote;
    private LottieAnimationView animationView;
    private Button Skip;

    private static Boolean checkclick=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictacsplash);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#3d84a8"));
        }
        Skip=findViewById(R.id.skip);
        legacyTitle=findViewById(R.id.legacytitle);
        quote=findViewById(R.id.quote);
        animationView=findViewById(R.id.animation_view);
        slideinleft=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);

        top=AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomslide=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        legacyTitle.setAnimation(top);
        quote.setAnimation(bottomslide);
        Skip.setAnimation(slideinleft);
        animationView.playAnimation();

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isPressed())
                {
                    checkclick=true;
                }
                checkclick=true;
                Intent intent=new Intent(TictacsplashActivity.this,tictacoptions.class);
                startActivity(intent);
                finish();
            }
        });



            Thread thread=new Thread() {

                public void run()
                {
                    try {
                        sleep(5800);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {

                        if (checkclick==false)
                        {
                            Intent intent=new Intent(TictacsplashActivity.this,tictacoptions.class);
                            startActivity(intent);
                            finish();
                        }


                    }
                }

            };
            thread.start();






    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.start();
        }
    }
}