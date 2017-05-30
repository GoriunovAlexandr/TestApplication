package com.example.user1.mytestapplication.tabs.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user1.mytestapplication.R;
import com.example.user1.mytestapplication.model.Question;

import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 5/29/2017.
 */

public class BinaryChoiceMode extends Fragment{

    static String TAG = "BinaryChoiceMode";
    public static BinaryChoiceMode instance;
    static List<Question> questionsList;
    Activity activity;
    View v;
    TextView question;
    TextView questionDescription;
    TextView questionVariant;
    Button yesButton;
    Button noButton;
    int iterator = 0;
    Question currentQuestion;
    String displayedVariant;
    RelativeLayout questionDescriptionContainer;
    RelativeLayout correctAnswerContainer;
    RelativeLayout answerButtonsContainer;
    TextView correctAnswer;
    Button loadNextButton;
    int positiveAnswers;
    int negativeAnswers;

    public static BinaryChoiceMode createFragment(Activity activity, List<Question> questionList) {
        Log.d(TAG, "Create Fragment!" );
        BinaryChoiceMode fragment = new BinaryChoiceMode();
        instance = fragment;
        fragment.setHostActivity(activity);
        questionsList = questionList;

        return fragment;
    }

    public void setHostActivity(Activity activity) {
        this.activity = activity;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");

        v = inflater.inflate(R.layout.binary_choice_mode_layout, null);
        question  = (TextView) v.findViewById(R.id.question);
        questionDescriptionContainer  = (RelativeLayout) v.findViewById(R.id.question_description_container);
        questionDescription  = (TextView) v.findViewById(R.id.question_description);
        questionVariant  = (TextView) v.findViewById(R.id.question_variant);
        yesButton  = (Button) v.findViewById(R.id.yes_button);
        noButton  = (Button) v.findViewById(R.id.no_button);
        answerButtonsContainer = (RelativeLayout) v.findViewById(R.id.answer_buttons_container);

        correctAnswer = (TextView) v.findViewById(R.id.correct_answer);
        correctAnswerContainer  = (RelativeLayout) v.findViewById(R.id.correct_answer_container);
        loadNextButton = (Button) v.findViewById(R.id.load_next_button);


        loadQuestion();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pressed YES");
                validateAnswer(true);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pressed NO");
                validateAnswer(false);
            }
        });

        loadNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pressed Next");
                correctAnswerContainer.setVisibility(View.GONE);
                answerButtonsContainer.setVisibility(View.VISIBLE);
                loadQuestion();
            }
        });


        return v;
    }


    public void loadQuestion() {
        Log.d(TAG, "loadQuestion");
        if (iterator < questionsList.size()){
            currentQuestion = questionsList.get(iterator);
            Random random = new Random();
            int answerVariantsCount = questionsList.get(iterator).getAnswerVariants().size();
            displayedVariant = questionsList.get(iterator).getAnswerVariants().get(random.nextInt(answerVariantsCount));
            iterator++;

            question.setText(currentQuestion.getQuestion());
            if (currentQuestion.getQuestionDescription() != null && !currentQuestion.getQuestionDescription().equals("")) {
                questionDescriptionContainer.setVisibility(View.VISIBLE);
                questionDescription.setText(currentQuestion.getQuestionDescription());
            } else {
                questionDescriptionContainer.setVisibility(View.GONE);
            }
            questionVariant.setText(displayedVariant);
        }else{
            Log.d(TAG," 10 Questions! Right answers: " + positiveAnswers + "  Wrong Answers: " + negativeAnswers);
            showDialog();
        }
    }

    public boolean validateAnswer(boolean isYes){
        boolean result =false;
        String rightAnswer = currentQuestion.getRightAnswer();

        if(rightAnswer.equals(displayedVariant) && isYes){
            Log.d(TAG,"You pressed Yes - The answer is right Wooohooo");
            result = true;
            showResult(result, rightAnswer);
        }else if(!rightAnswer.equals(displayedVariant) && !isYes ){
            Log.d(TAG,"You pressed No - The answer is right Wooohooo");
            result = true;
            showResult(result, rightAnswer);
        }else{
            Log.d(TAG,"The answer is False");
            showResult(result, rightAnswer);
        }
        return result;
    }

    public void showResult(boolean isCorrect, String answer){
        correctAnswerContainer.setVisibility(View.VISIBLE);
        answerButtonsContainer.setVisibility(View.GONE);
        if(isCorrect) {
            correctAnswer.setText(getResources().getString(R.string.answer_confirmation) + answer);
            positiveAnswers++;
        }
        else{
            correctAnswer.setText(getResources().getString(R.string.answer_negation) + answer);
            negativeAnswers++;
        }
    }


    public void showDialog(){
        AlertDialog.Builder adb = new AlertDialog.Builder(getContext());

        adb.setTitle("Congatulations you have answered the questions");
        adb.setMessage("Right answers: " + positiveAnswers + " \nWrong Answers: " + negativeAnswers);

        adb.setIcon(R.drawable.question_logo);


        adb.setPositiveButton(getResources().getString(R.string.start_again_button_label), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                iterator = 0;
                loadQuestion();
            } });

        adb.show();
    }



}
