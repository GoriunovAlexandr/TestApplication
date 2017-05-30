package com.example.user1.mytestapplication.pages;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import com.example.user1.mytestapplication.R;
import com.example.user1.mytestapplication.tabs.QuestionTabActivity;
import com.example.user1.mytestapplication.tabs.SettingsTabActivity;
import com.example.user1.mytestapplication.tabs.UserProfileTab;

/**
 * Created by Alex on 5/29/2017.
 */

public class MainPage extends TabActivity {

    String TAG = "MainPage";

    String QUESTION_TAB_TAG = "tag1";
    String SETTINGS_TAB_TAG = "tag1";
    String PROFILE_TAB_TAG = "tag1";

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        tabHost = getTabHost();

        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(QUESTION_TAB_TAG);
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.question_tab_selector)); //"Question"
        tabSpec.setContent(new Intent(this, QuestionTabActivity.class));
        tabHost.addTab(tabSpec);


        tabSpec = tabHost.newTabSpec(SETTINGS_TAB_TAG);
        tabSpec.setIndicator("",getResources().getDrawable(R.drawable.settings_tab_selector)); //Settings
        tabSpec.setContent(new Intent(this, SettingsTabActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(PROFILE_TAB_TAG);
        tabSpec.setIndicator("", getResources().getDrawable(R.drawable.profile_tab_selector));  //UserProfile
        tabSpec.setContent(new Intent(this, UserProfileTab.class));
        tabHost.addTab(tabSpec);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"onBackPressed ");
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
