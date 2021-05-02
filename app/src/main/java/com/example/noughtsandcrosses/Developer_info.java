package com.example.noughtsandcrosses;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.crowdfire.cfalertdialog.CFAlertDialog;

public class Developer_info {

    public static final String dev_info="Hey User,\n" +
            "We have been briefly introduced, now let's know about the developer:-\n" +
            "\n" +
            "Auro Saswat Raj, a 17 years old Coding geek and aspirant who tries to learn with fun. \n" +
            "This app consists of a Board game (Tic-tac-toe) and a Hand Game (Rock Paper Scissors)\n"+
            "In this app there are two modes available in Crosses as well as in Roshambo i.e Human V/S Human,Human V/S AI playing with variety types of players.";

    public static void show_about_dev(Activity activity)
    {
        SpannableStringBuilder str = new SpannableStringBuilder(dev_info);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD),0,dev_info.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString Title=new SpannableString("About the Developer");
        SpannableString Message=new SpannableString(str);
        ForegroundColorSpan TitleColor=new ForegroundColorSpan(Color.parseColor("#f5f6fa"));
        ForegroundColorSpan MessageColor=new ForegroundColorSpan(Color.parseColor("#dcdde1"));
        Title.setSpan(TitleColor,0,"About the Developer".length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        Message.setSpan(MessageColor,0,dev_info.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        CFAlertDialog.Builder builder = new CFAlertDialog.Builder(activity)
                .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                .setTitle(Title)
                .setMessage(Message).setDialogBackgroundColor(Color.parseColor("#810000"))
                .addButton("Back", Color.parseColor("#FFFFFF"), Color.parseColor("#ce1212"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


        builder.show();
    }

}
