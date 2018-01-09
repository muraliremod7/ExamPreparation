package com.sreedharcce.prabhu.exampreparation.model;


import java.util.ArrayList;

/**
 * Created by Prabhu on 26-10-2017.
 */

public class ChoicesModel {

    private String choicetext;
    private String choiceimg;
    private String answer;

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }

    private ArrayList<String> choice;
    public ChoicesModel(String choicetext, String choiceimg, String answer, ArrayList<String> choice) {
        this.choicetext = choicetext;
        this.choiceimg = choiceimg;
        this.answer = answer;
        this.choice = choice;
    }


    public ChoicesModel(){

    }

    public String getChoicetext() {
        return choicetext;
    }

    public void setChoicetext(String choicetext) {
        this.choicetext = choicetext;
    }

    public String getChoiceimg() {
        return choiceimg;
    }

    public void setChoiceimg(String choiceimg) {
        this.choiceimg = choiceimg;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
