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

import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

    Button signup;
    ImageView back;
    TextView login;
    EditText username,email,password,repassword;
    DataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button) findViewById(R.id.button3);
        back = (ImageView) findViewById(R.id.imageView4);
        login = (TextView) findViewById(R.id.textView8);
        username = (EditText) findViewById(R.id.editTextText3);
        email = (EditText) findViewById(R.id.editTextText4);
        password = (EditText) findViewById(R.id.editTextText5);
        repassword = (EditText) findViewById(R.id.editTextText6);

        db = new DataBase(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean flag = true;
                String iusername = username.getText().toString();
                String iemail = email.getText().toString();
                String ipassword = password.getText().toString();
                String irepassword = repassword.getText().toString();
                Cursor result = db.fetchValues(iusername);

                if (iusername.matches("")) {
                    Toast.makeText(signup.this, "Enter Username", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (result.moveToFirst()) {
                    Toast.makeText(signup.this, "Username Already Exists", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (iemail.matches("")) {
                    Toast.makeText(signup.this, "Enter E-Mail", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (!isValidEmail(iemail)) {
                    Toast.makeText(signup.this, "Enter A Valid E-Mail", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (ipassword.matches("")) {
                    Toast.makeText(signup.this, "Enter Password", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (!isvalidPassword(ipassword)) {
                    Toast.makeText(signup.this, "Enter A Valid Password", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (irepassword.matches("")) {
                    Toast.makeText(signup.this, "Re-Enter Your Password", Toast.LENGTH_LONG).show();
                    flag = false;
                } else if (!ipassword.matches(irepassword)) {
                    Toast.makeText(signup.this, "Re-Entered Password Does Not Match", Toast.LENGTH_LONG).show();
                    flag = false;
                } else {
                    if (flag) {
                        boolean insert = db.insertValues(iusername, iemail, ipassword);
                        if (insert && flag) {
                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();
                            username.setText("");
                            email.setText("");
                            password.setText("");
                            repassword.setText("");
                            Intent intent = new Intent(signup.this,Login.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern special = Pattern.compile("^.*[@#$%^&*(){},.;/].*$");
    boolean isvalidPassword(String userpwd) {
        if (userpwd.length() < 8) {
            return false;
        }
        if (!lowercase.matcher(userpwd).matches()) {
            return false;
        }
        if (!uppercase.matcher(userpwd).matches()) {
            return false;
        }
        if (!number.matcher(userpwd).matches()) {
            return false;
        }
        if (!special.matcher(userpwd).matches()) {
            return false;
        }
        return true;
    }

    boolean isValidEmail(String useremail)
    {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(useremail).matches();
    }
}