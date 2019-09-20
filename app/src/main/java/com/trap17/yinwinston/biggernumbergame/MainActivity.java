package com.trap17.yinwinston.biggernumbergame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Dialog;
import android.widget.ImageView;
import android.content.Context;
import android.view.View.OnClickListener;
import java.lang.Math.*;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView score;
    EditText name;
    Button left, right, start, reset;
    ImageView graphic;
    int scores = 0;
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
        start.setOnClickListener(new View.OnClickListener(){//once started, left button and right numbers get random numbers between 0-100 and start button becomes invisible
            @Override
            public void onClick(View v){
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
                score.setText(name.getText().toString()+"'s Score: 0");
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
                }
                else{
                    scores -=100;
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                if(name.getText().toString() != ""){
                    score.setText(name.getText().toString()+"'s Score: " + scores);
                }
                else {
                    score.setText("Your Score" + scores);
                }
                if(scores == 500){// if score reaches 500, show congrats message
                    graphic.setImageResource(R.drawable.congrats);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                }
                else if(scores == -200){ //else show a sorry image
                    graphic.setImageResource(R.drawable.sorry);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
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
                }
                else{
                    scores -=100;
                    Context context = getApplicationContext();
                    CharSequence text = "Incorrect :(";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                if(name.getText().toString() != ""){
                    score.setText(name.getText().toString()+"'s Score: " + scores);
                }
                else {
                    score.setText("Your Score" + scores);
                }
                if(scores == 500){
                    graphic.setImageResource(R.drawable.congrats);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
                }
                else if(scores == -200){
                    graphic.setImageResource(R.drawable.sorry);
                    left.setVisibility(View.INVISIBLE);
                    left.setClickable(false);
                    right.setVisibility(View.INVISIBLE);
                    right.setClickable(false);
                    reset.setClickable(true);
                    reset.setVisibility(View.VISIBLE);
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
            }
        });

    }
}
