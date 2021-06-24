package com.AuroSaswatRaj.noughtsandcrosses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.crowdfire.cfalertdialog.CFAlertDialog;

import es.dmoral.toasty.Toasty;

public class game_UI_1 extends AppCompatActivity implements View.OnClickListener {

    //Layout for Radio button...!!!

    //layout BAckground
    private ConstraintLayout mylayout;
    private static Boolean checkmusicstate=false;

    private static MediaPlayer applause;

    private static int count=0,musicstate=0;
    private static String playerName1,playername2;
    private MediaPlayer mediaPlayer,player1music;
    private TextView player1score, player2score, playerstatus,player1,player2;
    private Button button[] = new Button[9];
    private Button save, reset;

    private static int savescorep1,savescorep2,savedraw;

    private  MediaPlayer drawmusic;

    private int player1scorecount, player2scorecount;//To keep tracks of Score of players
    private int rountcount;//To keeptrack of pressed buttons


    boolean activestate;//To switch between players.....

    //for player 1 button=>0
    //for player 2 button=>1
    //for empty button=>2

    int gamestate[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};//initial state of the game....

    //if(gamestate==0)->player 1
    //if(gamestate==1)->player 2
    //if(gamestate==2)->empty

    //Logic for winning positions..
    int winninglogic[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8} //Rows Logic
            , {0, 3, 6}, {1, 4, 7}, {2, 5, 8}//Columns logic
            , {2, 4, 6}, {0, 4, 8}};//Diagonal logic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.musicimagecount%2==0)
        {
            BackgroundSoundService.backgroundMusic.start();
        }
        setContentView(R.layout.activity_game__u_i_1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customAlertDialogi();
        customizeActionBari();
        statusbarColor1();

        //Background UI

        mylayout=findViewById(R.id.gamebackground);

        player1score = findViewById(R.id.playeronescore);
        player2score = findViewById(R.id.playertwoscore);
        playerstatus = findViewById(R.id.playerstatus);
        player1=findViewById(R.id.player1);
        player2=findViewById(R.id.player2);

        drawmusic=MediaPlayer.create(game_UI_1.this,R.raw.humanwin);


         mediaPlayer=MediaPlayer.create(this,R.raw.clickbtn);
        player1music=MediaPlayer.create(this,R.raw.player1);



        reset = findViewById(R.id.reset);

        for (int i = 0; i < button.length; i++) {
            String id = "btn_" + i;
            int resourceID = getResources().getIdentifier(id, "id", getPackageName());
            button[i] = findViewById(resourceID);
            button[i].setOnClickListener(this);
        }

        rountcount = 0;
        player1scorecount = 0;
        player2scorecount = 0;
        activestate = true;


        resetit();
        //saveit();




        SharedPreferences getShared=getSharedPreferences("score",MODE_PRIVATE);
        SharedPreferences getShared1=getSharedPreferences("score1",MODE_PRIVATE);

//        String value=getShared.getString("score","0");
//        String value1=getShared1.getString("score1","0");

//        player1score.setText(value);
//        player2score.setText(value1);







    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")) {
            return;
        }

        String ButtonID = v.getResources().getResourceEntryName(v.getId());//Tracks the pressed Button Id...
        int gamestatepointer = Integer.parseInt(ButtonID.substring(ButtonID.length() - 1, ButtonID.length()));//Tracks the Id-Number

        if (activestate)
        {
            ((Button) v).setText("X");
            if(musicstate%2==0) {
                player1music.start();
            }
            v.setBackgroundColor(Color.parseColor("#f7ea00"));
            ((Button) v).setTextColor(Color.parseColor("#151515"));
            gamestate[gamestatepointer] = 0;
        }
        else
        {
            ((Button)v).setText("O");
            if(musicstate%2==0) {
                mediaPlayer.start();
            }
            v.setBackgroundColor(Color.parseColor("#007580"));
            ((Button) v).setTextColor(Color.parseColor("#f9f3f3"));
            gamestate[gamestatepointer] = 1;
        }

        rountcount++;
        if(checkwinner())//Check player 1 or player 2......!!!
        {
            if (activestate)//player-1....
            {
                player1scorecount++;
                savescorep1++;
                updateplayerscore();
                showwin(playerName1);
            }
            else//player-2....
            {
                player2scorecount++;
                savescorep2++;
                updateplayerscore();
                showwin(playername2);
            }
            playagain();
        }
        else if(rountcount==9)//Draw Condition.....
        {
            draw();
            savedraw++;
            drawmusic.start();
            playagain();

        }
        else
        {
            activestate=!activestate;
        }
    }
    public boolean checkwinner()
    {

        boolean winnerresult = false;

        //Check pressed button with the winning positions...
        for (int winningposion[] : winninglogic) {
            if (gamestate[winningposion[0]] == gamestate[winningposion[1]] &&
                    gamestate[winningposion[1]] == gamestate[winningposion[2]] &&
                    gamestate[winningposion[0]] != 2)
            {
                winnerresult=true;
            }
        }
        return winnerresult;

    }
    public void updateplayerscore()
    {
        String value=Integer.toString(player1scorecount);
        String value1=Integer.toString(player2scorecount);
        player1score.setText(Integer.toString(player1scorecount));
        player2score.setText(Integer.toString(player2scorecount));

    }
    public void playagain()
    {
        if(count%2==0)
        {
            rountcount=0;
            activestate=true;
            for (int i=0;i<button.length;i++)
            {
                gamestate[i]=2;
                button[i].setText("");
                button[i].setBackgroundColor(Color.parseColor("#ff7597"));
            }
        }
        else
        {
            rountcount=0;
            activestate=true;
            for (int i=0;i<button.length;i++)
            {
                gamestate[i]=2;
                button[i].setText("");
                button[i].setBackgroundColor(Color.parseColor("#3E4C59"));
            }
        }
    }
    public void showAlert()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(game_UI_1.this);
        builder.setIcon(R.drawable.ic_baseline_videogame_asset_24);
        builder.setMessage("Your support means the world to us! Thanks for your Time. Hope to work with you again in the future.");
        builder.setTitle("Thank You for choosing Noughts and Crosses!!");
        SpannableString Title=new SpannableString("Play Again");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#f21170"));
        Title.setSpan(TitleColor,0,"Play Again".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setPositiveButton(Title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();

            }
        }).setPositiveButtonIcon(getResources().getDrawable(R.drawable.ic_baseline_emoji_events_24));

        builder.show();
    }

    public void resetit()
    {
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1score.setText("0");
                player2score.setText("0");
                playagain();
                showAlert();
            }
        });
    }

    public void saveit()
    {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SharedPreferences sharedPreferences=getSharedPreferences("score",MODE_PRIVATE);
            SharedPreferences sharedPreferences1=getSharedPreferences("score1",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            SharedPreferences.Editor editor1=sharedPreferences1.edit();
            editor.putString("score",Integer.toString(player1scorecount));
            editor1.putString("score1",Integer.toString(player2scorecount));
            editor.apply();
            editor1.apply();
//            player1score.setText(Integer.toString(player1scorecount));
//            player2score.setText(Integer.toString(player2scorecount));
            Toast.makeText(game_UI_1.this,"Saved!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void statusbarColor1()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#521727"));

        }
    }
    public void statusbarColor2()
    {
        if (Build.VERSION.SDK_INT>=21) {
            Window window = this.getWindow();
            window.setStatusBarColor(Color.parseColor("#616E7E"));
        }
    }
    public void  customizeActionBari()
    {
        getSupportActionBar().setTitle("Noughts and Crosses");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.darkmodeui));
        statusbarColor1();
    }
    public void customizeActionBari1()
    {
        getSupportActionBar().setTitle("Noughts and Crosses");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.presentcolor));
        statusbarColor2();
    }
    public void customAlertDialogi()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(game_UI_1.this).create();
        View view = getActivity().getLayoutInflater().inflate(R.layout.custom_welcome, null);
        alertDialog.setCancelable(false);
        final EditText etComments = view.findViewById(R.id.etComments);
        final EditText et1Comments = view.findViewById(R.id.et1Comments);
        Button button=view.findViewById(R.id.playnow);
        ImageButton crossit=view.findViewById(R.id.crossit0);
        crossit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerName1="Player-1";
                playername2="Opponent";
                player1.setText(playerName1);
                player2.setText(playername2);
                alertDialog.cancel();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etComments.getText().toString();
                String name1=et1Comments.getText().toString();
                playerName1=name;
                playername2=name1;
                if ((name.isEmpty() && name1.isEmpty()) || (name.isEmpty() || name1.isEmpty()))
                {
                    alertDialog.dismiss();
                    customAlertDialogi();
                    Toasty.error(game_UI_1.this,"Please Enter a Meaningful Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length()>=8) {
                    String exname = name.substring(0,7);
                    String ex1name = name1.substring(0,7);
                    player1.setText(" " + exname);
                    player2.setText(ex1name+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==7)
                {
                    player1.setText(" " + name);
                    player2.setText(name1+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==6)
                {
                    player1.setText(" " + name);
                    player2.setText(name1+" ");
                    alertDialog.dismiss();
                }
                else if (name.length()==5)
                {
                    player1.setText("  " + name);
                    player2.setText(name1+"  ");
                    alertDialog.dismiss();

                }
                else if (name.length()==4) {
                    player1.setText("   " + name);
                    player2.setText(name1+"   ");
                    alertDialog.dismiss();
                }
                else if (name.length()==3)
                {
                    player1.setText("    " + name);
                    player2.setText(name1+"    ");
                    alertDialog.dismiss();
                }
                else if (name.length()==2)
                {
                    player1.setText("     " + name);
                    player2.setText(name1+"     ");
                    alertDialog.dismiss();
                }
                else if (name.length()==1)
                {
                    player1.setText("      " + name);
                    player2.setText(name1+"      ");
                    alertDialog.dismiss();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }

    private game_UI_1 getActivity() {
        return game_UI_1.this;
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId())
        {
            case R.id.setusertac:
            {
                AlertDialog alertDialog = new AlertDialog.Builder(game_UI_1.this).create();
                View view = game_UI_1.this.getLayoutInflater().inflate(R.layout.settings, null);
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
                        Developer_info.show_about_dev(game_UI_1.this);
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
                        Settings.showSettings(game_UI_1.this);
                    }
                });
                alertDialog.setView(view);
                alertDialog.show();
            }
            return true;

            case R.id.action_search: //Your task
                count++;
                if(count%2==0) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_moon));
                    customizeActionBari();
                    mylayout.setBackground(getResources().getDrawable(R.drawable.tictacbackground));
                    for (int i=0;i<button.length;i++)
                    {
                        gamestate[i]=2;
                        button[i].setText("");
                        button[i].setBackgroundColor(Color.parseColor("#ff7597"));
                    }

                    playagain();
                }
                else
                {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_light_moon));
                    mylayout.setBackground(getResources().getDrawable(R.drawable.darkmandroidui));
                    for (int i=0;i<button.length;i++)
                    {
                        gamestate[i]=2;
                        button[i].setText("");
                        button[i].setBackgroundColor(Color.parseColor("#686262"));
                    }
                    customizeActionBari1();
                    playagain();
                }
                return true;
            case R.id.action_user:
                musicstate++;
                if (musicstate % 2 != 0) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_music_off_24));
                    player1music.pause();
                    mediaPlayer.pause();
                }
                else
                {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_baseline_music_note_24));
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

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(game_UI_1.this)
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



    private void showwin(String playerName12)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(game_UI_1.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(game_UI_1.this).create();
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

    private void showscorecard()
    {
        checkmusicstate=true;
        applause=MediaPlayer.create(this,R.raw.applause);
        AlertDialog alertDialog = new AlertDialog.Builder(game_UI_1.this).create();
        View view = game_UI_1.this.getLayoutInflater().inflate(R.layout.scorecard_roshambo_human, null);
        alertDialog.setCancelable(false);
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
                Intent intent=new Intent(game_UI_1.this,tictacoptions.class);
                startActivity(intent);
                finish();
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();
    }



//    @Override
//    protected void onResume() {
//        super.onResume();
//
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        count=0;
        musicstate=0;
        checkmusicstate=false;
    }
}
/* 24191c
Thank You for choosing Noughts and Crosses!!
Your support means the world to us! Thanks for your Time. Hope to work with you again in the future.
https://stackoverflow.com/questions/4747311/how-can-i-keep-a-button-as-pressed-after-clicking-on-it
*/


