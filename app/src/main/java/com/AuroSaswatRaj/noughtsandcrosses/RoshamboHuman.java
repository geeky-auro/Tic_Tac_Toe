package com.AuroSaswatRaj.noughtsandcrosses;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import es.dmoral.toasty.Toasty;

public class RoshamboHuman extends AppCompatActivity {

    private Button playerbtn1,playerbtn2;
    private TextView player1score,player2score;
    private ImageButton p1rock,p1scissor,p1paper;
    private ImageButton p2rock,p2scissor,p2paper;
    private int p1scorecount,p2scorecount;
    private TextView status;

    private LinearLayout rock0,paper1,scissor2;
    private LinearLayout rock3,paper4,scissor5;

    private static MediaPlayer applause;

    private static String playerName1,playername2;

    private LinearLayout player1linearbackie,player2linearbackie,player1stroke,player2stroke;

    private static boolean checkdraw;

    private ImageButton musicallybutton,showsettings;

    private ImageButton button[] = new ImageButton[6];

    //To keep tracks of Score of players
    private int gamestate[] = {2, 2, 2, 2, 2, 2};

    private int winninglogics[][]={{0,5},{1,3},{2,4}};
    private int drawlogic[][]={{0,3},{1,4},{2,5},{3,0},{4,1},{5,2}};
    private static int Rountcount,musiccount=0;

    private static int savescorep1,savescorep2,savedraw;

    private MediaPlayer computermusic,playermusic;

    private static MediaPlayer drawmusic;


    private Boolean acticvestate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
        setContentView(R.layout.activity_roshambo_human);
        getSupportActionBar().hide();
        statusbarColor2();

        introscreen();

        playerbtn1=findViewById(R.id.btn111);
        playerbtn2=findViewById(R.id.btn222);

        player1score=findViewById(R.id.p1scoreeee);
        player2score=findViewById(R.id.p2scoreeee);

        musicallybutton=findViewById(R.id.musicallynote);

        playermusic=MediaPlayer.create(this,R.raw.clickbtn);
        computermusic=MediaPlayer.create(this,R.raw.player1);

        showsettings=findViewById(R.id.opensettings);

        p1rock=findViewById(R.id.p1rock0);
        p1paper=findViewById(R.id.p1paper1);
        p1scissor=findViewById(R.id.p1scissor2);
        p2rock=findViewById(R.id.p2rock3);
        p2paper=findViewById(R.id.p2paper4);
        p2scissor=findViewById(R.id.p2scissor5);

        



        button[0]=findViewById(R.id.p1rock0);
        button[1]=findViewById(R.id.p1paper1);
        button[2]=findViewById(R.id.p1scissor2);
        button[3]=findViewById(R.id.p2rock3);
        button[4]=findViewById(R.id.p2paper4);
        button[5]=findViewById(R.id.p2scissor5);

        rock0=findViewById(R.id.stonebackground);
        paper1=findViewById(R.id.paperbackground);
        scissor2=findViewById(R.id.scissorbackground);

        drawmusic=MediaPlayer.create(RoshamboHuman.this,R.raw.humanwin);

        player1linearbackie=findViewById(R.id.player1linearBackie);
        player2linearbackie=findViewById(R.id.player2linearBackie);
        player1stroke=findViewById(R.id.player1background);
        player2stroke=findViewById(R.id.player2background);


        opensettings();


        acticvestate=true;
        Rountcount=0;
        p1scorecount = 0;
        p2scorecount = 0;
        checkdraw=true;

