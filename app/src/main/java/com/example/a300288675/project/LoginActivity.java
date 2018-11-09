package com.example.a300288675.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin, buttonSignup;
    EditText etUserId, etPassword;
    String userid="", password="";
    TextView tvWrongEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvWrongEntry = (TextView)findViewById(R.id.tvWrongCredentials);
        etUserId = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etPassword);
        buttonLogin = (Button)findViewById(R.id.btnLogin);
        buttonSignup = (Button)findViewById(R.id.btnSignUp);

        tvWrongEntry.setVisibility(View.INVISIBLE);

        final DatabaseHelper dbh = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = etUserId.getText().toString();
                password = etPassword.getText().toString();

                if (!userid.isEmpty() && !password.isEmpty()){
                    if (dbh.validateUser(userid,password)){
                        tvWrongEntry.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(LoginActivity.this,ExpenseActivity.class));
                    } else {
                        tvWrongEntry.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));
            }
        });


    }
}
