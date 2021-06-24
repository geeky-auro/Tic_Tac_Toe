package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    Animation topscreen,bottomscreen,fadeanim,slidein;
    ImageView imageView1,imageView2;
    TextView textView1,textView2,textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        statusbar("#ffa801");
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent=new Intent(SplashActivity.this,BackgroundSoundService.class);
        startService(intent);

        topscreen= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomscreen= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        fadeanim=AnimationUtils.loadAnimation(this,R.anim.left_animation);
        slidein=AnimationUtils.loadAnimation(this,R.anim.slide_in_left);



        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);

        textView1=findViewById(R.id.text11);
        textView2=findViewById(R.id.text22);
        textView=findViewById(R.id.text23);

        imageView1.setAnimation(topscreen);
        imageView2.setAnimation(bottomscreen);
        textView1.setAnimation(topscreen);

        Thread t1=new Thread();
        try {
            t1.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            textView2.setAnimation(fadeanim);
            textView.setAnimation(fadeanim);
        }





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
                    Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }


        };thread.start();



    }
    public void statusbar(String Colour)
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor(Colour));

        }
    }
}