package com.trap17.yinwinston.biggernumbergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Dialog;
import android.widget.ImageView;
import android.content.Context;
import android.view.View.OnClickListener;
import java.lang.Math.*;
import android.widget.Toast;
import java.util.TimerTask;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    TextView score,timeTracker;
    EditText name;
    Button left, right, start, reset;
    ImageView graphic;
    int scores,duration = 0;
    double timesec;
    Timer timer,timer1, timer2;
    ProgressBar progress;
    ConstraintLayout wholething;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        left = findViewById(R.id.leftButton);
        right = findViewById(R.id.rightButton);
        start = findViewById(R.id.start);
        score = findViewById(R.id.score);
        name = findViewById(R.id.insertName);
        graphic = findViewById(R.id.graphic);
        reset = findViewById(R.id.reset);
        timeTracker = findViewById(R.id.timer);
        progress = findViewById(R.id.timebar);
        wholething = findViewById(R.id.wholething);
        start.setOnClickListener(new View.OnClickListener(){//once started, left button and right numbers get random numbers between 0-100 and start button becomes invisible
            @Override
            public void onClick(View v){
                progress.setVisibility(View.VISIBLE);
                left.setVisibility(View.VISIBLE);
                right.setVisibility(View.VISIBLE);
                left.setClickable(true);
                right.setClickable(true);
                name.setVisibility(View.INVISIBLE);
                name.setClickable(false);
                start.setClickable(false);
                start.setVisibility(View.INVISIBLE);
                int rand = (int)(Math.random()*100);
                left.setText(""+rand);
                rand = (int)(Math.random()*100);
                right.setText(""+rand);
                if(!name.getText().toString().equals("")){
                    score.setText(name.getText().toString()+"'s Score: " + scores);
                }
                else {
                    score.setText("Your Score: " + scores);
                }
                timer=new Timer();
                duration=0;
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String currentTime = getString(R.string.time,duration++);
                                timeTracker.setVisibility(View.VISIBLE);
                                timeTracker.setText("Time Remaining: " + (30-duration)+ " seconds");
                                progress.setProgress((int)(100-duration*(100.0/30.0)));
                                if(duration>=30){
                                    graphic.setImageResource(R.drawable.yourtimeisup);
                                    progress.setProgress(100);
                                    progress.setVisibility(View.INVISIBLE);
                                    left.setClickable(false);
                                    left.setVisibility(View.INVISIBLE);
                                    right.setVisibility(View.INVISIBLE);
                                    right.setClickable(false);
                                    reset.setClickable(true);
                                    reset.setVisibility(View.VISIBLE);
                                    timer.cancel();
                                    timer.purge();
                                }

                            }
                        });
                    }
                }, 1000, 1000);
            }
        });
        left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Integer.parseInt(left.getText().toString()) >= Integer.parseInt(right.getText().toString())){ //checks for larger int, if so, sends correct toast, if not, sends incorrect toast
                    scores += 100;
                    Context context = getApplicationContext();
                    CharSequence text = "Correct!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    wholething.setBackgroundColor(Color.GREEN);
                    timer1 = new Timer();
                    timesec = 0.0;
                    timer1.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timesec = timesec +0.5;
                                    timeTracker.setVisibility(View.VISIBLE);
                                    if(timesec>=0.5){
                                        wholething.setBackgroundColor(Color.WHITE);
                                        timer1.cancel();
                                        timer1.purge();
                                    }

                                }
                            });
                        }
                    }, 500, 1000);

                }
                else{
                    scores -=100;
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    wholething.setBackgroundColor(Color.RED);
                    timer1 = new Timer();
                    timesec = 0.0;
                    timer1.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timesec = timesec +0.5;
                                    timeTracker.setVisibility(View.VISIBLE);
                                    if(timesec>=0.5){
                                        wholething.setBackgroundColor(Color.WHITE);
                                        timer1.cancel();
                                        timer1.purge();
                                    }

                                }
                            });
                        }
                    }, 500, 1000);
                }
                if(!name.getText().toString().equals("")){
                    score.setText(name.getText().toString()+"'s Score: " + scores);
                }
                else {
                    score.setText("Your Score: " + scores);
                }
                if(scores == 500){// if score reaches 500, show congrats message
                    graphic.setImageResource(R.drawable.congrats);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                    timeTracker.setVisibility(View.INVISIBLE);
                    timer.cancel();
                }
                else if(scores == -200){ //else show a sorry image
                    graphic.setImageResource(R.drawable.sorry);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                    timeTracker.setVisibility(View.INVISIBLE);
                    timer.cancel();
                }
                else {
                    int rand = (int)(Math.random()*100);
                    left.setText(""+rand);
                    rand = (int)(Math.random()*100);
                    right.setText(""+rand);
                }
            }
        });
        right.setOnClickListener(new View.OnClickListener(){//same logic and leftButton
            @Override
            public void onClick(View v){
                if(Integer.parseInt(left.getText().toString()) <= Integer.parseInt(right.getText().toString())){ //checks for larger int, if so, sends correct toast, if not, sends incorrect toast
                    scores += 100;
                    Context context = getApplicationContext();
                    CharSequence text = "Correct!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    wholething.setBackgroundColor(Color.GREEN);
                    timer1 = new Timer();
                    timesec = 0.0;
                    timer1.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timesec = timesec +0.5;
                                    timeTracker.setVisibility(View.VISIBLE);
                                    if(timesec>=0.5){
                                        wholething.setBackgroundColor(Color.WHITE);
                                        timer1.cancel();
                                        timer1.purge();
                                    }

                                }
                            });
                        }
                    }, 500, 1000);
                }
                else{
                    scores -=100;
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    wholething.setBackgroundColor(Color.RED);
                    timer1 = new Timer();
                    timesec = 0.0;
                    timer1.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timesec = timesec +0.5;
                                    timeTracker.setVisibility(View.VISIBLE);
                                    if(timesec>=0.5){
                                        wholething.setBackgroundColor(Color.WHITE);
                                        timer1.cancel();
                                        timer1.purge();
                                    }

                                }
                            });
                        }
                    }, 500, 1000);
                }
                if(!name.getText().toString().equals("")){
                    score.setText(name.getText().toString()+"'s Score: " + scores);
                }
                else {
                    score.setText("Your Score: " + scores);
                }
                if(scores == 500){
                    graphic.setImageResource(R.drawable.congrats);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                    timeTracker.setVisibility(View.INVISIBLE);
                    timer.cancel();
                }
                else if(scores == -200){
                    graphic.setImageResource(R.drawable.sorry);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                    timeTracker.setVisibility(View.INVISIBLE);
                    timer.cancel();
                }
                else {
                    int rand = (int)(Math.random()*100);
                    left.setText(""+rand);
                    rand = (int)(Math.random()*100);
                    right.setText(""+rand);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                start.setVisibility(View.VISIBLE);
                start.setClickable(true);
                reset.setVisibility(View.INVISIBLE);
                reset.setClickable(false);
                scores = 0;
                score.setText("Score: 0");
                name.setClickable(true);
                name.setVisibility(View.VISIBLE);
                name.setText("");
                progress.setProgress(100);
                progress.setVisibility(View.INVISIBLE);
                graphic.setImageResource(R.drawable.numbers);
                timeTracker.setText("Time remaining: 30 seconds");
                timeTracker.setVisibility(View.INVISIBLE);
            }
        });

    }
}
