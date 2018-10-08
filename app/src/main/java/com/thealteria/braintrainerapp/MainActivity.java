package com.thealteria.braintrainerapp;

import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button play, button0, button1, button2, button3, playAgain;
    TextView result, sum, points, timer, brainlogo;
    GridLayout quiz;
    RelativeLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Integer locOfCorrectAns;
    int score =0;
    int noOfques = 0;

    public void playAgain(View v) {
        score = 0;
        noOfques = 0;

        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        brainlogo.setVisibility(View.INVISIBLE);

        genQues();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(noOfques));


            }
        }.start();

    }

    public void genQues() {

        Random random = new Random();

        int a = random.nextInt(31);
        int b = random.nextInt(31);

        sum.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locOfCorrectAns = random.nextInt(4);
        answers.clear(); //clear the previous ans and generates a new set of ans

        int incorrectAns;

        for (int i = 0; i <4; i++) {

            if (i == locOfCorrectAns) {
                answers.add( a + b );
            }

            else {

                incorrectAns = random.nextInt(41);

                while (incorrectAns == a+b) {
                    answers.add(random.nextInt(61));
                }

                answers.add(incorrectAns);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void play(View v) {

        play.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgain));

    }

    public void chooseAns(View v) {

        if (v.getTag().toString().equals(locOfCorrectAns.toString(locOfCorrectAns))) {

            score++;
            result.setText("Correct!");
        }

        else {
            result.setText("Wrong!");
        }

        noOfques++;
        points.setText(Integer.toString(score) + "/" + Integer.toString(noOfques));
        genQues();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);

        sum = (TextView) findViewById(R.id.sum);

        button0 =(Button) findViewById(R.id.button);
        button1 =(Button) findViewById(R.id.button2);
        button2 =(Button) findViewById(R.id.button3);
        button3 =(Button) findViewById(R.id.button4);
        result = (TextView) findViewById(R.id.result);
        points = (TextView) findViewById(R.id.points);
        brainlogo = findViewById(R.id.textView4);
        timer = (TextView) findViewById(R.id.timer);
        playAgain = (Button) findViewById(R.id.playAgain);
        quiz = (GridLayout) findViewById(R.id.quiz);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);

        brainlogo.setText("  Brain" + "\n" + "Trainer");

    }
}
