package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button loginBtn, registerBtn;
    private EditText emailEt, passwordEt;
    private Switch darkModeSw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //attach to this Activity th activity_layout.xml file
        setContentView(R.layout.activity_login);

        //initialize UI controllers
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);
        darkModeSw = findViewById(R.id.dark_mode_sw);

        //set button click
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        //set switch click
        darkModeSw.setOnClickListener(this);
    }

    private void doLogin(String email, String password){
        if (!Utils.verifyEmail(email)){
            showToast(R.string.email_wrong);
        }
        else if (password.length() < Utils.LEN_PASSWORD) {
            showToast(R.string.password_wrong);
        }
        else {
            showToast(R.string.access_success);
            /*Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Utils.EMAIL_KEY, email);
            startActivity(intent);*/
        }
    }

    private void showToast(@StringRes int resId){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn) {
            doLogin(emailEt.getText().toString(), passwordEt.getText().toString());
        }
        else if (v.getId() == R.id.register_btn){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}
