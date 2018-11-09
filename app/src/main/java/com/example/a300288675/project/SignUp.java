package com.example.a300288675.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    TextView tvInvalidLogin, tvInvalidEmail, tvInvalidPassword, tvInvalidCnfrmPassword;
    EditText eLogin, eFname, eLname, eEmail, ePhone, ePassword, eCnfrmPassword;
    Button btnSave;
    Boolean hasErrors;
    String login="", fname="", lname="", phone="", email="", pass="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final DatabaseHelper dbh = new DatabaseHelper(this);

        tvInvalidLogin = (TextView)findViewById(R.id.tvLoginExists);
        tvInvalidEmail = (TextView)findViewById(R.id.tvInvalidEmail);
        tvInvalidPassword = (TextView)findViewById(R.id.tvInvalidPassword);
        tvInvalidCnfrmPassword = (TextView)findViewById(R.id.tvPasswordsMismatch);

        eLogin = (EditText)findViewById(R.id.etLogin);
        eFname = (EditText)findViewById(R.id.etFName);
        eLname = (EditText)findViewById(R.id.etLName);
        eEmail = (EditText)findViewById(R.id.etEmail);
        ePhone = (EditText)findViewById(R.id.etPhone);
        ePassword = (EditText)findViewById(R.id.etPassword);
        eCnfrmPassword = (EditText)findViewById(R.id.etCnfrmPassword);

        eLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(dbh.checkUserExists(eLogin.getText().toString())){
                        tvInvalidLogin.setVisibility(View.VISIBLE);
                        hasErrors = true;
                    } else {
                        tvInvalidLogin.setVisibility(View.INVISIBLE);
                        hasErrors = false;
                    }
                }
            }
        });

        eEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!eEmail.getText().toString().contains("@") || !eEmail.getText().toString().contains(".")){
                        tvInvalidEmail.setVisibility(View.VISIBLE);
                        hasErrors = true;
                    } else {
                        tvInvalidEmail.setVisibility(View.INVISIBLE);
                        hasErrors = false;
                    }
                }
            }
        });

        ePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(ePassword.length()<8){
                        tvInvalidPassword.setVisibility(View.VISIBLE);
                        hasErrors = true;
                    } else {
                        tvInvalidPassword.setVisibility(View.INVISIBLE);
                        hasErrors = false;
                    }
                }
            }
        });

        eCnfrmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!eCnfrmPassword.getText().toString().equals(ePassword.getText().toString())){
                        tvInvalidCnfrmPassword.setVisibility(View.VISIBLE);
                        hasErrors = true;
                    } else {
                        tvInvalidCnfrmPassword.setVisibility(View.INVISIBLE);
                        hasErrors = false;
                    }
                }
            }
        });

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = eLogin.getText().toString();
                fname = eFname.getText().toString();
                lname = eLname.getText().toString();
                email = eEmail.getText().toString();
                phone = ePhone.getText().toString();
                pass = ePassword.getText().toString();

                if(!login.trim().isEmpty() && !pass.trim().isEmpty() && !email.trim().isEmpty()){
                    if(!hasErrors){
                        if(dbh.addUser(login, fname, lname, email, phone, pass, "")){
                            Toast toast = Toast.makeText(SignUp.this,"Saved Successfully", Toast.LENGTH_LONG);
                            toast.show();
                            startActivity(new Intent(SignUp.this, LoginActivity.class));
                        }
                    } else {
                        Toast toast = Toast.makeText(SignUp.this,"Data not saved. Recheck the data you have entered.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(SignUp.this,"'LoginId', 'Email' and 'Password' are mandatory fields.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


    }
}
