package com.mate.narutoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {

    TextView congo,result;
    ImageView character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        congo = (TextView) findViewById(R.id.congo);
        character = (ImageView) findViewById(R.id.char_image);
        result = (TextView) findViewById(R.id.result);
        Intent intent = getIntent();
        String course = intent.getStringExtra("course");
        String name = intent.getStringExtra("name");
        String clan = intent.getStringExtra("clan");
        String gender = intent.getStringExtra("gender");

        int score = intent.getIntExtra("score", 0);
        result.setText("Score is " + score + "/100");
        if (score >= 80) {
            if (course.equals("genin")) {
                character.setImageResource(R.drawable.genin_naruto);
                congo.setText("Congratulations " + clan + " " + name + ", you are a Genin");
            } else if (course.equals("chunin")) {
                character.setImageResource(R.drawable.chunin_neji);
                congo.setText("Congratulations " + clan + " " + name + ", you are a Chunin");
            } else if (course.equals("jonin")) {
                character.setImageResource(R.drawable.jonin_kakashi);
                congo.setText("Congratulations " + clan + " " + name + ", you are a Jonin");
            } else if (course.equals("rogue")) {
                character.setImageResource(R.drawable.rogue_itachi);
                congo.setText("Congratulations " + clan + " " + name + ", you are a Rogue Ninja");
            }

        }
        else {
            character.setImageResource(R.drawable.fail_naruto);
            congo.setText("You failed");
        }
    }



    public void GoHome(View v)
    {
        Intent intent = new Intent(ResultPage.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ResultPage.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Exit me", true);
        startActivity(intent);
    }
}
