package com.example.user1.mytestapplication.pages;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user1.mytestapplication.R;

/**
 * Created by Alex on 5/27/2017.
 */

public class LogInPage extends Activity{
    String TAG = "LogInPage";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";
    EditText emailInput;
    TextView emailInputError;

    EditText passwordInput;
    TextView passwordError;

    Button logInButton;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        //sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);

        emailInput = (EditText) findViewById(R.id.email_input);
        emailInputError = (TextView) findViewById(R.id.email_input_error);

        passwordInput = (EditText) findViewById(R.id.password_input);
        passwordError = (TextView) findViewById(R.id.password_input_error);

        logInButton  = (Button) findViewById(R.id.log_in_button);

        checkIfUserIsAthorised();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateFields(emailInput.getText().toString(), passwordInput.getText().toString());

            }
        });




        emailInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    emailInputError.setVisibility(View.GONE);
                }
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                emailInputError.setVisibility(View.GONE);
            }
        });

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    passwordError.setVisibility(View.GONE);
                }
            }
        });
        passwordInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                passwordError.setVisibility(View.GONE);
            }
        });

    }


    public void validateFields (String emailInput, String passwordInput){
        Log.d(TAG,"validate Fields");
        boolean emailValidation = false;
        boolean passwordValidation = false;


        if(emailInput != null && !emailInput.equals("")){
            emailValidation = true;
        }

        if(passwordInput != null && !passwordInput.equals("")){
            passwordValidation = true;
        }

        if(emailValidation && passwordValidation){
            Log.d(TAG," go to next activity");

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(EMAIL_KEY, emailInput);
            editor.putString(PASSWORD_KEY, passwordInput);
            editor.commit();
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);

        }else{
            if(emailValidation == false){
                emailInputError.setVisibility(View.VISIBLE);
            }
            if(passwordValidation == false){
                passwordError.setVisibility(View.VISIBLE);
            }
        }

    }


    public void checkIfUserIsAthorised(){

        Log.d(TAG,"checkIfUserIsAthorised");
        String  defaultValue = sharedPref.getString(EMAIL_KEY, "");
        Log.d(TAG,"checkIfUserIsAthorised : " + defaultValue);
        if(defaultValue != null && !defaultValue.equals("")){
            Log.d(TAG,"user !null : " + defaultValue);
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }else{
            Log.d(TAG,"user null : " + defaultValue);
        }
    }




}
