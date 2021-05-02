package com.example.noughtsandcrosses;

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

public class ChooselevelRoshambo extends AppCompatActivity {

    private ImageButton playshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselevel_roshambo);
        getSupportActionBar().hide();
        statusbarcolor();
        playshare=findViewById(R.id.playrosamboshare);
        showsettings();
    }

    public void goback(View view) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationX",-50f,0f);
        animY.setDuration(500);//1sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
        quit();
    }

    public void h2h(View view) {
        Intent intent=new Intent(ChooselevelRoshambo.this,RoshamboHuman.class);
        startActivity(intent);
    }

    public void c2h(View view) {
        Intent intent=new Intent(ChooselevelRoshambo.this,Rock_paper_surface.class);
        startActivity(intent);
    }

    private void statusbarcolor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.parseColor("#0080FF"));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0080FF"));
        }
    }

    private void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#FFFFFF"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(ChooselevelRoshambo.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#111E6C"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#000080"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(ChooselevelRoshambo.this,MainActivity.class);
                        startActivity(intent);

                        finish();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#1034A6"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.addButton("Try out Noughts & Crosses!", Color.parseColor("#FFFFFF"), Color.parseColor("#315c4e"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(ChooselevelRoshambo.this,TictacsplashActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showsettings()
    {
        playshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ChooselevelRoshambo.this).create();
                View view = ChooselevelRoshambo.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(ChooselevelRoshambo.this);
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
                        Settings.showSettings(ChooselevelRoshambo.this);
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