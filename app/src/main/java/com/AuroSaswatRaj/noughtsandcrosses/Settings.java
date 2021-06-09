package com.AuroSaswatRaj.noughtsandcrosses;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.crowdfire.cfalertdialog.CFAlertDialog;

public class Settings {

    public static int musicimagecount=0;

    public static void showSettings(Activity activity)
    {
        showmessage(activity);
    }

    public static void showmessage(Activity activity)
    {
        SpannableString Title=new SpannableString("Crosses and Roshambo");

        SpannableString Message=new SpannableString("Raise your game");
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,"Crosses and Roshambo".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,"Raise your game".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#222f3e"))
                .addButton("Noughts and Crosses", Color.parseColor("#1b1717"), Color.parseColor("#fed049"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tictacinstruction(activity);
                        dialog.dismiss();
                    }
                });
        builder.addButton("Roshambo", Color.parseColor("#FFFFFF"), Color.parseColor("#007580"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Roshamboinstruction(activity);
                dialog.dismiss();
            }
        });

        builder.addButton("Cancel", Color.parseColor("#FFFFFF"), Color.parseColor("#810000"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private static void tictacinstruction(Activity activity)
    {
        SpannableString Title=new SpannableString("Noughts and Crosses");

        String message="The game has a number of English names:-\n" +
                "Tick-tack-toe, tick-tat-toe, tit-tat-toe, naughts and crosses, Exy-ozies, Xs and Os.\n" +
                "A player can play a perfect game of tic-tac-toe (to win or at least draw) if, each time it is their turn to play, they choose the first available move from the following list. If the player has two in a row, they can place a third to get three in a row to win. If the opponent has two in a row, the player must play the third themselves to block the opponent. A player marks the center. (If it is the first move of the game, playing a corner move gives the second player more opportunities to make a mistake and may therefore be the better choice; however, it makes no difference between perfect players.)\n" +
                "In this app there are three modes available.These are Human V/S Human,Human V/S AI(Easy) and Human V/S AI(Hard) playing with variety types of players.\n";
        SpannableString Message=new SpannableString(message);
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,"Noughts and Crosses".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#222f3e"))
                .addButton("Back", Color.parseColor("#FFFFFF"), Color.parseColor("#535c68"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showmessage(activity);
                        dialog.dismiss();
                    }
                });
        builder.addButton("OK", Color.parseColor("#FFFFFF"), Color.parseColor("#6ab04c"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showmessage(activity);
                dialog.dismiss();
            }
        });

        builder.show();
    }
    public static void Roshamboinstruction(Activity activity)
    {
        String title="Roshambo";
        String Message1="This game is also known as Rock paper scissors.It is a hand game usually played between two people, in which each player simultaneously forms one of three shapes with an outstretched hand.These shapes are \"rock\" (a closed fist), \"paper\" (a flat hand), and \"scissors\" (a fist with the index finger and middle finger extended, forming a V).\n";
        String rules="The game is played where players deliver hand signals that will represent the elements of the game; rock, paper and scissors. The outcome of the game is determined by 3 simple rules:\n" +
                "Rock wins against scissors.\n" +
                "Scissors win against paper.\n" +
                "Paper wins against rock.";
        String finalmessage=Message1+rules;
        SpannableString Title=new SpannableString(title);
        SpannableString Message=new SpannableString(finalmessage);
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#ffffff"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#f1f2f6"));
        Title.setSpan(TitleColor,0,title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,finalmessage.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#222f3e"))
                .addButton("Back", Color.parseColor("#FFFFFF"), Color.parseColor("#535c68"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showmessage(activity);
                        dialog.dismiss();
                    }
                });
        builder.addButton("OK", Color.parseColor("#FFFFFF"), Color.parseColor("#6ab04c"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showmessage(activity);
                dialog.dismiss();
            }
        });

        builder.show();

    }

    public Activity getActivity(Activity activity) {
        return activity;
    }



}
