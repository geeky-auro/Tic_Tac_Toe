package com.example.noughtsandcrosses;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class TicTacAI extends AppCompatActivity {

    Button button[]=new Button[9];
    Button computerbutton;
    private static Boolean checkmusicstate=true;

    private static MediaPlayer applause;
    TextView p1score,computerscore,playername,ainame;
    int rountcount,p1scorecount,p2scorecount;

    private static int savescorep1,savescorep2,savedraw;

    private static MediaPlayer welcome;



    private static int musiccount;

    private static MediaPlayer humanwin,musicwin,musiclose;

    //For button count...
    static int buttoncount=0,innercount=0;

    private MediaPlayer computermusic,playermusic;


    private static String playerName1,computername;

    int gamestatepointer;

    boolean activestate;

    int gamestate[]={2,2,2,2,2,2,2,2,2};

    int winninglogic[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tic_tac_a_i);
        generatecomputername();
        statusbarColor1();
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.aititlebar));
        playerintro();
        welcome.start();


        playermusic=MediaPlayer.create(this,R.raw.clickbtn);
        computermusic=MediaPlayer.create(this,R.raw.player1);

        ainame=findViewById(R.id.player1023);
        ainame.setText(computername);

        humanwin=MediaPlayer.create(TicTacAI.this,R.raw.humanwin);

        button[0]=findViewById(R.id.btn_0);
        button[1]=findViewById(R.id.btn_1);
        button[2]=findViewById(R.id.btn_2);
        button[3]=findViewById(R.id.btn_3);
        button[4]=findViewById(R.id.btn_4);
        button[5]=findViewById(R.id.btn_5);
        button[6]=findViewById(R.id.btn_6);
        button[7]=findViewById(R.id.btn_7);
        button[8]=findViewById(R.id.btn_8);

        playername=findViewById(R.id.player1011);

        computerbutton=findViewById(R.id.computerbutoon);

        p1score=findViewById(R.id.playeronescore);
        computerscore=findViewById(R.id.playertwoscore);

        rountcount=0;
        p1scorecount=0;
        p2scorecount=0;
        activestate=true;


        computerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttoncount++;
                if (buttoncount==1)
                {
                 computerbutton.setText("Make Your Move");
                }
                else if (buttoncount==2)
                {
                    computerbutton.setText("It's Your Turn!!");
                }
                else if(buttoncount>=3)
                {
                    innercount++;
                    if(innercount==1) {
                        computerbutton.setText("Clack one more time for "+computername+"'s turn!");
                        innercount=0;
                    }

                }
                playgame();
            }
        });



    }

    public void letsplay(View view) {
        if(!((Button)view).getText().toString().equals(""))
        {
            return;
        }

            showcustomwarning();

        String ButtonID=view.getResources().getResourceEntryName(view.getId());
        gamestatepointer=Integer.parseInt(ButtonID.substring(ButtonID.length()-1,ButtonID.length()));


    }

    public void playgame()
    {

        if(activestate)
        {
            playerbutton();

        }
        else
        {

            AI();

        }
        rountcount++;

        if(checkwin())
        {
            if(activestate)//player-1
            {
                p1scorecount++;
                savescorep1++;
                showwin(playerName1);
                musiclose.start();
                updateplayerscore();
                playagain();
            }
            else
            {
                p2scorecount++;
                savescorep2++;
                showwin(computername);
                musicwin.start();
                updateplayerscore();
                playagain();
            }
        }
        else if (rountcount==9)
        {
            draw();
            savedraw++;
            humanwin.start();
            playagain();
        }
        else
        {
            activestate=!activestate;
        }

    }
    public void AI()
    {
        Random random=new Random();
        while (true)
        {

            int genposition=random.nextInt(8);
            if(gamestate[genposition]!=0 && gamestate[genposition]==2)
            {
                button[genposition].setText("O");
                button[genposition].setBackgroundColor(Color.parseColor("#303841"));
                gamestate[genposition]=1;
                setenable();
                updatecomputermusic();
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#ecf0f1"));
                computerbutton.setEnabled(false);
                computerbutton.setBackgroundColor(Color.parseColor("#130f40"));
                break;
            }

        }
    }


    public void setdisable()
    {
        button[0].setEnabled(false);
        button[1].setEnabled(false);
        button[2].setEnabled(false);
        button[3].setEnabled(false);
        button[4].setEnabled(false);
        button[5].setEnabled(false);
        button[6].setEnabled(false);
        button[7].setEnabled(false);
        button[8].setEnabled(false);
    }

    public void setenable()
    {
        button[0].setEnabled(true);
        button[1].setEnabled(true);
        button[2].setEnabled(true);
        button[3].setEnabled(true);
        button[4].setEnabled(true);
        button[5].setEnabled(true);
        button[6].setEnabled(true);
        button[7].setEnabled(true);
        button[8].setEnabled(true);
    }


    public void playerbutton()
    {

        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[0].setText("X");
                button[0].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=0;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[1].setText("X");
                button[1].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=1;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[2].setText("X");
                button[2].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=2;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[3].setText("X");
                button[3].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=3;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[4].setText("X");
                button[4].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=4;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[5].setText("X");
                button[5].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=5;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[6].setText("X");
                button[6].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=6;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[7].setText("X");
                button[7].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=7;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
        button[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button[8].setText("X");
                button[8].setBackgroundColor(Color.parseColor("#00adb5"));
                setdisable();
                gamestatepointer=8;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Clack it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#0984e3"));
            }
        });
    }

    public boolean checkwin()
    {
        boolean winner=false;
        for(int winposionn[]:winninglogic)
        {
            if(gamestate[winposionn[0]]==gamestate[winposionn[1]] &&
                    gamestate[winposionn[1]]==gamestate[winposionn[2]] &&
                    gamestate[winposionn[0]]!=2)
            {
                winner=true;
            }

        }
        return winner;
    }
    private void playagain()
    {
        rountcount=0;
        activestate=true;
        for (int i=0;i<button.length;i++)
        {
            gamestate[i]=2;
            button[i].setText("");
            button[i].setBackgroundColor(Color.parseColor("#487eb0"));
            button[i].setEnabled(true);
        }
        computerbutton.setText("It's Your Turn!!");

    }
    private void updateplayerscore()
    {
        p1score.setText(Integer.toString(p1scorecount));
        computerscore.setText(Integer.toString(p2scorecount));
    }
    private void statusbarColor1()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#74b9ff"));
        }
    }
    private void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#f5f6fa"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#dcdde1"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(TicTacAI.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#273c75"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#192a56"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        showscorecard();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#44bd32"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ai_menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void updatehumanmusic()
    {
        if (musiccount%2!=0)
        {
            playermusic.pause();
        }
        else
        {
            playermusic.start();
        }
    }
    private void updatecomputermusic()
    {
        if (musiccount%2!=0)
        {
            computermusic.pause();
        }
        else
        {
            computermusic.start();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.usermanual1:
            {
                instruction_mannual.callinstructionmannual(TicTacAI.this);
            }
            return true;
            case R.id.musictune:
            {

                musiccount++;
                if (musiccount%2!=0)
                {
                    item.setIcon(getResources().getDrawable(R.drawable.musicoffie));
                }
                else
                {
                    item.setIcon(getResources().getDrawable(R.drawable.musiconnie));
                }

            }
            return true;

            case R.id.settingsopt:
            {
                AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
                View view = TicTacAI.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(TicTacAI.this);
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
                        Settings.showSettings(TicTacAI.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
            return true;

            case android.R.id.home:
                quit();
                return true;

                default:
                return super.onOptionsItemSelected(item);
        }



    }

    private void playerintro()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_tictac_ai, null);
        alertDialog.setCancelable(false);
        final EditText etComments = view.findViewById(R.id.etCommentsi);
        Button button=view.findViewById(R.id.playnow);

        ImageButton crossit5=view.findViewById(R.id.crossit5);
        crossit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName1="Player-1";
                playername.setText(playerName1);
                alertDialog.cancel();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etComments.getText().toString();
                playerName1 = name;
                if (name.isEmpty())
                {
                    alertDialog.dismiss();
                    playerintro();
                    Toasty.error(TicTacAI.this,"Please Enter a Meaningful Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length()>=8) {
                    String exname = name.substring(0,7);

                    playername.setText(" " + exname);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==7)
                {
                    playername.setText(" " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==6)
                {
                    playername.setText(" " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==5)
                {
                    playername.setText("  " + name);
                    showcustomwarning();
                    alertDialog.dismiss();


                }
                else if (name.length()==4) {
                    playername.setText("   " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==3)
                {
                    playername.setText("    " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==2)
                {
                    playername.setText("     " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else if (name.length()==1)
                {
                    playername.setText("      " + name);
                    showcustomwarning();
                    alertDialog.dismiss();

                }
                else
                {
                    alertDialog.dismiss();
                    showcustomwarning();
                }
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private TicTacAI getActivity() {
        return TicTacAI.this;
    }

    private void showwin(String playerName12)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.winnerdialogtictac, null);
        alertDialog.setCancelable(true);
        StringBuffer winnername=new StringBuffer("Congratulations Robin! You deserve this success.");
        winnername.replace(16,21,playerName12);
        TextView playerwintext=view.findViewById(R.id.playerwintext);
        playerwintext.setText(""+winnername.toString());

        Button button=view.findViewById(R.id.playagain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();


    }

    private void draw()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.drawbackietac, null);
        alertDialog.setCancelable(true);

        Button button=view.findViewById(R.id.playagain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private void showcustomwarning()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.customwarning, null);
        alertDialog.setCancelable(true);

        Button button=view.findViewById(R.id.playagainiii);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        quit();
    }

    private void generatecomputername()
    {
        Random random=new Random();
        int n=random.nextInt(14);
        int genfemalewin=random.nextInt(3);

        switch(n)
        {
            case 0:
            {
                computername="Frank";
                malesound();
                break;
            }
            case 1:
            {
                computername="Fawn";
                malesound();
                break;
            }
            case 2:
            {
                computername="Talia";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 3:
            {
                computername="Floyd";
                malesound();
                break;
            }
            case 4:
            {
                computername="Stanley";
                malesound();
                break;
            }
            case 5:
            {
                computername="Caldwell";
                malesound();
                break;
            }
            case 6:
            {
                computername="Maria";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 7:
            {
                computername="Beata";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 8:
            {
                computername="Tracy";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 9:
            {
                computername="Philip";
                malesound();
                break;
            }
            case 10:
            {
                computername="Alec";
                malesound();
                break;
            }
            case 11:
            {
                computername="Roy";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 12:
            {
                computername="Taylor";
                welcome=MediaPlayer.create(TicTacAI.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 13:
            {
                computername="Malcom";
                malesound();
                break;
            }
        }
    }

    private void femalesound(int genfemalewin)
    {
        switch(genfemalewin)
        {
            case 0:
            {
                musicwin=MediaPlayer.create(TicTacAI.this,R.raw.femalewin1);
                break;
            }
            case 1:
            {
                musicwin=MediaPlayer.create(TicTacAI.this,R.raw.femalewin2);
                break;
            }
            case 2:
            {
                musicwin=MediaPlayer.create(TicTacAI.this,R.raw.femalewin3);
                break;
            }
        }
        musiclose=MediaPlayer.create(TicTacAI.this,R.raw.femalelostcry);
    }

    private void malesound()
    {
        welcome=MediaPlayer.create(TicTacAI.this,R.raw.malewelcome);
        musicwin=MediaPlayer.create(TicTacAI.this,R.raw.malewinvoice);
        musiclose=MediaPlayer.create(TicTacAI.this,R.raw.malelostcry);
    }

    private void showscorecard()
    {
        checkmusicstate=true;
        AlertDialog alertDialog = new AlertDialog.Builder(TicTacAI.this).create();
        View view = TicTacAI.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
        alertDialog.setCancelable(false);
        applause=MediaPlayer.create(this,R.raw.applause);
        applause.start();
        TextView player1name=view.findViewById(R.id.playernamebtn);
        TextView player1score=view.findViewById(R.id.player1scorebtn);
        TextView player2score=view.findViewById(R.id.player2scorebtn);
        TextView player2name=view.findViewById(R.id.playername1btn);
        TextView drawstatus=view.findViewById(R.id.drawstatus);

        //playernames
        player1name.setText(""+playerName1);
        player2name.setText(""+computername);
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
            win.replace(16,24,computername);
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
                Intent intent=new Intent(TicTacAI.this,tictacoptions.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.start();
        }

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
    protected void onDestroy() {
        super.onDestroy();
        buttoncount=0;
        innercount=0;
    }
}
//First of all pop up the start switch
//Control the brain of the AI

//Best of Luck!