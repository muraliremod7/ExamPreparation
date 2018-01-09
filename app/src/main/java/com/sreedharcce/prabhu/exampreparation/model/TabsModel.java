package com.sreedharcce.prabhu.exampreparation.model;

/**
 * Created by Prabhu on 09-11-2017.
 */

public class TabsModel {
    public TabsModel(){

    }

    public TabsModel(String mid, String mname, String mnoofqu, String mqfrom, String mqto, String mduration, String mqpsmarks, String mqngmarks, QuestionsModel model) {
        this.mid = mid;
        this.mname = mname;
        this.mnoofqu = mnoofqu;
        this.mqfrom = mqfrom;
        this.mqto = mqto;
        this.mduration = mduration;
        this.mqpsmarks = mqpsmarks;
        this.mqngmarks = mqngmarks;
        this.model = model;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMnoofqu() {
        return mnoofqu;
    }

    public void setMnoofqu(String mnoofqu) {
        this.mnoofqu = mnoofqu;
    }

    public String getMqfrom() {
        return mqfrom;
    }

    public void setMqfrom(String mqfrom) {
        this.mqfrom = mqfrom;
    }

    public String getMqto() {
        return mqto;
    }

    public void setMqto(String mqto) {
        this.mqto = mqto;
    }

    public String getMduration() {
        return mduration;
    }

    public void setMduration(String mduration) {
        this.mduration = mduration;
    }

    public String getMqpsmarks() {
        return mqpsmarks;
    }

    public void setMqpsmarks(String mqpsmarks) {
        this.mqpsmarks = mqpsmarks;
    }

    public String getMqngmarks() {
        return mqngmarks;
    }

    public void setMqngmarks(String mqngmarks) {
        this.mqngmarks = mqngmarks;
    }

    public QuestionsModel getModel() {
        return model;
    }

    public void setModel(QuestionsModel model) {
        this.model = model;
    }

    private String mid,mname,mnoofqu,mqfrom,mqto,mduration,mqpsmarks,mqngmarks;
    private QuestionsModel model;

}
