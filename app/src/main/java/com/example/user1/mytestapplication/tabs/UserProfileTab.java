package com.example.user1.mytestapplication.tabs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user1.mytestapplication.R;

import static com.example.user1.mytestapplication.pages.LogInPage.EMAIL_KEY;
import static com.example.user1.mytestapplication.pages.LogInPage.PASSWORD_KEY;

/**
 * Created by Alex on 5/29/2017.
 */

public class UserProfileTab extends Activity {

    String TAG ="UserProfile";
    TextView emailValue;
    TextView passValue;
    Button logOutButton;

    SharedPreferences sharedPref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_tab);

        emailValue  = (TextView)findViewById(R.id.email_value);
        passValue  = (TextView)findViewById(R.id.pass_value);
        logOutButton = (Button) findViewById(R.id.log_out_button);


        //sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);

        String  userEmail = sharedPref.getString(EMAIL_KEY, "");
        String userPassword = sharedPref.getString(PASSWORD_KEY, "");

        emailValue.setText(userEmail);
        passValue.setText(userPassword);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(EMAIL_KEY, "");
                editor.putString(PASSWORD_KEY, "");
                editor.commit();
                finish();
            }
        });
    }
}