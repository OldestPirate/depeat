package com.example.myapplication2;

import android.util.Patterns;

public class Utils {

    //attributi statici
    final static int LEN_NUMBER = 10;
    final static int LEN_PASSWORD = 6;
    final static String EMAIL_KEY = "email";


    //metodi statici
    public static boolean verifyEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPasswordValid(String password, String passwordConfirm){
        return password.length() > LEN_PASSWORD && password.equals(passwordConfirm);
    }

    public static boolean isNumberValid(String number){
        return number.length() == LEN_NUMBER;
    }
}
