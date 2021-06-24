package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageButton;

import com.crowdfire.cfalertdialog.CFAlertDialog;

public class tictacoptions extends AppCompatActivity {

    private ImageButton playsharetac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
        setContentView(R.layout.activity_tictacoptions);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.parseColor("#892cdc"));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#892cdc"));
        }
        playsharetac=findViewById(R.id.playtacshare);
        showsettings();
    }



    public void h2h(View view) {
        Intent intent=new Intent(tictacoptions.this,game_UI_1.class);
        startActivity(intent);
    }

    public void c2h(View view) {
        Intent intent=new Intent(tictacoptions.this,Chooselevel.class);
        startActivity(intent);
    }

    public void goback(View view) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationX",-50f,0f);
        animY.setDuration(500);//1sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
        quit();
    }
    private void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(tictacoptions.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#52057b"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#542e71"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(tictacoptions.this,MainActivity.class);
                        startActivity(intent);

                        finish();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#bc6ff1"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.addButton("Try out Roshambo!", Color.parseColor("#FFFFFF"), Color.parseColor("#fb3640"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(tictacoptions.this,Fullscreen_splash.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showsettings()
    {
        playsharetac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(tictacoptions.this).create();
                View view = tictacoptions.this.getLayoutInflater().inflate(R.layout.settings, null);
                alertDialog.setCancelable(false);
                ImageButton back=view.findViewById(R.id.goback);
                ImageButton person=view.findViewById(R.id.person);
                ImageButton speaker=view.findViewById(R.id.speaker);
                ImageButton info=view.findViewById(R.id.info);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                person.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Developer_info.show_about_dev(tictacoptions.this);
                    }
                });

                if (Settings.musicimagecount%2==0)
                {
                    speaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker));
                }
                else
                {
                    speaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_mute));
                }
                speaker.setBackgroundColor(Color.parseColor("#f5f6fa"));

                speaker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        speaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_mute));
                        speaker.setBackgroundColor(Color.parseColor("#f5f6fa"));
                        Settings.musicimagecount++;
                        BackgroundSoundService.backgroundMusic.pause();
                        if (Settings.musicimagecount%2==0)
                        {
                            speaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker));
                            speaker.setBackgroundColor(Color.parseColor("#f5f6fa"));
                            BackgroundSoundService.backgroundMusic.start();
                        }
                    }
                });
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Settings.showSettings(tictacoptions.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        quit();
    }


//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (Settings.musicimagecount%2==0)
//        {
//            BackgroundSoundService.gamebackground.pause();
//        }
//    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (Settings.musicimagecount%2==0)
//        {
//            BackgroundSoundService.gamebackground.start();
//        }
//    }
}