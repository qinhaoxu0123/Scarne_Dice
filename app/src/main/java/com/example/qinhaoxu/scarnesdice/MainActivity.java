package com.example.qinhaoxu.scarnesdice;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;


import java.util.logging.LogRecord;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public int U_score =0;
    public  int UT_score =0;
    public  int C_score =0;
    public int CT_score=0;
    Handler diceHandler = new Handler();
    Runnable diceRunnable = new Runnable() {
        @Override
        public void run() {
        TextView text=(TextView)findViewById(R.id.compscore);
            if (computerTurn()==true)
                if (CT_score<20)
                    diceHandler.postDelayed(this,500);
                else{
                    C_score+= CT_score;
                }
            else{
                CT_score=0;
                enableButton(true);
            }
            text.setText ("Computer Score: " +CT_score);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void Roll_image(View view) {
       int diceValue = randomValue();
        setDiceFace(diceValue);
        if(diceValue!=1)
            UT_score+=diceValue;
        else
        {
            enableButton(false);
            UT_score=0;
            diceHandler.postDelayed(diceRunnable,1000);
        }
    }
    public void enableButton(boolean v){
        Button roll = (Button)findViewById(R.id.rollbutton);
        Button hold = (Button)findViewById(R.id.holdbutton);
        Button reset = (Button)findViewById(R.id.resetbutton);
        roll.setEnabled(v);
        hold.setEnabled(v);
        reset.setEnabled(v);
    }

    public int randomValue()
    {
        Random temp= new Random();
        int value= temp.nextInt(6)+1;

        return value;
    }

    public boolean computerTurn(){
        int diceValue = randomValue();
        setDiceFace(diceValue);
        if(diceValue!=1)
        {
            CT_score+=diceValue;
            return true;
        }
        else
        {
            CT_score=0;
            return false;
        }
    }

    public void setDiceFace(int diceFace){
        ImageView v =(ImageView)findViewById(R.id.imageView);


        switch(diceFace)
        {
            case 1:v.setImageResource(R.drawable.dice1);
                break;
            case 2:v.setImageResource(R.drawable.dice2);
                break;
            case 3:v.setImageResource(R.drawable.dice3);
                break;
            case 4:v.setImageResource(R.drawable.dice4);
                break;
            case 5:v.setImageResource(R.drawable.dice5);
                break;
            case 6:v.setImageResource(R.drawable.dice6);
                break;

        }
    }

    public void holdDice(View view){
        enableButton(false);
        TextView text = (TextView)findViewById(R.id.yourscore);
        U_score+=UT_score;
        UT_score=0;
        text.setText("Your score: " + U_score);
        diceHandler.postDelayed(diceRunnable,0);
    }

    public void resetDice(View view){
        TextView usertext = (TextView)findViewById(R.id.yourscore);
        TextView comptext = (TextView)findViewById(R.id.compscore);
        U_score =0;
        UT_score=0;
        C_score=0;
        CT_score=0;
        usertext.setText("Your score: " + U_score);
        comptext.setText("Computer Score: " + C_score);

    }
}
