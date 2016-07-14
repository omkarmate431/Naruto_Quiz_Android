package com.mate.narutoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mate.narutoquiz.R;

import org.w3c.dom.Text;

public class CourseSelect extends AppCompatActivity {

    TextView greet;
    Button geninBtn, chuninBtn, joninBtn, rogueBtn;

    String name, clan, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_course_select);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        clan = intent.getStringExtra("clan");
        gender = intent.getStringExtra("gender");

        greet = (TextView)findViewById(R.id.greeting);
        geninBtn = (Button)findViewById(R.id.geninBtn);
        chuninBtn = (Button)findViewById(R.id.chuninBtn);
        joninBtn = (Button)findViewById(R.id.joninBtn);
        rogueBtn = (Button)findViewById(R.id.rogueBtn);
        
        greet.setText("Hello "+clan+" "+name);

    }

    public void showGeninTerms(View v)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseSelect.this).setTitle("Terms for Genin Exam")
                .setMessage("To become a Genin, you need to pass 3 C Class Quiz and 7 D Class Quiz.The Test will began immediately.")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CourseSelect.this,QuizPage.class);
                        intent.putExtra("course","genin");
                        intent.putExtra("name",name);
                        intent.putExtra("clan",clan);
                        intent.putExtra("gender",gender);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void showChuninTerms(View v)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseSelect.this).setTitle("Terms for Chunin Exam")
                .setMessage("To become a Chunin, you need to pass 3 C Class Quiz and 7 B Class Quiz.The Test will began immediately.")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CourseSelect.this,QuizPage.class);
                        intent.putExtra("course","chunin");
                        intent.putExtra("name",name);
                        intent.putExtra("clan",clan);
                        intent.putExtra("gender",gender);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void showJoninTerms(View v)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseSelect.this).setTitle("Terms for Jonin Exam")
                .setMessage("To become a Jonin, you need to pass 5 A Class Quiz and 5 S Class Quiz.The Test will began immediately.")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CourseSelect.this,QuizPage.class);
                        intent.putExtra("course","jonin");
                        intent.putExtra("name",name);
                        intent.putExtra("clan",clan);
                        intent.putExtra("gender",gender);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void showRogueTerms(View v)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CourseSelect.this).setTitle("Terms for Rogue Exam")
                .setMessage("To become a Rogue Ninja, you need to pass 3 A Class Quiz and 7 S Class Quiz.The Test will began immediately.")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(CourseSelect.this,QuizPage.class);
                        intent.putExtra("course","rogue");
                        intent.putExtra("name",name);
                        intent.putExtra("clan",clan);
                        intent.putExtra("gender",gender);
                        startActivity(intent);
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
