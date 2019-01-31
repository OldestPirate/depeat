package com.example.myapplication2.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication2.R;
import com.example.myapplication2.ui.Utils;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailInsertEt, passwordInsertEt, passwordConfirmEt, numberInsertEt;
    private Button registerBtn;
    Intent intent;
    boolean verifyEmail, isNumberValid, isPasswordValid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        emailInsertEt = findViewById(R.id.email_insert_et);
        passwordInsertEt = findViewById(R.id.password_insert_et);
        passwordConfirmEt =  findViewById(R.id.password_confirm_et);
        numberInsertEt = findViewById(R.id.number_insert_et);
        registerBtn = findViewById(R.id.register_btn);
        intent = getIntent();

        emailInsertEt.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verifyEmail = Utils.verifyEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableButton();
            }
        });

        passwordConfirmEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println(s.toString() + " " + passwordInsertEt.getText().toString());
                isPasswordValid = Utils.isPasswordValid(s.toString(), passwordInsertEt.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableButton();
            }
        });

        numberInsertEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isNumberValid = Utils.isNumberValid(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableButton();
            }
        });

    }

    private void enableButton(){
        registerBtn.setEnabled(verifyEmail&&isNumberValid&&isPasswordValid);
    }
}
