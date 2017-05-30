package com.example.user1.mytestapplication.tabs.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user1.mytestapplication.R;
import com.example.user1.mytestapplication.model.Question;

import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 5/29/2017.
 */

public class MultipleChoiceMode extends Fragment {

    static String TAG = "BinaryChoiceMode";
    public static MultipleChoiceMode instance;
    static List<Question> questionsList;
    Activity activity;
    View v;
    TextView question;
    RelativeLayout  questionDescriptionContainer;
    TextView questionDescription;
    LinearLayout answerButtonsContainer;
    Button firstVariantButton;
    Button secondVariantButton;
    Button thirdVariantButton;
    RelativeLayout correctAnswerContainer;
    TextView correctAnswer;
    Button loadNextButton;
    int iterator = 0;
    Question currentQuestion;
    int positiveAnswers;
    int negativeAnswers;


    public static MultipleChoiceMode createFragment(Activity activity, List<Question> questionList) {
        Log.d(TAG, "Create Fragment!" );
        MultipleChoiceMode fragment = new MultipleChoiceMode();
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

        v = inflater.inflate(R.layout.multiple_choice_mode_layout, null);

        question  = (TextView) v.findViewById(R.id.question);
        questionDescriptionContainer  = (RelativeLayout) v.findViewById(R.id.question_description_container);
        questionDescription  = (TextView) v.findViewById(R.id.question_description);
        answerButtonsContainer  = (LinearLayout) v.findViewById(R.id.answer_buttons_container);
        firstVariantButton = (Button) v.findViewById(R.id.first_variant_button);
        secondVariantButton = (Button) v.findViewById(R.id.second_variant_button);
        thirdVariantButton = (Button) v.findViewById(R.id.third_variant_button);

        correctAnswerContainer  = (RelativeLayout) v.findViewById(R.id.correct_answer_container);
        correctAnswer  = (TextView) v.findViewById(R.id.correct_answer);
        loadNextButton = (Button) v.findViewById(R.id.load_next_button);

        loadNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pressed Next");
                correctAnswerContainer.setVisibility(View.GONE);
                answerButtonsContainer.setVisibility(View.VISIBLE);
                loadQuestion();
            }
        });

        loadQuestion();

        return v;
    }

    public void loadQuestion() {
        Log.d(TAG," loadQuestion "+ iterator);
        if (iterator < questionsList.size()){
            Log.d(TAG," iterator:  "+ iterator + " from list: " + questionsList.size());
            currentQuestion = questionsList.get(iterator);

            question.setText(currentQuestion.getQuestion());
            if (currentQuestion.getQuestionDescription() != null && !currentQuestion.getQuestionDescription().equals("")) {
                questionDescriptionContainer.setVisibility(View.VISIBLE);
                questionDescription.setText(currentQuestion.getQuestionDescription());
            } else {
                questionDescriptionContainer.setVisibility(View.GONE);
            }
            answerButtonsContainer.removeAllViews();

            for(int i= 0; i < questionsList.get(iterator).getAnswerVariants().size(); i++ ){
                Log.i("TAG", i + "iteration " + " variants count: " + questionsList.get(iterator).getAnswerVariants().size() + " listSize: " + questionsList.size());
                Button myButton = new Button(getActivity());
                myButton.setText(questionsList.get(iterator).getAnswerVariants().get(i));
                myButton.setBackground(getResources().getDrawable(R.drawable.rounde_gray_background));
                myButton.setTextColor(getResources().getColor(R.color.white));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0,30,0,0);
                final int index = i;
                final int loadingIteration = iterator;
                myButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Log.i("TAG", "The index is" + index + " iteator" + loadingIteration);

                        String answ =  questionsList.get(loadingIteration).getAnswerVariants().get(index);
                        validateAnswer(answ);
                    }
                });

                answerButtonsContainer.addView(myButton, lp);
            }
                iterator++;
        }else{
            Log.d(TAG," 10 Questions! Right answers: " + positiveAnswers + "  Wrong Answers: " + negativeAnswers);
            showDialog();
        }
    }


    public boolean validateAnswer(String answer){
        boolean result =false;
        String rightAnswer = currentQuestion.getRightAnswer();

        if(rightAnswer.equals(answer)){
            Log.d(TAG," The answer is right Wooohooo");
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