        status=findViewById(R.id.plstatus);
    }

    public void activateplayer1(View view) {
        if (view.isPressed()==false)
        {
            return;
        }
        String ButtonID = view.getResources().getResourceEntryName(view.getId());//Tracks the pressed Button Id...
        int gamestatepointer = Integer.parseInt(ButtonID.substring(ButtonID.length() - 1, ButtonID.length()));//Tracks the Id-Number
        Rountcount++;
        acticvestate=true;
        gamestate[gamestatepointer]=0;
        StringBuffer stringBuffer=new StringBuffer("Player-2 Make your Turn");
        stringBuffer.replace(0,8,playername2);
        status.setText(stringBuffer);
        updatehumanmusic();
        activestatecustomizationp1();

        checkdraw();
        checkwin();
    }

    public void activateplayer2(View view) {
        if (view.isPressed()==false)
        {
            return;
        }
        String ButtonID = view.getResources().getResourceEntryName(view.getId());//Tracks the pressed Button Id...
        int gamestatepointer = Integer.parseInt(ButtonID.substring(ButtonID.length() - 1, ButtonID.length()));//Tracks the Id-Number
        Rountcount++;
        acticvestate=false;
        gamestate[gamestatepointer]=1;
        StringBuffer stringBuffer=new StringBuffer("Player-1 Make your Turn");
        stringBuffer.replace(0,8,playerName1);
        status.setText(stringBuffer);
        updatecomputermusic();
        activestatecustomizationp2();
        checkdraw();
        checkwin();
    }
    private void checkdraw()
    {
        checkdraw=true;
        if(Rountcount>=2)
        {
            boolean draw=false;
            for (int drawcheck[]:drawlogic)
            {
                if (gamestate[drawcheck[0]]==0 && gamestate[drawcheck[1]]==1 && gamestate[drawcheck[0]]!=2 && gamestate[drawcheck[1]]!=2)
                {
                    draw=true;
                    checkdraw=false;
                    break;
                }
            }
            if (draw && checkdraw==false)
            {
                draw();
                savedraw++;
                updatescore();
                playagain();
            }
        }
    }

    private void draw()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.drawbackietac, null);
        alertDialog.setCancelable(true);
        if (musiccount%2==0)
        {
            drawmusic.start();
        }
        Button button=view.findViewById(R.id.playagain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawmusic.pause();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private void checkwin()
    {
        boolean winner=false;
        if (Rountcount>=2 && checkdraw==true)
        {

        for(int winninglogic[]:winninglogics)
        {
            if (gamestate[winninglogic[0]]== 0 &&gamestate[winninglogic[1]]==1 && gamestate[winninglogic[0]]!=2 && gamestate[winninglogic[1 ]]!=2)
            {
                winner=true;
                break;
            }

        }
        if (winner) {

            showwin(playerName1);
            p1scorecount++;
            savescorep1++;
        }

        else
            {
                showwin(playername2);
                p2scorecount++;
                savescorep2++;
            }
        updatescore();
        playagain();
        }
    }
    private void updatescore()
    {
        player1score.setText(""+p1scorecount);
        player2score.setText(""+p2scorecount);
    }
    private void playagain()
    {
        Rountcount=0;
        acticvestate=true;
        checkdraw=false;
        for (int i=0;i<button.length;i++)
        {
            gamestate[i]=2;
            button[i].setEnabled(true);
            button[i].setBackgroundColor(Color.parseColor("#fab1a0"));
        }
        player1linearbackie.setBackground(null);
        player2linearbackie.setBackground(null);
        player1stroke.setBackgroundColor(Color.parseColor("#e55039"));
        player2stroke.setBackgroundColor(Color.parseColor("#e55039"));

    }
    public void bye(View view) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationX",-50f,0f);
        animY.setDuration(500);//1sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
        showcustomMessage();

    }
    private void statusbarColor2()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#ff4d4d"));

        }
    }

    private void introscreen()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.roshambohuman_welcome, null);
        alertDialog.setCancelable(false);
        final EditText player1name=view.findViewById(R.id.p1edittext);
        final EditText player2name=view.findViewById(R.id.p2edittext);

        Button playnow=view.findViewById(R.id.playnow);
        ImageButton crossit3=view.findViewById(R.id.crossit3);

        crossit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName1="Player-1";
                playername2="Rookie";
                playerbtn1.setText(playerName1);
                playerbtn2.setText(playername2);
                alertDialog.dismiss();
            }
        });

        playnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=player1name.getText().toString();
                String name1=player2name.getText().toString();
                playerName1=name;
                playername2=name1;
                if ((name.isEmpty() && name1.isEmpty()) || (name.isEmpty() || name1.isEmpty()))
                {
                    alertDialog.dismiss();
                    introscreen();
                    Toasty.error(RoshamboHuman.this,"Please Enter a Meaningful Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length()>=8) {
                    String exname = name.substring(0,7);
                    String ex1name = name1.substring(0,7);
                    playerbtn1.setText(" " + exname);
                    playerbtn2.setText(ex1name+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==7)
                {
                    playerbtn1.setText(" " + name);
                    playerbtn2.setText(name1+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==6)
                {
                    playerbtn1.setText(" " + name);
                    playerbtn2.setText(name1+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==5)
                {
                    playerbtn1.setText("  " + name);
                    playerbtn2.setText(name1+"  ");
                    alertDialog.dismiss();

                }
                else if (name.length()==4) {
                    playerbtn1.setText("   " + name);
                    playerbtn2.setText(name1+"   ");
                    alertDialog.dismiss();
                }
                else if (name.length()==3)
                {
                    playerbtn1.setText("    " + name);
                    playerbtn2.setText(name1+"    ");
                    alertDialog.dismiss();
                }
                else if (name.length()==2)
                {
                    playerbtn1.setText("     " + name);
                    playerbtn2.setText(name1+"     ");
                    alertDialog.dismiss();
                }
                else if (name.length()==1)
                {
                    playerbtn1.setText("      " + name);
                    playerbtn2.setText(name1+"      ");
                    alertDialog.dismiss();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }



    public void resetgame(View view) {
        ObjectAnimator animY = ObjectAnimator.ofFloat(view, "translationX",50f,0f);
        animY.setDuration(500);//1sec
        animY.setInterpolator(new BounceInterpolator());
        animY.setRepeatCount(0);
        animY.start();
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View v = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.resetlayoutroshambo, null);
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
        playagain();
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
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
        player2name.setText(""+playername2);
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
            win.replace(16,24,playername2);
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
    private void showcustomMessage()
    {
        SpannableString Title=new SpannableString("Thanks for choosing us!");
        SpannableString Message=new SpannableString("Do You Want To Quit The Game?");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,"Thanks for choosing us!".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Do You Want To Quit The Game?".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(RoshamboHuman.this)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#d63031"))
                .addButton("YES", Color.parseColor("#FFFFFF"),  Color.parseColor("#eb2f06"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showscorecard();
                        dialog.dismiss();
                    }
                });
        builder.addButton("NO", Color.parseColor("#FFFFFF"),Color.parseColor("#ff7675"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showscorecard()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
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
        player2name.setText(""+playername2);
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
            win.replace(16,24,playername2);
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
                Intent intent=new Intent(RoshamboHuman.this,ChooselevelRoshambo.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        showcustomMessage();
    }
    public void disable1()
    {
        p1scissor.setEnabled(false);
        p1rock.setEnabled(false);
        p1paper.setEnabled(false);
    }
    public void disable2()
    {
        p2scissor.setEnabled(false);
        p2rock.setEnabled(false);
        p2paper.setEnabled(false);
    }

    private void activestatecustomizationp1()
    {
        button[0].setEnabled(false);
        button[0].setBackgroundColor(Color.parseColor("#cf9595"));
        button[1].setEnabled(false);
        button[1].setBackgroundColor(Color.parseColor("#cf9595"));
        button[2].setEnabled(false);
        button[2].setBackgroundColor(Color.parseColor("#cf9595"));
        player1linearbackie.setBackgroundColor(Color.parseColor("#cf7272"));
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setStroke(4,Color.parseColor("#ff0000"));
        player2stroke.setBackground(gradientDrawable);
        player1stroke.setBackgroundColor(Color.parseColor("#e55039"));

    }

    private void activestatecustomizationp2()
    {
        button[3].setEnabled(false);
        button[3].setBackgroundColor(Color.parseColor("#cf9595"));
        button[4].setEnabled(false);
        button[4].setBackgroundColor(Color.parseColor("#cf9595"));
        button[5].setEnabled(false);
        button[5].setBackgroundColor(Color.parseColor("#cf9595"));
        player2linearbackie.setBackgroundColor(Color.parseColor("#cf7272"));
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setStroke(4,Color.parseColor("#ff0000"));
        player1stroke.setBackground(gradientDrawable);
        player2stroke.setBackgroundColor(Color.parseColor("#e55039"));
    }


    public void musical(View view) {
        musiccount++;
        if (musiccount%2!=0)
        {
            musicallybutton.setImageDrawable(getResources().getDrawable(R.drawable.roshambomusicofff));
        }
        else
        {
            musicallybutton.setImageDrawable(getResources().getDrawable(R.drawable.roshamboon));
        }
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
    private void opensettings()
    {
        showsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
                View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(RoshamboHuman.this);
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
                        Settings.showSettings(RoshamboHuman.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
        });
    }

    private void showwin(String playerName12)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(RoshamboHuman.this).create();
        View view = RoshamboHuman.this.getLayoutInflater().inflate(R.layout.winnerdialogtictac, null);
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



//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (Settings.musicimagecount%2==0)
//        {
//            BackgroundSoundService.gamebackground.start();
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
    }
}
//#ff7675 ->When pressed!
//#FF4800 ->colour state