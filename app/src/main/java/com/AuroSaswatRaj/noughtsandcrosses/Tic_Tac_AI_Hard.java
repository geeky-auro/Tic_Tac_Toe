package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.annotation.NonNull;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class Tic_Tac_AI_Hard extends AppCompatActivity {

    private Button buttons[]=new Button[9];
    int gamestatepointer;
    static int startloop;

    private static MediaPlayer applause;

    private static Boolean checkmusicstate=false;

    private static MediaPlayer humanwin,musicwin,musiclose;

    private static int savescorep1,savescorep2,savedraw;

    private Random random;
    private static String playerName1,computername;

    private static MediaPlayer welcome;

    private static int countactive=0;

    private static int musiccount;

    private MediaPlayer computermusic,playermusic;

    boolean activestate;

    private int gamestate[]={2,2,2,2,2,2,2,2,2};

    int winninglogic[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    static int buttoncount=0,innercount=0;
    Button computerbutton;
    TextView p1score,computerscore,playername,computernamee;
    int rountcount,p1scorecount,p2scorecount;

    private static Boolean get1stOccouranc=false,get2ndOccourance=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.gamebackground.start();
        }
        setContentView(R.layout.activity_tic__tac__a_i__hard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        genrandomname();
        statusbarcolor();
        customizeActionBari();
        customAlertDialogi();
        welcome.start();
        checkmusicstate=false;
        humanwin=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.humanwin);

        playermusic=MediaPlayer.create(this,R.raw.clickbtn);
        computermusic=MediaPlayer.create(this,R.raw.player1);

        buttons[0]=findViewById(R.id.btn_0);
        buttons[1]=findViewById(R.id.btn_1);
        buttons[2]=findViewById(R.id.btn_2);
        buttons[3]=findViewById(R.id.btn_3);
        buttons[4]=findViewById(R.id.btn_4);
        buttons[5]=findViewById(R.id.btn_5);
        buttons[6]=findViewById(R.id.btn_6);
        buttons[7]=findViewById(R.id.btn_7);
        buttons[8]=findViewById(R.id.btn_8);

        p1score=findViewById(R.id.playeronescore);
        playername=findViewById(R.id.player1011);
        computerscore=findViewById(R.id.computerscore);
        computernamee=findViewById(R.id.player102);
        computernamee.setText(computername);

        computerbutton=findViewById(R.id.computerbutoon);


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
                    computerbutton.setText("Click any of the Box");
                }
                else if (buttoncount==2)
                {
                    computerbutton.setText("It's Your Turn!!");
                }
                else if(buttoncount>=3)
                {
                    innercount++;
                    if(innercount==1) {
                        computerbutton.setText("Click once more for "+computername+"'s turn!");
                        innercount=0;
                    }

                }
                playgame();
            }
        });
    }

    private void playgame() {
        if(activestate)
        {
            playerbutton();
        }
        else
        {

            AI();
            setEnable();


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
    private void AI() {
        boolean nonexecuted=true;

        if(check1stOccourance() && get1stOccouranc==false)
        {
            get1stOccouranc=true;
            return;
        }else
        {
            get1stOccouranc=false;
        }
        //TODO:Hold the value of 1st and 2nd Instance
        if ((check2ndOccourance() && get2ndOccourance) && get1stOccouranc==true)
        {
            get2ndOccourance=true;
            return;
        }
        else
        {
            get2ndOccourance=false;
        }

        //Apply for Logic
        if ((get1stOccouranc && get2ndOccourance)==true)
        {
            if(buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("") && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }
            else if (buttons[0].getText().equals("X") && buttons[2].getText().equals("X") && buttons[4].getText().equals("O") &&
                    buttons[1].getText().equals("O") && buttons[3].getText().equals("X") && gamestate[5]==2)
            {
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }else if(buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("")
                    && gamestate[5]==2){
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[1].getText().equals("O") && buttons[2].getText().equals("O") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;

            } else if(buttons[4].getText().equals("O") && buttons[5].getText().equals("O") && buttons[3].getText().equals("")
                    && gamestate[3]==2){
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                updatecomputermusic();
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[7].getText().equals("O") && buttons[8].getText().equals("O") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("O") && buttons[2].getText().equals("O") && buttons[1].getText().equals("")
                    && gamestate[1]==2){
                buttons[1].setText("O");
                buttons[1].setEnabled(false);
                buttons[1].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=1;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[3].getText().equals("O") && buttons[5].getText().equals("O") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("O") && buttons[8].getText().equals("O") && buttons[7].getText().equals("")
                    && gamestate[7]==2){
                buttons[7].setText("O");
                buttons[7].setEnabled(false);
                buttons[7].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=7;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;

            }

            else if(buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("")
                    && gamestate[3]==2){
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[3].getText().equals("O") && buttons[6].getText().equals("O") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[4].getText().equals("O") && buttons[7].getText().equals("O") && buttons[1].getText().equals("")
                    && gamestate[1]==2){
                buttons[1].setText("O");
                buttons[1].setEnabled(false);
                buttons[1].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=1;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[5].getText().equals("O") && buttons[8].getText().equals("O") && buttons[2].getText().equals("")
                    && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("O") && buttons[6].getText().equals("O") && buttons[3].getText().equals("")
                    && gamestate[3]==2){
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[1].getText().equals("O") && buttons[7].getText().equals("O") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[2].getText().equals("O") && buttons[8].getText().equals("O") && buttons[5].getText().equals("")
                    && gamestate[5]==2){
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[4].getText().equals("O") && buttons[8].getText().equals("O") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[0].getText().equals("O") && buttons[8].getText().equals("O") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("O") && buttons[4].getText().equals("O") && buttons[2].getText().equals("")
                    && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("O") && buttons[2].getText().equals("O") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("")
                    && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("")
                    && gamestate[5]==2){
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[1].getText().equals("X") && buttons[2].getText().equals("X") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[4].getText().equals("X") && buttons[5].getText().equals("X") && buttons[3].getText().equals("")
                    && gamestate[3]==2){
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[7].getText().equals("X") && buttons[8].getText().equals("X") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[2].getText().equals("X") && buttons[1].getText().equals("")
                    && gamestate[1]==2){
                buttons[1].setText("O");
                buttons[1].setEnabled(false);
                buttons[1].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=1;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[3].getText().equals("X") && buttons[5].getText().equals("X") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("X") && buttons[8].getText().equals("X") && buttons[7].getText().equals("")
                    && gamestate[7]==2){
                buttons[7].setText("O");
                buttons[7].setEnabled(false);
                buttons[7].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=7;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("")
                    && gamestate[7]==2){
                buttons[7].setText("O");
                buttons[7].setEnabled(false);
                buttons[7].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=7;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[3].getText().equals("X") && buttons[6].getText().equals("X") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[4].getText().equals("X") && buttons[7].getText().equals("X") && buttons[1].getText().equals("")
                    && gamestate[1]==2){
                buttons[1].setText("O");
                buttons[1].setEnabled(false);
                buttons[1].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=1;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[5].getText().equals("X") && buttons[8].getText().equals("X") && buttons[2].getText().equals("")
                    && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[6].getText().equals("X") && buttons[3].getText().equals("")
                    && gamestate[3]==2){
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[1].getText().equals("X") && buttons[7].getText().equals("X") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[2].getText().equals("X") && buttons[8].getText().equals("X") && buttons[5].getText().equals("")
                    && gamestate[5]==2){
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("")
                    && gamestate[8]==2){
                buttons[8].setText("O");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=8;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[4].getText().equals("X") && buttons[8].getText().equals("X") && buttons[0].getText().equals("")
                    && gamestate[0]==2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[0].getText().equals("X") && buttons[8].getText().equals("X") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("")
                    && gamestate[6]==2){
                buttons[6].setText("O");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=6;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("X") && buttons[4].getText().equals("X") && buttons[2].getText().equals("")
                    && gamestate[2]==2){
                buttons[2].setText("O");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=2;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            } else if(buttons[6].getText().equals("X") && buttons[2].getText().equals("X") && buttons[4].getText().equals("")
                    && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("X") && buttons[4].getText().equals("O") && buttons[8].getText().equals("X")
                    && gamestate[5]==2) {
                buttons[5].setText("O");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=5;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }



            else if(buttons[2].getText().equals("X") && buttons[4].getText().equals("O") && buttons[6].getText().equals("X")
                    && gamestate[3]==2) {
                buttons[3].setText("O");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=3;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[4].getText().equals("") && gamestate[4]==2){
                buttons[4].setText("O");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=4;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }

            else if(buttons[0].getText().equals("") && gamestate[0]!=2){
                buttons[0].setText("O");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#44240f"));
                computerbutton.setText("It's Your Turn!!");
                computerbutton.setTextColor(Color.parseColor("#393232"));
                computerbutton.setEnabled(false);
                updatecomputermusic();
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                gamestatepointer=0;
                gamestate[gamestatepointer]=1;
                nonexecuted=false;
                countactive++;
                return;
            }
        }







        if (countactive>2 && nonexecuted==true)
        {
            random=new Random();
            while(true)
            {
                int nextstate=random.nextInt(8);
                if(gamestate[nextstate]!=0 && gamestate[nextstate]==2)
                {
                    buttons[nextstate].setText("O");
                    buttons[nextstate].setEnabled(false);
                    buttons[nextstate].setBackgroundColor(Color.parseColor("#44240f"));
                    computerbutton.setText("It's Your Turn!!");
                    updatecomputermusic();
                    computerbutton.setTextColor(Color.parseColor("#393232"));
                    computerbutton.setEnabled(false);
                    computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                    gamestatepointer=nextstate;
                    gamestate[gamestatepointer]=1;
                    nonexecuted=false;
                    countactive++;
                    break;
                }

            }
        }

        if (nonexecuted)
        {
            random=new Random();
            while(true)
            {
                int nextstate=random.nextInt(8);
                if(gamestate[nextstate]!=0 && gamestate[nextstate]==2)
                {
                    buttons[nextstate].setText("O");
                    buttons[nextstate].setEnabled(false);
                    buttons[nextstate].setBackgroundColor(Color.parseColor("#44240f"));
                    computerbutton.setText("It's Your Turn!!");
                    computerbutton.setTextColor(Color.parseColor("#393232"));
                    computerbutton.setEnabled(false);
                    updatecomputermusic();
                    computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                    gamestatepointer=nextstate;
                    gamestate[gamestatepointer]=1;
                    nonexecuted=false;
                    countactive++;
                    break;
                }

            }
//            Toasty.normal(Tic_Tac_AI_Hard.this,"Draw",Toast.LENGTH_SHORT).show();
//            playagain();
        }


    }

    private Boolean check1stOccourance()
    {
        boolean nonexecuted=false;
        if (buttons[0].getText().toString().equals("") && buttons[1].getText().toString().equals("") &&
                buttons[2].getText().toString().equals("") && buttons[3].getText().toString().equals("")
                && buttons[4].getText().toString().equals("") && buttons[5].getText().toString().equals("")
                && buttons[6].getText().toString().equals("") && buttons[7].getText().toString().equals("")
                && buttons[8].getText().toString().equals("") && gamestate[0]==2 && gamestate[1]==2 && gamestate[2]==2
                && gamestate[3]==2 && gamestate[4]==2 && gamestate[5]==2 && gamestate[6]==2
                && gamestate[7]==2 && gamestate[8]==2)
        {
            random=new Random();
            while(true)
            {
                int nextstate=random.nextInt(8);
                if(gamestate[nextstate]!=0 && gamestate[nextstate]==2)
                {
                    buttons[nextstate].setText("O");
                    buttons[nextstate].setEnabled(false);
                    buttons[nextstate].setBackgroundColor(Color.parseColor("#44240f"));
                    computerbutton.setText("It's Your Turn!!");
                    computerbutton.setTextColor(Color.parseColor("#393232"));
                    computerbutton.setEnabled(false);
                    updatecomputermusic();
                    computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                    gamestatepointer=nextstate;
                    gamestate[gamestatepointer]=1;
                    nonexecuted=true;
                    countactive++;
                    break;
                }

            }
        }
        return nonexecuted;
    }

    private Boolean check2ndOccourance()
    {
        boolean nonexecuted=false;
        int firstcase;
        if (buttons[4].getText().equals("X") && buttons[0].getText().equals("") && buttons[1].getText().equals("")
                && buttons[2].getText().equals("")&& buttons[3].getText().equals("")&& buttons[5].getText().equals("")
                && buttons[6].getText().equals("") && buttons[7].getText().equals("")
                && buttons[8].getText().equals("") && gamestate[4]!=2)
        {
            while (true)
            {
                Random random=new Random();
                int n=random.nextInt(9);
                if (n!=4)
                {
                    firstcase=n;
                    break;
                }
            }
            buttons[firstcase].setText("O");
            buttons[firstcase].setEnabled(false);
            buttons[firstcase].setBackgroundColor(Color.parseColor("#44240f"));
            computerbutton.setText("It's Your Turn!!");
            updatecomputermusic();
            computerbutton.setTextColor(Color.parseColor("#393232"));
            computerbutton.setEnabled(false);
            computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
            gamestatepointer=2;
            gamestate[gamestatepointer]=1;
            nonexecuted=true;
            countactive++;
        }
        return nonexecuted;
    }


    public void playerbutton()
    {
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[0].setText("X");
                buttons[0].setEnabled(false);
                buttons[0].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                updatehumanmusic();
                gamestatepointer=0;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for Computer's turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[1].setText("X");
                buttons[1].setEnabled(false);
                buttons[1].setBackgroundColor(Color.parseColor("#025955"));
                gamestatepointer=1;
                updatehumanmusic();
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                startloop=1;
                setDisable();
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[2].setText("X");
                buttons[2].setEnabled(false);
                buttons[2].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                updatehumanmusic();
                gamestatepointer=2;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[3].setText("X");
                buttons[3].setEnabled(false);
                buttons[3].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                updatehumanmusic();
                gamestatepointer=3;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[4].setText("X");
                buttons[4].setEnabled(false);
                buttons[4].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                updatehumanmusic();
                gamestatepointer=4;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[5].setText("X");
                buttons[5].setEnabled(false);
                buttons[5].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                gamestatepointer=5;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                updatehumanmusic();
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[6].setText("X");
                buttons[6].setEnabled(false);
                buttons[6].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                gamestatepointer=6;
                updatehumanmusic();
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[7].setText("X");
                buttons[7].setEnabled(false);
                buttons[7].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                updatehumanmusic();
                gamestatepointer=7;
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
            }
        });
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttons[8].setText("X");
                buttons[8].setEnabled(false);
                buttons[8].setBackgroundColor(Color.parseColor("#025955"));
                startloop=1;
                gamestatepointer=8;
                updatehumanmusic();
                gamestate[gamestatepointer]=0;
                computerbutton.setText("Click it for "+computername+"'s turn!");
                computerbutton.setEnabled(true);
                computerbutton.setBackgroundColor(Color.parseColor("#ffab73"));
                countactive++;
                setDisable();
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
        countactive=0;
        get1stOccouranc=false;
        get2ndOccourance=false;
        for (int i=0;i<buttons.length;i++)
        {

            buttons[i].setText("");
            buttons[i].setBackgroundColor(Color.parseColor("#D2A56D"));
            buttons[i].setEnabled(true);
            gamestate[i]=2;
        }
        computerbutton.setText("It's Your Turn!!");
    }
    private void updateplayerscore()
    {
        p1score.setText(Integer.toString(p1scorecount));
        computerscore.setText(Integer.toString(p2scorecount));
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
    private void setEnable()
    {
        for (int i=0;i<buttons.length;i++)
        {
            if(gamestate[i]==1)
            {
               buttons[i].setEnabled(false);
            }
            buttons[i].setEnabled(true);
        }
    }
    private void setDisable()
    {
        while(true)
        {
            int i=0;
            while(i<buttons.length)
            {
               if (gamestate[i]==2)
               {
                   buttons[i].setEnabled(false);
               }
               i++;
            }
            break;
        }
    }

    public void customAlertDialogi()
    {

        AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.hardaimodeui, null);
        alertDialog.setCancelable(false);
        final EditText etComments = view.findViewById(R.id.etCommentsiii);
        Button button=view.findViewById(R.id.playnow);

        ImageButton crossit4=view.findViewById(R.id.crossit4);
        crossit4.setOnClickListener(new View.OnClickListener() {
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
                playerName1=name;
                if ((name.isEmpty()))
                {
                    alertDialog.dismiss();
                    customAlertDialogi();
                    Toasty.error(Tic_Tac_AI_Hard.this,"Please Enter a Meaningful Name!", Toast.LENGTH_SHORT).show();
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

    private Activity getActivity() {
        return Tic_Tac_AI_Hard.this;
    }
    private void statusbarcolor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(Color.parseColor("#492000"));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#492000"));
        }
    }
    public void  customizeActionBari()
    {
        getSupportActionBar().setTitle("Noughts and Crosses");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.aihardactionbarcolor));
    }

    public void quit()
    {
        SpannableString Title=new SpannableString("Thanks for choosing AI mode!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,"Thanks for choosing AI mode!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(Tic_Tac_AI_Hard.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#3B270C"))
                .addButton("YES", Color.parseColor("#FFFFFF"), Color.parseColor("#543b19"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showscorecard();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"), Color.parseColor("#997950"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {
        quit();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.custom_action, menu);
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
        switch (item.getItemId())
        {
            case R.id.usermanual:
            {
                instruction_mannual.callinstructionmannual(Tic_Tac_AI_Hard.this);
            }
            return true;

            case R.id.musictune:
            {
                musiccount++;
                if (musiccount%2!=0)
                {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_musiccancel));
                }
                else
                {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_musicon));
                }
            }
            return true;
            case R.id.settingsopt:
            {
                AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
                View view = Tic_Tac_AI_Hard.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(Tic_Tac_AI_Hard.this);
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
                        Settings.showSettings(Tic_Tac_AI_Hard.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
            return true;
            case android.R.id.home:
            {
                quit();
            }
            return true;

            default:return super.onOptionsItemSelected(item);
        }
    }
    private void genrandomname()
    {
        Random random=new Random();
        int n=random.nextInt(14);
        int genfemalewin=random.nextInt(3);
        switch(n)
        {
            case 0:
            {
                computername="Sabrina";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 1:
            {
                computername="Georgia";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 2:
            {
                computername="Dale";
                malesound();
                break;
            }
            case 3:
            {
                computername="Honey";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 4:
            {
                computername="Blossom";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 5:
            {
                computername="Faye";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 6:
            {
                computername="Elena";
                welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewelcome);
                femalesound(genfemalewin);
                break;
            }
            case 7:
            {
                computername="Milo";
                malesound();
                break;
            }
            case 8:
            {
                computername="Bailey";
                malesound();
                break;
            }
            case 9:
            {
                computername="Oliver";
                malesound();
                break;
            }
            case 10:
            {
                computername="Windsor";
                malesound();
                break;
            }
            case 11:
            {
                computername="Matt";
                malesound();
                break;
            }
            case 12:
            {
                computername="Maxwell";
                malesound();
                break;
            }
            case 13:
            {
                computername="Neal";
                malesound();
                break;
            }

        }
    }
    private void showwin(String playerName12)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.customwarninghard, null);
        alertDialog.setCancelable(true);

        Button button=view.findViewById(R.id.playagaine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }
    private void malesound()
    {
        welcome=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.malewelcome);
        musicwin=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.malewinvoice);
        musiclose=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.malelostcry);
    }
    private void femalesound(int genfemalewin)
    {
        switch(genfemalewin)
        {
            case 0:
            {
                musicwin=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewin1);
                break;
            }
            case 1:
            {
                musicwin=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewin2);
                break;
            }
            case 2:
            {
                musicwin=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalewin3);
                break;
            }
        }
        musiclose=MediaPlayer.create(Tic_Tac_AI_Hard.this,R.raw.femalelostcry);
    }

    private void showscorecard()
    {
        applause=MediaPlayer.create(this,R.raw.applause);
        AlertDialog alertDialog = new AlertDialog.Builder(Tic_Tac_AI_Hard.this).create();
        View view = Tic_Tac_AI_Hard.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
        alertDialog.setCancelable(false);
        applause.start();
        checkmusicstate=true;
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
                Intent intent=new Intent(Tic_Tac_AI_Hard.this,tictacoptions.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
            buttons[i].setBackgroundColor(Color.parseColor("#D2A56D"));
        }
        buttoncount=0;
        innercount=0;
        startloop=0;
        rountcount=0;
        p1scorecount=0;
        p2scorecount=0;
        activestate=true;
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
//
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


//https://colorhunt.co/palette/257903