package com.example.voterapplicationfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    ImageView back;
    TextView signup;
    Button login;
    EditText username,password;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        back = (ImageView) findViewById(R.id.imageView4);
        signup = (TextView)findViewById(R.id.textView8);
        login = (Button)findViewById(R.id.button);
        username=(EditText)findViewById(R.id.editTextText);
        password=(EditText)findViewById(R.id.editTextText2);

        db = new DataBase(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = true;
                String lusername = username.getText().toString();
                String lpassword = password.getText().toString();
                Cursor result = db.fetchValues(lusername);

                if(lusername.matches("")){
                    Toast.makeText(Login.this, "Enter Username", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if(lpassword.matches("")){
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if(!result.moveToFirst()){
                    Toast.makeText(Login.this, "Enter A Valid Username", Toast.LENGTH_LONG).show();
                    flag = false;
                }else {
                    if(flag)
                    {
                        if(result.moveToFirst())
                        {
                            String dbpassword = (result.getString(2));
                            if(dbpassword.matches(lpassword))
                            {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Login.this, vote.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                                password.setText("");
                            }
                        }
                    }
                }
            }
        });
    }
}