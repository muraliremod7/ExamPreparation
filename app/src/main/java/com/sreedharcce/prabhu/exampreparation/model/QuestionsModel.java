package com.sreedharcce.prabhu.exampreparation.model;

import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Prabhu on 22-10-2017.
 */

public class QuestionsModel {
    public static final String MODEL_TITLE_KEY = "modelTitleKey";
    public static final String MODEL_QUESTON_KEY = "modelQuestionKey";
    public static final String MODEL_DIRECTION_KEY = "modelDirectionKey";
    public static final String MODEL_CHOICES_KEY = "modelChoicesKey";
    private String qnum;
    private String mQuestionDirection;
    private String mQuestion;
    private String choice1,choice2,choice3,choice4,choice5;

    public String getManswer() {
        return manswer;
    }

    public void setManswer(String manswer) {
        this.manswer = manswer;
    }

    private String manswer;

    public QuestionsModel(String qnum, String mQuestionDirection, String mQuestion, String choice1, String choice2, String choice3, String choice4, String choice5,String manswer, ArrayList<String> choices) {
        this.qnum = qnum;
        this.mQuestionDirection = mQuestionDirection;
        this.mQuestion = mQuestion;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
        this.choices = choices;
        this.manswer = manswer;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    private ArrayList<String> choices;

    public QuestionsModel(String qnum, String mQuestionDirection, String mQuestion, String choice1, String choice2, String choice3, String choice4, String choice5) {
        this.qnum = qnum;
        this.mQuestionDirection = mQuestionDirection;
        this.mQuestion = mQuestion;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
    }
    public QuestionsModel(){

    }
    public String getQnum() {
        return qnum;
    }

    public void setQnum(String qnum) {
        this.qnum = qnum;
    }
    public String getmQuestionDirection() {
        return mQuestionDirection;
    }

    public void setmQuestionDirection(String mQuestionDirection) {
        this.mQuestionDirection = mQuestionDirection;
    }

    public String getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestion = mQuestion;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString(MODEL_TITLE_KEY, this.getQnum());
        bundle.putString(MODEL_QUESTON_KEY, this.getmQuestion());
        bundle.putString(MODEL_DIRECTION_KEY,this.getmQuestionDirection());
        bundle.putStringArrayList(MODEL_CHOICES_KEY,getChoices());
        return bundle;

    }

    public QuestionsModel toModel (Bundle bundle){

        QuestionsModel model = new QuestionsModel();
        model.setQnum(bundle.getString(MODEL_TITLE_KEY));
        model.setmQuestion(bundle.getString(MODEL_QUESTON_KEY));
        model.setmQuestionDirection(bundle.getString(MODEL_DIRECTION_KEY));
        model.setChoices(bundle.getStringArrayList(MODEL_CHOICES_KEY));
        return model;

    }

}
