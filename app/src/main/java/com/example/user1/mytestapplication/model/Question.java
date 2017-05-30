package com.example.user1.mytestapplication.model;

import java.util.List;

/**
 * Created by Alex on 5/29/2017.
 */

public class Question {

    String question;
    String questionDescription;
    boolean isMultiple;
    List<String> answerVariants;
    String rightAnswer;

    public Question() {
    }

    public Question(String question, String questionDescription, boolean isMultiple, List<String> answerVariants, String rightAnswer) {
        this.question = question;
        this.questionDescription = questionDescription;
        this.isMultiple = isMultiple;
        this.answerVariants = answerVariants;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public boolean isMultiple() {
        return isMultiple;
    }

    public void setMultiple(boolean multiple) {
        isMultiple = multiple;
    }

    public List<String> getAnswerVariants() {
        return answerVariants;
    }

    public void setAnswerVariants(List<String> answerVariants) {
        this.answerVariants = answerVariants;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", questionDescription='" + questionDescription + '\'' +
                ", isMultiple=" + isMultiple +
                ", answerVariants=" + answerVariants +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }
}
