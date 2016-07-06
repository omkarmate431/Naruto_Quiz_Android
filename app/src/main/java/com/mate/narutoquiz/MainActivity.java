package com.mate.narutoquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mate.narutoquiz.R;

import static android.support.v7.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity {

    Button proceedBtn;
    EditText fname,cname;
    RadioGroup rg;
    RadioButton male,female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
        setContentView(R.layout.activity_main);
        proceedBtn = (Button)findViewById(R.id.proceedBtn);
        fname = (EditText)findViewById(R.id.fname);
        cname = (EditText)findViewById(R.id.cname);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

    }

    public void Proceed(View v)
    {

        String name = fname.getText().toString();
        String clan = cname.getText().toString();
        String gender;
        if(rg.getCheckedRadioButtonId()==R.id.male)
            gender="shinobi";
        else
            gender="kunoichi";
        Intent intent = new Intent(MainActivity.this,CourseSelect.class);
        intent.putExtra("name",name);
        intent.putExtra("clan",clan);
        intent.putExtra("gender",gender);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
