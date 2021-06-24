package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class Rock_paper_surface extends AppCompatActivity {

    private Button rock,paper,scissor;
    private TextView playerstatus,player1score,player2score,playername;
    private Button rule;

    private static MediaPlayer applause;

    private static int savescorep1,savescorep2,savedraw;

    private static int musiccount;


    private int pl1score,pl2score;

    private static String playerName1;


    private Button button[]=new Button[3];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
        setContentView(R.layout.activity_rock_paper_surface);
        customizeActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        statusbarColor();
        customAlertDialog();
        //Play Music






        rock=findViewById(R.id.rock);
        paper=findViewById(R.id.paper);
        scissor=findViewById(R.id.scissor);

        player1score=findViewById(R.id.player1score);
        player2score=findViewById(R.id.player2score);
        playername=findViewById(R.id.player1);

        pl1score=0;
        pl2score=0;

        playerstatus=findViewById(R.id.playerscoretext);

        button[0]=findViewById(R.id.compscissor);
        button[1]=findViewById(R.id.comprock);
        button[2]=findViewById(R.id.comppaper);

        


        rule=findViewById(R.id.rules);





        rock();
        paper();
        scissor();
        //View rules via Button

    }
//    0->scissor
//    1->rock
//    2->paper
    public void rock()
    {
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random genRandom=new Random();
                int choice=genRandom.nextInt(3);
                int win=genRandom.nextInt(3);
                int loose=genRandom.nextInt(2);
                int draw=genRandom.nextInt(2);
                switch (choice)
                {
                    case 0:
                    {
                                button[0].setPressed(true);
                                button[0].setBackgroundColor(Color.parseColor("#E21717"));
                                if (button[0].isEnabled())
                                {

                                    if (musiccount%2==0)
                                    {
                                        musiclose();
                                    }
                                    String Message="Bravo!Good one mate! You've got it.";
                                    popupAlert("Felicitations",Message);
                                    pl1score++;
                                    savescorep1++;
                                    updateplayerscore();
                                    try {
                                        Thread.sleep(200);
                                        playerstatus.setText("Bravo!Good one mate! You've got it.");

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;
                    }
                    case 1:
                    {
                                button[1].setPressed(true);
                                button[1].setBackgroundColor(Color.parseColor("#E21717"));
                                if (button[1].isEnabled())
                                {
                                    if (musiccount%2==0)
                                    {
                                        musicdraw();
                                    }
                                    String Message="Tie!Good Attempt..";
                                    savedraw++;
                                    popupAlert("La Corbata",Message);
                                    try {
                                        Thread.sleep(200);
                                        playerstatus.setText("Tie!Good Attempt..");

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                    }
                    case 2:
                    {

                        button[2].setPressed(true);
                        button[2].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[2].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musicwin();
                            }
                            String Message="REVENGE IS BENEATH ME\nBUT ACCIDENT HAPPENS!!";
                            popupAlert("Catastrophe",Message);
                            pl2score++;
                            savescorep2++;
                            updateplayerscore();
                            try {
                                Thread.sleep(200);
                                playerstatus.setText("HAHA!!Defeated");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                    }
                    }
                }
            });
        }
        public void paper()
        {
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random genRandom=new Random();
                int choice=genRandom.nextInt(3);

                switch (choice)
                {
                    case 0:
                    {
                        button[0].setPressed(true);
                        button[0].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[0].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musicwin();
                            }
                            String Message="REVENGE IS BENEATH ME\nBUT ACCIDENT HAPPENS!!";
                            popupAlert("Catastrophe",Message);
                            pl2score++;
                            savescorep2++;
                            updateplayerscore();

                            try {
                                Thread.sleep(200);
                                playerstatus.setText("HAHA!!Defeated");

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                    }

                    case 1:
                    {
                        button[1].setPressed(true);
                        button[1].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[1].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musiclose();
                            }
                            String Message="Bravo!Good one mate! You've got it.";
                            popupAlert("Felicitations",Message);
                            pl1score++;
                            savescorep1++;
                            updateplayerscore();
                            try {
                                Thread.sleep(200);
                                playerstatus.setText("Bravo!Good one mate! You've got it.");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }

                    case 2:
                    {


                        button[2].setPressed(true);
                        button[2].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[2].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musicdraw();
                            }
                            String Message="Tie!Good Attempt..";
                            savedraw++;
                            popupAlert("La Corbata",Message);
                            try {
                                Thread.sleep(200);
                                playerstatus.setText("Tie!Good Attempt..");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;

                    }
                }


            }
        });

    }

    public void scissor()
    {
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random genRandom=new Random();
                int choice=genRandom.nextInt(3);

                switch (choice)
                {
                    case 0:
                    {
                        button[0].setPressed(true);
                        button[0].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[0].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musicdraw();
                            }
                            String Message="Tie!Good Attempt..";
                            savedraw++;
                            popupAlert("La Corbata",Message);

                            try {
                                Thread.sleep(200);
                                playerstatus.setText("Tie!Good Attempt..");

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                    }

                    case 1:
                    {
                        button[1].setPressed(true);
                        button[1].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[1].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musicwin();
                            }
                            String Message="REVENGE IS BENEATH ME\nBUT ACCIDENT HAPPENS!!";
                            popupAlert("Catastrophe",Message);
                            pl2score++;
                            savescorep2++;
                            updateplayerscore();
                            try {
                                Thread.sleep(200);
                                playerstatus.setText("HAHA!!Defeated");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }

                    case 2:
                    {
                        button[2].setPressed(true);
                        button[2].setBackgroundColor(Color.parseColor("#E21717"));
                        if (button[2].isEnabled())
                        {
                            if (musiccount%2==0)
                            {
                                musiclose();
                            }
                            String Message="Bravo!Good one mate! You've got it.";
                            popupAlert("Felicitations",Message);
                            pl1score++;
                            savescorep1++;
                            updateplayerscore();

                            try {
                                Thread.sleep(200);
                                playerstatus.setText("Bravo!Good one mate! You've got it.");


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                }

            }
        });

    }
    public void customizeActionBar()
    {
        getSupportActionBar().setTitle("Roshambo");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backie));
    }
    public void popupAlert(String Titile,String Messsage)
    {
        // Create Alert using Builder
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(Rock_paper_surface.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Titile)
                .setMessage(Messsage)
                .addButton("Play Again!", -1, -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, (dialog, which) -> {
                    button[0].setBackgroundColor(Color.parseColor("#ccae62"));
                    button[1].setBackgroundColor(Color.parseColor("#ccae62"));
                    button[2].setBackgroundColor(Color.parseColor("#ccae62"));

                    dialog.dismiss();
                });
        builder.show();

    }
    public void statusbarColor()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#485460"));
        }
    }


    public void musicwin()
        {
            Random genRandom=new Random();
            int win=genRandom.nextInt(3);
            MediaPlayer win1,win2,win3;
            win1=MediaPlayer.create(this,R.raw.win1);
            win2=MediaPlayer.create(this,R.raw.win2);
            win3= MediaPlayer.create(this,R.raw.win3);
            switch(win)
            {
                case 0:
                {
                    win1.start();
                    break;
                }
                case 1:
                {
                    win2.start();
                    break;
                }
                case 2:
                {
                    win3.start();
                    break;
                }

            }


        }
        public void musiclose()
        {
            Random genRandom=new Random();
            int loose=genRandom.nextInt(2);
            MediaPlayer lose1=MediaPlayer.create(this,R.raw.los1);
            MediaPlayer lose2=MediaPlayer.create(this,R.raw.los2);
            switch(loose)
            {
                case 0:
                {
                    lose1.start();
                    break;
                }
                case 1:
                {
                    lose2.start();
                    break;
                }
            }
        }

        public void musicdraw()
        {
            Random genRandom=new Random();
            int draw=genRandom.nextInt(2);
            MediaPlayer draw0=MediaPlayer.create(this,R.raw.draw0);
            MediaPlayer draw1=MediaPlayer.create(this,R.raw.draw1);
            switch(draw)
            {
                case 0:
                {
                    draw0.start();
                    break;
                }
                case 1:
                {
                    draw1.start();
                    break;
                }
            }
        }

    public void rules(View view) {
        MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.rules);
        mediaPlayer.start();

        String rules="The game is played where players deliver hand signals that will represent the elements of the game; rock, paper and scissors. The outcome of the game is determined by 3 simple rules:\n" +
                "Rock wins against scissors.\n" +
                "Scissors win against paper.\n" +
                "Paper wins against rock.";
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(Rock_paper_surface.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle("Rules Of Play").setCancelable(false)
                .setMessage(rules).setDialogBackgroundColor(Color.parseColor("#fffa65"))
                .addButton("Got it!!", Color.parseColor("#FFFFFF"), Color.parseColor("#429ef4"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        dialog.dismiss();
                    }
                });
        builder.show();


    }

    public void updateplayerscore()
    {
        player1score.setText(Integer.toString(pl1score));
        player2score.setText(Integer.toString(pl2score));
    }


    public void onBackPressed()
    {
    quit();
    }
    public void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(Rock_paper_surface.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#222f3e"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#535c68"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showscorecard();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#6ab04c"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void showscorecard()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Rock_paper_surface.this).create();
        View view = Rock_paper_surface.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
        alertDialog.setCancelable(false);
        applause=MediaPlayer.create(this,R.raw.applause);
        applause.start();
        LinearLayout background=view.findViewById(R.id.scorecardbackie);
        TextView tittletext=view.findViewById(R.id.tittletext);
        TextView player1name=view.findViewById(R.id.playernamebtn);
        TextView player1score=view.findViewById(R.id.player1scorebtn);
        TextView player2score=view.findViewById(R.id.player2scorebtn);
        TextView player2name=view.findViewById(R.id.playername1btn);
        TextView drawstatus=view.findViewById(R.id.drawstatus);

        background.setBackgroundColor(Color.parseColor("#151515"));

        tittletext.setTextColor(Color.parseColor("#f7ea00"));

        //playernames
        player1name.setText(""+playerName1);
        player2name.setText("Bot");
        //playerscore
        player1score.setText(""+savescorep1);
        player2score.setText(""+savescorep2);

        //Draw Status
        String draw="Draw:"+savedraw;
        drawstatus.setText(draw);

        TextView playerstatus=view.findViewById(R.id.playerwonstatus);
        Button exitnow=view.findViewById(R.id.exitnow);

        String wintext="Congratulations Player-1.Well done,You’re right on track!";
        StringBuffer win=new StringBuffer(wintext);

        if (savescorep1>savescorep2)
        {
            win.replace(16,24,playerName1);
            playerstatus.setText(win);
        }
        else if (savescorep1==savescorep2)
        {
            playerstatus.setText("Ginormous effort–The game ended with a draw!");
        }
        else
        {
            win.replace(16,24,"Bot");
            playerstatus.setText(win);
        }

        exitnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vibrator.vibrate(300);
                }
                applause.pause();
                Intent intent=new Intent(Rock_paper_surface.this,ChooselevelRoshambo.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.musictune:
            {
                musiccount++;
                if (musiccount%2!=0)
                {
                    item.setIcon(getResources().getDrawable(R.drawable.roshamboaisoundoff));
                }
                else
                {
                    item.setIcon(getResources().getDrawable(R.drawable.roshamboaisoundon));
                }

            }
            return true;

            case R.id.settingsopti:
            {
                AlertDialog alertDialog = new AlertDialog.Builder(Rock_paper_surface.this).create();
                View view = Rock_paper_surface.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(Rock_paper_surface.this);
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
                        Settings.showSettings(Rock_paper_surface.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
            return true;

            case android.R.id.home: {

                quit();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void customAlertDialog()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(Rock_paper_surface.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_alert, null);
        alertDialog.setCancelable(false);
        final EditText etComments = view.findViewById(R.id.p1edittextii);
         Button button=view.findViewById(R.id.playnow);
         ImageButton crossit1=view.findViewById(R.id.crossit1);

        crossit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName1="Player-1";
                playername.setText(playerName1);
                alertDialog.dismiss();
            }
        });

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name=etComments.getText().toString();
                 playerName1=name;
                 if (name.isEmpty())
                 {
                     alertDialog.dismiss();
                     customAlertDialog();
                     Toasty.error(Rock_paper_surface.this,"Please Enter a Meaningful Name!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (name.length()>=8) {
                     String exname = name.substring(0,7);
                     playername.setText(" " + exname);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==7)
                 {
                     playername.setText(" " + name);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==6)
                 {
                     playername.setText(" " + name);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==5)
                 {
                     playername.setText("  " + name);
                     alertDialog.dismiss();

                 }
                 else if (name.length()==4) {
                     playername.setText("   " + name);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==3)
                 {
                     playername.setText("    " + name);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==2)
                 {
                     playername.setText("     " + name);
                     alertDialog.dismiss();
                 }
                 else if (name.length()==1)
                 {
                     playername.setText("      " + name);
                     alertDialog.dismiss();
                 }
                 alertDialog.dismiss();

             }
         });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private Activity getActivity() {
        return Rock_paper_surface.this;
    }


    public void resetitnow(View view) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vibrator.vibrate(300);
        }
        AlertDialog alertDialog = new AlertDialog.Builder(Rock_paper_surface.this).create();
        View v = Rock_paper_surface.this.getLayoutInflater().inflate(R.layout.resetlayoutroshambo, null);
        alertDialog.setCancelable(false);
        Button cancelbutton=v.findViewById(R.id.cancel_roshambo);
        Button acceptbutton=v.findViewById(R.id.agree_roshambo);

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        acceptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                donereset();

            }
        });

        alertDialog.setView(v);
        alertDialog.show();
    }

    private void donereset()
    {
        player1score.setText("0");
        player2score.setText("0");
        AlertDialog alertDialog = new AlertDialog.Builder(Rock_paper_surface.this).create();
        View view = Rock_paper_surface.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
        alertDialog.setCancelable(false);
        MediaPlayer applause1=MediaPlayer.create(this,R.raw.applause);
        applause1.start();
        TextView player1name=view.findViewById(R.id.playernamebtn);
        TextView player1score=view.findViewById(R.id.player1scorebtn);
        TextView player2score=view.findViewById(R.id.player2scorebtn);
        TextView player2name=view.findViewById(R.id.playername1btn);
        TextView drawstatus=view.findViewById(R.id.drawstatus);

        //playernames
        player1name.setText(""+playerName1);
        player2name.setText("Bot");
        //playerscore
        player1score.setText(""+savescorep1);
        player2score.setText(""+savescorep2);

        //Draw Status
        String draw="Draw:"+savedraw;
        drawstatus.setText(draw);

        TextView playerstatus=view.findViewById(R.id.playerwonstatus);
        Button exitnow=view.findViewById(R.id.exitnow);

        String wintext="Congratulations Player-1.Well done,You’re right on track!";
        StringBuffer win=new StringBuffer(wintext);

        if (savescorep1>savescorep2)
        {
            win.replace(16,24,playerName1);
            playerstatus.setText(win);
        }
        else if (savescorep1==savescorep2)
        {
            playerstatus.setText("Ginormous effort–The game ended with a draw!");
        }
        else
        {
            win.replace(16,24,"Bot");
            playerstatus.setText(win);
        }

        exitnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    vibrator.vibrate(300);
                }
                applause1.pause();
                savedraw=0;
                savescorep1=0;
                savescorep2=0;
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.roshamboai, menu);
        return super.onCreateOptionsMenu(menu);
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
            BackgroundSoundService.backgroundMusic.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.pause();
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