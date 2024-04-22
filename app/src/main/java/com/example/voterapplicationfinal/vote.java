package com.example.voterapplicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class vote extends AppCompatActivity {

    Button submit;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        submit = (Button) findViewById(R.id.button4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        db = new DataBase(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if(selectedRadioButtonId==-1)
                    Toast.makeText(vote.this, "Please Choose A Party", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(vote.this, "Voting Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(vote.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}