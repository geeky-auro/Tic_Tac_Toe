package com.AuroSaswatRaj.noughtsandcrosses;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class instruction_mannual extends AppCompatActivity {

    public static int instructionbuttoncount=0,previnnercount=0;
    public static void callinstructionmannual(Activity activity)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = activity.getLayoutInflater().inflate(R.layout.instruction_mannual, null);
        alertDialog.setCancelable(false);

        ImageButton remove=view.findViewById(R.id.remove);
        ImageView instructionimage=view.findViewById(R.id.instruction);
        TextView instructiontext=view.findViewById(R.id.instructiontext);
        Button nextButton=view.findViewById(R.id.nestinstructions);
        Button prevButton=view.findViewById(R.id.previnstructions);
        prevButton.setVisibility(View.GONE);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionbuttoncount=0;
                previnnercount=0;
                alertDialog.dismiss();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionbuttoncount++;
                if (instructionbuttoncount==1)
                {
                    prevButton.setVisibility(View.VISIBLE);
                    prevButton.setEnabled(true);
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_2));
                    instructiontext.setText("Control the brain of the AI");
                    previnnercount=1;
                }
                else if (instructionbuttoncount==2)
                {
                    previnnercount=2;
                    prevButton.setVisibility(View.VISIBLE);
                    prevButton.setEnabled(true);
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_3));
                    instructiontext.setText("Sometimes there may be a situation,Where the AI will face some difficulty to make a move.."+"\n Than Hit the Brain of the AI once more..!");


                }
                else if (instructionbuttoncount==3)
                {
                    previnnercount=3;
                    prevButton.setVisibility(View.VISIBLE);
                    nextButton.setText("Next");
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_3));
                    instructiontext.setText("Best of Luck!");


                }
                else if (instructionbuttoncount==4)
                {
                    previnnercount=4;
                    nextButton.setText("Exit");
                    prevButton.setVisibility(View.VISIBLE);

                }
                else if (instructionbuttoncount==5)
                {
                    alertDialog.dismiss();
                    instructionbuttoncount=0;
                    previnnercount=0;
                }
            }
        });



        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previnnercount==1)
                {
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_1));
                    instructiontext.setText("First of all pop up the start switch.");
                    prevButton.setVisibility(View.GONE);
                    prevButton.setEnabled(false);
                    instructionbuttoncount--;
                }
                else if (previnnercount==2)
                {

                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_2));
                    instructiontext.setText("Control the brain of the AI");
                    instructionbuttoncount=1;
                    previnnercount=1;

                }
                else if (previnnercount==3)
                {
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_3));
                    instructiontext.setText("Sometimes there may be a situation,Where the AI will face some difficulty to make a move.."+"\n Than Hit the Brain of the AI once more..!");
                    nextButton.setText("Next");
                    instructionbuttoncount=2;
                    previnnercount=2;
                }
                else if(previnnercount==4)
                {
                    instructionbuttoncount--;
                    instructionimage.setImageDrawable(activity.getResources().getDrawable(R.drawable.ist_3));
                    instructiontext.setText("Best of Luck!");
                    instructionbuttoncount=3;
                    previnnercount=3;
                    nextButton.setText("Next");
                }

            }
        });

        alertDialog.setView(view);
        alertDialog.show();
    }




}
