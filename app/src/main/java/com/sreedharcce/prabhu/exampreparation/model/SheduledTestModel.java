package com.sreedharcce.prabhu.exampreparation.model;

/**
 * Created by Prabhu on 02-11-2017.
 */

public class SheduledTestModel {
    public SheduledTestModel(){

    }

    public SheduledTestModel(String examId, String examName, String examDesc, String examdate, String totalque, String totaldur, String totalmarks) {
        this.examId = examId;
        this.examName = examName;
        this.examDesc = examDesc;
        this.examdate = examdate;
        this.totalque = totalque;
        this.totaldur = totaldur;
        this.totalmarks = totalmarks;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDesc() {
        return examDesc;
    }

    public void setExamDesc(String examDesc) {
        this.examDesc = examDesc;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public String getTotalque() {
        return totalque;
    }

    public void setTotalque(String totalque) {
        this.totalque = totalque;
    }

    public String getTotaldur() {
        return totaldur;
    }

    public void setTotaldur(String totaldur) {
        this.totaldur = totaldur;
    }

    public String getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(String totalmarks) {
        this.totalmarks = totalmarks;
    }

    private String examId, examName, examDesc, examdate, totalque, totaldur, totalmarks;
}
