package com.example.noughtsandcrosses;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.crowdfire.cfalertdialog.CFAlertDialog;


public class MainActivity extends AppCompatActivity  {

    private ImageButton Tictactoe,Roshambo,Help,Exit,Share,playshare;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#f39c12"));
        }
        Tictactoe=findViewById(R.id.playTickTacToe);
        Roshambo=findViewById(R.id.playRoshambo);

        Exit=findViewById(R.id.playExit);
        Share=findViewById(R.id.playshare);
        //Settings
        playshare=findViewById(R.id.playshare);



        playtactoe();
        playRoshambo();

        showExit();
        shareit();

        //settings
        showsetting();


    }
    private void playtactoe()
    {
    Tictactoe.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,TictacsplashActivity.class);
            startActivity(intent);

        }
    });

    }
    private void playRoshambo()
    {
        Roshambo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Fullscreen_splash.class);
                startActivity(intent);
            }
        });

    }

    private void showExit()
    {
    Exit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            quit();
        }
    });
    }
    private void shareit()
    {
        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    private void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#2f3640"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#2f3640"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(MainActivity.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#fbc531"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#e1b12c"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BackgroundSoundService.gamebackground.stop();
                        BackgroundSoundService.gamebackground.release();
                        MainActivity.this.finishAffinity();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#353b48"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void onBackPressed()
    {
        quit();
    }

    private void showsetting()
    {
        playshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                View view = getActivity().getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(MainActivity.this);
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
                        BackgroundSoundService.gamebackground.pause();
                        if (Settings.musicimagecount%2==0)
                        {
                            speaker.setImageDrawable(getResources().getDrawable(R.drawable.ic_speaker));
                            speaker.setBackgroundColor(Color.parseColor("#f5f6fa"));
                            BackgroundSoundService.gamebackground.start();
                        }

                    }
                });
                info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Settings.showSettings(MainActivity.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
        });

    }


    private MainActivity getActivity() {
        return MainActivity.this;
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
