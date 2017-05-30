package com.example.user1.mytestapplication.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.user1.mytestapplication.R;
import com.example.user1.mytestapplication.tabs.fragments.BinaryChoiceMode;

/**
 * Created by Alex on 5/29/2017.
 */

public class SettingsTabActivity extends Activity {

    String TAG = "SettingsTab";
    Switch switchMode;
    static boolean switchValue = false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_tab);

        switchMode = (Switch) findViewById(R.id.switch_mode);
        if(switchValue) {
            switchMode.setChecked(true);
        }else{
            switchMode.setChecked(false);
        }

        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                Log.d(TAG, "Switch1 State Changed");
                if (buttonView.isChecked()){
                    Log.d(TAG, "Switch is currently ON");
                    switchValue = true;
                    QuestionTabActivity.questionType = true;
                }else{
                    Log.d(TAG, "Switch is currently OFF");
                    switchValue = false;
                    QuestionTabActivity.questionType = false;
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}