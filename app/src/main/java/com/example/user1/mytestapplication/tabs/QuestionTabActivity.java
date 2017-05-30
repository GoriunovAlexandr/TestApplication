package com.example.user1.mytestapplication.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user1.mytestapplication.R;
import com.example.user1.mytestapplication.model.Question;
import com.example.user1.mytestapplication.tabs.fragments.BinaryChoiceMode;
import com.example.user1.mytestapplication.tabs.fragments.MultipleChoiceMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 5/29/2017.
 */

public class QuestionTabActivity extends AppCompatActivity {
    String TAG = "QuestionTabActivity";
    static boolean questionType = false;
    List<Question> questionList;
    static QuestionTabActivity instance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_tab);

        instance = this;
        loadQuestions();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadQuestionType(questionType);


    }



    public void addFragment(Fragment fragment) {
        Log.d(TAG, "addFragment() " + fragment.getTag());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment, null);
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    public void loadQuestions(){
        questionList = new ArrayList<>();
        for (int i =0; i< 10; i++) {
            Question q = new Question();
            q.setQuestion("SomeQuestion" + i);
            q.setQuestionDescription("Some Question Description");

            List<String> variants = new ArrayList<>();
            variants.add("variant1");
            variants.add("variant2");
            variants.add("variant3");

            q.setAnswerVariants(variants);
            q.setRightAnswer("variant1");
            questionList.add(q);
        }

    }


    public void loadQuestionType(boolean questionType){
        if(questionType){
            addFragment(MultipleChoiceMode.createFragment(this,questionList));
        }else{
            addFragment(BinaryChoiceMode.createFragment(this,questionList));
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"onBackPressed ");
        finish();
    }
}