package com.mate.narutoquiz;

import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class QuizPage extends AppCompatActivity {

    /*DECLARATIONS*/

    int question_count = 0;
    int count_down=11;
    int score;
    TextView question, level, solution,questionCount,timer_score;
    Button option1, option2, option3, option4;
    String answer;
    String name,clan,gender,course;
    String row[];
    QuizDbHelper dbHelper = new QuizDbHelper(this);
    Timer timer = new Timer();     //Timer Object Created
    int[] arr = new int[10];
    final Handler timerHandler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        /*SETUP DATABASE*/
        try {

            dbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            dbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

        /*GET INTENT VALUES*/

        Intent intent = getIntent();
        course = intent.getStringExtra("course");
        score = intent.getIntExtra("score",0);
        name = intent.getStringExtra("name");
        clan = intent.getStringExtra("clan");
        gender = intent.getStringExtra("gender");


        /*SET TIMERTASK FUNCTION*/

        final Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                timer_score.setText(""+count_down);
                if(count_down==0)
                {
                    sendValues();
                }
            }
        };

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count_down=count_down-1;
                timerHandler.post(timerRunnable);


            }
        };
        timer.schedule(timerTask,0,1000);



        /*SET QUESTION SEQUENCE*/
        question_count = intent.getIntExtra("question_count",0);
        if(question_count==0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }
            RandomizeArray(arr);
        }
        else {
            arr = intent.getIntArrayExtra("sequence");
        }


        /*SET VIEWS*/
        question = (TextView) findViewById(R.id.question);
        level = (TextView) findViewById(R.id.level);
        solution = (TextView) findViewById(R.id.solution);
        questionCount = (TextView)findViewById(R.id.questionCount);
        timer_score = (TextView)findViewById(R.id.timer_score);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);

        questionCount.setText(question_count+1+"/10");         //SET QUESTION COUNT VIEW

        /*SET QUESTIONS AND ANSWER*/
        if(question_count<10) {
            switch (course) {
                case "genin":

                    if(question_count<7)
                    {
                        level.setText("Class:D");
                        row = dbHelper.retrieveQuestionD(arr[question_count]);
                    }
                    else
                    {
                        level.setText("Class:C");
                        row = dbHelper.retrieveQuestionC(arr[question_count]);
                    }
                    question.setText(row[0]);
                    option1.setText(row[1]);
                    option2.setText(row[2]);
                    option3.setText(row[3]);
                    option4.setText(row[4]);
                    answer = row[5];

                    break;
                case "chunin":

                    if(question_count<7)
                    {
                        level.setText("Class:C");
                        row = dbHelper.retrieveQuestionC(arr[question_count]);
                    }
                    else
                    {
                        level.setText("Class:B");
                        row = dbHelper.retrieveQuestionB(arr[question_count]);
                    }
                    question.setText(row[0]);
                    option1.setText(row[1]);
                    option2.setText(row[2]);
                    option3.setText(row[3]);
                    option4.setText(row[4]);
                    answer = row[5];

                    break;

                case "jonin":

                    if(question_count<5)
                    {
                        level.setText("Class:A");
                        row = dbHelper.retrieveQuestionA(arr[question_count]);
                    }
                    else
                    {
                        level.setText("Class:S");
                        row = dbHelper.retrieveQuestionS(arr[question_count]);
                    }
                    question.setText(row[0]);
                    option1.setText(row[1]);
                    option2.setText(row[2]);
                    option3.setText(row[3]);
                    option4.setText(row[4]);
                    answer = row[5];
                    break;

                case "rogue":

                    level.setText("Class:S");
                    if(question_count<7)
                    {
                        level.setText("Class:S");
                        row = dbHelper.retrieveQuestionS(arr[question_count]);
                    }
                    else
                    {
                        level.setText("Class:A");
                        row = dbHelper.retrieveQuestionA(arr[question_count]);
                    }
                    question.setText(row[0]);
                    option1.setText(row[1]);
                    option2.setText(row[2]);
                    option3.setText(row[3]);
                    option4.setText(row[4]);
                    answer = row[5];
                    break;
            }
        }
        else
        {
            Intent intent1 = new Intent(QuizPage.this,ResultPage.class);
            intent1.putExtra("course",course);
            intent1.putExtra("score",score);
            intent1.putExtra("name",name);
            intent1.putExtra("clan",clan);
            intent1.putExtra("gender",gender);
            startActivity(intent1);
        }

    }


    /*CHECK ANSWER*/
    public void CheckAnswer(View v) throws InterruptedException {
        Button b = (Button) v;
        timer.cancel();

        switch (b.getId()) {
            case R.id.option1:
                if (b.getText().toString().equals(answer))
                {
                    solution.setText("Answer: "+answer);
                    option1.setBackgroundColor(Color.GREEN);
                    score=score+count_down;
                }
                else
                {
                    solution.setText("Answer: "+answer);
                    option1.setBackgroundColor(Color.RED);
                }
                findViewById(R.id.option2).setClickable(false);
                findViewById(R.id.option3).setClickable(false);
                findViewById(R.id.option4).setClickable(false);
                sendValues();
                break;

            case R.id.option2:
                if(b.getText().toString().equals(answer))
                {
                    solution.setText("Answer: "+answer);
                    option2.setBackgroundColor(Color.GREEN);
                    score=score+count_down;
                }

                else
                {
                    solution.setText("Answer: " + answer);
                    option2.setBackgroundColor(Color.RED);
                }

                findViewById(R.id.option1).setClickable(false);
                findViewById(R.id.option3).setClickable(false);
                findViewById(R.id.option4).setClickable(false);
                sendValues();
                break;

            case R.id.option3:
                if(b.getText().toString().equals(answer))
                {
                    solution.setText("Answer: "+answer);
                    option3.setBackgroundColor(Color.GREEN);
                    score=score+count_down;
                }

                else
                {
                    solution.setText("Answer: " + answer);
                    option3.setBackgroundColor(Color.RED);
                }
                findViewById(R.id.option1).setClickable(false);
                findViewById(R.id.option2).setClickable(false);
                findViewById(R.id.option4).setClickable(false);
                sendValues();
                break;

            case R.id.option4:
                if(b.getText().toString().equals(answer))
                {
                    solution.setText("Answer: "+answer);
                    option4.setBackgroundColor(Color.GREEN);
                    score=score+count_down;
                }

                else
                {
                    solution.setText("Answer: " + answer);
                    option4.setBackgroundColor(Color.RED);
                }
                findViewById(R.id.option1).setClickable(false);
                findViewById(R.id.option2).setClickable(false);
                findViewById(R.id.option3).setClickable(false);

                sendValues();

                break;

        }
    }

    /*RANDOMIZE QUESTION SEQUENCE*/
    public static int[] RandomizeArray(int[] array){
        Random rgen = new Random();

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    /*SET BACK BUTTON TO DO NOTHING*/
    @Override
    public void onBackPressed() {

    }

    public void sendValues()
    {
        final Intent intent = new Intent(QuizPage.this,QuizPage.class);
        Handler mHandler = new Handler();

        solution.setText("Answer: "+answer);

        question_count++;
        intent.putExtra("score",score);
        intent.putExtra("question_count",question_count);
        intent.putExtra("course",course);
        intent.putExtra("name",name);
        intent.putExtra("clan",clan);
        intent.putExtra("gender",gender);
        intent.putExtra("sequence",arr);
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(intent);
            }

        }, 1000L);
    }

}

